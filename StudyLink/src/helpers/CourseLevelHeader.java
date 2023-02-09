package helpers;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class CourseLevelHeader extends JLabel{

    public CourseLevelHeader(String courseLevel, Color foregroundColor, Border border, Font font) {
        this.setText(courseLevel);
        this.setForeground(foregroundColor);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setBorder(border);
		this.setFont(font);
    }
}