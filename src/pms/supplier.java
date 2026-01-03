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


public class supplier extends javax.swing.JFrame {
   
    DBclass dbo;
    public supplier() {
        initComponents();
        dbo=new DBclass();
        comboload();
        tableload();
          TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        
        //comboload2();
    }
   
      public class rooty{
        String id;
        String name;
        
        public rooty(String id,String name){
            
            this.id=id;
            this.name=name;
            
        }
        
        public String toString(){
            return name;
        }
    }
   
    
    public void comboload(){
        try {
            
            String sql="select * from root";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            
            while(rs.next()){
                combo1.addItem(new rooty(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
    }
    
    public void tableload(){
        try {
            String sql="select a.sup_id as ID,a.s_name as Name,a.l_name ,a.s_address as Address,a.number as Phone ,a.root_id,b.root_name as RootName from supplier a inner join root b on a.root_id=b.root_id";
            dbo.connect();
           ResultSet rs= dbo.select(sql);
           table1.setModel(DbUtils.resultSetToTableModel(rs));
           
           table1.getColumnModel().getColumn(2).setMinWidth(0);
            table1.getColumnModel().getColumn(2).setMaxWidth(0);
            
            table1.getColumnModel().getColumn(5).setMinWidth(0);
            table1.getColumnModel().getColumn(5).setMaxWidth(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void tabledetail(){
        int r=table1.getSelectedRow();
        
        String id=table1.getValueAt(r, 0).toString();
        String fName=table1.getValueAt(r, 1).toString();
        String lname=table1.getValueAt(r, 2).toString();
        String address=table1.getValueAt(r, 3).toString();
        String number=table1.getValueAt(r, 4).toString();
        String root=table1.getValueAt(r, 5).toString();
        
        txttid.setText(id);
        txtfname.setText(fName);
        txtlname.setText(lname);
        txtadd.setText(address);
        txtphone.setText(number);
        
        for (int i = 0; i < combo1.getItemCount(); i++) {
        rooty item = (rooty) combo1.getItemAt(i);
        if (String.valueOf(item.id).equals(root)) {
            combo1.setSelectedIndex(i); 
            break;
        }
    }
    
   /* public class coll{
        
        int id;
        String name;
        
        public  coll(int id,String name){
            this.id=id;
            this.name=name;
        }
        public String toString(){
            return name;
        }
        
    }
    public void comboload2(){
        try {
            String sql="select * from collector";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            
            while(rs.next()){
               combo2.addItem(new coll(rs.getInt(1), rs.getString(2)));
            }
            
            
            
            
        } catch (Exception e) {
        }
    }*/
    }
    
    
    
    public void insert (){
         rooty rootid= (rooty) combo1.getSelectedItem();
      // coll collid=(coll) combo2.getSelectedItem();
        
        try {
           String sql="insert into supplier(sup_id,s_name,l_name,s_address,number,root_id)"
    + "values('"+txttid.getText()+"','"+txtfname.getText()+"','"+txtlname.getText()+"','"+txtadd.getText()+"','"+txtphone.getText()+"','"+rootid.id+"')";
            
           

    
            dbo.connect();
            dbo.insert(sql);
            dbo.conclose();
            
            //JOptionPane.showMessageDialog(null,"Added");
             Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Register Succesfully ");
            noti.showNotification();
            
            tableload();
            
            txtfname.setText("");
            txtlname.setText("");
            txtadd.setText("");
            txtphone.setText("");
            combo1.setSelectedIndex(0);
           
            
            txtfname.requestFocus();
            
        } catch (Exception e) {
             Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong ");
            noti.showNotification();
           // JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void update(){
         rooty rootid= (rooty) combo1.getSelectedItem();
        try {
        String sql="update supplier set f_name='"+txtfname.getText()+"',l_name='"+txtlname.getText()+"',s_address='"+txtadd.getText()+"',number='"+txtphone.getText()+"',root_id='"+rootid.id+"' where sup_id='"+txttid.getText()+"'";  
        dbo.connect();
        dbo.update(sql);
        dbo.conclose();
        
        //JOptionPane.showMessageDialog(null,"Updated");
         Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Updated Succesfully ");
            noti.showNotification();
        tableload();
            txttid.setText("");
            txtfname.setText("");
            txtlname.setText("");
            txtadd.setText("");
            txtphone.setText("");
            combo1.setSelectedIndex(0);
           
            
            txtfname.requestFocus();
        } catch (Exception e) {
             Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong");
            noti.showNotification();
           // JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void delete(){
        int k=JOptionPane.showConfirmDialog(null, "Are You Sure?");
        
        if(k ==0){
            try {
                String sql="delete from supplier where sup_id='"+txttid.getText()+"'";
                dbo.connect();
                dbo.delete(sql);
                dbo.conclose();
                
              //  JOptionPane.showMessageDialog(null,"Deleted");
               Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Deleted Succesfully ");
            noti.showNotification();
        tableload();
            txttid.setText("");
            txtfname.setText("");
            txtlname.setText("");
            txtadd.setText("");
            txtphone.setText("");
            combo1.setSelectedIndex(0);
           
            
            txtfname.requestFocus();
                
            } catch (Exception e) {
                 Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong ");
            noti.showNotification();
            }
        }
    }
    public void clear(){
        txtfname.setText("");
            txtlname.setText("");
            txtadd.setText("");
            txtphone.setText("");
            combo1.setSelectedIndex(0);
            txttid.setText("");
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
        txttid = new textfield.TextField();
        txtfname = new textfield.TextField();
        txtlname = new textfield.TextField();
        txtadd = new textfield.TextField();
        txtphone = new textfield.TextField();
        combo1 = new combobox.Combobox();
        myButton5 = new button.MyButton();
        myButton1 = new button.MyButton();
        myButton2 = new button.MyButton();
        myButton3 = new button.MyButton();
        myButton4 = new button.MyButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(34, 59, 68));
        kGradientPanel1.setkStartColor(new java.awt.Color(34, 59, 68));

        panelRound1.setBackground(new java.awt.Color(34, 59, 68));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(50);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SUPPLIERS");

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

        txttid.setBackground(new java.awt.Color(34, 59, 68));
        txttid.setForeground(new java.awt.Color(255, 255, 255));
        txttid.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txttid.setLabelText("ID");

        txtfname.setBackground(new java.awt.Color(34, 59, 68));
        txtfname.setForeground(new java.awt.Color(255, 255, 255));
        txtfname.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtfname.setLabelText("First Name");

        txtlname.setBackground(new java.awt.Color(34, 59, 68));
        txtlname.setForeground(new java.awt.Color(255, 255, 255));
        txtlname.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtlname.setLabelText("Last Name");

        txtadd.setBackground(new java.awt.Color(34, 59, 68));
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtadd.setLabelText("Address");

        txtphone.setBackground(new java.awt.Color(34, 59, 68));
        txtphone.setForeground(new java.awt.Color(255, 255, 255));
        txtphone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtphone.setLabelText("Phone");

        combo1.setBackground(new java.awt.Color(34, 59, 68));
        combo1.setForeground(new java.awt.Color(255, 255, 255));
        combo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        combo1.setLabeText("Route");

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

        myButton1.setBackground(new java.awt.Color(34, 59, 68));
        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/add_48px.png"))); // NOI18N
        myButton1.setText("Insert");
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

        myButton4.setBackground(new java.awt.Color(34, 59, 68));
        myButton4.setForeground(new java.awt.Color(255, 255, 255));
        myButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/clear_symbol_28px.png"))); // NOI18N
        myButton4.setText("Clear");
        myButton4.setBorderColor(new java.awt.Color(255, 153, 204));
        myButton4.setColorClick(new java.awt.Color(255, 255, 255));
        myButton4.setColorOver(new java.awt.Color(255, 153, 204));
        myButton4.setRadius(40);
        myButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(combo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txttid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtsrch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtfname, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                                    .addComponent(txtlname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                                    .addComponent(txtadd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                                    .addComponent(txtphone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(txtsrch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txttid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtfname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtlname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
       tabledetail();
    }//GEN-LAST:event_table1MouseClicked

    private void myButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_myButton5ActionPerformed

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        insert();
    }//GEN-LAST:event_myButton1ActionPerformed

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
        update();
    }//GEN-LAST:event_myButton2ActionPerformed

    private void myButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton4ActionPerformed
        clear();
    }//GEN-LAST:event_myButton4ActionPerformed

    private void myButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton3ActionPerformed
       delete();
    }//GEN-LAST:event_myButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        IntelliJTheme.setup(supplier.class.getResourceAsStream("/template.theme.json"));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private combobox.Combobox combo1;
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
    private textfield.TextField txtadd;
    private textfield.TextField txtfname;
    private textfield.TextField txtlname;
    private textfield.TextField txtphone;
    private swing.TextFieldAnimation txtsrch;
    private textfield.TextField txttid;
    // End of variables declaration//GEN-END:variables
}
