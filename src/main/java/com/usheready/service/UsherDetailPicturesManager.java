package com.usheready.service;

import com.usheready.dao.UsherDetailPicturesDao;
import com.usheready.model.UsherDetailPictures;

/**
 * Created by Emerio on 12/19/2015.
 */
public interface UsherDetailPicturesManager extends GenericManager<UsherDetailPictures, Long> {

    /**
     *
     * @param usherDetailPicturesDao
     */
    void setUsherDetailPicturesDao(final UsherDetailPicturesDao usherDetailPicturesDao);


}
