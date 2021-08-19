package com.nsapi.niceschoolapi.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.nsapi.niceschoolapi.common.base.AliPayBean;
import com.nsapi.niceschoolapi.common.config.AlipayConfig;
import com.nsapi.niceschoolapi.common.config.MySysUser;
import com.nsapi.niceschoolapi.entity.LayuiResult;
import com.nsapi.niceschoolapi.entity.TuitionDB;
import com.nsapi.niceschoolapi.service.PayService;
import com.nsapi.niceschoolapi.service.StuTuitionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("tuition")
public class stuTuitionController {

    @Autowired
    private StuTuitionService stuTuitionService;

    @Autowired
    private AlipayConfig alipayConfig;

    //管理员进入学费查询页面
    @RequestMapping("/tuitionPage")
    public String tuitionPage() {
        return "view/student/selTuition";
    }

    //学生进入学费缴纳页面
    @RequestMapping("/stuTuitionPage")
    public String stuTuitionPage() {
        return "view/student/stuTuition";
    }


    //管理员查看所有学生的学费缴纳情况
    @ResponseBody
    @PostMapping("listTuitions")
    public LayuiResult listTuitions() {
        LayuiResult<TuitionDB> result = new LayuiResult<>();
        List<TuitionDB> list = stuTuitionService.listTuitions();
        if (list.isEmpty()) {
            result.setCode(0);
        } else {
            result.setCode(1);
        }
        result.setData(list);
        return result;
    }

    //查单个学生的学费缴纳情况
    @ResponseBody
    @PostMapping("getTuition")
    public TuitionDB getTuition() {
        String stuid = MySysUser.loginName();
        return stuTuitionService.getTuition(Integer.parseInt(stuid));
    }

//    //添加学生时为该学生添加一条缴费记录
//    @ResponseBody
//    @PostMapping("addTuition")
//    public Integer addTuition(@RequestBody TuitionDB tuition) {
//        return stuTuitionService.addTuition(tuition);
//    }

    //学生缴纳学费
    @ResponseBody
    @PostMapping("updateTuition/{sid}")
    public Integer updateTuition(@PathVariable("sid") Integer sid) {
        return stuTuitionService.updateTuition(sid);
    }

    @Autowired
    private PayService payService;

    @GetMapping(value = "aliPay")
    @ResponseBody
    public String alipay(String outTradeNo, String subject, String totalAmount, String body) throws AlipayApiException {
        System.out.println("outTradeNo = " + outTradeNo);
        System.out.println("subject = " + subject);
        System.out.println("totalAmount = " + totalAmount);
        System.out.println("body = " + body);
        AliPayBean alipayBean = new AliPayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        System.out.println("alipayBean = " + alipayBean);
        return payService.aliPay(alipayBean);
    }

    @RequestMapping(value = "success")
    @ResponseBody
    public void success() {
        System.out.println("支付成功！");
    }

    @RequestMapping("index")
    public ModelAndView alipay() {
        return new ModelAndView("tuition/index");
    }

    @RequestMapping("payServlet")
    @ResponseBody
    public String pay(
//            @RequestParam("orderId") String orderId,
            @RequestParam("subject") String subject,
            @RequestParam("total_amount") String total_amount) throws AlipayApiException, InterruptedException {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getAppId(), alipayConfig.getPrivateKey(), "JSON", alipayConfig.getCharset(), alipayConfig.getPublicKey(), alipayConfig.getSignType());
        System.out.println("stuTuitionController.pay");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for ( int i = 0; i < 16; i++ ) {
            builder.append(random.nextInt(10));
        }
        String orderId = builder.append(System.currentTimeMillis()).toString();
//        String orderId = "63425431253412452342779";
        try {
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderId + "\"," +
                    "\"total_amount\":\"" + total_amount + "\"," +
                    "\"subject\":\"" + subject + "\"," +
                    "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}"
            );
//            alipayRequest.setBizContent("{\n" +
//                    "  \"out_trade_no\": \" " + orderId + "\",\n" +
//                    "  \"product_code\": \"FAST_INSTANT_TRADE_PAY\",\n" +
//                    "  \"total_amount\": \"" + total_amount + "\",\n" +
//                    "  \"subject\" : \"" + subject + "\"\n" +
//                    "}"
//            );
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            stuTuitionService.updateTuition(Integer.parseInt(MySysUser.loginName()));
            System.out.println("result = " + result);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        finally {
//            boolean flag = false;
//            for ( int i = 0; i < 5; i++ ) {
//                AlipayClient client = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getAppId(), alipayConfig.getPrivateKey(), "JSON", alipayConfig.getCharset(), alipayConfig.getPublicKey(), alipayConfig.getSignType());
//                AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//                request.setApiVersion("1.0");
//
//                request.setBizContent("{\"out_trade_no\":\"" + orderId + "\"" +
//                                "\"version\": \"1.0\"}"
//
//                );
//                AlipayTradeQueryResponse response = client.execute(request);
//                if ("10000".equals(response.getCode())) {
//                    log.info("第{}次 尝试查询 用户{} 支付结果===>>支付成功", i, MySysUser.loginName());
//                    flag = true;
//                    break;
//                } else {
//                    log.info("第{}次 尝试查询 用户{} 支付结果===>>支付失败", i, MySysUser.loginName());
//                    Thread.sleep(20000);
//                }
//            }
//            if (flag) {
//                stuTuitionService.updateTuition(Integer.parseInt(MySysUser.loginName()));
//            }
//        }
        return null;
    }

    @RequestMapping("alipay-callback-return-sult")
    @ResponseBody
    public String success(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("stuTuitionController.success");
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] out_trade_nos = parameterMap.get("out_trade_no");
        return out_trade_nos[0];
    }

    @RequestMapping("alipay-callback-notify-url")
    @ResponseBody
    public String fail(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("stuTuitionController.fail");
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] out_trade_nos = parameterMap.get("out_trade_no");
        return out_trade_nos[0];
    }

}
