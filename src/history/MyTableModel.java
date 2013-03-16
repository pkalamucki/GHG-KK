/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package history;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author power
 */
public class MyTableModel extends AbstractTableModel {
    private String[] columnNames = {"Current emission", 
                                    "Volume",
                                    "Transaction price",
                                    "Shadow price",
                                    "Reduction cost",
                                    "Purchase cost"};
    private TransactionList data;
    
    public MyTableModel(TransactionList list){
        this.data = list;
        list.setTableModel(this);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.getList().size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) return data.getList().get(row).getF();
        if (col == 1) return data.getList().get(row).getVolume();
        if (col == 2) return data.getList().get(row).getTransactionPrice();
        if (col == 3) return data.getList().get(row).getShadowPrice();
        if (col == 4) return data.getList().get(row).getKosztRedukcji();
        if (col == 5) return data.getList().get(row).getKosztZakupu();
        return null;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
            return false;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
}

