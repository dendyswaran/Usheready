package com.usheready.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Emerio on 12/19/2015.
 */

@Entity
@Table(name = "m_usher_detail_pictures")
@Indexed
public class UsherDetailPictures implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private UsherDetail usherDetail;
    private String url;
    private Date uploadDate;
    private boolean isEnabled;
    private Integer version;

    public UsherDetailPictures() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "usherDetail_id", nullable = false)
    public UsherDetail getUsherDetail() {
        return usherDetail;
    }

    @Column
    @Field
    public String getUrl() {
        return url;
    }

    @Column
    @Field
    public Date getUploadDate() {
        return uploadDate;
    }

    @Column
    @Field
    public boolean isEnabled() {
        return isEnabled;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setUsherDetail(UsherDetail usherDetail) {
        this.usherDetail = usherDetail;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
