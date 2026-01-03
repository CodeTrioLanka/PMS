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
import java.sql.ResultSet;

//import sun.jvm.hotspot.tools.PStack;
import java.util.*;
import javaswingdev.Notification;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import table.TableCustom;

/**
 *
 * @author ravin
 */
public class factory extends javax.swing.JFrame {
    
    DBclass dbo;

    /**
     * Creates new form collector
     */
    public factory() {
        initComponents();
        dbo=new DBclass();
        comboload();
        tableload();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
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
    
    public class coll{
        
        String id;
        String name;
        
        public  coll(String id,String name){
            this.id=id;
            this.name=name;
        }
        public String toString(){
            return name;
        }
        
    }
    public void comboload(){
        try {
            String sql="select * from collector";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            
            while(rs.next()){
               combo1.addItem(new coll(rs.getString(1), rs.getString(3)));
            }
            
            
            
            
        } catch (Exception e) {
        }
    }
    
    public void tableload(){
       try{
       String sql="select a.f_id as ID,a.fac_name as Factory_name,a.fac_address as FactoryAddress ,a.number as Number,b.f_name as CollectorName from factory a inner join collector b on a.c_id=b.c_id";
      // String sql="select * from factory";
        dbo.connect();
        ResultSet rs=dbo.select(sql);
        table1.setModel(DbUtils.resultSetToTableModel(rs));
        dbo.conclose();
        
        /*table1.getColumnModel().getColumn(4).setMinWidth(0);
        table1.getColumnModel().getColumn(4).setMaxWidth(0);*/
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           
       }
    }
    
    public void tabledetail(){
      int r=table1.getSelectedRow();
      
      String id=table1.getValueAt(r,0).toString();
      String name=table1.getValueAt(r,1).toString();
      String address=table1.getValueAt(r, 2).toString();
      String phone=table1.getValueAt(r, 3).toString();
      String collc=table1.getValueAt(r, 4).toString();
     
      txtid.setText(id);
      txtfname.setText(name);
      txtadd.setText(address);
      txttp.setText(phone);
     
      for (int i = 0; i < combo1.getItemCount(); i++) {
         coll item = (coll) combo1.getItemAt(i);
        if (String.valueOf(item.id).equals(collc)) {
            combo1.setSelectedIndex(i); 
            break;
        }
    }

    }
    
    public void insert(){
           coll collid=(coll) combo1.getSelectedItem();
        
        try {
            String sql="insert into factory (c_id,fac_name,fac_address,number)values('"+collid.id+"','"+txtfname.getText()+"','"+txtadd.getText()+"','"+txttp.getText()+"')";
            dbo.connect();
            dbo.insert(sql);
            dbo.conclose();
            
          //  JOptionPane.showMessageDialog(null, "Added");
           Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Register Succesfully ");
            noti.showNotification();
            tableload();
            txtid.setText("");
            txtfname.setText("");
            txtadd.setText("");
            txttp.setText("");
            combo1.setSelectedIndex(0);
            txtfname.requestFocus();
        } catch (Exception e) {
             Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Somthing Wrong ");
            noti.showNotification();
          //  JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void update (){
         coll collid=(coll) combo1.getSelectedItem();
        try {
            String sql="update factory set fac_name='"+txtfname.getText()+"',fac_address='"+txtadd.getText()+"',number='"+txttp.getText()+"',c_id='"+collid.id+"' where f_id='"+txtid.getText()+"' ";
            dbo.connect();
            dbo.update(sql);
            dbo.conclose();
          //  JOptionPane.showMessageDialog(null, "Updated");
           Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Update Succesfully ");
            noti.showNotification();
            tableload();
            txtid.setText("");
            txtfname.setText("");
            txtadd.setText("");
            txttp.setText("");
            combo1.setSelectedIndex(0);
            txtfname.requestFocus();
        } catch (Exception e) {
             Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong");
            noti.showNotification();
            
           // JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void delete(){
        int k=JOptionPane.showConfirmDialog(null,"Are You Sure");
       try{
       if(k ==0 ){
           String sql="delete from factory where f_id='"+txtid.getText()+"'";
           dbo.connect();
           dbo.delete(sql);
           dbo.conclose();
          // JOptionPane.showMessageDialog(null, "Deleted");
           Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Deleted Succesfully ");
            noti.showNotification();
          //  tableload();
            txtfname.setText("");
            txtadd.setText("");
            txttp.setText("");
            combo1.setSelectedIndex(0);
            txtfname.requestFocus();
            txtid.setText("");
            tableload();
       }
       }catch(Exception e){
            Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing wrong ");
            noti.showNotification();
          // JOptionPane.showMessageDialog(null, e);
       }
    }
    
    public void clear(){
        txtfname.setText("");
            txtadd.setText("");
            txttp.setText("");
            combo1.setSelectedIndex(0);
            txtfname.requestFocus();
            txtid.setText("");
            
        
    }
    
    
    public void search(){
        try {
            String sql="select * from factory where f_id like '%"+txtsrch.getText()+"%' or fac_name like '%"+txtsrch.getText()+"%' ";
            dbo.connect();
           ResultSet rs= dbo.select(sql);
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            dbo.conclose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jLabel2 = new javax.swing.JLabel();
        myButton5 = new button.MyButton();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        panelRound1 = new style.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        txtsrch = new swing.TextFieldAnimation();
        txtid = new textfield.TextField();
        txtfname = new textfield.TextField();
        txtadd = new textfield.TextField();
        txttp = new textfield.TextField();
        combo1 = new combobox.Combobox();
        myButton1 = new button.MyButton();
        myButton2 = new button.MyButton();
        myButton3 = new button.MyButton();
        myButton4 = new button.MyButton();
        myButton6 = new button.MyButton();

        jLabel2.setText("jLabel2");

        myButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/icons8_close_window_26px.png"))); // NOI18N
        myButton5.setBorderColor(new java.awt.Color(0, 0, 0));
        myButton5.setColorClick(new java.awt.Color(0, 0, 0));
        myButton5.setColorOver(new java.awt.Color(0, 0, 0));
        myButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton5ActionPerformed(evt);
            }
        });

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
        jLabel1.setText("FACTORY");

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
        txtid.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtid.setLabelText("ID");

        txtfname.setBackground(new java.awt.Color(34, 59, 68));
        txtfname.setForeground(new java.awt.Color(255, 255, 255));
        txtfname.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtfname.setLabelText("Factory Name");

        txtadd.setBackground(new java.awt.Color(34, 59, 68));
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtadd.setLabelText("Address");

        txttp.setBackground(new java.awt.Color(34, 59, 68));
        txttp.setForeground(new java.awt.Color(255, 255, 255));
        txttp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txttp.setLabelText("Number");

        combo1.setBackground(new java.awt.Color(34, 59, 68));
        combo1.setForeground(new java.awt.Color(255, 255, 255));
        combo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        combo1.setLabeText("Collector");

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

        myButton6.setBackground(new java.awt.Color(34, 59, 68));
        myButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/close_window_26px.png"))); // NOI18N
        myButton6.setBorderColor(new java.awt.Color(0, 0, 0));
        myButton6.setColorClick(new java.awt.Color(0, 0, 0));
        myButton6.setColorOver(new java.awt.Color(0, 0, 0));
        myButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(112, 112, 112)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsrch, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelRound1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 50, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtfname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtadd, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                .addComponent(txttp, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                .addComponent(combo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(50, 50, 50)))
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(myButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addComponent(myButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(txtsrch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtfname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txttp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
      tabledetail();
    }//GEN-LAST:event_table1MouseClicked

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        insert();
    }//GEN-LAST:event_myButton1ActionPerformed

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
        update();
    }//GEN-LAST:event_myButton2ActionPerformed

    private void myButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton3ActionPerformed
       tableload();
        delete();
    }//GEN-LAST:event_myButton3ActionPerformed

    private void myButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton4ActionPerformed
        clear();
    }//GEN-LAST:event_myButton4ActionPerformed

    private void myButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_myButton5ActionPerformed

    private void myButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton6ActionPerformed
        this.dispose();
    }//GEN-LAST:event_myButton6ActionPerformed

    private void txtsrchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsrchMouseClicked
       search();
    }//GEN-LAST:event_txtsrchMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        IntelliJTheme.setup(factory.class.getResourceAsStream("/template.theme.json"));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new factory().setVisible(true);
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
    private button.MyButton myButton6;
    private style.PanelRound panelRound1;
    private javax.swing.JTable table1;
    private textfield.TextField txtadd;
    private textfield.TextField txtfname;
    private textfield.TextField txtid;
    private swing.TextFieldAnimation txtsrch;
    private textfield.TextField txttp;
    // End of variables declaration//GEN-END:variables
}
