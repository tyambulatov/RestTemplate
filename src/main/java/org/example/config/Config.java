//package org.example.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.example.db.ConnectionManager;
//import org.example.db.impl.ConnectionManagerImpl;
//import org.example.model.User;
//import org.example.repository.UserRepository;
//import org.example.repository.impl.UserRepositoryImpl;
//import org.example.repository.mapper.UserResultSetMapper;
//import org.example.repository.mapper.impl.UserResultSetMapperImpl;
//import org.example.service.UserService;
//import org.example.service.impl.UserServiceImpl;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class Config {
//
//    private final UserService userService;
//
//    private final UserRepository<User, Long> userRepository;
//
////    private final Servlet servlet;
//
//    public Config() {
//        Properties dbProperties = configProperties();
//        DataSource dataSource = configHikariDataSource(dbProperties);
//        UserResultSetMapper userResultSetMapper = new UserResultSetMapperImpl();
//        ConnectionManager connectionManager = new ConnectionManagerImpl();
//        userRepository = new UserRepositoryImpl(dataSource, 100, userResultSetMapper, connectionManager);
//        userService = new UserServiceImpl(userRepository);
////        servlet = new Servlet(userRepository);
//    }
//
//    public static Properties configProperties() {
//        Properties dbProperties = new Properties();
//
//        InputStream fileBDProperties = Config.class.getClassLoader().getResourceAsStream("db.properties");
//        try {
//            dbProperties.load(fileBDProperties);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
////        try {
////            dbProperties.load(new BufferedReader(
////                    new InputStreamReader(Main.class.getResourceAsStream("/db.properties"))));
////        } catch (IOException e) {
////            throw new IllegalArgumentException(e);
////        }
//        return dbProperties;
//    }
//
//    public static DataSource configHikariDataSource(Properties dbProperties) {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setPassword(dbProperties.getProperty("db.password"));
//        dataSource.setUsername(dbProperties.getProperty("db.username"));
//        dataSource.setJdbcUrl(dbProperties.getProperty("db.url"));
//        dataSource.setMaximumPoolSize(
//                Integer.parseInt(
//                        dbProperties.getProperty("db.hikari.MaxPoolSize")));
//        return dataSource;
//    }
//
//    public UserRepository<User, Long> getUserRepository() {
//        return userRepository;
//    }
//    public UserService getUserService() {
//        return userService;
//    }
//}
