<%-- 
    Document   : sales_filter
    Created on : Nov 23, 2024, 1:39:13 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Filtered Sales Records</title>
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Sale ID</th>
                    <th>Visit ID</th>
                    <th>Amount Paid</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    String min_amt = request.getParameter("min_amt");
                    String max_amt = request.getParameter("max_amt");
                    s.filter_sales(min_amt, max_amt);
                    for (int i = 0; i < s.sale_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= s.sale_idList.get(i) %></td>
                        <td><%= s.visit_idList.get(i) %></td>
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
