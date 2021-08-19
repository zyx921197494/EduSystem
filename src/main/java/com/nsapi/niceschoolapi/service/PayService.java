package com.nsapi.niceschoolapi.service;


import com.alipay.api.AlipayApiException;
import com.nsapi.niceschoolapi.common.base.AliPayBean;

public interface PayService {
    String aliPay(AliPayBean aliPayBean) throws AlipayApiException;
}
