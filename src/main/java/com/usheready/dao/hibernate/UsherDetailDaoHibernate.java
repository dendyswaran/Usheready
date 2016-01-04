package com.usheready.dao.hibernate;

import com.usheready.dao.UsherDetailDao;
import com.usheready.model.UsherDetail;
import org.hibernate.Hibernate;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

/**
 * Created by Emerio on 12/18/2015.
 */

@Repository("usherDetailDao")
public class UsherDetailDaoHibernate extends GenericDaoHibernate<UsherDetail, Long> implements UsherDetailDao {

    public UsherDetailDaoHibernate() {
        super(UsherDetail.class);
    }

    @Override
    public UsherDetail get(Long id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(UsherDetail.class);
        UsherDetail entity = (UsherDetail) byId.load(id);

        if (entity == null) {
            log.warn("Uh oh, '" + UsherDetail.class + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(UsherDetail.class, id);
        }
        Hibernate.initialize(entity.getUsherDetailPictures());
        log.info("lazy loading pictures");
        return entity;
    }
}
