/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System.Struct;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author ya22y
 */
public class Produit {
    private AtomicInteger code;
    private String nom, desc;
    private Integer qte;
    private Categorie cat;

    public Produit(String nom, Integer qte,Categorie cat,String desc) {
        this.nom = nom;
        this.desc = desc;
        this.qte = qte;
        this.cat=cat;
        
    }
     public String toStringRow(){
        return "'"+nom+"',"+Integer.toString(qte)+","+cat.getCode().toString()+",'"+desc+"'";
    }
    
}
