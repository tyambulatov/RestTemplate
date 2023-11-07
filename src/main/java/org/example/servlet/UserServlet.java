package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import org.example.config.Config;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.impl.UserRepositoryImpl;
import org.example.repository.mapper.UserResultSetMapper;
import org.example.repository.mapper.impl.UserResultSetMapperImpl;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import javax.sql.DataSource;
import java.io.IOException;

import java.io.PrintWriter;
import java.io.Serial;
import java.util.Collection;
import java.util.Properties;

import static org.example.config.Config.configHikariDataSource;
import static org.example.config.Config.configProperties;


@WebServlet("/users")
public class UserServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private final UserService userService;

//    private Gson _gson = null;
//
    public UserServlet() {
        super();

        Config config = new Config();
        UserRepository<User, Long> userRepository = config.getUserRepository();
        userService = new UserServiceImpl(userRepository);

//        _gson = new Gson();
    }

    //a utility method to send an object
    //as JSON response
//    private void sendAsJson(
//            HttpServletResponse response,
//            Object obj) throws IOException {
//
//        response.setContentType("application/json");
//
//        String res = _gson.toJson(obj);
//
//        PrintWriter out = response.getWriter();
//
//        out.print(res);
//        out.flush();
//    }

    // Get models
    // GET/JavaViewer/users/
    // GET/JavaViewer/users/id
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

//        response.setContentType("application/json");
//        response.getWriter().write("Testing");
        User user = userService.findById(1L);
//        System.out.println(user.getName());
        response.getWriter().write(user.getName());

//        String pathInfo = request.getPathInfo();
//
//        if(pathInfo == null || pathInfo.equals("/")){
//
//            Collection<User> users = userService.findAll();
//
//            sendAsJson(response, users);
//        }
//
//        String[] splits = pathInfo.split("/");
//
//        if(splits.length != 2) {
//
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        Long userId = Long.parseLong(splits[1]);
//
//        User user = userService.findById(userId);
//
//        if(user == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        sendAsJson(response, user);
    }
//
//    // Adds new model in DB
//    // POST/JavaViewer/models
//    protected void doPost(
//            HttpServletRequest request,
//            HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String pathInfo = request.getPathInfo();
//
//        if(pathInfo == null || pathInfo.equals("/")){
//
//            StringBuilder buffer = new StringBuilder();
//            BufferedReader reader = request.getReader();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line);
//            }
//
//            String payload = buffer.toString();
//
//            User user = _gson.fromJson(payload, User.class);
//
//            userService.save(user);
//
//            sendAsJson(response, user);
//        }
//        else {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//        }
//    }
//
//    // Updates a model in DB
//    // PUT/JavaViewer/models/id
//    protected void doPut(
//            HttpServletRequest request,
//            HttpServletResponse response)
//            throws IOException, ServletException {
//
//        String pathInfo = request.getPathInfo();
//
//        if(pathInfo == null || pathInfo.equals("/")){
//
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        String[] splits = pathInfo.split("/");
//
//        if(splits.length != 2) {
//
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        Long userId = Long.parseLong(splits[1]);
//
//        // not contains key
//
//        if(userService.hasUser(userId)) {
//
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        StringBuilder buffer = new StringBuilder();
//        BufferedReader reader = request.getReader();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            buffer.append(line);
//        }
//
//        String payload = buffer.toString();
//
//        User user = _gson.fromJson(payload, User.class);
//
//        user.setId(userId);
//
//        // вынести в сервис update
//        userService.deleteById(userId);
//        userService.save(user);
//
//        sendAsJson(response, user);
//    }
//
//    // Deletes a model in DB
//    // DELETE/JavaViewer/models/id
//    protected void doDelete(
//            HttpServletRequest request,
//            HttpServletResponse response)
//            throws IOException, ServletException {
//
//        String pathInfo = request.getPathInfo();
//
//        if(pathInfo == null || pathInfo.equals("/")){
//
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        String[] splits = pathInfo.split("/");
//
//        if(splits.length != 2) {
//
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        Long userId = Long.parseLong(splits[1]);
//
//        User userToDelete = userService.findById(userId);
//
//        if(userToDelete == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        userService.deleteById(userId);
//
//        sendAsJson(response, userToDelete);
//    }
}