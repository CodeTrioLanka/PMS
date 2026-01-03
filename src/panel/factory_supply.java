/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import code.DBclass;
import com.toedter.calendar.JCalendar;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
//import jdk.internal.classfile.impl.BufferedCodeBuilder;
import net.proteanit.sql.DbUtils;
import pms.collector11;
import scroll.ScrollBarCustomUI;
import table.BooleanCellRenderer;
import table.HoverIndex;
import table.TableCustom;
import table.TableCustomCellRender;
import table.TableHeaderCustomCellRender;
import table.TextAreaCellRenderer;

/**
 *
 * @author ravin
 */
public class factory_supply extends javax.swing.JPanel {
    
    
    DBclass dbo;

    /**
     * Creates new form dailysupply
     */
    public factory_supply() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        dbo=new DBclass();
        collcomboload1();
        combofacload();
        JTable.getColumnModel().getColumn(1).setMinWidth(0);
        JTable.getColumnModel().getColumn(1).setMaxWidth(0);
        
        JTable.getColumnModel().getColumn(2).setMinWidth(0);
        JTable.getColumnModel().getColumn(2).setMaxWidth(0);
      // Example
        
    



        
        
        
       // tableload();
    }
//      public static void apply(JScrollPane scroll, TableCustom.TableType type) {
//        JTable table = (JTable) scroll.getViewport().getComponent(0);
//        table.setSelectionBackground(new Color(123, 207, 255));
//        table.getTableHeader().setReorderingAllowed(false);
//        table.getTableHeader().setDefaultRenderer(new TableHeaderCustomCellRender(table));
//        table.setRowHeight(30);
//        HoverIndex hoverRow = new HoverIndex();
//        TableCellRenderer cellRender;
//        if (type == TableCustom.TableType.DEFAULT) {
//            cellRender = new TableCustomCellRender(hoverRow);
//        } else {
//            cellRender = new TextAreaCellRenderer(hoverRow);
//        }
//        table.setDefaultRenderer(Object.class, cellRender);
//        table.setDefaultRenderer(Boolean.class, new BooleanCellRenderer(hoverRow));
//        table.setShowVerticalLines(true);
//        table.setGridColor(new Color(220, 220, 220));
//        table.setForeground(new Color(51, 51, 51));
//        table.setSelectionForeground(new Color(51, 51, 51));
//        scroll.setBorder(new LineBorder(new Color(220, 220, 220)));
//        JPanel panel = new JPanel() {
//            @Override
//            public void paint(Graphics grphcs) {
//                super.paint(grphcs);
//                grphcs.setColor(new Color(220, 220, 220));
//                grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
//                grphcs.dispose();
//            }
//        };
//        panel.setBackground(new Color(250, 250, 250));
//        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
//        scroll.getViewport().setBackground(Color.WHITE);
//        scroll.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
//        scroll.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
//        table.getTableHeader().setBackground(new Color(250, 250, 250));
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseExited(MouseEvent e) {
//                hoverRow.setIndex(-1);
//                table.repaint();
//            }
//
//        });
//        table.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                int row = table.rowAtPoint(e.getPoint());
//                if (row != hoverRow.getIndex()) {
//                    hoverRow.setIndex(row);
//                    table.repaint();
//                }
//            }
//
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                int row = table.rowAtPoint(e.getPoint());
//                if (row != hoverRow.getIndex()) {
//                    hoverRow.setIndex(row);
//                    table.repaint();
//                }
//            }
//        });
//    }
      
       
   
    





    
    
    public class collectorhh{
        String id;
        String name;
        public collectorhh(String id,String name){
            
            this.id=id;
            this.name=name;
        }
        public String toString(){
            return name;
        }
    }
    
    public void collcomboload1(){
        try {
            String sql="select * from collector";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            
            while(rs.next()){
                collectorComboBox.addItem(new collectorhh(rs.getString(1), rs.getString(3)) );
              
            }
            dbo.conclose();
        } catch (Exception e) {
        }
    }
    
    
    public class factoryxx{
        int id;
        String name;
        public factoryxx(int id,String name){
           this.id=id;
           this.name=name;
        }
        public String toString(){
            return name;
        }
    }
    
    public void combofacload(){
        try {
            String sql="select * from factory";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            
            while(rs.next()){
                combo2.addItem(new factoryxx(rs.getInt(1), rs.getString(3)));
            }
        } catch (Exception e) {
        }
    }
    /*public void tableload(){
        try {
            String sql="select a.f_sup_id as ID,a.date as DATE,a.qty as Quantity,a.w_weight as Water,a.total as Total,a.c_id,a.f_id ,b.fac_name as Factory_Name,c.f_name as Collector_name from factory_supply a inner join collector c on a.c_id=c.c_id inner join factory b on a.f_id=b.f_id";
            dbo.connect();
            ResultSet rs=dbo.select(sql);
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            
            table1.getColumnModel().getColumn(5).setMinWidth(0);
            table1.getColumnModel().getColumn(5).setMaxWidth(0);
            
            table1.getColumnModel().getColumn(6).setMinWidth(0);
            table1.getColumnModel().getColumn(6).setMaxWidth(0);
        } catch (Exception e) {
        }
    }*/
    
   /* public void tabledetail(){
        try {
        DefaultTableModel model=(DefaultTableModel)table1.getModel();
        int rawselect=table1.getSelectedRow();
        
            
                    
        int r=table1.getSelectedRow();
        
        String id=table1.getValueAt(r, 0).toString();
        //String date=table1.getValueAt(r, 1).toString();
        String qty=table1.getValueAt(r, 2).toString();
        String w_weight=table1.getValueAt(r,3 ).toString();
        String total=table1.getValueAt(r, 4).toString();
        String collc_id=table1.getValueAt(r, 5).toString();
        String fact_id=table1.getValueAt(r, 6).toString();
        
        txtid.setText(id);
       // cal.setDate(date);
        txtqty.setText(qty);
        txtwweight.setText(w_weight);
        
        
         for (int i = 0; i < combo1.getItemCount(); i++) {
        collectorhh item = (collectorhh) combo1.getItemAt(i);
        if (String.valueOf(item.id).equals(collc_id)) {
            combo1.setSelectedIndex(i); 
            break;
        }
    }
         
          for (int i = 0; i < combo2.getItemCount(); i++) {
        factoryxx item = (factoryxx) combo2.getItemAt(i);
        if (String.valueOf(item.id).equals(fact_id)) {
            combo2.setSelectedIndex(i); 
            break;
        }
    }
         
         
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    
    }*/


   
    
 
 
    
    private void updateTotals() {
    DefaultTableModel model = (DefaultTableModel) JTable.getModel();
    
    double totalQty = 0, totalDifference = 0;
    
  
    for (int i = model.getRowCount() - 1; i >= 0; i--) {
        if ("Total".equals(model.getValueAt(i, 4))) {
            model.removeRow(i);
        }
    }

    
    for (int i = 0; i < model.getRowCount(); i++) {
        double qty = (double) model.getValueAt(i, 6);
        //double wetWeight = (double) model.getValueAt(i, 7);

        totalQty += qty;
        //totalWetWeight += wetWeight;
        totalDifference += qty ;
    }

    
    model.addRow(new Object[]{
        "", "", "", "","Total","", totalQty, totalDifference
    });
}



    
     
    
 
    
    

    
    

  
   

public void addRowToTable() {
    DefaultTableModel model = (DefaultTableModel) JTable.getModel();

    try {
        
        collectorhh coll_id = (collectorhh) collectorComboBox.getSelectedItem();
        factoryxx fact_id = (factoryxx) combo2.getSelectedItem();

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(cal.getDate());

       
        String c_name = coll_id.name;
        String cc_name=coll_id.id;
        String fac_name = fact_id.name;
        int facc_id=fact_id.id;
        double qty = Double.parseDouble(txtqty.getText());  
        //double wetWeight = Double.parseDouble(txtwweight.getText());  

       
        double difference =  qty;

       
        model.addRow(new Object[]{
            model.getRowCount() + 1,cc_name,facc_id, c_name, fac_name, date, qty ,difference
        });

       
       updateTotals();
       
        /*table1.getColumnModel().getColumn(1).setMinWidth(0);
        table1.getColumnModel().getColumn(1).setMaxWidth(0);
        
        table1.getColumnModel().getColumn(2).setMinWidth(0);
        table1.getColumnModel().getColumn(2).setMaxWidth(0);
       
       */

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input in quantity or wet weight fields.");
    }
}

public void update(){
      int selectedRow = JTable.getSelectedRow();
    
    if (selectedRow != -1) { 
        try {
            
            collectorhh coll_id = (collectorhh) collectorComboBox.getSelectedItem();
            factoryxx fact_id = (factoryxx) combo2.getSelectedItem();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(cal.getDate()); 

            String c_name = coll_id.name;
            String cc_name = coll_id.id;
            String fac_name = fact_id.name;
            int facc_id = fact_id.id;

            double qty = Double.parseDouble(txtqty.getText());
            //double wetWeight = Double.parseDouble(txtwweight.getText());
            double difference = qty ;

            
            DefaultTableModel model = (DefaultTableModel) JTable.getModel();
            model.setValueAt(cc_name, selectedRow, 1);  
            model.setValueAt(facc_id, selectedRow, 2);  
            model.setValueAt(c_name, selectedRow, 3);   
            model.setValueAt(fac_name, selectedRow, 4); 
            model.setValueAt(date, selectedRow, 5);     
            model.setValueAt(qty, selectedRow, 6);      
          //  model.setValueAt(wetWeight, selectedRow, 7); 
            model.setValueAt(difference, selectedRow, 8); 

            
            JTable.repaint();
            updateTotals();
            
            
            JOptionPane.showMessageDialog(null, "Row updated successfully!");
            btninsert.setEnabled(true);
            collectorComboBox.setSelectedIndex(0);
            combo2.setSelectedIndex(0);
            cal.setDate(null);
            txtqty.setText("");
           // txtwweight.setText("");

        } catch (Exception e) {
            
        }
    } 
}

public void delete(){
        int selectedRow = JTable.getSelectedRow();
    
    if (selectedRow != -1) { 
        int confirm = JOptionPane.showConfirmDialog(  null,"Are you sure you want to delete this row?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

       
        if (confirm == JOptionPane.YES_OPTION) {
           
            DefaultTableModel model = (DefaultTableModel) JTable.getModel();
            model.removeRow(selectedRow);
            
            
            updateTotals(); 
            JOptionPane.showMessageDialog(null, "Row deleted successfully!");
        }
    } 
    btninsert.setEnabled(true);
    collectorComboBox.setSelectedIndex(0);
    combo2.setSelectedIndex(0);
    cal.setDate(null);
    txtqty.setText("");
   // txtwweight.setText("");
}

public void clear(){
    collectorComboBox.setSelectedIndex(0);
    combo2.setSelectedIndex(0);
    cal.setDate(null);
    txtqty.setText("");
   // txtwweight.setText("");
    btninsert.setEnabled(true);
    DefaultTableModel model=(DefaultTableModel)JTable.getModel();
    model.setRowCount(0);
}

public void save(){
     DefaultTableModel model = (DefaultTableModel) JTable.getModel();
    // int i=table1.getRowCount();*/
    for (int i = 0; i < model.getRowCount(); i++) {
        try {
            String sql="insert  into factory_supply(c_id,f_id,date,qty)values('"+model.getValueAt(i, 1).toString()+"','"+model.getValueAt(i, 2).toString()+"','"+model.getValueAt(i, 5)+"','"+model.getValueAt(i, 6)+"') ";
            dbo.connect();
            dbo.insert(sql);
            dbo.conclose();
            
            
           // model.setRowCount(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
     }
    JOptionPane.showMessageDialog(null, "Saved");
    model.setRowCount(0);
}

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        panelRound1 = new style.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable = new javax.swing.JTable();
        textFieldAnimation1 = new swing.TextFieldAnimation();
        txtid = new textfield.TextField();
        btninsert = new button.MyButton();
        myButton2 = new button.MyButton();
        myButton3 = new button.MyButton();
        myButton4 = new button.MyButton();
        myButton5 = new button.MyButton();
        collectorComboBox = new combobox.Combobox();
        combo2 = new combobox.Combobox();
        cal = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtqty = new textfield.TextField();

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

        kGradientPanel1.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 0, 0));

        panelRound1.setBackground(new java.awt.Color(245, 245, 245));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(50);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("FACTORY SUPPLY");

        JTable.setBackground(new java.awt.Color(245, 245, 245));
        JTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "c_id", "f_id", "Collector", "Factory_name", "Date", "QTY", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JTable.setShowGrid(true);
        JTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JTableMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(JTable);
        JTable.getAccessibleContext().setAccessibleParent(jScrollPane1);

        textFieldAnimation1.setBackground(new java.awt.Color(245, 245, 245));
        textFieldAnimation1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        txtid.setBackground(new java.awt.Color(245, 245, 245));
        txtid.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtid.setLabelText("ID");

        btninsert.setForeground(new java.awt.Color(255, 255, 255));
        btninsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/add_48px.png"))); // NOI18N
        btninsert.setText("Insert");
        btninsert.setColor(new java.awt.Color(34, 59, 68));
        btninsert.setColorClick(new java.awt.Color(255, 255, 255));
        btninsert.setColorOver(new java.awt.Color(0, 102, 102));
        btninsert.setRadius(40);
        btninsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninsertActionPerformed(evt);
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

        myButton5.setForeground(new java.awt.Color(255, 255, 255));
        myButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/save_28px.png"))); // NOI18N
        myButton5.setText("Save");
        myButton5.setBorderColor(new java.awt.Color(204, 204, 204));
        myButton5.setColor(new java.awt.Color(0, 0, 0));
        myButton5.setColorClick(new java.awt.Color(255, 255, 255));
        myButton5.setColorOver(new java.awt.Color(204, 204, 204));
        myButton5.setRadius(40);
        myButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton5ActionPerformed(evt);
            }
        });

        collectorComboBox.setBackground(new java.awt.Color(245, 245, 245));
        collectorComboBox.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        collectorComboBox.setLabeText("Collector");

        combo2.setBackground(new java.awt.Color(245, 245, 245));
        combo2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        combo2.setLabeText("Factory Name");

        cal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("DATE");

        txtqty.setBackground(new java.awt.Color(245, 245, 245));
        txtqty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtqty.setLabelText("Quantity");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btninsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(myButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cal, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel9))
                            .addComponent(textFieldAnimation1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                            .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(collectorComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtqty, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldAnimation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(collectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(myButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
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

    private void JTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableMouseClicked
    int row = JTable.getSelectedRow(); 
 
    
    if (row != -1) {
                    
    String cname=JTable.getValueAt(row, 1).toString();
                    
    for (int i = 0; i < collectorComboBox.getItemCount(); i++) {
    collectorhh item = (collectorhh) collectorComboBox.getItemAt(i);
    if (String.valueOf(item.id).equals(cname)) {
    collectorComboBox.setSelectedIndex(i); 
    break;
    }              
    }
    
    String f_name=JTable.getValueAt(row, 2).toString();
    for (int i = 0; i < combo2.getItemCount(); i++) {
    factoryxx item = (factoryxx) combo2.getItemAt(i);
   if (String.valueOf(item.id).equals(f_name)) {
    combo2.setSelectedIndex(i); 
    break;
    }
    }
    
    
    String dateString=JTable.getValueAt(row, 5).toString();
    
    
    try {
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-DD");
    Date date=dateFormat.parse(dateString);
    
    cal.setDate(date);
    } catch (Exception e) {
    }
    
    String qty=JTable.getValueAt(row, 6).toString();
    txtqty.setText(qty);
    
    //String w_weight=table1.getValueAt(row, 7).toString();
    //txtwweight.setText(w_weight);
    
    btninsert.setEnabled(false);
    
    
                    
                    
    }
       
    }//GEN-LAST:event_JTableMouseClicked

    private void JTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JTableMouseEntered

    private void btninsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninsertActionPerformed
        addRowToTable();
        updateTotals();
        collectorComboBox.setSelectedIndex(0);
        combo2.setSelectedIndex(0);
        cal.setDate(null);
        txtqty.setText("");
        
    }//GEN-LAST:event_btninsertActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable;
    private button.MyButton btninsert;
    private com.toedter.calendar.JDateChooser cal;
    private combobox.Combobox collectorComboBox;
    private combobox.Combobox combo2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private button.MyButton myButton2;
    private button.MyButton myButton3;
    private button.MyButton myButton4;
    private button.MyButton myButton5;
    private style.PanelRound panelRound1;
    private swing.TextFieldAnimation textFieldAnimation1;
    private textfield.TextField txtid;
    private textfield.TextField txtqty;
    // End of variables declaration//GEN-END:variables
}
