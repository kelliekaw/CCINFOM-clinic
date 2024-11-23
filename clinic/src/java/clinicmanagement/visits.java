/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicmanagement;

/**
 *
 * @author kiwik
 */

import java.sql.*;
import java.util.*;
public class visits {
    
    public int visit_id;
    public int patient_id;
    public String log_in;
    public String log_out;
    public int ailment_id;
    public int doctor_id;
    
    public ArrayList<Integer> visit_idList = new ArrayList<>();
    public ArrayList<Integer> patient_idList = new ArrayList<>();
    public ArrayList<String> log_inList = new ArrayList<>();
    public ArrayList<String> log_outList = new ArrayList<>();
    public ArrayList<Integer> ailment_idList = new ArrayList<>();
    public ArrayList<Integer> doctor_idList = new ArrayList<>();
    
    public visits() {}
    
    public void clearLists() {
        visit_idList.clear();
        patient_idList.clear();
        log_inList.clear();
        log_outList.clear();
        ailment_idList.clear();
        doctor_idList.clear();
    }
    
    public boolean select_visit(patients p, doctors d, ailments a) {
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
            PreparedStatement ps = conn.prepareStatement("SELECT v.*, p.last_name, p.first_name, p.middle_initial, "
                    + "d.last_name AS `doc_last_name`, d.first_name AS `doc_first_name`, d.middle_initial AS `doc_mid_initial`, a.name " +
                    "FROM visits v " +
                    "INNER JOIN patients p ON v.patient_id = p.patient_id " +
                    "INNER JOIN doctors d ON v.doctor_id = d.doctor_id " +
                    "INNER JOIN ref_ailments a ON v.ailment_id = a.ailment_id;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            p.clearLists();
            d.clearLists();
            a.clearLists();
            
            while (rs.next()) {
                visit_id = rs.getInt("visit_id");
                patient_id = rs.getInt("patient_id");
                log_in = rs.getString("log_in");
                log_out = rs.getString("log_out");
                ailment_id = rs.getInt("ailment_id");
                doctor_id = rs.getInt("doctor_id");
                
                p.last_name = rs.getString("last_name");
                p.first_name = rs.getString("first_name");
                p.middle_initial = rs.getString("middle_initial");
                
                d.last_name = rs.getString("doc_last_name");
                d.first_name = rs.getString("doc_first_name");
                d.middle_initial = rs.getString("doc_mid_initial");
                
                a.name = rs.getString("name");
                
                visit_idList.add(visit_id);
                patient_idList.add(patient_id);
                log_inList.add(log_in);
                log_outList.add(log_out);
                ailment_idList.add(ailment_id);
                doctor_idList.add(doctor_id);
                
                p.last_nameList.add(p.last_name);
                p.first_nameList.add(p.first_name);
                p.middle_initialList.add(p.middle_initial);
                
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
    
}
