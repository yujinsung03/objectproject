package objectproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class page2frame extends JFrame {
	CardLayout cardLayout;
	JPanel mainPanel;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	
    public page2frame() {
        setSize(2000, 1000);
        setTitle("page4444");
        setLayout(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        panel1 = new firstpage(this);
        panel2 = new secondpage(this);
        panel3 = new thirdpage(this);
        
        mainPanel.add(panel1, "firstpage");
        mainPanel.add(panel2, "secondpage");
        mainPanel.add(panel3, "thirdpage");        
        mainPanel.setBounds(0, 0, 2000, 700);
        add(mainPanel);
        
        //초성 버튼들
        JButton j1 = new JButton("ㄱ");
    	JButton j2 = new JButton("ㄴ");
    	JButton j3 = new JButton("ㄷ");
    	JButton j4 = new JButton("ㄹ");
    	JButton j5 = new JButton("ㅁ");
    	JButton j6 = new JButton("ㅂ");
    	JButton j7 = new JButton("ㅅ");
    	JButton j8 = new JButton("ㅇ");
    	JButton j9 = new JButton("ㅈ");
    	JButton j10 = new JButton("ㅊ");
    	JButton j11 = new JButton("ㅋ");
    	JButton j12 = new JButton("ㅌ");
    	JButton j13 = new JButton("ㅍ");
    	JButton j14 = new JButton("ㅎ");
    	
    	styleButton(j1);
		styleButton(j2);
		styleButton(j3);
		styleButton(j4);
		styleButton(j5);
		styleButton(j6);
		styleButton(j7);
		styleButton(j8);
		styleButton(j9);
		styleButton(j10);
		styleButton(j11);
		styleButton(j12);
		styleButton(j13);
		styleButton(j14);
		
		j1.setBounds(250, 740, 50, 50);
		j2.setBounds(320, 740, 50, 50);
		j3.setBounds(390, 740, 50, 50);
		j4.setBounds(460, 740, 50, 50);
		j5.setBounds(530, 740, 50, 50);
		j6.setBounds(600, 740, 50, 50);
		j7.setBounds(670, 740, 50, 50);
		j8.setBounds(740, 740, 50, 50);
		j9.setBounds(810, 740, 50, 50);
		j10.setBounds(880, 740, 50, 50);
		j11.setBounds(950, 740, 50, 50);
		j12.setBounds(1020, 740, 50, 50);
		j13.setBounds(1090, 740, 50, 50);
		j14.setBounds(1160, 740, 50, 50);
		
	
		add(j1);
		add(j2);
		add(j3);
		add(j4);
		add(j5);
		add(j6);
		add(j7);
		add(j8);
		add(j9);
		add(j10);
		add(j11);
		add(j12);
		add(j13);
		add(j14);
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
    }
    	// 버튼 스타일 설정 메서드
    	private void styleButton(JButton button) {
    		button.setBackground(Color.WHITE);
    		button.setForeground(Color.BLACK);
    		button.setFont(new Font("나눔 고딕", Font.BOLD, 30));
    		button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    	}
    	
        public void showFirstPage() {
            cardLayout.show(mainPanel, "firstpage");
        }
        
        public void showSecondPage() {
            cardLayout.show(mainPanel, "secondpage");
        }
        
        public void showThirdPage() {
            cardLayout.show(mainPanel, "thirdpage");
        }
}