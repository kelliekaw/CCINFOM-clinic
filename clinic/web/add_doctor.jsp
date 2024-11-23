<%-- 
    Document   : add_doctor
    Created on : Nov 22, 2024, 1:32:37 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, clinicmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Doctor Record</title>
    </head>
    <body>
        <form action="index.html" method="post">
            <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
            <% // Receive values
               String last_name = request.getParameter("last_name");
               String first_name = request.getParameter("first_name");
               String middle_initial = request.getParameter("middle_initial");
               String gender = request.getParameter("gender");
               String birthdate = request.getParameter("birthdate");
               String str_consultation_rate = request.getParameter("consultation_rate");
               float consultation_rate = Float.parseFloat(str_consultation_rate);
               String str_mobile_number = request.getParameter("mobile_number");
               long mobile_number = Long.parseLong(str_mobile_number);
               String email_address = request.getParameter("email_address");
               String[] specializations = request.getParameterValues("specialization[]");
               d.set_values(last_name, first_name, middle_initial, gender, birthdate, consultation_rate, mobile_number, email_address, specializations);
               boolean status = d.add_doctor();
               if (status) {
           %>
                   <h1>Doctor Successfully Recorded</h1>
           <%  } else { %>
                   <h1>Doctor Not Recorded</h1>
           <%  } %>
           
           <input type="submit" value="Return to Menu">
        </form>
    </body>
</html>
