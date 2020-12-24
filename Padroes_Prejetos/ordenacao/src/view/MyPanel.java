package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

@SuppressWarnings("deprecation")
public class MyPanel extends JPanel implements Observer {

    MainWindow frame;
	OrderMethod orderMethod;
  
    protected List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

    public MyPanel(MainWindow mw) {
        this.frame = mw;
        this.orderMethod = new BubbleSort();
        orderMethod.setNumbers(this.numbers);
        orderMethod.setDelay((Integer)this.frame.getdQuantity().getValue());
        orderMethod.addObserver(this);
    }
    
    private void createRandomizedArray(int qtd) {
        this.numbers = new ArrayList(qtd);
        for(int i = 1; i <= qtd; i++) {
            this.numbers.add(i);
        }
        Collections.shuffle(numbers);
    }
    
    public void shuffle() {
        int quantity = (Integer) this.frame.getsQuantity().getValue();
        this.createRandomizedArray(quantity);
        this.orderMethod.numbers = this.numbers;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        orderMethod.setDelay((Integer)this.frame.getdQuantity().getValue()); // Atualiza o delay da ordenação
        this.frame.getGraphComboBox().getDrawSelected().toDraw(g, numbers, this.getHeight(), this.getHeight()); // Chama o grafico selecionado para pintar a tela
    }

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void sort() {
		orderMethod.sort();
	}

	public void updateOrderMethod() {
		this.orderMethod = this.frame.getMethodsComboBox().getMethodSelected();
		orderMethod.setNumbers(this.numbers);
        orderMethod.setDelay((Integer)this.frame.getdQuantity().getValue());
        orderMethod.addObserver(this);
        try {
			this.frame.callSortThread();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
