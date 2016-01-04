package com.usheready.dao;

import com.usheready.model.Client;

/**
 * Created by Dendy on 02/01/2016.
 */
public interface ClientDao extends GenericDao<Client, Long> {

    /**
     * Load client by name
     * @param name
     * @return
     * @throws Exception
     */
    Client loadClientByName(String name);

}
