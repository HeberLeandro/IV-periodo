package view;

public class InsertionSort extends OrderMethod {

	@Override
	public void sort() {
        for (int i = 1; i < this.numbers.size(); ++i) { 
            int key = this.numbers.get(i); 
            int j = i - 1; 
            
            try { Thread.sleep(delay);} catch (InterruptedException e) {
            	
            }
            
            while (j >= 0 && this.numbers.get(j) > key) { 
            	this.numbers.set(j+1, this.numbers.get(j)); 
                j = j - 1; 
                arrayChanged();
                try { Thread.sleep(delay);} catch (InterruptedException e) {}
            } 
            this.numbers.set(j+1, key); 
            arrayChanged();
        }
        System.out.println(toString());
		
	}
	
    @Override
    public String toString() {
    	return "InsertionSort";
    }
}
