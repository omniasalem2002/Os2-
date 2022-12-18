package javaapplication2;


import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class producer_consumer_Solution {

    public static void main(String[] args) throws InterruptedException {
        Run r1=new Run();
        r1.setVisible(true);
        
       
        
        
    }
///////////////////////////////

    public static class producer_consumer {
            String x= "";

        int value = 0;
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 4;
        ////////////////////////////this is shared data 🙄

        public void produce(JTable jTable1) throws InterruptedException {
            //int value = 0;
            while (true) {
                synchronized (this) {
                    while (list.size() == capacity) {
                        wait();
                    }
                    
                    //System.out.println("Producer produced=" + value);
                    //x=("Producer produced=" + value);
                    DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();
                    tblModel.insertRow(tblModel.getRowCount(), new Object[]{"Producer produced=" + value});

                    list.add(value++);
                    notify();
                    Thread.sleep(500);
                }
            }

        }

        public void consume(JTable jTable1) throws InterruptedException {
            while (true) {
                synchronized (this) {
                    while (list.isEmpty()) {
                        wait();
                    }
                    int val = list.removeFirst();
                   // System.out.println("Consumer consumed=" + val);
                    
                    DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();
                    tblModel.insertRow(tblModel.getRowCount(), new Object[]{"Consumer consumed=" + val});
                    //x=("Consumer consumed=" + val);

                    notify();
                   Thread.sleep(500);
                }
            }
        }
    }
}
