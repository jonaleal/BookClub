/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.udea.bookclub.controller;

import com.udea.bookclub.business.DiscussionBusiness;
import com.udea.bookclub.business.IDiscussionBusiness;
import com.udea.bookclub.domain.BookClub;
import com.udea.bookclub.domain.Discussion;
import com.udea.bookclub.domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jon Leal
 */
public class DiscussionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private IDiscussionBusiness discussionBusiness;

    @Override
    public void init() {
        discussionBusiness = new DiscussionBusiness();
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
            case "/discussion/create":
                createDiscussion(request, response);
                break;
            case "/discussion/update":
                updateDiscussion(request, response);
                break;
            case "/discussion/delete":
                deleteDiscussion(request, response);
                break;
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

    private void createDiscussion(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Recupera los datos del formulario
        String tittle = request.getParameter("tittle");
        String description = request.getParameter("description");
        String username = request.getParameter("username");
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        // Crea un objeto Discussion con los datos del formulario
        Discussion newDiscussion = new Discussion(tittle, description, new Date(), new User(username), new BookClub(clubId));
        // Crea el club en la base de datos
        discussionBusiness.createDiscussion(newDiscussion);
        // Redirecciona a la lista de clubes
        response.sendRedirect("/BookClub/book-club/show?clubId=" + String.valueOf(clubId));
    }

    private void updateDiscussion(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void deleteDiscussion(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
