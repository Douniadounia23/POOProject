/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System.controller;

import System.Dao.Dao;
import SystemGUI.AdminGUI;
import SystemGUI.QTE_InputGUI;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ya22y
 */
public class AdminController {
    private AdminGUI adminView;
    private QTE_InputGUI inputView;
    private Dao db;
    
    public AdminController(AdminGUI adminView,QTE_InputGUI inputView){
        this.adminView=adminView;
        this.inputView=inputView;
        db=Dao.getInstance();
        updateData();
        adminView.SuppButton.addActionListener(e->supprimerActionPerformed());
        adminView.searchButton.addActionListener(e->searchActionPerformed());
        inputView.ModifyButton.addActionListener(e->modifyClickedActionPerformed());
    }
    
    void modifyClickedActionPerformed(){
        Integer value=null,code=null;
        try{
        value=Integer.parseInt(inputView.getValue());
        code=Integer.parseInt(adminView.getSelected());
        }catch(Exception E){
            JOptionPane.showConfirmDialog(null,"Error while parsing !");
        }
        if(code != null){
           try{ db.ModifyQte(code,value);
           inputView.setVisible(false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null,"Error while altering !");
        }}
            updateData();
    }
    
    
    void supprimerActionPerformed(){
        Integer code=null;
       if(adminView.selected!=null){
        try{
         code= Integer.parseInt(adminView.getSelected());
        }catch(Exception E){
            JOptionPane.showConfirmDialog(null,"Error while getting value !");
        }
        if(code != null){
            db.deleteProduct(code);
        }
           }
        updateData();
        adminView.selected=null;
    }
    
    void searchActionPerformed(){
        String keyword=adminView.searchField.getText();
        List<String[]> resultByCat=db.getProductByCat(keyword);
        String[] resultByName=db.getProduct(keyword);
        if(!keyword.equals("")){
           
        if(resultByName.length>0){
            DefaultTableModel model=(DefaultTableModel) adminView.ProductTabel.getModel();
           int rows = model.getRowCount(); 
           for(int i=rows-1;i>=0;i--){
                model.removeRow(i); 
           }
          
           Object[] tableRow= (Object [])resultByName;
         
           adminView.showAllProduct(tableRow);
       }
        
        if(!resultByCat.isEmpty()){
   
             DefaultTableModel model=(DefaultTableModel) adminView.ProductTabel.getModel();
           int rows = model.getRowCount(); 
           for(int i=rows-1;i>=0;i--){
                model.removeRow(i); }
           
           for(int i=0;i<resultByCat.size();i++){
            Object[] tableRow= (Object [])resultByCat.get(i);
            adminView.showAllProduct(tableRow); }
        }
        }else{
            updateData();
        }
    }

     void updateData(){
        
         DefaultTableModel model=(DefaultTableModel) adminView.ProductTabel.getModel();
           int rows = model.getRowCount(); 
           for(int i=rows-1;i>=0;i--){
                model.removeRow(i); 
           }
       List<String[]> allResult= db.getAllProduct();
       
       if(allResult!=null){
         
       for(int i=0;i<allResult.size();i++){
           Object[] tableRow= (Object [])allResult.get(i);
           adminView.showAllProduct(tableRow);
       }
       }else{
          JOptionPane.showMessageDialog(null,"Empty Database!");
       }
       
    }
}
