package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import sun.awt.windows.ThemeReader;

public class MyPanel extends JPanel {

    MainWindow frame;
    int pivoIdx = -1;
    int comparado = -1;
    int deley;
    
		
    protected List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

    public MyPanel(MainWindow mw) {
        this.frame = mw;
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
        pivoIdx = -1;
        repaint();
    }
    
    public void bubbleSort() {
    	boolean hadChange = true;
    	int aux = 0;
    	
    	while (hadChange) {
    		hadChange = false;
			for (int i = 0; i < numbers.size() - 1; i++) {
				deley = (Integer) this.frame.getdQuantity().getValue();
				pivoIdx = i;
				comparado = i+1;
				repaint();
				try { Thread.sleep(deley);} catch (InterruptedException e) {}
				if (numbers.get(i) > numbers.get(i+1)) {
					aux = numbers.get(i);
					numbers.set(i, numbers.get(i+1));
					numbers.set(i+1, aux);
					pivoIdx = i+1;
					comparado = i;
					repaint();
					try { Thread.sleep(deley);} catch (InterruptedException e) {}
					hadChange = true;
				}	
				
			}
		}
    	comparado = -1;
    	pivoIdx = -2;
    	repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.frame.getComboBox().getDrawSelected().toDraw(this.pivoIdx, this.comparado, g, numbers, this.getHeight(), this.getHeight());
    }
}
