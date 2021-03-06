/*
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/). All rights reserved.
 */
package com.ymatou.payment.domain.refund.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymatou.payment.domain.pay.model.BussinessOrder;
import com.ymatou.payment.domain.pay.model.Payment;
import com.ymatou.payment.domain.pay.repository.BussinessOrderRepository;
import com.ymatou.payment.domain.pay.repository.PaymentRepository;
import com.ymatou.payment.domain.refund.DomainConfig;
import com.ymatou.payment.domain.refund.repository.RefundPository;
import com.ymatou.payment.facade.BizException;
import com.ymatou.payment.facade.ErrorCode;
import com.ymatou.payment.facade.constants.OrderStatusEnum;
import com.ymatou.payment.facade.constants.PayStatusEnum;
import com.ymatou.payment.facade.constants.PayTypeEnum;
import com.ymatou.payment.facade.constants.RefundStatusEnum;
import com.ymatou.payment.facade.model.AcquireRefundDetail;
import com.ymatou.payment.facade.model.AcquireRefundRequest;
import com.ymatou.payment.facade.model.TradeDetail;
import com.ymatou.payment.facade.model.TradeRefundDetail;
import com.ymatou.payment.infrastructure.db.model.RefundRequestPo;

/**
 * 
 * @author qianmin 2016年5月13日 上午11:01:57
 * 
 */
@Component
public class SubmitRefundServiceImpl implements SubmitRefundService {

    private static final Logger logger = LoggerFactory.getLogger(SubmitRefundServiceImpl.class);

    @Autowired
    private RefundPository refundPository;

    @Autowired
    private BussinessOrderRepository bussinessOrderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DomainConfig domainConfig;

    @Override
    public List<TradeRefundDetail> generateTradeRefundDetailList(List<String> tradeNos) {
        List<TradeRefundDetail> tradeRefundDetails = new ArrayList<>();

        String refundSupportDaysStr = domainConfig.getRefundSupportDays(); // 获取退款允许的天数
        int refundSupportDays = StringUtils.isBlank(refundSupportDaysStr) ? 90 : Integer.parseInt(refundSupportDaysStr);
        LocalDate validDate = LocalDate.now().minusDays(refundSupportDays); // 获取有效的订单日期

        logger.info("generate tradeRefundDetailList begin.");
        for (String tradeNo : tradeNos) {
            // 根据tradeNo获取已支付，退款有效期内的BussinessOrder
            BussinessOrder bussinessOrder = bussinessOrderRepository.getBussinessOrderCanRefund(
                    tradeNo, OrderStatusEnum.Paied.getIndex(), Date.valueOf(validDate));

            if (bussinessOrder != null) {
                logger.info(bussinessOrder.toString());

                // 根据bussinessorderid找到支付单Payment
                Payment payment = paymentRepository.getPaymentCanRefund(bussinessOrder.getBussinessOrderId(),
                        PayStatusEnum.Paied.getIndex());
                logger.info(payment.toString());

                // 组装可退款交易信息
                TradeRefundDetail tradeRefundDetail = new TradeRefundDetail();
                tradeRefundDetail.setCurrencyType(payment.getPayCurrencyType());
                tradeRefundDetail.setRefundable(true); // 可退款
                tradeRefundDetail.setPayAmount(payment.getPayPrice().getAmount());
                tradeRefundDetail.setTradeNo(tradeNo);
                tradeRefundDetail.setPayChannel(PayTypeEnum.getChannelType(payment.getPayType()).getCode());
                tradeRefundDetail.setPaymentId(payment.getPaymentId());
                tradeRefundDetail.setPayType(payment.getPayType().getCode());
                tradeRefundDetail.setInstPaymentId(payment.getInstitutionPaymentId());

                tradeRefundDetails.add(tradeRefundDetail);
            } else {
                // 不能退款的交易信息
                TradeRefundDetail tradeRefundDetail = new TradeRefundDetail();
                tradeRefundDetail.setTradeNo(tradeNo);

                tradeRefundDetails.add(tradeRefundDetail);
            }
        }
        logger.info("generate tradeRefundDetailList end.");

        return tradeRefundDetails;
    }

    @Override
    public List<AcquireRefundDetail> checkAndSaveRefundRequest(List<TradeRefundDetail> tradeRefundDetails,
            AcquireRefundRequest req) {
        List<AcquireRefundDetail> acquireRefundDetails = new ArrayList<>();
        List<RefundRequestPo> refundrequestWithBLOBs = new ArrayList<>();

        logger.info("generate RefundRequest list");
        for (TradeRefundDetail tradeRefundDetail : tradeRefundDetails) {
            // TradeNo查找RefundRequest， 保证幂等
            List<RefundRequestPo> refundRequests =
                    refundPository.getRefundRequestByTradeNo(tradeRefundDetail.getTradeNo());

            // 组装AcquireRefundDetail，可被退款的交易(接口的返回参数)
            AcquireRefundDetail acquireRefundDetail = new AcquireRefundDetail();
            acquireRefundDetail.setRefundable(true);
            acquireRefundDetail.setTradeNo(tradeRefundDetail.getTradeNo());
            acquireRefundDetails.add(acquireRefundDetail);

            if (refundRequests.size() == 0) { // 若不存在RefundRequest，则插入
                RefundRequestPo refundrequest = new RefundRequestPo();
                refundrequest.setPaymentId(tradeRefundDetail.getPaymentId());
                refundrequest.setInstPaymentId(tradeRefundDetail.getInstPaymentId());
                refundrequest.setTradeNo(tradeRefundDetail.getTradeNo());
                refundrequest.setOrderId(req.getOrderId());
                refundrequest.setBizNo(req.getBizNo());
                refundrequest.setTraceId(req.getTraceId());
                refundrequest.setAppId(req.getAppId());
                refundrequest.setPayType(tradeRefundDetail.getPayType());
                refundrequest.setRefundAmount(tradeRefundDetail.getPayAmount());
                refundrequest.setCurrencyType(tradeRefundDetail.getCurrencyType());
                refundrequest.setRefundStatus(RefundStatusEnum.INIT.getCode());
                for (TradeDetail tradeDetail : req.getTradeDetails()) {
                    if (tradeDetail.getTradeNo().equals(tradeRefundDetail.getTradeNo())) {
                        refundrequest.setTradeType(tradeDetail.getTradeType());
                        break;
                    }
                }

                // refundrequest.setRefundBatchNo(refundPository.generateRefundBatchNo()); //
                // 生成退款批次号
                // 此处不能生成退款批次号，否则隔天审批的退款申请将无法提交支付宝
                refundrequestWithBLOBs.add(refundrequest); // 需要被插入的RefundRequest
            }
        }
        logger.info("{} RefundRequest will be saved.", refundrequestWithBLOBs.size());

        logger.info("batch save RefundRequest and update refundAmt.");
        refundPository.batchSaveRefundRequestAndUpdateRefundAmt(refundrequestWithBLOBs); // 插入RefundRequest

        return acquireRefundDetails;
    }

    @Override
    public List<String> generateTradeNos(List<TradeDetail> tradeDetails) {
        // 获取退款的相关的交易信息
        if (tradeDetails == null || tradeDetails.size() == 0)
            throw new BizException(ErrorCode.ILLEGAL_ARGUMENT, "TradeDetail值不能为 null");

        List<String> tradeNos = new ArrayList<>();
        Set<String> tradeNoSet = new HashSet<>();
        for (TradeDetail tradeDetail : tradeDetails) {
            tradeNos.add(tradeDetail.getTradeNo());
            tradeNoSet.add(tradeDetail.getTradeNo());
        }
        if (tradeNos.size() != tradeNoSet.size()) {
            throw new BizException(ErrorCode.FAIL, "duplicate trade");
        }

        return tradeNos;
    }
}
