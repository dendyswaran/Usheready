package com.usheready.service.impl;

import com.usheready.dao.UsherDetailDao;
import com.usheready.model.UsherDetail;
import com.usheready.service.UsherDetailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Emerio on 12/18/2015.
 */

@Service("usherDetailManager")
public class UsherDetailManagerImpl extends GenericManagerImpl<UsherDetail, Long> implements UsherDetailManager {

    private UsherDetailDao usherDetailDao;

    @Autowired
    @Override
    public void setUsherDetailDao(UsherDetailDao usherDetailDao) {
        this.dao = usherDetailDao;
        this.usherDetailDao = usherDetailDao;
    }
}
