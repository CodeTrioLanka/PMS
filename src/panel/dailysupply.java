
package panel;

import code.DBclass;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import javaswingdev.Notification;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import pms.collector11;
import table.TableCustom;


public class dailysupply extends javax.swing.JPanel {
    
    DBclass dbo;
    
   
    
    

    /**
     * Creates new form dailysupply
     */
    public dailysupply() {
        initComponents();
        dbo=new DBclass();
        comboload();
        comboload2();
        table1.getColumnModel().getColumn(0).setMinWidth(0);
        table1.getColumnModel().getColumn(0).setMaxWidth(0);
        
        table1.getColumnModel().getColumn(3).setMinWidth(0);
        table1.getColumnModel().getColumn(3).setMaxWidth(0);
        
        table1.getColumnModel().getColumn(4).setMinWidth(0);
        table1.getColumnModel().getColumn(4).setMaxWidth(0);
        
        combo1.setEnabled(false);
        combo2.setEnabled(false);
   
    }
    
      private void showNotification(String message, Notification.Type type) {
    Notification noti = new Notification(this, type, Notification.Location.TOP_CENTER, message);
    noti.showNotification();
}
    
    

    
    
    public class collectss{
        
        String id;
        String name;
        
        public  collectss(String id,String name){
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
               combo1.addItem(new collectss(rs.getString(1), rs.getString(3)));
            }
            
            
            
            
        } catch (Exception e) {
        }
    }
    
    public class rootsup{
        String id;
        String name;
        
        public rootsup(String id,String name){
            
            this.id=id;
            this.name=name;
            
        }
        
        public String toString(){
            return name;
        }
    }
      public void comboload2(){
        try {
            
            String sql="select * from root";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            
            while(rs.next()){
                combo2.addItem(new rootsup(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
    }
    
    public void txtfill(){
        
        try {
            String sql="select a.s_name,b.root_name ,b.root_id ,c.f_name from supplier a inner join root b on a.root_id=b.root_id inner join collector c on a.root_id=c.root_id where a.sup_id='"+txtsupid.getText()+"'";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            
            if(rs.next()){
               String name =rs.getString("s_name");
               txtname.setText(name);
               
               
            String rootName = rs.getString("root_name");
            for (int i = 0; i < combo2.getItemCount(); i++) {
            rootsup item = (rootsup) combo2.getItemAt(i); 
            if (item.toString().equals(rootName)) { 
            combo2.setSelectedItem(item); 
            break; 
 
    }
}
        String cname = rs.getString("f_name"); 


for (int i = 0; i < combo1.getItemCount(); i++) {
    collectss item = (collectss) combo1.getItemAt(i);

    
    if (item.toString().equals(cname)) { 
        combo1.setSelectedItem(item); 
        break; 
    }
}

            
                
              
               
               
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        

    }
    
    
    private void updateTotals() {
    DefaultTableModel model = (DefaultTableModel) table1.getModel();
    
    double totalQty = 0, totalWetWeight = 0, totalDifference = 0,totalsackWeight=0;
    
    
    for (int i = model.getRowCount() - 1; i >= 0; i--) {
        if ("Total".equals(model.getValueAt(i, 7))) {
            model.removeRow(i);
        }
    }

    
    for (int i = 0; i < model.getRowCount(); i++) {
        double qty = (double) model.getValueAt(i, 8);
        double wetWeight = (double) model.getValueAt(i, 9);
        double sWeight = (double) model.getValueAt(i, 10);
        totalQty += qty;
        totalWetWeight += wetWeight;
        totalsackWeight +=sWeight;
        totalDifference += qty-(wetWeight+sWeight);
    }

    
    model.addRow(new Object[]{
        "", "", "", "","","","","Total", totalQty, totalWetWeight,totalsackWeight, totalDifference
    });
}
    
    public void addRowToTable(){
        DefaultTableModel model =(DefaultTableModel)table1.getModel();
        
        try {
            String sup_id=txtsupid.getText();
            String name=txtname.getText();
            
            rootsup root_id=(rootsup)combo2.getSelectedItem();
            collectss coll_id=(collectss)combo1.getSelectedItem();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(cal1.getDate());
            
            String rootid = root_id.name;
             String rootiddd=root_id.id;
             
             String collidd = coll_id.name;
             String colliddddd=coll_id.id;
            
            double qty = Double.parseDouble(txtqty.getText());
            double wetWeight = Double.parseDouble(txtwweight.getText());
            
            double sWeight = Double.parseDouble(txtsweight.getText());
            
            double difference =  qty - (wetWeight + sWeight);
            
             model.addRow(new Object[]{  
            model.getRowCount() + 1,sup_id,name,colliddddd,rootiddd, rootid , collidd, date,qty,wetWeight,sWeight,difference
        });
            updateTotals(); 
            
            
        } catch (Exception e) {
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        panelRound1 = new style.PanelRound();
        btninsert = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtsweight = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtwweight = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtsupid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cal1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        combo1 = new javax.swing.JComboBox();
        combo2 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));

        kGradientPanel1.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 0, 0));

        panelRound1.setBackground(new java.awt.Color(245, 245, 245));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(50);

        btninsert.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btninsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/add_48px.png"))); // NOI18N
        btninsert.setText("INSERT");
        btninsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninsertActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/delete_48px.png"))); // NOI18N
        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/update_48px.png"))); // NOI18N
        jButton5.setText("UPDATE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tea Description"));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Qty");

        txtsweight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("W.Weight");

        txtwweight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("S.Weight");

        txtqty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(txtsweight))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(txtwweight))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(109, 109, 109))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtwweight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtsweight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Customer Option"));

        txtsupid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
        txtsupid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsupidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsupidKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Date");

        cal1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Collector Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("ROUTE");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Name");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Supplier ID");

        txtname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsupid, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtname)
                            .addComponent(cal1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo1, 0, 212, Short.MAX_VALUE)
                            .addComponent(combo2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtsupid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(cal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("DAILY SUPPLY");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/save_28px.png"))); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/clear_symbol_28px.png"))); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        table1.setBackground(new java.awt.Color(245, 245, 245));
        table1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Supplier_id", "Name", "root_id", "coll_id", "Root_Name", "Collector_Name", "Date", "QTY", "W_Weight", "S_Weight", "Total"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        table1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table1KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRound1Layout.createSequentialGroup()
                                        .addComponent(jButton5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton4))
                                    .addComponent(jButton2)))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel9)
                        .addGap(41, 41, 41)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(19, 19, 19)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btninsert)
                            .addComponent(jButton4)
                            .addComponent(jButton5))
                        .addGap(35, 35, 35))))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtsupidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupidKeyReleased
        txtfill();
    }//GEN-LAST:event_txtsupidKeyReleased

    private void txtsupidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupidKeyPressed
        
        
    }//GEN-LAST:event_txtsupidKeyPressed

    private void btninsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninsertActionPerformed
      addRowToTable();
      updateTotals();
      txtsupid.setText("");
      txtname.setText("");
      combo2.setSelectedIndex(0);
      combo1.setSelectedIndex(0);
      cal1.setDate(null);
      txtqty.setText("");
      txtwweight.setText("");
      txtsweight.setText("");
    }//GEN-LAST:event_btninsertActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            
            try {
            String sql="insert into supplier_supply(sup_id,c_id,root_id,date,w_weight,s_weight,qty) values ('"+model.getValueAt(i, 1)+"','"+model.getValueAt(i, 3)+"','"+model.getValueAt(i, 4)+"','"+model.getValueAt(i, 7)+"','"+model.getValueAt(i, 9)+"','"+model.getValueAt(i, 10)+"','"+model.getValueAt(i, 8)+"')";
            dbo.connect();
            dbo.insert(sql);
            dbo.conclose();
                showNotification("Saved", Notification.Type.SUCCESS);
            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
            }
        }
        model.setRowCount(0);
        JOptionPane.showMessageDialog(null,"Saved");
        
     /*   DefaultTableModel model = (DefaultTableModel) table1.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
        try {
        String supId = model.getValueAt(i, 1).toString();
        String cId = model.getValueAt(i, 4).toString();
        String rootId = model.getValueAt(i, 3).toString();
        String date = model.getValueAt(i, 7).toString();
        String wWeight = model.getValueAt(i, 9).toString();
        String sWeight = model.getValueAt(i, 10).toString();
        String qty = model.getValueAt(i, 8).toString();

        String checkSql = "SELECT COUNT(*) FROM supplier_supply WHERE sup_id = '" + supId + "' AND c_id = '" + cId + "' AND root_id = '" + rootId + "'";
        dbo.connect();
        ResultSet rs = dbo.select(checkSql);

        if (rs.next() && rs.getInt(1) == 0) {  
            String insertSql = "INSERT INTO supplier_supply (sup_id, c_id, root_id, date, w_weight, s_weight, qty) " +
                               "VALUES ('" + supId + "', '" + cId + "', '" + rootId + "', '" + date + "', '" + wWeight + "', '" + sWeight + "', '" + qty + "')";
            dbo.insert(insertSql);
        }

        dbo.conclose();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}*/
      //model.setRowCount(0);
      //JOptionPane.showMessageDialog(null, "Saved");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            int selectedRow = table1.getSelectedRow();
    
    if (selectedRow != -1) { 
        try {
            
            int sup_id=Integer.parseInt(txtsupid.getText());
            String name=txtname.getText();
            
            rootsup root_id=(rootsup)combo2.getSelectedItem();
            collectss coll_id=(collectss)combo1.getSelectedItem();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(cal1.getDate());
            
            String rootid = root_id.name;
             String rootiddd=root_id.id;
             
             String collidd = coll_id.name;
             String colliddddd=coll_id.id;
            
            double qty = Double.parseDouble(txtqty.getText());
            double wetWeight = Double.parseDouble(txtwweight.getText());
            
            double sWeight = Double.parseDouble(txtsweight.getText());
            
            double difference =  (qty-wetWeight)-sWeight;

            
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setValueAt(sup_id, selectedRow, 1);  
            model.setValueAt(name, selectedRow, 2);  
            model.setValueAt(rootiddd, selectedRow, 3);   
            model.setValueAt(colliddddd, selectedRow, 4); 
            model.setValueAt(root_id, selectedRow, 5);     
            model.setValueAt(collidd, selectedRow, 6);      
            model.setValueAt(qty, selectedRow, 7); 
            model.setValueAt(wetWeight, selectedRow, 8);
            model.setValueAt(sWeight, selectedRow, 9); 
            model.setValueAt(difference, selectedRow, 10); 
            
            table1.repaint();
            updateTotals();
            
            
            JOptionPane.showMessageDialog(null, "Row updated successfully!");
            txtsupid.setText("");
            txtname.setText("");
            combo2.setSelectedIndex(0);
            combo1.setSelectedIndex(0);
            cal1.setDate(null);
            txtqty.setText("");
            txtwweight.setText("");
            txtsweight.setText("");
            
            btninsert.setEnabled(true);
            txtsupid.setEnabled(true);
            

        } catch (Exception e) {
            
        }
    } 
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txtsupid.setText("");
      txtname.setText("");
      combo2.setSelectedIndex(0);
      combo1.setSelectedIndex(0);
      cal1.setDate(null);
      txtqty.setText("");
      txtwweight.setText("");
      txtsweight.setText("");
      txtsupid.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void table1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table1KeyPressed
     
    }//GEN-LAST:event_table1KeyPressed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
    int row = table1.getSelectedRow(); 
 
    
    if (row != -1) {
        
    String id=table1.getValueAt(row, 1).toString();
    txtsupid.setText(id);
    
    String name=table1.getValueAt(row, 2).toString();
    txtname.setText(name);
    
    String routen=table1.getValueAt(row, 5).toString();
    for (int i = 0; i < combo1.getItemCount(); i++) {
    rootsup item = (rootsup) combo2.getItemAt(i);
    if (String.valueOf(item.id).equals(routen)) {
    combo2.setSelectedIndex(i); 
    break;
    }              
    }
    
    String collectorn=table1.getValueAt(row, 6).toString();
    for (int i = 0; i < combo1.getItemCount(); i++) {
    collectss item = (collectss) combo1.getItemAt(i);
    if (String.valueOf(item.id).equals(collectorn)) {
    combo1.setSelectedIndex(i); 
    break;
    }              
    }
    
    String dated=table1.getValueAt(row, 7).toString();
    try {
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-DD");
    java.util.Date date=dateFormat.parse(dated);
    
    cal1.setDate(date);
    } catch (Exception e) {
    }
    
    String qty=table1.getValueAt(row, 8).toString();
    txtqty.setText(qty);
    
    String w_weig=table1.getValueAt(row, 9).toString();
    txtwweight.setText(w_weig);
    
    String s_weig=table1.getValueAt(row, 10).toString();
    txtsweight.setText(s_weig);
    
    btninsert.setEnabled(false);
    txtsupid.setEnabled(false);
    
    
                    
                    
    }
       
       
    }//GEN-LAST:event_table1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    int selectedRow = table1.getSelectedRow();
    
    if (selectedRow != -1) { 
    int confirm = JOptionPane.showConfirmDialog(  null,"Are you sure you want to delete this row?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

       
        if (confirm == JOptionPane.YES_OPTION) {
           
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.removeRow(selectedRow);
            
            
            updateTotals(); 
            JOptionPane.showMessageDialog(null, "Row deleted successfully!");
            txtsupid.setText("");
            txtname.setText("");
            combo2.setSelectedIndex(0);
            combo1.setSelectedIndex(0);
            cal1.setDate(null);
            txtqty.setText("");
            txtwweight.setText("");
            txtsweight.setText("");
        }
        
    }
      
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btninsert;
    private com.toedter.calendar.JDateChooser cal1;
    private javax.swing.JComboBox combo1;
    private javax.swing.JComboBox combo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private style.PanelRound panelRound1;
    private javax.swing.JTable table1;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtsupid;
    private javax.swing.JTextField txtsweight;
    private javax.swing.JTextField txtwweight;
    // End of variables declaration//GEN-END:variables
}
