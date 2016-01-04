package com.usheready.model;

import com.usheready.enums.Gender;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Emerio on 12/17/2015.
 */

@Entity
@Table(name = "m_usher_detail")
@Indexed
public class UsherDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private User userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private Integer age;
    private Gender gender;
    private String location;
    private Set<UsherDetailPictures> usherDetailPictures = new HashSet<>();
    private String cvUrl;
    private Date cvLastUpdate;
    private Integer height;
    private Integer weight;
    private String phoneNo;
    private String lastEducation;
    private String occupation;
    private boolean enabled;
    private Integer version;

    public UsherDetail() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public User getUser() {
        return userId;
    }

    @OneToMany(mappedBy = "usherDetail", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    public Set<UsherDetailPictures> getUsherDetailPictures() {
        return usherDetailPictures;
    }

    @Column(name = "first_name", nullable = false, length = 50)
    @Field
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name", nullable = false, length = 50)
    @Field
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the full name.
     *
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    @Column
    @Field
    public String getEmail() {
        return email;
    }

    @Column
    public boolean isEnabled() {
        return enabled;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    @Column
    @Field
    public Date getDob() {
        return dob;
    }

    @Column
    @Field
    public Integer getAge() {
        return age;
    }

    @Column
    @Field
    public Gender getGender() {
        return gender;
    }

    @Column
    @Field
    public String getLocation() {
        return location;
    }

    @Column
    @Field
    public String getCvUrl() {
        return cvUrl;
    }

    @Column
    @Field
    public Date getCvLastUpdate() {
        return cvLastUpdate;
    }

    @Column
    @Field
    public Integer getHeight() {
        return height;
    }

    @Column
    @Field
    public Integer getWeight() {
        return weight;
    }

    @Column
    @Field
    public String getPhoneNo() {
        return phoneNo;
    }

    @Column
    @Field
    public String getLastEducation() {
        return lastEducation;
    }

    @Column
    @Field
    public String getOccupation() {
        return occupation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setLastEducation(String lastEducation) {
        this.lastEducation = lastEducation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setCvLastUpdate(Date cvLastUpdate) {
        this.cvLastUpdate = cvLastUpdate;
    }
    public void setUsherDetailPictures(Set<UsherDetailPictures> usherDetailPictures) {
        this.usherDetailPictures = usherDetailPictures;
    }
}
