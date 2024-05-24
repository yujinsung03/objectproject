import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SlideshowController { 
	private EventSlider slider;
	private EventLoader eventLoader;
	private Timer timer;
	private int currentEventIndex = 0;


	public SlideshowController(EventSlider slider, EventLoader eventLoader) {
		this.slider = slider;
		this.eventLoader = eventLoader;
	}

	public void loadEvent(int index) {
		ImageIcon resizedIcon = eventLoader.loadEvent(index);
		slider.updateImageLabel(resizedIcon);
	}

	public void startSlideshow() {
		if(timer ==null){
			timer = new Timer(5000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					currentEventIndex = (currentEventIndex + 1)% eventLoader.getEventPaths().length;
					loadEvent(currentEventIndex);
				}
			});
			timer.start();
		}
	}

	public void stopSlideshow() {
		if(timer != null) {
			timer.stop();
			timer = null;
		}
	}

	public void nextEvent() {
		currentEventIndex = (currentEventIndex +1)% eventLoader.getEventPaths().length;
		loadEvent(currentEventIndex);
	}

	public void previousEvent() {
		currentEventIndex = (currentEventIndex -1)% eventLoader.getEventPaths().length;
		loadEvent(currentEventIndex);
	}
}