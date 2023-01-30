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

public class Inv_ReceiptPrint extends JFrame {

    public Inv_ReceiptPrint(String nameFile) {
        this(nameFile, null);
    }

    public Inv_ReceiptPrint(String nameFile, HashMap params) {
        super("Le Classique Beauté Collectif (Receipt Viewer)"); //report title

        //db connection
        SQLConnection sqlconnect = new SQLConnection();
        Connection connect = sqlconnect.getConnection();

        try {
            JasperPrint printReceipt = JasperFillManager.fillReport(nameFile, params, connect);
            JRViewer viewerReceipt = new JRViewer(printReceipt);
            Container cont = getContentPane();
            cont.add(viewerReceipt);
            JasperPrintManager.printReport(printReceipt, false);
            /*This function is for automatic printer I guess this is necessary for printing receipt*/

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
