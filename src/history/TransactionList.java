/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package history;

import agents.GHGTrader;
import java.util.ArrayList;
import java.util.Iterator;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author power
 */
public class TransactionList {
    private ArrayList<Transaction> list;
    private MyTableModel tableModel;

    public TransactionList() {
        this.list = new ArrayList<Transaction>();
        tableModel = new MyTableModel(this);
    }
    
    public ArrayList<Transaction> getList() {
        return list;
    }   
    
    public void add(Transaction t){
        this.list.add(t);
        this.tableModel.fireTableStructureChanged();
    }
    
    public String summary(GHGTrader agent){
        Transaction last = list.get(list.size() - 1);
        StringBuilder sb = new StringBuilder();
        Double sum = 0.0;
        sb.append("\tName: ");
        sb.append(agent.getLocalName());
        sb.append("\t F:");
        
        sb.append(last.getF());
        sb.append("\t ShadowPrice:");
        
        sb.append(agent.shadowPrice(0, -1));
        sb.append("\t TransactionPrice:");
        
        sb.append(last.getTransactionPrice());
        sb.append("\t");
        
        Iterator iter = list.iterator();
        while(iter.hasNext()){
            Transaction tr = (Transaction) iter.next();
            sum += tr.getVolume();
        }
        
        sb.append(sum);
        sb.append("\t");
        
        sb.append(last.getKosztRedukcji());
        sb.append("\t");
        
        sb.append(last.getKosztZakupu());
        sb.append("\t");
        return sb.toString();
    }
    
    public Double getPriceDifferenceMovingAverage(int steps){
        Double rv = 150.0;
        
        if (this.list == null || this.list.isEmpty()) return rv;
        
        rv = 0.0;
        for(int i = 0; i < Math.min(steps, list.size()); i++){
            rv += Math.abs(list.get(list.size() - i - 1).getTransactionPrice() - list.get(list.size() - i - 1).getShadowPrice());
        }
        rv /= Math.min(steps, list.size());
        
        return rv;
    }
    public Double getVolumeMovingAverage(int steps){
        Double rv = 3.0;
        
        if (this.list == null || this.list.isEmpty()) return rv;
        
        rv = 0.0;
        for(int i = 0; i < Math.min(steps, list.size()); i++){
            rv += list.get(list.size() - i - 1).getVolume();
        }
        rv /= Math.min(steps, list.size());
        
        return rv;
    }

    public Double getPriceMaxDev(int steps, Double mean) {
        Double rv = 10.0;
        
        if (this.list == null || this.list.isEmpty()) return rv;
        int size = list.size();
        
        rv = 0.0;
        for(int i = 0; i < Math.min(steps, list.size()); i++){
            rv += Math.pow(mean - list.get(size - i - 1).getTransactionPrice(), 2);
        }
        rv = Math.sqrt(rv);
        
        return rv;
    }

    void setTableModel(MyTableModel aThis) {
        this.tableModel = aThis;
    }
}
