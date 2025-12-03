package com.erick.repository;

import com.erick.entity.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);
}
