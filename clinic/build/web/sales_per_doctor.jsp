<%-- 
    Document   : sales_per_doctor
    Created on : Nov 23, 2024, 8:23:11 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Per Doctor</title>
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <jsp:useBean id="v" class="clinicmanagement.visits" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Doctor Name</th>
                    <th>Total Number of Sales</th>
                    <th>Total Sales Amount</th>
                    <th>Average Sale per Visit</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    s.sales_per_doctor(d, v);
                    for (int i = 0; i < d.last_nameList.size(); i++) {
                %>
                    <tr>
                        <td><%= d.last_nameList.get(i) %>, <%= d.first_nameList.get(i) %></td>
                        <td><%= s.total_salesList.get(i) %></td>
                        <td><%= s.total_sales_amtList.get(i) %></td>
                        <td><%= s.avg_sale_per_visitList.get(i) %></td>
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
