//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard implements ActionListener{

	private JFrame frame;
	
	private JPanel name_bg;
    private JPanel taskbar;

	private JButton addCourse;
	private JButton courseList;
	private JButton profile;

	private JButton logoutbutton;
	private JButton accountbutton;
	private JButton settingsbutton;

	private JButton one;
	private JButton two;
	private JButton three;

	private JTextField searchbar;
	
	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(255, 255, 255));
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		
		name_bg = new JPanel();
		name_bg.setBounds(0, 0, 834, 175);
		name_bg.setBackground(new Color(117, 153, 193));
		frame.getContentPane().add(name_bg);
		name_bg.setLayout(null);
		
		taskbar = new JPanel();
		taskbar.setBounds(0, 0, 1921, 55);
		taskbar.setBackground(new Color(70, 99, 172));
		name_bg.add(taskbar);
		taskbar.setLayout(null);
		
		addCourse = new JButton("+");
		addCourse.setForeground(new Color(198, 207, 232));
		addCourse.setBackground(new Color(70, 99, 172));
		addCourse.setFocusPainted(false);
		addCourse.setBorderPainted(false);
		addCourse.setFont(new Font("Tahoma", Font.BOLD, 32));
		addCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addCourse.setBounds(0, 0, 78, 55);
		taskbar.add(addCourse);
		
		profile = new JButton("|     Profile   ");
		profile.setForeground(new Color(198, 207, 232));
		profile.setFont(new Font("Tahoma", Font.PLAIN, 26));
		profile.setBackground(new Color(70, 99, 172));
		profile.setFocusPainted(false);
		profile.setBorderPainted(false);
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		profile.setBounds(614, 3, 220, 55);
		taskbar.add(profile);
		
		searchbar = new JTextField();
		searchbar.setBorder(new LineBorder(new Color(70, 99, 172), 9));
		searchbar.setBackground(new Color(198, 207, 232, 100));
		searchbar.setSelectedTextColor(new Color(198, 207, 232));
		searchbar.setBounds(324, 0, 295, 55);
		taskbar.add(searchbar);
		searchbar.setColumns(10);
		
		final JPanel courseMenu = new JPanel();
		courseMenu.setAlignmentX(0.0f);
		courseMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		courseMenu.setDoubleBuffered(false);
		courseMenu.setBackground(new Color(70, 99, 172, 150));
		courseMenu.setBounds(110, 0, 188, 55);
		name_bg.add(courseMenu);
		courseMenu.setLayout(null);
		
		one = new JButton("1000 Level");
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		one.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		one.setFont(new Font("Tahoma", Font.BOLD, 16));
		one.setFocusPainted(false);
		one.setBorderPainted(false);
		one.setForeground(new Color(198, 207, 232, 150));
		one.setBackground(new Color(70, 99, 172, 150));
		one.setBounds(0, 55, 188, 40);
		courseMenu.add(one);
		
		courseList = new JButton("|   Course List   |");
		
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
		
		courseList.setContentAreaFilled(false);
		
		courseList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		courseList.setForeground(new Color(198, 207, 232));
		courseList.setFont(new Font("Tahoma", Font.PLAIN, 26));
		courseList.setFocusPainted(false);
		courseList.setBorderPainted(false);
		courseList.setBackground(new Color(70, 99, 172, 150));
		courseList.setBounds(88, 0, 234, 55);
		taskbar.add(courseList);
		
		two = new JButton("3000 Level");
		two.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
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
		
		two.setFont(new Font("Tahoma", Font.BOLD, 16));
		two.setFocusPainted(false);
		two.setForeground(new Color(198, 207, 232, 150));
		two.setBackground(new Color(70, 99, 172, 150));
		two.setBorderPainted(false);
		two.setBounds(0, 134, 188, 40);
		courseMenu.add(two);
		
		three = new JButton("2000 Level");
		three.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
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
		
		three.setBackground(new Color(70, 99, 172, 150));
		three.setForeground(new Color(198, 207, 232, 150));
		three.setBorderPainted(false);
		three.setFocusPainted(false);
		three.setFont(new Font("Tahoma", Font.BOLD, 16));
		three.setBounds(0, 94, 188, 40);
		courseMenu.add(three);
		
		final JPanel profileMenu = new JPanel();
		
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
		
		profileMenu.setLayout(null);
		profileMenu.setDoubleBuffered(false);
		profileMenu.setBackground(new Color(70, 99, 172));
		profileMenu.setBounds(646, 0, 188, 55);
		name_bg.add(profileMenu);
		
		accountbutton = new JButton("Account");
		
		accountbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				profileMenu.setSize(188, 175);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				profileMenu.setSize(0, 0);
			}
		});
		
		accountbutton.setForeground(new Color(198, 207, 232, 150));
		accountbutton.setFont(new Font("Tahoma", Font.BOLD, 16));
		accountbutton.setFocusPainted(false);
		accountbutton.setBorderPainted(false);
		accountbutton.setBackground(new Color(70, 99, 172, 150));
		accountbutton.setBounds(0, 54, 188, 40);
		accountbutton.addActionListener(this);
		profileMenu.add(accountbutton);
		
		logoutbutton = new JButton("Log out");
		
		logoutbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				profileMenu.setSize(188, 175);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				profileMenu.setSize(0, 0);
			}
		});
		
		logoutbutton.setForeground(new Color(198, 207, 232, 150));
		logoutbutton.setFont(new Font("Tahoma", Font.BOLD, 16));
		logoutbutton.setFocusPainted(false);
		logoutbutton.setBorderPainted(false);
		logoutbutton.setBackground(new Color(70, 99, 172, 150));
		logoutbutton.setBounds(0, 134, 188, 40);
		logoutbutton.addActionListener(this);
		profileMenu.add(logoutbutton);
		
		settingsbutton = new JButton("Settings");
		
		settingsbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				profileMenu.setSize(188, 175);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				profileMenu.setSize(0, 0);
			}
		});
		
		settingsbutton.setForeground(new Color(198, 207, 232, 150));
		settingsbutton.setFont(new Font("Tahoma", Font.BOLD, 16));
		settingsbutton.setFocusPainted(false);
		settingsbutton.setBorderPainted(false);
		settingsbutton.setBackground(new Color(70, 99, 172, 150));
		settingsbutton.setBounds(0, 94, 188, 40);
		profileMenu.add(settingsbutton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==logoutbutton) {
			
			new LoginPage(); // takes the user back to the login page
			frame.setVisible(false); //exits the dashboard upon clicking the logout button
			
		}

	}
}
