/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recuento_horas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author javie
 */
public class Trabajadores {
    
    private String nombre;
    private double horas_diarias;
    private double horas_anuales;
    private String fecha_corta;
    private Date fecha_larga;
    private String hora_inicial;
    private String hora_final;
    private String horas_trabajadas;
    private String proyecto;
    private String actividad;
    private String accion;
    private String modelo;
    private String empresa;
    private String interlocutor;
    private String temas;
    private int mes;
    private int ano;
    private ArrayList<String> filtro = new ArrayList<String>();
    private String sum;
    private String usuario;
    private String password;
    private int ID;

    
    
    
    
    
    public Trabajadores (String name){
        nombre = name;
    
    }
    
    public Trabajadores(){
    
    
    }
    
    public void setUsername(String usu){
    
        this.usuario = usu;
    
    }
    
    public void setPassword(String password){
    
        this.password = password;
    
    }
    
    public void setMes(int mes){
    
        this.mes=mes;
    
    }
    
    public void setAno(int ano){
    
        this.ano=ano;
    
    }
    public void setHoras(double num){
    
        horas_diarias = num;
    
    }
    public void setNombre(String nombre){
    
        this.nombre = nombre;
    
    }    
    
    public void setHorasAnuales(double horas){
    
        this.horas_anuales = horas;
    
    }    
    
    public void setFechaCorta(String fecha){
    
        this.fecha_corta = fecha;
    
    
    }
    
    public void setFechaLarga(Date fecha){
    
        this.fecha_larga = fecha;
    
    
    }

    public void setHoraInicial(String hora){
    
        this.hora_inicial = hora;
    
    
    }    
    
    
    public void setHoraFinal(String hora){
    
        this.hora_final = hora;
    
    
    } 
    public void setEmpresa(String hora){
    
        this.empresa = hora;
   
    
    } 
    public void setModelo(String hora){
    
        this.modelo = hora;
    
    
    } 
    public void setInterlocutor(String hora){
    
        this.interlocutor = hora;
    
    
    } 
    public void setTemas(String hora){
    
        this.temas = hora;
    
    
    } 
    public void setAccion(String hora){
    
        this.accion = hora;
    
    
    } 
    public void setActividad(String hora){
    
        this.actividad = hora;
    
    
    } 
    public void setProyecto(String hora){
    
        this.proyecto = hora;
    
    
    } 
    
    public void setFiltro(String... a){
    
        for(String i : a){
        
            filtro.add(i);
        
        
        }
    }
        
    public void setID(int a){
    
        this.ID= a;
        
        
    }    
        
    
    
        public void addFiltro(String a){
    
      
        
            filtro.add(a);
        
        
        
    }
        
        
    public void setSum(String sum){
    
        this.sum = sum;
    
    
    }
    
    
    
    
    
    
    public String getSum(){
    
    
        return this.sum;
    
    }
    
    public String getHoraInicial(){
    
        return this.hora_inicial;
    
    }
    
    public int getMes() throws ParseException{
        
        
        return this.mes ;
    
    
    }
    public int getAno() throws ParseException{
        
        
        return this.ano ;
    
    
    }    
    
    
    
    
    public String getHoraFinal(){
    
        return this.hora_final;
    
    }    
    
    public String getNombre(){
    
        return this.nombre;
    }
    
    public String getProyecto(){
    
        return this.proyecto;
    }
    public String getActividad(){
    
        return this.actividad;
    }
    public String getAccion(){
    
        return this.accion;
    }
    public String getEmpresa(){
    
        return this.empresa;
    }
    public String getModelo(){
    
        return this.modelo;
    }
    public String getInterlocutor(){
    
        return this.interlocutor;
    }
    public String getTemas(){
    
        return this.temas;
    }
    
    public String getUsuario(){
    
        return this.usuario;
    }
    
    public String getPassword(){
    
        return this.password;
    }
    
    public int getID(){
    
        return this.ID;
    }
    
    
    
    
    
    

    public double getHoras(){
    
        return this.horas_diarias;
    }
    
    public double getHorasAnuales(){
    
        return this.horas_anuales;
    }
    
    public String getFechaCorta(){
        
        return this.fecha_corta;
    
    }
    
    public Date getFechaLarga(){
        
        return this.fecha_larga;
    
    }
    
    public String getHorasTrabajadas() throws ParseException{
    
        horas_trabajadas = Utilidades.resta_horas(hora_inicial, hora_final);
    
        return horas_trabajadas;
    
    }
    
    public ArrayList<String> getFiltro(){
    
        return this.filtro;
    
    
    }
    
    
    public int mes_desdeFecha(){
    

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.fecha_larga);
        this.mes = calendar.get(Calendar.MONTH)+1;
        return this.mes;
    }
    
    public int ano_desdefecha() throws ParseException{
        
        return Utilidades.ano_desdeFecha(fecha_corta);
        
    }
    
    public void recogerdatos(){
    
        
    
    
    
    }
    
    
}
