import javax.swing.*;
import java.awt.*;

public class EventLoader {
    private String[] eventPaths;
    private int eventWidth;
    private int eventHeight;

    public EventLoader(String[] eventPaths, int eventWidth, int eventHeight) {
        this.eventPaths = eventPaths;
        this.eventWidth = eventWidth;
        this.eventHeight = eventHeight;
    }

    public ImageIcon loadEvent(int index) {
        ImageIcon originalIcon = new ImageIcon(eventPaths[index]);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(eventWidth, eventHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
	public String[] getEventPaths() {
		return eventPaths;
	}
}
