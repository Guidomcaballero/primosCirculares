/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciopractico.ifelse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Guido Caballero
 */
public class ViewEscritorio extends javax.swing.JFrame {
    
    /**
     * Creates new form ViewEscritorio
     */
    public ViewEscritorio() {
        initComponents();
        jtPrimosCirculares.setLineWrap(true);
        jtPrimosCirculares.setWrapStyleWord(true);
    }
    
    public static boolean esPrimo(int n){
        int contador = 2;
        boolean primo = true;
        if(n == 1)
            return true;
        if((n%contador) == 0)
            return false;
        else{    
            while((primo)&&(contador!=n)){
                if ((n%contador) == 0)
                    primo = false;
                contador++;
            }
            return primo;
        }
    }
    public static boolean esPrimoCircular(int n){
        String convertido = n + "";
        boolean circular = true;
        ArrayList<String> rotaciones = new ArrayList<>(); 
        if(!convertido.contains("0")&&!convertido.contains("2")&&!convertido.contains("4")&&!convertido.contains("6")&&!convertido.contains("8")){   
            for(int i=0; i<=convertido.length()-1; i++){
            convertido = convertido.charAt(convertido.length()-1)+ convertido.substring(0,convertido.length()-1);
            rotaciones.add(convertido);
            }
            for(String cont:rotaciones){
                int convAux = Integer.valueOf(cont);
                if(!esPrimo(convAux))
                    circular = false;
            }
        }else
            circular = false;
        return circular;
    }
    public static ArrayList rotaciones(int numero){
        String convertido = numero + "";
        ArrayList<Integer> numerosRotados = new ArrayList<>(); 
                for(int i=0; i<=convertido.length()-1; i++){
                    convertido = convertido.charAt(convertido.length()-1)+ convertido.substring(0,convertido.length()-1);
                    numerosRotados.add(Integer.valueOf(convertido));
                }
        return numerosRotados;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbGenerar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPrimosCirculares = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbGenerar.setText("Generar");
        jbGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGenerarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Corbel Light", 0, 22)); // NOI18N
        jLabel1.setText("Generador de numeros primos circulares");

        jtPrimosCirculares.setColumns(20);
        jtPrimosCirculares.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 18)); // NOI18N
        jtPrimosCirculares.setRows(5);
        jScrollPane1.setViewportView(jtPrimosCirculares);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jbGenerar))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jbGenerar)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGenerarActionPerformed
        String salida = "los siguientes conjuntos son primos pares menores a un millon: \n";
        Primo p = null;
        PrimoData pd = null;
        ArrayList<Integer> rotaciones = new ArrayList<>();
        ArrayList<Primo> primos =new ArrayList<>();
        boolean e = false;
        try{
            Conexion con = new Conexion();
            pd = new PrimoData(con);
            pd.crearTabla();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewEscritorio.class.getName()).log(Level.SEVERE, null, ex);
            e = true;
        }
        if(!e){
            jtPrimosCirculares.setText("Aguarde un instante, se esta llevando a cabo la acción...");

            for(int i = 0;i<=199933;i++){
                p = new Primo(i);
                if (esPrimoCircular(i)&&!pd.primoEsta(p)){
                    rotaciones = rotaciones(i);
                    for(int j:rotaciones){
                        p = new Primo(j);
                        pd.ingresarPrimo(p);
                    }
                } 
            }
        }
        primos =  (ArrayList<Primo>) pd.obtenerPrimos();
        for(Primo i: primos)
        salida += i.toString();
        jtPrimosCirculares.setText(salida);
    }//GEN-LAST:event_jbGenerarActionPerformed

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
            java.util.logging.Logger.getLogger(ViewEscritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEscritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEscritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEscritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEscritorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbGenerar;
    private javax.swing.JTextArea jtPrimosCirculares;
    // End of variables declaration//GEN-END:variables
}
