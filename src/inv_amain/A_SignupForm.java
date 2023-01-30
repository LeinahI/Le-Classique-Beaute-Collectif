/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv_amain;

import java.awt.Image;//nav icon
import javax.swing.Icon;//nav icon
import javax.swing.ImageIcon;//nav icon

import javax.swing.*;//minimize
import java.awt.geom.RoundRectangle2D;

import java.sql.*;//mysql

/**
 *
 * @author PREDATOR HELIOS 300
 */
public class A_SignupForm extends javax.swing.JFrame {

    //mysql
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    //Draggable Frame
    int positionX = 0, positionY = 0;

    public A_SignupForm() {
        initComponents();
        //logo change
        Image imgLogo = new ImageIcon(this.getClass().getResource("/inv_images/LCBC_logo_pr.png")).getImage();
        this.setIconImage(imgLogo);
        //mysql
        conn = SQLConnection.getConnection();

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

        //ShowHide Icon
        Icon hide = ShowHidePw.getIcon();
        ImageIcon iconHide = (ImageIcon) hide;
        Image iconH = iconHide.getImage().getScaledInstance(minimizeBtn.getWidth(), minimizeBtn.getHeight(), Image.SCALE_SMOOTH);
        ShowHidePw.setIcon(new ImageIcon(iconH));

        //Rounder Corner
        // x,y,jframe width,jframe height,arc width,arc height
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
    }

    public boolean checkDuplicateUser() {
        String name = txtUname.getText();
        boolean isExist = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lcbcdatabase"/*My database*/, "root", "");

            PreparedStatement pst = con.prepareStatement("SELECT * FROM user where `uname` = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                isExist = true;
            } else {
                isExist = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExist;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SignupMainPanel = new javax.swing.JPanel();
        NavBar = new javax.swing.JPanel();
        exitBtn = new javax.swing.JLabel();
        minimizeBtn = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtFname = new javax.swing.JTextField();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        txtLname = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtUname = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        HaveAccLabel = new javax.swing.JLabel();
        LoginLabel = new javax.swing.JLabel();
        SignupBtn = new com.k33ptoo.components.KButton();
        jLabel5 = new javax.swing.JLabel();
        txtQues = new javax.swing.JComboBox<>();
        txtAns = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ShowHidePw = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 650));
        setMinimumSize(new java.awt.Dimension(600, 650));
        setUndecorated(true);
        setResizable(false);

        SignupMainPanel.setBackground(new java.awt.Color(245, 215, 189));
        SignupMainPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 185, 136)));
        SignupMainPanel.setMaximumSize(new java.awt.Dimension(600, 650));
        SignupMainPanel.setMinimumSize(new java.awt.Dimension(600, 650));
        SignupMainPanel.setPreferredSize(new java.awt.Dimension(600, 650));

        NavBar.setBackground(new java.awt.Color(0, 185, 136));
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

        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/red.png"))); // NOI18N
        exitBtn.setToolTipText("Close");
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitBtnMouseClicked(evt);
            }
        });

        minimizeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/yellow.png"))); // NOI18N
        minimizeBtn.setToolTipText("Minimize");
        minimizeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout NavBarLayout = new javax.swing.GroupLayout(NavBar);
        NavBar.setLayout(NavBarLayout);
        NavBarLayout.setHorizontalGroup(
            NavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavBarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(539, Short.MAX_VALUE))
        );
        NavBarLayout.setVerticalGroup(
            NavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavBarLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(NavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("first name");

        txtFname.setBackground(new java.awt.Color(245, 215, 189));
        txtFname.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        txtFname.setBorder(null);
        txtFname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFnameKeyReleased(evt);
            }
        });

        kGradientPanel1.setkEndColor(new java.awt.Color(0, 185, 136));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 185, 136));

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("last name");

        txtLname.setBackground(new java.awt.Color(245, 215, 189));
        txtLname.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        txtLname.setBorder(null);
        txtLname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLnameKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 185, 136));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("username");

        txtUname.setBackground(new java.awt.Color(245, 215, 189));
        txtUname.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        txtUname.setBorder(null);
        txtUname.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtUname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUnameFocusLost(evt);
            }
        });
        txtUname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnameKeyReleased(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 185, 136));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("password");

        txtPass.setBackground(new java.awt.Color(245, 215, 189));
        txtPass.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        txtPass.setBorder(null);
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassKeyReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 185, 136));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        HaveAccLabel.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        HaveAccLabel.setForeground(new java.awt.Color(0, 0, 0));
        HaveAccLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HaveAccLabel.setText("have an account already?");

        LoginLabel.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        LoginLabel.setForeground(new java.awt.Color(0, 185, 136));
        LoginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LoginLabel.setText("LOg IN now");
        LoginLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LoginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginLabelMouseClicked(evt);
            }
        });

        SignupBtn.setText("SIGN UP");
        SignupBtn.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        SignupBtn.setkBorderRadius(30);
        SignupBtn.setkEndColor(new java.awt.Color(0, 185, 136));
        SignupBtn.setkForeGround(new java.awt.Color(245, 215, 189));
        SignupBtn.setkHoverEndColor(new java.awt.Color(245, 195, 152));
        SignupBtn.setkHoverForeGround(new java.awt.Color(0, 185, 136));
        SignupBtn.setkHoverStartColor(new java.awt.Color(245, 195, 152));
        SignupBtn.setkPressedColor(new java.awt.Color(242, 179, 65));
        SignupBtn.setkStartColor(new java.awt.Color(0, 185, 136));
        SignupBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignupBtnMouseClicked(evt);
            }
        });
        SignupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignupBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("security question");

        txtQues.setBackground(new java.awt.Color(255, 255, 255));
        txtQues.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        txtQues.setForeground(new java.awt.Color(0, 0, 0));
        txtQues.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Favorite Food?", "Favorite Artist?", "Favorite Film?", "Favorite Person?", "Favorite Number?", "Favorite Album/Song?", "What's your dream job?" }));
        txtQues.setSelectedItem(null);
        txtQues.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtAns.setBackground(new java.awt.Color(245, 215, 189));
        txtAns.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        txtAns.setBorder(null);
        txtAns.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtAns.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnsKeyReleased(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 185, 136));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Answer");

        ShowHidePw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/eyeHide.png"))); // NOI18N
        ShowHidePw.setMaximumSize(new java.awt.Dimension(24, 22));
        ShowHidePw.setMinimumSize(new java.awt.Dimension(24, 22));
        ShowHidePw.setPreferredSize(new java.awt.Dimension(24, 22));
        ShowHidePw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ShowHidePwMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ShowHidePwMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout SignupMainPanelLayout = new javax.swing.GroupLayout(SignupMainPanel);
        SignupMainPanel.setLayout(SignupMainPanelLayout);
        SignupMainPanelLayout.setHorizontalGroup(
            SignupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NavBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SignupMainPanelLayout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addGroup(SignupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAns)
                    .addComponent(SignupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HaveAccLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LoginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQues, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUname)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLname)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFname)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SignupMainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(SignupMainPanelLayout.createSequentialGroup()
                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ShowHidePw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(172, 172, 172))
        );
        SignupMainPanelLayout.setVerticalGroup(
            SignupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignupMainPanelLayout.createSequentialGroup()
                .addComponent(NavBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(txtLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(txtUname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addGap(1, 1, 1)
                .addComponent(txtQues, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addGap(1, 1, 1)
                .addComponent(txtAns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addGroup(SignupMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ShowHidePw, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(SignupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(HaveAccLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SignupMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SignupMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitBtnMouseClicked

    private void minimizeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeBtnMouseClicked
        //minimize
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeBtnMouseClicked

    private void NavBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NavBarMouseDragged
        //Draggable Frame set JFrame Location
        setLocation(evt.getXOnScreen() - positionX, evt.getYOnScreen() - positionY);
    }//GEN-LAST:event_NavBarMouseDragged

    private void NavBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NavBarMousePressed
        //Draggable Frame Get Jfram Coord value
        positionX = evt.getX();
        positionY = evt.getY();
    }//GEN-LAST:event_NavBarMousePressed

    private void LoginLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginLabelMouseClicked
        A_LoginForm l = new A_LoginForm();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LoginLabelMouseClicked

    private void SignupBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignupBtnMouseClicked
        //registration form mysql
        String pw = String.valueOf(txtPass.getPassword());

        if (txtUname.getText().isEmpty() || txtFname.getText().isEmpty() || txtLname.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Don't leave the fields empty");
        } else if (pw.equals("")) {
            JOptionPane.showMessageDialog(null, "Don't leave the password empty");
        } else if (pw.length() < 6) {
            JOptionPane.showMessageDialog(null, "Your password must be atleast 6 characters.");
        } else if (pw.length() < 6) {
            JOptionPane.showMessageDialog(null, "Your password must be atleast 6 characters");
        } else if (pw.length() > 20) {
            JOptionPane.showMessageDialog(null, "Your password maximum charactuer should be 20 only.");
        } else if (txtUname.getText().length() < 6) {
            JOptionPane.showMessageDialog(null, "Your username must be atleast 6 characters");
        } else if (txtUname.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Your username maximum charactuer should be 20 only.");
        } else if (txtAns.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Don't leave the security answer empty");
        } else if (txtQues.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Don't leave the security question empty");
        } else if (txtFname.getText().length() < 2) {
            JOptionPane.showMessageDialog(null, "Your first name must be atleast 2 characters");
        } else if (txtLname.getText().length() < 2) {
            JOptionPane.showMessageDialog(null, "Your last name must be atleast 2 characters");
        } else if (txtFname.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Your first name maximum charactuer should be 20 only.");
        } else if (txtLname.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Your last name maximum charactuer should be 20 only.");
        } else if (checkDuplicateUser() == true) {
            JOptionPane.showMessageDialog(null, "Username is already exists");
        } else {
            String sql = "INSERT INTO `user`(`uname`, `pass`, `fname`, `lname`, `ques`, `ans`) VALUES (?,?,?,?,?,?)";
            try {
                ps = conn.prepareStatement(sql);

                ps.setString(1, txtUname.getText());
                ps.setString(2, String.valueOf(txtPass.getPassword()));
                ps.setString(3, txtFname.getText());
                ps.setString(4, txtLname.getText());
                ps.setString(5, String.valueOf(txtQues.getSelectedItem()));
                ps.setString(6, txtAns.getText());

                ps.execute();
                JOptionPane.showMessageDialog(null, "Data Saved Successfully!");
                A_LoginForm l = new A_LoginForm();
                l.setVisible(true);
                this.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_SignupBtnMouseClicked

    private void ShowHidePwMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowHidePwMousePressed
        ImageIcon Sh = new ImageIcon(getClass().getResource("/inv_images/eyeShow.png"));
        Image iconSh = Sh.getImage().getScaledInstance(minimizeBtn.getWidth(), minimizeBtn.getHeight(), Image.SCALE_SMOOTH);
        ShowHidePw.setIcon(new ImageIcon(iconSh));
        txtPass.setEchoChar((char) 0);
    }//GEN-LAST:event_ShowHidePwMousePressed

    private void ShowHidePwMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowHidePwMouseReleased
        ImageIcon Hd = new ImageIcon(getClass().getResource("/inv_images/eyeHide.png"));
        Image iconHd = Hd.getImage().getScaledInstance(minimizeBtn.getWidth(), minimizeBtn.getHeight(), Image.SCALE_SMOOTH);
        ShowHidePw.setIcon(new ImageIcon(iconHd));
        txtPass.setEchoChar('*');
    }//GEN-LAST:event_ShowHidePwMouseReleased

    private void txtFnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFnameKeyReleased
        int position = txtFname.getCaretPosition();
        txtFname.setText(txtFname.getText().toUpperCase());
        txtFname.setCaretPosition(position);
    }//GEN-LAST:event_txtFnameKeyReleased

    private void txtLnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLnameKeyReleased
        int position = txtLname.getCaretPosition();
        txtLname.setText(txtLname.getText().toUpperCase());
        txtLname.setCaretPosition(position);
    }//GEN-LAST:event_txtLnameKeyReleased

    private void txtUnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnameKeyReleased
        int position = txtUname.getCaretPosition();
        txtUname.setText(txtUname.getText().toUpperCase());
        txtUname.setCaretPosition(position);
    }//GEN-LAST:event_txtUnameKeyReleased

    private void txtAnsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnsKeyReleased
        int position = txtAns.getCaretPosition();
        txtAns.setText(txtAns.getText().toUpperCase());
        txtAns.setCaretPosition(position);
    }//GEN-LAST:event_txtAnsKeyReleased

    private void txtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyReleased
        int position = txtPass.getCaretPosition();
        txtPass.setText(txtPass.getText().toUpperCase());
        txtPass.setCaretPosition(position);
    }//GEN-LAST:event_txtPassKeyReleased

    private void txtUnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnameFocusLost
        if (checkDuplicateUser() == true) {
            JOptionPane.showMessageDialog(null, "Username is already exists");
        }
    }//GEN-LAST:event_txtUnameFocusLost

    private void SignupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignupBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SignupBtnActionPerformed

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
            java.util.logging.Logger.getLogger(A_SignupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(A_SignupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(A_SignupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(A_SignupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new A_SignupForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HaveAccLabel;
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JPanel NavBar;
    private javax.swing.JLabel ShowHidePw;
    private com.k33ptoo.components.KButton SignupBtn;
    private javax.swing.JPanel SignupMainPanel;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel minimizeBtn;
    private javax.swing.JTextField txtAns;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtLname;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JComboBox<String> txtQues;
    private javax.swing.JTextField txtUname;
    // End of variables declaration//GEN-END:variables
}
