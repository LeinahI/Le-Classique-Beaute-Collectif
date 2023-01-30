/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv_amain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.*;
import javafx.scene.chart.Chart;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author PREDATOR HELIOS 300
 */
public class B_DashboardPanel extends javax.swing.JPanel {

    /**
     * Creates new form B_DashboardPanel
     */
    //Mysql connection
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public B_DashboardPanel() {
        initComponents();
        showOnHand();
        employeeCount();
        customerCount();
        showpieChart();
        showBarChart();
    }

    //show Onhand Items
    public void showOnHand() {
        try {
            Statement st = SQLConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(inv_ONHAND) FROM inventory");
            if (rs.next()) {
                String sum = rs.getString("SUM(inv_ONHAND)");
                OHtext.setText(sum);
            }
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex);
        }
    }

    //show Employee Count
    public void employeeCount() {
        try {
            Statement st = SQLConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(emp_id) FROM employee");
            if (rs.next()) {
                String sum = rs.getString("COUNT(emp_id)");
                EmployeeText.setText(sum);
            }
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex);
        }
    }

    //show Customer Count
    public void customerCount() {
        try {
            Statement st = SQLConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(cust_ID) FROM customer");
            if (rs.next()) {
                String sum = rs.getString("COUNT(cust_ID)");
                CustText.setText(sum);
            }
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex);
        }
    }

    //Show Piechart
    public void showpieChart() {
        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset();

        try {
            Connection con = SQLConnection.getConnection();
            String sql = "select `Product_Name`, sum(`cart_qty`),sum(`total_price`) from cart group by `Product_Name`,`upc` order by `upc`,`Product_Name`";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                barDataset.setValue(rs.getString("Product_Name"), new Double(rs.getDouble("sum(`cart_qty`)")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart3D("Purchase quantity", barDataset, false, true, false);
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piechart.getTitle().setFont(new Font("Retro Computer", Font.PLAIN | Font.BOLD, 18));
        piechart.setBackgroundPaint(new Color(245, 167, 238));  //#F5A7EE
        piechart.getTitle().setPaint(Color.white);
        piePlot.setOutlineVisible(false);

        //3D piechart
        PiePlot3D PD = (PiePlot3D) piechart.getPlot();
        piePlot.setLabelGenerator(null); //remove label

        piePlot.setBackgroundPaint(new Color(245, 167, 238)); //#F5A7EE

        //create chartPanel to display chart (graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        barChartPanel.setPopupMenu(null);
        panelPieChart.removeAll();
        panelPieChart.add(barChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();

    }

    //show bar chart
    public void showBarChart() {
        try {

            String query = "select `Product_Name`,sum(`total_price`) from cart group by `Product_Name`,`upc` order by `upc`,`Product_Name`";
            //String query = "SELECT `inv_NAME`,`inv_ONHAND` FROM inventory";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(SQLConnection.getConnection(), query);
            JFreeChart chart = ChartFactory.createBarChart3D("Product Sales", "Products", "Total Sales", dataset, PlotOrientation.VERTICAL, false, true, false);
            //JFreeChart chart = ChartFactory.createBarChart3D("Item on Hand", "Products", "Quantity", dataset, PlotOrientation.VERTICAL, false, true, false);

            BarRenderer renderer = null;
            renderer = new BarRenderer();
            BarRenderer bRender = (BarRenderer) chart.getCategoryPlot().getRenderer();
            bRender.setSeriesPaint(0, new Color(188, 177, 208));

            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setRangeGridlinePaint(Color.red);

            CategoryAxis categoryAxis = (CategoryAxis) plot.getDomainAxis();
            categoryAxis.setLabelPaint(Color.white);
            categoryAxis.setLabelFont(new Font("Retro Computer", Font.BOLD, 12));
            categoryAxis.setTickLabelsVisible(false);//Product Labels

            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setAxisLineVisible(false);
            rangeAxis.setLabelPaint(Color.white);
            rangeAxis.setLabelFont(new Font("Retro Computer", Font.BOLD, 12));
            rangeAxis.setTickLabelPaint(Color.white);//Number range
            rangeAxis.setTickLabelFont(new Font("Retro Computer", Font.BOLD, 8));

            chart.getTitle().setFont(new Font("Retro Computer", Font.BOLD, 18));
            chart.getTitle().setPaint(Color.white);
            chart.setBackgroundPaint(new Color(216, 148, 167));  //#D5FAF7

            ChartPanel barChartPanel = new ChartPanel(chart);
            barChartPanel.setPopupMenu(null);//disable right click at chart
            panelPieChart1.removeAll();
            panelPieChart1.add(barChartPanel, BorderLayout.CENTER);
            panelPieChart1.validate();
        } catch (Exception e) {
            e.printStackTrace();
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

        InvoicePanel = new javax.swing.JPanel();
        OHPanel = new com.k33ptoo.components.KGradientPanel();
        noProdOH_label = new javax.swing.JLabel();
        OHicon = new javax.swing.JLabel();
        OHtext = new javax.swing.JLabel();
        noEmpPanel = new com.k33ptoo.components.KGradientPanel();
        noEmployee_label = new javax.swing.JLabel();
        EmployeeIcon_lbl = new javax.swing.JLabel();
        EmployeeText = new javax.swing.JLabel();
        noCustPanel = new com.k33ptoo.components.KGradientPanel();
        noCust_label = new javax.swing.JLabel();
        CustEmployeeIcon_lbl = new javax.swing.JLabel();
        CustText = new javax.swing.JLabel();
        phPanel = new com.k33ptoo.components.KGradientPanel();
        panelPieChart = new com.k33ptoo.components.KGradientPanel();
        phPanel1 = new com.k33ptoo.components.KGradientPanel();
        panelPieChart1 = new com.k33ptoo.components.KGradientPanel();

        InvoicePanel.setBackground(new java.awt.Color(245, 215, 189));
        InvoicePanel.setForeground(new java.awt.Color(245, 215, 189));
        InvoicePanel.setMaximumSize(new java.awt.Dimension(890, 650));
        InvoicePanel.setPreferredSize(new java.awt.Dimension(890, 650));

        OHPanel.setBackground(new java.awt.Color(245, 215, 189));
        OHPanel.setForeground(new java.awt.Color(245, 215, 189));
        OHPanel.setkBorderRadius(40);
        OHPanel.setkEndColor(new java.awt.Color(201, 189, 223));
        OHPanel.setkStartColor(new java.awt.Color(201, 189, 223));
        OHPanel.setMaximumSize(new java.awt.Dimension(220, 150));
        OHPanel.setMinimumSize(new java.awt.Dimension(220, 150));
        OHPanel.setPreferredSize(new java.awt.Dimension(220, 150));

        noProdOH_label.setBackground(new java.awt.Color(0, 0, 0));
        noProdOH_label.setFont(new java.awt.Font("Retro Computer", 1, 10)); // NOI18N
        noProdOH_label.setForeground(new java.awt.Color(255, 255, 255));
        noProdOH_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noProdOH_label.setText("No. of Products on hand");
        noProdOH_label.setMaximumSize(new java.awt.Dimension(200, 15));
        noProdOH_label.setMinimumSize(new java.awt.Dimension(200, 15));
        noProdOH_label.setPreferredSize(new java.awt.Dimension(200, 15));

        OHicon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OHicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/cosmetic_64.png"))); // NOI18N

        OHtext.setBackground(new java.awt.Color(255, 255, 255));
        OHtext.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        OHtext.setForeground(new java.awt.Color(255, 255, 255));
        OHtext.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        OHtext.setText("1");

        javax.swing.GroupLayout OHPanelLayout = new javax.swing.GroupLayout(OHPanel);
        OHPanel.setLayout(OHPanelLayout);
        OHPanelLayout.setHorizontalGroup(
            OHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(OHicon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OHtext, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(noProdOH_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        OHPanelLayout.setVerticalGroup(
            OHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OHPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noProdOH_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(OHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(OHicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OHtext, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        noEmpPanel.setBackground(new java.awt.Color(245, 215, 189));
        noEmpPanel.setForeground(new java.awt.Color(245, 215, 189));
        noEmpPanel.setkBorderRadius(40);
        noEmpPanel.setkEndColor(new java.awt.Color(104, 179, 214));
        noEmpPanel.setkStartColor(new java.awt.Color(104, 179, 214));
        noEmpPanel.setMaximumSize(new java.awt.Dimension(220, 150));
        noEmpPanel.setMinimumSize(new java.awt.Dimension(220, 150));

        noEmployee_label.setBackground(new java.awt.Color(0, 0, 0));
        noEmployee_label.setFont(new java.awt.Font("Retro Computer", 1, 10)); // NOI18N
        noEmployee_label.setForeground(new java.awt.Color(255, 255, 255));
        noEmployee_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noEmployee_label.setText("No. of employees");
        noEmployee_label.setMaximumSize(new java.awt.Dimension(200, 15));
        noEmployee_label.setMinimumSize(new java.awt.Dimension(200, 15));
        noEmployee_label.setPreferredSize(new java.awt.Dimension(200, 15));

        EmployeeIcon_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeIcon_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/name_tag_64px.png"))); // NOI18N

        EmployeeText.setBackground(new java.awt.Color(255, 255, 255));
        EmployeeText.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        EmployeeText.setForeground(new java.awt.Color(255, 255, 255));
        EmployeeText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeText.setText("1");

        javax.swing.GroupLayout noEmpPanelLayout = new javax.swing.GroupLayout(noEmpPanel);
        noEmpPanel.setLayout(noEmpPanelLayout);
        noEmpPanelLayout.setHorizontalGroup(
            noEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noEmpPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(EmployeeIcon_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeeText, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(noEmployee_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        noEmpPanelLayout.setVerticalGroup(
            noEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, noEmpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noEmployee_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(noEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EmployeeIcon_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EmployeeText, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        noCustPanel.setBackground(new java.awt.Color(245, 215, 189));
        noCustPanel.setForeground(new java.awt.Color(245, 215, 189));
        noCustPanel.setkBorderRadius(40);
        noCustPanel.setkEndColor(new java.awt.Color(85, 209, 176));
        noCustPanel.setkStartColor(new java.awt.Color(85, 209, 176));
        noCustPanel.setMaximumSize(new java.awt.Dimension(220, 150));
        noCustPanel.setMinimumSize(new java.awt.Dimension(220, 150));

        noCust_label.setBackground(new java.awt.Color(0, 0, 0));
        noCust_label.setFont(new java.awt.Font("Retro Computer", 1, 10)); // NOI18N
        noCust_label.setForeground(new java.awt.Color(255, 255, 255));
        noCust_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noCust_label.setText("No. of customers");
        noCust_label.setMaximumSize(new java.awt.Dimension(200, 15));
        noCust_label.setMinimumSize(new java.awt.Dimension(200, 15));
        noCust_label.setPreferredSize(new java.awt.Dimension(200, 15));

        CustEmployeeIcon_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CustEmployeeIcon_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/customer_64px.png"))); // NOI18N

        CustText.setBackground(new java.awt.Color(255, 255, 255));
        CustText.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        CustText.setForeground(new java.awt.Color(255, 255, 255));
        CustText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CustText.setText("1");

        javax.swing.GroupLayout noCustPanelLayout = new javax.swing.GroupLayout(noCustPanel);
        noCustPanel.setLayout(noCustPanelLayout);
        noCustPanelLayout.setHorizontalGroup(
            noCustPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noCustPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(CustEmployeeIcon_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CustText, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(noCust_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        noCustPanelLayout.setVerticalGroup(
            noCustPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, noCustPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noCust_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(noCustPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CustEmployeeIcon_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CustText, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        phPanel.setBackground(new java.awt.Color(245, 215, 189));
        phPanel.setForeground(new java.awt.Color(245, 215, 189));
        phPanel.setkBorderRadius(40);
        phPanel.setkEndColor(new java.awt.Color(245, 167, 238));
        phPanel.setkStartColor(new java.awt.Color(245, 167, 238));
        phPanel.setMaximumSize(new java.awt.Dimension(350, 350));
        phPanel.setMinimumSize(new java.awt.Dimension(350, 350));

        panelPieChart.setBackground(new java.awt.Color(245, 167, 238));
        panelPieChart.setForeground(new java.awt.Color(245, 167, 238));
        panelPieChart.setkBorderRadius(40);
        panelPieChart.setkEndColor(new java.awt.Color(245, 167, 238));
        panelPieChart.setkStartColor(new java.awt.Color(245, 167, 238));
        panelPieChart.setMaximumSize(new java.awt.Dimension(320, 320));
        panelPieChart.setMinimumSize(new java.awt.Dimension(320, 320));
        panelPieChart.setPreferredSize(new java.awt.Dimension(320, 320));
        panelPieChart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout phPanelLayout = new javax.swing.GroupLayout(phPanel);
        phPanel.setLayout(phPanelLayout);
        phPanelLayout.setHorizontalGroup(
            phPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        phPanelLayout.setVerticalGroup(
            phPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, phPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(panelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        phPanel1.setBackground(new java.awt.Color(245, 215, 189));
        phPanel1.setForeground(new java.awt.Color(245, 215, 189));
        phPanel1.setkBorderRadius(40);
        phPanel1.setkEndColor(new java.awt.Color(216, 148, 167));
        phPanel1.setkStartColor(new java.awt.Color(216, 148, 167));
        phPanel1.setMaximumSize(new java.awt.Dimension(350, 350));
        phPanel1.setMinimumSize(new java.awt.Dimension(350, 350));

        panelPieChart1.setBackground(new java.awt.Color(216, 148, 167));
        panelPieChart1.setForeground(new java.awt.Color(216, 148, 167));
        panelPieChart1.setkBorderRadius(40);
        panelPieChart1.setkEndColor(new java.awt.Color(216, 148, 167));
        panelPieChart1.setkStartColor(new java.awt.Color(216, 148, 167));
        panelPieChart1.setMaximumSize(new java.awt.Dimension(320, 320));
        panelPieChart1.setMinimumSize(new java.awt.Dimension(320, 320));
        panelPieChart1.setPreferredSize(new java.awt.Dimension(320, 320));
        panelPieChart1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout phPanel1Layout = new javax.swing.GroupLayout(phPanel1);
        phPanel1.setLayout(phPanel1Layout);
        phPanel1Layout.setHorizontalGroup(
            phPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelPieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        phPanel1Layout.setVerticalGroup(
            phPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelPieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout InvoicePanelLayout = new javax.swing.GroupLayout(InvoicePanel);
        InvoicePanel.setLayout(InvoicePanelLayout);
        InvoicePanelLayout.setHorizontalGroup(
            InvoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InvoicePanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(InvoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InvoicePanelLayout.createSequentialGroup()
                        .addComponent(phPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(phPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InvoicePanelLayout.createSequentialGroup()
                        .addComponent(OHPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(noEmpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(noCustPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        InvoicePanelLayout.setVerticalGroup(
            InvoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InvoicePanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(InvoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(noCustPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noEmpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OHPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(InvoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(phPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(InvoicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(InvoicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CustEmployeeIcon_lbl;
    private javax.swing.JLabel CustText;
    private javax.swing.JLabel EmployeeIcon_lbl;
    private javax.swing.JLabel EmployeeText;
    private javax.swing.JPanel InvoicePanel;
    private com.k33ptoo.components.KGradientPanel OHPanel;
    private javax.swing.JLabel OHicon;
    private javax.swing.JLabel OHtext;
    private com.k33ptoo.components.KGradientPanel noCustPanel;
    private javax.swing.JLabel noCust_label;
    private com.k33ptoo.components.KGradientPanel noEmpPanel;
    private javax.swing.JLabel noEmployee_label;
    private javax.swing.JLabel noProdOH_label;
    private com.k33ptoo.components.KGradientPanel panelPieChart;
    private com.k33ptoo.components.KGradientPanel panelPieChart1;
    private com.k33ptoo.components.KGradientPanel phPanel;
    private com.k33ptoo.components.KGradientPanel phPanel1;
    // End of variables declaration//GEN-END:variables
}
