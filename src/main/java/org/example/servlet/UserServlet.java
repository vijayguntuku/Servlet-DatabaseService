package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Interface.UserService;
import org.example.model.User;
import org.example.service.InmemoryService;
import org.example.service.InDatabaseService;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private InmemoryService inMemoryService=new InmemoryService();

    private UserService userService = new InDatabaseService();
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
      List<User> users= userService.listUsers();
      response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));
    }
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse  response)throws IOException {
        User user = objectMapper.readValue(request.getReader(), User.class);
        request.getRequestURI();
        inMemoryService.insert(user);
        userService.insert(user);
    }
    @Override
    protected void doDelete(HttpServletRequest request,HttpServletResponse  response)throws IOException
    {
        User user = objectMapper.readValue(request.getReader(),User.class);
        inMemoryService.delete(user.getId());
        userService.delete(user.getId());
    }

}
