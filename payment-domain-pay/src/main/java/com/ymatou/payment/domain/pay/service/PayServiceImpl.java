package com.ymatou.payment.domain.pay.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ymatou.payment.domain.pay.model.BussinessOrder;
import com.ymatou.payment.domain.pay.model.OrderStatus;
import com.ymatou.payment.domain.pay.model.PayStatus;
import com.ymatou.payment.domain.pay.model.Payment;
import com.ymatou.payment.domain.pay.repository.BussinessOrderRepository;
import com.ymatou.payment.domain.pay.repository.PaymentRepository;
import com.ymatou.payment.facade.model.AcquireOrderReq;
import com.ymatou.payment.infrastructure.db.model.BussinessOrderPo;
import com.ymatou.payment.infrastructure.db.model.PaymentPo;

@Component
public class PayServiceImpl implements PayService {

    @Resource
    private PaymentRepository paymentRepository;

    @Resource
    private BussinessOrderRepository bussinessOrderRepository;



    /*
     * (non-Javadoc)
     * 
     * @see com.payment.domain.pay.service.PayService#GetByPaymentId(java.lang.String)
     */
    @Override
    public Payment getPaymentByPaymentId(String paymentId) {
        return paymentRepository.getByPaymentId(paymentId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ymatou.payment.domain.pay.service.PayService#GetPaymentByBussinessOrderId(java.lang.
     * String)
     */
    @Override
    public Payment getPaymentByBussinessOrderId(String bussinessOrderId) {
        return paymentRepository.getByBussinessOrderId(bussinessOrderId);
    }

    @Override
    public BussinessOrder getBussinessOrderByOrderId(String orderId) {
        return bussinessOrderRepository.getByOrderId(orderId);
    }

    @Override
    public Payment createPayment(AcquireOrderReq req) {
        BussinessOrderPo bussinessOrderPo = buildBussinessOrder(req);
        PaymentPo paymentPo = buildPayment(req, bussinessOrderPo);

        paymentRepository.acquireOrder(paymentPo, bussinessOrderPo);

        Payment payment = Payment.convertFromPo(paymentPo);
        payment.setBussinessOrder(BussinessOrder.convertFromPo(bussinessOrderPo));

        // 将请求带入到模型中
        payment.setAcquireOrderReq(req);

        return payment;
    }

    /**
     * 构建支付单
     * 
     * @param req
     * @param bussinessOrder
     * @return
     */
    private PaymentPo buildPayment(AcquireOrderReq req, BussinessOrderPo bussinessOrder) {
        PaymentPo payment = new PaymentPo();
        payment.setBankId(req.getBankId());
        payment.setBussinessOrderId(bussinessOrder.getBussinessOrderId());
        payment.setPayStatus(PayStatus.Init.getIndex());
        payment.setPayCurrencyType(req.getCurrency());
        payment.setPayPrice(bussinessOrder.getOrderPrice());
        payment.setPayType(req.getPayType());

        return payment;
    }


    /**
     * 构建商户订单信息
     * 
     * @param req
     * @return
     */
    private BussinessOrderPo buildBussinessOrder(AcquireOrderReq req) {
        BussinessOrderPo bo = new BussinessOrderPo();
        bo.setBussinessOrderId(UUID.randomUUID().toString());
        bo.setAppId(req.getAppId());
        bo.setTraceId(req.getTraceId());
        bo.setBizCode(req.getBizCode());
        bo.setCallbackUrl(req.getCallbackUrl());
        bo.setClientIp(req.getUserIp());
        bo.setCodePage(req.getEncoding());
        bo.setCurrencyType(req.getCurrency());
        bo.setExt(req.getExt());
        bo.setMemo(req.getMemo());
        bo.setNotifyUrl(req.getNotifyUrl());
        bo.setOrderId(req.getOrderId());
        bo.setOrderPrice(new BigDecimal(req.getPayPrice()));
        bo.setOrderStatus(OrderStatus.Init.getIndex());
        bo.setOrderTime(req.getOrderTime());
        bo.setOriginAppId(req.getOriginAppId());
        bo.setPayType(req.getPayType());
        bo.setProductDesc(req.getProductDesc());
        bo.setProductUrl(req.getProductUrl());
        bo.setProductName(req.getProductName());
        bo.setSignMethod(req.getSignMethod());
        bo.setThirdPartyUserId(req.getThirdPartyUserId());
        bo.setThirdPartyUserType(0); // 0-代表JAVA版，null-代表.NET版本
        bo.setUserId(req.getUserId());
        bo.setVersion(req.getVersion());

        return bo;
    }


    /*
     * (non-Javadoc)
     * 
     * @see com.ymatou.payment.domain.pay.service.PayService#getBussinessOrderById(java.lang.String)
     */
    @Override
    public BussinessOrder getBussinessOrderById(String bussinessOrderId) {
        return bussinessOrderRepository.getBussinessOrderById(bussinessOrderId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ymatou.payment.domain.pay.service.PayService#setPaymentOrderPaid(com.ymatou.payment.
     * infrastructure.db.model.PaymentPo, java.lang.String)
     */
    @Override
    public void setPaymentOrderPaid(Payment payment, String traceId) {
        PaymentPo paymentPo = new PaymentPo();
        paymentPo.setPaymentId(payment.getPaymentid());
        paymentPo.setInstitutionPaymentId(payment.getInstitutionpaymentid());
        paymentPo.setPayStatus(payment.getPaystatus());
        paymentPo.setActualPayPrice(payment.getActualpayprice());
        paymentPo.setActualPayCurrencyType(payment.getActualpaycurrencytype());
        paymentPo.setBankId(payment.getBankid());
        paymentPo.setCardType(payment.getCardtype());
        paymentPo.setPayTime(payment.getPaytime());
        paymentPo.setLastUpdatedTime(new Date());
        paymentPo.setExchangeRate(payment.getExchangerate());
        paymentPo.setCheckStatus(payment.getCheckstatus());

        paymentRepository.setPaymentOrderPaid(paymentPo, traceId);
    }

}
