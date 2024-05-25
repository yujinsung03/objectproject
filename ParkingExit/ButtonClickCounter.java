package 객체지향기말;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ButtonClickCounter {
    private static HashMap<String, Integer> buttonClickCounts = new HashMap<>();
    private static JTextArea resultArea; // 결과를 표시할 JTextArea

    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("Button Click Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 패널 생성 및 프레임에 추가
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // 프레임 표시
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        String[] buttonNames = {"Nike", "Adidas", "Spao", "Puma", "Shinun", "Porter", "Maetingkim", "Dimangshi"};

        for (String name : buttonNames) {
            JButton button = new JButton(name);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonClicked(name);
                }
            });
            panel.add(button);
        }

        // showTop3Button 대신 실시간 업데이트를 위해 바로 JTextArea 추가
        resultArea = new JTextArea(5, 30); // 크기 설정
        resultArea.setEditable(false); // 수정 불가 설정
        JScrollPane scrollPane = new JScrollPane(resultArea); // 스크롤 가능하게 설정
        panel.add(scrollPane);
    }

    private static void buttonClicked(String buttonName) {
        // 클릭 횟수 업데이트
        buttonClickCounts.put(buttonName, buttonClickCounts.getOrDefault(buttonName, 0) + 1);
        // 클릭 후 실시간으로 Top 3 업데이트
        printTop3Buttons();
    }

    private static void printTop3Buttons() {
        StringBuilder sb = new StringBuilder();
        buttonClickCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"));

        // 결과를 JTextArea에 표시
        resultArea.setText(sb.toString());
    }
}
