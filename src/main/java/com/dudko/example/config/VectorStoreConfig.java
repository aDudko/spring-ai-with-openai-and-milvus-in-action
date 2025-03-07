package com.dudko.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "ai-app")
public class VectorStoreConfig {

    private String vectorStorePath;

    private List<Resource> documentsToLoad;

}
