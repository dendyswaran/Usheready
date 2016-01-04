package com.usheready.model;

import com.usheready.dao.filter.FilterByClient;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dendy on 02/01/2016.
 */

@Entity
@Table(name = "m_client_person")
@Indexed
@FullTextFilterDefs({
    @FullTextFilterDef(name = ClientPerson.FILTER_BY_CLIENT, impl = FilterByClient.class)
})
public class ClientPerson implements Serializable {
    public static final String FILTER_BY_CLIENT = "filterByClient";

    private static final long serialVersionUID = 1L;

    private Long id;
    private User userId;
    private Client client;
    private String name;
    private String phoneNumber;
    private String email;
    private boolean enabled;
    private Integer version;

    public ClientPerson() {
    }

    @Id
    @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public User getUserId() {
        return userId;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    public Client getClient() {
        return client;
    }

    @Column
    @Field
    public String getName() {
        return name;
    }

    @Column
    @Field
    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
