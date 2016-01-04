package com.usheready.webapp.controller;

import com.usheready.Constants;
import com.usheready.enums.AccountType;
import com.usheready.enums.Gender;
import com.usheready.model.User;
import com.usheready.model.UsherDetail;
import com.usheready.service.UsherDetailManager;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Emerio on 12/19/2015.
 */

@Controller
@RequestMapping("/usherSkillForm*")
public class UsherSkillFormController extends BaseFormController {
    private static final Log log = LogFactory.getLog(UsherSkillFormController.class);

    @Autowired
    private UsherDetailManager usherDetailManager;

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
            if (user.getAccountType().equals(AccountType.USHER)) {
                mv.addObject("usherDetail", user.getUsherDetail() == null
                        ? new UsherDetail()
                        : user.getUsherDetail());
            }
        }

        mv.setViewName("usherDetail/usherSkillForm");
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("usherDetail") UsherDetail usherDetail, BindingResult errors, HttpServletRequest request)
            throws Exception {
        String uploadedFile = upload(request);
        try {
            usherDetail.setCvUrl(uploadedFile);
            usherDetailManager.save(usherDetail);
            log.info("save cv url");
        } catch (Exception e) {
            log.error(e.getMessage());
        }


        return "redirect:/home";
    }

}
