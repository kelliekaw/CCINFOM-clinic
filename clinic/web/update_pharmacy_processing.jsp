<%-- 
    Document   : update_pharmacy_processing
    Created on : Nov 23, 2024, 4:15:17 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Pharmacy Processing</title>
    </head>
    <body>
        <jsp:useBean id="ph" class="clinicmanagement.pharmacy" scope="session" />
        <form action="index.html" method="post">
            <% // Receive values
                String str_drug_id = request.getParameter("drug_id");
                int drug_id = Integer.parseInt(str_drug_id);
                String str_price = request.getParameter("price");
                float price = Float.parseFloat(str_price);
                boolean status = ph.update_pharmacy(drug_id, price);
                    if (status) {
           %>
                   <h1>Pharmacy Update Successful</h1>
           <%       } else {    %>
                   <h1>Pharmacy Update Unsuccessful</h1>
           <%       }           %>
            <input type="submit" value="Back to Menu">
        </form>
    </body>
</html>
