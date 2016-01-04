package com.usheready.webapp.controller;

import com.usheready.Constants;
import com.usheready.model.Client;
import com.usheready.service.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dendy on 02/01/2016.
 */

@Controller
@RequestMapping("/clientForm*")
public class ClientFormController extends BaseFormController {

    @Autowired
    private ClientManager clientManager;

    @ModelAttribute("client")
    protected Client loadClient(final HttpServletRequest request) {
        final String userId = request.getParameter("id");
        if (isFormSubmission(request) && org.apache.commons.lang.StringUtils.isNotBlank(userId)) {
            Client client = clientManager.get(Long.parseLong(userId));
            return client;
        }
        return new Client();
    }
    
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(final HttpServletRequest request) {
        final String id = request.getParameter("id");
        ModelAndView mv = new ModelAndView("client/clientForm");

        // Only ROLE_ADMIN can view this data
        if (request.isUserInRole(Constants.ADMIN_ROLE) && !StringUtils.isEmpty(id)) {
            mv.addObject("client", clientManager.get(Long.parseLong(id)));
        }

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("client") final Client client, final HttpServletRequest request)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return "redirect:/home";
        }

        try {
            clientManager.save(client);
            saveMessage(request, client.getName() + " has been save..");
        } catch (Exception e) {
            log.error(e.getMessage());
            saveError(request, e.getMessage());
        }

        return "redirect:/home";
    }

}
