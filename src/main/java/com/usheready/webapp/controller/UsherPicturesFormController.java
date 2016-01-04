package com.usheready.webapp.controller;

import com.usheready.Constants;
import com.usheready.enums.AccountType;
import com.usheready.model.User;
import com.usheready.model.UsherDetail;
import com.usheready.model.UsherDetailPictures;
import com.usheready.service.UsherDetailManager;
import com.usheready.service.UsherDetailPicturesManager;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Emerio on 12/19/2015.
 */

@Controller
@RequestMapping("/usherPicturesForm*")
public class UsherPicturesFormController extends BaseFormController {
    private static final Log log = LogFactory.getLog(UsherSkillFormController.class);

    @Autowired
    private UsherDetailPicturesManager usherDetailPicturesManager;
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
                UsherDetail usherDetail = new UsherDetail();
                if (user.getUsherDetail() != null) {
                    usherDetail = usherDetailManager.get(user.getUsherDetail().getId());
                }
                mv.addObject("usherDetail", usherDetail);
            }
        }
        mv.setViewName("usherDetail/usherPicturesForm");
        return mv;
    }

    /**
     *
     * @param usherDetail
     * @param errors
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("usherDetail") UsherDetail usherDetail, BindingResult errors, HttpServletRequest request)
            throws Exception {
        String uploadedFile = upload(request);
        if (request.getParameter("upload") != null) {
            try {
                UsherDetailPictures pictures = new UsherDetailPictures();
                pictures.setEnabled(true);
                pictures.setUploadDate(new Date());
                pictures.setUrl(uploadedFile);
                pictures.setUsherDetail(usherDetail);
                usherDetailPicturesManager.save(pictures);

                log.info("saving pictures");
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        return "redirect:/home";
    }

}
