package view;

import java.util.List;
import java.util.Observable;

public class OrderMethods extends Observable {
	protected List<Integer> numbers;
	private Integer delay;
	
	public OrderMethods(List<Integer> numbers) {
		this.numbers = numbers;
	}
	
	@SuppressWarnings("deprecation")
	public void arrayChanged () {
		setChanged();
		notifyObservers();
	}

	
    public void bubbleSort() {
    	boolean hadChange = true;
    	int aux = 0;
    	
    	while (hadChange) {
    		hadChange = false;
			for (int i = 0; i < this.numbers.size() - 1; i++) {
				try { Thread.sleep(delay);} catch (InterruptedException e) {}
				if (this.numbers.get(i) > this.numbers.get(i+1)) {
					aux = this.numbers.get(i);
					this.numbers.set(i, this.numbers.get(i+1));
					this.numbers.set(i+1, aux);
					arrayChanged();
					try { Thread.sleep(delay);} catch (InterruptedException e) {}
					hadChange = true;
				}	
				
			}
		}
    	arrayChanged();
    }
    
    public void setDelay(Integer delay) {
		this.delay = delay;
	}
}
