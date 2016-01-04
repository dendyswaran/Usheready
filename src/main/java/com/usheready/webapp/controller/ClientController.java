package com.usheready.webapp.controller;

import com.usheready.dao.SearchException;
import com.usheready.model.Client;
import com.usheready.service.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Dendy on 02/01/2016.
 */

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientManager clientManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("clientList", clientManager.search(query));
        } catch (SearchException se) {
            model.addAttribute("searcError", se.getMessage());
        }
        return new ModelAndView("client/clientList", model.asMap());
    }

}
