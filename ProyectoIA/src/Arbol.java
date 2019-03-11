import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arbol {
    private String arbolStr="";
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
        File f= new File("t.txt");
        f.delete();
    }

    

    
}
