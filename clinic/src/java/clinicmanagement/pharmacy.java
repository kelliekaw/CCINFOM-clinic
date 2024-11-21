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

public class pharmacy {
    public String generic_name;
    public String brand_name;
    public float price;
    
    public ArrayList<String> generic_nameList = new ArrayList<>();
    public ArrayList<String> brand_nameList = new ArrayList<>();
    public ArrayList<Float> priceList = new ArrayList<>();
    
    public pharmacy() {}
    
    public void set_values(String generic_name, String brand_name, float price) {
        this.generic_name = generic_name;
        this.brand_name = brand_name;
        this.price = price;
    }
    
    public boolean add_pharmacy() {
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
            // Save new drug
            PreparedStatement ps = conn.prepareStatement("INSERT INTO drugs (generic_name, brand_name, price) VALUE (?, ?, ?);");
            ps.setString(1, generic_name);
            ps.setString(2, brand_name);
            ps.setFloat(3, price);
            ps.executeUpdate();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
