/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import javax.swing.JPanel;

/**
 *
 * @author coolsasisndu@gmail.com yt.com /techinbox
 */
public class JpanelLoader {
   
   public  void jPanelLoader(JPanel Main,JPanel setPanel){
       
     /* Main.removeAll();
     
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Main);
        Main.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        System.gc();*/
     Main.removeAll(); // Clear existing components
      
      // Set up GroupLayout for the target panel (Main)
      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Main);
      Main.setLayout(layout);
      layout.setHorizontalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(setPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(setPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );
      
      Main.revalidate();  // Refresh layout
      Main.repaint();     // Redraw with new panel content
   
    
    }
   
}
