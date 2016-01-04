package com.usheready.service.impl;

import com.usheready.dao.ClientDao;
import com.usheready.model.Client;
import com.usheready.service.ClientManager;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dendy on 02/01/2016.
 */

@Service("clientManager")
public class ClientManagerImpl extends GenericManagerImpl<Client, Long> implements ClientManager {

    private ClientDao clientDao;

    @Override
    @Autowired
    public void setClientDao(ClientDao clientDao) {
        this.dao = clientDao;
        this.clientDao = clientDao;
    }

    @Override
    public Client getByName(String name) {
        return lazyLoading(clientDao.loadClientByName(name));
    }

    @Override
    public Client get(Long id) {
        return lazyLoading(super.get(id));
    }

    private Client lazyLoading(Client client) {
        try {
            Hibernate.initialize(client.getClientPersons());
        } catch (NullPointerException e) {
            log.warn(e.getMessage());
        }
        return client;
    }

    @Override
    public List<Client> search(String searchTerm) {
        return super.search(searchTerm, Client.class);
    }
}
