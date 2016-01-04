package com.usheready.webapp.controller;

import com.usheready.model.Client;
import com.usheready.model.ClientPerson;
import com.usheready.service.ClientManager;
import com.usheready.service.ClientPersonManager;
import org.apache.cxf.common.i18n.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dendy on 03/01/2016.
 */

@Controller
@RequestMapping("/clientPersonForm*")
public class ClientPersonFormController extends BaseFormController {

    @Autowired
    private ClientPersonManager clientPersonManager;
    @Autowired
    private ClientManager clientManager;

    @ModelAttribute("clientPerson")
    protected ClientPerson loadClient(final HttpServletRequest request) {
        final String userId = request.getParameter("id");
        if (isFormSubmission(request) && org.apache.commons.lang.StringUtils.isNotBlank(userId)) {
            ClientPerson person = clientPersonManager.get(Long.parseLong(userId));
            return person;
        }
        return new ClientPerson();
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(final HttpServletRequest request) {
        final String id = request.getParameter("id");
        ModelAndView mv = new ModelAndView("clientPerson/clientPersonForm");
        if (StringUtils.isEmpty(id)) {
            mv.addObject("clientPerson", new ClientPerson());
        } else {
            ClientPerson clientPerson = clientPersonManager.get(Long.parseLong(id));
            mv.addObject("clientPerson", clientPerson);
        }
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("clientPerson") final ClientPerson clientPerson, final HttpServletRequest request)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return "redirect:/home";
        }
        final String clientId = request.getParameter("c");
        if (StringUtils.isEmpty(clientId)) {
            saveError(request, "Client not found!");
            return "redirect:/home";
        }
        try {
            Client client = clientManager.get(Long.parseLong(clientId));

            // set client & save client person
            clientPerson.setClient(client);
            clientPersonManager.save(clientPerson);

            // set clientPerson & save client
            client.addClientPerson(clientPerson);
            clientManager.save(client);

            log.info(clientPerson.getName() + " has been save..");
            saveMessage(request, clientPerson.getName() + " has been save..");
        } catch (java.lang.Exception e) {
            log.error(e.getMessage());
            saveError(request, e.getMessage());
        }

        return "redirect:/home";
    }

}
