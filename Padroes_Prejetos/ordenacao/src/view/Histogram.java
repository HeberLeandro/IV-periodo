package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Histogram implements Draw {

	@Override
	public void toDraw(int pivoInx, int comparado,Graphics g, List<Integer> numbers, int PanelWidth, int PanelHeight) {
        int outerSpace = 5;
        int innerSpace = 1;
        int QTD = numbers.size();
        int width = (PanelWidth - 2 * outerSpace - (QTD - 1) * innerSpace) / QTD;
        int height = (PanelHeight - 2 * outerSpace) / QTD;
        
        for (int idx = 0; idx < QTD; idx++) {
            int n = numbers.get(idx);
            if (pivoInx == idx) {
				g.setColor(Color.red);
            }
            else if (comparado == idx) {
            	g.setColor(Color.blue);
            }
            else if (pivoInx == -2) {
            	g.setColor(Color.green);
            }else g.setColor(Color.white);
            	
            g.fillRect(idx * (width + innerSpace) + outerSpace, PanelHeight - n * height - outerSpace, width, n * height);
        }
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Histogram";
	}
	
}
