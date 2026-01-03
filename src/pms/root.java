/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pms;


import code.DBclass;
import com.formdev.flatlaf.IntelliJTheme;
import java.sql.*;
import javaswingdev.Notification;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import table.TableCustom;

/**
 *
 * @author ravin
 */
public class root extends javax.swing.JFrame {
    
    DBclass dbo;
   

    /**
     * Creates new form collector
     */
    public root() {
        initComponents();
        dbo=new DBclass();
        tableload();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
       
    
        
    }
    
    public void tableload(){
        try {
            String sql="select root_id as ID,root_name as Root from root";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            dbo.conclose();
            
        } catch (Exception e) {
        }
    }
    
    public void tabledata(){
        int r=table1.getSelectedRow();
        String id=table1.getValueAt(r, 0).toString();
        String name=table1.getValueAt(r, 1).toString();
        
        txtid.setText(id);
        txtroot.setText(name);
    }
    
    public void search(){
        //String srch=txtsrch.getText();
        
        try {
            String sql="select * from root where root_name LIKE '%"+txtsrch.getText()+"%' or root_id LIKE '%"+txtsrch.getText()+"%' ";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
        }
    }
    
    private void insert(){
         try {
            String sql="insert into root(root_id,root_name)values('"+txtid.getText()+"','"+txtroot.getText()+"')";
            dbo.connect();
            dbo.insert(sql);
            dbo.conclose();
            
           // JOptionPane.showMessageDialog(null,"Added");
            Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Register Succesfully ");
            noti.showNotification();
            tableload();
            txtid.setText("");
            txtroot.setText("");
            txtroot.requestFocus();
            
        } catch (Exception e) {
             Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong ");
            noti.showNotification();
            
           // JOptionPane.showMessageDialog(null, e);
        }
    }
    private void update(){
      try {
            String sql="update root set root_name='"+txtroot.getText()+"' where root_id='"+txtid.getText()+"'";
            dbo.connect();
            dbo.update(sql);
            dbo.conclose();
           // JOptionPane.showMessageDialog(null, "Updated");
            Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Updated Succesfully ");
            noti.showNotification();
            tableload();
            txtroot.setText("");
            txtroot.requestFocus();
            txtid.setText("");
            
            
        } catch (Exception e) {
             Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong ");
            noti.showNotification();
           // JOptionPane.showMessageDialog(null, e);
        }  
    }
    
    private void delete(){
         try {
            int x=JOptionPane.showConfirmDialog(null, "Are You Sure");
            
            if(x == 0){
            String sql="delete from root where root_id='"+txtid.getText()+"'";
            dbo.connect();
            dbo.delete(sql);
            dbo.conclose();
            
           // JOptionPane.showMessageDialog(null, "Deleted");
            Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Deleted Succesfully ");
            noti.showNotification();
            tableload();
            txtroot.setText("");
            txtroot.requestFocus();
            txtid.setText("");
            }
        } catch (Exception e) {
             Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong ");
            noti.showNotification();
          //  JOptionPane.showMessageDialog(null, e);
        }
    }
    private void clear(){
        txtid.setText("");
         txtroot.setText("");
         txtid.requestFocus();
         txtsrch.setText("");
    }
        
        
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        panelRound1 = new style.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        txtsrch = new swing.TextFieldAnimation();
        txtid = new textfield.TextField();
        txtroot = new textfield.TextField();
        myButton1 = new button.MyButton();
        myButton2 = new button.MyButton();
        myButton3 = new button.MyButton();
        myButton4 = new button.MyButton();
        myButton5 = new button.MyButton();

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
        jLabel1.setText("ROUTE");

        table1.setBackground(new java.awt.Color(34, 59, 68));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        txtsrch.setBackground(new java.awt.Color(34, 59, 68));
        txtsrch.setForeground(new java.awt.Color(255, 255, 255));
        txtsrch.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        txtsrch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsrchMouseClicked(evt);
            }
        });

        txtid.setBackground(new java.awt.Color(34, 59, 68));
        txtid.setForeground(new java.awt.Color(255, 255, 255));
        txtid.setLabelText("ID");

        txtroot.setBackground(new java.awt.Color(34, 59, 68));
        txtroot.setForeground(new java.awt.Color(255, 255, 255));
        txtroot.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtroot.setLabelText("Route Name");

        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/add_48px.png"))); // NOI18N
        myButton1.setText("Insert");
        myButton1.setColor(new java.awt.Color(34, 59, 68));
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
        myButton2.setColor(new java.awt.Color(34, 59, 68));
        myButton2.setColorClick(new java.awt.Color(255, 255, 255));
        myButton2.setColorOver(new java.awt.Color(255, 204, 153));
        myButton2.setRadius(40);
        myButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton2ActionPerformed(evt);
            }
        });

        myButton3.setForeground(new java.awt.Color(255, 255, 255));
        myButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/delete_48px.png"))); // NOI18N
        myButton3.setText("Delete");
        myButton3.setBorderColor(new java.awt.Color(255, 51, 0));
        myButton3.setColor(new java.awt.Color(34, 59, 68));
        myButton3.setColorClick(new java.awt.Color(255, 255, 255));
        myButton3.setColorOver(new java.awt.Color(255, 51, 0));
        myButton3.setRadius(40);
        myButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton3ActionPerformed(evt);
            }
        });

        myButton4.setForeground(new java.awt.Color(255, 255, 255));
        myButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/clear_symbol_28px.png"))); // NOI18N
        myButton4.setText("Clear");
        myButton4.setBorderColor(new java.awt.Color(255, 153, 204));
        myButton4.setColor(new java.awt.Color(34, 59, 68));
        myButton4.setColorClick(new java.awt.Color(255, 255, 255));
        myButton4.setColorOver(new java.awt.Color(255, 153, 204));
        myButton4.setRadius(40);
        myButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton4ActionPerformed(evt);
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

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsrch, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtroot, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(230, 230, 230))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txtsrch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtroot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        tabledata();
    }//GEN-LAST:event_table1MouseClicked

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        insert();
    }//GEN-LAST:event_myButton1ActionPerformed

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
        update();
    }//GEN-LAST:event_myButton2ActionPerformed

    private void myButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton3ActionPerformed
        delete();
    }//GEN-LAST:event_myButton3ActionPerformed

    private void myButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton4ActionPerformed
        clear();
    }//GEN-LAST:event_myButton4ActionPerformed

    private void myButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_myButton5ActionPerformed

    private void txtsrchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsrchMouseClicked
       search();
    }//GEN-LAST:event_txtsrchMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        IntelliJTheme.setup(root.class.getResourceAsStream("/template.theme.json"));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new root().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private button.MyButton myButton1;
    private button.MyButton myButton2;
    private button.MyButton myButton3;
    private button.MyButton myButton4;
    private button.MyButton myButton5;
    private style.PanelRound panelRound1;
    private javax.swing.JTable table1;
    private textfield.TextField txtid;
    private textfield.TextField txtroot;
    private swing.TextFieldAnimation txtsrch;
    // End of variables declaration//GEN-END:variables
}
