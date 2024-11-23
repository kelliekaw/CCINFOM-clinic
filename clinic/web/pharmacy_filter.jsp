<%-- 
    Document   : pharmacy_filter
    Created on : Nov 23, 2024, 12:21:33 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Filtered Pharmacy Records</title>
    </head>
    <body>
        <jsp:useBean id="ph" class="clinicmanagement.pharmacy" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Drug ID</th>
                    <th>Generic Name</th>
                    <th>Brand Name</th>
                    <th>Price</th>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    String[] type_filter = request.getParameterValues("type_filter[]");
                    String min_price = request.getParameter("min_price");
                    String max_price = request.getParameter("max_price");
                    ph.filter_pharmacy(type_filter, min_price, max_price);
                    for (int i = 0; i < ph.drug_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= ph.drug_idList.get(i) %></td>
                        <td><%= ph.generic_nameList.get(i) %></td>
                        <td><%= ph.brand_nameList.get(i) %></td>
                        <td><%= ph.priceList.get(i) %></td>
                        <td><%= ph.typeList.get(i) %></td>
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
