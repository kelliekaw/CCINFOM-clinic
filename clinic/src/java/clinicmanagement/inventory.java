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
public class inventory {
    public int drug_id;
    public int qty;
    
    public ArrayList<Integer> drug_idList = new ArrayList<>();
    public ArrayList<Integer> qtyList = new ArrayList<>();
    
    public inventory() {}
    
    public void clearLists() {
        drug_idList.clear();
        qtyList.clear();
    }
}
