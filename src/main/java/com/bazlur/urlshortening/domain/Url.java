package com.bazlur.urlshortening.domain;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import com.google.cloud.spring.data.datastore.core.mapping.Field;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;

@Data
@Entity(name = "short_urls")
public class Url {
		@Id
		@Field(name = "hash")
		private String hash;

		@Field(name = "original_url")
		private String originalUrl;

		@Transient
		private String shortUrl;

		private LocalDateTime createdTime = LocalDateTime.now();

		@LastModifiedDate
		private LocalDateTime lastModifiedDate = LocalDateTime.now();

		private LocalDateTime expiredDate = LocalDateTime.now().plusDays(14);
		private Boolean expired = false;
}
