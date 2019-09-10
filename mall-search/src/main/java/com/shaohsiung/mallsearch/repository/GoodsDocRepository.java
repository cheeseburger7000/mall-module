package com.shaohsiung.mallsearch.repository;

import com.shaohsiung.mallsearch.model.GoodsDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsDocRepository extends ElasticsearchRepository<GoodsDoc, String> {
}
