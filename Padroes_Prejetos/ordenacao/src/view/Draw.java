package view;

import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public interface Draw {
	public void toDraw(Graphics g, List<Integer> numbers, int PanelWidth, int PanelHeight);
}
