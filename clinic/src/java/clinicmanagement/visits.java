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
public class visits {
    
    public int visit_id;
    public int patient_id;
    public String log_in;
    public String log_out;
    public int ailment_id;
    public int doctor_id;
    
    public ArrayList<Integer> visit_idList = new ArrayList<>();
    public ArrayList<Integer> patient_idList = new ArrayList<>();
    public ArrayList<String> log_inList = new ArrayList<>();
    public ArrayList<String> log_outList = new ArrayList<>();
    public ArrayList<Integer> ailment_idList = new ArrayList<>();
    public ArrayList<Integer> doctor_idList = new ArrayList<>();
    
    public visits() {}
    
    public void clearLists() {
        visit_idList.clear();
        patient_idList.clear();
        log_inList.clear();
        log_outList.clear();
        ailment_idList.clear();
        doctor_idList.clear();
    }
    
}
