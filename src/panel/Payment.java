/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import code.DBclass;
import java.io.File;



import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.function.Supplier;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;




/**
 *
 * @author ravin
 */
public class Payment extends javax.swing.JPanel {
    
    
    DBclass dbo;
    

    /**
     * Creates new form dailysupply
     */
    public Payment() {
        initComponents();
        date.setVisible(false);
        dbo=new DBclass();
        textload();
        ratetravel.setEnabled(false);
        ratetrade.setEnabled(false);
        
        comboload2();
        showDate();
    
    }
     private void showDate() {
        Date d = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(d);
        this.date.setText(date);
    }
    
   
    
    
    public void textload() {
    try {
        String sql = "SELECT * FROM rate";
        dbo.connect();
        ResultSet rs = dbo.select(sql);
        
        
        while (rs.next()) {
            String type = rs.getString("type");
            double rate = rs.getDouble("Rate");
            
           
            if ("Travelling".equals(type)) {
                ratetrade.setText(String.valueOf(rate));
            } else if ("Trade".equals(type)) {
                ratetravel.setText(String.valueOf(rate));
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    
    public class rootpay{
        String id;
        String name;
        
        public rootpay(String id,String name){
            
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
                combo1.addItem(new rootpay(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
    }
      
    /*  public void tableload(){
         try{
            // double total=0,wetweihgt=0,sackweihgt=0,pay=0;
          
          rootpay rootid= (rootpay) combo1.getSelectedItem();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           String startDateStr = sdf.format(startCal.getDate());
           String endDateStr = sdf.format(endCal.getDate());
          String sql="select  b.s_name , c.root_name , sum(a.w_weight) , sum(a.s_weight) , sum(a.qty) from supplier_supply a inner join supplier b on a.sup_id =b.sup_id inner join root c on a.root_id =c.root_id where a.root_id='"+rootid.id+"' and a.date between '"+startDateStr+"' and '"+endDateStr+"' group by a.sup_id,b.s_name,a.root_id,c.root_name";
          
          dbo.connect();
          
          ResultSet rs=dbo.select(sql);
          
        // table12.setModel(DbUtils.resultSetToTableModel(rs));
              
         
             
           double  wetweihgt=Double.parseDouble(rs.getString("sum(a.w_weight)"));
          double  sackweihgt=Double.parseDouble(rs.getString("sum(a.s_weight)"));
           double  total=Double.parseDouble(rs.getString("sum(a.qty)"));
             
            double perone=Double.parseDouble(amount.getText());
            
          double  pay=total * perone -(wetweihgt+sackweihgt);
            
              System.out.println(pay);
            
            
       
          
        
          
         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             
         }
      }*/
      
      public void tableload() {
    try {
        rootpay rootid = (rootpay) combo1.getSelectedItem();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sdf.format(startCal.getDate());
        String endDateStr = sdf.format(endCal.getDate());

        String sql = "SELECT b.sup_id,DATE_FORMAT(a.date, '%Y-%M'),b.s_name, c.root_name, SUM(a.w_weight), SUM(a.s_weight), SUM(a.qty) " +
                     "FROM supplier_supply a " +
                     "INNER JOIN supplier b ON a.sup_id = b.sup_id " +
                     "INNER JOIN root c ON a.root_id = c.root_id " +
                     "WHERE a.root_id = '" + rootid.id + "' " +
                     "AND a.date BETWEEN '" + startDateStr + "' AND '" + endDateStr + "' " +
                     "GROUP BY a.sup_id, b.s_name, a.root_id, c.root_name,DATE_FORMAT(a.date, '%Y-%M')";

        dbo.connect(); 
        ResultSet rs = dbo.select(sql);

       
        double perOne = Double.parseDouble(amount.getText());
        double ratefortravel=Double.parseDouble(ratetravel.getText());
        double ratefortrade=Double.parseDouble(ratetrade.getText());
        
        
        DefaultTableModel model = (DefaultTableModel) table12.getModel();
        model.setColumnIdentifiers(new Object[]{"Supplier Name", "Root Name", "Date","Wet Weight", "Sack Weight", "NetTotal","Total", "Per One", "Payment","TravelCom","Tradecom","TotalPay"});

        model.setRowCount(0);

        while (rs.next()) {
            double wetWeight = rs.getDouble("SUM(a.w_weight)");
            double sackWeight = rs.getDouble("SUM(a.s_weight)");
            double Ntotal = rs.getDouble("SUM(a.qty)");
            double total=Ntotal-(wetWeight+sackWeight);
            
            double pay = (total * perOne) - (wetWeight + sackWeight);
            double travelcom=pay * ratefortravel;
            double tradecom=pay* ratefortrade;
            
            double totalpay=pay-(tradecom+travelcom);
            model.addRow(new Object[]{
               // rs.getString("b.sup_id"),
                rs.getString("b.s_name"), 
                rs.getString("c.root_name"),
                rs.getString("Date_format(a.date, '%Y-%m')"),
                wetWeight, 
                sackWeight, 
                Ntotal,
                total,
                perOne, 
                pay,
                travelcom,
                tradecom,
                totalpay
                
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}
      
      
      public void paysave(){
           DefaultTableModel model = (DefaultTableModel) table12.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            try {
             String sql1="select sup_id from supplier where s_name='"+model.getValueAt(i, 0)+"'";
             dbo.connect();
             
             ResultSet rs=dbo.select2(sql1);
             String supp_id=null;
             if(rs.next()){
             supp_id=rs.getString("sup_id");
                 
             }
             
             String sql2="select root_id from root where root_name='"+model.getValueAt(i, 1)+"'";
             dbo.connect();
             
             ResultSet rst=dbo.select3(sql2);
             
             String routeid=null;
             
                if (rst.next()) {
                    routeid=rst.getString("root_id");
                   
                    
                }
                
                String sql="insert into supplier_payment(supplier_sup_id,root_root_id,date,wet_weight,sackweight,totalqty,total,peronekg,travel_com,trade_com,m_payemnt,system_date) values('"+supp_id+"','"+routeid+"','"+model.getValueAt(i, 2)+"','"+model.getValueAt(i, 3)+"','"+model.getValueAt(i, 4)+"','"+model.getValueAt(i, 5)+"','"+model.getValueAt(i, 6)+"','"+model.getValueAt(i, 7)+"','"+model.getValueAt(i, 9)+"','"+model.getValueAt(i, 10)+"','"+model.getValueAt(i, 11)+"','"+date.getText()+"')";
                dbo.connect();
                dbo.insert(sql);
                dbo.conclose();
                
                JOptionPane.showMessageDialog(null,"saved");
             
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
           
            
        }
      }
  
    





      
      
      

      
      
   /*public void tableload(){
       
       SimpleDateFormat aa=new SimpleDateFormat("YYYY-MM-dd");
       String date1=aa.format(cal.getDate());
       
       SimpleDateFormat ab=new SimpleDateFormat("YYYY-MM-dd");
       String date2=ab.format(cal2.getDate());
       
       String name=combo1.getSelectedItem().toString();
       
       try{
       String sql="select b.root_name,c.s_name,a.date,a.qty,a.w_weight,a.s_weight,a.total from supplier_supply a inner join root b on a.root_id=b.root_id inner join supplier c on b.root_id=c.root_id where b.root_name='"+name+"'and a.date >='"+date1+"'and a.date<='"+date2+"'";
       
       dbo.connect();
       ResultSet rs=dbo.select(sql);
       table1.setModel(DbUtils.resultSetToTableModel(rs));
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
   }*/
      
    /*public void generatePayments() {
        try {
            
            String routname = combo1.getSelectedItem().toString();
            

            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDateStr = sdf.format(startCal.getDate());
            String endDateStr = sdf.format(endCal.getDate());

            
            java.sql.Date startDate = java.sql.Date.valueOf(startDateStr);
            java.sql.Date endDate = java.sql.Date.valueOf(endDateStr);

            
            double deliveryCommission = Double.parseDouble(deliveryCommissionField.getText());
            double tradeCommission = Double.parseDouble(tradeCommissionField.getText());
            
            
            double pricePerKg=Double.parseDouble(amount.getText());
            
            rootpay rootid= (rootpay) combo1.getSelectedItem();
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
             
            String query = "SELECT S.Sup_ID, S.S_Name, SUM(ss.Qty) AS Total_Quantity  FROM Supplier S JOIN supplier_supply ss ON S.Sup_ID = ss.Sup_ID WHERE S.root_id = '"+rootid.id+"' AND ss.Date BETWEEN ? AND ? GROUP BY S.Sup_ID, S.S_Name";
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345678");
            
           // DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
            //rightAlign.setHorizontalAlignment(SwingConstants.RIGHT);
            
            
           // table1.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
          //  table1.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                
                 
                stmt.setDate(1, startDate);
                stmt.setDate(2, endDate);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String supplyId = rs.getString("sup_id");
                   // String sname=rs.getString("s_name");
                    
                    
                    
                    double totalQuantity = rs.getDouble("Total_Quantity");

                    
                    double totalPayment = (totalQuantity * pricePerKg)
                                        - (totalQuantity * pricePerKg * deliveryCommission)
                                        - (totalQuantity * pricePerKg * tradeCommission);

                    savePayment(supplyId, totalPayment);
                    
                    //model.addRow(new Object[]{supplyId,sname,totalQuantity,totalPayment});
                    
                }
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
        private void savePayment(String supplyId, double totalPayment) {
        java.sql.Date currentDate = new java.sql.Date(new Date().getTime());
        rootpay rootid= (rootpay) combo1.getSelectedItem();
        String insertQuery = "INSERT INTO Payemnt (date,price ,sup_id,root_id ) VALUES (?, ?,?,'"+rootid.id+"')";
        try {
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345678");
            
        try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {
            stmt.setDate(1, currentDate);
            stmt.setDouble(2, totalPayment);
            stmt.setString(3, supplyId);
            
            stmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }
        
     public void fillpaytable(){
         try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            String startDateStr = sdf.format(startCal.getDate());
            String endDateStr = sdf.format(endCal.getDate());
            
            
           String sql="select b.sup_id ,b.s_name, d.root_name, a.s_weight,a.w_weight, a.total, c.price from supplier_supply a inner join supplier b on a.sup_id=b.sup_id inner join payemnt c on b.sup_id=c.sup_id inner join root d on a.root_id=d.root_id where a.date BETWEEN '"+startDateStr+"' AND '"+endDateStr+"' ";
           
           

           
           dbo.connect();
            ResultSet rs=dbo.select(sql);
            while(rs.next()){
                
               DefaultTableModel model = (DefaultTableModel) jtable1.getModel();
               
              // model.setRowCount(0);
               String suppid=rs.getString("sup_id");
               String supplyname=rs.getString("s_name");
               String rootname=rs.getString("root_name");
               String sackweight=rs.getString("s_weight");
               String wetweight=rs.getString("w_weight");
               String totalqtyy=rs.getString("total");
               String pricefinal=rs.getString("price");
               
               
               model.addRow(new Object[]{suppid,supplyname,rootname,sackweight,wetweight,totalqtyy,pricefinal});
            }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
     } */  
        
        

    
       
    
    





    
    
    

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        panelRound1 = new style.PanelRound();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        combo1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        startCal = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        endCal = new com.toedter.calendar.JDateChooser();
        btnclr = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        amount = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ratetravel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ratetrade = new javax.swing.JTextField();
        btnclr1 = new javax.swing.JButton();
        date = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table12 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

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

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/delete_48px.png"))); // NOI18N
        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/process_30px.png"))); // NOI18N
        jButton5.setText("Generate");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/print_24px.png"))); // NOI18N
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        combo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Start Date");

        startCal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Route");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("End Date");

        endCal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startCal, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(endCal, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startCal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(endCal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnclr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/clear_symbol_28px.png"))); // NOI18N
        btnclr.setText("Clear");
        btnclr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclrActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        amount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Tea Amout");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Trade Com");

        ratetravel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Travellling");

        ratetrade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ratetravel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ratetrade, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(amount, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ratetravel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ratetrade, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        btnclr1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/save_28px.png"))); // NOI18N
        btnclr1.setText("Save");
        btnclr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclr1ActionPerformed(evt);
            }
        });

        date.setText("jLabel1");

        table12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        table12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SupID", "Name", "Root_Name", "Sack Weight", "Wet Weight", "Total Qunatuty", "Price"
            }
        ));
        table12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                table12MouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(table12);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 102));
        jLabel12.setText("PAYMENT");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(26, 26, 26)
                                .addComponent(btnclr, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(btnclr1)
                                .addGap(29, 29, 29)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel12)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(btnclr1)
                            .addComponent(jButton2))
                        .addGap(37, 37, 37)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(btnclr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(9, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void table12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table12MouseClicked
   
       
    }//GEN-LAST:event_table12MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /*try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345678");
            HashMap<String,Object>check=new HashMap<>();
            check.put("system_date", date.getText());
            
            String query="C:\\Users\\ravin\\JaspersoftWorkspaceV2\\MyReports\\reportpsm.jrxml";
            JasperReport report=JasperCompileManager.compileReport(query);
            JasperPrint print=JasperFillManager.fillReport(report, check,con);
            JasperViewer.viewReport(print);
        } catch (Exception e) {
        }*/
       /* try {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345678");

    
    String supplierQuery = "SELECT sup_id, s_name FROM supplier";
    PreparedStatement stmt = con.prepareStatement(supplierQuery);
    ResultSet rs = stmt.executeQuery();

   
    while (rs.next()) {
        
        HashMap<String, Object> check = new HashMap<>();
        check.put("system_date", date.getText());
        check.put("sup_id", rs.getString("sup_id"));  
        
        
        String query = "C:\\Users\\ravin\\JaspersoftWorkspaceV2\\MyReports\\reportpsm.jrxml";
        JasperReport report = JasperCompileManager.compileReport(query);
        JasperPrint print = JasperFillManager.fillReport(report, check, con);

       
        JasperViewer.viewReport(print, false);  // Set false to allow next navigation
        
       
    }
} catch (Exception e) {
    e.printStackTrace();
}*/
   
     
       try {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345678");

   
    String supplierQuery = "SELECT sup_id, s_name FROM supplier";
    PreparedStatement stmt = con.prepareStatement(supplierQuery);
    ResultSet rs = stmt.executeQuery();

  
    JasperPrint combinedPrint = null;
    String query = "E:\\related to pms\\PMS Latest version\\PMS\\PMS\\src\\jasperrep\\reportpsm.jrxml";
    JasperReport report = JasperCompileManager.compileReport(query);

   
    while (rs.next()) {
        
        HashMap<String, Object> check = new HashMap<>();
        check.put("system_date", date.getText());
        check.put("sup_id", rs.getString("sup_id"));  

       
        JasperPrint print = JasperFillManager.fillReport(report, check, con);

        
        if (combinedPrint == null) {
            combinedPrint = print;
        } else {
           
            for (JRPrintPage page : print.getPages()) {
                combinedPrint.addPage(page);
            }
        }
    }

    
    if (combinedPrint != null && !combinedPrint.getPages().isEmpty()) {
        JasperViewer.viewReport(combinedPrint, false);
    } else {
        System.out.println("No pages generated for the combined report.");
    }

} catch (Exception e) {
    e.printStackTrace();
}

     


    








   



    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
   
    
   

    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnclrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclrActionPerformed
   
    }//GEN-LAST:event_btnclrActionPerformed

    private void table12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_table12MouseEntered

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        tableload();
       


    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnclr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclr1ActionPerformed
        paysave();
        
        
    }//GEN-LAST:event_btnclr1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JButton btnclr;
    private javax.swing.JButton btnclr1;
    private javax.swing.JComboBox combo1;
    private javax.swing.JLabel date;
    private com.toedter.calendar.JDateChooser endCal;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JTextField ratetrade;
    private javax.swing.JTextField ratetravel;
    private com.toedter.calendar.JDateChooser startCal;
    private javax.swing.JTable table12;
    // End of variables declaration//GEN-END:variables
}
