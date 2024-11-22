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
    public String gender;
    public String birthdate;
    public long mobile_number;
    public String email_address;
    
    // list of patients
    public ArrayList<Integer> patient_idList = new ArrayList<>();
    public ArrayList<String> last_nameList = new ArrayList<>();
    public ArrayList<String> first_nameList = new ArrayList<>();
    public ArrayList<String> genderList = new ArrayList<>();
    public ArrayList<String> birthdateList = new ArrayList<>();
    public ArrayList<Long> mobile_numberList = new ArrayList<>();
    public ArrayList<String> email_addressList = new ArrayList<>();
    
    public patients() {}
    
    public void set_values(String last_name, String first_name, String gender, String birthdate, long mobile_number, String email_address) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.mobile_number = mobile_number;
        this.email_address = email_address;
    }
    
    public void clearLists() {
        patient_idList.clear();
        last_nameList.clear();
        first_nameList.clear();
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO patients (last_name, first_name, gender, birthdate, mobile_number, email_address) VALUE (?, ?, ?, ?, ?, ?);");
            ps.setString(1, last_name);
            ps.setString(2, first_name);
            ps.setString(3, gender);
            ps.setString(4, birthdate);
            ps.setLong(5, mobile_number);
            ps.setString(6, email_address);
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
            
            // Display all patients
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM patients;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            
            while (rs.next()) {
                patient_id = rs.getInt("patient_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                patient_idList.add(patient_id);
                last_nameList.add(last_name);
                first_nameList.add(first_name);
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
            
            // Delete the patient
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
            
            // Display all patients and their visits
            PreparedStatement ps = conn.prepareStatement("SELECT p.*, v.log_in, v.log_out, "
                    + "d.last_name AS `doc_last_name`, d.first_name AS `doc_first_name`, a.name " +
                    "FROM patients p " +
                    "LEFT JOIN visits v ON p.patient_id = v.patient_id " +
                    "LEFT JOIN doctors d ON v.doctor_id = d.doctor_id " +
                    "LEFT JOIN ref_ailments a ON v.ailment_id = a.ailment_id;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            v.clearLists();
            d.clearLists();
            a.clearLists();
            
            while (rs.next()) {
                patient_id = rs.getInt("patient_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                v.log_in = rs.getString("log_in");
                v.log_out = rs.getString("log_out");
                
                d.last_name = rs.getString("doc_last_name");
                d.first_name = rs.getString("doc_first_name");
                a.name = rs.getString("name");
                
                patient_idList.add(patient_id);
                last_nameList.add(last_name);
                first_nameList.add(first_name);
                genderList.add(gender);
                birthdateList.add(birthdate);
                mobile_numberList.add(mobile_number);
                email_addressList.add(email_address);
                
                v.log_inList.add(v.log_in);
                v.log_outList.add(v.log_out);
                
                d.last_nameList.add(d.last_name);
                d.first_nameList.add(d.first_name);
                
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
    
    
}
