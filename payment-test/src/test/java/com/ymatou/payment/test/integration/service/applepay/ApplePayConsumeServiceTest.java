package com.ymatou.payment.test.integration.service.applepay;

import com.ymatou.payment.domain.channel.InstitutionConfig;
import com.ymatou.payment.domain.channel.InstitutionConfigManager;
import com.ymatou.payment.facade.constants.PayTypeEnum;
import com.ymatou.payment.integration.IntegrationConfig;
import com.ymatou.payment.integration.model.ApplePayConsumeRequest;
import com.ymatou.payment.integration.model.ApplePayConsumeResponse;
import com.ymatou.payment.integration.service.applepay.ApplePayConsumeService;
import com.ymatou.payment.integration.service.applepay.common.ApplePayConstants;
import com.ymatou.payment.integration.service.applepay.common.ApplePaySignatureUtil;
import com.ymatou.payment.test.RestBaseTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by gejianhua on 2017/4/26.
 */
public class ApplePayConsumeServiceTest extends RestBaseTest {

    @Resource
    private ApplePayConsumeService applePayConsumeService;

    @Resource
    private InstitutionConfigManager instConfigManager;

    @Resource
    private IntegrationConfig integrationConfig;

    @Test
    public void testDoPost(){
        InstitutionConfig config = instConfigManager.getConfig(PayTypeEnum.ApplePay);


        ApplePayConsumeRequest request = new ApplePayConsumeRequest();

        request.setMerId(config.getMerchantId());
        request.setOrderId(String.valueOf(10000000 + new Random().nextInt(100000000)));

        SimpleDateFormat format = new SimpleDateFormat(ApplePayConstants.time_format);
        String time = format.format(new Date());
        request.setTxnTime(time);

        request.setTxnAmt("10000");
        request.setBackUrl(String.format("%s/notify/%s", integrationConfig.getYmtPaymentBaseUrl(), PayTypeEnum.ApplePay));

        request.setCertId(config.getCertId());

        //sign
        String sign = ApplePaySignatureUtil.sign(request.genMap(), config.getInstYmtPrivateKey());
        request.setSignature(sign);

        ApplePayConsumeResponse response = this.applePayConsumeService.doPost(request, null);
        System.out.println(response.getRespCode());
        System.out.println(response.getRespMsg());

        Assert.assertEquals(ApplePayConstants.response_success_code, response.getRespCode());


        //validate sign
        boolean flag = ApplePaySignatureUtil.validate(response.getOriginMap(), config.getInstPublicKey());
        Assert.assertEquals(true, flag);
    }



}













































