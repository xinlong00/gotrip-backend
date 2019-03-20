package com.ytzl.gotrip.service;

import com.ytzl.gotrip.bean.Hotel;
import com.ytzl.gotrip.utils.common.Page;
import com.ytzl.gotrip.utils.exception.GotripException;
import com.ytzl.gotrip.vo.hotel.SearchHotelVO;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface HotelSearchService {
    /**
     * 查询酒店分页
     * @param searchHotelVO
     * @return
     */
    Page<Hotel> searchGotripHotelPage(SearchHotelVO searchHotelVO) throws GotripException, IOException, SolrServerException;
}
