<%@page import="com.emergentes.modelo.Tarea"%>
<%
    Tarea reg = (Tarea) request.getAttribute("miobjtar");
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
        <h1>Registro de Tareas</h1>

        <form action="MainServlet" method="post">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="<%= reg.getId()%>" readonly></td>
                </tr>
                <tr>
                    <td>Tarea</td>
                    <td><input type="text" name="tarea" value="<%= reg.getTarea()%>"></td>
                </tr>
                <tr>
                    <td>Completado</td>
                    
                    <td>
                        <input type="radio" name="estado" value="Pendiente" <%= (reg.getEstado().equals("Pendiente")) ? "checked" : ""%>> Pendiente
                        <input type="radio" name="estado" value="Completado" <%= (reg.getEstado().equals("Completado")) ? "checked" : ""%>> Completado
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
