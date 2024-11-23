<%-- 
    Document   : patient_admission
    Created on : Nov 21, 2024, 8:26:26 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, clinicmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Patient</title>
    </head>
    <body>
        <form action="index.html" method="post" >
            <jsp:useBean id="p" class="clinicmanagement.patients" scope="session" />
            <% // Receive values
               String last_name = request.getParameter("last_name");
               String first_name = request.getParameter("first_name");
               String middle_initial = request.getParameter("middle_initial");
               String gender = request.getParameter("gender");
               String birthdate = request.getParameter("birthdate");
               String str_mobile_number = request.getParameter("mobile_number");
               long mobile_number = Long.parseLong(str_mobile_number);
               String email_address = request.getParameter("email_address");
               p.set_values(last_name, first_name, middle_initial, gender, birthdate, mobile_number, email_address);
               boolean status = p.add_patient();
               if (status) {
           %>
                   <h1>Patient Successfully Recorded</h1>
           <%  } else { %>
                   <h1>Patient Not Recorded</h1>
           <%  } %>
           
           <input type="submit" value="Return to Menu">
        </form>
    </body>
</html>
