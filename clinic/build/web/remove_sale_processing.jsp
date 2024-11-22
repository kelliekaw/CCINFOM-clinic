<%-- 
    Document   : remove_sale_processing
    Created on : Nov 22, 2024, 11:43:35 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, clinicmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Sales Record Processing</title>
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <form action="index.html">
            <% // Receive values
               String str_sale_id = request.getParameter("sale_id");
               if (str_sale_id == null || str_sale_id.trim().isEmpty()) { %>
               <h1>No Sale Selected</h1>
           <%
               } else {
                int sale_id = Integer.parseInt(str_sale_id);
                boolean status = s.remove_sale(sale_id);
                    if (status) {
           %>
                   <h1>Sales Record Removal Successful</h1>
           <%       } else { %>
                   <h1>Sales Record Removal Unsuccessful</h1>
           <%       }   
               } %>
            <input type="submit" value="Back to Menu">
        </form>
    </body>
</html>
