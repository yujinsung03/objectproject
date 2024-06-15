package 객체지향기말1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class Parkingexit2 {
    private static final String CSV_FILE = "C:\\Users\\sjh35\\eclipse-workspace\\객체지향2024\\bin\\객체지향기말1\\car1.csv";
    private static final Map<String, String> ParkingSpots = new HashMap<>();
    private static final StringBuilder carnum = new StringBuilder();
    private static DefaultListModel<String> exitListModel = new DefaultListModel<>();

    public static void main(String[] args) {
        loadDatafromCSV();

        JFrame frame = new JFrame("출차페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000); // 프레임 크기 조정

        // 배경 이미지 패널
        BackgroundPanel panel = new BackgroundPanel(new ImageIcon("C:\\Users\\sjh35\\eclipse-workspace\\객체지향기말\\background2.png").getImage());
        panel.setLayout(null);

        // 출차된 차량 리스트
        JList<String> exitList = new JList<>(exitListModel);
        JScrollPane scrollPane = new JScrollPane(exitList);
        scrollPane.setBounds(60, 250, 700, 450); // 위치와 크기 조정
        panel.add(scrollPane);

        // 번호 입력 영역
        JLabel numberLabel = new JLabel("차량번호:");
        numberLabel.setBounds(860, 300, 80, 25); // 위치 조정
        panel.add(numberLabel);

        JLabel ParkingLabel = new JLabel("차량번호를 입력하세요");
        ParkingLabel.setBounds(920, 260, 220, 25); // 위치 조정
        ParkingLabel.setFont(new Font("돋움", Font.PLAIN, 18));
        panel.add(ParkingLabel);

        JLabel writenum = new JLabel("");
        writenum.setBounds(920, 300, 170, 25); // 위치와 크기 조정
        writenum.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(writenum);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(1090, 300, 80, 25); // 위치와 크기 조정
        panel.add(clearButton);

        clearButton.addActionListener(e -> {
            carnum.setLength(0);
            writenum.setText("");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 10, 10)); // 버튼 간격 조정 및 행 추가
        buttonPanel.setBounds(860, 350, 280, 300); // 위치와 크기 조정

        ActionListener numberButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carnum.length() < 4) {
                    carnum.append(e.getActionCommand());
                    writenum.setText(carnum.toString());
                }
            }
        };

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font("Serif", Font.BOLD, 20)); // 버튼 글꼴 크기 조정
            button.addActionListener(numberButtonListener);
            buttonPanel.add(button);
        }

        // 0 버튼 추가
        JButton zeroButton = new JButton("0");
        zeroButton.setFont(new Font("Serif", Font.BOLD, 20)); // 버튼 글꼴 크기 조정
        zeroButton.addActionListener(numberButtonListener);
        buttonPanel.add(zeroButton);

        // 빈 버튼 추가
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));

        panel.add(buttonPanel);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(860, 670, 300, 30); // 위치와 크기 조정
        searchButton.setFont(new Font("Serif", Font.BOLD, 15)); // 글꼴 크기 조정
        panel.add(searchButton);

        searchButton.addActionListener(e -> {
            if (carnum.length() == 4) {
                List<String> matchNum = findCar(carnum.toString());
                if (matchNum.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, carnum + " 해당 번호를 찾을 수 없습니다." );
                } else if (matchNum.size() == 1) {
                    String selectedCar = matchNum.get(0);
                    ExitCar(selectedCar);
                } else {
                    String selectedCar = chooseCarToExit(matchNum);
                    if (selectedCar != null) {
                        ExitCar(selectedCar);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "4자리번호를 입력하세요");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void loadDatafromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String carnum = parts[0].trim();
                    String parkingSpot = parts[1].trim();
                    ParkingSpots.put(carnum, parkingSpot);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ExitCar(String vehicle) {
        int response = JOptionPane.showConfirmDialog(null, "차량번호:" + vehicle + " 위치: " + ParkingSpots.get(vehicle) + "가 맞습니까?",
                "", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            removeCar(vehicle);
        }
    }

    private static void removeCar(String vehicle) {
        JOptionPane.showMessageDialog(null, "출차완료 차량번호: " + vehicle + " 위치: " + ParkingSpots.get(vehicle));
        exitListModel.addElement(vehicle); // 출차된 차량을 리스트에 추가
        ParkingSpots.remove(vehicle); // 주차 구역 정보에서 제거
        updateCSVFile();
    }

    private static void updateCSVFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (String key : ParkingSpots.keySet()) {
                String value = ParkingSpots.get(key);
                writer.write(key + "," + value);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<String> findCar(String number) {
        List<String> matchNum = new ArrayList<>();
        for (String vehicle : ParkingSpots.keySet()) {
            if (vehicle.contains(number)) {
                matchNum.add(vehicle);
            }
        }
        return matchNum;
    }

    private static String chooseCarToExit(List<String> matchNum) {
        return (String) JOptionPane.showInputDialog(
                null,
                "출차할 차량을 선택하시오",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                matchNum.toArray(),
                matchNum.get(0)
        );
    }

    static class BackgroundPanel extends JPanel {
        private Image image;

        public BackgroundPanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
