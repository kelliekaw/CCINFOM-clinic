<%-- 
    Document   : update_pharmacy_form
    Created on : Nov 23, 2024, 3:51:51 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Pharmacy Record</title>
    </head>
    <body>
        <jsp:useBean id="ph" class="clinicmanagement.pharmacy" scope="session" />
        <% // Receive values
            String str_drug_id = request.getParameter("drug_id");
            if (str_drug_id == null || str_drug_id.trim().isEmpty()) { %>
                <h1>No Drug Selected</h1>
                <a href="index.html">Back to Menu</a>
        <%
            } else {
            int drugID = Integer.parseInt(str_drug_id);
            boolean status = ph.get_pharmacy_record(drugID);
            if (status) {   %>
            <form action="update_pharmacy_processing.jsp" method="post">
                <h2>Update Pharmacy Record</h2>

                <input type="hidden" name="drug_id" value="<%= drugID %>">

                Generic Name: <%= ph.generic_name %> <br><br>
                Brand Name: <%= ph.brand_name %> <br><br>
                Price: <input type="number" name="price" value="<%= ph.price %>" step ="0.01" min="0" required><br><br>
                Type: <%= ph.type %><br><br>

                <input type="submit" value="Update"><br>
            </form>
        <%      } else { %>
               <h1>Pharmacy Record Not Found</h1>
               <a href="index.html">Back to Menu</a>
        <%      }   
           } %>
    </body>
</html>
