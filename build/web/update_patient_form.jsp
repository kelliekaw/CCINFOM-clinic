<%-- 
    Document   : update_patient_processing
    Created on : Nov 23, 2024, 2:22:15 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Patient Record</title>
    </head>
    <body>
        <jsp:useBean id="p" class="clinicmanagement.patients" scope="session" />
        <% // Receive values
            String str_patient_id = request.getParameter("patient_id");
            if (str_patient_id == null || str_patient_id.trim().isEmpty()) { %>
                <h1>No Patient Selected</h1>
                <a href="index.html">Back to Menu</a>
        <%
            } else {
            int patientID = Integer.parseInt(str_patient_id);
            boolean status = p.get_patient_record(patientID);
            if (status) {   %>
            <form action="update_patient_processing.jsp" method="post">
                <h2>Update Patient Record</h2>

                <input type="hidden" name="patient_id" value="<%= patientID %>">

                Last Name: <input type="text" id="last_name" name="last_name" value="<%= p.last_name %>"required><br><br>
                First Name: <input type="text" id="first_name" name="first_name" value="<%= p.first_name %>" required><br><br>
                Middle Initial: <input type="text" id="middle_initial" name="middle_initial" value="<%= p.middle_initial %>"><br><br>
                Mobile Number: <input type="tel" id="mobile_number" name="mobile_number" value="<%= p.mobile_number %>" required><br><br>
                Email Address: <input type="email" id="email_address" name="email_address" value="<%= p.email_address %>"required><br><br>

                <input type="submit" value="Update"><br>
            </form>
        <%      } else { %>
               <h1>Patient Record Not Found</h1>
               <a href="index.html">Back to Menu</a>
        <%      }   
           } %>
    </body>
</html>
