package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MainWindow extends JFrame {

    MyPanel pCanvas;
    JSpinner sQuantity;
    JSpinner dQuantity;
    Draw[] drawOptions  = {new Histogram(), new Line()};
    GraphComboBox comboGraph;
    MethodsFactoryComboBox comboMethods;
    OrderMethod[] orderOptions = {new BubbleSort(), new InsertionSort()};
    List<Thread> threadAux = new ArrayList<Thread>();
    
    private void createWindow() {
        this.setPreferredSize(new Dimension(800, 900));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titulo = new JLabel("Sorting Algorithms");
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(titulo);
        topPanel.setMaximumSize(new Dimension(640, 50));
        this.add(topPanel);
        
        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        dQuantity = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 5));
        dQuantity.setToolTipText("Speed of Graphics Update");
        middlePanel.add(dQuantity);
        
        sQuantity = new JSpinner(new SpinnerNumberModel(25, 10, 2000, 1));
        sQuantity.setToolTipText("Quantity of numbers to sort");
        middlePanel.add(sQuantity);
        //BTN
        JButton bShuffle = new JButton("Shuffle");
        bShuffle.addActionListener(ae -> pCanvas.shuffle());
        JButton bSort = new JButton("Sort");
        
        bSort.addActionListener(ae -> 
        	{
				try {
					callSortThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
        );
        
        
        
        middlePanel.add(bShuffle);
        middlePanel.add(bSort);
        middlePanel.setMaximumSize(new Dimension(750, 50));
        this.add(middlePanel);
        
        JLabel graficoT = new JLabel("	Graphic Type:	");
        middlePanel.add(graficoT); 
        
        //combo box dos Graficos
        comboGraph = new GraphComboBox(drawOptions);
        comboGraph.addActionListener(ae -> pCanvas.repaint());
        middlePanel.add(comboGraph);
        
        JLabel sort = new JLabel("	Order Methods:	");
        middlePanel.add(sort); 
        // combo Box Ordenação 
        comboMethods = new MethodsFactoryComboBox(orderOptions);
        middlePanel.add(comboMethods);middlePanel.add(comboMethods);
        comboMethods.addActionListener(ae -> pCanvas.updateOrderMethod());
        
        //Stop Button
        JButton bStop = new JButton("Stop");
        bStop.addActionListener(ae -> stopSortThread());
        middlePanel.add(bStop);
        
       
        pCanvas = new MyPanel(this);
        this.add(pCanvas);

        this.pack();
    }
    
    public void callSortThread() throws InterruptedException {
    	if(!threadAux.isEmpty()) stopSortThread();
    	
    	Thread aux = new Thread(new Runnable() {
				@Override
				public void run() {
					pCanvas.sort();
				}
		});
    	aux.start();
		threadAux.add(aux);
    		
    }
    
    public void stopSortThread() {
    	threadAux.get(threadAux.size() -1).stop();
    	threadAux.remove(threadAux.get(threadAux.size() -1));
    }
    
    public JSpinner getsQuantity() {
        return sQuantity;
    }
    
    public JSpinner getdQuantity() {
        return dQuantity;
    }
    
    public GraphComboBox getGraphComboBox(){
    	return comboGraph;
    }
    
    public MethodsFactoryComboBox getMethodsComboBox(){
    	return comboMethods;
    }
    
    public MainWindow() {
        super("Sorting Algorithms");
    }
    
    public static void main(String[] args) {
        MainWindow w = new MainWindow();
        w.createWindow();
    }
}