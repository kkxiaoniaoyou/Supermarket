package com.cbz.example.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tencent.cos")
@Data
public class TencentCosConfig {

    private String secretId;

    private String secretKey;

    private String regionName;

    private String bucketName;

    private String url;

    @Bean
    public COSClient cosClient() {

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

        Region region = new Region(regionName);

        ClientConfig clientConfig = new ClientConfig(region);

        clientConfig.setHttpProtocol(HttpProtocol.https);

        return new COSClient(cred, clientConfig);
    }
}
