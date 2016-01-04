package com.usheready.webapp.controller;

import com.usheready.Constants;
import com.usheready.dao.SearchException;
import com.usheready.model.Client;
import com.usheready.model.ClientPerson;
import com.usheready.service.ClientManager;
import com.usheready.service.ClientPersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dendy on 03/01/2016.
 */

@Controller
@RequestMapping("/admin/clientPersons*")
public class ClientPersonController {

    @Autowired
    private ClientPersonManager clientPersonManager;
    @Autowired
    private ClientManager clientManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query, final HttpServletRequest request) {
        Model model = new ExtendedModelMap();
        final String clientId = request.getParameter("c");

        try {
            List<HashMap<String, Object>> filterList = new ArrayList<>();
            HashMap<String, Object> map = new HashMap();

            Client client = clientManager.get(Long.parseLong(clientId));
            if (client != null) {
                map.put(Constants.LUCENE_FILTER_NAME, ClientPerson.FILTER_BY_CLIENT);
                map.put(Constants.LUCENE_FILTER_KEY, "client");
                map.put(Constants.LUCENE_FILTER_VAL, client);
                filterList.add(map);
            }

            model.addAttribute("clientPersonList", clientPersonManager.search(query, ClientPerson.class, filterList));
        } catch (SearchException se) {
            model.addAttribute("searcError", se.getMessage());
        }
        return new ModelAndView("clientPerson/clientPersonList", model.asMap());
    }

}
