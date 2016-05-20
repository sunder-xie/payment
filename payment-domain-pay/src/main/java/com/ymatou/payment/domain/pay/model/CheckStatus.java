/*
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/). All rights reserved.
 */
package com.ymatou.payment.domain.pay.model;

/**
 * 对账状态
 * 
 * @author qianmin 2016年5月20日 下午6:52:09
 *
 */
public enum CheckStatus {
    SUCCESS(1), REPAIR_SUCCESS(2), THIRD_PART_NOT_PAID(15), AMOUNT_NOT_MATCH(-15);

    private int code;

    private CheckStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
