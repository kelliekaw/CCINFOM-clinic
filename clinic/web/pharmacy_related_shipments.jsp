<%-- 
    Document   : pharmacy_related_shipments
    Created on : Nov 23, 2024, 3:36:24 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pharmacy and Shipment Records</title>
    </head>
    <body>
        <jsp:useBean id="ph" class="clinicmanagement.pharmacy" scope="session" />
        <jsp:useBean id="sd" class="clinicmanagement.shipment_drug" scope="session" />
        <jsp:useBean id="s" class="clinicmanagement.shipments" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Drug ID</th>
                    <th>Generic Name</th>
                    <th>Brand Name</th>
                    <th>Price</th>
                    <th>Type</th>
                    <th>Shipment Date</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    ph.get_related_shipments(sd, s);
                    for (int i = 0; i < ph.drug_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= ph.drug_idList.get(i) %></td>
                        <td><%= ph.generic_nameList.get(i) %></td>
                        <td><%= ph.brand_nameList.get(i) %></td>
                        <td><%= ph.priceList.get(i) %></td>
                        <td><%= ph.typeList.get(i) %></td>
                        <td><%= s.dateList.get(i) %></td>
                        <td><%= sd.qtyList.get(i) %></td>
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
