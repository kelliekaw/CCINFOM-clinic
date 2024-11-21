<%-- 
    Document   : add_pharmacy
    Created on : Nov 22, 2024, 2:41:25 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, clinicmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Pharmacy Record</title>
    </head>
    <body>
        <form action="index.html">
            <jsp:useBean id="ph" class="clinicmanagement.pharmacy" scope="session" />
            <% // Receive values
               String generic_name = request.getParameter("generic_name");
               String brand_name = request.getParameter("brand_name");
               String str_price = request.getParameter("price");
               float price = Float.parseFloat(str_price);
               ph.set_values(generic_name, brand_name, price);
               boolean status = ph.add_pharmacy();
               if (status) {
           %>
                   <h1>Drug Successfully Recorded</h1>
           <%  } else { %>
                   <h1>Drug Not Recorded</h1>
           <%  } %>
           
           <input type="submit" value="Return to Menu">
        </form>
    </body>
</html>
