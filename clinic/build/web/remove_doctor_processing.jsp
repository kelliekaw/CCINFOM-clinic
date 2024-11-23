<%-- 
    Document   : removedoctor_processing
    Created on : Nov 22, 2024, 10:01:32 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, clinicmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Doctor Record Processing</title>
    </head>
    <body>
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <form action="index.html" method="post">
            <% // Receive values
               String str_doctor_id = request.getParameter("doctor_id");
               if (str_doctor_id == null || str_doctor_id.trim().isEmpty()) { %>
               <h1>No Doctor Selected</h1>
           <%
               } else {
                int doctor_id = Integer.parseInt(str_doctor_id);
                boolean status = d.remove_doctor(doctor_id);
                    if (status) {
           %>
                   <h1>Doctor Record Removal Successful</h1>
           <%       } else { %>
                   <h1>Doctor Record Removal Unsuccessful</h1>
           <%       }   
               } %>
            <input type="submit" value="Back to Menu">
        </form>
    </body>
</html>
