//이미지 슬라이더 + start, stop, next 버튼
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageSlider extends JFrame {

    private JLabel imageLabel;
    private JButton startButton;
    private JButton stopButton;
	private JButton nextButton;
	private JButton previousButton;
    private Timer timer;
    private int currentImageIndex = 0;
    private String[] imagePaths = {
        "../Images/pink.jpeg",
        "../Images/pink2.png",
        "../Images/sky.jpeg" ,
		"../Images/skyblue.png"
    };
    private int imageWidth = 1100;
    private int imageHeight = 550;

    public ImageSlider() {
        setTitle("Image Slider");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
		nextButton = new JButton("Next");
		previousButton = new JButton("Previous");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSlideshow();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSlideshow();
            }
        });

		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextImage();
			}
		});

		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousImage();
			}
		});

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(previousButton);
	

        add(imageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadImage(currentImageIndex);

        setVisible(true);
    }

    private void loadImage(int index) {
        ImageIcon originalIcon = new ImageIcon(imagePaths[index]);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        imageLabel.setIcon(resizedIcon);
    }

    private void startSlideshow() {
        if (timer == null) {
            timer = new Timer(4000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
                    loadImage(currentImageIndex);
                }
            });
            timer.start();
        }
    }

    private void stopSlideshow() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

	private void nextImage() {
		currentImageIndex = (currentImageIndex +1)% imagePaths.length;
		loadImage(currentImageIndex);
	}

	private void previousImage() {
		currentImageIndex = (currentImageIndex -1)% imagePaths.length;
		loadImage(currentImageIndex);
	}

    public static void main(String[] args) {
        new ImageSlider();
    }
}