
package pms;

import code.DBclass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javaswingdev.Notification;
import javax.swing.JOptionPane;


public class admin extends javax.swing.JFrame {
    DBclass dbo;
    
    public admin() {
        initComponents();
        dbo=new DBclass();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel2 = new keeptoo.KGradientPanel();
        panelRound2 = new style.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        comborole = new combobox.Combobox();
        myButton1 = new button.MyButton();
        myButton2 = new button.MyButton();
        myButton3 = new button.MyButton();
        myButton5 = new button.MyButton();
        panelGradient1 = new raven.panel.PanelGradient();
        ssupplier_c = new javax.swing.JCheckBox();
        s_payment = new javax.swing.JCheckBox();
        sfactory = new javax.swing.JCheckBox();
        s_rate = new javax.swing.JCheckBox();
        suser = new javax.swing.JCheckBox();
        sroute = new javax.swing.JCheckBox();
        scollectoc = new javax.swing.JCheckBox();
        sfactorys = new javax.swing.JCheckBox();
        s_daily = new javax.swing.JCheckBox();
        sadmin = new javax.swing.JCheckBox();
        txtusername = new textfield.TextField();
        txtpassword = new textfield.TextField();
        txtcol = new textfield.TextField();
        textFieldAnimation1 = new swing.TextFieldAnimation();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel2.setkEndColor(new java.awt.Color(34, 59, 68));
        kGradientPanel2.setkStartColor(new java.awt.Color(34, 59, 68));

        panelRound2.setBackground(new java.awt.Color(34, 59, 68));
        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(50);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ADMIN PANEL");

        comborole.setBackground(new java.awt.Color(34, 59, 68));
        comborole.setForeground(new java.awt.Color(255, 255, 255));
        comborole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Collector", " " }));
        comborole.setSelectedIndex(-1);
        comborole.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comborole.setLabeText("ROLE");

        myButton1.setBackground(new java.awt.Color(34, 59, 68));
        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/add_48px.png"))); // NOI18N
        myButton1.setText("Save");
        myButton1.setColorClick(new java.awt.Color(255, 255, 255));
        myButton1.setColorOver(new java.awt.Color(0, 102, 102));
        myButton1.setRadius(40);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

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

        myButton3.setBackground(new java.awt.Color(34, 59, 68));
        myButton3.setForeground(new java.awt.Color(255, 255, 255));
        myButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/delete_48px.png"))); // NOI18N
        myButton3.setText("Delete");
        myButton3.setBorderColor(new java.awt.Color(255, 51, 0));
        myButton3.setColorClick(new java.awt.Color(255, 255, 255));
        myButton3.setColorOver(new java.awt.Color(255, 51, 0));
        myButton3.setRadius(40);
        myButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton3ActionPerformed(evt);
            }
        });

        myButton5.setBackground(new java.awt.Color(34, 59, 68));
        myButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/close_window_26px.png"))); // NOI18N
        myButton5.setBorderColor(new java.awt.Color(0, 0, 0));
        myButton5.setColorClick(new java.awt.Color(0, 0, 0));
        myButton5.setColorOver(new java.awt.Color(0, 0, 0));
        myButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton5ActionPerformed(evt);
            }
        });

        panelGradient1.setBackground(new java.awt.Color(0, 102, 102));
        panelGradient1.setColorGradient(new java.awt.Color(0, 0, 0));
        panelGradient1.setRadius(50);

        ssupplier_c.setForeground(new java.awt.Color(255, 255, 255));
        ssupplier_c.setText("Supplier Control");
        ssupplier_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ssupplier_cActionPerformed(evt);
            }
        });
        panelGradient1.add(ssupplier_c);
        ssupplier_c.setBounds(160, 50, 120, 20);

        s_payment.setForeground(new java.awt.Color(255, 255, 255));
        s_payment.setText("payemnt");
        panelGradient1.add(s_payment);
        s_payment.setBounds(290, 80, 90, 20);

        sfactory.setForeground(new java.awt.Color(255, 255, 255));
        sfactory.setText("Factory");
        sfactory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sfactoryActionPerformed(evt);
            }
        });
        panelGradient1.add(sfactory);
        sfactory.setBounds(160, 20, 120, 20);

        s_rate.setForeground(new java.awt.Color(255, 255, 255));
        s_rate.setText("Rate");
        panelGradient1.add(s_rate);
        s_rate.setBounds(160, 80, 85, 20);

        suser.setForeground(new java.awt.Color(255, 255, 255));
        suser.setText("User");
        panelGradient1.add(suser);
        suser.setBounds(30, 20, 85, 20);

        sroute.setForeground(new java.awt.Color(255, 255, 255));
        sroute.setText("Route");
        panelGradient1.add(sroute);
        sroute.setBounds(30, 50, 85, 20);

        scollectoc.setForeground(new java.awt.Color(255, 255, 255));
        scollectoc.setText("Collector Control");
        scollectoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scollectocActionPerformed(evt);
            }
        });
        panelGradient1.add(scollectoc);
        scollectoc.setBounds(30, 80, 120, 20);

        sfactorys.setForeground(new java.awt.Color(255, 255, 255));
        sfactorys.setText("Facroty Supply");
        panelGradient1.add(sfactorys);
        sfactorys.setBounds(290, 20, 120, 20);

        s_daily.setForeground(new java.awt.Color(255, 255, 255));
        s_daily.setText("Daily Supply");
        panelGradient1.add(s_daily);
        s_daily.setBounds(290, 50, 100, 20);

        sadmin.setForeground(new java.awt.Color(255, 255, 255));
        sadmin.setText("Admin panel");
        sadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sadminActionPerformed(evt);
            }
        });
        panelGradient1.add(sadmin);
        sadmin.setBounds(30, 110, 120, 20);

        txtusername.setBackground(new java.awt.Color(34, 59, 68));
        txtusername.setForeground(new java.awt.Color(255, 255, 255));
        txtusername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtusername.setLabelText("Username");
        txtusername.setName(""); // NOI18N

        txtpassword.setBackground(new java.awt.Color(34, 59, 68));
        txtpassword.setForeground(new java.awt.Color(255, 255, 255));
        txtpassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtpassword.setLabelText("Password");
        txtpassword.setName(""); // NOI18N

        txtcol.setBackground(new java.awt.Color(34, 59, 68));
        txtcol.setForeground(new java.awt.Color(255, 255, 255));
        txtcol.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtcol.setLabelText("Collector ID");
        txtcol.setName(""); // NOI18N

        textFieldAnimation1.setBackground(new java.awt.Color(34, 59, 68));
        textFieldAnimation1.setForeground(new java.awt.Color(255, 255, 255));
        textFieldAnimation1.setAnimationColor(new java.awt.Color(41, 96, 0));
        textFieldAnimation1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(panelGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(116, 116, 116)
                .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textFieldAnimation1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcol, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comborole, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldAnimation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtcol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comborole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(panelGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
            try {
           // String userid=txtuserid.getText();
            String colid=txtcol.getText();
            String role =comborole.getSelectedItem().toString();
            String usernmae=txtusername.getText();
            String password=txtpassword.getText();
            
            
            
            boolean user=suser.isSelected();
            boolean route=sroute.isSelected();
            boolean collector_c=scollectoc.isSelected();
            boolean factory=sfactory.isSelected();
            boolean supplier_c=ssupplier_c.isSelected();
            boolean rate=s_rate.isSelected();
            boolean factory_s=sfactorys.isSelected();
            boolean daily_s=s_daily.isSelected();
            boolean pay=s_payment.isSelected();
            boolean adminp=sadmin.isSelected();
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345678");
            String sql="insert into user( c_id , username , password , role , user ,route , collector_c , factory , supplier_c , rate ,factory_s , daily_s , pay,admin_panel)values (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
            PreparedStatement stmt=con.prepareStatement(sql);
          //  stmt.setString(1, userid);
            stmt.setString(1, colid);
            stmt.setString(2, usernmae);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.setBoolean(5, user);
            stmt.setBoolean(6, route);
            stmt.setBoolean(7, collector_c);
            stmt.setBoolean(8, factory);
            stmt.setBoolean(9, supplier_c);
            stmt.setBoolean(10,rate );
            stmt.setBoolean(11, factory_s);
            stmt.setBoolean(12, daily_s);
            stmt.setBoolean(13, pay);
            stmt.setBoolean(14, adminp);
            stmt.executeUpdate();
            dbo.conclose();
            Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Role and Accses Created");
            noti.showNotification();
        } catch (Exception e) {
//             Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Role and Accses Created");
//            noti.showNotification();
                JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_myButton1ActionPerformed

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton2ActionPerformed

    private void myButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton3ActionPerformed

    private void myButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_myButton5ActionPerformed

    private void ssupplier_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ssupplier_cActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ssupplier_cActionPerformed

    private void sfactoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sfactoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sfactoryActionPerformed

    private void scollectocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scollectocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scollectocActionPerformed

    private void sadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sadminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sadminActionPerformed

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
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private combobox.Combobox comborole;
    private javax.swing.JLabel jLabel2;
    private keeptoo.KGradientPanel kGradientPanel2;
    private button.MyButton myButton1;
    private button.MyButton myButton2;
    private button.MyButton myButton3;
    private button.MyButton myButton5;
    private raven.panel.PanelGradient panelGradient1;
    private style.PanelRound panelRound2;
    private javax.swing.JCheckBox s_daily;
    private javax.swing.JCheckBox s_payment;
    private javax.swing.JCheckBox s_rate;
    private javax.swing.JCheckBox sadmin;
    private javax.swing.JCheckBox scollectoc;
    private javax.swing.JCheckBox sfactory;
    private javax.swing.JCheckBox sfactorys;
    private javax.swing.JCheckBox sroute;
    private javax.swing.JCheckBox ssupplier_c;
    private javax.swing.JCheckBox suser;
    private swing.TextFieldAnimation textFieldAnimation1;
    private textfield.TextField txtcol;
    private textfield.TextField txtpassword;
    private textfield.TextField txtusername;
    // End of variables declaration//GEN-END:variables
}
