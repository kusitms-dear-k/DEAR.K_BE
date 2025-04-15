package com.deark.be.global.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.aws")
public record S3Properties(
        Credentials credentials,
        S3 s3,
        Region region
) {
    public record Credentials(
            String accessKey, String secretKey
    ) {
    }

    public record S3(
            String bucket
    ) {
    }

    public record Region(
            String staticValue
    ) {
    }

    public String credentialsAccessKey() {
        return credentials.accessKey();
    }

    public String credentialsSecretKey() {
        return credentials.secretKey();
    }

    public String regionStaticValue() {
        return region.staticValue();
    }
}
