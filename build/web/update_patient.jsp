<%-- 
    Document   : update_patient
    Created on : Nov 23, 2024, 2:20:03 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Patient Record</title>
        
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <jsp:useBean id="p" class="clinicmanagement.patients" scope="session" />
        <form action="update_patient_form.jsp" method="post">
            <h2>Select Patient Record to Update</h2>
            <div class="custom-dropdown">
                <button class="dropdown-btn">Select Patient Record</button>
                <div class="dropdown-content">
                    <% 
                        p.select_patient();
                        for (int i = 0; i < p.patient_idList.size(); i++) {
                    %>
                        <div class="dropdown-item" data-value="<%= p.patient_idList.get(i) %>">
                            <strong><%= p.last_nameList.get(i) %>, <%= p.first_nameList.get(i) %> <%= p.middle_initialList.get(i) %></strong><br>
                            Gender: <%= p.genderList.get(i) %><br>
                            DOB: <%= p.birthdateList.get(i) %><br>
                            Mobile: <%= p.mobile_numberList.get(i) %><br>
                            Email: <%= p.email_addressList.get(i) %>
                        </div>
                    <% } %>
                </div>
            </div><br>
            <!<!-- selected patient id -->
            <input type="hidden" name="patient_id" value="">
            
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
                    const patientId = item.getAttribute('data-value');  // Get the patient ID from data-value attribute

                    dropdownBtn.innerText = selectedRecord;  // Update button text with selected record
                    document.querySelector('input[name="patient_id"]').value = patientId;  // Set the hidden input value to patient ID

                });
            });
        </script>
    </body>
</html>
