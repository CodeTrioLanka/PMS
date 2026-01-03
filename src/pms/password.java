/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pms;


import code.DBclass;
import com.formdev.flatlaf.IntelliJTheme;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.*;
import javaswingdev.Notification;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ravin
 */
public class password extends javax.swing.JFrame {
    
    DBclass dbo;
   

    /**
     * Creates new form collector
     */
    public password() {
        initComponents();
        dbo=new DBclass();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        makeFrameDraggable(this);
       
       
    
        
    }
    
    public void makeFrameDraggable(JFrame frame) {
        final int[] mousePos = {0, 0}; 

        
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePos[0] = e.getX();
                mousePos[1] = e.getY();
            }
        });

       
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                
                int x = e.getXOnScreen() - mousePos[0];
                int y = e.getYOnScreen() - mousePos[1];
                frame.setLocation(x, y); 
            }
        });
    }
    
    public void pwdupdate(){
          try {
            String sql="update user set password='"+pass.getText()+"' where username='"+username.getText()+"'" ;
            dbo.connect();
            dbo.update(sql);
            dbo.conclose();
            
            username.setText("");
            pass.setText("");
           // JOptionPane.showMessageDialog(null,"Password changed");
            Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Password Chnaged");
            noti.showNotification();
        } catch (Exception e) {
             Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Password Change Failed ");
            noti.showNotification();
           // JOptionPane.showMessageDialog(null,e);
        }
    }
    
 
    
    
   
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        panelRound1 = new style.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        username = new textfield.TextField();
        pass = new textfield.TextField();
        myButton2 = new button.MyButton();
        myButton7 = new button.MyButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(34, 59, 68));
        kGradientPanel1.setkStartColor(new java.awt.Color(34, 59, 68));

        panelRound1.setBackground(new java.awt.Color(34, 59, 68));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(50);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Password");

        username.setBackground(new java.awt.Color(34, 59, 68));
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        username.setLabelText("Username");
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameKeyReleased(evt);
            }
        });

        pass.setBackground(new java.awt.Color(34, 59, 68));
        pass.setForeground(new java.awt.Color(255, 255, 255));
        pass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pass.setLabelText("Password");

        myButton2.setBackground(new java.awt.Color(34, 59, 68));
        myButton2.setForeground(new java.awt.Color(255, 255, 255));
        myButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/update_48px.png"))); // NOI18N
        myButton2.setText("Update");
        myButton2.setBorderColor(new java.awt.Color(255, 204, 153));
        myButton2.setColorClick(new java.awt.Color(255, 255, 255));
        myButton2.setColorOver(new java.awt.Color(255, 204, 153));
        myButton2.setRadius(40);
        myButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton2ActionPerformed(evt);
            }
        });

        myButton7.setBackground(new java.awt.Color(34, 59, 68));
        myButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/close_window_26px.png"))); // NOI18N
        myButton7.setBorderColor(new java.awt.Color(0, 0, 0));
        myButton7.setColorClick(new java.awt.Color(0, 0, 0));
        myButton7.setColorOver(new java.awt.Color(0, 0, 0));
        myButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(myButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(myButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
        pwdupdate();
    }//GEN-LAST:event_myButton2ActionPerformed

    private void myButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton7ActionPerformed
        this.dispose();
    }//GEN-LAST:event_myButton7ActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        
    }//GEN-LAST:event_usernameActionPerformed

    private void usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyReleased
    
    }//GEN-LAST:event_usernameKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     IntelliJTheme.setup(password.class.getResourceAsStream("/template.theme.json"));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new password().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private button.MyButton myButton2;
    private button.MyButton myButton7;
    private style.PanelRound panelRound1;
    private textfield.TextField pass;
    private textfield.TextField username;
    // End of variables declaration//GEN-END:variables
}
