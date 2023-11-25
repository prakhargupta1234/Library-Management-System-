/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Library_Management_System;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;


/**
 *
 * @author TOSHIBA
 */
public class ManageStudents extends javax.swing.JFrame {

    
    String name,course,branch;
    int Id;
    DefaultTableModel model;
     Color mouseEnterColor = new Color(255,51,0);
   Color mouseExitColor = new Color(255,255,255);
   Color mouseBackColor = new Color(255,51,0);
        Color mouseBackExitColor = new Color(255,255,255);
    
    public ManageStudents() {
        initComponents();
        setStudentDetailsToTable();
    }
    
   public void setStudentDetailsToTable(){
       try {
             Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system","root","gupta22@");
             Statement st=con.createStatement();
             ResultSet rs = st.executeQuery("select id,name,course,branch from users");
             
             while(rs.next()){
                 
                 String Id = rs.getString("id");
                 String name = rs.getString("name");
                 String course = rs.getString("course");
                 String branch  = rs.getString("branch");
                 
                 Object[] obj = {Id,name,course,branch};
                 model = (DefaultTableModel) tbl_studentdetails.getModel();
                 model.addRow(obj);
             }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
  //to add student to student_details_table
   public boolean addStudent(){
       
       boolean isAdded =false;
       
       Id = Integer.parseInt(txt_studentId.getText());
       name = txt_studentName.getText();
       course = combo_CourseName.getSelectedItem().toString();
       branch = combo_branch.getSelectedItem().toString();

       try {
           
              Connection con =   DBConnection.getConnection();
           String sql ="insert into users(id,name,course,branch) values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
           pst.setInt(1, Id);
           pst.setString(2, name);
           pst.setString(3, course);
           pst.setString(4, branch);
           
           int rowCount = pst.executeUpdate();
           if(rowCount > 0){
               isAdded = true;
           }
           else{
               isAdded = false;
           }
           
       } catch (Exception e) {
           
           e.printStackTrace();
       }
       
        return isAdded;
   }
   
   
   //to update student details
   
   public boolean updateStudent(){
   
       boolean isUpdated = false;
       
        Id = Integer.parseInt(txt_studentId.getText());
       name = txt_studentName.getText();
       course = combo_CourseName.getSelectedItem().toString();
       branch = combo_branch.getSelectedItem().toString();

       
       try {
           
           
           
                Connection con =   DBConnection.getConnection();
           String sql ="update users set name = ?,course = ?, branch = ? where Id = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            
          
           pst.setString(1, name);
           pst.setString(2, course);
           pst.setString(3, branch);
            pst.setInt(4, Id);
           
           int rowCount = pst.executeUpdate();
           if(rowCount > 0){
               isUpdated = true;
           }
           else{
               isUpdated = false;
           }
           
           
       } catch (Exception e) {
           
           e.printStackTrace();
       }
       
       return isUpdated;
   }
   
   
   //method to delete a book details
   public boolean deletestudent(){
       
       boolean isDeleted = false;
       Id = Integer.parseInt(txt_studentId.getText());
       
       try {
           
                   Connection con =   DBConnection.getConnection();
           String sql ="delete from users  where id = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
              pst.setInt(1, Id);
              
                
           int rowCount = pst.executeUpdate();
           if(rowCount > 0){
              isDeleted = true;
           }
           else{
              isDeleted = false;
           }
 
           
       } catch (Exception e) {
           
           e.printStackTrace();
       }
       
       
       
       return isDeleted;
   }   
   
   
   
   
   //method to clear table
   
   public void clearTable(){
            
       DefaultTableModel model = (DefaultTableModel) tbl_studentdetails.getModel();
       
       model.setRowCount(0);
   
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_studentName = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        combo_branch = new javax.swing.JComboBox<>();
        combo_CourseName = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_studentdetails = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(102, 255, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_studentId.setBackground(new java.awt.Color(102, 255, 51));
        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_studentId.setToolTipText("");
        txt_studentId.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_studentId.setPlaceholder("Enter Student Id");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        jPanel7.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 210, -1));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 51, 42));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 51, 42));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 51, 42));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 51, 42));

        jLabel30.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Enter Student Id");
        jPanel7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 170, 30));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Enter Student Name");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 180, 30));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Select Course");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 150, 30));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Select Branch");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 150, 30));

        txt_studentName.setBackground(new java.awt.Color(102, 255, 51));
        txt_studentName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_studentName.setToolTipText("");
        txt_studentName.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_studentName.setPlaceholder("Enter Student Name");
        txt_studentName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentNameFocusLost(evt);
            }
        });
        txt_studentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentNameActionPerformed(evt);
            }
        });
        jPanel7.add(txt_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 210, -1));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(255, 51, 0));
        rSMaterialButtonCircle1.setText("Delete");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel7.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, 170, 50));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 51, 0));
        rSMaterialButtonCircle3.setText("Update");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel7.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 170, 50));

        combo_branch.setBackground(new java.awt.Color(102, 255, 51));
        combo_branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CS", "IT", "Electrical", "Electronics", " " }));
        combo_branch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_branchActionPerformed(evt);
            }
        });
        jPanel7.add(combo_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 500, 220, 30));

        combo_CourseName.setBackground(new java.awt.Color(102, 255, 51));
        combo_CourseName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSC ", "MSC ", "PHD", "B TECH", "M TECH", " " }));
        combo_CourseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_CourseNameActionPerformed(evt);
            }
        });
        jPanel7.add(combo_CourseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 220, 30));

        jButton5.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/back.png"))); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 40));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 720));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setAlignmentX(0.0F);
        jScrollPane1.setAlignmentY(0.0F);

        tbl_studentdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Id", "Name", "Course", "Branch"
            }
        ));
        tbl_studentdetails.setColorBackgoundHead(new java.awt.Color(255, 51, 0));
        tbl_studentdetails.setColorBordeFilas(new java.awt.Color(255, 51, 0));
        tbl_studentdetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_studentdetails.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl_studentdetails.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl_studentdetails.setColorSelBackgound(new java.awt.Color(255, 255, 0));
        tbl_studentdetails.setColorSelForeground(new java.awt.Color(0, 0, 0));
        tbl_studentdetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_studentdetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_studentdetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_studentdetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_studentdetails.setRowHeight(40);
        tbl_studentdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_studentdetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_studentdetails);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 790, 260));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel1.setText("Manage Students Details ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 520, -1));

        jPanel2.setBackground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 500, 4));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/cut.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 50, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 960, 720));

        setSize(new java.awt.Dimension(1350, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_studentNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentNameFocusLost


        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentNameFocusLost

    private void txt_studentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentNameActionPerformed

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed

              if(deletestudent() == true){
            JOptionPane.showMessageDialog(this, "student Deleted");
            clearTable();
            setStudentDetailsToTable();
        }
        else{
            
             JOptionPane.showMessageDialog(this, "student Deletion Failed ");
              }


        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
                
        if(updateStudent() == true){
            JOptionPane.showMessageDialog(this, "student updated");
            clearTable();
            setStudentDetailsToTable();
        }
        else{
            
             JOptionPane.showMessageDialog(this, "student updation Failed ");
        
        }       



        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void tbl_studentdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_studentdetailsMouseClicked

            int rowNo = tbl_studentdetails.getSelectedRow();
            TableModel model = tbl_studentdetails.getModel();
            
            txt_studentId.setText(model.getValueAt(rowNo, 0).toString());
             txt_studentName.setText(model.getValueAt(rowNo, 1).toString());
              combo_CourseName.setSelectedItem(model.getValueAt(rowNo, 2).toString());
              combo_branch.setSelectedItem(model.getValueAt(rowNo, 3).toString());

        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_studentdetailsMouseClicked

    private void combo_branchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_branchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_branchActionPerformed

    private void combo_CourseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_CourseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_CourseNameActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBackground(mouseEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited

        jButton2.setBackground(mouseExitColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered

        jButton5.setBackground(mouseBackColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        jButton5.setBackground(mouseBackExitColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        setVisible(false);
        HomePage home = new HomePage();
        home.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_CourseName;
    private javax.swing.JComboBox<String> combo_branch;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojeru_san.complementos.RSTableMetro tbl_studentdetails;
    private app.bolivia.swing.JCTextField txt_studentId;
    private app.bolivia.swing.JCTextField txt_studentName;
    // End of variables declaration//GEN-END:variables
}
