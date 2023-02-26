/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System.Dao;
import System.Struct.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ya22y
 */
public class Dao implements DaoManager{

       private static Dao INSTANCE=null;
       private static final String URL="jdbc:postgresql://localhost:5432/stockdb";
       private static final String USER="ya22is";
       private static final String PASSWORD="_co$mo$69_";
       private static final String CODE="code",NOM="nom",QTE="qte";
       private static final String CAT="nom_cat",DESCRIPTION="description";
       private static Connection connect;
       private List<String[]> productList=null;
       
       private Dao(){
           try {
               Class.forName("org.postgresql.Driver");
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
           }
             
        try {
            connect= DriverManager.getConnection(URL,USER,PASSWORD);
            
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       }
       
       public synchronized static Dao getInstance(){
           if(INSTANCE==null){
               INSTANCE= new Dao();
           }
           return INSTANCE;
       }
       
       
    @Override
    public String[] getProduct(String nom) {
           try {
               Statement statement= connect.createStatement();
               String query="SELECT * FROM produit INNER JOIN categorie on cat=id where nom='"+nom+"';";
               ResultSet result= statement.executeQuery(query);
               
               if(result.next()){
                   System.out.println("getProduct is returning");
                   return new String[]{result.getString(CODE),result.getString(NOM),
                   result.getString(QTE),result.getString(CAT),result.getString(DESCRIPTION)};
                   
               }
           } catch (SQLException ex) {
               Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
           }
           return null;
           }

    @Override
    public List<String[]> getAllProduct() {
 try {
            Statement statement= connect.createStatement();
      
           String query= "SELECT * FROM produit INNER JOIN categorie on cat=id;";
           
           ResultSet result= statement.executeQuery(query);
           productList= new ArrayList<String[]>();
           while(result.next()){
               String[] some_data=new String[]{result.getString(CODE),result.getString(NOM),result.getString(QTE),
           result.getString(CAT),result.getString(DESCRIPTION)};
               if(some_data!=null){
               productList.add(some_data);
               }else{
                   JOptionPane.showMessageDialog(null, "Empty Result!");
               }
           
           }
           return productList;
           
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    }

    @Override
    public void addProduct(Produit produit) {
        try {
            Statement statement= connect.createStatement();
            
           String query= "INSERT INTO produit(nom,qte,cat,description)VALUES("+produit.toStringRow()+");";
            statement.executeUpdate(query);
           
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteProduct(Integer code) {
 try {
            Statement statement= connect.createStatement();
      
           String query= "DELETE FROM produit WHERE code="+code+" RETURNING NULL;";
           statement.executeQuery(query);
        

           
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public String[] getCat(String nom) {
 try {
            Statement statement= connect.createStatement();
      
           String query= "SELECT * FROM categorie WHERE nom_cat='"+nom+"';";
           ResultSet result= statement.executeQuery(query);
           if(result.next())
           return new String[]{result.getString("id"),result.getString(CAT)};
           
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return null;    }

    @Override
    public void addCat(Categorie cat) {
           try {
               Statement statement= connect.createStatement();
               String query="INSERT INTO categorie(nom_cat) VALUES('"+ cat.getNom()+"');";
               statement.executeUpdate(query);
           } catch (SQLException ex) {
               Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    @Override
    public void ModifyQte(Integer id,Integer qte) {
        try {
            Statement statement= connect.createStatement();
                    String query= "UPDATE produit SET qte= '" + qte.toString()+"'where code ="+id.toString()+";";
                      statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }    }
   
    @Override
    public List<String[]> getProductByCat(String nom){
         try {
            Statement statement= connect.createStatement();
      
           String query= "SELECT * FROM categorie INNER JOIN produit on id=cat where nom_cat='"+nom+"';";
           
           ResultSet result= statement.executeQuery(query);
           productList= new ArrayList<String[]>();
          
           while(result.next()){
               String[] some_data=new String[]{result.getString(CODE),result.getString(NOM),result.getString(QTE),
           result.getString(CAT),result.getString(DESCRIPTION)};
               if(some_data!=null){
               productList.add(some_data);
               }else{
                   JOptionPane.showMessageDialog(null, "Empty Result!");
               }
           
           }
           System.out.println("GetProductByCat is returning");
           return productList;
           
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
