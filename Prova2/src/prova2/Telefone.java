/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova2;

/**
 *
 * @author vilar
 */
public class Telefone {
    String DDD;
    String numero;

    public String getDDD() {
        return DDD;
    }

    public String getNumero() {
        return numero;
    }

    public void setDDD(String DDD) {
        this.DDD = DDD;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Telefone(String DDD, String numero) {
        
        if(numero.length!=9 || numero.lenght!= 8){
            throw new TelefoneException9
        }
        
        this.DDD = DDD;
        this.numero = numero;
    }

   
}
