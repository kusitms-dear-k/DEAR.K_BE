package com.deark.be.global.config;

import com.deark.be.global.util.S3Properties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({S3Properties.class})
public class PropertiesConfig {
}
