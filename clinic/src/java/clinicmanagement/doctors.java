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
    public String middle_initial;
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
    public ArrayList<String> middle_initialList = new ArrayList<>();
    public ArrayList<String> genderList = new ArrayList<>();
    public ArrayList<String> birthdateList = new ArrayList<>();
    public ArrayList<Float> consultation_rateList = new ArrayList<>();
    public ArrayList<Long> mobile_numberList = new ArrayList<>();
    public ArrayList<String> email_addressList = new ArrayList<>();
    public ArrayList<String> specializationList = new ArrayList<>();

    public doctors() {}
    
    public void set_values(String last_name, String first_name, String middle_initial, String gender, String birthdate, float consultation_rate, long mobile_number, String email_address, String[] specializations) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.middle_initial = middle_initial;
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
        middle_initialList.clear();
        genderList.clear();
        birthdateList.clear();
        consultation_rateList.clear();
        mobile_numberList.clear();
        email_addressList.clear();
        specializationList.clear();
    }
    
    public boolean add_doctor() {        
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
            // Save new doctor
            PreparedStatement ps = conn.prepareStatement("INSERT INTO doctors (last_name, first_name, middle_initial, gender, birthdate, consultation_rate, mobile_number, email_address) VALUE (?, ?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, last_name);
            ps.setString(2, first_name);
            ps.setString(3, middle_initial);
            ps.setString(4, gender);
            ps.setString(5, birthdate);
            ps.setFloat(6, consultation_rate);
            ps.setLong(7, mobile_number);
            ps.setString(8, email_address);
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())  
                doctor_id = rs.getInt(1);

            // Save into doctor_speci
            int speci_id = -1;
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
            
            // Get all doctors
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM doctors d;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            
            while (rs.next()) {
                doctor_id = rs.getInt("doctor_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                middle_initial = rs.getString("middle_initial");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                consultation_rate = rs.getFloat("consultation_rate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                doctor_idList.add(doctor_id);
                last_nameList.add(last_name);
                first_nameList.add(first_name);
                middle_initialList.add(middle_initial);
                genderList.add(gender);
                birthdateList.add(birthdate);
                consultation_rateList.add(consultation_rate);
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
            
            // Prepare the statement to delete the doctor record
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
    
    public boolean get_related_speci() {
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM doctors d "
                    + "LEFT JOIN doctor_speci ds ON d.doctor_id = ds.doctor_id "
                    + "LEFT JOIN ref_specializations s ON ds.speci_id = s.speci_id;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            
            while (rs.next()) {
                doctor_id = rs.getInt("doctor_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                middle_initial = rs.getString("middle_initial");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                consultation_rate = rs.getFloat("consultation_rate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                if (doctor_idList.contains(doctor_id)) { // if the doctor is already included (due to specializations)
                    String updatedSpecialization = specializationList.get(doctor_idList.indexOf(doctor_id)).concat(", " + rs.getString("title"));
                    specializationList.set(doctor_idList.indexOf(doctor_id), updatedSpecialization);
                }
                else {
                    doctor_idList.add(doctor_id);
                    last_nameList.add(last_name);
                    first_nameList.add(first_name);
                    middle_initialList.add(middle_initial);
                    genderList.add(gender);
                    birthdateList.add(birthdate);
                    consultation_rateList.add(consultation_rate);
                    mobile_numberList.add(mobile_number);
                    email_addressList.add(email_address);
                    specializationList.add(rs.getString("title"));
                }
            }
            
            ps.close();
            conn.close();

            return true;
            
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
            
            // Prepare SELECT Statement
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
                middle_initial = rs.getString("middle_initial");
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
                middle_initialList.add(middle_initial);
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
    
    public boolean filter_doctors(String gender_filter, String[] specialization_filter, String min_rate, String max_rate) {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            String query = "SELECT * FROM doctors d " +
                    "LEFT JOIN doctor_speci ds ON d.doctor_id = ds.doctor_id " + 
                    "LEFT JOIN ref_specializations s ON ds.speci_id = s.speci_id " +
                    "WHERE 1=1";
            
            if (gender_filter != null && !gender_filter.isEmpty()) {
                query += " AND gender = ?";
            }
            if (specialization_filter != null) {
                query += " AND (s.title = ?";
                for (int x = 1; x < specialization_filter.length; x++) {
                    query += " OR s.title = ?";
                }
                query += ")";
            }
            if (min_rate != null && !min_rate.isEmpty()) {
                query += " AND consultation_rate >= ?";
            }
            if (max_rate != null && !max_rate.isEmpty()) {
                query += " AND consultation_rate <= ?";
            }
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            int index = 1;
            if (gender_filter != null && !gender_filter.isEmpty()) {
                ps.setString(index++, gender_filter);
            }
            if (specialization_filter != null) {
                for (String s: specialization_filter) {
                    ps.setString(index++, s);
                }
            }
            if (min_rate != null && !min_rate.isEmpty()) {
                ps.setFloat(index++, Float.parseFloat(min_rate));
            }
            if (max_rate != null && !max_rate.isEmpty()) {
                ps.setFloat(index++, Float.parseFloat(max_rate));
            }
            
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            
            while (rs.next()) {
                doctor_id = rs.getInt("doctor_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                middle_initial = rs.getString("middle_initial");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                consultation_rate = rs.getFloat("consultation_rate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
                
                doctor_idList.add(doctor_id);
                last_nameList.add(last_name);
                first_nameList.add(first_name);
                middle_initialList.add(middle_initial);
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
    
    public boolean get_doctor_record(int doctorID) {
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM doctors WHERE doctor_id = ?;");
            ps.setInt(1, doctorID);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                doctor_id = rs.getInt("doctor_id");
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                middle_initial = rs.getString("middle_initial");
                gender = rs.getString("gender");
                birthdate = rs.getString("birthdate");
                consultation_rate = rs.getFloat("consultation_rate");
                mobile_number = rs.getLong("mobile_number");
                email_address = rs.getString("email_address");
            }

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update_doctor(int doctorID, String lastName, String firstName, String middleInitial, long mobileNumber, String emailAddress) {
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
            // Update doctor
            PreparedStatement ps = conn.prepareStatement("UPDATE doctors SET last_name = ?, first_name = ?, middle_initial = ?, "
                    + "mobile_number = ?, email_address = ? WHERE doctor_id = ?;");
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, middleInitial);
            ps.setLong(4, mobileNumber);
            ps.setString(5, emailAddress);
            ps.setInt(6, doctorID);
            
            return ps.executeUpdate() > 0;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
