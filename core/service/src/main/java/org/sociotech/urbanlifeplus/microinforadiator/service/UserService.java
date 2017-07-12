/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import org.sociotech.urbanlifeplus.microinforadiator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author vituslehner 04.07.17
 */
@Service
public class UserService extends AbstractCacheService<User, String> {

    @Autowired
    public UserService(TimingService timingService) {
        super(timingService);
    }

    public User findUserbyId(String userId) {
        Optional<User> existingUser = getCachable(userId, user -> userId.equals(user.getId()));
        if (!existingUser.isPresent()) {
            User newUser = new User(userId, "Max-" + userId, "Mustermann");
            addCachable(newUser);
            return newUser;
        }
        return existingUser.get();
    }

    public List<User> getCurrentUsers() {
        return getAll();
    }
}
