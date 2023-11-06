/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.udea.bookclub.controller;

import com.udea.bookclub.business.BookClubBusiness;
import com.udea.bookclub.business.IBookClubBusiness;
import com.udea.bookclub.domain.BookClub;
import com.udea.bookclub.domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jon Leal
 */
public class BookClubServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private IBookClubBusiness bookClubBusiness;

    @Override
    public void init() {
        bookClubBusiness = new BookClubBusiness();
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
            case "/book-club/list":
                listBookclubs(request, response);
                break;
            case "/book-club/list-my-created-clubs":
                listMyCreatedBookclubs(request, response);
                break;
            case "/book-club/list-my-joined-clubs":
                listMyJoinedBookclubs(request, response);
                break;
            case "/book-club/show":
                showBookclub(request, response);
                break;
            case "/book-club/create-form":
                showCreateForm(request, response);
                break;
            case "/book-club/create":
                createBookclub(request, response);
                break;
            case "/book-club/update-form":
                showUpdateForm(request, response);
                break;
            case "/book-club/update":
                updateBookclub(request, response);
                break;
            case "/book-club/delete":
                deleteBookClub(request, response);
                break;
            default:
                listBookclubs(request, response);
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

    private void listBookclubs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<BookClub> bookClubs = bookClubBusiness.getBookClubs();
        request.setAttribute("bookClubs", bookClubs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/book-club/book-club-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showBookclub(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        BookClub bookClub = bookClubBusiness.getBookClub(new BookClub(clubId));
        request.setAttribute("bookClub", bookClub);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/book-club/book-club-show.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/book-club/book-club-create.jsp");
        dispatcher.forward(request, response);
    }

    private void createBookclub(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Recupera los datos del formulario
        String name = request.getParameter("name");
        String descripcion = request.getParameter("descripcion");
        String tags = request.getParameter("tags");
        String pictureUrl = request.getParameter("pictureUrl");
        String meetLink = request.getParameter("meetLink");
        String username = request.getParameter("username");
        // Crea un objeto BookClub con los datos del formulario
        BookClub newBookClub = new BookClub(name, descripcion, tags, meetLink, new User(username));
        newBookClub.setPictureUrl(pictureUrl);
        // Crea el club en la base de datos
        bookClubBusiness.createBookClub(newBookClub);
        // Redirecciona a la lista de clubes
        response.sendRedirect("/BookClub/book-club/list");
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        BookClub bookClub = bookClubBusiness.getBookClub(new BookClub(clubId));
        request.setAttribute("bookClub", bookClub);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/book-club/book-club-update.jsp");
        dispatcher.forward(request, response);
    }

    private void updateBookclub(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = (String) request.getSession().getAttribute("username");
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        String name = request.getParameter("name");
        String descripcion = request.getParameter("descripcion");
        String tags = request.getParameter("tags");
        String meetLink = request.getParameter("meetLink");
        // Obtiene el objeto BookClub de la Base de Datos
        BookClub bookClub = bookClubBusiness.getBookClub(new BookClub(clubId));
        bookClub.setName(name);
        bookClub.setDescription(descripcion);
        bookClub.setTags(tags);
        bookClub.setMeetLink(meetLink);
        bookClub.setUserList(List.of(new User(userName)));
        // Actuliza el club en la base de datos
        bookClubBusiness.updateBookClub(bookClub);
        response.sendRedirect("/BookClub/book-club/show?clubId=" + clubId);
    }

    private void listMyCreatedBookclubs(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("username");
        List<BookClub> bookClubs = bookClubBusiness.getCreatedBookClubsByUser(username);
        request.setAttribute("bookClubs", bookClubs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/book-club/book-club-my-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listMyJoinedBookclubs(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("username");
        List<BookClub> bookClubs = bookClubBusiness.getJoinedBookClubsByUser(username);
        request.setAttribute("bookClubs", bookClubs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/book-club/book-club-list.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteBookClub(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        bookClubBusiness.deleteBookClub(clubId);
        response.sendRedirect("/BookClub/book-club/list-my-created-clubs");
    }
}
