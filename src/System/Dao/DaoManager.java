/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package System.Dao;

import System.Struct.*;
import java.util.List;


/**
 *
 * @author ya22y
 */
public interface DaoManager {
    String[] getProduct(String nom);
    List<String[]> getAllProduct();
    void addProduct(Produit produit);
    void deleteProduct(Integer code);
    String[] getCat(String nom);
    void addCat(Categorie cat);
    void ModifyQte(Integer id,Integer qte);
    List<String[]> getProductByCat(String nom);
}
