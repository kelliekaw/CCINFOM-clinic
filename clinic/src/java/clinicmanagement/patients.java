/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kiwik
 */
package clinicmanagement;

import java.util.*;
import java.sql.*;

public class patients {
    // fields
    public int patient_id;
    public String last_name;
    public String first_name;
    public String middle_initial;
    public String gender;
    public String birthdate;
    public long mobile_number;
    public String email_address;
    
    // list of patients
    public ArrayList<Integer> patient_idList = new ArrayList<>();
    public ArrayList<String> last_nameList = new ArrayList<>();
    public ArrayList<String> first_nameList = new ArrayList<>();
    public ArrayList<String> middle_initialList = new ArrayList<>();
    public ArrayList<String> genderList = new ArrayList<>();
    public ArrayList<String> birthdateList = new ArrayList<>();
    public ArrayList<Long> mobile_numberList = new ArrayList<>();
    public ArrayList<String> email_addressList = new ArrayList<>();
    
    public patients() {}
    
    public void set_values(String last_name, String first_name, String middle_initial, String gender, String birthdate, long mobile_number, String email_address) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.middle_initial = middle_initial;
        this.gender = gender;
        this.birthdate = birthdate;
        this.mobile_number = mobile_number;
        this.email_address = email_address;
    }
    
    public void clearLists() {
        patient_idList.clear();
        last_nameList.clear();
        first_nameList.clear();
        middle_initialList.clear();
        genderList.clear();
        birthdateList.clear();
        mobile_numberList.clear();
        email_addressList.clear();
    }
    
    public boolean add_patient() {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Prepare SQL Statement
            // Save new patient
            PreparedStatement ps = conn.prepareStatement("INSERT INTO patients (last_name, first_name, middle_initial, gender, birthdate, mobile_number, email_address) VALUE (?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, last_name);
            ps.setString(2, first_name);
            ps.setString(3, middle_initial);
            ps.setString(4, gender);
            ps.setString(5, birthdate);
            ps.setLong(6, mobile_number);
            ps.setString(7, email_address);
            ps.executeUpdate();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean select_patient() {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Get all patients
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM patients;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            
            while (rs.next()) {
                patient_id = rs.getInt("patient_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                middle_initial = rs.getString("middle_initial");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                patient_idList.add(patient_id);
                last_nameList.add(last_name);
                first_nameList.add(first_name);
                middle_initialList.add(middle_initial);
                genderList.add(gender);
                birthdateList.add(birthdate);
                mobile_numberList.add(mobile_number);
                email_addressList.add(email_address);
            }
            
            ps.close();
            conn.close();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean remove_patient(int patientID) {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Prepare the SQL statement to delete the patient
            PreparedStatement ps = conn.prepareStatement("DELETE FROM patients WHERE patient_id = ?;");
            ps.setInt(1, patientID);
            int check = ps.executeUpdate();
            
            ps.close();
            conn.close();
            
            // Return true if a row was deleted
            return check > 0;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean get_related(visits v, doctors d, ailments a) {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Prepare SELECT statement
            PreparedStatement ps = conn.prepareStatement("SELECT p.*, v.log_in, "
                    + "d.last_name AS `doc_last_name`, d.first_name AS `doc_first_name`, d.middle_initial AS `doc_mid_initial`, a.name " +
                    "FROM patients p " +
                    "INNER JOIN visits v ON p.patient_id = v.patient_id " +
                    "INNER JOIN doctors d ON v.doctor_id = d.doctor_id " +
                    "INNER JOIN ref_ailments a ON v.ailment_id = a.ailment_id;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            v.clearLists();
            d.clearLists();
            a.clearLists();
            
            while (rs.next()) {
                patient_id = rs.getInt("patient_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                middle_initial = rs.getString("middle_initial");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                v.log_in = rs.getString("log_in");
                
                d.last_name = rs.getString("doc_last_name");
                d.first_name = rs.getString("doc_first_name");
                d.middle_initial = rs.getString("doc_mid_initial");
                
                a.name = rs.getString("name");
                
                patient_idList.add(patient_id);
                last_nameList.add(last_name);
                first_nameList.add(first_name);
                middle_initialList.add(middle_initial);
                genderList.add(gender);
                birthdateList.add(birthdate);
                mobile_numberList.add(mobile_number);
                email_addressList.add(email_address);
                
                v.log_inList.add(v.log_in);
                
                d.last_nameList.add(d.last_name);
                d.first_nameList.add(d.first_name);
                d.middle_initialList.add(d.middle_initial);
                
                a.nameList.add(a.name);
            }
            
            ps.close();
            conn.close();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean filter_patients(String gender_filter) {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            
            String query = "SELECT * FROM patients WHERE 1=1";
            
            if (gender_filter != null && !gender_filter.isEmpty()) {
                query += " AND gender = ?";
            }
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            if (gender_filter != null && !gender_filter.isEmpty()) {
                ps.setString(1, gender_filter);
            }
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            
            while (rs.next()) {
                patient_id = rs.getInt("patient_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                middle_initial = rs.getString("middle_initial");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                patient_idList.add(patient_id);
                last_nameList.add(last_name);
                first_nameList.add(first_name);
                middle_initialList.add(middle_initial);
                genderList.add(gender);
                birthdateList.add(birthdate);
                mobile_numberList.add(mobile_number);
                email_addressList.add(email_address);
            }
            
            ps.close();
            conn.close();
            
            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean get_patient_record(int patientID) {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Prepare SELECT statement
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM patients WHERE patient_id = ?;");
            ps.setInt(1, patientID);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                patient_id = rs.getInt("patient_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                middle_initial = rs.getString("middle_initial");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
            }

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update_patient(int patientID, String lastName, String firstName, String middleInitial, long mobileNumber, String emailAddress) {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Prepare SQL Statement
            // Update patient
            PreparedStatement ps = conn.prepareStatement("UPDATE patients SET last_name = ?, first_name = ?, middle_initial = ?, "
                    + "mobile_number = ?, email_address = ? WHERE patient_id = ?;");
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, middleInitial);
            ps.setLong(4, mobileNumber);
            ps.setString(5, emailAddress);
            ps.setInt(6, patientID);
            
            return ps.executeUpdate() > 0;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
