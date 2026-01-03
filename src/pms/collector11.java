
package pms;

import code.DBclass;
import com.formdev.flatlaf.IntelliJTheme;
import com.sun.net.httpserver.Authenticator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.sql.*;
import javaswingdev.Notification;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;
import table.TableCustom;


public class collector11 extends javax.swing.JFrame {
    
    DBclass dbo;
   

    /**
     * Creates new form collector
     */
    public collector11() {
        initComponents();
        
        dbo=new DBclass();
        comboload();
        tableload();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        makeFrameDraggable(this);
       // TableColumnModel columnmodel=table1.getColumnModel();
        //TableColumn column=columnmodel.getColumn(0);
        //column.setWidth(0);
        //column.setMaxWidth(0);
        //column.setPreferredWidth(0);
       // column.setResizable(false);
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
    }
    
    
        
    
    public class root{
        String id;
        String name;
        
        public root(String id,String name){
            
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
                txtcombo.addItem(new root(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
    }
    
    public void tableload(){
        try {
            String sql="select b.c_id as ID, b.f_name as NAME, b.l_name, b.address ADDRESS, b.number as Number,b.root_id,a.root_name as Root from root a inner join collector b on a.root_id=b.root_id";
            //String sql="select * from collector";
            dbo.connect();
            ResultSet rs= dbo.select(sql);
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            //con.close;
            dbo.conclose();
             
            
            /*table1.getColumnModel().getColumn(5).setMinWidth(0);
            table1.getColumnModel().getColumn(5).setMaxWidth(0);
            
            table1.getColumnModel().getColumn(7).setMinWidth(0);
            table1.getColumnModel().getColumn(7).setMaxWidth(0);*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void tabledata(){
       int r=table1.getSelectedRow();
       
       String id=table1.getValueAt(r,0).toString();
       String fname=table1.getValueAt(r,1).toString();
       String lname=table1.getValueAt(r,2).toString();
       String address=table1.getValueAt(r,3).toString();
       String number=table1.getValueAt(r, 4).toString();
       
       
       String rootc=table1.getValueAt(r,5).toString();
      // String username=table1.getValueAt(r, 6).toString();
     //  String password=table1.getValueAt(r, 7).toString();
     //  String role=table1.getValueAt(r, 8).toString();
       
       
       
       
       txtid.setText(id);
       txtname.setText(fname);
       txtlname.setText(lname);
       txtadd.setText(address);
       txtnum.setText(number);
       
       for (int i = 0; i < txtcombo.getItemCount(); i++) {
        root item = (root) txtcombo.getItemAt(i);
        if (String.valueOf(item.id).equals(rootc)) {
            txtcombo.setSelectedIndex(i); 
            break;
        }
    }
      // txtuser.setText(username);
      // txtpass.setText(password);
       //combo2.setSelectedItem(role);
       
               
    }
    
    public void search(){
        //String srch=txtsrch.getText();
        
        try {
            String sql="select *from collector where c_id like '%"+srch.getText()+"%' or f_name like '%"+srch.getText()+"%'";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            dbo.conclose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void insert(){
        root rootid= (root) txtcombo.getSelectedItem();
        try {
            String sql="insert into collector(c_id,f_name,l_name,address,number,root_id)values('"+txtid.getText()+"','"+txtname.getText()+"','"+txtlname.getText()+"','"+txtadd.getText()+"','"+txtnum.getText()+"','"+rootid.id+"')";
            dbo.connect();
            dbo.insert(sql);
            dbo.conclose();
            
            
            
            //JOptionPane.showMessageDialog(null, "Added");
            Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Registration Done");
            noti.showNotification();
            tableload();
            txtname.setText("");
            txtlname.setText("");
            txtadd.setText("");
            txtnum.setText("");
            txtcombo.setSelectedIndex(0);
           // txtuser.setText("");
           // txtpass.setText("");
           // combo2.setSelectedIndex(0);
            txtid.setText("");
            
            
        } catch (Exception e) {
            Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong");
            noti.showNotification();
        }
    }
    public void update(){
         root rootid= (root) txtcombo.getSelectedItem();
        
        try {
            String sql="update collector set f_name='"+txtname.getText()+"',l_name='"+txtlname.getText()+"',address='"+txtadd.getText()+"',number='"+txtnum.getText()+"',root_id='"+rootid.id+"' where c_id='"+txtid.getText()+"'";
            dbo.connect();
            dbo.update(sql);
            dbo.conclose();
            
            tableload();
           // JOptionPane.showMessageDialog(null, "Updated");
            Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Update Succesfully");
            noti.showNotification();
            txtname.setText("");
            txtlname.setText("");
            txtadd.setText("");
            txtnum.setText("");
           // txtuser.setText("");
           // txtpass.setText("");
           // combo2.setSelectedIndex(0);
            txtcombo.setSelectedIndex(0);
            txtid.setText("");
            
        } catch (Exception e) {
             Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong");
            noti.showNotification();
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void delete(){
        int k= JOptionPane.showConfirmDialog(null,"Are You Sure");
      
      if(k == 0){
          try {
             String sql="delete from collector where c_id='"+txtid.getText()+"'"; 
             dbo.connect();
             dbo.delete(sql);
             dbo.conclose();
             
             //JOptionPane.showMessageDialog(null,"Deleted");
              Notification noti=new Notification(this ,Notification.Type.SUCCESS,Notification.Location.TOP_CENTER,"Deleted Succesfully ");
            noti.showNotification();
             tableload();
             txtid.setText("");
             txtname.setText("");
            txtlname.setText("");
            txtadd.setText("");
            txtnum.setText("");
            txtcombo.setSelectedIndex(0);
          } catch (Exception e) {
               Notification noti=new Notification(this ,Notification.Type.WARNING,Notification.Location.TOP_CENTER,"Somthing Wrong ");
            noti.showNotification();
              //JOptionPane.showMessageDialog(null, e);
          }
      }
    }
    
    public void clear(){
        srch.setText("");
           txtname.setText("");
            txtlname.setText("");
            txtadd.setText("");
            txtnum.setText("");
            txtcombo.setSelectedIndex(0);
            txtid.setText("");
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
        
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        panelRound1 = new style.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        srch = new swing.TextFieldAnimation();
        txtid = new textfield.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        txtname = new textfield.TextField();
        txtlname = new textfield.TextField();
        txtadd = new textfield.TextField();
        txtnum = new textfield.TextField();
        txtcombo = new combobox.Combobox();
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

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COLLECTORS");

        srch.setBackground(new java.awt.Color(34, 59, 68));
        srch.setForeground(new java.awt.Color(255, 255, 255));
        srch.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        srch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                srchMouseClicked(evt);
            }
        });
        srch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                srchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srchKeyReleased(evt);
            }
        });

        txtid.setBackground(new java.awt.Color(34, 59, 68));
        txtid.setForeground(new java.awt.Color(255, 255, 255));
        txtid.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtid.setLabelText("ID");

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

        txtname.setBackground(new java.awt.Color(34, 59, 68));
        txtname.setForeground(new java.awt.Color(255, 255, 255));
        txtname.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtname.setLabelText("First Name");

        txtlname.setBackground(new java.awt.Color(34, 59, 68));
        txtlname.setForeground(new java.awt.Color(255, 255, 255));
        txtlname.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtlname.setLabelText("Last Name");

        txtadd.setBackground(new java.awt.Color(34, 59, 68));
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtadd.setLabelText("Address");

        txtnum.setBackground(new java.awt.Color(34, 59, 68));
        txtnum.setForeground(new java.awt.Color(255, 255, 255));
        txtnum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtnum.setLabelText("Number");

        txtcombo.setBackground(new java.awt.Color(34, 59, 68));
        txtcombo.setForeground(new java.awt.Color(255, 255, 255));
        txtcombo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtcombo.setLabeText("Select Route");

        myButton1.setBackground(new java.awt.Color(34, 59, 68));
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

        myButton3.setBackground(new java.awt.Color(34, 59, 68));
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

        myButton4.setBackground(new java.awt.Color(34, 59, 68));
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
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtcombo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                            .addComponent(srch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtlname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtadd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnum, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                                .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(279, 279, 279))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(srch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtlname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(txtcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
       tableload();
    }//GEN-LAST:event_myButton4ActionPerformed

    private void myButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_myButton5ActionPerformed

    private void srchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_srchMouseClicked
       search();
    }//GEN-LAST:event_srchMouseClicked

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        tabledata();
    }//GEN-LAST:event_table1MouseClicked

    private void srchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srchKeyReleased
       
    }//GEN-LAST:event_srchKeyReleased

    private void srchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srchKeyPressed
        
    }//GEN-LAST:event_srchKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        IntelliJTheme.setup(collector11.class.getResourceAsStream("/template.theme.json"));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new collector11().setVisible(true);
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
    private swing.TextFieldAnimation srch;
    private javax.swing.JTable table1;
    private textfield.TextField txtadd;
    private combobox.Combobox txtcombo;
    private textfield.TextField txtid;
    private textfield.TextField txtlname;
    private textfield.TextField txtname;
    private textfield.TextField txtnum;
    // End of variables declaration//GEN-END:variables
}
