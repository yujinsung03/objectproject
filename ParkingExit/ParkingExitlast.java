package 객체지향기말;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ParkingExitlast {
    private static final List<String> vehicles = new ArrayList<>(Arrays.asList("12가 4599", "23가 4599", "34나 1234", "45다 5678"));
    // 차량 번호와 주차 구역 정보를 매핑합니다.
    private static final Map<String, String> vehicleParkingSpots = new HashMap<String, String>() {{
        put("12가 4599", "A-12");
        put("23가 4599", "B-23");
        put("34나 1234", "C-34");
        put("45다 5678", "D-45");
    }};
    private static final StringBuilder vehicleNumber = new StringBuilder();
    private static DefaultListModel<String> exitListModel = new DefaultListModel<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exit Request");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000); // 프레임 크기 조정

        // 배경 이미지 패널
        BackgroundPanel panel = new BackgroundPanel(new ImageIcon("C:\\Users\\sjh35\\eclipse-workspace\\객체지향기말\\background2.png").getImage());
        panel.setLayout(null);

        // 출차 이미지
        JLabel exitingLabel = new JLabel();
        exitingLabel.setIcon(new ImageIcon("C:\\객체2024\\0034.png="));
        exitingLabel.setBounds(40, 170, 720, 80); // 이미지 크기와 위치 조정
        panel.add(exitingLabel);

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
        ParkingLabel.setBounds(920, 260, 220, 25);// 위치 조정
        ParkingLabel.setFont(new Font("돋움", Font.PLAIN, 18));
        panel.add(ParkingLabel);

        JLabel vehicleLabel = new JLabel("");
        vehicleLabel.setBounds(920, 300, 170, 25); // 위치와 크기 조정
        vehicleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(vehicleLabel);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(1090, 300, 80, 25); // 위치와 크기 조정
        panel.add(clearButton);

        clearButton.addActionListener(e -> {
            vehicleNumber.setLength(0);
            vehicleLabel.setText("");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 10, 10)); // 버튼 간격 조정 및 행 추가
        buttonPanel.setBounds(860, 350, 280, 300); // 위치와 크기 조정

        ActionListener numberButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehicleNumber.length() < 4) {
                    vehicleNumber.append(e.getActionCommand());
                    vehicleLabel.setText(vehicleNumber.toString());
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
        searchButton.setFont(new Font("Serif", Font.BOLD, 20)); // 글꼴 크기 조정
        panel.add(searchButton);

        searchButton.addActionListener(e -> {
            if (vehicleNumber.length() == 4) {
                List<String> matchedVehicles = findVehiclesWithNumber(vehicleNumber.toString());
                if (matchedVehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Cannot find the number: " + vehicleNumber);
                } else if (matchedVehicles.size() == 1) {
                    String selectedVehicle = matchedVehicles.get(0);
                    exitVehicle(selectedVehicle);
                } else {
                    String selectedVehicle = chooseVehicleToExit(matchedVehicles);
                    if (selectedVehicle != null) {
                        exitVehicle(selectedVehicle);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a 4-digit number.");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
    
    private static void exitVehicle(String vehicle) {
        JOptionPane.showMessageDialog(null, "Exit completed: Vehicle number " + vehicle + ". It was parked at " + vehicleParkingSpots.get(vehicle));
        vehicles.remove(vehicle); // 차량 리스트에서 제거
        exitListModel.addElement(vehicle); // 출차된 차량을 리스트에 추가
        vehicleParkingSpots.remove(vehicle); // 주차 구역 정보에서 제거
    }
    private static List<String> findVehiclesWithNumber(String number) {
        List<String> matchedVehicles = new ArrayList<>();
        for (String vehicle : vehicles) {
            if (vehicle.contains(number)) {
                matchedVehicles.add(vehicle);
            }
        }
        return matchedVehicles;
    }

    private static String chooseVehicleToExit(List<String> matchedVehicles) {
        return (String) JOptionPane.showInputDialog(
                null,
                "Select the vehicle to exit:",
                "Vehicle Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                matchedVehicles.toArray(),
                matchedVehicles.get(0)
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