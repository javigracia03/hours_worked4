/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package recuento_horas;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javie
 */
public class PRUEBA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Utilidades.milisToHoras_suma(3600000);
        Trabajadores t1 = new Trabajadores();
       // t1.setFechaCorta("20/03/2024");
       
        try {
            System.out.println(Utilidades.ano_desdeFecha("20/03/2020"));
        } catch (ParseException ex) {
            Logger.getLogger(PRUEBA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
