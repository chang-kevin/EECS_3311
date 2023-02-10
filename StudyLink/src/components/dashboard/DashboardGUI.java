package components.dashboard;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public interface DashboardGUI {
	public JButton createTaskbarButton(String name);
	
	public JFrame createFrame(String title);
	public JButton createMenuButton(String name);

	public JPanel createPanel();
	public JPanel createMenuPanel();
}

