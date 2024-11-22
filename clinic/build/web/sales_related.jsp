<%-- 
    Document   : sales_related
    Created on : Nov 23, 2024, 1:17:50 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Records with Related Records</title>
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Sale ID</th>
                    <th>Visit ID</th>
                    <th>Mode of Payment</th>
                    <th>Reference Number</th>
                    <th>Amount Paid</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    s.select_sale();
                    for (int i = 0; i < s.sale_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= s.sale_idList.get(i) %></td>
                        <td><%= s.visit_idList.get(i) %></td>
                        <td><%= s.mode_paymentList.get(i) %></td>
                        <td><%= s.ref_numList.get(i) %></td>
                        <td><%= s.amt_paidList.get(i) %></td>
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