/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova2;

/**
 *
 * @author vilar
 */
public class TelefoneException extends Exception{
    
    public TelefoneException(String message){
        super("Telefone invalido");
    }
}
