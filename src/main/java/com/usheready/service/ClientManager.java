package com.usheready.service;

import com.usheready.dao.ClientDao;
import com.usheready.model.Client;

import java.util.List;

/**
 * Created by Dendy on 02/01/2016.
 */
public interface ClientManager extends GenericManager<Client, Long> {

    /**
     *
     * @param clientDao
     */
    void setClientDao(final ClientDao clientDao);

    /**
     * Get client by name
     * @param name
     * @return
     * @throws Exception
     */
    Client getByName(String name);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Client> search(final String searchTerm);

}
