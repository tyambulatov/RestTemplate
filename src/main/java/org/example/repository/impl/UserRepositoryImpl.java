package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.mapper.UserResultSetMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository<User, Long> {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from account order by id";

    //language=SQL
    private static final String SQL_INSERT = "insert into account(user_name) " +
            "values (?)";

    //language=SQL
    private static final String SQL_FIND = "select * from account where id=? ";

    private static final String SQL_DELETE = "delete from account where id=? ";


    private final DataSource dataSource;

    private final int batchSize;

    private final UserResultSetMapper userResultSetMapper;

    private final ConnectionManager connectionManager;

    public UserRepositoryImpl(DataSource dataSource, int batchSize, UserResultSetMapper userResultSetMapper, ConnectionManager connectionManager) {
        this.dataSource = dataSource;
        this.batchSize = batchSize;
        this.userResultSetMapper = userResultSetMapper;
        this.connectionManager = connectionManager;
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND)) {
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {

            statement.setLong(1, id);

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
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


    @Override
    public User save(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {

            statement.setString(1, user.getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return user;
    }
}
