package com.erick.repository.mapper;

import com.erick.entity.User;
import com.erick.repository.orm.UserOrm;

public class UserRepositoryMapper {

    private UserRepositoryMapper() {
    }

    public static User toEntity(UserOrm orm) {
        return new User(
                orm.id(),
                orm.username(),
                orm.email(),
                orm.password()
        );
    }

    public static UserOrm toPersistence(User entity) {
        return new UserOrm(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword()
        );
    }

}
