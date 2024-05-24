import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class DigitalClock extends JFrame{
	public DigitalClock() {
		setTitle("Digital Clock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 100);
		setVisible(true);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel TimeLabel = new JLabel();
		TimeLabel.setFont(new Font("Serif", Font.BOLD, 40));
		c.add(TimeLabel);

		TimerThread th = new TimerThread(TimeLabel);
		th.start();

	}
	public static void main(String args[]) {
		new DigitalClock();
	}
}

		//int yy = cal.get(Calendar.YEAR);
		//int mm = cal.get(Calendar.MONTH) + 1;
		//int dd = cal.get(Calendar.DAY_OF_MONTH);
		//int hh = cal.get(Calendar.HOUR_OF_DAY);
		//int mi = cal.get(Calendar.MINUTE);

