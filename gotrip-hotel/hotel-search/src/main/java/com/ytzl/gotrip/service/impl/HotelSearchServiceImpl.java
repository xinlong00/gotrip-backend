package com.ytzl.gotrip.service.impl;

import com.ytzl.gotrip.BaseQuery;
import com.ytzl.gotrip.bean.Hotel;
import com.ytzl.gotrip.service.HotelSearchService;
import com.ytzl.gotrip.utils.common.EmptyUtils;
import com.ytzl.gotrip.utils.common.ErrorCode;
import com.ytzl.gotrip.utils.common.Page;
import com.ytzl.gotrip.utils.exception.GotripException;
import com.ytzl.gotrip.vo.hotel.SearchHotelVO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;


@Service("hotelSearchService")
public class HotelSearchServiceImpl implements HotelSearchService {

    @Resource
    private BaseQuery<Hotel> hotelBaseQuery;

    @Override
    public Page<Hotel> searchGotripHotelPage(SearchHotelVO searchHotelVO) throws GotripException, IOException, SolrServerException {
        if (EmptyUtils.isEmpty(searchHotelVO.getDestination())) {
            throw new GotripException("目的地不能为空", ErrorCode.AUTH_TOKEN_INVALID);
        }

        //拼接目的地
        SolrQuery solrQuery = new SolrQuery("*:*");
        StringBuffer query = new StringBuffer();
        query.append(" destination:").append(searchHotelVO.getDestination());
        //如果关键字不为空，则拼接关键字
        if (EmptyUtils.isNotEmpty(searchHotelVO.getKeywords())) {
            query.append(" AND keyword:").append(searchHotelVO.getKeywords());
        }
        //将查询条件设置到q查询中
        solrQuery.setQuery(query.toString());
        //如果酒店级别不为空，匹配酒店级别
        if (EmptyUtils.isNotEmpty(searchHotelVO.getHotelLevel())) {
            solrQuery.addFacetQuery(" hotelLevel:" + searchHotelVO.getHotelLevel());
        }
        //
        if (EmptyUtils.isNotEmpty(searchHotelVO.getTradeAreaIds())) {
            String[] split = searchHotelVO.getTradeAreaIds().split(",");
            for (int i = 0; i < split.length; i++) {
                String areaId = split[i];
                if (i> 0){
                    solrQuery.addFilterQuery(" OR tradingAreaIds:,"+areaId+",");
                }else{
                    solrQuery.addFilterQuery(" tradingAreaIds:,"+areaId+",");
                }
            }
        }
        if (EmptyUtils.isNotEmpty(searchHotelVO.getFeatureIds())) {
            String[] split = searchHotelVO.getTradeAreaIds().split(",");
            for (int i = 0; i < split.length; i++) {
                String areaId = split[i];
                if (i> 0){
                    solrQuery.addFilterQuery(" OR featureIds:,"+areaId+",");
                }else{
                    solrQuery.addFilterQuery(" featureIds:,"+areaId+",");
                }
            }
        }
        //最高价钱
        if (EmptyUtils.isNotEmpty(searchHotelVO.getMaxPrice())) {
            solrQuery.addFilterQuery(" minPrice:[0 TO " + searchHotelVO.getMaxPrice() + "]");
        }
        if (EmptyUtils.isNotEmpty(searchHotelVO.getMinPrice())) {
            solrQuery.addFilterQuery(" maxPrice:[" + searchHotelVO.getMinPrice() + " TO *]");
        }

        //排序
        if (EmptyUtils.isNotEmpty(searchHotelVO.getAscSort())) {
            solrQuery.setSort(searchHotelVO.getAscSort(),SolrQuery.ORDER.asc);
        }
        if (EmptyUtils.isNotEmpty(searchHotelVO.getDescSort())) {
            solrQuery.setSort(searchHotelVO.getDescSort(),SolrQuery.ORDER.desc);
        }
        //调用查询工具类返回 page，直接return
        return hotelBaseQuery.queryPage(solrQuery,searchHotelVO.getPageNo(),searchHotelVO.getPageSize(),Hotel.class);
    }
}
