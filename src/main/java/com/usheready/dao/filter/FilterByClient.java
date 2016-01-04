package com.usheready.dao.filter;

import com.usheready.model.Client;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.annotations.Key;
import org.hibernate.search.filter.FilterKey;
import org.hibernate.search.filter.StandardFilterKey;

/**
 * Created by Dendy on 03/01/2016.
 * Used in Client Person Controller. View the client person list by client object.
 */
public class FilterByClient {
    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    @Key
    public FilterKey getKey() {
        StandardFilterKey key = new StandardFilterKey();
        key.addParameter(client);
        return key;
    }

    @Factory
    public Filter getFilter() {
        Long id = client.getId();
        Query query = new TermQuery(new Term("client.id", String.valueOf(id)));
        return new CachingWrapperFilter(new QueryWrapperFilter(query));
    }
}
