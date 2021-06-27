package com.bazlur.urlshortening.repsoitory;

import com.bazlur.urlshortening.domain.Url;
import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;

public interface UrlRepository extends DatastoreRepository<Url, String> {
}

