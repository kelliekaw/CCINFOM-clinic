<%-- 
    Document   : add_sales
    Created on : Nov 22, 2024, 3:03:09 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, clinicmanagement.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Sales Record</title>
    </head>
    <body>
        <form action="index.html" method="post">
            <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
            <% // Receive values
                String str_visit_id = request.getParameter("visit_id");
                if (str_visit_id == null || str_visit_id.trim().isEmpty()) { %>
                    <h1>No Visit ID Selected</h1>
           <%
               } else {
                    int visit_id = Integer.parseInt(str_visit_id);
                    String str_amt_paid = request.getParameter("amt_paid");
                    float amt_paid = Float.parseFloat(str_amt_paid);
                    s.set_values(visit_id, amt_paid);
                    boolean status = s.add_sale();
                    if (status) {
               %>
                       <h1>Sale Successfully Recorded</h1>
               <%   } else { %>
                       <h1>Sale Not Recorded</h1>
               <%   } 
                } %>
           
           <input type="submit" value="Return to Menu">
        </form>
    </body>
</html>
