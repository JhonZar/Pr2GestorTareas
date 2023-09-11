<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Tarea"%>
<%
    if (session.getAttribute("listaTarea") == null) {
        ArrayList<Tarea> lisaux = new ArrayList<Tarea>();
        session.setAttribute("listaTarea", lisaux);
    }
    ArrayList<Tarea> lista = (ArrayList<Tarea>) session.getAttribute("listaTarea");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Gestor de tareas</h1>
        <h3>Nombre: Jhonatan Luis Zarzuri Chauca</h3>
        <a href="MainServlet?op=nuevo">Nuevo</a>
        <table border="1">
            <tr>
                <th>ID</th><!-- comment -->
                <th>Tarea</th><!-- comment -->
                <th>Completado</th>
                <th></th>
                    

            </tr>
            <%
                if (lista != null) {
                    for (Tarea tarea : lista) {


            %>
            <tr>
                <td><%= tarea.getId()%></td>
                <td><%= tarea.getTarea()%></td>
                <td>
                    <% if (tarea.getEstado().equals("Completado")) { %>
                    <input type="checkbox" name="estado" value="Completado" checked disabled> 
                    <% } else {%>
                    <input type="checkbox" name="estado" value="Completado" disabled> 
                    <% }%>
                </td>
                <td>
                    <a href="MainServlet?op=editar&id=<%= tarea.getId()%>">Editar</a>
                    <a href="MainServlet?op=eliminar&id=<%= tarea.getId()%>" onclick="return(confirm('esta seguro de eliminar?'))">Eliminar</a>
                </td>
                
            </tr>
            <%       }
                }
            %>
        </table>
    </center>
</body>
</html>
