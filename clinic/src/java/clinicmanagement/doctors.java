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
    public long mobile_number;
    public String email_address;
    public String[] specializations;
    
    // list of patients
    public ArrayList<Integer> doctor_idList = new ArrayList<>();
    public ArrayList<String> last_nameList = new ArrayList<>();
    public ArrayList<String> first_nameList = new ArrayList<>();
    public ArrayList<String> genderList = new ArrayList<>();
    public ArrayList<String> birthdateList = new ArrayList<>();
    public ArrayList<Float> consultation_rateList = new ArrayList<>();
    public ArrayList<Long> mobile_numberList = new ArrayList<>();
    public ArrayList<String> email_addressList = new ArrayList<>();
    public ArrayList<String> specializationList = new ArrayList<>();

    public doctors() {}
    
    public void set_values(String last_name, String first_name, String gender, String birthdate, float consultation_rate, long mobile_number, String email_address, String[] specializations) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.consultation_rate = consultation_rate;
        this.mobile_number = mobile_number;
        this.email_address = email_address;
        this.specializations = specializations;
    }
    
    public void clearLists() {
        doctor_idList.clear();
        last_nameList.clear();
        first_nameList.clear();
        genderList.clear();
        birthdateList.clear();
        consultation_rateList.clear();
        mobile_numberList.clear();
        email_addressList.clear();
        specializationList.clear();
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
//            PreparedStatement ps = conn.prepareStatement("SELECT speci_id FROM ref_specializations WHERE title = ?;");
//            ps.setString(1, specialization);
//            rs = ps.executeQuery();
//            
//            int speci_id = -1;
//            if (rs.next()) {
//                speci_id = rs.getInt("speci_id");
//            } else {
//                ps = conn.prepareStatement("INSERT INTO ref_specializations (title) VALUE (?);",
//                        Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1, specialization);
//                ps.executeUpdate();
//                rs = ps.getGeneratedKeys();
//                if (rs.next())
//                    speci_id = rs.getInt(1);
//            }
            
            // Save new doctor
            PreparedStatement ps = conn.prepareStatement("INSERT INTO doctors (last_name, first_name, gender, birthdate, consultation_rate, mobile_number, email_address) VALUE (?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, last_name);
            ps.setString(2, first_name);
            ps.setString(3, gender);
            ps.setString(4, birthdate);
            ps.setFloat(5, consultation_rate);
            ps.setLong(6, mobile_number);
            ps.setString(7, email_address);
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            if (rs.next())  
                doctor_id = rs.getInt(1);
            

            // save into doctor_speci
            int speci_id = -1;
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("INSERT INTO doctor_speci (doctor_id, speci_id) VALUES (?, ?);");
            for (String s: specializations) {
                PreparedStatement ps1 = conn.prepareStatement("SELECT speci_id FROM ref_specializations WHERE title = ?;");
                ps1.setString(1, s);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next())
                    speci_id = rs1.getInt(1);
                
                ps.setInt(1, doctor_id);
                ps.setInt(2, speci_id);
                ps.executeUpdate();
            }
           
            ps.close();
            conn.close();
            
            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean select_doctor() {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Display all doctors
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM doctors d "
                    + "LEFT JOIN doctor_speci ds ON d.doctor_id = ds.doctor_id "
                    + "LEFT JOIN ref_specializations s ON ds.speci_id = s.speci_id;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            
            while (rs.next()) {
                doctor_id = rs.getInt("doctor_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                consultation_rate = rs.getFloat("consultation_rate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                doctor_idList.add(doctor_id);
                last_nameList.add(last_name);
                first_nameList.add(first_name);
                genderList.add(gender);
                birthdateList.add(birthdate);
                consultation_rateList.add(consultation_rate);
                mobile_numberList.add(mobile_number);
                email_addressList.add(email_address);
                specializationList.add(rs.getString("title"));
            }
            
            ps.close();
            conn.close();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean remove_doctor(int doctorID) {
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM doctors WHERE doctor_id = ?;");
            ps.setInt(1, doctorID);
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
    
    public boolean get_related_visits(visits v, patients p, ailments a) {
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
            PreparedStatement ps = conn.prepareStatement("SELECT d.*, p.last_name AS `patient_lname`, p.first_name AS `patient_fname`, "
                    + "v.log_in, v.log_out, a.name " +
                    "FROM doctors d " +
                    "INNER JOIN visits v ON d.doctor_id = v.doctor_id " +
                    "INNER JOIN patients p ON v.patient_id = p.patient_id " +
                    "INNER JOIN ref_ailments a ON v.ailment_id = a.ailment_id;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            v.clearLists();
            p.clearLists();
            a.clearLists();
            
            while (rs.next()) {
                doctor_id = rs.getInt("doctor_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                consultation_rate = rs.getFloat("consultation_rate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                p.last_name = rs.getString("patient_lname");
                p.first_name = rs.getString("patient_fname");
                
                v.log_in = rs.getString("log_in");
                v.log_out = rs.getString("log_out");
                
                a.name = rs.getString("name");
                
                doctor_idList.add(doctor_id);
                last_nameList.add(last_name);
                first_nameList.add(first_name);
                genderList.add(gender);
                birthdateList.add(birthdate);
                consultation_rateList.add(consultation_rate);
                mobile_numberList.add(mobile_number);
                email_addressList.add(email_address);
                
                p.last_nameList.add(p.last_name);
                p.first_nameList.add(p.first_name);
                
                v.log_inList.add(v.log_in);
                v.log_outList.add(v.log_out);
                
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
