/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pms;

import button.MyButton;
import code.JpanelLoader;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.UIManager;

import panel.dailysupply;
import panel.factory_supply;
import panel.Payment;
import panel.dashboardani;
import table.TableCustom;

/**
 *
 * @author ravin
 */
public  class main extends javax.swing.JFrame {
    
    JpanelLoader loader=new JpanelLoader();
    
    
    
   

    /**
     * Creates new form main
     */
    public main() {
        initComponents();
        this.setExtendedState(main.MAXIMIZED_BOTH);
        showDateAndTime();
        
        jToggleButton1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        jToggleButton1.addActionListener(new ActionListener() {

            private final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
            private ScheduledFuture<?> scheduledFuture;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                scheduledFuture = scheduled.schedule(() -> {
                    changeThemes(jToggleButton1.isSelected());
                }, 500, TimeUnit.MILLISECONDS);
            }
        });
        dashboardani aa=new dashboardani();
        loader.jPanelLoader(pannel_load, aa);
       
       
    
        
        
        
    }
    public void setpermission( boolean user ,boolean route,boolean collector_c,boolean factory,boolean supplier_c,boolean rate,boolean factory_s,boolean daily_s,boolean pay,boolean adminp){
        menu5.setVisible(user);
        menu1.setVisible(route);
        menu2.setVisible(collector_c);
        menu3.setVisible(factory);
        jMenu2.setVisible(supplier_c);
        jMenu1.setVisible(rate);
        myButton1.setVisible(factory_s);
        myButton2.setVisible(daily_s);
        btnpay.setVisible(pay);
        jMenu3.setVisible(adminp);
        
    }
    
    public void showform(Component com){
        pannel_load.removeAll();
        pannel_load.add(com);
        pannel_load.revalidate();
        pannel_load.repaint();
        pannel_load.setLayout(new BorderLayout());
        pannel_load.add(com, BorderLayout.CENTER);

    }
   
    
    
    private void changeThemes(boolean dark) {
        if (FlatLaf.isLafDark() != dark) {
            if (!dark) {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatIntelliJLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            } else {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatDarculaLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public JMenu getroute(){
        return menu1;
    }
    
    public JMenu getcollec(){
        return menu2;
    }
    
    public JMenu getfac(){
        return menu3;
    }
    
    public JMenu getsup(){
        return jMenu2;
    }
    
    public JMenu getmenu5(){
        return menu5;
    }
    private void showDateAndTime() {
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                lb1.setText(dateFormatter.format(date));
                
               
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
               lb2.setText(timeFormatter.format(date));
            }
        });
        timer.start();
    }
    
    public JLabel getrole(){
        return txtrole;
    }
    
    public MyButton getpay(){
        return  btnpay;
    }
    public JMenu getrate(){
        return jMenu1;
    }
    public void logout(){
        login aa=new login();
        aa.setVisible(true);
        this.dispose();
    }
    
    public void applyTableTheme(JScrollPane scrollPane) {
    TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);
}
    
  


 

    
    /*public void setFactorySupplyCollectorName(String collectorName) {
    factory_supply factoryPanel = new factory_supply();  // Create the "factory supply" panel
    factoryPanel.getcombo1().setEnabled(false);          // Disable editing
    factoryPanel.getcombo1().setSelectedItem(collectorName);  // Set combo1 to the collector's name

    // Add the "factory supply" panel to the dashboard or tab where needed
    pannel_load.add(factoryPanel);  // Assuming `mainPanel` is your main dashboard panel
    pannel_load.revalidate();        // Refresh the UI to display the updated panel
}*/


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home = new javax.swing.ButtonGroup();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jMenuItem3 = new javax.swing.JMenuItem();
        darkLightSwitchIcon1 = new raven.themes.DarkLightSwitchIcon();
        pannel_load = new javax.swing.JPanel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jButton1 = new javax.swing.JButton();
        txtrole = new javax.swing.JLabel();
        lb1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        myButton1 = new button.MyButton();
        myButton2 = new button.MyButton();
        btnpay = new button.MyButton();
        Daschboard = new button.MyButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        menu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        menu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        menu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        kGradientPanel1.setkEndColor(new java.awt.Color(0, 102, 102));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(100, 100));

        pannel_load.setBackground(new java.awt.Color(255, 255, 255));
        pannel_load.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pannel_load.setMinimumSize(new java.awt.Dimension(0, 0));
        pannel_load.setLayout(new java.awt.BorderLayout());

        kGradientPanel2.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel2.setkStartColor(new java.awt.Color(0, 0, 0));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 102));
        jButton1.setText("LOG OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtrole.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtrole.setForeground(new java.awt.Color(255, 102, 255));
        txtrole.setText("ROLE");

        lb1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lb1.setForeground(new java.awt.Color(255, 255, 255));
        lb1.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setText("DATE :");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setText("TIME :");

        lb2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lb2.setForeground(new java.awt.Color(255, 255, 255));
        lb2.setText("jLabel1");

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(txtrole, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jButton1)
                .addContainerGap())
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(txtrole)
                    .addComponent(lb1)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(lb2))
                .addContainerGap())
        );

        kGradientPanel3.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel3.setkStartColor(new java.awt.Color(0, 0, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/tea new_1.png"))); // NOI18N

        jToggleButton1.setIcon(darkLightSwitchIcon1);

        myButton1.setForeground(new java.awt.Color(0, 102, 102));
        myButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/factory_20xxx.png"))); // NOI18N
        myButton1.setText("Factory Supply");
        myButton1.setBorderColor(new java.awt.Color(0, 0, 0));
        myButton1.setColorClick(new java.awt.Color(0, 0, 0));
        myButton1.setColorOver(new java.awt.Color(204, 204, 204));
        myButton1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        myButton1.setRadius(40);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        myButton2.setForeground(new java.awt.Color(0, 102, 102));
        myButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/plant_20x.png"))); // NOI18N
        myButton2.setText("Daily Supply");
        myButton2.setBorderColor(new java.awt.Color(0, 0, 0));
        myButton2.setColorClick(new java.awt.Color(0, 0, 0));
        myButton2.setColorOver(new java.awt.Color(204, 204, 204));
        myButton2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        myButton2.setRadius(40);
        myButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton2ActionPerformed(evt);
            }
        });

        btnpay.setForeground(new java.awt.Color(0, 102, 102));
        btnpay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/money_20x.png"))); // NOI18N
        btnpay.setText("Payemnt");
        btnpay.setBorderColor(new java.awt.Color(0, 0, 0));
        btnpay.setColorClick(new java.awt.Color(0, 0, 0));
        btnpay.setColorOver(new java.awt.Color(204, 204, 204));
        btnpay.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnpay.setRadius(40);
        btnpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpayActionPerformed(evt);
            }
        });

        Daschboard.setForeground(new java.awt.Color(0, 102, 102));
        Daschboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/chart_decreasing_48px.png"))); // NOI18N
        Daschboard.setText("Dashboard");
        Daschboard.setBorderColor(new java.awt.Color(0, 0, 0));
        Daschboard.setColorClick(new java.awt.Color(0, 0, 0));
        Daschboard.setColorOver(new java.awt.Color(204, 204, 204));
        Daschboard.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        Daschboard.setRadius(40);
        Daschboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DaschboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel2))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(myButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnpay, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Daschboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Daschboard, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnpay, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(14, 14, 14))
        );

        jMenuBar1.setForeground(new java.awt.Color(0, 102, 102));

        jMenu3.setText("Admin");

        jMenuItem8.setText("Setting");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        menu5.setText("User");

        jMenuItem6.setText("Change Password");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menu5.add(jMenuItem6);

        jMenuBar1.add(menu5);

        menu1.setText("Route");

        jMenuItem5.setText("Manage");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menu1.add(jMenuItem5);

        jMenuBar1.add(menu1);

        menu2.setText("Collectors");

        jMenuItem1.setText("Manage");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu2.add(jMenuItem1);

        jMenuBar1.add(menu2);

        menu3.setText("Factory");

        jMenuItem4.setText("Manage");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menu3.add(jMenuItem4);

        jMenuBar1.add(menu3);

        jMenu2.setText("Suppliers");

        jMenuItem2.setText("Manage");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Rate");

        jMenuItem7.setText("Add New Rate");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pannel_load, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pannel_load, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      collector11 a1=new collector11();
      a1.setVisible(true);
      
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      supplier a2=new supplier();
      a2.setVisible(true);
      
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
         factory a3=new factory();
         a3.setVisible(true);
         
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       root a4=new root();
       a4.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login aa=new login();
        aa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
       password aa=new password();
       aa.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Rate bb=new Rate();
        bb.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
//        factory_supply aa=new factory_supply();
//        
//        
//        loader.jPanelLoader(pannel_load, aa);
showform(new factory_supply());
    }//GEN-LAST:event_myButton1ActionPerformed

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
        dailysupply a5=new dailysupply();
     loader.jPanelLoader(pannel_load, a5);
        //loadpannel(pannel_load);
   
    }//GEN-LAST:event_myButton2ActionPerformed

    private void btnpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpayActionPerformed
       Payment vv=new Payment();
        loader.jPanelLoader(pannel_load, vv);
        
       
    
   

    }//GEN-LAST:event_btnpayActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
      admin aa=new admin();
      aa.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void DaschboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DaschboardActionPerformed
        dashboardani aa=new dashboardani();
        loader.jPanelLoader(pannel_load, aa);
    }//GEN-LAST:event_DaschboardActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
     //  IntelliJTheme.setup(main.class.getResourceAsStream("/template.theme.json"));
      // FlatRobotoFont.install();
      //  UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 20));
       FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.MyButton Daschboard;
    private button.MyButton btnpay;
    private raven.themes.DarkLightSwitchIcon darkLightSwitchIcon1;
    private javax.swing.ButtonGroup home;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JToggleButton jToggleButton1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JMenu menu1;
    private javax.swing.JMenu menu2;
    private javax.swing.JMenu menu3;
    private javax.swing.JMenu menu5;
    private button.MyButton myButton1;
    private button.MyButton myButton2;
    private javax.swing.JPanel pannel_load;
    private javax.swing.JLabel txtrole;
    // End of variables declaration//GEN-END:variables
}
