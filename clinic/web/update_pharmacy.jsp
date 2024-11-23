<%-- 
    Document   : update_pharmacy
    Created on : Nov 23, 2024, 3:33:55 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Pharmacy</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <jsp:useBean id="ph" class="clinicmanagement.pharmacy" scope="session" />
        <form action="update_pharmacy_form.jsp" method="post">
            <h2>Select Pharmacy Record to Update</h2>
            <div class="custom-dropdown">
                <button class="dropdown-btn">Select Pharmacy Record</button>
                <div class="dropdown-content">
                    <% 
                        ph.select_drug();
                        for (int i = 0; i < ph.drug_idList.size(); i++) {
                    %>
                        <div class="dropdown-item" data-value="<%= ph.drug_idList.get(i) %>">
                            <strong><%= ph.generic_nameList.get(i) %></strong><br>
                            Brand: <%= ph.brand_nameList.get(i) %><br>
                            Price: <%= ph.priceList.get(i) %><br>
                        </div>
                    <% } %>
                </div>
            </div><br>
            <input type="hidden" name="drug_id" value="">
            
            <a href="index.html">
                <input type="button" value="Back to Menu">
            </a>
        </form>
        
        <!-- JavaScript for dropdown functionality -->
        <script>
            const dropdownBtn = document.querySelector('.dropdown-btn');
            const dropdownContent = document.querySelector('.dropdown-content');
            const dropdownItems = document.querySelectorAll('.dropdown-item');

            // Toggle dropdown visibility on button click
            dropdownBtn.addEventListener('click', function () {
                dropdownContent.classList.toggle('show');
            });

            // Handle item click to select a pharmacy record
            dropdownItems.forEach(item => {
                item.addEventListener('click', function () {
                    const selectedRecord = item.querySelector('strong').innerText;  // Get the record's display text
                    const drugId = item.getAttribute('data-value');  // Get the drug ID from data-value attribute

                    dropdownBtn.innerText = selectedRecord;  // Update button text with selected record
                    document.querySelector('input[name="drug_id"]').value = drugId;  // Set the hidden input value to drug ID

                });
            });
        </script>
    </body>
</html>
