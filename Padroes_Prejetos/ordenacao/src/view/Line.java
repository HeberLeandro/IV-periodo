package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Line implements Draw{

		@Override
	public void toDraw(int pivoInx, Graphics g, List<Integer> numbers, int PanelWidth, int PanelHeight) {
	        g.setColor(Color.white);
	        int outerSpace = 5;
	        int innerSpace = 2;
	        int QTD = numbers.size();
	        int width = (PanelWidth - 2 * outerSpace - (QTD - 1) * innerSpace) / QTD;
	        int height = (PanelHeight - 2 * outerSpace) / QTD;
	        
	        for (int idx = 0; idx < QTD; idx++) {
	            int n = numbers.get(idx);
	            g.fillRect(idx * (width + innerSpace) + outerSpace, PanelHeight - n * height - outerSpace, width, height/2);
	        }
	}
		
		
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Line";
	}



}
