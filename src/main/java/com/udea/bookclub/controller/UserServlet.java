/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.udea.bookclub.controller;

import com.udea.bookclub.business.IUserBusiness;
import com.udea.bookclub.business.UserBusiness;
import com.udea.bookclub.domain.User;
import static com.udea.bookclub.util.DateConversionUtil.convertStringToDate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jon Leal
 */
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private IUserBusiness userBusiness;

    @Override
    public void init() {
        userBusiness = new UserBusiness();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        System.out.println(action);

        boolean isLogued = request.getSession().getAttribute("isLogued") == null ? false : (boolean) request.getSession().getAttribute("isLogued");
        if (!isLogued) {
            response.sendRedirect("/BookClub/test.jsp");
            return;
        }

        switch (action) {
            case "/user/list":
                listUsers(request, response);
                break;
            case "/user/update-form":
                showUpdateForm(request, response);
                break;
            case "/user/update":
                updateUser(request, response);
                break;
//            case "/user/delete":
//                deleteUser(request, response);
//                break;
            case "/user/sign-out":
                signOutUser(request, response);
                break;
            default:
                listUsers(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userBusiness.getUsers();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera el username
        String username = (String) request.getSession().getAttribute("username");
        // Crea un objeto User con el username
        User user = new User(username);
        // Obtiene el usuario de la base de datos
        user = userBusiness.getUser(user);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/user-update.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera los datos del formulario
        String username = request.getParameter("username");
        String pictureUrl = request.getParameter("pictureUrl");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        //Recupera el user de la Base de Datos
        User user = userBusiness.getUser(new User(username));
        // Actualiza el objeto User con los datos del formulario
        user.setPictureUrl(pictureUrl);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        // Crea el usuario en la base de datos
        userBusiness.updateUser(user);
        // Redirecciona a 
        HttpSession session = request.getSession();
        session.setAttribute("message", "Actualización exitosa");
        response.sendRedirect("/BookClub/user/update-form");
    }

    private void signOutUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //Recupera la sesion
        HttpSession session = request.getSession();
        session.setAttribute("username", "Null");
        session.setAttribute("isLogued", false);
        response.sendRedirect("/BookClub/user/sign-in-form");
    }
}
