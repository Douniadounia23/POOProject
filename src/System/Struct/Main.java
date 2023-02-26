/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System.Struct;

import SystemGUI.LoginGUI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ya22y
 */
public class Main {
    
    public static void main(String[] args) {
       
        try {
            LoginGUI.launch();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
