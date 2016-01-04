package com.usheready.service;

import com.usheready.dao.UsherDetailDao;
import com.usheready.model.UsherDetail;

/**
 * Created by Emerio on 12/18/2015.
 */
public interface UsherDetailManager extends GenericManager<UsherDetail, Long>  {

    /**
     *
     * @param usherDetailDao
     */
    void setUsherDetailDao(final UsherDetailDao usherDetailDao);

}
