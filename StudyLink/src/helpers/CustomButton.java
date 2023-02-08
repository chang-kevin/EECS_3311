package helpers;
import java.awt.*;
import javax.swing.*;


/**
 * Styling for customized Dashboard buttons. 
 *
 */
public class CustomButton extends JButton {
	private JButton btn; 
	
	public CustomButton() {
		
	}
	
	/**
	 * Contains the layout for the taskbar buttons. 
	 * @param name The name of the button. 
	 * @return btn Returns the JButton with styling. 
	 */
	public JButton taskBarButton(String name) {
		btn = new JButton();
		btn.setText(name);
		btn.setForeground(new Color(198, 207, 232));
		btn.setBackground(new Color(70, 99, 172));
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		if(name == "+") {
			btn.setFont(new Font("Tahoma", Font.BOLD, 36));
		}
		else {
			btn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		}
		
		return btn;
	}
	
	/**
	 * Customizes the course list drop down menu buttons. 
	 * @param name The name of the button.
	 * @return btn Returns the JButton with styling.
	 */
	public JButton courseListMenu(String name) {
		btn = new JButton();
		btn.setText(name);
		btn.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setForeground(new Color(198, 207, 232, 150));
		btn.setBackground(new Color(70, 99, 172, 150));
		return btn;
	}
	
	/**
	 * Customizes the profile drop down menu buttons. 
	 * @param name The name of the button.
	 * @return btn Returns the JButton with styling.
	 */
	public JButton profileListMenu(String name) {
		btn = new JButton();
		btn.setText(name);
		btn.setForeground(new Color(198, 207, 232, 150));
		btn.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setBackground(new Color(70, 99, 172, 150));
		return btn;	
	}
}