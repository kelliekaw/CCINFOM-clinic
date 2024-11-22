<%-- 
    Document   : doctors_related
    Created on : Nov 23, 2024, 2:13:18 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor and Specialization Records</title>
    </head>
    <body>
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Doctor ID</th>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>Specialization</th>
                    <th>Gender</th>
                    <th>Consultation Rate</th>
                    <th>Date of Birth</th>
                    <th>Mobile Number</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    d.select_doctor();
                    for (int i = 0; i < d.doctor_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= d.doctor_idList.get(i) %></td>
                        <td><%= d.last_nameList.get(i) %></td>
                        <td><%= d.first_nameList.get(i) %></td>
                        <td><%= d.specializationList.get(i) %></td>
                        <td><%= d.genderList.get(i) %></td>
                        <td><%= d.birthdateList.get(i) %></td>
                        <td><%= d.consultation_rateList.get(i) %></td>
                        <td><%= d.mobile_numberList.get(i) %></td>
                        <td><%= d.email_addressList.get(i) %></td>
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
