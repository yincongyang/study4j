package com.yincongyang.httpclient;

/**
 * 用来测试的URL
 *
 * Created by yincongyang on 17/9/27.
 */
public interface TESTURL {

    String SN_YFB_QUERY = "https://paymentsandbox.suning.com/epps-pag/apiGateway/merchantOrder/queryMerchantOrder.do";

    String SN_YFB_ORDER = "https://ebanksandbox.suning.com/epps-ebpg/singleWithhold/paymentOrder.do";

    String TXZF_TFB_ORDER = "http://apitest.tfb8.com/cgi-bin/v2.0/api_acp_single.cgi";

    String TXZF_TFB_QUERY = "http://apitest.tfb8.com/cgi-bin/v2.0/api_acp_single_query.cgi";

    String NOT_SECURITY_SSL = "https://kyfw.12306.cn/otn/leftTicket/init";

    String ZHIHU = "https://www.zhihu.com/";

    String GITBUB = "https://github.com/";

    String GOOGLE = "https://www.google.com.hk";

    String BAIDU = "https://www.baidu.com";
}

