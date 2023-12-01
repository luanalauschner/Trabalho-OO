/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.teste_trabalho;

/**
 *
 * @author vilar
 */
public class Teste_trabalho {
    
    public static boolean validaTelefone(String s) throws FormatoException{
        String[] aux = s.split("[\\-]");
        
        if(aux.length != 2)
            throw new FormatoException();

        /*if((aux[0].length() == 0 || aux[0].length() != 5) && !aux[1].equals('-') && (aux[0].length() == 0 || aux[0].length() != 3))*/
        if(aux[0].length() != 5 || aux[1].length() != 3)
            throw new FormatoException();
            else
                return true;
    }

    public static void main(String[] args) {
        try{
            validaTelefone("-00650");
            System.out.println("CEP cadastrado");
        }catch(FormatoException e){
            System.out.println(e.getMessage());
        }
        
        //System.out.println("CPF cadastrado");
    }
}
