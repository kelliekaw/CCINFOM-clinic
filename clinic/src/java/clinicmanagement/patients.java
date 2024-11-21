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
//    public int patient_id;
    public String last_name;
    public String first_name;
    public String gender;
    public String birthdate;
    public int mobile_number;
    public String email_address;
    
    // list of patients
//    public ArrayList<Integer> patient_idList = new ArrayList<>();
    public ArrayList<String> last_nameList = new ArrayList<>();
    public ArrayList<String> first_nameList = new ArrayList<>();
    public ArrayList<String> genderList = new ArrayList<>();
    public ArrayList<String> birthdateList = new ArrayList<>();
    public ArrayList<Integer> mobile_numberList = new ArrayList<>();
    public ArrayList<String> email_adddressList = new ArrayList<>();
    
    public patients() {}
    
    public void set_values(String last_name, String first_name, String gender, String birthdate, int mobile_number, String email_address) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.mobile_number = mobile_number;
        this.email_address = email_address;
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
            ps.setInt(5, mobile_number);
            ps.setString(6, email_address);
            ps.executeUpdate();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
