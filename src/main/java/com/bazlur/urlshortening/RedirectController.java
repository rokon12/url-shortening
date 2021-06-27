package com.bazlur.urlshortening;

import com.bazlur.urlshortening.domain.Url;
import com.bazlur.urlshortening.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class RedirectController {
		private static final Logger LOGGER = LoggerFactory.getLogger(RedirectController.class);
		private final UrlService urlService;

		public RedirectController(UrlService urlService) {
				this.urlService = urlService;
		}

		@GetMapping("/{key}")
		public String index(@PathVariable String key) {
				Optional<Url> url = urlService.findByHash(key);
				var originalUrl = url.map(Url::getOriginalUrl).orElse("");
				return "redirect:" + originalUrl;
		}
}
