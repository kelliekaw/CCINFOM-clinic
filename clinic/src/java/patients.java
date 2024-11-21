/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kiwik
 */
import java.util.*;
import java.sql.*;

public class patients {
    
    // fields
    private int patient_id;
    private String last_name;
    private String first_name;
    private char gender;
    private String birthdate;
    private int mobile_number;
    private String email_address;
    
    // list of patients
    private ArrayList<Integer> patient_idList = new ArrayList<>();
    private ArrayList<String> last_nameList = new ArrayList<>();
    private ArrayList<String> first_nameList = new ArrayList<>();
    private ArrayList<Character> genderList = new ArrayList<>();
    private ArrayList<String> birthdateList = new ArrayList<>();
    private ArrayList<Integer> mobile_numberList = new ArrayList<>();
    private ArrayList<String> email_adddressList = new ArrayList<>();
    
    public patients(int patientID, String lastName, String firstName, char gender, int mobileNumber, String email) {
        this.patient_id = patientID;
        this.last_name = lastName;
        this.first_name = firstName;
    }
    
    public boolean admit_patient() {
        try {
            // Connect to database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "password");
            System.out.println("Connection Successful");
            
            // Prepare SQL statement
            // Get next patient id
            PreparedStatement pstnt = conn.prepareStatement("SELECT MAX(patient_id) + 1 FROM patients");
            ResultSet rSet = pstnt.executeQuery();
            while (rSet.next()) {
                patient_id = rSet.getInt("newID");
            }
            
            // Save new asset
            pstnt = conn.prepareStatement("INSERT INTO assets [patient_id, last_name, first_name, gender, birthdate, mobile_number, email_address] VALUE (?, ?, ?, ?, ?, ?, ?)");
            pstnt.setInt(1, patient_id);
            pstnt.setString(2, last_name);
            pstnt.setString(3, first_name);
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static void main(String args[]) {
        
    }
}
