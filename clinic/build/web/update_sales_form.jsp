<%-- 
    Document   : update_sales_form
    Created on : Nov 23, 2024, 4:35:54 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Sales Record</title>
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <% // Receive values
            String str_sale_id = request.getParameter("sale_id");
            if (str_sale_id == null || str_sale_id.trim().isEmpty()) { %>
                <h1>No Sale Selected</h1>
                <a href="index.html">Back to Menu</a>
        <%
            } else {
            int saleID = Integer.parseInt(str_sale_id);
            boolean status = s.get_sales_record(saleID);
            if (status) {   %>
            <form action="update_sales_processing.jsp" method="post">
                <h2>Update Sales Record</h2>

                <input type="hidden" name="sale_id" value="<%= saleID %>">

                Sale ID: <%= s.sale_id %> <br><br>
                Visit ID: <%= s.visit_id %> <br><br>
                Amount Paid: <input type="number" name="amt_paid" value="<%= s.amt_paid %>" step ="0.01" min="0" required><br><br>

                <input type="submit" value="Update"><br>
            </form>
        <%      } else { %>
               <h1>Sales Record Not Found</h1>
               <a href="index.html">Back to Menu</a>
        <%      }   
           } %>
    </body>
</html>
