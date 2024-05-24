import java.awt.*;
import javax.swing.*;
import java.util.Calendar;

class TimerThread extends Thread {
	JLabel timerLabel;
	public TimerThread(JLabel Label) {
		timerLabel = Label;
	}

	public void run() {
		Calendar time = Calendar.getInstance();
		//int yy = cal.get(Calendar.YEAR);
		//int mm = cal.get(Calendar.MONTH) + 1;
		//int dd = cal.get(Calendar.DAY_OF_MONTH);
		int hh = time.get(Calendar.HOUR_OF_DAY);
		int mi = time.get(Calendar.MINUTE);
		int sec = time.get(Calendar.SECOND);
		
		while(true) {
			timerLabel.setText((Integer.toString(hh))+"시"+(Integer.toString(mi))+"분");
			sec++;
			if(sec==60) {
				sec = 0;
				mi++;
			}
			if(mi==60){
				mi = 0;
				hh++;
			}
			try{Thread.sleep(1000);}
			catch(InterruptedException e) {return;}
		}
	}
}