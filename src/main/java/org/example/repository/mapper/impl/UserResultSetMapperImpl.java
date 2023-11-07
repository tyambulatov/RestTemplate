package org.example.repository.mapper.impl;

import org.example.model.User;
import org.example.repository.mapper.UserResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetMapperImpl implements UserResultSetMapper {
    @Override
    public User parseUser(ResultSet row) {
        try {
            User user = new User();
            user.setId(row.getLong(1));
            user.setName(row.getString(2));
            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
