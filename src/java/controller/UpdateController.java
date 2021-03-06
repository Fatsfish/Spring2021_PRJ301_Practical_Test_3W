/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.FoodDAO;
import dtos.FoodDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User-PC
 */
public class UpdateController extends HttpServlet {

    private static final String SUCCESS = "index.jsp";
    private static final String ERROR = "error.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Delete".equals(action)) {
                String id = request.getParameter("ID");
                String search = request.getParameter("search");
                FoodDAO dao = new FoodDAO();
                FoodDTO dto = dao.getFood(id);
                dto.setIsDelete(false);
                dao.delete(dto);
                HttpSession session = request.getSession();
                ArrayList<FoodDTO> list = new ArrayList<FoodDTO>();
                list = dao.search(search);
                if (list != null) {
                    session.setAttribute("LIST", list);
                    url = SUCCESS;
                }
            }
            if ("Update".equals(action)) {
                String id = request.getParameter("ID");
                String search = request.getParameter("search");
                FoodDAO dao = new FoodDAO();
                FoodDTO dto = dao.getFood(id);
                dao.update(dto);
                HttpSession session = request.getSession();
                ArrayList<FoodDTO> list = new ArrayList<FoodDTO>();
                list = dao.search(search);
                if (list != null) {
                    session.setAttribute("LIST", list);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

}
