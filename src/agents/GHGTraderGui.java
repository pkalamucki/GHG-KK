/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import history.MyTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author power
 */
class GHGTraderGui  extends JFrame{
    private GHGTrader myAgent; // Reference to the agent class
    public JTable table;


    public GHGTraderGui(GHGTrader a) {
        
        
        JPanel panel = new JPanel(new GridLayout(1, 0));
        this.setTitle(a.name);
        this.add(panel);
        
        myAgent = a; // provide the value of the reference of BankClientAgent class here
        
        table = new JTable(new MyTableModel(a.tList));
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}
