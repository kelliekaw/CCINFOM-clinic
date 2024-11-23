<%-- 
    Document   : sales_related_visits
    Created on : Nov 23, 2024, 9:07:36 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales and Visits</title>
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <jsp:useBean id="v" class="clinicmanagement.visits" scope="session" />
        <jsp:useBean id="p" class="clinicmanagement.patients" scope="session" />
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <jsp:useBean id="a" class="clinicmanagement.ailments" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Sale ID</th>
                    <th>Visit ID</th>
                    <th>Amount Paid</th>
                    <th>Patient Name</th>
                    <th>Ailment</th>
                    <th>Doctor Assigned</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    s.get_related_visits(v, p, d, a);
                    for (int i = 0; i < s.sale_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= s.sale_idList.get(i) %></td>
                        <td><%= s.visit_idList.get(i) %></td>
                        <td><%= s.amt_paidList.get(i) %></td>
                        <td><%= p.last_nameList.get(i) %>, <%= p.first_nameList.get(i) %> <%= p.middle_initialList.get(i) %></td>
                        <td><%= a.nameList.get(i) %></td>
                        <td><%= d.last_nameList.get(i) %>, <%= d.first_nameList.get(i) %> <%= d.middle_initialList.get(i) %></td>
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
