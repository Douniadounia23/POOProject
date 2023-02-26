/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System.controller;
import System.Dao.Dao;
import System.Struct.*;
import SystemGUI.AdminGUI;
import SystemGUI.InputGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ya22y
 */
public class InputProductController implements ActionListener{
    
    private InputGUI inputView;
    private AdminGUI adminView;
    private Produit produit;
    private Dao db=null;
    private final static int NAME=0,QTE=1,CAT=2,DESC=3;
    public InputProductController(InputGUI inputView,AdminGUI adminView){
        this.inputView=inputView;
        this.adminView=adminView;
        db=Dao.getInstance();
        inputView.addButton.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String[] data=inputView.getData();
        Categorie categorie;
        String[] categorie_query=db.getCat(data[CAT]);
        if(categorie_query==null){
            categorie=new Categorie("0",data[CAT]);
            db.addCat(categorie);
            categorie_query=db.getCat(data[CAT]);
        }
            categorie=new Categorie(categorie_query[0],data[CAT]);
             produit= new Produit(data[NAME],Integer.parseInt(data[QTE]),categorie,data[DESC]);
             String[] queryResult=db.getProduct(data[NAME]);
        if(queryResult==null){
            db.addProduct(produit);
            inputView.setVisible(false);
            }else{
             JOptionPane.showMessageDialog(null, "Produit existe déja!");
             }
        
        updateData();
        
        
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
