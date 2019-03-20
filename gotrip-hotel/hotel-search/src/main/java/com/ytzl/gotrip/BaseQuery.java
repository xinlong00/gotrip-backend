package com.ytzl.gotrip;


import com.ytzl.gotrip.utils.common.Constants;
import com.ytzl.gotrip.utils.common.EmptyUtils;
import com.ytzl.gotrip.utils.common.Page;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

/**
 * Solr查询数据
 */
public class BaseQuery<T> {

    private HttpSolrClient solrClient;

    public BaseQuery(String url){
        solrClient = new HttpSolrClient(url);
        //数据解析器
        solrClient.setParser(new XMLResponseParser());
        //Solr服务器连接超时时间
        solrClient.setConnectionTimeout(10000);
    }

    public Page<T> queryPage(SolrQuery solrQuery, Integer pageIndex, Integer pageSize, Class clazz) throws IOException, SolrServerException {
        //判断非空
        if (EmptyUtils.isEmpty(solrQuery) || EmptyUtils.isEmpty(clazz)) {
            return new Page<>();
        }
        pageIndex = EmptyUtils.isEmpty(pageIndex) ?
                Constants.DEFAULT_PAGE_NO : pageIndex;
        pageSize = EmptyUtils.isEmpty(pageSize) ?
                Constants.DEFAULT_PAGE_SIZE : pageSize;
        //查询
        //分页
        solrQuery.setStart((pageIndex - 1) * pageSize);
        solrQuery.setRows(pageSize);
        QueryResponse queryResponse = solrClient.query(solrQuery);
        //获取数据总条数
        int numFound = Long.valueOf(queryResponse.getResults().getNumFound()).intValue();
        Page<T> page = new Page<>(pageIndex,pageSize,numFound);
        List list = queryResponse.getBeans(clazz);
        page.setRows(list);
        return page;
    }
}
