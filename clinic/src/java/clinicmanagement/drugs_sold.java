/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicmanagement;

/**
 *
 * @author kiwik
 */
import java.util.*;
public class drugs_sold {
    
    public int sale_id;
    public int shipment_id;
    public int drug_id;
    public int qty;
    
    public ArrayList<Integer> sale_idList = new ArrayList<>();
    public ArrayList<Integer> shipment_idList = new ArrayList<>();
    public ArrayList<Integer> drug_idList = new ArrayList<>();
    public ArrayList<Integer> qtyList = new ArrayList<>();

    public drugs_sold() {}
}
