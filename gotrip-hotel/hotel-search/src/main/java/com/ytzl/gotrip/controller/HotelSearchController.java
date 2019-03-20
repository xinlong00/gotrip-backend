package com.ytzl.gotrip.controller;

import com.ytzl.gotrip.bean.Hotel;
import com.ytzl.gotrip.service.HotelSearchService;
import com.ytzl.gotrip.utils.common.Dto;
import com.ytzl.gotrip.utils.common.DtoUtil;
import com.ytzl.gotrip.utils.common.Page;
import com.ytzl.gotrip.utils.exception.GotripException;
import com.ytzl.gotrip.vo.hotel.SearchHotelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@Api(description = "酒店搜索")
@RequestMapping("/api/hotellist")
public class HotelSearchController {

    @Resource
    private HotelSearchService hotelSearchService;

    @ApiOperation("查询酒店分页")
    @PostMapping("/searchItripHotelPage")
    public Dto searchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO) throws SolrServerException, IOException, GotripException {

        Page<Hotel> hotelPage = hotelSearchService.searchGotripHotelPage(searchHotelVO);
        return DtoUtil.returnDataSuccess(hotelPage);
    }

}
