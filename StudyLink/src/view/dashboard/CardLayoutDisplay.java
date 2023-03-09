package view.dashboard;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class to create the cardlayout for the dashboard display area. 
 *
 */

public class CardLayoutDisplay extends JPanel implements ActionListener {

	private CardLayout cardLayout;

	CourseLevel courses;
	JPanel displayArea;
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton courseBtn;
	private JButton uploadBtn;
	private JButton dashboardBtn;
	JPanel taskbar;
	private JPanel buttons;
	private List<ViewButtons> viewButtonsList;
	
	private static final String dashboardPanel = "Dashboard"; 
	private static final String uploadPanel = "Upload"; 
	private static final String onePanel = "One"; 
	private static final String twoPanel = "Two";
	private static final String threePanel = "Three"; 

	/**
	 * Constructor
	 */
	
	public CardLayoutDisplay() throws SQLException {
		displayArea = new JPanel();
		displayArea = panelBorder(displayArea);
		
		cardLayout = new CardLayout(0, 0);	
		displayArea.setLayout(cardLayout);
		
		createTaskbarPanel();
		createCard();

	}
	/**
	 * This method creates an object of the display panel and adds it into the cardlayout.
	 */
	public void createCard() throws SQLException {
		HomePage home = new HomePage();
		courses = new CourseLevel();
		viewButtonsList = new ArrayList<>();
		for (ViewButtons e: courses.viewButtonList) {
			e.getViewButton().addActionListener(this);
			viewButtonsList.add(e);
		}

		addCard(home.dashboard, dashboardPanel);	
		addCard(courses.oneLevel, onePanel);
		addCard(courses.twoLevel, twoPanel);
		addCard(courses.threeLevel, threePanel);
	}

	/**
	 * This method adds the panels in the cardlayout. 
	 * @param page JPanel component to be added to the cardlayout.
	 * @param key String key that identifies the component to be added. 
	 */
	public void addCard(JPanel page, String key) {

		displayArea.add(page, key);
	}
	
	
	/**
	 * This method sets the layout for the taskbar buttons. 
	 * @param btn JButton component 
	 * @param name Identifier name for the JButton
	 * @param y y-bound for JButton location
	 * @return JButton with custom layouts 
	 */
	public JButton createButton(JButton btn, String name, int y) {
		btn = new JButton(name);
		if(name == "Dashboard") {
			btn.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(239, 127, 127)));
			btn.setForeground(new Color(241, 171, 165));
		}
		else {
			btn.setForeground(new Color(255, 255, 255));
		}
		btn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		btn.setRolloverEnabled(false);
		btn.setBounds(0, y, 160, 40);
		btn.setBorder(null);
		btn.setOpaque(false);
		btn.setBackground(new Color(216, 237, 214, 100));
		btn.setFocusPainted(false);
		btn.addActionListener(this);
		taskbar.add(btn);

		return btn;
	}
	/**
	 * This method adds JButtons to a panel creating a drop down menu. 
	 */
	
	public void levelMenu() {
		one = new JButton("1000 Level");
		two = new JButton("2000 Level");
		three = new JButton("3000 Level");
		JButton[] arr = {one, two, three};
		buttons = new JPanel();
		buttons.setBorder(null);
		buttons.setBounds(0, 0, 0, 0);
		buttons.setBackground(new Color(216, 237, 214));
		buttons.setLayout(null);


		int y = 0;
		for(int i = 0; i < 3; i++) {
			arr[i].setBackground(new Color(216, 237, 214));
			arr[i].setForeground(new Color(255, 255, 255));
			arr[i].setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
			arr[i].setBorder(null);
			arr[i].setBorderPainted(false);
			arr[i].addActionListener(this);
			arr[i].setFocusPainted(false);
			buttons.add(arr[i]);
			arr[i].setBounds(0, y, 160, 40);
			y = y + 40;
		}

		taskbar.add(buttons);


	}
	/**
	 * This method creates the side panel containing the taskbar buttons and application name.
	 * @return JPanel component for the taskbar 
	 */
	public JPanel createTaskbarPanel() {
		taskbar = new JPanel();
		taskbar.setBackground(new Color(216, 237, 214));
		taskbar.setBounds(0, 0, 160, 561);
		taskbar.setLayout(null);

		JTextPane studylink = new JTextPane();
		studylink.setMargin(new Insets(5, 10, 5, 10));
		studylink.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
		StyledDocument style = studylink.getStyledDocument();
		javax.swing.text.Style study = studylink.addStyle("style", null);
		StyleConstants.setForeground(study, new Color(115, 165, 128));
		try {
			style.insertString(style.getLength(), "Study", study); }
		catch(BadLocationException e) {}
		StyleConstants.setForeground(study, new Color(239, 127, 127));
		try {
			style.insertString(style.getLength(), "Link", study); }
		catch(BadLocationException e) {}


		studylink.setBounds(32, 11, 101, 32);
		studylink.getHighlighter().removeAllHighlights();
		studylink.setEditable(false);
		studylink.setBackground(new Color(216, 237, 214, 100));
		studylink.setOpaque(false);
		studylink.setBorder(null);
		taskbar.add(studylink);

		dashboardBtn = createButton(dashboardBtn, "Dashboard", 203);
		courseBtn = createButton(courseBtn, "Course Levels", 243);
		uploadBtn = createButton(uploadBtn, "Upload", 283);
		levelMenu();

		return taskbar;

	}
	/**
	 * This method creates a rounded border and sets the layout for the panel display. 
	 * @param panel parent JPanel of the cardlayout 
	 * @return Returns the parent panel with rounded boarder. 
	 */
	 public JPanel panelBorder(JPanel panel) {
	        panel = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                Dimension arcs = new Dimension(50, 50);
	                int width = getWidth();
	                int height = getHeight();
	                Graphics2D graphics = (Graphics2D) g;
	                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                graphics.setColor(getBackground());
	                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
	                graphics.setColor(getForeground());
	                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
	            }
	        };

	        panel.setForeground(new Color(239, 239, 239, 75));
	        panel.setBackground(new Color(239, 239, 239, 75));
	        panel.setBounds(169, 203, 537, 348);
	        panel.setBorder(null);
	        panel.setOpaque(false);
	        panel.setLayout(null);


	        return panel;
	    }


	 /**
	  * Invoked on button click and displays a card. 
	  */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton[] menu = {dashboardBtn, courseBtn, uploadBtn, one, two, three};

		for (ViewButtons vb: viewButtonsList) {
			if(e.getSource() == vb.getViewButton()) {
				System.out.println(vb.getCourse().getCourseCode());
			}
		}

		for(JButton btn: menu) {
			if(e.getSource() == btn) {
				((JComponent) e.getSource()).setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(239, 127, 127)));
				btn.setForeground(new Color(241, 171, 165));
				if(btn == dashboardBtn) {
					cardLayout.show(displayArea, dashboardPanel);
					uploadBtn.setBounds(0, 283, 160, 40);
					buttons.setBounds(0, 0, 0 ,0);

				}
				else if(btn == courseBtn) {
					uploadBtn.setBounds(0, 403, 160, 40);
					buttons.setBounds(0, 283, 160, 120);

				}
				else if(btn == one) {
					cardLayout.show(displayArea, onePanel);
					uploadBtn.setBounds(0, 403, 160, 40);
					buttons.setBounds(0, 283, 160, 120);

				}
				else if(btn == two) {
					cardLayout.show(displayArea, twoPanel);
					uploadBtn.setBounds(0, 403, 160, 40);
					buttons.setBounds(0, 283, 160, 120);
				}
				else if(btn == three) {
					cardLayout.show(displayArea, threePanel);
					uploadBtn.setBounds(0, 403, 160, 40);
					buttons.setBounds(0, 283, 160, 120);
				}
				else {
					uploadBtn.setBounds(0, 283, 160, 40);
					buttons.setBounds(0, 0, 0 ,0);
				}
			}
			else {
				btn.setBorder(null);
				btn.setForeground(new Color(255, 255, 255));
			}
		}
		
	}
	
	
}
