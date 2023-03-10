/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolAVL;

import arbolBinario.NodoBin;

/**
 *
 * @author Pablo Alazraki
 * @param <T>
 */
public class NodoAVL<T extends Comparable<T>> implements PrintableNode<T> {
    private T elem;
    private NodoAVL<T> izq, der, papa;
    private int fe;
    
    public NodoAVL (T elem) {
        this.elem = elem;
        this.der = null;
        this.izq = null;
        this.papa = null;
        this.fe = 0;
    }

    @Override
    public T getElem() {
        return elem;
    }

    @Override
    public NodoAVL<T> getIzq() {
        return izq;
    }

    @Override
    public NodoAVL<T> getDer() {
        return der;
    }
    
    public NodoAVL<T> getPapa(){
        return papa;
    }
   
    public int getFE(){
        return fe;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public void setIzq(NodoAVL<T> izq) {
        this.izq = izq;
    }

    public void setDer(NodoAVL<T> der) {
        this.der = der;
    }
    
    public void setPapa(NodoAVL<T> papa){
        this.papa = papa;
    }
    
    public void setFE(int fe){
        this.fe = fe;
    }

    //Metodo que "cuelga" un nodo hijo en el papá
    public void cuelga(NodoAVL<T> hijo){
        if (hijo == null)
            return;
        if (hijo.getElem().compareTo(elem)<= 0)
            izq = hijo;
        else
            der = hijo;
        hijo.setPapa(this);
    }
    
    @Override
    public String toString(){
        return this.elem.toString();
    }
    
}