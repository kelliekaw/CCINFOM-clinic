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
    public int drug_id;
    public String generic_name;
    public String brand_name;
    public float price;
    public String type;
    
    public ArrayList<Integer> drug_idList = new ArrayList<>();
    public ArrayList<String> generic_nameList = new ArrayList<>();
    public ArrayList<String> brand_nameList = new ArrayList<>();
    public ArrayList<Float> priceList = new ArrayList<>();
    public ArrayList<String> typeList = new ArrayList<>();
    
    public pharmacy() {}
    
    public void set_values(String generic_name, String brand_name, float price, String type) {
        this.generic_name = generic_name;
        this.brand_name = brand_name;
        this.price = price;
        this.type = type;
    }
    
    public void clearLists() {
        drug_idList.clear();
        generic_nameList.clear();
        brand_nameList.clear();
        priceList.clear();
        typeList.clear();
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO drugs (generic_name, brand_name, price, type) VALUE (?, ?, ?, ?);");
            ps.setString(1, generic_name);
            ps.setString(2, brand_name);
            ps.setFloat(3, price);
            ps.setString(4, type);
            ps.executeUpdate();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean select_drug() {
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM drugs");
            ResultSet rs = ps.executeQuery();
            
            clearLists();

            while (rs.next()) {
                drug_id = rs.getInt("drug_id");
                generic_name = rs.getString("generic_name");
                brand_name = rs.getString("brand_name");
                price = rs.getFloat("price");
                type = rs.getString("type");
                
                drug_idList.add(drug_id);
                generic_nameList.add(generic_name);
                brand_nameList.add(brand_name);
                priceList.add(price);
                typeList.add(type);
            }
            
            ps.close();
            conn.close();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean remove_drug(int drugID) {
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM drugs WHERE drug_id = ?;");
            ps.setInt(1, drugID);
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
    
    public boolean get_related_shipments(shipment_drug sd, shipments s) {
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
            PreparedStatement ps = conn.prepareStatement("SELECT d.*, sd.qty, s.date " +
                    "FROM drugs d " +
                    "INNER JOIN shipment_drug sd ON d.drug_id = sd.drug_id " +
                    "INNER JOIN shipments s ON sd.shipment_id = s.shipment_id;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();

            while (rs.next()) {
                drug_id = rs.getInt("drug_id");
                generic_name = rs.getString("generic_name");
                brand_name = rs.getString("brand_name");
                price = rs.getFloat("price");
                type = rs.getString("type");
                
                sd.qty = rs.getInt("qty");
                
                s.date = rs.getString("date");
                
                drug_idList.add(drug_id);
                generic_nameList.add(generic_name);
                brand_nameList.add(brand_name);
                priceList.add(price);
                typeList.add(type);
                
                sd.qtyList.add(sd.qty);
                
                s.dateList.add(s.date);
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
