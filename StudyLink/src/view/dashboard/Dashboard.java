package view.dashboard;

import controller.Login;
import model.User.User;
import model.User.UserSession;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.*;
import javax.swing.*;


public class Dashboard implements ActionListener{

	private JFrame frame;
	static SearchBar searchbar;
	private CardLayoutDisplay cardLayout;
	private Profile profile;


	public Dashboard() throws SQLException {
		frame = new JFrame();
		frame = createFrame();
		frame.setLocationRelativeTo(null);

		createProfile();
		createTaskbar();
		pageTitle("Dashboard");
		createHeader();
		getLogoutButton();

	}

	/**
	 * Creates a JFrame and applies layout.
	 * @return the stylized frame
	 */
	public JFrame createFrame() {
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 960, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(255, 255, 255));
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		return frame;
	}

	public void createSearchbar() {
		searchbar = new SearchBar();
		profile.add(searchbar.searchIcon);
		profile.add(searchbar.searchbar);
	}
	public void pageTitle(String title) {
		JTextPane pageTitle = new JTextPane();
		pageTitle.getHighlighter().removeAllHighlights();
		pageTitle.setEditable(false);
		pageTitle.setForeground(new Color(115, 165, 128));
		pageTitle.setBounds(169, 15, 108, 28);
		frame.getContentPane().add(pageTitle);
		pageTitle.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		pageTitle.setText(title);
		pageTitle.setBackground(new Color(255, 255, 255));

	}

	public void createTaskbar() throws SQLException {
		cardLayout = new CardLayoutDisplay();
		frame.getContentPane().add(cardLayout.taskbar);

		createDisplay(cardLayout);
	}
	public void createProfile() throws SQLException {
		profile = new Profile();
		frame.getContentPane().add(profile.profile);
	}



	public void createDisplay(CardLayoutDisplay cardLayout) {
		frame.getContentPane().add(cardLayout.displayArea);
	}

	public void createHeader() {
		JPanel header = new JPanel();
		header = panelBorder();
		header.setForeground(new Color(137, 180, 148));
		header.setBackground(new Color(137, 180, 148));
		header.setBounds(169, 54, 538, 138);
		frame.getContentPane().add(header);
		header.setLayout(null);

		String text = "";
		JLabel greet = new JLabel();
		greet = greetingText(greet, 10);



		LocalDateTime now = LocalDateTime.now();
		int hour = now.getHour();
		if(hour < 12) {
			text = "Good Morning";
		}
		else if(hour < 17) {
			text = "Good Afternoon";
		}
		else {
			text = "Good Evening";
		}

		greet.setText(text);

		JLabel welcome = new JLabel();
		welcome = greetingText(welcome, 35);
		welcome.setText("Welcome to StudyLink!");

		header.add(greet);
		header.add(welcome);

	}
	public JLabel greetingText(JLabel greeting, int y) {
		greeting.setForeground(new Color(255, 255, 255));
		greeting.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 17));
		greeting.setBounds(10, y, 250, 50);
		greeting.setBorder(null);
		return greeting;
	}

	public void getLogoutButton() {
		profile.logoutBtn.addActionListener(this);
	}

	public JPanel panelBorder() {
		JPanel panel = new JPanel() {
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
		panel.setBorder(null);
		panel.setOpaque(false);
		panel.setLayout(null);


		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == profile.logoutBtn) {
			frame.dispose();
			new Login();
			UserSession.clearCurrentUser();
		}
	}
}
