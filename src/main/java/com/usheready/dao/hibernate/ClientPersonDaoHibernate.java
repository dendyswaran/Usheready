package com.usheready.dao.hibernate;

import com.usheready.dao.ClientPersonDao;
import com.usheready.dao.SearchException;
import com.usheready.model.Client;
import com.usheready.model.ClientPerson;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dendy on 02/01/2016.
 */

@Repository("clientPersonDao")
public class ClientPersonDaoHibernate extends GenericDaoHibernate<ClientPerson, Long> implements ClientPersonDao {

    public ClientPersonDaoHibernate() {
        super(ClientPerson.class);
    }

    @Override
    public ClientPerson loadByName(String name) {
        List persons = getSession().createCriteria(ClientPerson.class).add(Restrictions.eq("name", name)).list();
        if (persons == null || persons.isEmpty()) {
            return null;
        } else {
            return (ClientPerson) persons.get(0);
        }
    }

    @Override
    public List<ClientPerson> listByClient(Client client) {
        List persons = getSession().createCriteria(ClientPerson.class)
                .add(Restrictions.eq("client", client))
                .list();
        if (persons == null || persons.isEmpty()) {
            return null;
        } else {
            return persons;
        }
    }
}
