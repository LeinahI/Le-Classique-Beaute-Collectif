package inv_amain;

import java.awt.Container;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import inv_amain.SQLConnection;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

public class Inv_ReportView extends JFrame {

    public Inv_ReportView(String fileName) {
        this(fileName, null);
    }

    public Inv_ReportView(String fileName, HashMap para) {
        super("Le Classique Beauté Collectif (Report Viewer)"); //report title

        //db connection
        SQLConnection sqlcon = new SQLConnection();
        Connection con = sqlcon.getConnection();

        try {
            JasperPrint print = JasperFillManager.fillReport(fileName, para, con);
            JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.add(viewer);
            // JasperPrintManager.printReport(print, false); /*This function is for automatic printer I guess this is necessary for printing receipt*/
            // JasperViewer.viewReport(print, false); /*This is for Viewing report but u don't need it*/
        } catch (JRException jRException) {
            System.out.println(jRException);
        }
        setBounds(2, 2, 900, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void print() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
