<%-- 
    Document   : sales_related_drugs
    Created on : Nov 23, 2024, 8:54:39 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales and Drugs Sold</title>
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <jsp:useBean id="ds" class="clinicmanagement.drugs_sold" scope="session" />
        <jsp:useBean id="in" class="clinicmanagement.inventory" scope="session" />
        <jsp:useBean id="sd" class="clinicmanagement.shipment_drug" scope="session" />
        <jsp:useBean id="ph" class="clinicmanagement.pharmacy" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Sale ID</th>
                    <th>Visit ID</th>
                    <th>Amount Paid</th>
                    <th>Generic Name</th>
                    <th>Brand Name</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    s.get_related_drugs(ds, in, sd, ph);
                    for (int i = 0; i < s.sale_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= s.sale_idList.get(i) %></td>
                        <td><%= s.visit_idList.get(i) %></td>
                        <td><%= s.amt_paidList.get(i) %></td>
                        <td><%= ph.generic_nameList.get(i) %></td>
                        <td><%= ph.brand_nameList.get(i) %></td>
                        <td><%= ds.qtyList.get(i) %></td>
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
