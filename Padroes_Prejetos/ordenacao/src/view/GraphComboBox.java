package view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

public class GraphComboBox extends JComboBox {
	Map<String, Draw> comboObjects = new HashMap<String, Draw>();
	
	public GraphComboBox(Draw[] draws) {
		for (Draw draw : draws) {
			this.addItem(draw.toString());
			comboObjects.put(draw.toString(), draw);
		}
	}
	
	public Draw getDrawSelected() {
		return comboObjects.get(this.getSelectedItem().toString());
	}

}
