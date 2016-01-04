package com.usheready.service;

import com.usheready.dao.ClientPersonDao;
import com.usheready.model.Client;
import com.usheready.model.ClientPerson;

import java.util.List;

/**
 * Created by Dendy on 02/01/2016.
 */
public interface ClientPersonManager extends GenericManager<ClientPerson, Long> {

    /**
     *
     * @param clientPersonDao
     */
    void setClientPersonDao(final ClientPersonDao clientPersonDao);

    /**
     * Get by name
     * @param name
     * @return
     */
    ClientPerson getByName(String name);

}
