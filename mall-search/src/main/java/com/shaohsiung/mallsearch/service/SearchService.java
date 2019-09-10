package com.shaohsiung.mallsearch.service;

import com.shaohsiung.mallsearch.model.GoodsDoc;
import com.shaohsiung.mallsearch.repository.GoodsDocRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class SearchService {
    @Autowired
    private GoodsDocRepository goodsDocRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 高亮搜索
     * @param keyword
     * @param pageNum 0 1 2 3
     * @param pageSize
     * @return
     */
    public List<GoodsDoc> search(String keyword, Integer pageNum, Integer pageSize) {
        List<GoodsDoc> result = new ArrayList<GoodsDoc>();

            Pageable pageable = PageRequest.of(pageNum, pageSize);

            String preTag = "<font color='#dd4b39'>";
            String postTag = "</font>";

            SearchQuery searchQuery = new NativeSearchQueryBuilder().
                    //withQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("address", keyword))).
                            withQuery(QueryBuilders.multiMatchQuery(keyword, "name", "detail")).
                            withHighlightFields(
                                    new HighlightBuilder.Field("name").preTags(preTag).postTags(postTag),
                                    new HighlightBuilder.Field("detail").preTags(preTag).postTags(postTag)
                            ).build();
            searchQuery.setPageable(pageable);

            AggregatedPage<GoodsDoc> goodsDocList = elasticsearchTemplate.queryForPage(searchQuery, GoodsDoc.class, new SearchResultMapper() {
                @Override
                public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                    List<GoodsDoc> chunk = new ArrayList<>();
                    for (SearchHit searchHit : response.getHits()) {
                        if (response.getHits().getHits().length <= 0) {
                            return null;
                        }
                        // 构建返回内容
                        GoodsDoc goodsDoc = new GoodsDoc();
                        // 获取基本字段
                        Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                        // 获取高亮字段
                        Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();

                        goodsDoc.setId(searchHit.getId());
                        goodsDoc.setCategoryId((String) sourceAsMap.get("categoryId"));

                        // 时间转化
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date createTime = sdf.parse((String) sourceAsMap.get("createTime"));
                            goodsDoc.setCreateTime(createTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        goodsDoc.setImage((String) sourceAsMap.get("image"));
                        log.info("{}", sourceAsMap.get("price"));

                        //goodsDoc.setPrice((Money) sourceAsMap.get("price"));
                        goodsDoc.setPrice(Money.of(CurrencyUnit.of("CNY"), (Double) sourceAsMap.get("price")));

                        goodsDoc.setStock((Integer) sourceAsMap.get("stock"));

                        HighlightField name = highlightFields.get("name");
                        if (name != null) {
                            goodsDoc.setName(name.fragments()[0].toString());
                        } else {
                            goodsDoc.setName((String) sourceAsMap.get("name"));
                        }
                        HighlightField detail = highlightFields.get("detail");
                        if (detail != null) {
                            goodsDoc.setDetail(detail.fragments()[0].toString());
                        } else {
                            goodsDoc.setDetail((String) sourceAsMap.get("detail"));
                        }

                        chunk.add(goodsDoc);
                    }
                    if (chunk.size() > 0) {
                        return new AggregatedPageImpl<>((List<T>) chunk);
                    }
                    return null;
                }
            });
            if (goodsDocList != null) {
                result = goodsDocList.getContent();
            } else {
                result = Collections.emptyList();
            }
            return result;
        }
}
