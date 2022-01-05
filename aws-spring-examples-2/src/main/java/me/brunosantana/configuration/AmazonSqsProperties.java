package me.brunosantana.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "amazon.sqs")
public class AmazonSqsProperties {

    private final String uri;
    private final String region;
    private final String accessKey;
    private final String secretKey;

    public AmazonSqsProperties(String uri, String region, String accessKey, String secretKey) {
        this.uri = uri;
        this.region = region;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getUri() {
        return uri;
    }

    public String getRegion() {
        return region;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
