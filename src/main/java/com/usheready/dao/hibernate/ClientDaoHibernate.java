package com.usheready.dao.hibernate;

import com.usheready.dao.ClientDao;
import com.usheready.model.Client;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dendy on 02/01/2016.
 */

@Repository("clientDao")
public class ClientDaoHibernate extends GenericDaoHibernate<Client, Long> implements ClientDao {

    public ClientDaoHibernate() {
        super(Client.class);
    }

    @Override
    public Client loadClientByName(String name) {
        List clients = getSession().createCriteria(Client.class).add(Restrictions.eq("name", name)).list();
        if (clients == null || clients.isEmpty()) {
            return null;
        } else {
            return (Client) clients.get(0);
        }
    }

}
