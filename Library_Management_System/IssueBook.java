/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Library_Management_System;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author TOSHIBA
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
     
    Color mouseEnterColor = new Color(102,255,51);
    Color mouseExitColor = new Color(0,102,0);
    Color mouseCrossEnterColor = new Color(255,51,0);
    Color mouseCrossExitColor = new Color(255,255,255);
    Color mouseHomeEnterColor = new Color(255,51,51);
    public IssueBook() {
        initComponents();
    }

    // to faech the book details from database and display it to book_details
    
    public void getBookDetaiils(){
            int bookId = Integer.parseInt(txt_bookId.getText());
            
            try {
                
                  Connection con = DBConnection.getConnection();
                   PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ? ");
                   pst.setInt(1, bookId);
                   ResultSet rs = pst.executeQuery();
                   
                   if(rs.next()){
                       
                       lbl_bookId.setText(rs.getString("book_id"));
                   
                       lbl_bookName.setText(rs.getString("book_name"));
                   
                       lbl_author.setText(rs.getString("author"));
                   
                       lbl_quantity.setText(rs.getString("quantity"));
                       lbl_bookError.setText("");
                   }
                   else{
                       lbl_bookError.setText("Invalid Book ID");
                   }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    
      // to faech the student details from database and display it to student_details
    
    public void getStudentDetaiils(){
            int studentId = Integer.parseInt(txt_studentId.getText());
            
            try {
                
                  Connection con = DBConnection.getConnection();
                   PreparedStatement pst = con.prepareStatement("select Id , name , course, branch  from users where Id = ? ");
                   pst.setInt(1, studentId);
                   ResultSet rs = pst.executeQuery();
                   
                   if(rs.next()){
                       
                       lbl_studentId.setText(rs.getString("Id"));
                   
                       lbl_studentName.setText(rs.getString("name"));
                   
                       lbl_course.setText(rs.getString("course"));
                   
                       lbl_branch.setText(rs.getString("branch"));
                       lbl_studentError.setText("");
                   }
                    else{
                       lbl_studentError.setText("Invalid Student ID");
                   }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    
    //insert issue book details to database
    
    public boolean issueBook(){
            
                boolean isIssued = false;
                
            int bookId = Integer.parseInt(txt_bookId.getText());
            int Id = Integer.parseInt(txt_studentId.getText());
            String bookName = lbl_bookName.getText();
            String name = lbl_studentName.getText();
            
            Date uIssueDate = date_issueDate.getDatoFecha();
             Date uDueDate = date_dueDate.getDatoFecha();
             
             Long l1 =  uIssueDate.getTime();
             long l2 = uDueDate.getTime();
             
             java.sql.Date sIssueDate = new java.sql.Date(l1);
             java.sql.Date sDueDate = new java.sql.Date(l2);
             
       
        try {
            
              Connection con =   DBConnection.getConnection();
           String sql ="insert into issue_book_details (book_id  , book_name ,student_id ,"
                   + " student_name ,  issue_date  , due_date , status ) values (?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
             pst.setInt(1, bookId);
           pst.setString(2, bookName);
            pst.setInt(3, Id);
           pst.setString(4, name);
           pst.setDate(5, sIssueDate);
           pst.setDate(6, sDueDate);
           pst.setString(7, "pending");
           
            int rowCount = pst.executeUpdate();
           if(rowCount > 0){
               isIssued = true;
           }
           else{
               isIssued = false;
           }
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
             
    return isIssued;
    }
    
    
    //updating book count
    
    public void updateBookCount(){
        
         int bookId = Integer.parseInt(txt_bookId.getText());
         try {
             
             Connection con =   DBConnection.getConnection();
             
              String sql  = "update book_details set quantity = quantity -1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
               pst.setInt(1, bookId);
               
                int rowCount = pst.executeUpdate();
           if(rowCount > 0){
                JOptionPane.showMessageDialog(this, "book count updated ");  
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount - 1));
                
           }
           else{
            JOptionPane.showMessageDialog(this, "can't update book count");  
           }
             
            
        } catch (Exception e) {
            e.printStackTrace();
        }
  
    }
  

// checking whether book  already allocated or not
    
       public  boolean isAlreadyIssued(){
           
           boolean isAlreadyIssued = false;
           
            int bookId = Integer.parseInt(txt_bookId.getText());
            int studentId = Integer.parseInt(txt_studentId.getText());
            
            try {
                
             Connection con =   DBConnection.getConnection();
             String sql  =  "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
             pst.setInt(2, studentId);
              pst.setString(3, "pending");
              
            ResultSet rs  = pst.executeQuery();
            if(rs.next()){
                  isAlreadyIssued = true;
           }else{
                isAlreadyIssued = false;
           }  
              
           } catch (Exception e) {
               e.printStackTrace();
        
           }

           
           return  isAlreadyIssued;
       
       
       }
    
    
 
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_branch = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        date_dueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        date_issueDate = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel75 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jPanel76 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jPanel262 = new javax.swing.JPanel();
        jLabel264 = new javax.swing.JLabel();
        jPanel263 = new javax.swing.JPanel();
        jLabel265 = new javax.swing.JLabel();
        jPanel264 = new javax.swing.JPanel();
        jLabel266 = new javax.swing.JLabel();
        jPanel265 = new javax.swing.JPanel();
        jLabel267 = new javax.swing.JLabel();
        jPanel266 = new javax.swing.JPanel();
        jLabel268 = new javax.swing.JLabel();
        jPanel268 = new javax.swing.JPanel();
        jLabel270 = new javax.swing.JLabel();
        jPanel267 = new javax.swing.JPanel();
        jLabel269 = new javax.swing.JLabel();
        jPanel269 = new javax.swing.JPanel();
        jLabel271 = new javax.swing.JLabel();
        jPanel270 = new javax.swing.JPanel();
        jLabel272 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel1.setText("Student Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 280, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 185, 330, 5));

        lbl_branch.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 200, 30));

        lbl_studentId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_studentId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 150, 30));

        lbl_studentName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_studentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 160, 30));

        lbl_course.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 190, 30));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Student ID :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Student Name :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 150, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Course :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 110, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Branch :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 110, -1));

        lbl_studentError.setFont(new java.awt.Font("Yu Gothic UI", 1, 30)); // NOI18N
        lbl_studentError.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 547, 300, 40));

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 350, 670));

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel10.setText("   Book Details");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 280, -1));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 150, 30));

        lbl_bookId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_bookId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 140, 30));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 140, 30));

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 210, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book ID :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Book Name :");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 120, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Author :");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 100, -1));

        lbl_bookError.setFont(new java.awt.Font("Yu Gothic UI", 1, 30)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(255, 204, 0));
        jPanel3.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 547, 300, 40));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Quantity :");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 110, -1));

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
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 40));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 320, 5));

        panel_main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 670));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel2.setText("Issue Book");
        panel_main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, -1, -1));

        jLabel30.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 51, 0));
        jLabel30.setText("Due Date :");
        panel_main.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 430, 110, 40));

        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 0)));
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
        panel_main.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 290, 210, 40));

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 51, 0));
        jLabel31.setText("Book Id :");
        panel_main.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 90, 40));

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 0)));
        txt_bookId.setToolTipText("");
        txt_bookId.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_bookId.setPlaceholder("Enter Book Id");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 220, 200, 40));

        date_dueDate.setColorBackground(new java.awt.Color(255, 51, 0));
        date_dueDate.setColorButtonHover(new java.awt.Color(255, 51, 0));
        date_dueDate.setColorForeground(new java.awt.Color(255, 51, 0));
        date_dueDate.setFuente(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date_dueDate.setPlaceholder("Select Due Date");
        panel_main.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 430, 230, -1));

        jLabel32.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 51, 0));
        jLabel32.setText("Student Id :");
        panel_main.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 130, 40));

        jLabel33.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 51, 0));
        jLabel33.setText("Issue Date :");
        panel_main.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 360, 120, 40));

        date_issueDate.setColorBackground(new java.awt.Color(255, 51, 0));
        date_issueDate.setColorButtonHover(new java.awt.Color(255, 51, 0));
        date_issueDate.setColorForeground(new java.awt.Color(255, 51, 0));
        date_issueDate.setFuente(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date_issueDate.setPlaceholder("Select Issue Date");
        panel_main.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 360, 230, -1));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(255, 51, 0));
        rSMaterialButtonCircle1.setText("Issue Book");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 530, 190, 60));

        jPanel12.setBackground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        panel_main.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 260, 5));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 1120, 670));

        jPanel6.setBackground(new java.awt.Color(102, 255, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 5, 35));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel3.setText("Welcome ,Admin");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, 220, 60));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 30)); // NOI18N
        jLabel4.setText(" Library Management System");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1, 400, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 34));

        jButton1.setBackground(new java.awt.Color(102, 255, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/cut.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, 50, 50));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 58));

        jPanel4.setBackground(new java.awt.Color(0, 102, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 102, 0));
        jPanel5.setPreferredSize(new java.awt.Dimension(260, 60));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel14.setText("         Home Page");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 270, 50));

        jPanel13.setBackground(new java.awt.Color(0, 102, 0));
        jPanel13.setForeground(java.awt.Color.lightGray);
        jPanel13.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Book_26px.png"))); // NOI18N
        jLabel18.setText("     Manage Books");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });
        jPanel13.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 6, 200, 28));

        jPanel4.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 270, 40));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Features");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 90, -1));

        jPanel75.setBackground(new java.awt.Color(0, 102, 0));
        jPanel75.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel75.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel77.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel77.setForeground(java.awt.Color.white);
        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Library_26px_1.png"))); // NOI18N
        jLabel77.setText("       LMS DashBoard");
        jLabel77.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel77MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel77MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel77MouseExited(evt);
            }
        });
        jPanel75.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 0, 210, 40));

        jPanel4.add(jPanel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 270, 40));

        jPanel76.setBackground(new java.awt.Color(0, 102, 0));
        jPanel76.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel76MouseEntered(evt);
            }
        });
        jPanel76.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel78.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel78.setText("    Manage Students");
        jLabel78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel78MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel78MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel78MouseExited(evt);
            }
        });
        jPanel76.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 6, 210, 28));

        jPanel4.add(jPanel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 270, 40));

        jPanel262.setBackground(new java.awt.Color(0, 102, 0));
        jPanel262.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel262.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel262MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel262MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel262MouseExited(evt);
            }
        });
        jPanel262.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel264.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel264.setForeground(new java.awt.Color(255, 255, 255));
        jLabel264.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Sell_26px.png"))); // NOI18N
        jLabel264.setText("     Issue Book");
        jLabel264.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel264MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel264MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel264MouseExited(evt);
            }
        });
        jPanel262.add(jLabel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 6, 190, 28));

        jPanel4.add(jPanel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 270, 40));

        jPanel263.setBackground(new java.awt.Color(0, 102, 0));
        jPanel263.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel263.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel265.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel265.setForeground(new java.awt.Color(255, 255, 255));
        jLabel265.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabel265.setText("      Return Book");
        jLabel265.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel265MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel265MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel265MouseExited(evt);
            }
        });
        jPanel263.add(jLabel265, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 200, 40));

        jPanel4.add(jPanel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 270, 40));

        jPanel264.setBackground(new java.awt.Color(0, 102, 0));
        jPanel264.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel264.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel266.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel266.setForeground(new java.awt.Color(255, 255, 255));
        jLabel266.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel266.setText("     View Records");
        jLabel266.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel266MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel266MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel266MouseExited(evt);
            }
        });
        jPanel264.add(jLabel266, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 200, 40));

        jPanel4.add(jPanel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 270, 40));

        jPanel265.setBackground(new java.awt.Color(0, 102, 0));
        jPanel265.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel265.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel267.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel267.setForeground(new java.awt.Color(255, 255, 255));
        jLabel267.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Books_26px.png"))); // NOI18N
        jLabel267.setText("      View Issued Books");
        jLabel267.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel267MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel267MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel267MouseExited(evt);
            }
        });
        jPanel265.add(jLabel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 230, 40));

        jPanel4.add(jPanel265, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 270, 40));

        jPanel266.setBackground(new java.awt.Color(0, 102, 0));
        jPanel266.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel266.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel268.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel268.setForeground(new java.awt.Color(255, 255, 255));
        jLabel268.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel268.setText("View Register Students");
        jLabel268.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel268MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel268MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel268MouseExited(evt);
            }
        });
        jPanel266.add(jLabel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 0, 240, 40));

        jPanel268.setBackground(new java.awt.Color(0, 102, 0));
        jPanel268.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel268.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel270.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel270.setForeground(new java.awt.Color(255, 255, 255));
        jLabel270.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        jLabel270.setText("      Defaulter List");
        jLabel270.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel270MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel270MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel270MouseExited(evt);
            }
        });
        jPanel268.add(jLabel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 0, 188, 40));

        jPanel266.add(jPanel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 270, 40));

        jPanel4.add(jPanel266, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 270, 40));

        jPanel267.setBackground(new java.awt.Color(0, 102, 0));
        jPanel267.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel267.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel269.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel269.setForeground(new java.awt.Color(255, 255, 255));
        jLabel269.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Exit_26px.png"))); // NOI18N
        jLabel269.setText("       Logout");
        jLabel269.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel269MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel269MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel269MouseExited(evt);
            }
        });
        jPanel267.add(jLabel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 210, 40));

        jPanel4.add(jPanel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 270, 50));

        jPanel269.setBackground(new java.awt.Color(0, 102, 0));
        jPanel269.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel269.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel271.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel271.setForeground(new java.awt.Color(255, 255, 255));
        jLabel271.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        jLabel271.setText("      Defaulter List");
        jLabel271.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel271MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel271MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel271MouseExited(evt);
            }
        });
        jPanel269.add(jLabel271, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 200, 40));

        jPanel270.setBackground(new java.awt.Color(0, 102, 0));
        jPanel270.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel270.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel272.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel272.setForeground(new java.awt.Color(255, 255, 255));
        jLabel272.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        jLabel272.setText("      Defaulter List");
        jLabel272.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel272MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel272MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel272MouseExited(evt);
            }
        });
        jPanel270.add(jLabel272, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 0, 188, 40));

        jPanel269.add(jPanel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 270, 40));

        jPanel4.add(jPanel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 270, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 250, 670));

        setSize(new java.awt.Dimension(1368, 727));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost

              if (!txt_studentId.getText().equals("")) {
            getStudentDetaiils();
        }
            
            
        
                // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        if (!txt_bookId.getText().equals("")) {
            getBookDetaiils();
        }
            
            
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        
        if(lbl_quantity.getText().equals("0")){
            
             JOptionPane.showMessageDialog(this,"Book is not available" );  
        }
        else{ 
        if(isAlreadyIssued() == false){   
        
        if(issueBook() == true ){
                JOptionPane.showMessageDialog(this,"Book Issued Successfully" );
                updateBookCount();
            }
            else{
                JOptionPane.showMessageDialog(this,"can't issue the book" );
            }
        }else{
            
            JOptionPane.showMessageDialog(this, "This Student Has already This Book ");
        }
     } 
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered

        jButton5.setBackground(mouseCrossEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        jButton5.setBackground(mouseCrossExitColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        setVisible(false);
        HomePage home = new HomePage();
        home.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(mouseCrossEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited

        jButton1.setBackground(mouseEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        setVisible(false);
        HomePage home = new  HomePage();
        home.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        jPanel5.setBackground(mouseHomeEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        jPanel5.setBackground(mouseExitColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked

        setVisible(false);
        ManageBooks books = new ManageBooks();
        books.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered

        jPanel13.setBackground(mouseEnterColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        jPanel13.setBackground(mouseExitColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel77MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel77MouseClicked
        setVisible(false);
        LMSDashBoard lmsdashboard = new  LMSDashBoard();
        lmsdashboard.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel77MouseClicked

    private void jLabel77MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel77MouseEntered

        jPanel75.setBackground(mouseEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel77MouseEntered

    private void jLabel77MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel77MouseExited

        jPanel75.setBackground(mouseExitColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel77MouseExited

    private void jLabel78MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel78MouseClicked

        setVisible(false);
        ManageStudents  managestudent = new ManageStudents();
        managestudent.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel78MouseClicked

    private void jLabel78MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel78MouseEntered

        jPanel76.setBackground(mouseEnterColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel78MouseEntered

    private void jLabel78MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel78MouseExited

        jPanel76.setBackground(mouseExitColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel78MouseExited

    private void jPanel76MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel76MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel76MouseEntered

    private void jLabel264MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel264MouseClicked

        setVisible(false);
        IssueBook book = new IssueBook();
        book.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel264MouseClicked

    private void jLabel264MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel264MouseEntered
        jPanel262.setBackground(mouseEnterColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel264MouseEntered

    private void jLabel264MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel264MouseExited

        jPanel262.setBackground(mouseExitColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel264MouseExited

    private void jPanel262MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel262MouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel262MouseClicked

    private void jPanel262MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel262MouseEntered

        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel262MouseEntered

    private void jPanel262MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel262MouseExited

        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel262MouseExited

    private void jLabel265MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel265MouseClicked

        setVisible(false);
        ReturnBook returnbook = new ReturnBook();
        returnbook.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel265MouseClicked

    private void jLabel265MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel265MouseEntered

        jPanel263.setBackground(mouseEnterColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel265MouseEntered

    private void jLabel265MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel265MouseExited

        jPanel263.setBackground(mouseExitColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel265MouseExited

    private void jLabel266MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel266MouseClicked

        setVisible(false);
        ViewAllRecord viewallrecord = new  ViewAllRecord();
        viewallrecord.setVisible(true);

    }//GEN-LAST:event_jLabel266MouseClicked

    private void jLabel266MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel266MouseEntered

        jPanel264.setBackground(mouseEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel266MouseEntered

    private void jLabel266MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel266MouseExited

        jPanel264.setBackground(mouseExitColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel266MouseExited

    private void jLabel267MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel267MouseClicked

        setVisible(false);
        IssueBookDetails issuebookdetails = new  IssueBookDetails();
        issuebookdetails.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel267MouseClicked

    private void jLabel267MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel267MouseEntered

        jPanel265.setBackground(mouseEnterColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel267MouseEntered

    private void jLabel267MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel267MouseExited

        jPanel265.setBackground(mouseExitColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel267MouseExited

    private void jLabel268MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel268MouseClicked

        setVisible(false);
        RegisterStudents registerstudents = new  RegisterStudents();
        registerstudents.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel268MouseClicked

    private void jLabel268MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel268MouseEntered

        jPanel266.setBackground(mouseEnterColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel268MouseEntered

    private void jLabel268MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel268MouseExited

        jPanel266.setBackground(mouseExitColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel268MouseExited

    private void jLabel270MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel270MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel270MouseClicked

    private void jLabel270MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel270MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel270MouseEntered

    private void jLabel270MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel270MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel270MouseExited

    private void jLabel269MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel269MouseClicked

        setVisible(false);
        AdminLogin adminlogin = new  AdminLogin();
        adminlogin.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel269MouseClicked

    private void jLabel269MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel269MouseEntered
        jPanel267.setBackground(mouseEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel269MouseEntered

    private void jLabel269MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel269MouseExited
        jPanel267.setBackground(mouseExitColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel269MouseExited

    private void jLabel271MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel271MouseClicked
        setVisible(false);
         DefaulterList defaulterlist = new  DefaulterList();
         defaulterlist.setVisible(true);

                // TODO add your handling code here:
    }//GEN-LAST:event_jLabel271MouseClicked

    private void jLabel271MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel271MouseEntered

        jPanel269.setBackground(mouseEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel271MouseEntered

    private void jLabel271MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel271MouseExited
        
                jPanel269.setBackground(mouseExitColor);

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel271MouseExited

    private void jLabel272MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel272MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel272MouseClicked

    private void jLabel272MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel272MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel272MouseEntered

    private void jLabel272MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel272MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel272MouseExited

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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_dueDate;
    private rojeru_san.componentes.RSDateChooser date_issueDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel264;
    private javax.swing.JLabel jLabel265;
    private javax.swing.JLabel jLabel266;
    private javax.swing.JLabel jLabel267;
    private javax.swing.JLabel jLabel268;
    private javax.swing.JLabel jLabel269;
    private javax.swing.JLabel jLabel270;
    private javax.swing.JLabel jLabel271;
    private javax.swing.JLabel jLabel272;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel262;
    private javax.swing.JPanel jPanel263;
    private javax.swing.JPanel jPanel264;
    private javax.swing.JPanel jPanel265;
    private javax.swing.JPanel jPanel266;
    private javax.swing.JPanel jPanel267;
    private javax.swing.JPanel jPanel268;
    private javax.swing.JPanel jPanel269;
    private javax.swing.JPanel jPanel270;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private javax.swing.JPanel panel_main;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
