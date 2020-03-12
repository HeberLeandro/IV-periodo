package view;

import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

public class MyComboBox extends JComboBox{
	
    public MyComboBox(Draw[] forms) {
        super(forms);
        this.addItemListener((ItemEvent e) -> {
            Draw d = (Draw)e.getItem();
            System.out.println("Você selecionou o item!");
        });}
}
