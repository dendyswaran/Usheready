package com.usheready.service.impl;

import com.usheready.dao.UsherDetailPicturesDao;
import com.usheready.model.UsherDetailPictures;
import com.usheready.service.UsherDetailPicturesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Emerio on 12/19/2015.
 */

@Service("usherDetailPicturesManager")
public class UsherDetailPicturesManagerImpl extends GenericManagerImpl<UsherDetailPictures, Long> implements UsherDetailPicturesManager {

    private UsherDetailPicturesDao usherDetailPicturesDao;

    @Autowired
    @Override
    public void setUsherDetailPicturesDao(UsherDetailPicturesDao usherDetailPicturesDao) {
        this.dao = usherDetailPicturesDao;
        this.usherDetailPicturesDao = usherDetailPicturesDao;
    }
}
