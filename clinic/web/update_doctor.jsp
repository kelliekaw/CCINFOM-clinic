<%-- 
    Document   : update_doctor
    Created on : Nov 23, 2024, 3:02:07 PM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Doctor Record</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <form action="update_doctor_form.jsp" method="post">
            <h2>Select Doctor Record to Update</h2>
            <div class="custom-dropdown">
                <button class="dropdown-btn">Select Doctor Record</button>
                <div class="dropdown-content">
                    <% 
                        d.get_related_speci();
                        for (int i = 0; i < d.doctor_idList.size(); i++) {
                    %>
                        <div class="dropdown-item" data-value="<%= d.doctor_idList.get(i) %>">
                            <strong><%= d.last_nameList.get(i) %>, <%= d.first_nameList.get(i) %> <%= d.middle_initialList.get(i) %></strong><br>
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

            // Handle item click to select a doctor record
            dropdownItems.forEach(item => {
                item.addEventListener('click', function () {
                    const selectedRecord = item.querySelector('strong').innerText;  // Get the record's display text
                    const doctorId = item.getAttribute('data-value');  // Get the doctor ID from data-value attribute

                    dropdownBtn.innerText = selectedRecord;  // Update button text with selected record
                    document.querySelector('input[name="doctor_id"]').value = doctorId;  // Set the hidden input value to doctor ID

                });
            });
        </script>
    </body>
</html>
