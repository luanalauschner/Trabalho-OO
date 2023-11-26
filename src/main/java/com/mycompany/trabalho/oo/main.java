/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho.oo;

import java.util.Random;

public class main {
    
    public static int gerarId() {
        
        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000);

        return numeroAleatorio;
    }
    public static void main(String args[]){
        
    }
}
