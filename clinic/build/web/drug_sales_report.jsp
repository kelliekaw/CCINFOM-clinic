<%-- 
    Document   : drug_sales_report
    Created on : Nov 23, 2024, 8:03:59 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Drug Sales Report</title>
    </head>
    <body>
        <jsp:useBean id="ds" class="clinicmanagement.drugs_sold" scope="session" />
        <jsp:useBean id="p" class="clinicmanagement.pharmacy" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Year</th>
                    <th>Month</th>
                    <th>Drug ID</th>
                    <th>Generic Name</th>
                    <th>Number of Drugs Sold</th>
                    <th>Total Revenue</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    ds.drug_sales_report(p);
                    for (int i = 0; i < ds.year_soldList.size(); i++) {
                %>
                    <tr>
                        <td><%= ds.year_soldList.get(i) %></td>
                        <td><%= ds.month_soldList.get(i) %></td>
                        <td><%= ds.drug_idList.get(i) %></td>
                        <td><%= p.generic_nameList.get(i) %></td>
                        <td><%= ds.num_of_drugs_soldList.get(i) %></td>
                        <td><%= ds.total_revenueList.get(i) %></td>
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
