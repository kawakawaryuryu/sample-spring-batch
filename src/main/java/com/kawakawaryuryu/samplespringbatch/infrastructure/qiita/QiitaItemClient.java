package com.kawakawaryuryu.samplespringbatch.infrastructure.qiita;

import com.kawakawaryuryu.samplespringbatch.step.chunk.reader.qiita.QiitaArticleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.net.URI;

@Component
public class QiitaItemClient {

    private final RestOperations restOperations;

    public QiitaItemClient(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public ResponseEntity<QiitaArticleResponse[]> getArticles() {
        URI uri = URI.create("https://qiita.com/api/v2/items?per_page=1");
        return restOperations.getForEntity(uri, QiitaArticleResponse[].class);
    }
}
