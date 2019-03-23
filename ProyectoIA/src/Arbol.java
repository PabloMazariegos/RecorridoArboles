import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;


public class Arbol {
    private String arbolStr="";
    public String RecorridoStr= "";
    public String ItsStr = "";
    public String AnchSTR [] = new String[255];
    public String cad ="";
    class Nodo{
        int valor;
        Nodo izq, der;
    }
    Nodo raiz;
    
    public Arbol(){
        raiz= null;
    }
    
    
    public void Insertar(int valor){
        Nodo nuevo = new Nodo();
        nuevo.valor = valor;
        nuevo.izq = null;
        nuevo.der = null;
        
        if(raiz == null){
            raiz = nuevo;   
            
        }else{
            Nodo aux = null, anterior= null;
            aux = raiz;
            
            while(aux != null){
                anterior = aux;
                if(valor < aux.valor){
                    aux = aux.izq;
                }else{
                    aux = aux.der;
                }
            }
            if(valor < anterior.valor){
                anterior.izq = nuevo;
            }else{
                anterior.der = nuevo;
            }
        }
    }
    
    public void GenerarImagen(Nodo raiz){ 

        if(raiz.izq!= null){
            arbolStr+=raiz.valor+ " -> "+raiz.izq.valor+",";
            GenerarImagen(raiz.izq);
        }
        //System.out.println(raiz.valor);        

        if(raiz.der!=null){
            arbolStr+=raiz.valor+ " -> "+raiz.der.valor+",";
            GenerarImagen(raiz.der);
        }     
        
        GenerarTxt();
    }
    
    public void GenerarTxt(){
        FileWriter  fw  = null;
        PrintWriter pw  = null;
        try{
           fw   = new FileWriter("t.txt");
           pw   = new PrintWriter(fw);
           pw.println("digraph g {");
           String nodos []= arbolStr.split(",");
           for(int i=0; i<nodos.length; i++){
               pw.println(nodos[i]);
           }
           pw.println("}");
           pw.close();
        }catch(Exception e){
            System.out.println(e);
        }
        
        Runtime rnt= Runtime.getRuntime();
        try {
                Process p = rnt.exec("dot -Tpng t.txt -o t.png");
                p.waitFor();
                if(p.exitValue() != 0){
                  p = rnt.exec("C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot -Tpng t.txt -o t.png");
                  p.waitFor();  
                }
            
        } catch (Exception ex)  {
            System.out.println(ex);
        }
        //File f= new File("t.txt");
        //f.delete();
    }
    
    
    public void  PrimeroProfundidad(Nodo raiz){        
        if(raiz!= null){
            RecorridoStr+=raiz.valor+" -> ";
            ItsStr +=RecorridoStr +"\n";
            PrimeroProfundidad(raiz.der); 
            PrimeroProfundidad(raiz.izq);
        }        
    }
    
    public void PrimeroAnchura(Nodo raiz){
        LinkedList cola= new LinkedList();
        LinkedList recorrido = new LinkedList();
        Nodo aux= null;
        int i=0;
        
        cola.addLast(raiz);
        while(cola.size()> 0){
            aux= (Nodo) cola.removeFirst();
            recorrido.addLast(aux.valor);
            cad+=aux.valor+"->";
            AnchSTR [i] = cad;
            
            if(aux.izq!= null){
                cola.addLast(aux.izq);
            }
            if(aux.der!= null){
                cola.addLast(aux.der);
            }
            i++;
        }
       
        RecorridoStr = recorrido.toString();
       
    }
    

    
}
