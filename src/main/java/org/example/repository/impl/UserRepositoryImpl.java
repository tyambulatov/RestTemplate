package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.impl.ConnectionManagerImpl;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.mapper.UserResultSetMapper;
import org.example.repository.mapper.impl.UserResultSetMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from account order by id";

    //language=SQL
    private static final String SQL_INSERT = "insert into account(user_name) values (?) returning id, user_name";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from account where id=? ";

    //language=SQL
    private static final String SQL_DELETE = "delete from account where id=? ";

    //language=SQL
    private static final String SQL_UPDATE = "update account set user_name=? where id=? returning id, user_name";

    private final UserResultSetMapper userResultSetMapper = new UserResultSetMapperImpl();

    private final ConnectionManager connectionManager = new ConnectionManagerImpl();


    @Override
    public User findById(Long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return userResultSetMapper.parseUser(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() == 1;

        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
                while (resultSet.next()) {
                    User user = userResultSetMapper.parseUser(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return users;
    }


    /*
    Должен возвращать пользователя с id из БД
     */
    @Override
    public User save(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {

            statement.setString(1, user.getName());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return userResultSetMapper.parseUser(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User update(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {

            statement.setString(1, user.getName());
            statement.setLong(2, user.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return userResultSetMapper.parseUser(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
