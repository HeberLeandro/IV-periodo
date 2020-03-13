package view;

import java.awt.Graphics;
import java.util.List;

public interface Draw {
	public void toDraw(int pivoInx,Graphics g, List<Integer> numbers, int PanelWidth, int PanelHeight);
}
