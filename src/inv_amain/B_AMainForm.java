/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv_amain;

import java.awt.Image;//nav icon
import javax.swing.Icon;//nav icon
import javax.swing.ImageIcon;//nav icon

import java.awt.Color;//Placeholder
import java.awt.Cursor;
import java.awt.Font;//Placeholder
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;//Placeholder

import javax.swing.*;//minimize
import java.awt.geom.RoundRectangle2D;

import java.sql.*;//sql
import java.text.SimpleDateFormat;

/**
 *
 * @author PREDATOR HELIOS 300
 */
public class B_AMainForm extends javax.swing.JFrame {

    //Panel Loader
    JpanelLoader jpload = new JpanelLoader();

    //Mysql connection
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    //Draggable Frame
    int positionX = 0, positionY = 0;
    
    //Colors
    Color purpleLight = Color.decode("#C9BDDF");
    Color purpleMedium = Color.decode("#AF9DCE");
    Color purpleDark = Color.decode("#8E7EA7");
    Color lightBrown = Color.decode("#F5D7BD");
    
    
    
    //separate method to add & remove placeholder style
    public void addPlaceholderStyle(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.gray);
    }

    public void removePlaceholderStyle(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN | Font.BOLD);
        textField.setFont(font);
        textField.setForeground(Color.black);
    }

    public B_AMainForm() {
        initComponents();
        
        //   setTime();
        //mysql
        conn = SQLConnection.getConnection();

    }

    //Dito ka maglalagay ng Method
    public B_AMainForm(String fname) {
        initComponents();
        //logo change
        Image imgLogo = new ImageIcon(this.getClass().getResource("/inv_images/LCBC_logo_pr.png")).getImage();
        this.setIconImage(imgLogo);
        
        //jframe ewan
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(420,90);
        
        username.setText(fname.toUpperCase());//Kung ano ni-log in mo 'yon yung name na lalabas
        
        //Red Icon
        Icon red = exitBtn.getIcon();
        ImageIcon iconR = (ImageIcon) red;
        Image imageR = iconR.getImage().getScaledInstance(exitBtn.getWidth(), exitBtn.getHeight(), Image.SCALE_SMOOTH);
        exitBtn.setIcon(new ImageIcon(imageR));
        //Yellow Icon
        Icon yellow = minimizeBtn.getIcon();
        ImageIcon iconY = (ImageIcon) yellow;
        Image imageY = iconY.getImage().getScaledInstance(minimizeBtn.getWidth(), minimizeBtn.getHeight(), Image.SCALE_SMOOTH);
        minimizeBtn.setIcon(new ImageIcon(imageY));

        //SortDown Icon
        Icon sortd = sortDown.getIcon();
        ImageIcon sortD = (ImageIcon) sortd;
        Image sortDwn = sortD.getImage().getScaledInstance(sortDown.getWidth(), sortDown.getHeight(), Image.SCALE_SMOOTH);
        sortDown.setIcon(new ImageIcon(sortDwn));

        //CSB-LOGO
        Icon csb = CSB_logo.getIcon();
        ImageIcon csbL = (ImageIcon) csb;
        Image csbLOGO = csbL.getImage().getScaledInstance(CSB_logo.getWidth(), CSB_logo.getHeight(), Image.SCALE_SMOOTH);
        CSB_logo.setIcon(new ImageIcon(csbLOGO));

        //Rounder Corner
        // x,y,jframe width,jframe height,arc width,arc height
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));

        //Make Dasboard panel appear on startup
        B_DashboardPanel DP = new B_DashboardPanel();
        jpload.jPanelLoader(PanelLoad, DP);

        showDate();
        showTime();
    }

    public void showDate() {
        java.util.Date d = new java.util.Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        String dat = s.format(d);
        Dating.setText(dat);

    }

    public void showTime() {

        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date d = new java.util.Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss");
                Timers.setText(s.format(d));
            }
        }
        ).start();
    }

    //SIDE PANDELS COLOR start
    public void DBPanelME() { //mouseenter
        DashboardPanelBtn.setBackground(lightBrown);
        DashboardPanelLbl.setForeground(Color.WHITE);
    }

    public void DBPanelML() { //mouseleave
        DashboardPanelBtn.setBackground(purpleDark);
        DashboardPanelLbl.setForeground(purpleLight);
    }

    public void EmpPanelME() {
        EmployeePanelBtn.setBackground(lightBrown);
        EmployeePanelLbl.setForeground(Color.WHITE);
    }

    public void EmpPanelML() {
        EmployeePanelBtn.setBackground(purpleDark);
        EmployeePanelLbl.setForeground(purpleLight);
    }

    public void CustPanelME() {
        CustomerPanelBtn.setBackground(lightBrown);
        CustomerPanelLbl.setForeground(Color.WHITE);
    }

    public void CustPanelML() {
        CustomerPanelBtn.setBackground(purpleDark);
        CustomerPanelLbl.setForeground(purpleLight);
    }

    public void InvPanelME() {
        InventoryPanelBtn.setBackground(lightBrown);
        InventoryPanelLbl.setForeground(Color.WHITE);
    }

    public void InvPanelML() {
        InventoryPanelBtn.setBackground(purpleDark);
        InventoryPanelLbl.setForeground(purpleLight);
    }

    public void SalesPanelME() {
        SalesPanelBtn.setBackground(lightBrown);
        SalesPanelLbl.setForeground(Color.WHITE);
    }

    public void SalesPanelML() {
        SalesPanelBtn.setBackground(purpleDark);
        SalesPanelLbl.setForeground(purpleLight);
    }

    public void InvoicePanelME() {
        InvoicePanelBtn.setBackground(lightBrown);
        InvoicePanelLbl.setForeground(Color.WHITE);
    }

    public void InvoicePanelML() {
        InvoicePanelBtn.setBackground(purpleDark);
        InvoicePanelLbl.setForeground(purpleLight);
    }
    
    public void CartDataPanelME() {
        CartDataPanelBtn.setBackground(lightBrown);
        CartDataPanelLbl.setForeground(Color.WHITE);
    }

    public void CartDataPanelML() {
        CartDataPanelBtn.setBackground(purpleDark);
        CartDataPanelLbl.setForeground(purpleLight);
    }
    
    public void ReportsPanelME() {
        ReportsPanelBtn.setBackground(lightBrown);
        ReportsPanelLbl.setForeground(Color.WHITE);
    }
    
    public void ReportsPanelML() {
        ReportsPanelBtn.setBackground(purpleDark);
        ReportsPanelLbl.setForeground(purpleLight);
    }

    //SIDE PANELS COLOR end
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginMainPanel = new javax.swing.JPanel();
        dropDownPanel = new com.k33ptoo.components.KGradientPanel();
        sortDown = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        DropDownMenu = new javax.swing.JPanel();
        signOut = new javax.swing.JLabel();
        NavBar = new javax.swing.JPanel();
        exitBtn = new javax.swing.JLabel();
        minimizeBtn = new javax.swing.JLabel();
        SidePanel = new javax.swing.JPanel();
        CSB_logo = new javax.swing.JLabel();
        DashboardPanelBtn = new javax.swing.JPanel();
        DashboardPanelLbl = new javax.swing.JLabel();
        EmployeePanelBtn = new javax.swing.JPanel();
        EmployeePanelLbl = new javax.swing.JLabel();
        CustomerPanelBtn = new javax.swing.JPanel();
        CustomerPanelLbl = new javax.swing.JLabel();
        InventoryPanelBtn = new javax.swing.JPanel();
        InventoryPanelLbl = new javax.swing.JLabel();
        SalesPanelBtn = new javax.swing.JPanel();
        SalesPanelLbl = new javax.swing.JLabel();
        InvoicePanelBtn = new javax.swing.JPanel();
        InvoicePanelLbl = new javax.swing.JLabel();
        CartDataPanelBtn = new javax.swing.JPanel();
        CartDataPanelLbl = new javax.swing.JLabel();
        DateTimePanel = new javax.swing.JPanel();
        jLabel = new javax.swing.JLabel();
        Dating = new javax.swing.JLabel();
        Timers = new javax.swing.JLabel();
        ReportsPanelBtn = new javax.swing.JPanel();
        ReportsPanelLbl = new javax.swing.JLabel();
        PanelLoad = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 720));
        setUndecorated(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LoginMainPanel.setBackground(new java.awt.Color(245, 215, 189));
        LoginMainPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(175, 157, 206)));
        LoginMainPanel.setMaximumSize(new java.awt.Dimension(1080, 720));
        LoginMainPanel.setMinimumSize(new java.awt.Dimension(1080, 720));
        LoginMainPanel.setPreferredSize(new java.awt.Dimension(1080, 720));
        LoginMainPanel.setLayout(null);

        dropDownPanel.setBackground(new java.awt.Color(175, 157, 206));
        dropDownPanel.setkBorderRadius(30);
        dropDownPanel.setkEndColor(new java.awt.Color(201, 189, 223));
        dropDownPanel.setkGradientFocus(0);
        dropDownPanel.setkStartColor(new java.awt.Color(201, 189, 223));
        dropDownPanel.setMaximumSize(new java.awt.Dimension(160, 31));
        dropDownPanel.setMinimumSize(new java.awt.Dimension(160, 31));
        dropDownPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dropDownPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dropDownPanelMouseExited(evt);
            }
        });

        sortDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/Sort Down_128px.png"))); // NOI18N
        sortDown.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortDownMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sortDownMouseEntered(evt);
            }
        });

        username.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        username.setForeground(new java.awt.Color(0, 0, 0));
        username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        username.setText("USERNAME");
        username.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout dropDownPanelLayout = new javax.swing.GroupLayout(dropDownPanel);
        dropDownPanel.setLayout(dropDownPanelLayout);
        dropDownPanelLayout.setHorizontalGroup(
            dropDownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropDownPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortDown, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        dropDownPanelLayout.setVerticalGroup(
            dropDownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropDownPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(dropDownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sortDown, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        LoginMainPanel.add(dropDownPanel);
        dropDownPanel.setBounds(900, 10, 160, 31);

        DropDownMenu.setBackground(new java.awt.Color(175, 157, 206));
        DropDownMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DropDownMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DropDownMenuMouseExited(evt);
            }
        });

        signOut.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        signOut.setForeground(new java.awt.Color(0, 0, 0));
        signOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        signOut.setText("SIGN OUT");
        signOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signOut.setMaximumSize(new java.awt.Dimension(100, 15));
        signOut.setMinimumSize(new java.awt.Dimension(100, 15));
        signOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signOutMouseExited(evt);
            }
        });

        javax.swing.GroupLayout DropDownMenuLayout = new javax.swing.GroupLayout(DropDownMenu);
        DropDownMenu.setLayout(DropDownMenuLayout);
        DropDownMenuLayout.setHorizontalGroup(
            DropDownMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DropDownMenuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(signOut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        DropDownMenuLayout.setVerticalGroup(
            DropDownMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DropDownMenuLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(signOut, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LoginMainPanel.add(DropDownMenu);
        DropDownMenu.setBounds(915, 41, 130, 0);

        NavBar.setBackground(new java.awt.Color(175, 157, 206));
        NavBar.setMaximumSize(new java.awt.Dimension(1074, 50));
        NavBar.setMinimumSize(new java.awt.Dimension(1074, 50));
        NavBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                NavBarMouseDragged(evt);
            }
        });
        NavBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NavBarMousePressed(evt);
            }
        });
        NavBar.setLayout(null);

        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/red.png"))); // NOI18N
        exitBtn.setToolTipText("Close");
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitBtnMouseClicked(evt);
            }
        });
        NavBar.add(exitBtn);
        exitBtn.setBounds(15, 5, 15, 15);

        minimizeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/yellow.png"))); // NOI18N
        minimizeBtn.setToolTipText("Minimize");
        minimizeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeBtnMouseClicked(evt);
            }
        });
        NavBar.add(minimizeBtn);
        minimizeBtn.setBounds(40, 5, 15, 15);

        LoginMainPanel.add(NavBar);
        NavBar.setBounds(3, 3, 1080, 50);

        SidePanel.setBackground(new java.awt.Color(142, 126, 167));
        SidePanel.setMaximumSize(new java.awt.Dimension(120, 52));
        SidePanel.setMinimumSize(new java.awt.Dimension(120, 52));

        CSB_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/LCBC_logo_wt.png"))); // NOI18N
        CSB_logo.setMaximumSize(new java.awt.Dimension(48, 174));
        CSB_logo.setMinimumSize(new java.awt.Dimension(48, 174));
        CSB_logo.setPreferredSize(new java.awt.Dimension(48, 174));

        DashboardPanelBtn.setBackground(new java.awt.Color(142, 126, 167));
        DashboardPanelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DashboardPanelBtn.setMaximumSize(new java.awt.Dimension(170, 40));
        DashboardPanelBtn.setMinimumSize(new java.awt.Dimension(170, 40));
        DashboardPanelBtn.setPreferredSize(new java.awt.Dimension(170, 40));
        DashboardPanelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardPanelBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DashboardPanelBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DashboardPanelBtnMouseExited(evt);
            }
        });

        DashboardPanelLbl.setBackground(new java.awt.Color(0, 0, 0));
        DashboardPanelLbl.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        DashboardPanelLbl.setForeground(new java.awt.Color(201, 189, 223));
        DashboardPanelLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DashboardPanelLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/dashboard_layout_24px.png"))); // NOI18N
        DashboardPanelLbl.setText("DASHBOARD");
        DashboardPanelLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        DashboardPanelLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DashboardPanelLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardPanelLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DashboardPanelLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DashboardPanelLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout DashboardPanelBtnLayout = new javax.swing.GroupLayout(DashboardPanelBtn);
        DashboardPanelBtn.setLayout(DashboardPanelBtnLayout);
        DashboardPanelBtnLayout.setHorizontalGroup(
            DashboardPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardPanelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(DashboardPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        DashboardPanelBtnLayout.setVerticalGroup(
            DashboardPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardPanelBtnLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(DashboardPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        EmployeePanelBtn.setBackground(new java.awt.Color(142, 126, 167));
        EmployeePanelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EmployeePanelBtn.setMaximumSize(new java.awt.Dimension(170, 40));
        EmployeePanelBtn.setMinimumSize(new java.awt.Dimension(170, 40));
        EmployeePanelBtn.setPreferredSize(new java.awt.Dimension(170, 40));
        EmployeePanelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmployeePanelBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EmployeePanelBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EmployeePanelBtnMouseExited(evt);
            }
        });

        EmployeePanelLbl.setBackground(new java.awt.Color(0, 0, 0));
        EmployeePanelLbl.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        EmployeePanelLbl.setForeground(new java.awt.Color(201, 189, 223));
        EmployeePanelLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EmployeePanelLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/name_tag_24px.png"))); // NOI18N
        EmployeePanelLbl.setText("employee");
        EmployeePanelLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        EmployeePanelLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EmployeePanelLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmployeePanelLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EmployeePanelLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EmployeePanelLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout EmployeePanelBtnLayout = new javax.swing.GroupLayout(EmployeePanelBtn);
        EmployeePanelBtn.setLayout(EmployeePanelBtnLayout);
        EmployeePanelBtnLayout.setHorizontalGroup(
            EmployeePanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeePanelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(EmployeePanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        EmployeePanelBtnLayout.setVerticalGroup(
            EmployeePanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeePanelBtnLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(EmployeePanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        CustomerPanelBtn.setBackground(new java.awt.Color(142, 126, 167));
        CustomerPanelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CustomerPanelBtn.setMaximumSize(new java.awt.Dimension(170, 40));
        CustomerPanelBtn.setMinimumSize(new java.awt.Dimension(170, 40));
        CustomerPanelBtn.setPreferredSize(new java.awt.Dimension(170, 40));
        CustomerPanelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerPanelBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CustomerPanelBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CustomerPanelBtnMouseExited(evt);
            }
        });

        CustomerPanelLbl.setBackground(new java.awt.Color(0, 0, 0));
        CustomerPanelLbl.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        CustomerPanelLbl.setForeground(new java.awt.Color(201, 189, 223));
        CustomerPanelLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CustomerPanelLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/customer_24px.png"))); // NOI18N
        CustomerPanelLbl.setText("Customer");
        CustomerPanelLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        CustomerPanelLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CustomerPanelLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerPanelLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CustomerPanelLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CustomerPanelLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout CustomerPanelBtnLayout = new javax.swing.GroupLayout(CustomerPanelBtn);
        CustomerPanelBtn.setLayout(CustomerPanelBtnLayout);
        CustomerPanelBtnLayout.setHorizontalGroup(
            CustomerPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPanelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(CustomerPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        CustomerPanelBtnLayout.setVerticalGroup(
            CustomerPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPanelBtnLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(CustomerPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        InventoryPanelBtn.setBackground(new java.awt.Color(142, 126, 167));
        InventoryPanelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        InventoryPanelBtn.setMaximumSize(new java.awt.Dimension(170, 40));
        InventoryPanelBtn.setMinimumSize(new java.awt.Dimension(170, 40));
        InventoryPanelBtn.setPreferredSize(new java.awt.Dimension(170, 40));
        InventoryPanelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventoryPanelBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InventoryPanelBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InventoryPanelBtnMouseExited(evt);
            }
        });

        InventoryPanelLbl.setBackground(new java.awt.Color(0, 0, 0));
        InventoryPanelLbl.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        InventoryPanelLbl.setForeground(new java.awt.Color(201, 189, 223));
        InventoryPanelLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InventoryPanelLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/product_24px.png"))); // NOI18N
        InventoryPanelLbl.setText("inventory");
        InventoryPanelLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        InventoryPanelLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        InventoryPanelLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventoryPanelLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InventoryPanelLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InventoryPanelLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout InventoryPanelBtnLayout = new javax.swing.GroupLayout(InventoryPanelBtn);
        InventoryPanelBtn.setLayout(InventoryPanelBtnLayout);
        InventoryPanelBtnLayout.setHorizontalGroup(
            InventoryPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryPanelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(InventoryPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        InventoryPanelBtnLayout.setVerticalGroup(
            InventoryPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryPanelBtnLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(InventoryPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        SalesPanelBtn.setBackground(new java.awt.Color(142, 126, 167));
        SalesPanelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SalesPanelBtn.setMaximumSize(new java.awt.Dimension(170, 40));
        SalesPanelBtn.setMinimumSize(new java.awt.Dimension(170, 40));
        SalesPanelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalesPanelBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SalesPanelBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SalesPanelBtnMouseExited(evt);
            }
        });

        SalesPanelLbl.setBackground(new java.awt.Color(0, 0, 0));
        SalesPanelLbl.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        SalesPanelLbl.setForeground(new java.awt.Color(201, 189, 223));
        SalesPanelLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SalesPanelLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/shopping_cart_24px.png"))); // NOI18N
        SalesPanelLbl.setText("sales");
        SalesPanelLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        SalesPanelLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SalesPanelLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalesPanelLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SalesPanelLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SalesPanelLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout SalesPanelBtnLayout = new javax.swing.GroupLayout(SalesPanelBtn);
        SalesPanelBtn.setLayout(SalesPanelBtnLayout);
        SalesPanelBtnLayout.setHorizontalGroup(
            SalesPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalesPanelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(SalesPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        SalesPanelBtnLayout.setVerticalGroup(
            SalesPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalesPanelBtnLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(SalesPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        InvoicePanelBtn.setBackground(new java.awt.Color(142, 126, 167));
        InvoicePanelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        InvoicePanelBtn.setMaximumSize(new java.awt.Dimension(170, 40));
        InvoicePanelBtn.setMinimumSize(new java.awt.Dimension(170, 40));
        InvoicePanelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InvoicePanelBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InvoicePanelBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InvoicePanelBtnMouseExited(evt);
            }
        });

        InvoicePanelLbl.setBackground(new java.awt.Color(0, 0, 0));
        InvoicePanelLbl.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        InvoicePanelLbl.setForeground(new java.awt.Color(201, 189, 223));
        InvoicePanelLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InvoicePanelLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/invoice_24px.png"))); // NOI18N
        InvoicePanelLbl.setText("invoice");
        InvoicePanelLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        InvoicePanelLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        InvoicePanelLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InvoicePanelLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InvoicePanelLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InvoicePanelLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout InvoicePanelBtnLayout = new javax.swing.GroupLayout(InvoicePanelBtn);
        InvoicePanelBtn.setLayout(InvoicePanelBtnLayout);
        InvoicePanelBtnLayout.setHorizontalGroup(
            InvoicePanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InvoicePanelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(InvoicePanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        InvoicePanelBtnLayout.setVerticalGroup(
            InvoicePanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InvoicePanelBtnLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(InvoicePanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        CartDataPanelBtn.setBackground(new java.awt.Color(142, 126, 167));
        CartDataPanelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CartDataPanelBtn.setMaximumSize(new java.awt.Dimension(170, 40));
        CartDataPanelBtn.setMinimumSize(new java.awt.Dimension(170, 40));
        CartDataPanelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CartDataPanelBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CartDataPanelBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CartDataPanelBtnMouseExited(evt);
            }
        });

        CartDataPanelLbl.setBackground(new java.awt.Color(0, 0, 0));
        CartDataPanelLbl.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        CartDataPanelLbl.setForeground(new java.awt.Color(201, 189, 223));
        CartDataPanelLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CartDataPanelLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/trolley_24px.png"))); // NOI18N
        CartDataPanelLbl.setText("cart data");
        CartDataPanelLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        CartDataPanelLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CartDataPanelLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CartDataPanelLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CartDataPanelLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CartDataPanelLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout CartDataPanelBtnLayout = new javax.swing.GroupLayout(CartDataPanelBtn);
        CartDataPanelBtn.setLayout(CartDataPanelBtnLayout);
        CartDataPanelBtnLayout.setHorizontalGroup(
            CartDataPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CartDataPanelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(CartDataPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        CartDataPanelBtnLayout.setVerticalGroup(
            CartDataPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CartDataPanelBtnLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(CartDataPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        DateTimePanel.setBackground(new java.awt.Color(142, 126, 167));
        DateTimePanel.setMaximumSize(new java.awt.Dimension(170, 40));
        DateTimePanel.setMinimumSize(new java.awt.Dimension(170, 40));

        jLabel.setBackground(new java.awt.Color(0, 0, 0));
        jLabel.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        jLabel.setForeground(new java.awt.Color(255, 255, 255));
        jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel.setText("date & time");

        Dating.setBackground(new java.awt.Color(0, 0, 0));
        Dating.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        Dating.setForeground(new java.awt.Color(255, 255, 255));
        Dating.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Dating.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/calendar_24px.png"))); // NOI18N
        Dating.setText("date");

        Timers.setBackground(new java.awt.Color(0, 0, 0));
        Timers.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        Timers.setForeground(new java.awt.Color(255, 255, 255));
        Timers.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Timers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/time_24px.png"))); // NOI18N
        Timers.setText("time");
        Timers.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout DateTimePanelLayout = new javax.swing.GroupLayout(DateTimePanel);
        DateTimePanel.setLayout(DateTimePanelLayout);
        DateTimePanelLayout.setHorizontalGroup(
            DateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DateTimePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addGroup(DateTimePanelLayout.createSequentialGroup()
                        .addGroup(DateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Dating, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(Timers, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        DateTimePanelLayout.setVerticalGroup(
            DateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DateTimePanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Dating, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(Timers, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        ReportsPanelBtn.setBackground(new java.awt.Color(142, 126, 167));
        ReportsPanelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReportsPanelBtn.setMaximumSize(new java.awt.Dimension(170, 40));
        ReportsPanelBtn.setMinimumSize(new java.awt.Dimension(170, 40));
        ReportsPanelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportsPanelBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReportsPanelBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReportsPanelBtnMouseExited(evt);
            }
        });

        ReportsPanelLbl.setBackground(new java.awt.Color(0, 0, 0));
        ReportsPanelLbl.setFont(new java.awt.Font("Retro Computer", 1, 12)); // NOI18N
        ReportsPanelLbl.setForeground(new java.awt.Color(201, 189, 223));
        ReportsPanelLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ReportsPanelLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/graph_report_24px.png"))); // NOI18N
        ReportsPanelLbl.setText("reports");
        ReportsPanelLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ReportsPanelLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReportsPanelLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportsPanelLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReportsPanelLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReportsPanelLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ReportsPanelBtnLayout = new javax.swing.GroupLayout(ReportsPanelBtn);
        ReportsPanelBtn.setLayout(ReportsPanelBtnLayout);
        ReportsPanelBtnLayout.setHorizontalGroup(
            ReportsPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsPanelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(ReportsPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        ReportsPanelBtnLayout.setVerticalGroup(
            ReportsPanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsPanelBtnLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(ReportsPanelLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SidePanelLayout.createSequentialGroup()
                .addGap(6, 14, Short.MAX_VALUE)
                .addComponent(CSB_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SidePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DashboardPanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomerPanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EmployeePanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InventoryPanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SalesPanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InvoicePanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CartDataPanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateTimePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReportsPanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        SidePanelLayout.setVerticalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CSB_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DashboardPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(CustomerPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(EmployeePanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(InventoryPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(SalesPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(InvoicePanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(CartDataPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ReportsPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(DateTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        LoginMainPanel.add(SidePanel);
        SidePanel.setBounds(-7, 53, 180, 670);

        PanelLoad.setBackground(new java.awt.Color(245, 215, 189));
        PanelLoad.setForeground(new java.awt.Color(245, 215, 189));
        PanelLoad.setMaximumSize(new java.awt.Dimension(910, 670));

        javax.swing.GroupLayout PanelLoadLayout = new javax.swing.GroupLayout(PanelLoad);
        PanelLoad.setLayout(PanelLoadLayout);
        PanelLoadLayout.setHorizontalGroup(
            PanelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        PanelLoadLayout.setVerticalGroup(
            PanelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        LoginMainPanel.add(PanelLoad);
        PanelLoad.setBounds(180, 60, 890, 650);

        getContentPane().add(LoginMainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // this.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void NavBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NavBarMousePressed
        //Draggable Frame Get Jfram Coord value
        positionX = evt.getX();
        positionY = evt.getY();
    }//GEN-LAST:event_NavBarMousePressed

    private void NavBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NavBarMouseDragged
        //Draggable Frame set JFrame Location
        setLocation(evt.getXOnScreen() - positionX, evt.getYOnScreen() - positionY);
    }//GEN-LAST:event_NavBarMouseDragged

    private void minimizeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeBtnMouseClicked
        //minimize
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeBtnMouseClicked

    private void exitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitBtnMouseClicked

    private void sortDownMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortDownMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_sortDownMouseClicked

    private void dropDownPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dropDownPanelMouseEntered
        Cursor cur1 = new Cursor(Cursor.HAND_CURSOR);
        dropDownPanel.setCursor(cur1);

        DropDownMenu.setSize(130, 40);
    }//GEN-LAST:event_dropDownPanelMouseEntered

    private void dropDownPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dropDownPanelMouseExited
        DropDownMenu.setSize(130, 5);
    }//GEN-LAST:event_dropDownPanelMouseExited

    private void sortDownMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortDownMouseEntered
        DropDownMenu.setSize(130, 40);
    }//GEN-LAST:event_sortDownMouseEntered

    private void signOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signOutMouseClicked

        int SO = JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?", "Sign out", JOptionPane.YES_NO_OPTION);
        if (SO == JOptionPane.YES_OPTION) {
            A_LoginForm L = new A_LoginForm();
            L.setVisible(true);
            this.dispose();
        } else if (SO == JOptionPane.NO_OPTION) {
            DropDownMenu.setSize(130, 5);
        } else if (SO == JOptionPane.CLOSED_OPTION) {
            DropDownMenu.setSize(130, 5);
        }

    }//GEN-LAST:event_signOutMouseClicked

    private void signOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signOutMouseEntered
        signOut.setForeground(Color.white);
        DropDownMenu.setSize(130, 40);
    }//GEN-LAST:event_signOutMouseEntered

    private void signOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signOutMouseExited
        signOut.setForeground(Color.black);
    }//GEN-LAST:event_signOutMouseExited

    private void DropDownMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DropDownMenuMouseExited
        DropDownMenu.setSize(130, 5);
    }//GEN-LAST:event_DropDownMenuMouseExited

    private void DropDownMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DropDownMenuMouseEntered
        DropDownMenu.setSize(130, 40);

    }//GEN-LAST:event_DropDownMenuMouseEntered

    private void DashboardPanelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardPanelBtnMouseClicked
        B_DashboardPanel DP = new B_DashboardPanel();
        jpload.jPanelLoader(PanelLoad, DP);
    }//GEN-LAST:event_DashboardPanelBtnMouseClicked

    private void DashboardPanelLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardPanelLblMouseClicked
        B_DashboardPanel DP = new B_DashboardPanel();
        jpload.jPanelLoader(PanelLoad, DP);
    }//GEN-LAST:event_DashboardPanelLblMouseClicked

    private void EmployeePanelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeePanelBtnMouseClicked
        B_EmployeePanel EP = new B_EmployeePanel();
        jpload.jPanelLoader(PanelLoad, EP);
    }//GEN-LAST:event_EmployeePanelBtnMouseClicked

    private void EmployeePanelLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeePanelLblMouseClicked
        B_EmployeePanel EP = new B_EmployeePanel();
        jpload.jPanelLoader(PanelLoad, EP);
    }//GEN-LAST:event_EmployeePanelLblMouseClicked

    private void DashboardPanelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardPanelBtnMouseEntered
        DBPanelME();
    }//GEN-LAST:event_DashboardPanelBtnMouseEntered

    private void DashboardPanelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardPanelBtnMouseExited
        DBPanelML();
    }//GEN-LAST:event_DashboardPanelBtnMouseExited

    private void DashboardPanelLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardPanelLblMouseEntered
        DBPanelME();
    }//GEN-LAST:event_DashboardPanelLblMouseEntered

    private void DashboardPanelLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardPanelLblMouseExited
        DBPanelML();
    }//GEN-LAST:event_DashboardPanelLblMouseExited

    private void EmployeePanelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeePanelBtnMouseEntered
        EmpPanelME();
    }//GEN-LAST:event_EmployeePanelBtnMouseEntered

    private void EmployeePanelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeePanelBtnMouseExited
        EmpPanelML();
    }//GEN-LAST:event_EmployeePanelBtnMouseExited

    private void EmployeePanelLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeePanelLblMouseEntered
        EmpPanelME();
    }//GEN-LAST:event_EmployeePanelLblMouseEntered

    private void EmployeePanelLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeePanelLblMouseExited
        EmpPanelML();
    }//GEN-LAST:event_EmployeePanelLblMouseExited

    private void CustomerPanelLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerPanelLblMouseClicked
        B_CustomerPanel CP = new B_CustomerPanel();
        jpload.jPanelLoader(PanelLoad, CP);
    }//GEN-LAST:event_CustomerPanelLblMouseClicked

    private void CustomerPanelLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerPanelLblMouseEntered
        CustPanelME();
    }//GEN-LAST:event_CustomerPanelLblMouseEntered

    private void CustomerPanelLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerPanelLblMouseExited
        CustPanelML();
    }//GEN-LAST:event_CustomerPanelLblMouseExited

    private void CustomerPanelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerPanelBtnMouseClicked
        B_CustomerPanel CP = new B_CustomerPanel();
        jpload.jPanelLoader(PanelLoad, CP);
    }//GEN-LAST:event_CustomerPanelBtnMouseClicked

    private void CustomerPanelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerPanelBtnMouseEntered
        CustPanelME();
    }//GEN-LAST:event_CustomerPanelBtnMouseEntered

    private void CustomerPanelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerPanelBtnMouseExited
        CustPanelML();
    }//GEN-LAST:event_CustomerPanelBtnMouseExited

    private void InventoryPanelLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryPanelLblMouseClicked
        B_InventoryPanel IP = new B_InventoryPanel();
        jpload.jPanelLoader(PanelLoad, IP);
    }//GEN-LAST:event_InventoryPanelLblMouseClicked

    private void InventoryPanelLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryPanelLblMouseEntered
        InvPanelME();
    }//GEN-LAST:event_InventoryPanelLblMouseEntered

    private void InventoryPanelLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryPanelLblMouseExited
        InvPanelML();
    }//GEN-LAST:event_InventoryPanelLblMouseExited

    private void InventoryPanelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryPanelBtnMouseClicked
        B_InventoryPanel IP = new B_InventoryPanel();
        jpload.jPanelLoader(PanelLoad, IP);
    }//GEN-LAST:event_InventoryPanelBtnMouseClicked

    private void InventoryPanelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryPanelBtnMouseEntered
        InvPanelME();
    }//GEN-LAST:event_InventoryPanelBtnMouseEntered

    private void InventoryPanelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryPanelBtnMouseExited
        InvPanelML();
    }//GEN-LAST:event_InventoryPanelBtnMouseExited

    private void SalesPanelLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesPanelLblMouseClicked
        B_SalesPanel SP = new B_SalesPanel();
        jpload.jPanelLoader(PanelLoad, SP);
    }//GEN-LAST:event_SalesPanelLblMouseClicked

    private void SalesPanelLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesPanelLblMouseEntered
        SalesPanelME();
    }//GEN-LAST:event_SalesPanelLblMouseEntered

    private void SalesPanelLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesPanelLblMouseExited
        SalesPanelML();
    }//GEN-LAST:event_SalesPanelLblMouseExited

    private void SalesPanelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesPanelBtnMouseClicked
        B_SalesPanel SP = new B_SalesPanel();
        jpload.jPanelLoader(PanelLoad, SP);
    }//GEN-LAST:event_SalesPanelBtnMouseClicked

    private void SalesPanelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesPanelBtnMouseEntered
        SalesPanelME();
    }//GEN-LAST:event_SalesPanelBtnMouseEntered

    private void SalesPanelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesPanelBtnMouseExited
        SalesPanelML();
    }//GEN-LAST:event_SalesPanelBtnMouseExited

    private void InvoicePanelLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InvoicePanelLblMouseClicked
        B_InvoicePanel IP = new B_InvoicePanel();
        jpload.jPanelLoader(PanelLoad, IP);
    }//GEN-LAST:event_InvoicePanelLblMouseClicked

    private void InvoicePanelLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InvoicePanelLblMouseEntered
        InvoicePanelME();
    }//GEN-LAST:event_InvoicePanelLblMouseEntered

    private void InvoicePanelLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InvoicePanelLblMouseExited
        InvoicePanelML();
    }//GEN-LAST:event_InvoicePanelLblMouseExited

    private void InvoicePanelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InvoicePanelBtnMouseClicked
        B_InvoicePanel IP = new B_InvoicePanel();
        jpload.jPanelLoader(PanelLoad, IP);
    }//GEN-LAST:event_InvoicePanelBtnMouseClicked

    private void InvoicePanelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InvoicePanelBtnMouseEntered
        InvoicePanelME();
    }//GEN-LAST:event_InvoicePanelBtnMouseEntered

    private void InvoicePanelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InvoicePanelBtnMouseExited
        InvoicePanelML();
    }//GEN-LAST:event_InvoicePanelBtnMouseExited

    private void CartDataPanelLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartDataPanelLblMouseClicked
        B_CartData CD = new B_CartData();
        jpload.jPanelLoader(PanelLoad, CD);
    }//GEN-LAST:event_CartDataPanelLblMouseClicked

    private void CartDataPanelLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartDataPanelLblMouseEntered
        CartDataPanelME();
    }//GEN-LAST:event_CartDataPanelLblMouseEntered

    private void CartDataPanelLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartDataPanelLblMouseExited
        CartDataPanelML();
    }//GEN-LAST:event_CartDataPanelLblMouseExited

    private void CartDataPanelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartDataPanelBtnMouseClicked
        B_CartData CD = new B_CartData();
        jpload.jPanelLoader(PanelLoad, CD);
    }//GEN-LAST:event_CartDataPanelBtnMouseClicked

    private void CartDataPanelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartDataPanelBtnMouseEntered
        CartDataPanelME();
    }//GEN-LAST:event_CartDataPanelBtnMouseEntered

    private void CartDataPanelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CartDataPanelBtnMouseExited
        CartDataPanelML();
    }//GEN-LAST:event_CartDataPanelBtnMouseExited

    private void ReportsPanelLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsPanelLblMouseClicked
        B_ReportsPanel RP = new B_ReportsPanel();
        jpload.jPanelLoader(PanelLoad, RP);
    }//GEN-LAST:event_ReportsPanelLblMouseClicked

    private void ReportsPanelLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsPanelLblMouseEntered
        ReportsPanelME();
    }//GEN-LAST:event_ReportsPanelLblMouseEntered

    private void ReportsPanelLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsPanelLblMouseExited
        ReportsPanelML();
    }//GEN-LAST:event_ReportsPanelLblMouseExited

    private void ReportsPanelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsPanelBtnMouseClicked
        B_ReportsPanel RP = new B_ReportsPanel();
        jpload.jPanelLoader(PanelLoad, RP);
    }//GEN-LAST:event_ReportsPanelBtnMouseClicked

    private void ReportsPanelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsPanelBtnMouseEntered
        ReportsPanelME();
    }//GEN-LAST:event_ReportsPanelBtnMouseEntered

    private void ReportsPanelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsPanelBtnMouseExited
        ReportsPanelML();
    }//GEN-LAST:event_ReportsPanelBtnMouseExited

    /**
     * @param args the command line arguments
     */
    /* Set the Nimbus look and feel */
    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {//Nimbus for best result of Look and Feel
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(B_AMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(B_AMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(B_AMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(B_AMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new B_AMainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CSB_logo;
    private javax.swing.JPanel CartDataPanelBtn;
    private javax.swing.JLabel CartDataPanelLbl;
    private javax.swing.JPanel CustomerPanelBtn;
    private javax.swing.JLabel CustomerPanelLbl;
    private javax.swing.JPanel DashboardPanelBtn;
    private javax.swing.JLabel DashboardPanelLbl;
    private javax.swing.JPanel DateTimePanel;
    private javax.swing.JLabel Dating;
    private javax.swing.JPanel DropDownMenu;
    private javax.swing.JPanel EmployeePanelBtn;
    private javax.swing.JLabel EmployeePanelLbl;
    private javax.swing.JPanel InventoryPanelBtn;
    private javax.swing.JLabel InventoryPanelLbl;
    private javax.swing.JPanel InvoicePanelBtn;
    private javax.swing.JLabel InvoicePanelLbl;
    private javax.swing.JPanel LoginMainPanel;
    private javax.swing.JPanel NavBar;
    private javax.swing.JPanel PanelLoad;
    private javax.swing.JPanel ReportsPanelBtn;
    private javax.swing.JLabel ReportsPanelLbl;
    private javax.swing.JPanel SalesPanelBtn;
    private javax.swing.JLabel SalesPanelLbl;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JLabel Timers;
    private com.k33ptoo.components.KGradientPanel dropDownPanel;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel minimizeBtn;
    private javax.swing.JLabel signOut;
    private javax.swing.JLabel sortDown;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
