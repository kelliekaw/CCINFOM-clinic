<%-- 
    Document   : removepharmacy_processing
    Created on : Nov 22, 2024, 11:09:12 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, clinicmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Pharmacy Record Processing</title>
    </head>
    <body>
        <jsp:useBean id="ph" class="clinicmanagement.pharmacy" scope="session" />
        <form action="index.html" method="post">
            <% // Receive values
               String str_drug_id = request.getParameter("drug_id");
               if (str_drug_id == null || str_drug_id.trim().isEmpty()) { %>
               <h1>No Drug Selected</h1>
           <%
               } else {
                int drug_id = Integer.parseInt(str_drug_id);
                boolean status = ph.remove_drug(drug_id);
                    if (status) {
           %>
                   <h1>Pharmacy Record Removal Successful</h1>
           <%       } else { %>
                   <h1>Pharmacy Record Removal Unsuccessful</h1>
           <%       }   
               } %>
            <input type="submit" value="Back to Menu">
        </form>
    </body>
</html>
