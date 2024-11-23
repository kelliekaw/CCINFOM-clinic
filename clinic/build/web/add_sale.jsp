<%-- 
    Document   : add_sale
    Created on : Nov 23, 2024, 10:18:33 AM
    Author     : kiwik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Sales Record</title>
        
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <jsp:useBean id="v" class="clinicmanagement.visits" scope="session" />
        <jsp:useBean id="p" class="clinicmanagement.patients" scope="session" />
        <jsp:useBean id="d" class="clinicmanagement.doctors" scope="session" />
        <jsp:useBean id="a" class="clinicmanagement.ailments" scope="session" />
        <form action="add_sale_processing.jsp" method="post">
            <h2>Add Sales Record</h2>
            
            Visit ID:
            <div class="custom-dropdown">
                <button class="dropdown-btn">Select Visit Record</button>
                <div class="dropdown-content">
                    <% 
                        v.select_visit(p, d, a);
                        for (int i = 0; i < v.visit_idList.size(); i++) {
                    %>
                        <div class="dropdown-item" data-value="<%= v.visit_idList.get(i) %>">
                            <strong><%= p.last_nameList.get(i) %>, <%= p.first_nameList.get(i) %> <%= p.middle_initialList.get(i) %></strong><br>
                            Log In: <%= v.log_inList.get(i) %><br>
                            Log Out: <%= v.log_outList.get(i) %><br>
                            Ailment: <%= a.nameList.get(i) %><br>
                            Doctor Assigned: <%= d.last_nameList.get(i) %>, <%= d.first_nameList.get(i) %> <%= d.middle_initialList.get(i) %>
                        </div>
                    <% } %>
                </div>
            </div>
            <br>
            
            Amount Paid: <input type="number" name="amt_paid" value="" step="0.01" min="0" required><br><br>
            
            <input type="hidden" name="visit_id" value="">
            
            <input type="submit" value="Submit">
        </form>
                
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
                    const visitID = item.getAttribute('data-value');  // Get the visit ID from data-value attribute

                    dropdownBtn.innerText = selectedRecord;  // Update button text with selected record
                    document.querySelector('input[name="visit_id"]').value = visitID;  // Set the hidden input value to patient ID

                });
            });
        </script>
    </body>
</html>
