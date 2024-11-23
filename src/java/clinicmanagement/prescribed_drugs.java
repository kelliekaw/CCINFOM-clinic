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
public class prescribed_drugs {
    
    public int visit_id;
    public int drug_id;
    public int qty_drugs;
    
    public ArrayList<Integer> visit_idList = new ArrayList<>();
    public ArrayList<Integer> drug_idList = new ArrayList<>();
    public ArrayList<Integer> qty_drugsList = new ArrayList<>();
    
    public prescribed_drugs() {}
    
    public void clearLists() {
        visit_idList.clear();
        drug_idList.clear();
        qty_drugsList.clear();
    }
}
