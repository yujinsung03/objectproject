package objectproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class thirdpage extends JPanel{

    public thirdpage(page2frame frame) {
        setLayout(null);

        JButton prevPageButton = new JButton("<");
        prevPageButton.setBounds(50, 350, 50, 50); // 버튼 위치와 크기 설정
        prevPageButton.addActionListener(e -> frame.showSecondPage());
        add(prevPageButton);
        
        JPanel panel = new page2panel2();
        panel.setBounds(150, 30, 254, 310);

        JPanel panel2 = new page2panel3();
        panel2.setBounds(470, 50, 254, 310);

        JPanel panel3 = new page2panel4();
        panel3.setBounds(800, 50, 254, 310);

        JPanel panel4 = new page2panel5();
        panel4.setBounds(1130, 50, 254, 310);

        JPanel panel5 = new page2panel6();
        panel5.setBounds(150, 400, 254, 310);

        JPanel panel6 = new page2panel7();
        panel6.setBounds(470, 400, 254, 310);

        JPanel panel7 = new page2panel8();
        panel7.setBounds(800, 400, 254, 310);

        JPanel panel8 = new page2panel9();
        panel8.setBounds(1130, 400, 254, 310);

        setVisible(true);
        
        add(panel);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        add(panel6);
        add(panel7);
        add(panel8);

    }
}