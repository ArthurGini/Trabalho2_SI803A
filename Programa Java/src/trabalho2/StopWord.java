/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2;

import java.util.ArrayList;

/**
 *
 * @author Vinicius
 */
public class StopWord {
    static ArrayList<String> wd = new ArrayList<>();;  
    
    public static void Add(String word){
          wd.add(word);
    }
    public static ArrayList<String> filter(ArrayList<String> lista) throws Exception{
       
        lista.removeAll(wd);
        return lista;
    }
    
    StopWord() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
