package com.usheready.dao;

import com.usheready.model.Client;
import com.usheready.model.ClientPerson;

import java.util.List;

/**
 * Created by Dendy on 02/01/2016.
 */
public interface ClientPersonDao extends GenericDao<ClientPerson, Long> {

    /**
     *
     * @param name
     * @return
     */
    ClientPerson loadByName(String name);

    /**
     * Get list by client object
     * @param client
     * @return
     */
    List<ClientPerson> listByClient(Client client);

}
