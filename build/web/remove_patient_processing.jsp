<%-- 
    Document   : removepatient_processing
    Created on : Nov 22, 2024, 8:26:56 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Patient Processing</title>
    </head>
    <body>
        <jsp:useBean id="p" class="clinicmanagement.patients" scope="session" />
        <form action="index.html" method="post">
            <% // Receive values
               String str_patient_id = request.getParameter("patient_id");
               if (str_patient_id == null || str_patient_id.trim().isEmpty()) { %>
               <h1>No Patient Selected</h1>
           <%
               } else {
                int patient_id = Integer.parseInt(str_patient_id);
                boolean status = p.remove_patient(patient_id);
                    if (status) {
           %>
                   <h1>Patient Record Removal Successful</h1>
           <%       } else { %>
                   <h1>Patient Record Removal Unsuccessful</h1>
           <%       }   
               } %>
            <input type="submit" value="Back to Menu">
        </form>
    </body>
</html>
