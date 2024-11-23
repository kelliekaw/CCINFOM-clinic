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
public class ailments {
    
    public int ailment_id;
    public String name;
    public int speci_id;
    
    public ArrayList<Integer> ailment_idList = new ArrayList<>();
    public ArrayList<String> nameList = new ArrayList<>();
    public ArrayList<Integer> speci_idList = new ArrayList<>();
    
    public ailments() {}
    
    public void clearLists() {
        ailment_idList.clear();
        nameList.clear();
        speci_idList.clear();
    }
}
