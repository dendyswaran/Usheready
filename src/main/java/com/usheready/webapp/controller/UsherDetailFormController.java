package com.usheready.webapp.controller;

import com.usheready.Constants;
import com.usheready.enums.AccountType;
import com.usheready.enums.Gender;
import com.usheready.model.User;
import com.usheready.model.UsherDetail;
import com.usheready.service.UsherDetailManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.util.Locale;

/**
 * Created by Emerio on 12/17/2015.
 */

@Controller
@RequestMapping("/usherDetailForm*")
public class UsherDetailFormController extends BaseFormController {

    @Autowired
    private UsherDetailManager usherDetailManager;

    public UsherDetailFormController() {
        setCancelView("redirect:/home");
        setSuccessView("redirect:/home");
    }

    /**
     * Load user object from db before web data binding in order to keep properties not populated from web post.
     *
     * @param request
     * @return
     */
    @ModelAttribute("usherDetail")
    protected UsherDetail loadUsher(final HttpServletRequest request) {
        final String userId = request.getParameter("id");
        if (isFormSubmission(request) && StringUtils.isNotBlank(userId)) {
            UsherDetail usherDetail = usherDetailManager.get(Long.parseLong(userId));
            return usherDetail;
        }
        return new UsherDetail();
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final String id = request.getParameter("id");
        ModelAndView mv = new ModelAndView();
        // If requested by non admin user
        if (!isFormSubmission(request) && StringUtils.isEmpty(id)) {
            User user = getUserManager().getUserByUsername(request.getRemoteUser());
            log.info("user account type 123: " + user.getAccountType());
            if (user.getAccountType().equals(AccountType.USHER)) {
                mv.addObject("usherDetail", user.getUsherDetail() == null
                        ? new UsherDetail()
                        : user.getUsherDetail());
            }
        }
        mv.addObject("genderList", Gender.values());
        mv.setViewName("usherDetail/usherDetailForm");
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("usherDetail") final UsherDetail usherDetail, final BindingResult errors, final HttpServletRequest request,
            final HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        log.debug("entering 'onSubmit' method...");
        final Locale locale = request.getLocale();

        try {
            usherDetailManager.save(usherDetail);
            saveMessage(request, getText("user.saved", usherDetail.getFullName(), locale));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return getSuccessView();
    }

}
