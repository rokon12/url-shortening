package com.bazlur.urlshortening;

import com.bazlur.urlshortening.domain.Url;
import com.bazlur.urlshortening.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/url-shortening")
public class UrlController {
		private static final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);
		private final UrlService urlService;

		public UrlController(UrlService urlService) {
				this.urlService = urlService;
		}

		@PostMapping("add")
		public Url addNewUrl(@RequestBody Url url) {
				return urlService.save(url);
		}
}
