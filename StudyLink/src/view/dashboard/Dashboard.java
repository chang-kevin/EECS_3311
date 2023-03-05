package view.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import view.*;

public class Dashboard implements ActionListener, DashboardGUI {
	JFrame frame;
	
	private JPanel name_bg;
    private JPanel taskbar;
	private JPanel courseMenu;
	private JPanel profileMenu; 

	private JButton addCourse;
	private JButton courseList;
	private JButton profile;
	private JButton logoutButton;
	private JButton accountButton;
	private JButton settingsButton;
	private JButton one;
	private JButton two;
	private JButton three;

	private JTextField searchbar;
	
	/**
	 * Create the application.
	 */
	public Dashboard() {
		name_bg = createPanel();
		frame = createFrame("Dashboard");
		frame.getContentPane().add(name_bg);
		frame.setLocationRelativeTo(null);

		this.generateTaskbar();
		this.generateAddButton();
		this.generateProfile();
		this.generateSearchBar();
		this.generateCourseMenu();
		this.generateCourseList();
		this.generateCourseMenuItems();
		this.generateProfileMenu();
		this.generateAccountButton();
		this.generateLogoutButton();
		this.generateSettingsButton();
	}

	public void generateCourseList() {
		courseList = createTaskbarButton("|   Course List   |");
		courseList.setBounds(88, 0, 234, 55);
		courseList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				courseMenu.setSize(188, 234);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				courseMenu.setSize(0, 0);
			}
		});
		taskbar.add(courseList);
	}

	public void generateAddButton() {
		addCourse = createTaskbarButton("+");;
		addCourse.setBounds(0, 0, 78, 55);
		addCourse.addActionListener(this);
		taskbar.add(addCourse);
	}
	public void generateTaskbar() {
		taskbar = createPanel();
		taskbar.setBounds(0, 0, 1921, 55);
		taskbar.setBackground(new Color(70, 99, 172));
		name_bg.add(taskbar);
	}

	public void generateProfile() {
		profile = createTaskbarButton("|     Profile   ");
		profile.setBounds(614, 3, 220, 55);
		profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				profileMenu.setSize(188, 175);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				profileMenu.setSize(0, 0);
			}
		});
		taskbar.add(profile);
	}

	public void generateSearchBar() {
		searchbar = new JTextField();
		searchbar.setBorder(null);
		searchbar.setBackground(new Color(198, 207, 232, 100));
		searchbar.setSelectedTextColor(new Color(198, 207, 232));
		searchbar.setBounds(329, 11, 285, 33);
		taskbar.add(searchbar);
		searchbar.setColumns(10);
	}

	public void generateCourseMenuItems() {
		// this needs to be refactored into a for-loop
		one = createMenuButton("1000 Level");
		one.setBounds(0, 55, 188, 40);
		one.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				courseMenu.setSize(188, 234);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				courseMenu.setSize(0, 0);
			}
		});
		courseMenu.add(one);

		two = createMenuButton("3000 Level");
		two.setBounds(0, 134, 188, 40);
		two.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				courseMenu.setSize(188, 234);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				courseMenu.setSize(0, 0);
			}
		});
		courseMenu.add(two);

		three = createMenuButton("2000 Level");
		three.setBounds(0, 94, 188, 40);
		three.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				courseMenu.setSize(188, 234);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				courseMenu.setSize(0, 0);
			}
		});
		courseMenu.add(three);
	}

	public void generateProfileMenu() {
		profileMenu = createMenuPanel();
		profileMenu.setBounds(646, 0, 188, 55);
		name_bg.add(profileMenu);
	}

	public void generateCourseMenu() {
		courseMenu = createMenuPanel();
		courseMenu.setAlignmentX(0.0f);
		courseMenu.setBounds(110, 0, 188, 55);
		name_bg.add(courseMenu);
	}

	public void generateAccountButton() {
		accountButton = createMenuButton("Account");
		accountButton.setBounds(0, 55, 188, 40);
		accountButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				profileMenu.setSize(188, 175);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				profileMenu.setSize(0, 0);
			}
		});

		accountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				new AccountManagement();
			}
		});
		profileMenu.add(accountButton);
	}

	public void generateLogoutButton() {
		logoutButton = createMenuButton("Log out");
		logoutButton.setBounds(0, 134, 188, 40);
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				profileMenu.setSize(188, 175);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				profileMenu.setSize(0, 0);
			}
		});
		profileMenu.add(logoutButton);
	}

	public void generateSettingsButton() {
		settingsButton = createMenuButton("Settings");
		settingsButton.setBounds(0, 94, 188, 40);
		settingsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				profileMenu.setSize(188, 175);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				profileMenu.setSize(0, 0);
			}
		});
		profileMenu.add(settingsButton);
	}

	/**
	 * Overrides the actionPerformed method from ActionListener interface. 
	 * @param e event to be processed. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==logoutButton) {
			new Login(); // takes the user back to the login page
			frame.dispose();
		}
		if(e.getSource() == addCourse) {
			new UploadFile();
		}

		if (e.getSource()==two) {
			new ThirdYearCourses();
			frame.dispose();
		}
	}
	/**
	 * Creates the taskbar and applies layout. 
	 * @param name The name of the taskbar button. 
	 * @return The taskbar button 
	 */
	public JButton createTaskbarButton(String name) {
		JButton taskbarButton = new JButton(name);
		taskbarButton.setText(name);
		taskbarButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		taskbarButton.setFocusPainted(false);
		taskbarButton.setBorderPainted(false);
		taskbarButton.setForeground(Color.WHITE);
		taskbarButton.setBackground(new Color(70, 99, 172, 150));
		return taskbarButton;
	}
	
	/**
	 * Creates a JFrame and applies layout. 
	 * @param name The name of the frame. 
	 * @return the stylized frame
	 */
	public JFrame createFrame(String title) {
		JFrame theFrame = new JFrame(title);
        theFrame.setBounds(100, 100, 850, 550);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setBackground(new Color(255, 255, 255));
		theFrame.setVisible(true);
		theFrame.getContentPane().setLayout(null);
		return theFrame;
	}
	
	/**
	 * Creates the drop down menu and applies layout. 
	 * @param name The name of the menu button. 
	 * @return Returns the drop down menu buttons.
	 */
	public JButton createMenuButton(String name) {
		JButton menuButton = new JButton(name);
		menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuButton.setFocusPainted(false);
		menuButton.setBorderPainted(false);
		menuButton.setForeground(new Color(198, 207, 232, 150));
		menuButton.setBackground(new Color(70, 99, 172));
		menuButton.addActionListener(this);
		return menuButton;
	}
	
	/**
	 * Creates a JPanel and applies layout. 
	 * @param name The name of the panel. 
	 * @return the stylized panel
	 */
	public JPanel createPanel() {
		JPanel thePanel = new JPanel();
		thePanel.setBounds(0, 0, 834, 175);
		thePanel.setBackground(new Color(117, 153, 193));
		thePanel.setLayout(null);
		return thePanel;
	}
	
	/**
	 * Creates a JPanel for the menu and applies layout. 
	 * @param name The name of the panel. 
	 * @return the stylized panel
	 */
	public JPanel createMenuPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setDoubleBuffered(false);
		menuPanel.setBackground(new Color(70, 99, 172));
		return menuPanel;
	}
}