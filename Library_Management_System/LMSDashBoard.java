/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Library_Management_System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import java.sql.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author TOSHIBA
 */
public class LMSDashBoard extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    Color mouseEnterColor = new Color(102, 255, 51);
    Color mouseExitColor = new Color(0, 102, 0);
    Color mouseCrossEnterColor = new Color(255,51,0);
    Color mouseCrossExitColor = new Color(255, 255, 255);
    Color mouseHomeEnterColor = new Color(255, 51, 51);
    DefaultTableModel model;

    public LMSDashBoard() {
        initComponents();
        showPieChart();
        setStudentDetailsToTable();
        setBookDetailsToTable();
        setDataToCards();
        setDataToQuantity();
    }

    public void setStudentDetailsToTable() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "gupta22@");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select id,name,course,branch from users");

            while (rs.next()) {

                String Id = rs.getString("id");
                String name = rs.getString("name");
                String course = rs.getString("course");
                String branch = rs.getString("branch");

                Object[] obj = {Id, name, course, branch};
                model = (DefaultTableModel) tbl_studentdetails.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBookDetailsToTable() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "gupta22@");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book_details");

            while (rs.next()) {

                String bookId = rs.getString("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");

                Object[] obj = {bookId, bookName, author, quantity};
                model = (DefaultTableModel) tbl_bookdetails.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataToCards() {

        Statement st = null;
        ResultSet rs = null;

        long l = System.currentTimeMillis();
        Date todaysDate = new Date(l);

        try {

            Connection con = DBConnection.getConnection();
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("select * from book_details");
            rs.last();
            lbl_noOfBooks.setText(Integer.toString(rs.getRow()));

            rs = st.executeQuery("select * from users");
            rs.last();
            lbl_noOfStudents.setText(Integer.toString(rs.getRow()));

            //show only pending details
            rs = st.executeQuery("select * from issue_book_details where status ='" + "pending" + "'");
            rs.last();
            lbl_noIssueBooks.setText(Integer.toString(rs.getRow()));

            //show all records
            //rs = st.executeQuery("select * from issue_book_details ");
            //rs.last();
            //lbl_noIssueBooks.setText(Integer.toString(rs.getRow()));
            rs = st.executeQuery("select * from issue_book_details where due_date < '" + todaysDate + "' and status = '" + "pending" + "'");
            rs.last();
            lbl_noDefaulterList.setText(Integer.toString(rs.getRow()));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void setDataToQuantity() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "gupta22@");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select  Sum(quantity) from book_details");

            if (rs.next()) {
                String sum = rs.getString("Sum(quantity)");
                lbl_noAvailableBooks.setText(sum);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPieChart() {

        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "select book_name, count(*) as issue_count from issue_book_details group by book_id  ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                barDataset.setValue(rs.getString("book_name"), new Double(rs.getDouble("issue_count")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Issue Book Ratio", barDataset, true, true, false);//explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();

        //changing pie chart blocks colors
        piePlot.setSectionPaint("IPhone 5s", new Color(255, 255, 102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102, 255, 102));
        piePlot.setSectionPaint("MotoG", new Color(255, 102, 153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0, 204, 204));

        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        PanelPieChart.removeAll();
        PanelPieChart.add(barChartPanel, BorderLayout.CENTER);
        PanelPieChart.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookdetails = new rojeru_san.complementos.RSTableMetro();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_studentdetails = new rojeru_san.complementos.RSTableMetro();
        PanelPieChart = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbl_noAvailableBooks = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbl_noDefaulterList1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lbl_noDefaulterList = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lbl_noDefaulterList3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbl_noIssueBooks = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbl_noOfStudents = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbl_noOfBooks = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
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
        setMinimumSize(new java.awt.Dimension(1300, 60));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 255, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 5, 35));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel2.setText("Welcome ,Admin");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 0, 230, 60));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 30)); // NOI18N
        jLabel3.setText(" Library Management System");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1, 400, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 34));

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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 0, 40, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 55));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setForeground(java.awt.Color.lightGray);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Lucida Sans", 1, 25)); // NOI18N
        jLabel11.setText("Book  Details");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 210, 30));

        jScrollPane1.setAlignmentX(0.0F);
        jScrollPane1.setAlignmentY(0.0F);

        tbl_bookdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Id", "Name", "Author", "Quantity"
            }
        ));
        tbl_bookdetails.setColorBackgoundHead(new java.awt.Color(255, 51, 0));
        tbl_bookdetails.setColorBordeFilas(new java.awt.Color(255, 51, 0));
        tbl_bookdetails.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl_bookdetails.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl_bookdetails.setColorSelBackgound(new java.awt.Color(102, 255, 51));
        tbl_bookdetails.setColorSelForeground(new java.awt.Color(0, 0, 0));
        tbl_bookdetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_bookdetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_bookdetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_bookdetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_bookdetails.setRowHeight(40);
        jScrollPane1.setViewportView(tbl_bookdetails);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 710, 190));

        jLabel17.setFont(new java.awt.Font("Lucida Sans", 1, 25)); // NOI18N
        jLabel17.setText("Student  Details");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 210, 30));

        jScrollPane2.setAlignmentX(0.0F);
        jScrollPane2.setAlignmentY(0.0F);

        tbl_studentdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Id", "Name", "Course", "Branch"
            }
        ));
        tbl_studentdetails.setColorBackgoundHead(new java.awt.Color(255, 51, 0));
        tbl_studentdetails.setColorBordeFilas(new java.awt.Color(255, 51, 0));
        tbl_studentdetails.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl_studentdetails.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl_studentdetails.setColorSelBackgound(new java.awt.Color(102, 255, 51));
        tbl_studentdetails.setColorSelForeground(new java.awt.Color(0, 0, 0));
        tbl_studentdetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_studentdetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_studentdetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_studentdetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_studentdetails.setRowHeight(40);
        tbl_studentdetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbl_studentdetails);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 710, 170));

        PanelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel6.add(PanelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 330, 340));

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noAvailableBooks.setFont(new java.awt.Font("Stencil", 1, 48)); // NOI18N
        lbl_noAvailableBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_noAvailableBooks.setText(" 10");
        jPanel7.add(lbl_noAvailableBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 140, 50));

        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 255, 51)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noDefaulterList1.setFont(new java.awt.Font("Stencil", 1, 48)); // NOI18N
        lbl_noDefaulterList1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_noDefaulterList1.setText(" 10");
        jPanel11.add(lbl_noDefaulterList1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 140, 50));

        jPanel7.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 200, 120));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, 170, 110));

        jLabel8.setFont(new java.awt.Font("Lucida Sans", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Quantity of Books ");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 60, 200, 30));

        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 255, 51)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noDefaulterList.setFont(new java.awt.Font("Stencil", 1, 48)); // NOI18N
        lbl_noDefaulterList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_noDefaulterList.setText(" 10");
        jPanel12.add(lbl_noDefaulterList, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 140, 50));

        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 255, 51)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noDefaulterList3.setFont(new java.awt.Font("Stencil", 1, 48)); // NOI18N
        lbl_noDefaulterList3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_noDefaulterList3.setText(" 10");
        jPanel13.add(lbl_noDefaulterList3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 140, 50));

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 200, 120));

        jPanel6.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 180, 110));

        jLabel9.setFont(new java.awt.Font("Lucida Sans", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("    Defaulter List ");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 180, 30));

        jLabel15.setFont(new java.awt.Font("Lucida Sans", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("  Issued Books");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 160, 30));

        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noIssueBooks.setFont(new java.awt.Font("Stencil", 1, 48)); // NOI18N
        lbl_noIssueBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Sell_50px.png"))); // NOI18N
        lbl_noIssueBooks.setText(" 10");
        jPanel10.add(lbl_noIssueBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 140, 50));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 180, 110));

        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 255, 51)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfStudents.setFont(new java.awt.Font("Stencil", 1, 48)); // NOI18N
        lbl_noOfStudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_People_50px.png"))); // NOI18N
        lbl_noOfStudents.setText(" 10");
        jPanel9.add(lbl_noOfStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 140, 50));

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 180, 110));

        jLabel13.setFont(new java.awt.Font("Lucida Sans", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText(" No. of students");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 170, 30));

        jLabel16.setFont(new java.awt.Font("Lucida Sans", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Set of Books");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 140, 30));

        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfBooks.setFont(new java.awt.Font("Stencil", 1, 48)); // NOI18N
        lbl_noOfBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_noOfBooks.setText(" 10");
        jPanel8.add(lbl_noOfBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 140, 50));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 180, 110));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/library-icon.png"))); // NOI18N
        jLabel1.setText("LMS  DashBoard");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 260, 60));

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 55, 280, 4));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 1080, 670));

        jPanel3.setBackground(new java.awt.Color(0, 102, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 102, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(260, 60));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel5.setText("         Home Page");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 270, 50));

        jPanel5.setBackground(new java.awt.Color(0, 102, 0));
        jPanel5.setForeground(java.awt.Color.lightGray);
        jPanel5.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Book_26px.png"))); // NOI18N
        jLabel6.setText("     Manage Books");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 6, 200, 28));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 270, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Features");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 90, -1));

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

        jPanel3.add(jPanel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 270, 40));

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

        jPanel3.add(jPanel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 270, 40));

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

        jPanel3.add(jPanel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 270, 40));

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

        jPanel3.add(jPanel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 270, 40));

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

        jPanel3.add(jPanel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 270, 40));

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

        jPanel3.add(jPanel265, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 270, 40));

        jPanel266.setBackground(new java.awt.Color(0, 102, 0));
        jPanel266.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel266.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel268.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel268.setForeground(new java.awt.Color(255, 255, 255));
        jLabel268.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel268.setText("    View Register Students");
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

        jPanel3.add(jPanel266, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 270, 40));

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

        jPanel3.add(jPanel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 270, 50));

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

        jPanel3.add(jPanel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 270, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 270, 670));

        setSize(new java.awt.Dimension(1350, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        setVisible(false);
        HomePage home = new HomePage();
        home.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        jPanel4.setBackground(mouseCrossEnterColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        jPanel4.setBackground(mouseExitColor);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        setVisible(false);
        ManageBooks books = new ManageBooks();
        books.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered

        jPanel5.setBackground(mouseEnterColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        jPanel5.setBackground(mouseExitColor);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel77MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel77MouseClicked
        setVisible(false);
        LMSDashBoard lmsdashboard = new LMSDashBoard();
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
        ManageStudents managestudent = new ManageStudents();
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
        ViewAllRecord viewallrecord = new ViewAllRecord();
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
        IssueBookDetails issuebookdetails = new IssueBookDetails();
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
        RegisterStudents registerstudents = new RegisterStudents();
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
        AdminLogin adminlogin = new AdminLogin();
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
        DefaulterList defaulterlist = new DefaulterList();
        defaulterlist.setVisible(true);


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
            java.util.logging.Logger.getLogger(LMSDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LMSDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LMSDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LMSDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LMSDashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPieChart;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_noAvailableBooks;
    private javax.swing.JLabel lbl_noDefaulterList;
    private javax.swing.JLabel lbl_noDefaulterList1;
    private javax.swing.JLabel lbl_noDefaulterList3;
    private javax.swing.JLabel lbl_noIssueBooks;
    private javax.swing.JLabel lbl_noOfBooks;
    private javax.swing.JLabel lbl_noOfStudents;
    private rojeru_san.complementos.RSTableMetro tbl_bookdetails;
    private rojeru_san.complementos.RSTableMetro tbl_studentdetails;
    // End of variables declaration//GEN-END:variables
}
