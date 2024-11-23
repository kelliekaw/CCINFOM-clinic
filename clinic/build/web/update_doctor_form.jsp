<%-- 
    Document   : update_doctor_form
    Created on : Nov 23, 2024, 3:05:42 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Doctor Record</title>
    </head>
    <body>
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <% // Receive values
            String str_doctor_id = request.getParameter("doctor_id");
            if (str_doctor_id == null || str_doctor_id.trim().isEmpty()) { %>
                <h1>No Doctor Selected</h1>
                <a href="index.html">Back to Menu</a>
        <%
            } else {
            int doctorID = Integer.parseInt(str_doctor_id);
            boolean status = d.get_doctor_record(doctorID);
            if (status) {   %>
            <form action="update_doctor_processing.jsp" method="post">
                <h2>Update Doctor Record</h2>

                <input type="hidden" name="doctor_id" value="<%= doctorID %>">

                Last Name: <input type="text" id="last_name" name="last_name" value="<%= d.last_name %>"required><br><br>
                First Name: <input type="text" id="first_name" name="first_name" value="<%= d.first_name %>" required><br><br>
                Middle Initial: <input type="text" id="middle_initial" name="middle_initial" value="<%= d.middle_initial %>"><br><br>
                Mobile Number: <input type="tel" id="mobile_number" name="mobile_number" value="<%= d.mobile_number %>" required><br><br>
                Email Address: <input type="email" id="email_address" name="email_address" value="<%= d.email_address %>"required><br><br>

                <input type="submit" value="Update"><br>
            </form>
        <%      } else { %>
               <h1>Doctor Record Not Found</h1>
               <a href="index.html">Back to Menu</a>
        <%      }   
           } %>
    </body>
</html>
