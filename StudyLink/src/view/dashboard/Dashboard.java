package view.dashboard;

import java.awt.*;

import java.sql.SQLException;
import java.time.*;
import javax.swing.*;


public class Dashboard {


	private JFrame frame;
	private JPanel profile;

	static SearchBar searchbar;
	private CardLayoutDisplay cardLayout;
	

	public Dashboard() throws SQLException {
		frame = new JFrame();
		frame = createFrame();
		frame.setLocationRelativeTo(null);

		createProfilePanel();
		createSearchbar();
		createTaskbar();
		pageTitle("Dashboard");
		createHeader();


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

	public JPanel createProfilePanel() {
		profile = new JPanel();
		profile.setBackground(new Color(216, 237, 214));
		profile.setBounds(716, 0, 228, 561);
		profile.setLayout(null);

		frame.getContentPane().add(profile);

		Profile user = new Profile();
		profile.add(user.profile);

		JPanel calendar = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Dimension arcs = new Dimension(50, 50);
				int width = getWidth();
				int height = getHeight();
				Graphics2D graphics = (Graphics2D) g;
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.setColor(new Color(255, 255, 255));
				graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
				graphics.setColor(new Color(255, 255, 255));
				graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
			}
		};

		calendar.setBounds(10, 294, 210, 247);

		calendar.setLayout(null);
		calendar.setOpaque(false);
		calendar.setBorder(null);

		calendar = createCalendar(calendar);
		profile.add(calendar);


		return profile;
	}


	public JPanel createCalendar(JPanel calendar) {
		LocalDate dates = LocalDate.now();
		JLabel month = new JLabel();
		month = getMonth(calendar, month, dates);

		JTextPane[] weeks = new JTextPane[7];
		weeks = getWeek(weeks, calendar);


		int numOfDays = dates.lengthOfMonth();
		JTextPane[] days = new JTextPane[numOfDays + 1];
		days = getDates(numOfDays, days, dates, calendar);

		return calendar;

	}

	public JLabel getMonth(JPanel calendar, JLabel month, LocalDate dates) {
		String monthName = dates.getMonth().toString();
		String[] months = {"January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
		for(int i = 0; i < months.length; i++) {
			if(monthName.equalsIgnoreCase(months[i])) {
				month.setText(months[i]);
			}
		}

		month.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 17));
		month.setForeground(new Color(239, 127, 127));
		month.setBackground(new Color(187, 223, 183, 25));
		month.setBorder(null);
		month.setBounds(10, 10, 134, 32);

		calendar.add(month);
		return month;
	}

	public JTextPane[] getWeek(JTextPane[] weeks, JPanel calendar) {
		String[] daysOfWeek = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};

		int x = 10;
		for(int i = 0; i < 7; i++) {
			weeks[i] = new JTextPane();
			weeks[i].setText(daysOfWeek[i]);
			weeks[i].setBounds(x, 45, 30, 30);
			weeks[i] = styler(weeks[i]);
			calendar.add(weeks[i]);
			x+= 30;

		}

		return weeks;

	}
	public JTextPane[] getDates(int num, JTextPane[] days, LocalDate dates, JPanel calendar) {

		DayOfWeek firstOfMonth = dates.withDayOfMonth(1).getDayOfWeek();

		String[] dayOfWeek = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
		int start = 0;
		for(int i = 0; i < 7; i++) {
			if(dayOfWeek[i] == firstOfMonth.toString()) {
				start = i;
			}
		}

		int y = 75;
		start = start * 30 + 10;
		int x = start;

		for(int i = 1; i <= num; i++) {
			days[i] = new JTextPane();
			days[i].setText(String.valueOf(i));
			if(x < 210) {
				days[i].setBounds(x, y, 30, 30);
				x = x + 30;
			}
			else {
				x = 10;
				y = y + 30;
				days[i].setBounds(x, y, 30, 30);
				x = x + 30;
			}
			days[i] = styler(days[i]);
			calendar.add(days[i]);

			calendar.add(days[i]);
		}
		return days;

	}

	public JTextPane styler(JTextPane box) {
		box.setMargin(new Insets(8, 8, 8, 8));
		box.setFont(new Font("Tahoma", Font.PLAIN, 11));
		box.setBackground(new Color(187, 223, 183, 25));
		box.setForeground(new Color(239, 127, 127));
		box.setBorder(null);
		box.getHighlighter().removeAllHighlights();
		box.setEditable(false);
		box.setOpaque(false);
		return box;
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
}
