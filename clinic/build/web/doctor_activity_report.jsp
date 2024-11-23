<%-- 
    Document   : doctor_activity_report
    Created on : Nov 23, 2024, 7:20:26 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Activity Report</title>
    </head>
    <body>
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <jsp:useBean id="v" class="clinicmanagement.visits" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Doctor ID</th>
                    <th>Full Name</th>
                    <th>Visit Year</th>
                    <th>Visit Month</th>
                    <th>Number of Visits</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    d.activity_report(v);
                    for (int i = 0; i < v.doctor_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= v.doctor_idList.get(i) %></td>
                        <td><%= d.last_nameList.get(i) %>, <%= d.first_nameList.get(i) %> <%= d.middle_initialList.get(i) %></td>
                        <td><%= v.visit_yearList.get(i) %></td>
                        <td><%= v.visit_monthList.get(i) %></td>
                        <td><%= v.num_visitsList.get(i) %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <br>
        <a href="index.html">
            <input type="button" value="Back">
        </a>
    </body>
</html>
