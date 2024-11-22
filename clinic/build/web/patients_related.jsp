<%-- 
    Document   : patients_related
    Created on : Nov 23, 2024, 1:19:06 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patients and Visits Records</title>
    </head>
    <body>
        <jsp:useBean id="p" class="clinicmanagement.patients" scope="session" />
        <jsp:useBean id="v" class="clinicmanagement.visits" scope="session" />
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <jsp:useBean id="a" class="clinicmanagement.ailments" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Patient ID</th>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>Gender</th>
                    <th>Date of Birth</th>
                    <th>Mobile Number</th>
                    <th>Email</th>
                    <th>Log In</th>
                    <th>Log Out</th>
                    <th>Ailment</th>
                    <th>Doctor</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    p.get_related(v, d, a);       
                    for (int i = 0; i < p.patient_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= p.patient_idList.get(i) %></td>
                        <td><%= p.last_nameList.get(i) %></td>
                        <td><%= p.first_nameList.get(i) %></td>
                        <td><%= p.genderList.get(i) %></td>
                        <td><%= p.birthdateList.get(i) %></td>
                        <td><%= p.mobile_numberList.get(i) %></td>
                        <td><%= p.email_addressList.get(i) %></td>
                        <td><%= v.log_inList.get(i) %></td>
                        <td><%= v.log_outList.get(i) %></td>
                        <td><%= a.nameList.get(i) %></td>
                        <td><%= d.last_nameList.get(i)%>, <%= d.first_nameList.get(i)%></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <br>
        <a href="view_record.html">
            <input type="button" value="Back">
        </a>
    </body>
</html>
