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
public class drugs_sold {
    
    public int sale_id;
    public int drug_id;
    public int qty;
    
    public ArrayList<Integer> sale_idList = new ArrayList<>();
    public ArrayList<Integer> drug_idList = new ArrayList<>();
    public ArrayList<Integer> qtyList = new ArrayList<>();
    
    public String year_sold;
    public String month_sold;
    public int num_of_drugs_sold;
    public float total_revenue;
    
    public ArrayList<String> year_soldList = new ArrayList<>();
    public ArrayList<String> month_soldList = new ArrayList<>();
    public ArrayList<Integer> num_of_drugs_soldList = new ArrayList<>();
    public ArrayList<Float> total_revenueList = new ArrayList<>();
    
    public drugs_sold() {}
    
    public void clearLists() {
        sale_idList.clear();
        drug_idList.clear();
        qtyList.clear();
    }
    
    public boolean drug_sales_report(pharmacy ph) {
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
            PreparedStatement ps = conn.prepareStatement("SELECT YEAR(v.log_in) as year_sold, MONTH(v.log_in) as month_sold, " +
                "d.drug_id, dr.generic_name, SUM(d.qty) as number_of_drugs_sold, " +
                "CAST(SUM(d.qty) * dr.price as DECIMAL(20,2)) as total_revenue " +
                "FROM drugs_sold d " +
                "JOIN sales s ON d.sale_id = s.sale_id " +
                "JOIN drugs dr ON d.drug_id = dr.drug_id " +
                "JOIN visits v ON s.visit_id = v.visit_id " +
                "GROUP BY YEAR(v.log_in), MONTH(v.log_in), d.drug_id " +
                "ORDER BY YEAR(v.log_in), MONTH(v.log_in) ASC, SUM(d.qty) DESC;");
                 
            ResultSet rs = ps.executeQuery();
            
            clearLists();
            ph.clearLists();
            while (rs.next()) {
                year_sold = rs.getString("year_sold");
                month_sold = rs.getString("month_sold");
                drug_id = rs.getInt("d.drug_id");
                ph.generic_name = rs.getString("dr.generic_name");
                num_of_drugs_sold = rs.getInt("number_of_drugs_sold");
                total_revenue = rs.getFloat("total_revenue");
                
                year_soldList.add(year_sold);
                month_soldList.add(month_sold);
                drug_idList.add(drug_id);
                ph.generic_nameList.add(ph.generic_name);
                num_of_drugs_soldList.add(num_of_drugs_sold);
                total_revenueList.add(total_revenue);
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
