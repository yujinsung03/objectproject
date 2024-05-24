import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EventSlider extends JFrame {

	private JLabel imageLabel;
	private JButton startButton;
	private JButton stopButton;
	private JButton nextButton;
	private JButton previousButton;
	private SlideshowController slideshowController;

	public EventSlider() {
		setTitle("Event Slider");
		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(JLabel.CENTER);

		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		nextButton = new JButton("Next");
		previousButton = new JButton("Previous");

		String[] eventPaths = {
			"../Images/1.jpeg",
			"../Images/2.jpeg",
			"../Images/3.jpeg" ,
			"../Images/4e.jpeg"
		};
		int eventWidth = 1100;
		int eventHeight = 550;

		EventLoader eventLoader = new EventLoader(eventPaths, eventWidth, eventHeight);
		slideshowController = new SlideshowController(this, eventLoader);

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				slideshowController.startSlideshow();
			}
		});

		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				slideshowController.stopSlideshow();
			}
		});

		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				slideshowController.nextEvent();
			}
		});

		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				slideshowController.previousEvent();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(previousButton);

		add(imageLabel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		slideshowController.loadEvent(0);
		setVisible(true);
	}

	public void updateImageLabel(ImageIcon icon) {
		imageLabel.setIcon(icon);
	}

	public static void main(String[] args) {
		new EventSlider();
	}
}
