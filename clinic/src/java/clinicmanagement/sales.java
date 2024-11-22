package clinicmanagement;

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

public class sales {
    public int sale_id;
    public int visit_id;
    public String mode_payment;
    public String ref_num;
    public float amt_paid;
    
    public ArrayList<Integer> sale_idList = new ArrayList<>();
    public ArrayList<Integer> visit_idList = new ArrayList<>();
    public ArrayList<String> mode_paymentList = new ArrayList<>();
    public ArrayList<String> ref_numList = new ArrayList<>();
    public ArrayList<Float> amt_paidList = new ArrayList<>();

    public sales() {}
    
    public void set_values(int visit_id, String mode_payment, String ref_num, float amt_paid) {
        this.visit_id = visit_id;
        this.mode_payment = mode_payment;
        this.ref_num = ref_num;
        this.amt_paid = amt_paid;
    }

    public boolean add_sale() {
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
            // Save new sale
            PreparedStatement ps = conn.prepareStatement("INSERT INTO sales (visit_id, mode_payment, ref_num, amt_paid) VALUES (?, ?, ?, ?);");
            ps.setInt(1, visit_id);
            ps.setString(2, mode_payment);
            ps.setString(3, ref_num);
            ps.setFloat(4, amt_paid);
            ps.executeUpdate();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean select_sale() {
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM sales");
            ResultSet rs = ps.executeQuery();
            
            sale_idList.clear();
            visit_idList.clear();
            mode_paymentList.clear();
            ref_numList.clear();
            amt_paidList.clear();

            while (rs.next()) {
                sale_id = rs.getInt("sale_id");
                visit_id = rs.getInt("visit_id");
                mode_payment = rs.getString("mode_payment");
                ref_num = rs.getString("ref_num");
                amt_paid = rs.getFloat("amt_paid");
                
                sale_idList.add(sale_id);
                visit_idList.add(visit_id);
                mode_paymentList.add(mode_payment);
                ref_numList.add(ref_num);
                amt_paidList.add(amt_paid);
            }
            
            ps.close();
            conn.close();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean remove_sale(int saleID) {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password
            
            // Establish Connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Delete the drug
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sales WHERE sale_id = ?;");
            ps.setInt(1, saleID);
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
    
    
}
