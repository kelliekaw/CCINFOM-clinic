<%-- 
    Document   : finance_report
    Created on : Nov 23, 2024, 7:59:02 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finance Report</title>
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Month and Year</th>
                    <th>Total Income</th>
                    <th>Total Shipments</th>
                    <th>Monthly Profit</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    s.finance_report();
                    for (int i = 0; i < s.month_yearList.size(); i++) {
                %>
                    <tr>
                        <td><%= s.month_yearList.get(i) %></td>
                        <td><%= s.total_incomeList.get(i) %></td>
                        <td><%= s.total_shipmentsList.get(i) %></td>
                        <td><%= s.monthly_profitList.get(i) %></td>
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
