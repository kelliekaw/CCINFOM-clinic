<%-- 
    Document   : remove_doctor
    Created on : Nov 22, 2024, 9:48:58 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, clinicmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Doctor Record</title>
        
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <form action="remove_doctor_processing.jsp">
            <h2>Select Doctor Record to Remove</h2>
            <div class="custom-dropdown">
                <button class="dropdown-btn">Select Doctor Record</button>
                <div class="dropdown-content">
                    <% 
                        d.select_doctor();
                        for (int i = 0; i < d.doctor_idList.size(); i++) {
                    %>
                        <div class="dropdown-item" data-value="<%= d.doctor_idList.get(i) %>">
                            <strong><%= d.last_nameList.get(i) %>, <%= d.first_nameList.get(i) %></strong><br>
                            Specialization: <%= d.specializationList.get(i) %><br>
                            Gender: <%= d.genderList.get(i) %><br>
                            DOB: <%= d.birthdateList.get(i) %><br>
                            Rate: <%= d.consultation_rateList.get(i) %><br>
                            Mobile: <%= d.mobile_numberList.get(i) %><br>
                            Email: <%= d.email_addressList.get(i) %><br>
                        </div>
                    <% } %>
                </div>
            </div><br>
            <input type="hidden" name="doctor_id" value="">
            
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
                    const doctorId = item.getAttribute('data-value');  // Get the patient ID from data-value attribute

                    dropdownBtn.innerText = selectedRecord;  // Update button text with selected record
                    document.querySelector('input[name="doctor_id"]').value = doctorId;  // Set the hidden input value to patient ID

                });
            });
        </script>
    </body>
</html>
