package com.bazlur.urlshortening.config;

import com.google.cloud.spring.data.datastore.repository.config.EnableDatastoreAuditing;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDatastoreAuditing
public class FirestoreConfig {
}
