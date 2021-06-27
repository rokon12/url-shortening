package com.bazlur.urlshortening.service;


import com.bazlur.urlshortening.domain.Url;
import com.bazlur.urlshortening.repsoitory.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;


@Service
public class UrlService {
		private static final Logger LOGGER = LoggerFactory.getLogger(UrlService.class);
		private final UrlRepository urlRepository;
		private final ServletContext context;
		private final Random random = new Random();

		public UrlService(UrlRepository urlRepository, ServletContext context) {
				this.urlRepository = urlRepository;
				this.context = context;
		}

		//TODO:: check for uniqueness
		public Url save(Url url) {
				var hash = base64(url.getOriginalUrl());
				var reHashed = base64(hash);
				reHashed = base64(reHashed);
				reHashed = base64(reHashed);
				var uniqueId = getRandomPosition(reHashed, 6);
				url.setHash(uniqueId);

				var savedUrl = urlRepository.save(url);
				url.setShortUrl(context.getContextPath() + "/" + savedUrl.getHash());

				return savedUrl;
		}

		private String getRandomPosition(String from, int diff) {
				var length = from.length();
				var beginIndex = random.nextInt(length - diff);
				return from.substring(beginIndex, beginIndex + diff);
		}

		private String base64(String url) {
				return Base64.getEncoder()
								.encodeToString(url.getBytes(StandardCharsets.UTF_8));
		}

		public Optional<Url> findByHash(String key) {
				return urlRepository.findById(key).map(url -> {
						var originalUrl = new String(Base64.getDecoder().decode(url.getOriginalUrl()));
						url.setOriginalUrl(originalUrl);
						return url;
				});
		}
}
