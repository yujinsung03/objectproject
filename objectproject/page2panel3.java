package objectproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class page2panel3 extends JPanel {
	ImageIcon img;
	JButton b1 = new JButton("매장 설명");
	JButton b2 = new JButton("약도");
	
	public page2panel3() {	
		img = new ImageIcon("nan.jpg");
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(2, 2, 250, 250);
		add(imgLabel);
		
		setLayout(null);
		
		b1.setBounds(30, 270, 100, 30);
		b2.setBounds(150, 270, 70, 30);
		
		add(b1);
		add(b2);
		
		setBounds(0, 0, img.getIconWidth(), img.getIconHeight() + 50);
		setVisible(true);
	}
}
