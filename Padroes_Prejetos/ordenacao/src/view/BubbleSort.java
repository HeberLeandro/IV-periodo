package view;

import java.util.List;
import java.util.Observable;

public class BubbleSort extends OrderMethod{
	
    public void sort() {
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
    	System.out.println(toString());
    }
    
    @Override
    public String toString() {
    	return "BubbleSort";
    }
}
