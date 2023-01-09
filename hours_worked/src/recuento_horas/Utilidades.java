/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recuento_horas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.Path;
import java.util.Calendar;
import static javax.management.Query.lt;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;




/**
 *
 * @author javie
 */
public class Utilidades {
    

    public static Date hora_to_date(String fecha) throws ParseException{
    

    
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    SimpleDateFormat short_format =new SimpleDateFormat("HH:mm"); 
    Date hora_corta = short_format.parse(fecha);
    
    
    System.out.println(fecha+"\t"+hora_corta);  
    
    return hora_corta;
    
    }
    

    
    public static String convert_milis(long miliseconds) {
    
    long seconds = miliseconds/1000;
    long s = seconds % 60;
    long m = (seconds / 60) % 60;
    long h = (seconds / (60 * 60)) % 24;
    
    String res = String.format("%02d:%02d:%02d", h,m,s);
    String res1 = String.format("%d:%02d:%02d", h,m,s);
    
   
    
    return res1;
    }
    
    public static long horaToMilis(String hora) throws ParseException{
        
        Date hora_comofecha = hora_to_date(hora);
        
        //System.out.println("HORA24: " + hora_comofecha);
        
        //System.out.println("HORARA" + hora);
    
        long hora_milis = hora_comofecha.getTime();
        
        hora_milis = hora_milis + 3600000;
        
        //System.out.println("EN MILIS" + hora_milis);
    
        return hora_milis;
    
    }
    
    public static String milisToHoras_suma(long a){
    
    
        Duration duration = Duration.ofMillis(a);
        
        
    
        long HH = duration.toHours();
        long MM = duration.toMinutesPart();
        long SS = duration.toSecondsPart();
        String timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);
        
         System.out.println(timeInHHMMSS);
        
        return timeInHHMMSS;

    }
    
    
    public static String resta_horas(String ini, String fin) throws ParseException{
    
        long ini_milis = horaToMilis(ini);
        long fin_milis = horaToMilis(fin);
        
        long resta = fin_milis - ini_milis;
        
        String horas_totales = convert_milis(resta);
        
        return horas_totales;
    }
    
    public static String sumatiempo(String a, String b) throws ParseException{
        
        if (b==null){
            
            return a;
        }
        
       long a_mili = horaToMilis(a);
       long b_mili = horaToMilis(b);
       long suma = a_mili + b_mili;
       
       
       
       System.out.println("long" + a_mili);
      System.out.println("longb" + b_mili);
       System.out.println("longsuma" + suma);
       
       String res = milisToHoras_suma(suma);
       
       return res;
    
    }
    
    public void recogerDatos(ArrayList<JComboBox<String>> a){
        
        Trabajadores t1 = new Trabajadores();
        
        for (int i=0; i<=a.size(); i++ ){
        
            String input = a.get(i).toString();
            
            
            switch(input){
             
                case "nombre": t1.setNombre(a.get(i).getSelectedItem().toString()); break;
                case "actividad": t1.setActividad(a.get(i).getSelectedItem().toString()); break;
                case "proyecto": t1.setProyecto(a.get(i).getSelectedItem().toString()); break;
                case "accion": t1.setAccion(a.get(i).getSelectedItem().toString()); break;
                case "modelo": t1.setModelo(a.get(i).getSelectedItem().toString()); break;
                case "id": t1.setID(Integer.parseInt(a.get(i).getSelectedItem().toString())); break;
               
            
            
            }
        
        }
    
    
    }
        public static Trabajadores recogerDatos2(Trabajadores t1,JComboBox<String>... a){
        
        
        
        for (int i=0; i<=a.length-1; i++ ){
        
            String input = t1.getFiltro().get(i);
            System.out.println(input);
            
            switch(input){
             
                case "nombre": t1.setNombre(a[i].getSelectedItem().toString()); break;
                case "actividad": t1.setActividad(a[i].getSelectedItem().toString()); break;
                case "proyecto": t1.setProyecto(a[i].getSelectedItem().toString());  break;
                case "accion": t1.setAccion(a[i].getSelectedItem().toString());  break;
                case "modelo": t1.setModelo(a[i].getSelectedItem().toString());  break;
                case "mes": t1.setMes(Integer.parseInt(a[i].getSelectedItem().toString()));  break;
                case "id": t1.setID(Integer.parseInt(a[i].getSelectedItem().toString())); break;
               
            
            
            }
            
          
        
        }
    
          return t1;
    }
        
    public static Trabajadores recogerDatos2(Trabajadores t1,JTextField... a){
        
        
        
        for (int i=0; i<=a.length-1; i++ ){
        
            String input = t1.getFiltro().get(i);
            System.out.println(input);
            
            switch(input){
             
                case "nombre": t1.setNombre(a[i].getText()); break;
                case "actividad": t1.setActividad(a[i].getText()); break;
                case "proyecto": t1.setProyecto(a[i].getText());  break;
                case "accion": t1.setAccion(a[i].getText());  break;
                case "modelo": t1.setModelo(a[i].getText());  break;
                case "mes": t1.setMes(Integer.parseInt(a[i].getText()));  break;
                case "id": t1.setID(Integer.parseInt(a[i].getText().toString())); break;
               
            
            
            }
            
          
        
        }
    
          return t1;
    }
        
        public static Trabajadores filtrarDatos (Trabajadores t1, JTable table1) throws ParseException, SQLException{
            
            
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        PreparedStatement prep_statement = null ;
        ResultSet rs = null;
        Connection con = Conectar_db.conectDB();
        String sum = null;
      
        
        try {
            
            String inicio = "SELECT ID, FECHA, NOMBRE, ACCIÓN, ACTIVIDAD, HORA_INICIO, HORA_FIN, TIEMPO_DEDICADO, MES from ficha WHERE ";
            
            ArrayList<String> filtro = t1.getFiltro();
            
            
            /*for (int i=0; i<=filtro.size()-1;i++){
            
                String option = filtro.get(i);
                
                switch(option){
             
                case "nombre": inicio = inicio + "NOMBRE, "; break;
                case "actividad": inicio = inicio + "ACTIVIDAD, "; break;
                case "proyecto": inicio = inicio + "PROYECTO, "; break;
                case "modelo": inicio = inicio + "MODELO, "; break;
                case "accion": inicio = inicio + "FECHA, "; break;
                case "accion": inicio = inicio + "HORA_INICIO, "; break;
                case "accion": inicio = inicio + "HORA_FIN, "; break;
                case "accion": inicio = inicio + "TIEMPO_DEDICADO, "; break;
                case "accion": inicio = inicio + "EMPRESA "; break;
                case "accion": inicio = inicio + "INTERLOCUTOR "; break;
                case "accion": inicio = inicio + "TEMAS_TRATADOS"; break;
                case "accion": inicio = inicio + "ACCIÓN, "; break;
                
           
                }
                
                
            }
        
             
            
            String option2= filtro.get(filtro.size()-1);
            
                switch(option2){

                    case "nombre": inicio = inicio + "NOMBRE "; break;
                    case "actividad": inicio = inicio + "ACTIVIDAD "; break;
                    case "proyecto": inicio = inicio + "PROYECTO "; break;
                    case "modelo": inicio = inicio + "MODELO "; break;
                    case "accion": inicio = inicio + "ACCIÓN "; break;


                    }
                
              inicio = inicio + "FROM ficha WHERE ";
        
            
            */
           
            
            for (int i=0; i<=filtro.size()-2;i++){
                
                String option3 = filtro.get(i);
                
                switch(option3){
             
                case "nombre": inicio = inicio + "nombre = ? AND "; break;
                case "actividad": inicio = inicio + "ACTIVIDAD = ? AND "; break;
                case "proyecto": inicio = inicio + "PROYECTO = ? AND "; break;
                case "modelo": inicio = inicio + "MODELO = ? AND "; break;
                case "accion": inicio = inicio + "ACCIÓN = ? AND "; break;
                case "mes": inicio = inicio + "MES = ? AND "; break;
                case "id": inicio = inicio + "ID = ? AND "; break;
                
           
                }
                
                
                
                
            }
            
            String option4= filtro.get(filtro.size()-1);
            
                switch(option4){

                    case "nombre": inicio = inicio + "nombre = ?;"; break;
                    case "actividad": inicio = inicio + "ACTIVIDAD = ?;"; break;
                    case "proyecto": inicio = inicio + "PROYECTO = ?;"; break;
                    case "modelo": inicio = inicio + "MODELO = ?;"; break;
                    case "accion": inicio = inicio + "ACCIÓN = ?;"; break;
                    case "mes": inicio = inicio + "MES = ?;"; break;
                    case "id": inicio = inicio + "ID = ?;"; break;


                    }
            //System.out.println(inicio);
            prep_statement = con.prepareStatement(inicio);
            
             for (int i=0; i<=filtro.size()-1;i++){
                int j = i +1;
                String option5 = filtro.get(i);
                
                switch(option5){
             
                case "nombre":  prep_statement.setString(j, t1.getNombre()); break;
                case "actividad":  prep_statement.setString(j, t1.getActividad()); break;
                case "proyecto":  prep_statement.setString(j, t1.getProyecto()); break;
                case "modelo":  prep_statement.setString(j, t1.getModelo()); break;
                case "accion":  prep_statement.setString(j, t1.getAccion()); break;
                case "mes":  prep_statement.setInt(j, t1.getMes()); System.out.println(t1.getMes());break;
                case "id":  prep_statement.setInt(j, t1.getID()); System.out.println(t1.getMes());break;
               
           
                }
                
                //System.out.println(prep_statement);
                
            }
            
            
            
            
            
            
            rs = prep_statement.executeQuery();
            
            
            
            
            
            while (rs.next()){
            
                
                //String name = rs.getString("nombre");
                String date = rs.getString("FECHA");
                String name = rs.getString("NOMBRE");
                String actividad = rs.getString("ACTIVIDAD");
             
                String accion = rs.getString("ACCIÓN");

                String hora_in = rs.getString("HORA_INICIO");
                String hora_fin = rs.getString("HORA_FIN");
                String time_dedicated = rs.getString("TIEMPO_DEDICADO");
                int mes = rs.getInt("MES");
                int ID = rs.getInt("ID");
                
                
                System.out.println("tiempo dedicado" + time_dedicated);
                
                sum = Utilidades.sumatiempo(time_dedicated, sum);
                
                
                System.out.println("SUMA: " + sum);

                //String total_hours = String.valueOf(rs.getDouble("suma"));
                
               Object tbData[]={ID, date, name, accion, actividad, hora_in, hora_fin, time_dedicated};
               
               DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();
                
               tblModel.addRow(tbData); 
               
               
                 
                
            }
            
            t1.setSum(sum);
              
              
        } catch (SQLException ex) {
            Logger.getLogger(CONSULTAS.class.getName()).log(Level.SEVERE, null, ex);
            
         
        } finally {
            prep_statement.close();
            rs.close();
            con.close();
            System.out.println("Connection closed");
        }
        
      return t1;
    
    }    
        
    public static void mostrarSuma(Trabajadores t2, JLabel a){
    
        a.setText(t2.getSum());
    
    }   
    
    public static void export(JTable jTable1) throws FileNotFoundException, IOException {
        String fileDictName = "";
         XSSFWorkbook workbook;
         TableModel model = jTable1.getModel(); 
 

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open the file"); //name for chooser
        javax.swing.filechooser.FileFilter filter = new FileNameExtensionFilter("Files", ".xlsx"); //filter to show only that
        fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
        fileChooser.setVisible(true);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return;
        }

        File file = new File(fileDictName);
        if (file.getAbsoluteFile().exists() == false) {
            
            workbook = new XSSFWorkbook();
            XSSFSheet exampleSheet = workbook.createSheet("1");
            
            
            XSSFRow excelRow = exampleSheet.createRow(0);
            for (int j = 0; j < model.getColumnCount(); j++) {
                
                        XSSFCell excelCell = excelRow.createCell(j);
                        
                        excelCell.setCellValue(jTable1.getColumnName(j));
                        
                        /*if (j == model.getColumnCount()-1){
                            
                            excelCell = excelRow.createCell(j+2);
                            excelCell.setCellValue("SUMA TOTAL");
                            excelCell = excelRow.createCell(j+3);
                            excelCell.setCellValue(suma.getText());
                            
            }*/
                 
                       
                    }
            for (int i = 1; i < model.getRowCount(); i++) {
                    XSSFRow excelRow2 = exampleSheet.createRow(i);
                    for (int j = 0; j < model.getColumnCount(); j++) {
              
                       XSSFCell excelCell = excelRow2.createCell(j);
                        
                        excelCell.setCellValue(jTable1.getValueAt(i, j).toString());
                        
 
                 
                       
                    }
            }

            try {
                    //Write the workbook in file system
                FileOutputStream out = new FileOutputStream(file + ".xlsx"); 
                workbook.write(out);
                JOptionPane.showMessageDialog(null, "Exported Successfully");
                out.close();
            } catch (Exception err){
            
                JOptionPane.showMessageDialog(null, "HA HABIDO UN ERROR AL EXPORTAR, INTÉNTELO DE NUEVO");
            
            }
            
                    
            
            
        } else {
            // Sheet already exists
            System.out.println("File already exist");
        }
    
    }

public static void export(JTable jTable1, JLabel suma ) throws FileNotFoundException, IOException {
        String fileDictName = "";
         XSSFWorkbook workbook;
         TableModel model = jTable1.getModel(); 
 

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open the file"); //name for chooser
        javax.swing.filechooser.FileFilter filter = new FileNameExtensionFilter("Files", ".xlsx"); //filter to show only that
        fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
        fileChooser.setVisible(true);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return;
        }

        File file = new File(fileDictName);
        if (file.exists() == false) {
            workbook = new XSSFWorkbook();
            XSSFSheet exampleSheet = workbook.createSheet("1");
            
            
            XSSFRow excelRow = exampleSheet.createRow(0);
            for (int j = 0; j < model.getColumnCount(); j++) {
                
                        XSSFCell excelCell = excelRow.createCell(j);
                        
                        excelCell.setCellValue(jTable1.getColumnName(j));
                        
                        if (j == model.getColumnCount()-1){
                            
                            excelCell = excelRow.createCell(j+2);
                            excelCell.setCellValue("SUMA TOTAL");
                            excelCell = excelRow.createCell(j+3);
                            excelCell.setCellValue(suma.getText());
                            
            }
                 
                       
                    }
            for (int i = 1; i < model.getRowCount(); i++) {
                    XSSFRow excelRow2 = exampleSheet.createRow(i);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        int h=j+1;
                       XSSFCell excelCell = excelRow2.createCell(j);
                        
                        excelCell.setCellValue(jTable1.getValueAt(i, j).toString());
                        
 
                 
                       
                    }
            }

            try {
                    //Write the workbook in file system
                FileOutputStream out = new FileOutputStream(file + ".xlsx"); 
                workbook.write(out);
                JOptionPane.showMessageDialog(null, "Exported Successfully");
                out.close();
            } catch (Exception err){
            
                JOptionPane.showMessageDialog(null, "HA HABIDO UN ERROR AL EXPORTAR, INTÉNTELO DE NUEVO");
            
            }
            
                    
            
            
        } else {
            // Sheet already exists
            System.out.println("File already exist");
        }
}

public static void export3(JTable table) throws IOException{
   Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Sheet1");
for (int i = 0; i < table.getRowCount(); i++) {
    Row row = sheet.createRow(i);
    for (int j = 0; j < table.getColumnCount(); j++) {
        Cell cell = row.createCell(j);
        cell.setCellValue(table.getValueAt(i, j).toString());
    }
}
JFileChooser fileChooser = new JFileChooser();
int result = fileChooser.showSaveDialog(table);
if (result == JFileChooser.APPROVE_OPTION) {
    File file = fileChooser.getSelectedFile();
    try (FileOutputStream fileOut = new FileOutputStream(file)) {
        workbook.write(fileOut);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

public static int ano_desdeFecha(String fecha) throws ParseException{


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = sdf.parse(fecha);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    //String str = String.valueOf(year);
    //System.out.println("Year: " + year);
    return year;
}

  
        
    
    
    
    
    
    
    //public static String restar_horas()




}
