<%-- 
    Document   : pharmacy_related_prescriptions
    Created on : Nov 23, 2024, 8:21:26 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pharmacy and Prescribed Drugs Record</title>
    </head>
    <body>
        <jsp:useBean id="ph" class="clinicmanagement.pharmacy" scope="session" />
        <jsp:useBean id="pd" class="clinicmanagement.prescribed_drugs" scope="session" />
        <jsp:useBean id="v" class="clinicmanagement.visits" scope="session" />
        <jsp:useBean id="p" class="clinicmanagement.patients" scope="session" />
        <table border="1">
            <thead>
                <tr>
                    <th>Drug ID</th>
                    <th>Generic Name</th>
                    <th>Brand Name</th>
                    <th>Price</th>
                    <th>Type</th>
                    <th>Patient Name</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    ph.get_related_prescriptions(pd, v, p);
                    for (int i = 0; i < ph.drug_idList.size(); i++) {
                %>
                    <tr>
                        <td><%= ph.drug_idList.get(i) %></td>
                        <td><%= ph.generic_nameList.get(i) %></td>
                        <td><%= ph.brand_nameList.get(i) %></td>
                        <td><%= ph.priceList.get(i) %></td>
                        <td><%= ph.typeList.get(i) %></td>
                        <td><%= p.last_nameList.get(i) %>, <%= p.first_nameList.get(i) %></td>
                        <td><%= pd.qty_drugsList.get(i) %></td>
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
