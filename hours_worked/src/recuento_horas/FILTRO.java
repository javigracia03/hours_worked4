/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package recuento_horas;

import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author javie
 */
public class FILTRO extends PANTALLA {

    /**
     * Creates new form FILTRO
     */
    
    protected String[] filtro;
    protected JComboBox<String>[] desplegables;
    private Trabajadores trab = new Trabajadores();
    
    
    public FILTRO() {
        
        
        
        initComponents();
        text_horas.setVisible(false);
           
        this.setPreferredSize(new Dimension(907, 550));
        
       
    
    
    }
    public void setFiltro(String... f){
    
        this.filtro = f;
    }
    
    public void setDesplegables(JComboBox<String>... des){
    
        this.desplegables = des;
    
    }
    

    public void recogerDatos(){
        
        
        
        for (int i=0; i<=desplegables.length-1; i++ ){
        
            String input = filtro[i];
            System.out.println(input);
            
            switch(input){
             
                case "nombre": trab.setNombre(desplegables[i].getSelectedItem().toString()); break;
                case "actividad": trab.setActividad(desplegables[i].getSelectedItem().toString()); break;
                case "proyecto": trab.setProyecto(desplegables[i].getSelectedItem().toString());  break;
                case "accion": trab.setAccion(desplegables[i].getSelectedItem().toString());  break;
                case "modelo": trab.setModelo(desplegables[i].getSelectedItem().toString());  break;
                case "mes": trab.setMes(Integer.parseInt(desplegables[i].getSelectedItem().toString()));  break;
                case "id": trab.setID(Integer.parseInt(desplegables[i].getSelectedItem().toString())); break;
               
            
            
            }
            
          
        
        }
    
          
    }
    
    public void filtrarDatos () throws ParseException, SQLException{
            
            
        DefaultTableModel model = (DefaultTableModel) tabla_filtrada.getModel();
        model.setRowCount(0);
        PreparedStatement prep_statement = null;
        ResultSet rs = null;
        
        Connection con = Conectar_db.conectDB();
        String sum = null;
        
        //Get current 
        int currentYear = Year.now().getValue();
        
        try {
            
            String inicio = "SELECT ID, FECHA, NOMBRE, ACCIÓN, ACTIVIDAD, HORA_INICIO, HORA_FIN, TIEMPO_DEDICADO, MES from ficha WHERE ";
            
            inicio = inicio + "ANO = " + currentYear + " AND ";
            
           
            
            for (int i=0; i<=filtro.length-2;i++){
                
                String option3 = filtro[i];
                
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
            
            String option4= filtro[filtro.length-1];
            
                switch(option4){

                    case "nombre": inicio = inicio + "nombre = ? ORDER BY FECHA;"; break;
                    case "actividad": inicio = inicio + "ACTIVIDAD = ? ORDER BY FECHA;"; break;
                    case "proyecto": inicio = inicio + "PROYECTO = ? ORDER BY FECHA;"; break;
                    case "modelo": inicio = inicio + "MODELO = ? ORDER BY FECHA;"; break;
                    case "accion": inicio = inicio + "ACCIÓN = ? ORDER BY FECHA ;"; break;
                    case "mes": inicio = inicio + "MES = ? ORDER BY FECHA;"; break;
                    case "id": inicio = inicio + "ID = ? ORDER BY FECHA;"; break;


                    }
            //System.out.println(inicio);
            prep_statement = con.prepareStatement(inicio);
            
             for (int i=0; i<=filtro.length-1;i++){
                int j = i +1;
                String option5 = filtro[i];
                
                switch(option5){
             
                case "nombre":  prep_statement.setString(j, trab.getNombre()); break;
                case "actividad":  prep_statement.setString(j, trab.getActividad()); break;
                case "proyecto":  prep_statement.setString(j, trab.getProyecto()); break;
                case "modelo":  prep_statement.setString(j, trab.getModelo()); break;
                case "accion":  prep_statement.setString(j, trab.getAccion()); break;
                case "mes":  prep_statement.setInt(j, trab.getMes()); System.out.println(trab.getMes());break;
                case "id":  prep_statement.setInt(j, trab.getID()); System.out.println(trab.getMes());break;
               
           
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
               
               DefaultTableModel tblModel = (DefaultTableModel) tabla_filtrada.getModel();
                
               tblModel.addRow(tbData); 
               
               
                 
                
            }
            
               trab.setSum(sum);
               
              System.out.println("Connection closed");
        } catch (SQLException ex) {
            Logger.getLogger(CONSULTAS.class.getName()).log(Level.SEVERE, null, ex);
            
         
        }finally {
            prep_statement.close();
            rs.close();
            con.close();
            System.out.println("Connection closed");
        }
        
  
    
    }    

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_filtrada = new javax.swing.JTable();
        atras_button = new javax.swing.JButton();
        filtrar_button = new javax.swing.JButton();
        export_button = new javax.swing.JButton();
        print_button = new javax.swing.JButton();
        text_horas = new javax.swing.JLabel();
        suma = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla_filtrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FECHA", "NOMBRE", "ACCIÓN", "ACTIVIDAD", "HORA INICIAL", "HORA FINAL", "HORAS TRABAJADAS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_filtrada);

        atras_button.setText("ATRÁS");
        atras_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atras_buttonActionPerformed(evt);
            }
        });

        filtrar_button.setText("FILTRAR");
        filtrar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrar_buttonActionPerformed(evt);
            }
        });

        export_button.setText("EXPORT");
        export_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export_buttonActionPerformed(evt);
            }
        });

        print_button.setText("PRINT");
        print_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_buttonActionPerformed(evt);
            }
        });

        text_horas.setText("HORAS TOTALES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(atras_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(export_button)
                        .addGap(55, 55, 55)
                        .addComponent(print_button)
                        .addGap(51, 51, 51))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(filtrar_button)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(text_horas)
                        .addGap(18, 18, 18)
                        .addComponent(suma, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(filtrar_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_horas)
                    .addComponent(suma, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(atras_button)
                    .addComponent(export_button)
                    .addComponent(print_button))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atras_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atras_buttonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        PANEL_CONSULTAS p1 = new PANEL_CONSULTAS();
        p1.setVisible(true);  
    }//GEN-LAST:event_atras_buttonActionPerformed

    private void print_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_buttonActionPerformed
        try {
            // TODO add your handling code here:
            if (tabla_filtrada.getRowCount() != 0){
            tabla_filtrada.print();
            } else JOptionPane.showMessageDialog(null, "LA TABLA ESTÁ VACÍA INTÉNTELO DE NUEVO");
        } catch (PrinterException ex) {
            Logger.getLogger(FILTRO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "HA HABIDO UN ERROR AL IMPRIMIR");
        }
    }//GEN-LAST:event_print_buttonActionPerformed

    private void filtrar_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrar_buttonActionPerformed
        // TODO add your handling code here:
         recogerDatos();
        try {
            filtrarDatos();
            Utilidades.mostrarSuma(trab, suma);
            text_horas.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FILTRO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_filtrar_buttonActionPerformed

    private void export_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_buttonActionPerformed
        try {
            // TODO add your handling code here:
            export();
        } catch (IOException ex) {
            Logger.getLogger(FILTRO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_export_buttonActionPerformed

    
    public void export() throws FileNotFoundException, IOException {
        String fileDictName = "";
         XSSFWorkbook workbook;
         TableModel model = tabla_filtrada.getModel(); 
 

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
                        
                        excelCell.setCellValue(tabla_filtrada.getColumnName(j));
                        
                        if (j == model.getColumnCount()-1){
                            
                            excelCell = excelRow.createCell(j+2);
                            excelCell.setCellValue("SUMA TOTAL");
                            excelCell = excelRow.createCell(j+3);
                            excelCell.setCellValue(suma.getText());
                            
            }
                 
                       
                    }
            for (int i = 1; i <= model.getRowCount(); i++) {
                    XSSFRow excelRow2 = exampleSheet.createRow(i);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        
                       XSSFCell excelCell = excelRow2.createCell(j);
                        
                        excelCell.setCellValue(tabla_filtrada.getValueAt(i-1, j).toString());
                        
 
                 
                       
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
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FILTRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FILTRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FILTRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FILTRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FILTRO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton atras_button;
    private javax.swing.JButton export_button;
    protected javax.swing.JButton filtrar_button;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton print_button;
    private javax.swing.JLabel suma;
    protected javax.swing.JTable tabla_filtrada;
    private javax.swing.JLabel text_horas;
    // End of variables declaration//GEN-END:variables
}
