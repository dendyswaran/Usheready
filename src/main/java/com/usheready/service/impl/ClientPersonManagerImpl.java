package com.usheready.service.impl;

import com.usheready.dao.ClientPersonDao;
import com.usheready.model.Client;
import com.usheready.model.ClientPerson;
import com.usheready.service.ClientPersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dendy on 02/01/2016.
 */

@Service("clientPersonManager")
public class ClientPersonManagerImpl extends GenericManagerImpl<ClientPerson, Long> implements ClientPersonManager {

    private ClientPersonDao clientPersonDao;

    @Override
    @Autowired
    public void setClientPersonDao(ClientPersonDao clientPersonDao) {
        this.dao = clientPersonDao;
        this.clientPersonDao = clientPersonDao;
    }

    @Override
    public ClientPerson getByName(String name) {
        return clientPersonDao.loadByName(name);
    }
}
