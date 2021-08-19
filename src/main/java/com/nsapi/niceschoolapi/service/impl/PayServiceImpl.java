package com.nsapi.niceschoolapi.service.impl;

import com.alipay.api.AlipayApiException;
import com.nsapi.niceschoolapi.common.base.AliPayBean;
import com.nsapi.niceschoolapi.common.util.Alipay;
import com.nsapi.niceschoolapi.service.PayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private Alipay alipay;

    @Override
    public String aliPay(AliPayBean aliPayBean) throws AlipayApiException {
        System.out.println("PayServiceImpl.aliPay");
        System.out.println("aliPayBean = " + aliPayBean);
        return alipay.pay(aliPayBean);
    }
}
