/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.modelo.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zerlu
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
String op=request.getParameter("op");
        Tarea objper=new Tarea();
        int id,pos;
        HttpSession ses=request.getSession();
        ArrayList<Tarea> lista=(ArrayList<Tarea>)ses.getAttribute("listaTarea");
        switch(op){
            case "nuevo":
                request.setAttribute("miobjtar", objper);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id=Integer.parseInt(request.getParameter("id"));
                pos=buscarPorIndice(request, id);
                objper=lista.get(pos);
                request.setAttribute("miobjtar", objper);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                id=Integer.parseInt(request.getParameter("id"));
                pos=buscarPorIndice(request, id);
                if (pos>=0) {
                    lista.remove(pos);
                }
                request.setAttribute("listaTarea", lista);
                response.sendRedirect("index.jsp");
                break;
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
int id=Integer.parseInt(request.getParameter("id"));
        HttpSession ses=request.getSession();
        ArrayList<Tarea> lista=(ArrayList<Tarea>) ses.getAttribute("listaTarea");
        Tarea objper=new Tarea();
        objper.setId(id);
        objper.setTarea(request.getParameter("tarea"));
        objper.setEstado(request.getParameter("estado"));
        
        if (id==0) {
            int idNuevo=obtenerId(request);
            objper.setId(idNuevo);
            lista.add(objper);
        }else{
            int pos=buscarPorIndice(request, id);
            lista.set(pos, objper);
        }
        request.setAttribute("listaTarea", lista);
        response.sendRedirect("index.jsp");
    }
     public int buscarPorIndice(HttpServletRequest request, int id){
        HttpSession ses=request.getSession();
        ArrayList<Tarea>lista=(ArrayList<Tarea>) ses.getAttribute("listaTarea");
        
        int pos=-1;
        if (lista!=null) {
            for (Tarea tarea : lista) {
                ++pos;
                if (tarea.getId()==id) {
                    break;
                }
            }
        }
        return pos;
    }
    
    public int obtenerId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Tarea> lista=(ArrayList<Tarea>) ses.getAttribute("listaTarea");
        
        int idn=0;
        for (Tarea tarea : lista) {
            idn=tarea.getId();
        }
        return idn+1;
    }
}
