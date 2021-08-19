package com.nsapi.niceschoolapi;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nsapi.niceschoolapi.common.config.AlipayConfig;
import com.nsapi.niceschoolapi.entity.Menu;
import com.nsapi.niceschoolapi.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.alipay.api.AlipayConstants.CHARSET;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NiceschoolapiApplicationTests {

    @Test
    public void alipayTest() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2021000118604263", "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCh+KV5ZyHRlvyUVWvyDs6FLFs2xhv6z8XU/Jp3AhXT/0BPjoQ4DvY3XkfU5bJx2htqxinqRM9WcxTRKS+4Hxlbq6YFli5puKUSQbpMvoNUjQmeU+wdh9FJOWYNV/i0w76FS+lUgWsbOtpktqxC205harTCOabr7jJZHBRzcCdjGTxIIegTeQ8XlHJRQdb4fP/ywqMczH3uIgr5WMo9rmsv4d/RFE7OI+SbcABz1AxW3RAI2KwS5dMXpuupT+snrcMOXCjLF1hXYe3ly/JtQriN1PrW3VmU5tlCB/mJOE/mEApYpebouV9/RxpL79UcZVXGwwibr5kqpgdBvT52BwVjAgMBAAECggEAcbxsVie5c7eWfYLOXhdPpexT0M6kbybfQIRw4wiv00H2IudsqQC4nICz6CezeocxoXStkjfncEk3YPiO7bfI0rphxqKCzPI/1+YXsDRHfGPdUTSVJNduewC3TL9tgWHCs09u9Uw7rc9R9E7h/8CH9Zg8ffaF5D5LVI+HPNiQj+DWjRSXwu4G/BNCiJxiCV9vI1X1S3WERQvY9fHi+5dbPhpPRGl7aJid61C/D0uuqPOBmRWMrGLGhoWBq0dBL97mKT23G73vsVmrV0zSkyWQiHncSH5E3lXtkZ54VGBkBnTyHsZRNbRR38ox97OPW9RgsSOp8ChIQ+VUotmKXyUeoQKBgQDP5vRVroNA1euAU4OlgBMlu9aZoXvScVW0wZ92T43JAwD/n66D5n+ax4tnRhrZVarfhUGDD9pwrJ3e6IR0LJqzR/3c25gvXjbxl8F8zn/wdD2mvRF/FcZhJ6k0QD5fSZFQXqGKvc56UfZWsinGU3AED8ZvjuQiqYSpY7NtXRZFLwKBgQDHcW0YJiKWMavNGXOjE0BZ4GGKxofgaKkDUm6JUyg6Rr94FjPW9BR8t3DsZdkTBrPJ3bC3kIVIVR4klGu3HRLlAd5lEj9NYVcmfNtBFdOELY8z3jGC+rHFHe4fzzEIF70ur4ozeFfclmKfO08QR3rj5Y51pjoG2NA8YNdMvlYeDQKBgAe76b7+PPe+fYJKmQ0hFJVExZj1fuu3N57/zPTTxMyDhhf8UrupaXnSjocK/pizLSYfiO5dOp0SbtUKBVNlDWQ5+2Gp8aFhed3G7XW9INlLGbgINIxrvPOETDEbaraUwAkjXBMTOIkoX2eePRuZowMjqxgfUrFPaRX8FYKuAdQVAoGAMQay2JLXQbBoGPy+rkGXOgCsOZ7f28web9Cdl2g3pUuq2WwrxhUtzBKT0aifmcu1+lNik10VRliWFkvdxp+A+4ZgEtxPb0gUUkOy6ebhncZNQ2TFKvtRI7Fdzbfyx5h+SrGf+VMF1hWpnJbJsqi/jrHpOQZpkTFOzujHQ/gt1ykCgYAhEO1T7c0sy53F+8YbbllkPMyGYS2HGi6yymn2oVpJVPwaJsGZOn33qLQtWhs0uDnB3pkY7k8szCIbaILuEF7tJgVJsmiTzvXW8YSQCSa91CCqRk1cG/32KaHiCcfHcVeoXI1gh6vKjmIxu/oe4BDr+3O9yq33fUC7GrwdsF+C2A==", "json", "utf-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAofileWch0Zb8lFVr8g7OhSxbNsYb+s/F1PyadwIV0/9AT46EOA72N15H1OWycdobasYp6kTPVnMU0SkvuB8ZW6umBZYuabilEkG6TL6DVI0JnlPsHYfRSTlmDVf4tMO+hUvpVIFrGzraZLasQttOYWq0wjmm6+4yWRwUc3AnYxk8SCHoE3kPF5RyUUHW+Hz/8sKjHMx97iIK+VjKPa5rL+Hf0RROziPkm3AAc9QMVt0QCNisEuXTF6brqU/rJ63DDlwoyxdYV2Ht5cvybUK4jdT61t1ZlObZQgf5iThP5hAKWKXm6Llff0caS+/VHGVVxsMIm6+ZKqYHQb0+dgcFYwIDAQAB", "RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"20150320010101001\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "\"total_amount\":88.88," +
                "\"subject\":\"Iphone6 16G\"," +
                "\"body\":\"Iphone6 16G\"," +
                "\"time_expire\":\"2016-12-31 10:05:01\"," +
                "      \"goods_detail\":[{" +
                "        \"goods_id\":\"apple-01\"," +
                "\"alipay_goods_id\":\"20010001\"," +
                "\"goods_name\":\"ipad\"," +
                "\"quantity\":1," +
                "\"price\":2000," +
                "\"goods_category\":\"34543238\"," +
                "\"categories_tree\":\"124868003|126232002|126252004\"," +
                "\"body\":\"特价手机\"," +
                "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
                "        }]," +
                "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "\"extend_params\":{" +
                "\"sys_service_provider_id\":\"2088511833207846\"," +
                "\"hb_fq_num\":\"3\"," +
                "\"hb_fq_seller_percent\":\"100\"," +
                "\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
                "\"card_type\":\"S0JP0000\"," +
                "\"specified_seller_name\":\"XXX的跨境小铺\"" +
                "    }," +
                "\"goods_type\":\"0\"," +
                "\"timeout_express\":\"90m\"," +
                "\"promo_params\":\"{\\\"storeIdType\\\":\\\"1\\\"}\"," +
                "\"royalty_info\":{" +
                "\"royalty_type\":\"ROYALTY\"," +
                "        \"royalty_detail_infos\":[{" +
                "          \"serial_no\":1," +
                "\"trans_in_type\":\"userId\"," +
                "\"batch_no\":\"123\"," +
                "\"out_relation_id\":\"20131124001\"," +
                "\"trans_out_type\":\"userId\"," +
                "\"trans_out\":\"2088101126765726\"," +
                "\"trans_in\":\"2088101126708402\"," +
                "\"amount\":0.1," +
                "\"desc\":\"分账测试1\"," +
                "\"amount_percentage\":\"100\"" +
                "          }]" +
                "    }," +
                "\"sub_merchant\":{" +
                "\"merchant_id\":\"2088000603999128\"," +
                "\"merchant_type\":\"alipay: 支付宝分配的间连商户编号, merchant: 商户端的间连商户编号\"" +
                "    }," +
                "\"merchant_order_no\":\"20161008001\"," +
                "\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                "\"store_id\":\"NJ_001\"," +
                "\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                "\"qr_pay_mode\":\"1\"," +
                "\"qrcode_width\":100," +
                "\"settle_info\":{" +
                "        \"settle_detail_infos\":[{" +
                "          \"trans_in_type\":\"cardAliasNo\"," +
                "\"trans_in\":\"A0001\"," +
                "\"summary_dimension\":\"A0001\"," +
                "\"settle_entity_id\":\"2088xxxxx;ST_0001\"," +
                "\"settle_entity_type\":\"SecondMerchant、Store\"," +
                "\"amount\":0.1" +
                "          }]," +
                "\"settle_period_time\":\"7d\"" +
                "    }," +
                "\"invoice_info\":{" +
                "\"key_info\":{" +
                "\"is_support_invoice\":true," +
                "\"invoice_merchant_name\":\"ABC|003\"," +
                "\"tax_num\":\"1464888883494\"" +
                "      }," +
                "\"details\":\"[{\\\"code\\\":\\\"100294400\\\",\\\"name\\\":\\\"服饰\\\",\\\"num\\\":\\\"2\\\",\\\"sumPrice\\\":\\\"200.00\\\",\\\"taxRate\\\":\\\"6%\\\"}]\"" +
                "    }," +
                "\"agreement_sign_params\":{" +
                "\"personal_product_code\":\"GENERAL_WITHHOLDING_P\"," +
                "\"sign_scene\":\"INDUSTRY|CARRENTAL\"," +
                "\"external_agreement_no\":\"test\"," +
                "\"external_logon_id\":\"13852852877\"," +
                "\"sign_validity_period\":\"2m\"," +
                "\"third_party_type\":\"PARTNER\"," +
                "\"buckle_app_id\":\"1001164\"," +
                "\"buckle_merchant_id\":\"268820000000414397785\"," +
                "\"promo_params\":\"{\\\"key\\\",\\\"value\\\"}\"" +
                "    }," +
                "\"integration_type\":\"PCWEB\"," +
                "\"request_from_url\":\"https://\"," +
                "\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"," +
                "\"ext_user_info\":{" +
                "\"name\":\"李明\"," +
                "\"mobile\":\"16587658765\"," +
                "\"cert_type\":\"IDENTITY_CARD\"," +
                "\"cert_no\":\"362334768769238881\"," +
                "\"min_age\":\"18\"," +
                "\"fix_buyer\":\"F\"," +
                "\"need_check_info\":\"F\"" +
                "    }" +
                "  }");
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    public void payTest(HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2021000118604263", "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCh+KV5ZyHRlvyUVWvyDs6FLFs2xhv6z8XU/Jp3AhXT/0BPjoQ4DvY3XkfU5bJx2htqxinqRM9WcxTRKS+4Hxlbq6YFli5puKUSQbpMvoNUjQmeU+wdh9FJOWYNV/i0w76FS+lUgWsbOtpktqxC205harTCOabr7jJZHBRzcCdjGTxIIegTeQ8XlHJRQdb4fP/ywqMczH3uIgr5WMo9rmsv4d/RFE7OI+SbcABz1AxW3RAI2KwS5dMXpuupT+snrcMOXCjLF1hXYe3ly/JtQriN1PrW3VmU5tlCB/mJOE/mEApYpebouV9/RxpL79UcZVXGwwibr5kqpgdBvT52BwVjAgMBAAECggEAcbxsVie5c7eWfYLOXhdPpexT0M6kbybfQIRw4wiv00H2IudsqQC4nICz6CezeocxoXStkjfncEk3YPiO7bfI0rphxqKCzPI/1+YXsDRHfGPdUTSVJNduewC3TL9tgWHCs09u9Uw7rc9R9E7h/8CH9Zg8ffaF5D5LVI+HPNiQj+DWjRSXwu4G/BNCiJxiCV9vI1X1S3WERQvY9fHi+5dbPhpPRGl7aJid61C/D0uuqPOBmRWMrGLGhoWBq0dBL97mKT23G73vsVmrV0zSkyWQiHncSH5E3lXtkZ54VGBkBnTyHsZRNbRR38ox97OPW9RgsSOp8ChIQ+VUotmKXyUeoQKBgQDP5vRVroNA1euAU4OlgBMlu9aZoXvScVW0wZ92T43JAwD/n66D5n+ax4tnRhrZVarfhUGDD9pwrJ3e6IR0LJqzR/3c25gvXjbxl8F8zn/wdD2mvRF/FcZhJ6k0QD5fSZFQXqGKvc56UfZWsinGU3AED8ZvjuQiqYSpY7NtXRZFLwKBgQDHcW0YJiKWMavNGXOjE0BZ4GGKxofgaKkDUm6JUyg6Rr94FjPW9BR8t3DsZdkTBrPJ3bC3kIVIVR4klGu3HRLlAd5lEj9NYVcmfNtBFdOELY8z3jGC+rHFHe4fzzEIF70ur4ozeFfclmKfO08QR3rj5Y51pjoG2NA8YNdMvlYeDQKBgAe76b7+PPe+fYJKmQ0hFJVExZj1fuu3N57/zPTTxMyDhhf8UrupaXnSjocK/pizLSYfiO5dOp0SbtUKBVNlDWQ5+2Gp8aFhed3G7XW9INlLGbgINIxrvPOETDEbaraUwAkjXBMTOIkoX2eePRuZowMjqxgfUrFPaRX8FYKuAdQVAoGAMQay2JLXQbBoGPy+rkGXOgCsOZ7f28web9Cdl2g3pUuq2WwrxhUtzBKT0aifmcu1+lNik10VRliWFkvdxp+A+4ZgEtxPb0gUUkOy6ebhncZNQ2TFKvtRI7Fdzbfyx5h+SrGf+VMF1hWpnJbJsqi/jrHpOQZpkTFOzujHQ/gt1ykCgYAhEO1T7c0sy53F+8YbbllkPMyGYS2HGi6yymn2oVpJVPwaJsGZOn33qLQtWhs0uDnB3pkY7k8szCIbaILuEF7tJgVJsmiTzvXW8YSQCSa91CCqRk1cG/32KaHiCcfHcVeoXI1gh6vKjmIxu/oe4BDr+3O9yq33fUC7GrwdsF+C2A==", "json", "utf-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAofileWch0Zb8lFVr8g7OhSxbNsYb+s/F1PyadwIV0/9AT46EOA72N15H1OWycdobasYp6kTPVnMU0SkvuB8ZW6umBZYuabilEkG6TL6DVI0JnlPsHYfRSTlmDVf4tMO+hUvpVIFrGzraZLasQttOYWq0wjmm6+4yWRwUc3AnYxk8SCHoE3kPF5RyUUHW+Hz/8sKjHMx97iIK+VjKPa5rL+Hf0RROziPkm3AAc9QMVt0QCNisEuXTF6brqU/rJ63DDlwoyxdYV2Ht5cvybUK4jdT61t1ZlObZQgf5iThP5hAKWKXm6Llff0caS+/VHGVVxsMIm6+ZKqYHQb0+dgcFYwIDAQAB", "RSA2");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl("http://templates/jsp/alipay.trade.close.jsp");
        alipayRequest.setNotifyUrl("http://templates/jsp/alipay.trade.close.jsp"); //在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }"); //填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @Autowired
    private AlipayConfig alipayConfig;

    @Test
    public void configTest(){
        System.out.println("alipayConfig = " + alipayConfig);
    }

    @Test
    public void test(){
        System.out.println(System.currentTimeMillis());
    }


    @Autowired
    MenuService menuService;

    @Test
    public void contextLoads() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        Object o = menuService.getObj(wrapper.select("max(sort) as sort").isNull("parent_id"));
        System.out.println(o.toString());

    }

    public static void main(String[] args) throws ParseException {
        String strDate = "2018-07-18T08:12:08.000+0000";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+0000'");
        Date date = format.parse(strDate);
        System.out.println(date.toString());
    }

}
