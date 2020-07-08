/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius
 */
public class Book {
    ArrayList<String>   words = new ArrayList<String>();
    String              name;
    Book(File file) throws Exception{
        name = file.getName().replaceAll(".txt","");
        try{
            Path p = Paths.get(file.getPath());
            BufferedReader buf = Files.newBufferedReader(p);            
            String aux;
            
            while((aux = buf.readLine())!= null){
                for(String a:normalize(aux).split(" ")){
                    if(!a.equals(""))words.add(a);
                }
            }
            words =  StopWord.filter(words); 
        }catch (IOException ex) {
            Logger.getLogger("problem accessing file"+file.getAbsolutePath());
        }
    }

    public String getName() {
        return name;
    }
    
    public ArrayList<String> getWords(){
        return words;
    }
    
    private String normalize(String phrase){
        phrase = phrase.replaceAll("\\s+"," ");
        /*phrase = phrase.replaceAll("(","");
        phrase = phrase.replaceAll(")","");
        phrase = phrase.replaceAll("\\.","");
        phrase = phrase.replaceAll("-","");
        phrase = phrase.replaceAll(",","");
        phrase = phrase.replaceAll(";","");
        phrase = phrase.replaceAll(":","");
        phrase = phrase.replaceAll("?","");
        phrase = phrase.replaceAll("!","");
        phrase = phrase.replaceAll("\""," ");*/
        return phrase.toLowerCase();
    }
    
    public ArrayList<String> wordsClouds(ArrayList<String> cp){
        ArrayList<String> res = new ArrayList<>(); 
        Collections.sort(cp);
        String previous = cp.get(0);
        int qtd = 0;
        if(previous.equals("")) return res;
        for(String s:cp){
            if(s.equals(previous)){
                qtd++;
            }else{
                res.add(previous+","+qtd);
                previous = s;
                qtd = 0;
            }
        };
        res.add(previous+","+qtd);
        return res;
    } 
    
    public int qtdSearch(String s){
        int qtd = 0;
        for(String v:words)
            if (v.equals(s)) qtd++;
        return qtd;
    }
    
    public ArrayList<String> phraseNets(ArrayList<String> cp){
        ArrayList<String> vert = new ArrayList<>(); 
        ArrayList<String> res = new ArrayList<>(); 
        vert.add("digraph G {");
        String str1,str2;
        String nxt1,nxt2;
        double str1f, nxt1f;
        int qtd = 0;
        int size = cp.size()-1;
        //passar por todas as duplas palavras
        for(int i=0; i<size; i++){
            str1 = normalize(cp.get(i));
            nxt1 = normalize(cp.get(i+1));
            str1f = qtdSearch(str1);
            nxt1f = qtdSearch(nxt1);
            //para cada dupla palavra fazer a combinação com todo o resto do array
            if(str1.equals(str1) && nxt1.equals(nxt1)){
                for(int j=0; j<size; j++){
                    str2 = normalize(cp.get(j));
                    nxt2 = normalize(cp.get(j+1));
                    if(str1.equals(str2) && nxt1.equals(nxt2)){
                        qtd++;
                    }
                }
                //salvar o resultado em algum lugar
                
                //matix, saturação, luminosicade
                String hexColor1 =  "\"0.6,"+(str1f/10)+",0.6\"";
                String hexColor2 =  "\"0.6,"+(nxt1f/10)+",0.6\"";
                String vert1 = "\""+str1+"\"[fontname=\"Verdana\", fontcolor="+hexColor1+", fontsize=\""+(int)(str1f+10)+"\", color=\"white\", style=filled]";
                String vert2 = "\""+nxt1+"\"[fontname=\"Verdana\", fontcolor="+hexColor2+", fontsize=\""+(int)(nxt1f+10)+"\", color=\"white\", style=filled]";
                String regs = "\""+str1+"\"->\""+nxt1+"\" [color=\"1,1,"+((double)qtd/10)+"\", penwidth=\""+qtd+"\"]";
                if(qtd>1){ 
                    if(!res.contains(regs))
                        res.add(regs);
                    if(!vert.contains(vert1))
                        vert.add(vert1);
                    if(!vert.contains(vert2))
                        vert.add(vert2);
                }
                
            }
            qtd = 0;
        };
        for(String s:res)
            vert.add(s);
        
        vert.add("}");
        return vert;
    } 
    
    public void write(ArrayList<String> wr) throws IOException{
        try{
            String p = System.getProperty("user.dir")+"\\"+getName()+".txt";
            FileWriter fstream = new FileWriter(p,true);
            BufferedWriter out = new BufferedWriter(fstream);
            for(String s:wr){
                out.write(s);
                out.newLine();
            }
            out.close();
            
        }catch(IOException e){
            Logger.getLogger(HomeScreen.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
