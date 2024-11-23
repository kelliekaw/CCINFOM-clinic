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
    public float amt_paid;
    
    public ArrayList<Integer> sale_idList = new ArrayList<>();
    public ArrayList<Integer> visit_idList = new ArrayList<>();
    public ArrayList<Float> amt_paidList = new ArrayList<>();
    
    public String month_year;
    public float total_income;
    public int total_shipments;
    public float monthly_profit;
    
    public ArrayList<String> month_yearList = new ArrayList<>();
    public ArrayList<Float> total_incomeList = new ArrayList<>();
    public ArrayList<Integer> total_shipmentsList = new ArrayList<>();
    public ArrayList<Float> monthly_profitList = new ArrayList<>();
    
    public int total_sales;
    public float total_sales_amt;
    public float avg_sale_per_visit;
    
    public ArrayList<Integer> total_salesList = new ArrayList<>();
    public ArrayList<Float> total_sales_amtList = new ArrayList<>();
    public ArrayList<Float> avg_sale_per_visitList = new ArrayList<>();
    
    


    public sales() {}
    
    public void set_values(int visit_id, float amt_paid) {
        this.visit_id = visit_id;
        this.amt_paid = amt_paid;
    }
    
    public void clearLists() {
        sale_idList.clear();
        visit_idList.clear();
        amt_paidList.clear();
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO sales (visit_id,amt_paid) VALUES (?, ?);");
            ps.setInt(1, visit_id);
            ps.setFloat(2, amt_paid);
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
            
            // Display all sales records
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM sales");
            ResultSet rs = ps.executeQuery();
            
            clearLists();

            while (rs.next()) {
                sale_id = rs.getInt("sale_id");
                visit_id = rs.getInt("visit_id");
                amt_paid = rs.getFloat("amt_paid");
                
                sale_idList.add(sale_id);
                visit_idList.add(visit_id);
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
    
    public boolean get_related_drugs(drugs_sold ds, inventory i, shipment_drug sd, pharmacy ph) {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Display all sales + drugs sold
            PreparedStatement ps = conn.prepareStatement("SELECT s.*, ds.qty, d.generic_name, d.brand_name " +
                    "FROM sales s " +
                    "INNER JOIN drugs_sold ds ON s.sale_id = ds.sale_id " +
                    "INNER JOIN drugs d ON ds.drug_id = d.drug_id;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            ds.clearLists();
            ph.clearLists();
            
            while (rs.next()) {
                sale_id = rs.getInt("sale_id");
                visit_id = rs.getInt("visit_id");
                amt_paid = rs.getFloat("amt_paid");
                
                ds.qty = rs.getInt("qty");
                
                ph.generic_name = rs.getString("generic_name");
                ph.brand_name = rs.getString("brand_name");
                
                sale_idList.add(sale_id);
                visit_idList.add(visit_id);
                amt_paidList.add(amt_paid);
                
                ds.qtyList.add(ds.qty);
                
                ph.generic_nameList.add(ph.generic_name);
                ph.brand_nameList.add(ph.brand_name);
            }
            
            ps.close();
            conn.close();

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean get_related_visits(visits v, patients p, doctors d, ailments a) {
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
            PreparedStatement ps = conn.prepareStatement("SELECT s.*, p.last_name, p.first_name, p.middle_initial, "
                    + "d.last_name AS `doc_last_name`, d.first_name AS `doc_first_name`, d.middle_initial AS `doc_mid_initial`, a.name " +
                    "FROM sales s " +
                    "INNER JOIN visits v ON s.visit_id = v.visit_id " +
                    "INNER JOIN patients p ON v.patient_id = p.patient_id " +
                    "INNER JOIN doctors d ON v.doctor_id = d.doctor_id " +
                    "INNER JOIN ref_ailments a ON v.ailment_id = a.ailment_id;");
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            v.clearLists();
            p.clearLists();
            d.clearLists();
            a.clearLists();

            while (rs.next()) {
                sale_id = rs.getInt("sale_id");
                visit_id = rs.getInt("visit_id");
                amt_paid = rs.getFloat("amt_paid");
                
                p.last_name = rs.getString("last_name");
                p.first_name = rs.getString("first_name");
                p.middle_initial = rs.getString("middle_initial");
                
                d.last_name = rs.getString("doc_last_name");
                d.first_name = rs.getString("doc_first_name");
                d.middle_initial = rs.getString("doc_mid_initial");
                
                a.name = rs.getString("name");
                                
                sale_idList.add(sale_id);
                visit_idList.add(visit_id);
                amt_paidList.add(amt_paid);
                
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
    
    public boolean filter_sales(String min_amt, String max_amt) {
        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/clinic";
            String username = "root"; 
            String password = "cupcakes101";  // change with ur password

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            String query = "SELECT * FROM sales WHERE 1=1";
            
            if (min_amt != null && !min_amt.isEmpty()) {
                query += " AND amt_paid >= ?";
            }
            if (max_amt != null && !max_amt.isEmpty()) {
                query += " AND amt_paid <= ?";
            }
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            int index = 1;
            if (min_amt != null && !min_amt.isEmpty()) {
                ps.setFloat(index++, Float.parseFloat(min_amt));
            }
            if (max_amt != null && !max_amt.isEmpty()) {
                ps.setFloat(index++, Float.parseFloat(max_amt));
            }
            
            ResultSet rs = ps.executeQuery();
            
            clearLists();

            while (rs.next()) {
                sale_id = rs.getInt("sale_id");
                visit_id = rs.getInt("visit_id");
                amt_paid = rs.getFloat("amt_paid");
                
                sale_idList.add(sale_id);
                visit_idList.add(visit_id);
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
    
    public boolean get_sales_record(int salesID) {
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM sales WHERE sale_id = ?;");
            ps.setInt(1, salesID);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                sale_id = rs.getInt("sale_id");
                visit_id = rs.getInt("visit_id");
                amt_paid = rs.getFloat("amt_paid");
            }

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update_sales(int salesID, float newAmt) {
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
            // Update Drug
            PreparedStatement ps = conn.prepareStatement("UPDATE sales SET amt_paid = ? WHERE sale_id = ?;");
            ps.setFloat(1, newAmt);
            ps.setInt(2, salesID);
            
            return ps.executeUpdate() > 0;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public boolean finance_report() {
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
            PreparedStatement ps = conn.prepareStatement("SELECT DATE_FORMAT(v.log_in, '%Y-%m') AS month_year, " +
                "SUM(s.amt_paid) + SUM(d.consultation_rate) AS total_income, " +
                "IFNULL(SUM(sh.shipment_cost), 0) AS total_shipments, " +
                "(SUM(s.amt_paid) + SUM(d.consultation_rate) - IFNULL(SUM(sh.shipment_cost), 0)) AS monthly_profit " +
                "FROM clinic.sales s " +
                "JOIN clinic.visits v ON s.visit_id = v.visit_id " +
                "JOIN  clinic.doctors d ON v.doctor_id = d.doctor_id " +
                "LEFT JOIN clinic.shipments sh ON DATE_FORMAT(v.log_in, '%Y-%m') = DATE_FORMAT(sh.date, '%Y-%m') " +
                "GROUP BY month_year " +
                "ORDER BY month_year;");
                 
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            
            while (rs.next()) {
                month_year = rs.getString("month_year");
                total_income = rs.getFloat("total_income");
                total_shipments = rs.getInt("total_shipments");
                monthly_profit = rs.getFloat("monthly_profit");
                
                month_yearList.add(month_year);
                total_incomeList.add(total_income);
                total_shipmentsList.add(total_shipments);
                monthly_profitList.add(monthly_profit);
            }
            ps.close();
            conn.close();
            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public boolean sales_per_doctor(doctors d, visits v) {
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
            PreparedStatement ps = conn.prepareStatement("SELECT d.first_name AS doctor_first_name, d.last_name AS doctor_last_name, " +
                "COUNT(s.sale_id) AS total_sales, " +
                "SUM(s.amt_paid) AS total_sales_amount, " +
                "AVG(s.amt_paid) AS avg_sale_per_visit " +
                "FROM clinic.sales s " +
                "JOIN clinic.visits v ON s.visit_id = v.visit_id " +
                "JOIN clinic.doctors d ON v.doctor_id = d.doctor_id " +
                "GROUP BY d.doctor_id " +
                "ORDER BY total_sales DESC;");
                 
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            d.clearLists();
            v.clearLists();
            
            while (rs.next()) {
                d.first_name = rs.getString("doctor_first_name");
                d.last_name = rs.getString("doctor_last_name");
                total_sales = rs.getInt("total_sales");
                total_sales_amt = rs.getFloat("total_sales_amount");
                avg_sale_per_visit = rs.getFloat("avg_sale_per_visit");
                
                d.first_nameList.add(d.first_name);
                d.last_nameList.add(d.last_name);
                total_salesList.add(total_sales);
                total_sales_amtList.add(total_sales_amt);
                avg_sale_per_visitList.add(avg_sale_per_visit);
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
