/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolAVL;

/**
 *
 * @author Pablo Alazraki
 * @param <T>
 */
public class ArbolAVL<T extends Comparable<T>> {

    private NodoAVL<T> raiz;

    public ArbolAVL(T elem) {
        this.raiz = new NodoAVL(elem);
    }

    public NodoAVL<T> getRaiz() {
        return this.raiz;
    }

    //Método que calcula la altura del subarbol a partir del nodo actual 
    public int alturaArbol(NodoAVL<T> actual) {
        if (actual == null) 
            return 0;
        int f1 = alturaArbol(actual.getIzq()) + 1;
        int f2 = alturaArbol(actual.getDer()) + 1;
        
        return Math.max(f1, f2);
    }
    //Método que calcula el factor de equilibrio de un nodo
    private int calculaFe(NodoAVL<T> actual){
        NodoAVL<T> subDer = actual.getDer();
        NodoAVL<T> subIzq = actual.getIzq();
        
        return alturaArbol(subDer) - alturaArbol(subIzq);
    }
    
    //ROTACIONES
    private NodoAVL<T> rotacionIzqIzq(NodoAVL<T> actual){
        NodoAVL<T> alfa = actual;
        NodoAVL<T> beta = alfa.getIzq();
        NodoAVL<T> C = beta.getDer();
        
        if (alfa == this.raiz)
            this.raiz = beta;
        
        beta.setPapa(alfa.getPapa());
        alfa.setPapa(beta);
        alfa.setIzq(C);
        
        if (C != null)
            C.setPapa(alfa);
      
        beta.setDer(alfa);
        
        if (beta.getPapa() != null) 
            if (beta.getPapa().getIzq() == alfa) 
                beta.getPapa().setIzq(beta);
            else 
                beta.getPapa().setDer(beta);
        
        alfa.setFE(calculaFe(alfa));
        beta.setFE(calculaFe(beta));
        
        return beta;
    }
    
    private NodoAVL<T> rotacionDerDer(NodoAVL<T> actual){
        NodoAVL<T> alfa = actual;
        NodoAVL<T> beta = alfa.getDer();
        NodoAVL<T> B = beta.getIzq();
        
        if (alfa == this.raiz)
            this.raiz = beta;
        
        beta.setPapa(alfa.getPapa());
        alfa.setPapa(beta);
        
        if (B != null)
            B.setPapa(alfa);
        
        alfa.setDer(B);
        beta.setIzq(alfa);
        
        if (beta.getPapa() != null) 
            if (beta.getPapa().getIzq() == alfa) 
                beta.getPapa().setIzq(beta);
            else 
                beta.getPapa().setDer(beta);

        alfa.setFE(calculaFe(alfa));
        beta.setFE(calculaFe(beta));
        
        return beta;
    }
    
    private NodoAVL<T> rotacionIzqDer(NodoAVL<T> actual){
        NodoAVL<T> alfa = actual;
        NodoAVL<T> beta = alfa.getIzq();
        NodoAVL<T> gamma = beta.getDer();
        NodoAVL<T> B = gamma.getIzq();
        NodoAVL<T> C = gamma.getDer();
        
        if (alfa == this.raiz)
            this.raiz = gamma;
        
        gamma.setPapa(alfa.getPapa());
        
        if (B != null)
            B.setPapa(beta);
        
        beta.setDer(B);
        
        if (C != null)
            C.setPapa(alfa);
        
        alfa.setIzq(C);
        alfa.setPapa(gamma);
        beta.setPapa(gamma);
        gamma.setIzq(beta);
        gamma.setDer(alfa);
        
        if (gamma.getPapa() != null) 
            if (gamma.getPapa().getIzq() == alfa) 
                gamma.getPapa().setIzq(gamma);
            else 
                gamma.getPapa().setDer(gamma);
            
        alfa.setFE(calculaFe(alfa));
        beta.setFE(calculaFe(beta));
        gamma.setFE(calculaFe(gamma));
        
        return gamma; 
    }
    
    private NodoAVL<T> rotacionDerIzq(NodoAVL<T> actual){
        NodoAVL<T> alfa = actual;
        NodoAVL<T> beta = alfa.getDer();
        NodoAVL<T> gamma = beta.getIzq();
        NodoAVL<T> B = gamma.getIzq();
        NodoAVL<T> C = gamma.getDer();

        if (alfa == this.raiz)
            this.raiz = gamma;
        
        gamma.setPapa(alfa.getPapa());
        
        if (B != null)
            B.setPapa(alfa);
        
        alfa.setDer(B);
        
        if (C != null)
            C.setPapa(beta);
        
        beta.setIzq(C);
        gamma.setDer(beta);
        gamma.setIzq(alfa);
        alfa.setPapa(gamma);
        beta.setPapa(gamma);
        
        if (gamma.getPapa() != null)
            if (gamma.getPapa().getIzq() == alfa) 
                gamma.getPapa().setIzq(gamma);
            else 
                gamma.getPapa().setDer(gamma);

        alfa.setFE(calculaFe(alfa));
        beta.setFE(calculaFe(beta));
        gamma.setFE(calculaFe(gamma));
        
        return gamma;
    }

    //INSERTA
    public void add(T dato) {
        //El arbol esta vacio
        if (raiz == null) {
            raiz = new NodoAVL(dato);
            return;
        }
        //1. Inserta Normal
        NodoAVL<T> actual = raiz, papa = raiz;
        NodoAVL<T> nuevo = new NodoAVL(dato);
        while (actual != null) {
            papa = actual;
            if (dato.compareTo(actual.getElem()) <= 0) 
                actual = actual.getIzq();
            else 
                actual = actual.getDer(); 
        }
        papa.cuelga(nuevo);
        
        //2. Recorre la rama donde se insertó para actualizar factores de equilibrio y equilibrar
        actual = nuevo;
        boolean bandera = false;
        while (!bandera && papa != null){
            papa.setFE(calculaFe(papa));
            
            if (papa.getFE() == 0)
                bandera = true;
            
            if (papa.getFE() == 2)
                if (actual.getFE() >= 0)
                    papa = rotacionDerDer(papa);
                else
                    papa = rotacionDerIzq(papa);
            
            if (papa.getFE() == -2)
                if (actual.getFE() <= 0)
                    papa = rotacionIzqIzq(papa);
                else
                    papa = rotacionIzqDer(papa);
            
            actual = papa;
            papa = papa.getPapa();     
        }
    }
    //Método que busca un dato en el arbol y regresa al direccion del nodo. Si no lo encuentra regresa null
    private NodoAVL<T> busca(T dato) {
        NodoAVL<T> actual = this.raiz;
        while (actual != null && actual.getElem().compareTo(dato) != 0)
            if (dato.compareTo(actual.getElem()) <= 0)
                actual = actual.getIzq();
            else
                actual = actual.getDer();
        
        return actual;
    }
    
    public boolean contains(T dato){
        boolean res = false;
        NodoAVL<T> temp = busca(dato);
        if (temp != null)
            res = true;
        return res;
    }
    
    //Método que elimina un nodo del arbol y regresa el dato del nodo eliminado
    public T remove (T elem){
        //-------1. ELIMINA NORMAL --------
        NodoAVL<T> actual = busca(elem);
        //No está el dato a eliminar
        if (actual == null) 
            throw new RuntimeException();
        NodoAVL<T> papa = actual.getPapa();
        NodoAVL<T> temp;
        T res = actual.getElem();//guarda el dato del actual

        //Caso 1) No tiene hijo
        if (actual.getIzq() == null && actual.getDer() == null) {
            //¿Es la raíz?
            if (actual == raiz) 
                raiz = null;
            //¿Soy hijo derecho?
            if ((res.compareTo(actual.getPapa().getElem())) > 0) //Si
                papa.setDer(null);
            else //No, soy hijo izquierdo
                papa.setIzq(null);
            actual.setPapa(null);
            temp = papa;
        }
      
        //Caso 2) Tiene un hijo
        if (actual.getIzq() == null || actual.getDer() == null) {
            NodoAVL<T> hijo;
            if (actual.getIzq() == null) 
                hijo = actual.getDer();
            else 
                hijo = actual.getIzq();
            
            if (actual.equals(raiz)) 
                raiz = hijo;
            else 
                papa.cuelga(hijo);
            actual.setPapa(null);
            temp = papa;
        }
        //Caso 3) Tiene 2 hijos
        else {
            NodoAVL<T> aux = actual.getDer();
            while (aux.getIzq() != null) 
                aux = aux.getIzq(); 
            actual.setElem(aux.getElem());
            //1.1 Aux no es hijo de actual
            if (aux != actual.getDer()){ 
                temp = aux.getPapa();
                if (aux.getDer() == null) {//Aux es una hoja
                    aux.getPapa().setIzq(null);
                    aux.setPapa(null);
                }
                else{ //Aux tiene un hijo derecho 
                    aux.getPapa().cuelga(aux.getDer()); 
                    aux.setPapa(null);
                }
            }
            //1.2 Aux si fue hijo de actual
            else{ 
                temp = actual;
                if (aux.getDer() == null){ //Aux es hoja
                    actual.setDer(null);
                    aux.setPapa(null);
                }
                else{//Aux tiene hijo derecho
                    aux.getDer().setPapa(actual.getPapa());
                    aux.getPapa().setDer(aux.getDer());
                    aux.setDer(null);
                    aux.setPapa(null);
                }
            }
        }
        //-------2. ELIMINA AVL --------
        boolean bandera = false;
        NodoAVL<T> papaTemp = temp;
        while (!bandera && papaTemp != null){
            papaTemp.setFE(calculaFe(papaTemp));
            if (papaTemp.getFE() == 1 || papaTemp.getFE() == -1)
                bandera = true;
            if (papaTemp.getFE() == 2)
                if (actual.getFE() >= 0)
                    papaTemp = rotacionDerDer(papaTemp);
                else
                    papaTemp = rotacionDerIzq(papaTemp);
            
            if (papaTemp.getFE() == -2)
                if (actual.getFE() <= 0)
                    papaTemp = rotacionIzqIzq(papaTemp);
                else
                    papaTemp = rotacionIzqDer(papaTemp);
            
            temp = papaTemp;
            papaTemp = papaTemp.getPapa();
        }
        
        return res;
    }
    
    
    
    
}
    