<%-- 
    Document   : update_doctor_processing
    Created on : Nov 23, 2024, 3:09:10 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Doctor Processing</title>
    </head>
    <body>
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <form action="index.html" method="post">
            <% // Receive values
                String str_doctor_id = request.getParameter("doctor_id");
                int doctor_id = Integer.parseInt(str_doctor_id);
                String last_name = request.getParameter("last_name");
                String first_name = request.getParameter("first_name");
                String middle_initial = request.getParameter("middle_initial");
                String str_mobile_number = request.getParameter("mobile_number");
                long mobile_number = Long.parseLong(str_mobile_number);
                String email_address = request.getParameter("email_address");
                boolean status = d.update_doctor(doctor_id, last_name, first_name, middle_initial, mobile_number, email_address);
                if (status) {
            %>
                   <h1>Doctor Update Successful</h1>
            <%  } else {    %>
                   <h1>Doctor Update Unsuccessful</h1>
            <%  }           %>
            <input type="submit" value="Back to Menu">
        </form>
    </body>
</html>
