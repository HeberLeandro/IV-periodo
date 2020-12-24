package view;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;

public class MethodsFactoryComboBox extends JComboBox {
	Map<String, OrderMethod> comboObjects = new HashMap<String, OrderMethod>();
	
	public MethodsFactoryComboBox(OrderMethod[] methodArr) {
		for (OrderMethod method : methodArr) {
			this.addItem(method.toString());
			comboObjects.put(method.toString(), method);
		}
	}
	
	public OrderMethod getMethodSelected() {
		return comboObjects.get(this.getSelectedItem().toString());
	}
}
