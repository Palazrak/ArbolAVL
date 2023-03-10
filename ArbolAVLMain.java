/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolAVL;

import static arbolAVL.TreePrinter.print;

/**
 *
 * @author Pablo Alazraki
 */
public class ArbolAVLMain {
    
    public static void main(String[] args) {
        ArbolAVL<Integer> a1 = new ArbolAVL(0);
        for (int i = 1; i < 20; i++)
            a1.add(i);

        print(a1.getRaiz());
        
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
        
        a1.remove(8);
        print(a1.getRaiz());
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
    
        
        
        a1.remove(9);
        print(a1.getRaiz());
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
    
        
        a1.remove(3);
        print(a1.getRaiz());
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
    
        a1.remove(17);
        print(a1.getRaiz());
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
    
       
        
    }
}
