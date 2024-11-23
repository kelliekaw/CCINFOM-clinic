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
public class shipments {
    
    public int shipment_id;
    public String date;
    public float shipment_cost;
    
    public ArrayList<Integer> shipment_idList = new ArrayList<>();
    public ArrayList<String> dateList = new ArrayList<>();
    public ArrayList<Float> shipment_costList = new ArrayList<>();
    
    public shipments() {}
    
    public void clearLists() {
        shipment_idList.clear();
        dateList.clear();
        shipment_costList.clear();
    }
}
