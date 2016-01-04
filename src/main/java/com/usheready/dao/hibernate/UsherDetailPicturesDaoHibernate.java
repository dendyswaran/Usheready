package com.usheready.dao.hibernate;

import com.usheready.dao.UsherDetailPicturesDao;
import com.usheready.model.UsherDetailPictures;
import org.springframework.stereotype.Service;

/**
 * Created by Emerio on 12/19/2015.
 */

@Service("usherDetailPicturesDao")
public class UsherDetailPicturesDaoHibernate extends GenericDaoHibernate<UsherDetailPictures, Long> implements UsherDetailPicturesDao {

    public UsherDetailPicturesDaoHibernate() {
        super(UsherDetailPictures.class);
    }
}
