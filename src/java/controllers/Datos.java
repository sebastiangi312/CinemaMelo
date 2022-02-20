/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Factura;
import models.Pelicula;
import models.Persona;

/**
 *
 * @author juan pablo cano
 */
@WebServlet(name = "Datos", urlPatterns = {"/Datos"})
public class Datos extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

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
        HttpSession session = request.getSession();
        int AsisMaxima = 0;
        int AsisMinima = 100;
        Pelicula PAsisMaxima = null;
        Pelicula PAsisMinima = null;
        for (Pelicula peli : Pelicula.pelis) {
            if (AsisMaxima < peli.aumentarAsistencia(peli)) {
                AsisMaxima = peli.aumentarAsistencia(peli);
                PAsisMaxima = peli;
            }
            if(AsisMinima > peli.aumentarAsistencia(peli)){
                AsisMinima = peli.aumentarAsistencia(peli);
                PAsisMinima = peli;
            }
                
            
        }
        Pelicula pelicula = Pelicula.buscarPelicula(request.getParameter("pelicula"));
        double valMaxima = 0;
        double valMinima = 100;
        Pelicula PVMaxima = null;
        Pelicula PVMinima = null;
        for (Pelicula peli : Pelicula.pelis) {
            if (valMaxima < peli.getPuntuacion()) {
                valMaxima =  peli.getPuntuacion();
                PVMaxima = peli;
            }
            if(valMinima > peli.getPuntuacion()){
                valMinima = peli.getPuntuacion();
                PVMinima = peli;
            }
        }
        session.setAttribute("valMinima",valMinima);
        session.setAttribute("PVMaxima",PVMaxima);
        session.setAttribute("valMaxima",valMaxima);
        session.setAttribute("PVMinima",PVMinima);
        session.setAttribute("AsisMinima", AsisMaxima);
        session.setAttribute("PAsisMinima", PAsisMaxima);
        session.setAttribute("AsisMaxima", AsisMaxima);
        session.setAttribute("PAsisMaxima", PAsisMaxima);
        Persona MayorD = (Persona) session.getAttribute("MayorD");
        Factura factura = (Factura) session.getAttribute("factura");
        RequestDispatcher view = request.getRequestDispatcher("datos.jsp");
        view.forward(request, response);

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
