package org.example.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.User;
import org.example.repository.impl.UserRepositoryImpl;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final ObjectMapper mapper = new ObjectMapper();


    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        try {
            String id = request.getParameter("id");

            if (id == null) {
                writeEmptyId(response);
            } else if (id.equals("all")) {
                writeAllUsers(response);
            } else {
                writeUserById(response, id);
            }

        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("id unknown");
            response.setStatus(400);
        } finally {
            response.getWriter().close();
        }
    }

    private void writeUserById(HttpServletResponse response, String id) throws JsonProcessingException, IOException {
        User user = userService.findById(Long.parseLong(id));
        String jsonString = mapper.writeValueAsString(user);
        response.getWriter().write(jsonString);
        response.setStatus(200);
    }

    private void writeAllUsers(HttpServletResponse response) throws JsonProcessingException, IOException {
        List<User> users = userService.findAll();
        String jsonString = mapper.writeValueAsString(users);
        response.getWriter().write(jsonString);
        response.setStatus(200);
    }

    private void writeEmptyId(HttpServletResponse response) throws IOException {
        response.getWriter().write("id must be empty");
        response.setStatus(400);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        try {
            String name = request.getParameter("name");
            if (name == null) {
                writeEmptyName(response);
            } else {
                User updatedUser = updateUser(name);
                writeUser(response, updatedUser);
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("user unknown");
        }
    }

    private void writeUser(HttpServletResponse response, User updatedUser) throws JsonProcessingException, IOException {
        String jsonString = mapper.writeValueAsString(updatedUser);
        response.getWriter().write(jsonString);
        response.setStatus(200);
    }

    private User updateUser(String name) {
        User user = new User();
        user.setName(name);
        return userService.save(user);
    }

    private void writeEmptyName(HttpServletResponse response) throws IOException {
        response.getWriter().write("name mustn't be empty");
        response.setStatus(400);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        try {
            String id = request.getParameter("id");

            if (id == null) {
                response.getWriter().write("id must not empty");
                response.setStatus(400);
            } else {
                Long longId = Long.valueOf(id);

                String newName = request.getParameter("name");

                if (newName.isBlank() || newName.isEmpty()) {
                    response.getWriter().write("empty name");
                } else {
                    User newUser = new User();
                    newUser.setId(longId);
                    newUser.setName(newName);

                    User updatedUser = userService.update(newUser);
                    String jsonString = mapper.writeValueAsString(updatedUser);
                    response.getWriter().write(jsonString);
                    response.setStatus(200);
                }

            }
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("id unknown");
            response.setStatus(400);
        } finally {
            response.getWriter().close();
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        try {
            String id = request.getParameter("id");

            if (id == null) {
                response.getWriter().write("id must not empty");
                response.setStatus(400);
            } else if (id.equals("all")) {
                if (userService.deleteById(Long.valueOf(id))) {
                    response.getWriter().write("name deleted");
                    response.setStatus(200);
                } else {
                    response.getWriter().write("name wasn't found");
                    response.setStatus(200);
                }
            } else {
                if (userService.deleteById(Long.valueOf(id))) {
                    response.getWriter().write("name deleted");
                    response.setStatus(200);
                } else {
                    response.getWriter().write("name wasn't found");
                    response.setStatus(200);
                }
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.getWriter().write("id unknown");
            response.setStatus(400);
        } finally {
            response.getWriter().close();
        }
    }
}
