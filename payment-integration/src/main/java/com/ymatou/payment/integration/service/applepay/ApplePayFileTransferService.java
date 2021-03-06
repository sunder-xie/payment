package com.ymatou.payment.integration.service.applepay;

import com.ymatou.payment.facade.BizException;
import com.ymatou.payment.integration.IntegrationConfig;
import com.ymatou.payment.integration.common.HttpClientUtil;
import com.ymatou.payment.integration.common.constants.Constants;
import com.ymatou.payment.integration.common.utils.CompressUtil;
import com.ymatou.payment.integration.model.ApplePayFileTransferRequest;
import com.ymatou.payment.integration.model.ApplePayFileTransferResponse;
import com.ymatou.payment.integration.service.applepay.common.ApplePayConstants;
import com.ymatou.payment.integration.service.applepay.common.ApplePayMessageUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiaoming on 2017/4/24.
 * 获取对账文件交易
 */
@Service
public class ApplePayFileTransferService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ApplePayFileTransferService.class);

    private CloseableHttpClient httpClient;

    @Autowired
    private IntegrationConfig integrationConfig;


    public ApplePayFileTransferResponse doService(ApplePayFileTransferRequest request, HashMap<String, String> header) {

        try {
            String body = ApplePayMessageUtil.genRequestMessage(request.genMap());
            String url = this.integrationConfig.getApplePayfileTransferUrl(header);
            String result = HttpClientUtil.sendPost(url, body, ApplePayConstants.content_type, header, httpClient);
            Map<String, String> resultMap = ApplePayMessageUtil.genResponseMessage(result);
            ApplePayFileTransferResponse response = ApplePayFileTransferResponse.loadProperty(resultMap, ApplePayFileTransferResponse.class);
            if (response != null && !StringUtils.isBlank(response.getFileContent())) {
                byte[] bytes = response.getFileContent().getBytes(response.getEncoding());
                String content = new String(CompressUtil.inflater(Base64.decodeBase64(bytes)));
                response.setFileContent(content);
            }
            return response;
        } catch (Exception ex) {
            throw new BizException("ApplePayFileTransferService.doPost Exception", ex);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SSLContext ctx = SSLContext.getInstance("SSL");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);

        SSLConnectionSocketFactory ssf =
                new SSLConnectionSocketFactory(ctx, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(Constants.DEFAULT_MAX_PER_ROUTE);
        cm.setMaxTotal(Constants.MAX_TOTAL);

        this.httpClient = HttpClientBuilder.create()
                .setSSLSocketFactory(ssf)
                .setConnectionManager(cm)
                .build();
    }
}
