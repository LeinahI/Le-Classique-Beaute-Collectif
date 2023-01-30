/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv_amain;

import java.awt.Image;//nav icon
import javax.swing.Icon;//nav icon
import javax.swing.ImageIcon;//nav icon
import java.awt.Frame;
import java.awt.Color;//Placeholder
import java.awt.Font;//Placeholder
import java.awt.event.KeyEvent;
import javax.swing.JTextField;//Placeholder

import javax.swing.*;//minimize
import java.awt.geom.RoundRectangle2D;

import java.sql.*;//sql
import java.text.SimpleDateFormat;

import java.util.regex.*; //Regular Expression diko alam kung gagamitin ko pa AHAHAHAHAHAHA

/**
 *
 * @author PREDATOR HELIOS 300
 */
public class A_LoginForm extends javax.swing.JFrame {

    //Mysql connection
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    //Draggable Frame
    int positionX = 0, positionY = 0;

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

    //LOGIN BTN & Enter
    public void login() {
        //MYSQL LOGIN
        String uname = UsernameField.getText();
        String pass = String.valueOf(PasswordField.getPassword());

        //statements
        if (pass.equals("") & uname.equals("")) {
            JOptionPane.showMessageDialog(null, "Don't leave both fields empty");
        } else if (pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Password field is empty");
        } else if (pass.length() < 6) {
            JOptionPane.showMessageDialog(null, "Your password must be atleast 6 characters");

        } else if (pass.length() > 20) {
            JOptionPane.showMessageDialog(null, "Your password maximum charactuer should be 20 only.");
        } else if (uname.equals("")) {
            JOptionPane.showMessageDialog(null, "Username field is empty");
        } else if (uname.length() < 6) {
            JOptionPane.showMessageDialog(null, "Your username must be atleast 6 characters");

        } else if (uname.length() > 20) {
            JOptionPane.showMessageDialog(null, "Your username maximum charactuer should be 20 only.");
        } else {
            String query = "SELECT * FROM `user` WHERE `uname` =? AND `pass`=?";
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, uname);
                ps.setString(2, pass);

                rs = ps.executeQuery();

                if (rs.next()) {
                    String fname = rs.getString(4);/*4 means fname in our database*/

                    B_AMainForm h = new B_AMainForm(fname);
                    h.setVisible(true);
                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or password");

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public A_LoginForm() {
        initComponents();
        
        //logo change
        Image imgLogo = new ImageIcon(this.getClass().getResource("/inv_images/LCBC_logo_pr.png")).getImage();
        this.setIconImage(imgLogo);
        
        
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

        //ShowHidePw Icon
        Icon hide = ShowHidePw.getIcon();
        ImageIcon iconHide = (ImageIcon) hide;
        Image iconH = iconHide.getImage().getScaledInstance(minimizeBtn.getWidth(), minimizeBtn.getHeight(), Image.SCALE_SMOOTH);
        ShowHidePw.setIcon(new ImageIcon(iconH));

        //Rounder Corner
        // x,y,jframe width,jframe height,arc width,arc height
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));

        //Placeholder style
        addPlaceholderStyle(UsernameField);
        addPlaceholderStyle(PasswordField);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginMainPanel = new javax.swing.JPanel();
        NavBar = new javax.swing.JPanel();
        exitBtn = new javax.swing.JLabel();
        minimizeBtn = new javax.swing.JLabel();
        UsernameField = new javax.swing.JTextField();
        PasswordField = new javax.swing.JPasswordField();
        ForgotYrPass = new javax.swing.JLabel();
        DontHave = new javax.swing.JLabel();
        SignUpLabel = new javax.swing.JLabel();
        UsrnameLbl = new javax.swing.JLabel();
        PwLbl = new javax.swing.JLabel();
        LoginBtn = new com.k33ptoo.components.KButton();
        ShowHidePw = new javax.swing.JLabel();
        PanelLine = new com.k33ptoo.components.KGradientPanel();
        PanelLine1 = new com.k33ptoo.components.KGradientPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 500));
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
        LoginMainPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(28, 142, 193)));
        LoginMainPanel.setMaximumSize(new java.awt.Dimension(600, 500));
        LoginMainPanel.setMinimumSize(new java.awt.Dimension(600, 500));
        LoginMainPanel.setPreferredSize(new java.awt.Dimension(600, 500));

        NavBar.setBackground(new java.awt.Color(28, 142, 193));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        UsernameField.setBackground(new java.awt.Color(245, 215, 189));
        UsernameField.setFont(new java.awt.Font("Retro Computer", 0, 18)); // NOI18N
        UsernameField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        UsernameField.setBorder(null);
        UsernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UsernameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                UsernameFieldFocusLost(evt);
            }
        });
        UsernameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsernameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UsernameFieldKeyReleased(evt);
            }
        });

        PasswordField.setBackground(new java.awt.Color(245, 215, 189));
        PasswordField.setFont(new java.awt.Font("Retro Computer", 0, 18)); // NOI18N
        PasswordField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PasswordField.setBorder(null);
        PasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusLost(evt);
            }
        });
        PasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PasswordFieldKeyReleased(evt);
            }
        });

        ForgotYrPass.setFont(new java.awt.Font("Retro Computer", 0, 12)); // NOI18N
        ForgotYrPass.setForeground(new java.awt.Color(28, 142, 193));
        ForgotYrPass.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ForgotYrPass.setText("Forgot your password?");
        ForgotYrPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ForgotYrPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ForgotYrPassMouseClicked(evt);
            }
        });

        DontHave.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        DontHave.setForeground(new java.awt.Color(0, 0, 0));
        DontHave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DontHave.setText("don't have an account?");

        SignUpLabel.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        SignUpLabel.setForeground(new java.awt.Color(28, 142, 193));
        SignUpLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SignUpLabel.setText("sign up now");
        SignUpLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SignUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignUpLabelMouseClicked(evt);
            }
        });

        UsrnameLbl.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        UsrnameLbl.setForeground(new java.awt.Color(0, 0, 0));
        UsrnameLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UsrnameLbl.setText("USERNAME");

        PwLbl.setFont(new java.awt.Font("Retro Computer", 0, 13)); // NOI18N
        PwLbl.setForeground(new java.awt.Color(0, 0, 0));
        PwLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PwLbl.setText("PASSWORD");

        LoginBtn.setText("LOG IN");
        LoginBtn.setFont(new java.awt.Font("Retro Computer", 1, 18)); // NOI18N
        LoginBtn.setkBorderRadius(30);
        LoginBtn.setkEndColor(new java.awt.Color(28, 142, 193));
        LoginBtn.setkForeGround(new java.awt.Color(245, 215, 189));
        LoginBtn.setkHoverEndColor(new java.awt.Color(245, 195, 152));
        LoginBtn.setkHoverForeGround(new java.awt.Color(28, 142, 193));
        LoginBtn.setkHoverStartColor(new java.awt.Color(245, 195, 152));
        LoginBtn.setkPressedColor(new java.awt.Color(242, 179, 65));
        LoginBtn.setkStartColor(new java.awt.Color(28, 142, 193));
        LoginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginBtnMouseClicked(evt);
            }
        });
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });

        ShowHidePw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inv_images/eyeHide.png"))); // NOI18N
        ShowHidePw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ShowHidePw.setMaximumSize(new java.awt.Dimension(41, 30));
        ShowHidePw.setMinimumSize(new java.awt.Dimension(41, 30));
        ShowHidePw.setPreferredSize(new java.awt.Dimension(41, 30));
        ShowHidePw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ShowHidePwMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ShowHidePwMouseReleased(evt);
            }
        });

        PanelLine.setkEndColor(new java.awt.Color(28, 142, 193));
        PanelLine.setkStartColor(new java.awt.Color(28, 142, 193));
        PanelLine.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout PanelLineLayout = new javax.swing.GroupLayout(PanelLine);
        PanelLine.setLayout(PanelLineLayout);
        PanelLineLayout.setHorizontalGroup(
            PanelLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        PanelLineLayout.setVerticalGroup(
            PanelLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        PanelLine1.setkEndColor(new java.awt.Color(28, 142, 193));
        PanelLine1.setkStartColor(new java.awt.Color(28, 142, 193));

        javax.swing.GroupLayout PanelLine1Layout = new javax.swing.GroupLayout(PanelLine1);
        PanelLine1.setLayout(PanelLine1Layout);
        PanelLine1Layout.setHorizontalGroup(
            PanelLine1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        PanelLine1Layout.setVerticalGroup(
            PanelLine1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout LoginMainPanelLayout = new javax.swing.GroupLayout(LoginMainPanel);
        LoginMainPanel.setLayout(LoginMainPanelLayout);
        LoginMainPanelLayout.setHorizontalGroup(
            LoginMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NavBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(LoginMainPanelLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addGroup(LoginMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelLine, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PwLbl)
                    .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DontHave, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsrnameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ForgotYrPass, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(LoginMainPanelLayout.createSequentialGroup()
                        .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(ShowHidePw, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(SignUpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        LoginMainPanelLayout.setVerticalGroup(
            LoginMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginMainPanelLayout.createSequentialGroup()
                .addComponent(NavBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(UsrnameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(PanelLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(PwLbl)
                .addGroup(LoginMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginMainPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(LoginMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LoginMainPanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(PanelLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(ForgotYrPass, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(DontHave, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(SignUpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginMainPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(ShowHidePw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        getContentPane().add(LoginMainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void minimizeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeBtnMouseClicked
        //minimize
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeBtnMouseClicked

    private void exitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitBtnMouseClicked

    private void NavBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NavBarMousePressed
        //Draggable Frame Get Jfram Coord value
        positionX = evt.getX();
        positionY = evt.getY();
    }//GEN-LAST:event_NavBarMousePressed

    private void NavBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NavBarMouseDragged
        //Draggable Frame set JFrame Location
        setLocation(evt.getXOnScreen() - positionX, evt.getYOnScreen() - positionY);
    }//GEN-LAST:event_NavBarMouseDragged

    private void UsernameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameFieldFocusGained
        //PlaceholderUsername
        if (UsernameField.getText().equals("")) {
            UsernameField.setText(null);
            UsernameField.requestFocus();
            //remove placeholder style
            removePlaceholderStyle(UsernameField);
        }

    }//GEN-LAST:event_UsernameFieldFocusGained

    private void UsernameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameFieldFocusLost
        //PlaceholderUsername
        if (UsernameField.getText().length() == 0) {
            //add placeholder style
            addPlaceholderStyle(UsernameField);
            UsernameField.setText("");
        }

    }//GEN-LAST:event_UsernameFieldFocusLost

    private void PasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusGained
        //PlaceholderPassword
        if (PasswordField.getText().equals("")) {
            PasswordField.setText(null);
            PasswordField.requestFocus();
            //set pw character
            PasswordField.setEchoChar('*');
            //remove placeholder style
            removePlaceholderStyle(PasswordField);
        }
    }//GEN-LAST:event_PasswordFieldFocusGained

    private void PasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusLost
        //PlaceholderUsername
        if (PasswordField.getText().length() == 0) {
            //add placeholder style
            addPlaceholderStyle(PasswordField);
            PasswordField.setText("");

        }

    }//GEN-LAST:event_PasswordFieldFocusLost

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // this.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void SignUpLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignUpLabelMouseClicked
        A_SignupForm s = new A_SignupForm();
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SignUpLabelMouseClicked

    private void LoginBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginBtnMouseClicked
        login();
    }//GEN-LAST:event_LoginBtnMouseClicked

    private void ForgotYrPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ForgotYrPassMouseClicked
        A_ForgotForm f = new A_ForgotForm();
        f.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_ForgotYrPassMouseClicked

    private void ShowHidePwMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowHidePwMousePressed
        ImageIcon Sh = new ImageIcon(getClass().getResource("/inv_images/eyeShow.png"));
        Image iconSh = Sh.getImage().getScaledInstance(minimizeBtn.getWidth(), minimizeBtn.getHeight(), Image.SCALE_SMOOTH);
        ShowHidePw.setIcon(new ImageIcon(iconSh));
        PasswordField.setEchoChar((char) 0);
    }//GEN-LAST:event_ShowHidePwMousePressed

    private void ShowHidePwMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowHidePwMouseReleased
        ImageIcon Hd = new ImageIcon(getClass().getResource("/inv_images/eyeHide.png"));
        Image iconHd = Hd.getImage().getScaledInstance(minimizeBtn.getWidth(), minimizeBtn.getHeight(), Image.SCALE_SMOOTH);
        ShowHidePw.setIcon(new ImageIcon(iconHd));
        PasswordField.setEchoChar('*');
    }//GEN-LAST:event_ShowHidePwMouseReleased

    private void UsernameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameFieldKeyReleased
        int position = UsernameField.getCaretPosition();
        UsernameField.setText(UsernameField.getText().toUpperCase());
        UsernameField.setCaretPosition(position);
    }//GEN-LAST:event_UsernameFieldKeyReleased

    private void PasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordFieldKeyReleased
        int position = PasswordField.getCaretPosition();
        PasswordField.setText(PasswordField.getText().toUpperCase());
        PasswordField.setCaretPosition(position);
    }//GEN-LAST:event_PasswordFieldKeyReleased

    private void PasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_PasswordFieldKeyPressed

    private void UsernameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_UsernameFieldKeyPressed

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoginBtnActionPerformed

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
            java.util.logging.Logger.getLogger(A_LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(A_LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(A_LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(A_LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new A_LoginForm().setVisible(true);
            }
        });
        
       
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DontHave;
    private javax.swing.JLabel ForgotYrPass;
    private com.k33ptoo.components.KButton LoginBtn;
    private javax.swing.JPanel LoginMainPanel;
    private javax.swing.JPanel NavBar;
    private com.k33ptoo.components.KGradientPanel PanelLine;
    private com.k33ptoo.components.KGradientPanel PanelLine1;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PwLbl;
    private javax.swing.JLabel ShowHidePw;
    private javax.swing.JLabel SignUpLabel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsrnameLbl;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JLabel minimizeBtn;
    // End of variables declaration//GEN-END:variables
}
