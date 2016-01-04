package com.usheready.service;

import com.usheready.enums.AccountType;
import com.usheready.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

/**
 * Created by Emerio on 12/17/2015.
 */

@Component("bootstrap")
public class Bootstrap {
    private static final Log log = LogFactory.getLog(Bootstrap.class);

    @Autowired
    private UserManager userManager;
    @Autowired
    private UsherDetailManager usherDetailManager;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private ClientPersonManager clientPersonManager;

    private Role roleUsher, roleUser;
    private User userPerson;

    /**
     * Start bootstrap
     */
    public void start() {
        initRole();
        initUser();
        initClient();
        initClientPerson();
    }

    void initRole() {
        if (roleManager.getRole("ROLE_USHER") == null) {
            roleUsher = new Role();
            roleUsher.setName("ROLE_USHER");
            roleUsher.setDescription("Role untuk para usher tercinta");
            roleUsher = roleManager.save(roleUsher);
        } else {
            log.warn("ROLE_USHER already present!");
        }

        if (roleManager.getRole("ROLE_USER") != null) {
            roleUser = roleManager.getRole("ROLE_USER");
        } else {
            log.warn("ROLE_USER not found!");
        }
    }

    void initUser() {
        User user = userManager.getUserByUsername("user");
        if (user != null) {
            user.setAccountType(AccountType.USHER);
            user.addRole(roleUsher);

            UsherDetail usherDetail = new UsherDetail();
            usherDetail.setFirstName("Balqis");
            usherDetail.setLastName("Situmorang");
            usherDetail.setWeight(50);
            usherDetail.setHeight(169);
            usherDetail.setUser(user);

            user.setUsherDetail(usherDetail);
            userManager.save(user);
            usherDetailManager.save(usherDetail);

            log.info("update account type for user: " + user.getUsername());
            log.info("save usher detail");
        } else {
            log.warn("user not found!");
        }

        try {
            userPerson = userManager.getUserByUsername("userpic");
            log.warn("userpic already present!");
        } catch (UsernameNotFoundException e) {
            userPerson = new User();
            userPerson.setUsername("userpic");
            userPerson.setPassword("$2a$10$CnQVJ9bsWBjMpeSKrrdDEeuIptZxXrwtI6CZ/OgtNxhIgpKxXeT9y");
            userPerson.setAccountExpired(false);
            userPerson.setAccountLocked(false);
            userPerson.setAccountType(AccountType.NON_USHER);
            userPerson.setEnabled(true);
            userPerson.setEmail("userpic@test.com");
            userPerson.addRole(roleUser);
            userManager.save(userPerson);
            log.info("userpic has been created..");
        }

    }

    void initClient() {
        if (clientManager.getByName("prudential") == null) {
            Client client = new Client();
            client.setName("prudential");
            client.setEnabled(true);
            client.setEmail("prudential@pru.com");
            client.setDateJoined(new Date());
            client.setPhoneNo("0218457896");
            clientManager.save(client);
            log.info("prudential has been created..");
        }
    }

    void initClientPerson() {
        if (clientPersonManager.getByName("aeroplane") == null) {
            Client client = clientManager.getByName("prudential");
            if (client == null) {
                log.error("prudential not found!");
            } else {
                ClientPerson cp = new ClientPerson();
                cp.setEnabled(true);
                cp.setName("aeroplane");
                cp.setUserId(userPerson);
                cp.setClient(client);
                clientPersonManager.save(cp);
                log.info("aeroplane has been created..");

                userPerson.setClientPerson(cp);
                userManager.save(userPerson);
                log.info("userpic has been updated..");

                client.addClientPerson(cp);
                clientManager.save(client);
                log.info("prudential has been updated..");
            }

        }
    }
}
