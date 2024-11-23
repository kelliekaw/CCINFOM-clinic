<%-- 
    Document   : doctors_filter
    Created on : Nov 23, 2024, 11:33:52 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Filtered Doctor Records</title>
    </head>
    <body>
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Doctor ID</th>
                    <th>Full Name</th>
                    <th>Specialization</th>
                    <th>Gender</th>
                    <th>Date of Birth</th>
                    <th>Consultation Rate</th>
                    <th>Mobile Number</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    String gender_filter = request.getParameter("gender_filter");
                    String[] specialization_filter = request.getParameterValues("specialization_filter[]");
                    String min_rate = request.getParameter("min_rate");
                    String max_rate = request.getParameter("max_rate");
                    d.filter_doctors(gender_filter, specialization_filter, min_rate, max_rate);
                    for (int i = 0; i < d.doctor_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= d.doctor_idList.get(i) %></td>
                        <td><%= d.last_nameList.get(i) %>, <%= d.first_nameList.get(i) %> <%= d.middle_initialList.get(i) %></td>
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
