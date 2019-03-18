package com.ytzl.gotrip.utils.common;

/**
 * 系统错误编码，根据业务定义如下
 * <br/>
 * 酒店主业务biz：1开头（10000）<br/>
 * 评论：10001 ——10100<br/>
 * 酒店详情：10101 ——10200<br/>
 * 订单：10201 ——10400<br/>
 * 搜索search：2开头（20000）<br/>
 * 认证auth：3开头（30000）<br/>
 * 支付trade：4开头（40000）<br/>
 *
 * @author hduser
 */
public class ErrorCode {

    /*认证模块错误码-start*/
    public final static String AUTH_UNKNOWN = "30000";
    public final static String AUTH_USER_ALREADY_EXISTS = "30001";//用户已存在
    public final static String AUTH_AUTHENTICATION_FAILED = "30002";//认证失败
    public final static String AUTH_PARAMETER_ERROR = "30003";//用户名密码参数错误，为空
    public final static String AUTH_ACTIVATE_FAILED = "30004";//邮件注册，激活失败
    public final static String AUTH_REPLACEMENT_FAILED = "30005";//置换token失败
    public final static String AUTH_TOKEN_INVALID = "30006";//token无效
    public static final String AUTH_ILLEGAL_USERCODE = "30007";//非法的用户名
    /*认证模块错误码-end*/

    /**
     * 主业务模块-start
     */
    public static final String BIZ_HOTEL_QUERY_HOTCITY_FAIL = "10202";//查询热门城市系统异常,获取失败
    public static final String BIZ_HOTEL_QUERY_HOTEL_FEATURE_FAIL = "10205";//查询酒店特色列表失败，系统异常
    public static final String BIZ_HOTEL_QUERY_TRADE_AREA_FAIL = "10204";//根据城市查询商圈失败，系统异常
    public static final String BIZ_GET_VIDEO_DESC_HOTELID_FAIL = "100215";//酒店Id不能为空
    public static final String BIZ_GET_VIDEO_DESC_FAIL = "100214";//获取酒店视频文字描述失败
    public static final String BIZ_QUERY_HOTEL_DETAILS_ID_FAIL = "10210";//酒店Id不能为空
    public static final String BIZ_QUERY_HOTEL_DETAILS_FAIL = "10211";//系统异常
    /**主业务模块-end*/

    /**
     * search搜索模块-start
     */
    public static final String SEARCH_ITRIP_HOTEL_PAGE_DESTINATION_FAIL = "20002";//分页查询酒店列表，目的地字段不能为空
    public static final String SEARCH_ITRIP_HOTEL_PAGE_FAIL = "20001";//分页查询酒店列表失败
    public static final String SEARCH_ITRIP_HOTEL_LIST_BY_HOT_CITY_CITYID_FAIL = "20004";//根据热门城市查询酒店列表，酒店Id不能为空
    public static final String SEARCH_ITRIP_HOTEL_LIST_BY_HOT_CITY_FAIL = "20003";//根据热门城市查询酒店列表失败
    /**search搜索模块-end*/
}
