/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv_amain;

import java.awt.Image;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PREDATOR HELIOS 300
 */
public class B_SalesPanel extends javax.swing.JPanel {

    //sql
    Connection conn = null;
    ResultSet st = null;
    PreparedStatement ps = null;

    public B_SalesPanel() {
        initComponents();
        //mysql
        conn = SQLConnection.getConnection();
        salesTB_load();
        disablePaste(TxtSellQuantity);
        disablePaste(PaidAmtNumberLbl);
    }
    //disable copy paste

    public void disableCopy(JComponent component) {
        component.getInputMap().put(KeyStroke.getKeyStroke("control C"), "none");
    }

    public void disablePaste(JComponent component) {
        component.getInputMap().put(KeyStroke.getKeyStroke("control V"), "none");
    }

    public void disableCopyPaste(JComponent component) {
        disableCopy(component);
        disablePaste(component);
    }
    public static String upc_c;//or barcode_c
    public static String cus_id = "0"; //customer id 
    public Double Stcok_qty = 0.0;//StockQTY siguro

    public void salesTB_load() {
        //load customer start
        try {

            Statement s = SQLConnection.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customer");
            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("cust_NAME"));
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                CustomersCB.setModel(com);
                //CustomersCB.setSelectedItem(null);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        //load customer end

        //load iventory start
        try {

            Statement s = SQLConnection.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM inventory");
            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("inv_NAME"));
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                ProductCB.setModel(com);

                //ProductCB.setSelectedItem(0);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        //load inventory end

        //load invoice
        try {

            Statement st = SQLConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM invoiceid where invoID=1");
            if (rs.next()) {
                InvoiceNumberLbl.setText(rs.getString("invoiceNUM"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        //update to new invoice+
        int i = Integer.valueOf(InvoiceNumberLbl.getText());
        i++;
        InvoiceNumberLbl.setText(String.valueOf(i));
    }

    public void prod_tot_cal() {
        //product calculation
        //  Double qt = Double.valueOf(SpinSellQuantity.getValue().toString());
        Double qt = Double.valueOf(TxtSellQuantity.getText());

        Double price = Double.valueOf(UnitPrice.getText());
        Double tots;

        tots = qt * price;
        TotalPrice.setText(String.valueOf(tots));

    }

    public void cart_total() {
        int numofrow = sales_table.getRowCount();
        double total = 0;
        for (int i = 0; i < numofrow; i++) {
            double value = Double.valueOf(sales_table.getValueAt(i, 5).toString());
            total += value;
        }
        TotAmtNumberLbl.setText(Double.toString(total));

        //total qty count
        int numofrows = sales_table.getRowCount();
        int totals = 0;
        for (int i = 0; i < numofrows; i++) {
            int values = Integer.valueOf(sales_table.getValueAt(i, 3).toString());
            totals += values;
        }
        TotQty.setText(Integer.toString(totals));
        //total qty count
    }

    public void tot() {
        try {

            Double paid = Double.valueOf(PaidAmtNumberLbl.getText());
            Double tota = Double.valueOf(TotAmtNumberLbl.getText());
            Double due;

            due = paid - tota;

            BalDueNumberLbl.setText(String.valueOf(due));

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public void stckup() {
        //get all table product id and sell qty
        DefaultTableModel dt = (DefaultTableModel) sales_table.getModel();
        int rc = dt.getRowCount();

        for (int i = 0; i < rc; i++) {
            String upc = dt.getValueAt(i, 2).toString(); // id or barcode
            String sell_qty = dt.getValueAt(i, 3).toString(); // id or barcode

            System.out.println(upc);
            System.out.println(sell_qty);

            try {

                Statement s = SQLConnection.getConnection().createStatement();
                ResultSet rs = s.executeQuery("SELECT inv_ONHAND From inventory WHERE upc = '" + upc + "'");

                if (rs.next()) {

                    Stcok_qty = Double.valueOf(rs.getString("inv_ONHAND"));

                }

            } catch (Exception e) {
                System.out.println(e);
            }

            Double st_qty = Stcok_qty;
            Double Sel_qty = Double.valueOf(sell_qty);

            Double new_qty = st_qty - Sel_qty;  // new qyt = Stock Qty - Sell Qty 

            String nqty = String.valueOf(new_qty);

            try {

                Statement ss = SQLConnection.getConnection().createStatement();
                ss.executeUpdate("UPDATE inventory SET inv_ONHAND ='" + nqty + "' WHERE upc ='" + upc + "'   ");
                // update new qty in product table 

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    //RCV 
    public void rcvup() {
        //Receivables will rise if the transaction isn't equals to total amount
        String cname = CustomersCB.getSelectedItem().toString();
        Double bal = Double.valueOf(BalDueNumberLbl.getText());
        Double tot = Double.valueOf(TotAmtNumberLbl.getText());

        try {
            if (bal >= 0) {
                return;
            } else if (bal < tot) {
                Statement ss = SQLConnection.getConnection().createStatement();
                ss.executeUpdate("UPDATE customer SET cust_RCV = cust_RCV +'" + Math.abs(bal) + "' WHERE cust_NAME ='" + cname + "'");
            }
            //https://stackoverflow.com/questions/13396119/how-to-add-a-value-to-existing-value-using-sql-query

        } catch (Exception e) {
            System.out.println(e);
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

        EmployeePanel = new javax.swing.JPanel();
        InvoiceNOLbl = new javax.swing.JLabel();
        InvoiceNumberLbl = new javax.swing.JLabel();
        CustomerSELbl = new javax.swing.JLabel();
        CustomersCB = new javax.swing.JComboBox<>();
        ProductSELbl = new javax.swing.JLabel();
        ProductCB = new javax.swing.JComboBox<>();
        QuantitySELbl = new javax.swing.JLabel();
        TxtSellQuantity = new javax.swing.JTextField();
        Line5 = new javax.swing.JPanel();
        UnitPriceSELbl = new javax.swing.JLabel();
        UnitPrice = new javax.swing.JLabel();
        TotalPriceSELbl = new javax.swing.JLabel();
        TotalPrice = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sales_table = new rojeru_san.complementos.TableMetro();
        AddToCartBtn = new com.k33ptoo.components.KButton();
        RemoveBtn = new com.k33ptoo.components.KButton();
        RemAllBtn = new com.k33ptoo.components.KButton();
        TotAmtLbl = new javax.swing.JLabel();
        TotAmtNumberLbl = new javax.swing.JLabel();
        BalDueLbl = new javax.swing.JLabel();
        BalDueNumberLbl = new javax.swing.JLabel();
        PayPrintBtn = new com.k33ptoo.components.KButton();
        PaidAMTLbl = new javax.swing.JLabel();
        PaidAmtNumberLbl = new javax.swing.JTextField();
        Line4 = new javax.swing.JPanel();
        TotalPriceSELbl1 = new javax.swing.JLabel();
        up_code = new javax.swing.JLabel();
        TotAmtLbl1 = new javax.swing.JLabel();
        TotQty = new javax.swing.JLabel();
        prod_img = new javax.swing.JLabel();
        StockQTY = new javax.swing.JLabel();
        StockQTYLbl = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(890, 650));
        setPreferredSize(new java.awt.Dimension(890, 650));

        EmployeePanel.setBackground(new java.awt.Color(245, 215, 189));
        EmployeePanel.setForeground(new java.awt.Color(245, 215, 189));
        EmployeePanel.setMaximumSize(new java.awt.Dimension(890, 650));
        EmployeePanel.setPreferredSize(new java.awt.Dimension(890, 650));

        InvoiceNOLbl.setBackground(new java.awt.Color(0, 0, 0));
        InvoiceNOLbl.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        InvoiceNOLbl.setForeground(new java.awt.Color(0, 0, 0));
        InvoiceNOLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InvoiceNOLbl.setText("invoice no: ");
        InvoiceNOLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        InvoiceNumberLbl.setBackground(new java.awt.Color(0, 0, 0));
        InvoiceNumberLbl.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        InvoiceNumberLbl.setForeground(new java.awt.Color(0, 0, 0));
        InvoiceNumberLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InvoiceNumberLbl.setText("01");
        InvoiceNumberLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        CustomerSELbl.setBackground(new java.awt.Color(0, 0, 0));
        CustomerSELbl.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        CustomerSELbl.setForeground(new java.awt.Color(0, 0, 0));
        CustomerSELbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CustomerSELbl.setText("customer:");
        CustomerSELbl.setMaximumSize(new java.awt.Dimension(83, 20));
        CustomerSELbl.setMinimumSize(new java.awt.Dimension(83, 20));
        CustomerSELbl.setPreferredSize(new java.awt.Dimension(83, 20));

        CustomersCB.setBackground(new java.awt.Color(255, 255, 255));
        CustomersCB.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        CustomersCB.setForeground(new java.awt.Color(0, 0, 0));
        CustomersCB.setSelectedItem(1);
        CustomersCB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CustomersCB.setMaximumSize(new java.awt.Dimension(229, 20));
        CustomersCB.setMinimumSize(new java.awt.Dimension(229, 20));
        CustomersCB.setPreferredSize(new java.awt.Dimension(229, 20));
        CustomersCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomersCBActionPerformed(evt);
            }
        });

        ProductSELbl.setBackground(new java.awt.Color(0, 0, 0));
        ProductSELbl.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        ProductSELbl.setForeground(new java.awt.Color(0, 0, 0));
        ProductSELbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ProductSELbl.setText("product:");

        ProductCB.setBackground(new java.awt.Color(255, 255, 255));
        ProductCB.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        ProductCB.setForeground(new java.awt.Color(0, 0, 0));
        ProductCB.setSelectedItem(null);
        ProductCB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProductCB.setMaximumSize(new java.awt.Dimension(229, 20));
        ProductCB.setMinimumSize(new java.awt.Dimension(229, 20));
        ProductCB.setPreferredSize(new java.awt.Dimension(229, 20));
        ProductCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductCBActionPerformed(evt);
            }
        });

        QuantitySELbl.setBackground(new java.awt.Color(0, 0, 0));
        QuantitySELbl.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        QuantitySELbl.setForeground(new java.awt.Color(0, 0, 0));
        QuantitySELbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        QuantitySELbl.setText("quantity:");
        QuantitySELbl.setPreferredSize(new java.awt.Dimension(83, 20));
        QuantitySELbl.setRequestFocusEnabled(false);

        TxtSellQuantity.setBackground(new java.awt.Color(245, 215, 189));
        TxtSellQuantity.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        TxtSellQuantity.setForeground(new java.awt.Color(0, 0, 0));
        TxtSellQuantity.setText("0");
        TxtSellQuantity.setBorder(null);
        TxtSellQuantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtSellQuantityMouseClicked(evt);
            }
        });
        TxtSellQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtSellQuantityKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtSellQuantityKeyTyped(evt);
            }
        });

        Line5.setBackground(new java.awt.Color(175, 157, 206));
        Line5.setPreferredSize(new java.awt.Dimension(138, 3));

        javax.swing.GroupLayout Line5Layout = new javax.swing.GroupLayout(Line5);
        Line5.setLayout(Line5Layout);
        Line5Layout.setHorizontalGroup(
            Line5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );
        Line5Layout.setVerticalGroup(
            Line5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        UnitPriceSELbl.setBackground(new java.awt.Color(0, 0, 0));
        UnitPriceSELbl.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        UnitPriceSELbl.setForeground(new java.awt.Color(0, 0, 0));
        UnitPriceSELbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UnitPriceSELbl.setText("unit price:");
        UnitPriceSELbl.setPreferredSize(new java.awt.Dimension(83, 20));

        UnitPrice.setBackground(new java.awt.Color(0, 0, 0));
        UnitPrice.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        UnitPrice.setForeground(new java.awt.Color(0, 0, 0));
        UnitPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UnitPrice.setText("00.00");
        UnitPrice.setPreferredSize(new java.awt.Dimension(83, 20));

        TotalPriceSELbl.setBackground(new java.awt.Color(0, 0, 0));
        TotalPriceSELbl.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        TotalPriceSELbl.setForeground(new java.awt.Color(0, 0, 0));
        TotalPriceSELbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TotalPriceSELbl.setText("total price:");
        TotalPriceSELbl.setPreferredSize(new java.awt.Dimension(83, 20));

        TotalPrice.setBackground(new java.awt.Color(0, 0, 0));
        TotalPrice.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        TotalPrice.setForeground(new java.awt.Color(0, 0, 0));
        TotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotalPrice.setText("00.00");
        TotalPrice.setPreferredSize(new java.awt.Dimension(83, 20));

        jScrollPane1.setBackground(new java.awt.Color(245, 215, 189));
        jScrollPane1.setForeground(new java.awt.Color(245, 215, 189));

        sales_table.setBackground(new java.awt.Color(245, 215, 189));
        sales_table.setForeground(new java.awt.Color(245, 215, 189));
        sales_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INID", "NAME", "UPC", "QTY", "UNIT PRICE", "TOTAL PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sales_table.setColorBackgoundHead(new java.awt.Color(142, 126, 167));
        sales_table.setColorBordeFilas(new java.awt.Color(175, 157, 206));
        sales_table.setColorBordeHead(new java.awt.Color(175, 157, 206));
        sales_table.setColorFilasBackgound1(new java.awt.Color(245, 215, 189));
        sales_table.setColorFilasBackgound2(new java.awt.Color(245, 215, 189));
        sales_table.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        sales_table.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        sales_table.setColorForegroundHead(new java.awt.Color(245, 215, 189));
        sales_table.setColorSelBackgound(new java.awt.Color(255, 255, 255));
        sales_table.setColorSelForeground(new java.awt.Color(0, 0, 0));
        sales_table.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        sales_table.setFuenteFilas(new java.awt.Font("Arial", 0, 10)); // NOI18N
        sales_table.setFuenteFilasSelect(new java.awt.Font("Arial", 0, 10)); // NOI18N
        sales_table.setFuenteHead(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        sales_table.setGridColor(new java.awt.Color(175, 157, 206));
        sales_table.setRowHeight(25);
        sales_table.setSelectionBackground(new java.awt.Color(255, 255, 255));
        sales_table.setSelectionForeground(new java.awt.Color(245, 215, 189));
        jScrollPane1.setViewportView(sales_table);
        if (sales_table.getColumnModel().getColumnCount() > 0) {
            sales_table.getColumnModel().getColumn(0).setPreferredWidth(60);
            sales_table.getColumnModel().getColumn(0).setMaxWidth(60);
            sales_table.getColumnModel().getColumn(1).setPreferredWidth(230);
            sales_table.getColumnModel().getColumn(1).setMaxWidth(230);
            sales_table.getColumnModel().getColumn(2).setPreferredWidth(80);
            sales_table.getColumnModel().getColumn(2).setMaxWidth(80);
            sales_table.getColumnModel().getColumn(3).setPreferredWidth(60);
            sales_table.getColumnModel().getColumn(3).setMaxWidth(60);
        }

        AddToCartBtn.setText("add to cart");
        AddToCartBtn.setAlignmentY(0.0F);
        AddToCartBtn.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        AddToCartBtn.setkBorderRadius(30);
        AddToCartBtn.setkEndColor(new java.awt.Color(175, 157, 206));
        AddToCartBtn.setkForeGround(new java.awt.Color(245, 215, 189));
        AddToCartBtn.setkHoverEndColor(new java.awt.Color(245, 195, 152));
        AddToCartBtn.setkHoverForeGround(new java.awt.Color(175, 157, 206));
        AddToCartBtn.setkHoverStartColor(new java.awt.Color(245, 195, 152));
        AddToCartBtn.setkPressedColor(new java.awt.Color(242, 179, 65));
        AddToCartBtn.setkStartColor(new java.awt.Color(175, 157, 206));
        AddToCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToCartBtnActionPerformed(evt);
            }
        });

        RemoveBtn.setText("remove");
        RemoveBtn.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        RemoveBtn.setkBorderRadius(30);
        RemoveBtn.setkEndColor(new java.awt.Color(175, 157, 206));
        RemoveBtn.setkForeGround(new java.awt.Color(245, 215, 189));
        RemoveBtn.setkHoverEndColor(new java.awt.Color(245, 195, 152));
        RemoveBtn.setkHoverForeGround(new java.awt.Color(175, 157, 206));
        RemoveBtn.setkHoverStartColor(new java.awt.Color(245, 195, 152));
        RemoveBtn.setkPressedColor(new java.awt.Color(242, 179, 65));
        RemoveBtn.setkStartColor(new java.awt.Color(175, 157, 206));
        RemoveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveBtnActionPerformed(evt);
            }
        });

        RemAllBtn.setText("remove all");
        RemAllBtn.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        RemAllBtn.setkBorderRadius(30);
        RemAllBtn.setkEndColor(new java.awt.Color(175, 157, 206));
        RemAllBtn.setkForeGround(new java.awt.Color(245, 215, 189));
        RemAllBtn.setkHoverEndColor(new java.awt.Color(245, 195, 152));
        RemAllBtn.setkHoverForeGround(new java.awt.Color(175, 157, 206));
        RemAllBtn.setkHoverStartColor(new java.awt.Color(245, 195, 152));
        RemAllBtn.setkPressedColor(new java.awt.Color(242, 179, 65));
        RemAllBtn.setkStartColor(new java.awt.Color(175, 157, 206));
        RemAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemAllBtnActionPerformed(evt);
            }
        });

        TotAmtLbl.setBackground(new java.awt.Color(0, 0, 0));
        TotAmtLbl.setFont(new java.awt.Font("Retro Computer", 1, 14)); // NOI18N
        TotAmtLbl.setForeground(new java.awt.Color(0, 0, 0));
        TotAmtLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TotAmtLbl.setText("total amount:");

        TotAmtNumberLbl.setBackground(new java.awt.Color(0, 0, 0));
        TotAmtNumberLbl.setFont(new java.awt.Font("Retro Computer", 1, 14)); // NOI18N
        TotAmtNumberLbl.setForeground(new java.awt.Color(0, 0, 0));
        TotAmtNumberLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotAmtNumberLbl.setText("00.00");

        BalDueLbl.setBackground(new java.awt.Color(0, 0, 0));
        BalDueLbl.setFont(new java.awt.Font("Retro Computer", 1, 14)); // NOI18N
        BalDueLbl.setForeground(new java.awt.Color(0, 0, 0));
        BalDueLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BalDueLbl.setText("balance due:");

        BalDueNumberLbl.setBackground(new java.awt.Color(0, 0, 0));
        BalDueNumberLbl.setFont(new java.awt.Font("Retro Computer", 1, 14)); // NOI18N
        BalDueNumberLbl.setForeground(new java.awt.Color(0, 0, 0));
        BalDueNumberLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BalDueNumberLbl.setText("00.00");

        PayPrintBtn.setText("pay & print");
        PayPrintBtn.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        PayPrintBtn.setkBorderRadius(30);
        PayPrintBtn.setkEndColor(new java.awt.Color(175, 157, 206));
        PayPrintBtn.setkForeGround(new java.awt.Color(245, 215, 189));
        PayPrintBtn.setkHoverEndColor(new java.awt.Color(245, 195, 152));
        PayPrintBtn.setkHoverForeGround(new java.awt.Color(175, 157, 206));
        PayPrintBtn.setkHoverStartColor(new java.awt.Color(245, 195, 152));
        PayPrintBtn.setkPressedColor(new java.awt.Color(242, 179, 65));
        PayPrintBtn.setkStartColor(new java.awt.Color(175, 157, 206));
        PayPrintBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PayPrintBtnMouseEntered(evt);
            }
        });
        PayPrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayPrintBtnActionPerformed(evt);
            }
        });

        PaidAMTLbl.setBackground(new java.awt.Color(0, 0, 0));
        PaidAMTLbl.setFont(new java.awt.Font("Retro Computer", 1, 14)); // NOI18N
        PaidAMTLbl.setForeground(new java.awt.Color(0, 0, 0));
        PaidAMTLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PaidAMTLbl.setText("paid amount:");
        PaidAMTLbl.setPreferredSize(new java.awt.Dimension(83, 20));
        PaidAMTLbl.setRequestFocusEnabled(false);

        PaidAmtNumberLbl.setBackground(new java.awt.Color(245, 215, 189));
        PaidAmtNumberLbl.setFont(new java.awt.Font("Retro Computer", 1, 14)); // NOI18N
        PaidAmtNumberLbl.setForeground(new java.awt.Color(0, 0, 0));
        PaidAmtNumberLbl.setText("00.00");
        PaidAmtNumberLbl.setBorder(null);
        PaidAmtNumberLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PaidAmtNumberLblMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PaidAmtNumberLblMouseExited(evt);
            }
        });
        PaidAmtNumberLbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PaidAmtNumberLblKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PaidAmtNumberLblKeyTyped(evt);
            }
        });

        Line4.setBackground(new java.awt.Color(175, 157, 206));
        Line4.setPreferredSize(new java.awt.Dimension(138, 3));

        javax.swing.GroupLayout Line4Layout = new javax.swing.GroupLayout(Line4);
        Line4.setLayout(Line4Layout);
        Line4Layout.setHorizontalGroup(
            Line4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );
        Line4Layout.setVerticalGroup(
            Line4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        TotalPriceSELbl1.setBackground(new java.awt.Color(0, 0, 0));
        TotalPriceSELbl1.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        TotalPriceSELbl1.setForeground(new java.awt.Color(0, 0, 0));
        TotalPriceSELbl1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TotalPriceSELbl1.setText("upc:");
        TotalPriceSELbl1.setPreferredSize(new java.awt.Dimension(83, 20));

        up_code.setBackground(new java.awt.Color(0, 0, 0));
        up_code.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        up_code.setForeground(new java.awt.Color(0, 0, 0));
        up_code.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        up_code.setText("000000000000");
        up_code.setPreferredSize(new java.awt.Dimension(83, 20));

        TotAmtLbl1.setBackground(new java.awt.Color(0, 0, 0));
        TotAmtLbl1.setFont(new java.awt.Font("Retro Computer", 1, 14)); // NOI18N
        TotAmtLbl1.setForeground(new java.awt.Color(0, 0, 0));
        TotAmtLbl1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TotAmtLbl1.setText("total qty:");

        TotQty.setBackground(new java.awt.Color(0, 0, 0));
        TotQty.setFont(new java.awt.Font("Retro Computer", 1, 14)); // NOI18N
        TotQty.setForeground(new java.awt.Color(0, 0, 0));
        TotQty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotQty.setText("0");

        prod_img.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 189, 223), 2));
        prod_img.setMaximumSize(new java.awt.Dimension(100, 100));
        prod_img.setMinimumSize(new java.awt.Dimension(100, 100));
        prod_img.setPreferredSize(new java.awt.Dimension(100, 100));

        StockQTY.setBackground(new java.awt.Color(0, 0, 0));
        StockQTY.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        StockQTY.setForeground(new java.awt.Color(0, 0, 0));
        StockQTY.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        StockQTY.setText("0.0");
        StockQTY.setPreferredSize(new java.awt.Dimension(83, 20));

        StockQTYLbl.setBackground(new java.awt.Color(0, 0, 0));
        StockQTYLbl.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        StockQTYLbl.setForeground(new java.awt.Color(0, 0, 0));
        StockQTYLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        StockQTYLbl.setText("stock qty:");
        StockQTYLbl.setPreferredSize(new java.awt.Dimension(83, 20));

        javax.swing.GroupLayout EmployeePanelLayout = new javax.swing.GroupLayout(EmployeePanel);
        EmployeePanel.setLayout(EmployeePanelLayout);
        EmployeePanelLayout.setHorizontalGroup(
            EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeePanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PayPrintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EmployeePanelLayout.createSequentialGroup()
                            .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TotAmtLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PaidAMTLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeePanelLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(TotQty, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(EmployeePanelLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Line4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(PaidAmtNumberLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(56, 56, 56)
                            .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TotAmtLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(BalDueLbl, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(18, 18, 18)
                            .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(BalDueNumberLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TotAmtNumberLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InvoiceNOLbl)
                            .addGroup(EmployeePanelLayout.createSequentialGroup()
                                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(EmployeePanelLayout.createSequentialGroup()
                                        .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(EmployeePanelLayout.createSequentialGroup()
                                                .addComponent(TotalPriceSELbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(184, 184, 184))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeePanelLayout.createSequentialGroup()
                                                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(ProductSELbl, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(CustomerSELbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(ProductCB, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(CustomersCB, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(up_code, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)))
                                        .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeePanelLayout.createSequentialGroup()
                                                .addComponent(StockQTYLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(StockQTY, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(EmployeePanelLayout.createSequentialGroup()
                                                    .addComponent(UnitPriceSELbl, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeePanelLayout.createSequentialGroup()
                                                    .addComponent(TotalPriceSELbl, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(TotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(EmployeePanelLayout.createSequentialGroup()
                                        .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(EmployeePanelLayout.createSequentialGroup()
                                                .addGap(276, 276, 276)
                                                .addComponent(QuantitySELbl, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeePanelLayout.createSequentialGroup()
                                                .addGap(170, 170, 170)
                                                .addComponent(InvoiceNumberLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Line5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TxtSellQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(12, 12, 12)
                                .addComponent(prod_img, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(27, 27, 27)
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddToCartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(RemoveBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RemAllBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        EmployeePanelLayout.setVerticalGroup(
            EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(EmployeePanelLayout.createSequentialGroup()
                        .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(EmployeePanelLayout.createSequentialGroup()
                                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(InvoiceNOLbl)
                                    .addComponent(InvoiceNumberLbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(CustomerSELbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CustomersCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5))
                            .addGroup(EmployeePanelLayout.createSequentialGroup()
                                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(QuantitySELbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtSellQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(Line5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EmployeePanelLayout.createSequentialGroup()
                                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ProductSELbl)
                                    .addComponent(ProductCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TotalPriceSELbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(up_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(UnitPriceSELbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(StockQTYLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(StockQTY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalPriceSELbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(prod_img, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EmployeePanelLayout.createSequentialGroup()
                        .addComponent(AddToCartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RemoveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RemAllBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TotAmtLbl)
                        .addComponent(TotAmtNumberLbl))
                    .addGroup(EmployeePanelLayout.createSequentialGroup()
                        .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PaidAmtNumberLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PaidAMTLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(Line4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BalDueLbl)
                    .addComponent(BalDueNumberLbl)
                    .addComponent(TotQty)
                    .addComponent(TotAmtLbl1))
                .addGap(18, 18, 18)
                .addComponent(PayPrintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(EmployeePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(EmployeePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddToCartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToCartBtnActionPerformed
        //ATC prod deets
        try {
            //  Double sell_qty = (Double) SpinSellQuantity.getValue();
            Double stk_qty = Double.valueOf(StockQTY.getText());//StockQTY
            //     String sell_qty = TxtSellQuantity.getText(); //TxtSellQuantity
            Double sell_qty = Double.parseDouble(TxtSellQuantity.getText());

            if (up_code.getText().equals("000000000000")) {
                JOptionPane.showMessageDialog(null, "You haven't selected a product!");
            } else if (stk_qty == 0) {
                JOptionPane.showMessageDialog(null, "Product out of stock!");
            } /*else if (TxtSellQuantity.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Quantity has empty value!");
        }*/ else if (sell_qty == 0) {
                JOptionPane.showMessageDialog(null, "Quantity has zero value!");
            } else if (sell_qty > stk_qty) {
                JOptionPane.showMessageDialog(null, "Stock Have " + stk_qty + " Quantity Only");
            } else {
                DefaultTableModel dt = (DefaultTableModel) sales_table.getModel();
                Vector v = new Vector();

                v.add(InvoiceNumberLbl.getText());
                v.add(ProductCB.getSelectedItem().toString());
                v.add(up_code.getText());//upc or bcode
                v.add(TxtSellQuantity.getText());
                v.add(UnitPrice.getText());
                v.add(TotalPrice.getText());

                dt.addRow(v);
                cart_total();
                tot();

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity has empty value!");
        }


    }//GEN-LAST:event_AddToCartBtnActionPerformed

    private void RemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveBtnActionPerformed
        //selected remove
        try {
            DefaultTableModel dt = (DefaultTableModel) sales_table.getModel();
            int rw = sales_table.getSelectedRow();
            if (sales_table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table doesn't have a item!");
            } else if (sales_table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "You haven't selected an item from table!");
            } else {
                dt.removeRow(rw);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cart_total();
        tot();
    }//GEN-LAST:event_RemoveBtnActionPerformed

    private void RemAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemAllBtnActionPerformed
        //remove all rows
        try {
            DefaultTableModel dt = (DefaultTableModel) sales_table.getModel();

            dt.setRowCount(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        cart_total();
        tot();
        TotAmtNumberLbl.setText("00.00");
        BalDueNumberLbl.setText("00.00");
        PaidAmtNumberLbl.setText("00.00");
    }//GEN-LAST:event_RemAllBtnActionPerformed

    private void PaidAmtNumberLblKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaidAmtNumberLblKeyReleased
        tot();
    }//GEN-LAST:event_PaidAmtNumberLblKeyReleased

    private void PayPrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayPrintBtnActionPerformed
        //data send to database
        if (PaidAmtNumberLbl.getText().isEmpty()) {
            PaidAmtNumberLbl.setText("00.00");//prevent the nullpointerexcetion error when paid amount is empty
        }

        String in_id = InvoiceNumberLbl.getText();
        String cname = CustomersCB.getSelectedItem().toString();
        String totqty = TotQty.getText();
        String tot_bil = TotAmtNumberLbl.getText();
        String bal = BalDueNumberLbl.getText();

        // paid check
        Double tot = Double.valueOf(TotAmtNumberLbl.getText());
        Double pid = Double.valueOf(PaidAmtNumberLbl.getText());

        try {
            //sales DB

            if (sales_table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table doesn't have a item!");
                return;
            } /*else if (pid.equals(0.0)) {
                JOptionPane.showMessageDialog(null, "Unsettled transaction isn't allowed!");
                return;
            } else if (tot > pid) {
                JOptionPane.showMessageDialog(null, "Partial transaction isn't allowed!");
                return;
            }*/ else if (CustomersCB.getSelectedIndex() == 0 && pid.equals(0.0)) {
                JOptionPane.showMessageDialog(null, "Unsettled transaction is not allowed in this customer");
                return;

            } else if (CustomersCB.getSelectedIndex() == 0 && tot > pid) {
                JOptionPane.showMessageDialog(null, "Partial transaction is not allowed in this customer");
                return;
            } else {

                //Status check
                String Status = null;
                if (pid.equals(0.0)) {

                    Status = "Unsettled";

                } else if (tot > pid) {
                    Status = "Partial";

                } else if (tot <= pid) {
                    Status = "Paid";
                }

                Statement st = SQLConnection.getConnection().createStatement();
                st.executeUpdate("INSERT INTO sales (INID, Cust_ID, Cust_NAME, total_qty, total_bill, sales_status, sales_balance) VALUES('" + in_id + "','" + cus_id + "','" + cname + "','" + totqty + "','" + tot_bil + "','" + Status + "','" + bal + "')");

            }

        } catch (NumberFormatException | SQLException e) {
            System.out.println(e);

        }

        try {

            // if (sales_table.getRowCount() == 0) {
            //     JOptionPane.showMessageDialog(null, "Table doesn't have a item!");
            //  } else {
            //cart db
            DefaultTableModel dt = (DefaultTableModel) sales_table.getModel();
            int rc = dt.getRowCount();
            for (int i = 0; i < rc; i++) {
                String inid = dt.getValueAt(i, 0).toString();
                String prod_name = dt.getValueAt(i, 1).toString();
                String upc = dt.getValueAt(i, 2).toString();
                String qty = dt.getValueAt(i, 3).toString();
                String unit_price = dt.getValueAt(i, 4).toString();
                String total_price = dt.getValueAt(i, 5).toString();
                //CART database
                Statement s = SQLConnection.getConnection().createStatement();
                s.executeUpdate(" INSERT INTO cart (INID, Product_Name, upc, cart_qty, unit_price, total_price) VALUES ('" + inid + "','" + prod_name + "','" + upc + "','" + qty + "','" + unit_price + "','" + total_price + "') ");

            }
            JOptionPane.showMessageDialog(null, "Data Saved");

            //   }
        } catch (Exception e) {
            System.out.println(e);

        }

        //save last invoice
        try {
            String id = InvoiceNumberLbl.getText();
            Statement st = SQLConnection.getConnection().createStatement();
            st.executeUpdate("UPDATE invoiceid SET invoiceNUM='" + id + "' where invoID=1");

        } catch (Exception e) {
            System.out.println(e);
        }

        //print or view ireport bill
        try {

            if (sales_table.getRowCount() == 0) {
                System.out.println("Error: Table doesn't have a item!");
            } else {
                HashMap params = new HashMap();

                params.put("inv_id"/*inv_id = Jaspersoft ireport param name from print.jasper*/, InvoiceNumberLbl.getText());
                //Print Receipt
                Inv_ReceiptPrint receiptView = new Inv_ReceiptPrint("src\\inv_reports\\print.jasper", params);

                receiptView.setVisible(true);
                receiptView.setLocationRelativeTo(null);//Center
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        stckup();//sell QTY & stock UD
        rcvup();//rcv will up if transaction is not fully paid
        salesTB_load();

        //delete table rows and refresh everything
        try {
            DefaultTableModel dt = (DefaultTableModel) sales_table.getModel();

            StockQTY.setText("0.0");
            TxtSellQuantity.setText("0");
            ProductCB.setSelectedItem(0);
            CustomersCB.setSelectedItem(0);
            up_code.setText("000000000000");
            UnitPrice.setText("00.00");
            TotalPrice.setText("00.00");
            PaidAmtNumberLbl.setText("00.00");
            TotQty.setText("0");
            TotAmtNumberLbl.setText("00.00");
            BalDueNumberLbl.setText("00.00");
            prod_img.setIcon(null);
            dt.setRowCount(0);
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_PayPrintBtnActionPerformed

    private void CustomersCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomersCBActionPerformed
        //get customer id
        String cname = CustomersCB.getSelectedItem().toString();
        try {
            Statement s = SQLConnection.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT cust_ID,cust_NAME FROM customer WHERE cust_NAME='" + cname + "'");

            if (rs.next()) {
                cus_id = (rs.getString("cust_ID"));

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_CustomersCBActionPerformed

    private void TxtSellQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSellQuantityKeyReleased
        try {
            prod_tot_cal();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_TxtSellQuantityKeyReleased

    private void TxtSellQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSellQuantityKeyTyped
        char c = evt.getKeyChar();
        if (TxtSellQuantity.getText().length() >= 10) {
            evt.consume();
        } else if (!Character.isDigit(c)) {
            evt.consume();
        } else if (Character.isAlphabetic(c)) {
            evt.consume();
        } else if (Character.isLetter(c)) {
            evt.consume();
        } else if (Character.isSpaceChar(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtSellQuantityKeyTyped

    private void ProductCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductCBActionPerformed
        String name = ProductCB.getSelectedItem().toString();
        try {
            Statement s = SQLConnection.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT upc,inv_PRICE,inv_IMG,inv_ONHAND FROM inventory WHERE inv_NAME='" + name + "'");

            if (rs.next()) {
                UnitPrice.setText(rs.getString("inv_PRICE"));
                up_code.setText(rs.getString("upc"));//barcode
                StockQTY.setText(rs.getString("inv_ONHAND"));
                //image display start
                byte[] img = rs.getBytes("inv_IMG");
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                Image myImg = im.getScaledInstance(prod_img.getWidth(), prod_img.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                prod_img.setIcon(newImage);
                //image display end
            }
            prod_tot_cal();

        } catch (Exception ex) {
            System.out.println(ex);
        }


    }//GEN-LAST:event_ProductCBActionPerformed

    private void TxtSellQuantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtSellQuantityMouseClicked
        TxtSellQuantity.setText("");
    }//GEN-LAST:event_TxtSellQuantityMouseClicked

    private void PaidAmtNumberLblKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaidAmtNumberLblKeyTyped
        char c = evt.getKeyChar();
        if (PaidAmtNumberLbl.getText().length() >= 10) {
            evt.consume();
        } else if (!Character.isDigit(c)) {
            evt.consume();
        } else if (Character.isAlphabetic(c)) {
            evt.consume();
        } else if (Character.isLetter(c)) {
            evt.consume();
        } else if (Character.isSpaceChar(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_PaidAmtNumberLblKeyTyped

    private void PaidAmtNumberLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaidAmtNumberLblMouseClicked
        PaidAmtNumberLbl.setText("");
    }//GEN-LAST:event_PaidAmtNumberLblMouseClicked

    private void PaidAmtNumberLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaidAmtNumberLblMouseExited
        if (PaidAmtNumberLbl.getText().isEmpty()) {
            PaidAmtNumberLbl.setText("00.00");
        }
    }//GEN-LAST:event_PaidAmtNumberLblMouseExited

    private void PayPrintBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PayPrintBtnMouseEntered
        if (PaidAmtNumberLbl.getText().isEmpty()) {
            PaidAmtNumberLbl.setText("00.00");
        }
    }//GEN-LAST:event_PayPrintBtnMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton AddToCartBtn;
    private javax.swing.JLabel BalDueLbl;
    private javax.swing.JLabel BalDueNumberLbl;
    private javax.swing.JLabel CustomerSELbl;
    private javax.swing.JComboBox<String> CustomersCB;
    private javax.swing.JPanel EmployeePanel;
    private javax.swing.JLabel InvoiceNOLbl;
    private javax.swing.JLabel InvoiceNumberLbl;
    private javax.swing.JPanel Line4;
    private javax.swing.JPanel Line5;
    private javax.swing.JLabel PaidAMTLbl;
    private javax.swing.JTextField PaidAmtNumberLbl;
    private com.k33ptoo.components.KButton PayPrintBtn;
    private javax.swing.JComboBox<String> ProductCB;
    private javax.swing.JLabel ProductSELbl;
    private javax.swing.JLabel QuantitySELbl;
    private com.k33ptoo.components.KButton RemAllBtn;
    private com.k33ptoo.components.KButton RemoveBtn;
    private javax.swing.JLabel StockQTY;
    private javax.swing.JLabel StockQTYLbl;
    private javax.swing.JLabel TotAmtLbl;
    private javax.swing.JLabel TotAmtLbl1;
    private javax.swing.JLabel TotAmtNumberLbl;
    private javax.swing.JLabel TotQty;
    private javax.swing.JLabel TotalPrice;
    private javax.swing.JLabel TotalPriceSELbl;
    private javax.swing.JLabel TotalPriceSELbl1;
    private javax.swing.JTextField TxtSellQuantity;
    private javax.swing.JLabel UnitPrice;
    private javax.swing.JLabel UnitPriceSELbl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel prod_img;
    private rojeru_san.complementos.TableMetro sales_table;
    private javax.swing.JLabel up_code;
    // End of variables declaration//GEN-END:variables
}
