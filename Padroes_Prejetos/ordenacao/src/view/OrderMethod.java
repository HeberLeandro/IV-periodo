package view;

import java.util.List;
import java.util.Observable;

public abstract class OrderMethod extends Observable {
	protected List<Integer> numbers;
	protected Integer delay;
	
	@SuppressWarnings("deprecation")
	public void arrayChanged () {
		setChanged();
		notifyObservers();
	}

	
    public abstract void sort();
    
    public void setDelay(Integer delay) {
		this.delay = delay;
	}
    
    public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
    }
}
