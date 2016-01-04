package com.usheready.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dendy on 02/01/2016.
 */

@Entity
@Table(name = "m_client")
@Indexed
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Address address;
    private String phoneNo;
    private Date dateJoined;
    private String email;
    private Set<ClientPerson> clientPersons = new HashSet<>();
    private boolean enabled;
    private Integer version;

    public Client() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    public Set<ClientPerson> getClientPersons() {
        return clientPersons;
    }

    @Column
    @Field
    public String getName() {
        return name;
    }

    @Embedded
    @IndexedEmbedded
    public Address getAddress() {
        return address;
    }

    @Column(name = "phone_no")
    @Field
    public String getPhoneNo() {
        return phoneNo;
    }

    @Column(name = "date_joined")
    @Field
    public Date getDateJoined() {
        return dateJoined;
    }

    @Column(name = "email")
    @Field
    public String getEmail() {
        return email;
    }

    @Column
    @Field
    public boolean isEnabled() {
        return enabled;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClientPersons(Set<ClientPerson> clientPersons) {
        this.clientPersons = clientPersons;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void addClientPerson(ClientPerson clientPerson) {
        getClientPersons().add(clientPerson);
    }
}
