<%-- 
    Document   : update_sales_processing
    Created on : Nov 23, 2024, 4:39:48 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Sales Processing</title>
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <form action="index.html" method="post">
            <% // Receive values
                String str_sale_id = request.getParameter("sale_id");
                int sale_id = Integer.parseInt(str_sale_id);
                String str_amt = request.getParameter("amt_paid");
                float amt_paid = Float.parseFloat(str_amt);
                boolean status = s.update_sales(sale_id, amt_paid);
                    if (status) {
           %>
                   <h1>Sale Update Successful</h1>
           <%       } else {    %>
                   <h1>Sale Update Unsuccessful</h1>
           <%       }           %>
            <input type="submit" value="Back to Menu">
        </form>
    </body>
</html>
