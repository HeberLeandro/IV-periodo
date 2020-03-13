package view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

public class MyComboBox extends JComboBox {
	Map<String, Draw> comboObjects = new HashMap<String, Draw>();
	
	public MyComboBox(Draw[] draws) {
		for (Draw draw : draws) {
			this.addItem(draw.toString());
			comboObjects.put(draw.toString(), draw);
		}
	}
	
	public Draw getDrawSelected() {
		return comboObjects.get(this.getSelectedItem().toString());
	}

}
