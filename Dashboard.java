package view.dashboard;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;


public class Dashboard implements ActionListener{


	private JFrame frame;
	private JButton dashboardBtn;
	private JButton courseBtn;
	private JButton uploadBtn;
	private JPanel taskbar;
	private JPanel profile;
	private JPanel buttons;
	private JButton one;
	private JButton two;
	private JButton three;
	private HomePage home;

	public Dashboard() {
		frame = new JFrame();
		frame = createFrame();
		frame.setLocationRelativeTo(null);

		createHomePage();
		createTaskbarPanel();
		createProfilePanel();


		pageTitle("Dashboard");

		createHeader();
		createSearchbar();

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
		SearchBar searchbar = new SearchBar();
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

	public void createDisplay() {




	}


	public void createHomePage() {
		home = new HomePage();
		frame.getContentPane().add(home.dashboard);
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
			buttons.add(arr[i]);
			arr[i].setBounds(0, y, 160, 40);
			y = y + 40;
		}

		taskbar.add(buttons);


	}

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

		frame.getContentPane().add(taskbar);


		return taskbar;

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



//    public void createHomePage() {
//        displayArea = new JPanel();
//        displayArea.setBackground(new Color(255, 255, 255));
//        displayArea.setBounds(169, 203, 537, 348);
//        frame.getContentPane().add(displayArea);
//        cards = new CardLayout();
//        displayArea.setLayout(cards);
//
//        CourseLevels homePage = new CourseLevels();
//        JPanel dashboard = new JPanel();
//        dashboard = homePage.dashboard;
//
//        createBookmarks(dashboard);
//
//        timeWidget = new JTextPane();
//        timeWidget = widgets(timeWidget, 20, dashboard);
//
//        widget_1 = new JTextPane();
//        widget_1 = widgets(widget_1, 293, dashboard);
//
//
//
//        cards.show(displayArea, "dashboard");
//
//    }


//    public void createBookmarks(JPanel dashboard) {
//    	 JPanel bookmark = new JPanel();
//         bookmark.setBounds(10, 90, 517, 247);
//         dashboard.add(bookmark);
//         GridBagLayout gbl_bookmark = new GridBagLayout();
//         gbl_bookmark.columnWidths = new int[]{0, 0};
//         gbl_bookmark.rowHeights = new int[]{50, 50, 0};
//         gbl_bookmark.columnWeights = new double[]{1.0, Double.MIN_VALUE};
//         gbl_bookmark.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
//         bookmark.setLayout(gbl_bookmark);
//         {
//         	JLabel myCourses = new JLabel(" My Courses");
//         	myCourses.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
//         	myCourses.setForeground(new Color(137, 180, 148));
//         	GridBagConstraints gbc_myCourses = new GridBagConstraints();
//         	gbc_myCourses.fill = GridBagConstraints.BOTH;
//         	bookmark.add(myCourses, gbc_myCourses);
//
//         	JLabel pinCourse = new JLabel();
//         	pinCourse = getBookmarks(1, bookmark, pinCourse, "<html> &nbsp;Course Name <br/>&nbsp;EECS #### </html>");
//
//         }
//    }
//
//    public JLabel getBookmarks(int y, JPanel bookmark, JLabel pinCourse, String text) {
//    	pinCourse = new JLabel(text);
//        pinCourse.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
//     	pinCourse.setForeground(new Color(137, 180, 148));
//        GridBagConstraints gbc_pinCourse = new GridBagConstraints();
//        gbc_pinCourse.fill = GridBagConstraints.BOTH;
//        gbc_pinCourse.gridy = 1;
//        bookmark.add(pinCourse, gbc_pinCourse);
//
//    	return pinCourse;
//    }

//    public JTextPane widgets(JTextPane widget, int x, JPanel dashboard) {
//        widget = new JTextPane() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                Dimension arcs = new Dimension(30, 30);
//                int width = getWidth();
//                int height = getHeight();
//                Graphics2D graphics = (Graphics2D) g;
//                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                graphics.setColor(new Color(202, 222, 237, 150));
//                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
//                graphics.setColor(new Color(202, 222, 237, 150));
//                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
//            }
//
//        };
//        widget.setEditable(false);
//        widget.setBounds(x, 10, 224, 57);
//        widget.setBorder(null);
//        widget.setOpaque(false);
//
//        if(x == 20) {
//            LocalDateTime time = LocalDateTime.now();
//            JLabel text = new JLabel();
//            if(time.getMinute() < 10) {
//                text.setText(String.valueOf(" " + time.getHour() + ":0"+ time.getMinute()));
//            }
//            else {
//                text.setText(String.valueOf(" " + time.getHour() + ":"+ time.getMinute()));
//            }
//            text.setBounds(x, 10, 135, 57);
//            text.setBorder(null);
//            text.setBackground(new Color(219,237,255));
//            text.setForeground(new Color(255, 255, 255));
//            text.setFont(new Font("Arial Narrow", Font.PLAIN, 45));
//            dashboard.add(text);
//            String week = String.valueOf(time.getDayOfWeek());
//            LocalDate day = LocalDate.now();
//            String date = String.valueOf(day);
//            JLabel dateText = new JLabel();
//            dateText.setText("<html>" + week + "<br/>" + date + "<html>");
//            dateText.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
//            dateText.setBorder(null);
//            dateText.setBackground(new Color(219,237,255));
//            dateText.setForeground(new Color(255, 255, 255));
//            dateText.setBounds(135, 10, 114, 57);
//            dashboard.add(dateText);
//        }
//
//        dashboard.add(widget);
//
//
//        return widget;
//    }

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
		JButton[] menu = {dashboardBtn, courseBtn, uploadBtn, one, two, three};

		for(JButton btn: menu) {
			if(e.getSource() == btn) {
				((JComponent) e.getSource()).setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(239, 127, 127)));
				btn.setForeground(new Color(241, 171, 165));
				if(btn == dashboardBtn) {
					uploadBtn.setBounds(0, 283, 160, 40);
					buttons.setBounds(0, 0, 0 ,0);

				}
				else if(btn == courseBtn) {
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
