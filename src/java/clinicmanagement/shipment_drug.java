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
public class shipment_drug {
    
    public int shipment_id;
    public int drug_id;
    public int qty;
    
    public ArrayList<Integer> shipment_idList = new ArrayList<>();
    public ArrayList<Integer> drug_idList = new ArrayList<>();
    public ArrayList<Integer> qtyList = new ArrayList<>();

    public shipment_drug() {}
    
    public void clearLists() {
        shipment_idList.clear();
        drug_idList.clear();
        qtyList.clear();
    }
}
