package helpers;
import java.awt.*;
import javax.swing.*;

public class CourseButton extends JButton{

    public CourseButton(String courseName, Font font, Color backgroundColor, Color foregroundColor) {
        this.setText(courseName);
		this.setFocusPainted(false);
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.setFont(font);
		this.setBackground(backgroundColor);
		this.setForeground(foregroundColor);
		this.setBorder(null);
    }
    
}
