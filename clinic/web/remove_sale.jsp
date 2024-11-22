<%-- 
    Document   : remove_sale
    Created on : Nov 22, 2024, 11:13:57 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, clinicmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Sales Record</title>
        
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <jsp:useBean id="s" class="clinicmanagement.sales" scope="session" />
        <form action="remove_sale_processing.jsp">
            <h2>Select Sales Record to Remove</h2>
            <div class="custom-dropdown">
                <button class="dropdown-btn">Select Sales Record</button>
                <div class="dropdown-content">
                    <% 
                        s.select_sale();
                        for (int i = 0; i < s.sale_idList.size(); i++) {
                    %>
                        <div class="dropdown-item" data-value="<%= s.sale_idList.get(i) %>">
                            <strong>Visit ID: <%= s.visit_idList.get(i) %></strong><br>
                            Mode of Payment: <%= s.mode_paymentList %><br>
                            Reference Number: <%= s.ref_numList.get(i) %><br>
                            Amount Paid: <%= s.amt_paidList.get(i) %><br>
                        </div>
                    <% } %>
                </div>
            </div><br>
            <input type="hidden" name="sale_id" value="">
            
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

            // Handle item click to select a patient record
            dropdownItems.forEach(item => {
                item.addEventListener('click', function () {
                    const selectedRecord = item.querySelector('strong').innerText;  // Get the record's display text
                    const saleId = item.getAttribute('data-value');  // Get the patient ID from data-value attribute

                    dropdownBtn.innerText = selectedRecord;  // Update button text with selected record
                    document.querySelector('input[name="sale_id"]').value = saleId;  // Set the hidden input value to patient ID

                });
            });
        </script>
    </body>
</html>
