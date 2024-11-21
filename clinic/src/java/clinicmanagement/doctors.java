/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicmanagement;

import java.util.*;
import java.sql.*;

/**
 *
 * @author kiwik
 */
public class doctors {
    // fields
    public int doctor_id;
    public String last_name;
    public String first_name;
    public String gender;
    public String birthdate;
    public float consultation_rate;
    public int mobile_number;
    public String email_address;
    public String specialization;
    
    // list of patients
    public ArrayList<Integer> doctor_idList = new ArrayList<>();
    public ArrayList<String> last_nameList = new ArrayList<>();
    public ArrayList<String> first_nameList = new ArrayList<>();
    public ArrayList<String> genderList = new ArrayList<>();
    public ArrayList<String> birthdateList = new ArrayList<>();
    public ArrayList<Float> consultation_rateList = new ArrayList<>();
    public ArrayList<Integer> mobile_numberList = new ArrayList<>();
    public ArrayList<String> email_adddressList = new ArrayList<>();
    public ArrayList<String> specializationList = new ArrayList<>();

    public doctors() {}
    
    public void set_values(String last_name, String first_name, String gender, String birthdate, float consultation_rate, int mobile_number, String email_address, String specialization) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.consultation_rate = consultation_rate;
        this.mobile_number = mobile_number;
        this.email_address = email_address;
        this.specialization = specialization;
    }
    
    public boolean add_doctor() {
        ResultSet rs;
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101"; // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Prepare SQL Statements
            
            // Check if specialization exists
            
            PreparedStatement ps = conn.prepareStatement("SELECT speci_id FROM ref_specializations WHERE title = ?;");
            ps.setString(1, specialization);
            rs = ps.executeQuery();
            
            int speci_id = -1;
            if (rs.next()) {
                speci_id = rs.getInt("speci_id");
            } else {
                ps = conn.prepareStatement("INSERT INTO ref_specializations (title) VALUE (?);",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, specialization);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next())
                    speci_id = rs.getInt(1);
            }
            
            // Save new doctor
            ps = conn.prepareStatement("INSERT INTO doctors (last_name, first_name, gender, birthdate, consultation_rate, mobile_number, email_address) VALUE (?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, last_name);
            ps.setString(2, first_name);
            ps.setString(3, gender);
            ps.setString(4, birthdate);
            ps.setFloat(5, consultation_rate);
            ps.setInt(6, mobile_number);
            ps.setString(7, email_address);
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            if (rs.next())
                doctor_id = rs.getInt(1);

            // save into doctor_speci
            ps = conn.prepareStatement("INSERT INTO doctor_speci (doctor_id, speci_id) VALUES (?, ?);");
            ps.setInt(1, doctor_id);
            ps.setInt(2, speci_id);
            ps.executeUpdate();
            
            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
