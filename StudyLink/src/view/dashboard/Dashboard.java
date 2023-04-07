package view.dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class Dashboard extends JFrame {

	private JFrame frame;
	private DateTimeFormatter timeFormat;
	private DateTimeFormatter dateFormat;
	private JLabel timeLabel;
	private JLabel dateLabel;
	private JLabel nameOfPage;

	public Dashboard() throws SQLException {
		frame = new JFrame("StudyLink");
		frame.setBackground(new Color(255, 255, 255));
		frame.setPreferredSize(new Dimension(960, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(new MigLayout("fill, insets 0", "[20%!]10[12%!][13%!]5[15%!]5[15%!]10[20%!]5", "[7%!][18%!][12%!]10[7%!]10[40%!][9%!]"));


		CardLayoutDisplay dashboard = new CardLayoutDisplay();
		dashboard.setBackground(new Color(151, 185, 189));
		MenuPane menu = new MenuPane();
		menu.setController(dashboard);

		JLabel title = pageTitle();
		Profile profile = new Profile();
		RoundedPanel header = new RoundedPanel(30, 30);
		addGreeting(header);

		setTime();

		Logout logout = new Logout();
		SearchBar search = new SearchBar();
		CalendarCustom calendar = new CalendarCustom();

		frame.getContentPane().add(menu, "cell 0 0 1 6, grow");
		frame.getContentPane().add(title, "cell 1 0 4 1, grow");
		frame.getContentPane().add(logout, "cell 5 0 1 1, grow");
		frame.getContentPane().add(profile, "cell 5 1 1 2, grow");
		frame.getContentPane().add(search, "cell 5 3 1 1, grow");
		frame.getContentPane().add(calendar, "cell 5 4 1 1, grow");
		frame.getContentPane().add(dashboard, "cell 1 3 4 3, grow");
		frame.getContentPane().add(header, "cell 1 1 4 1, grow");

		frame.getContentPane().add(timeLabel, "cell 1 2 1 1, grow");
		frame.getContentPane().add(dateLabel, "cell 2 2 1 1, grow");

		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

	public JLabel pageTitle() {
		nameOfPage = new JLabel("Dashboard");
		nameOfPage.setForeground(new Color(53, 79, 82));
		nameOfPage.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		return nameOfPage;
	}

	public void addGreeting(RoundedPanel header) {
		header.setBackground(new Color(74, 113, 117));
		header.setLayout(new BorderLayout());
		JLabel greeting = new JLabel();
		int hour = LocalDateTime.now().getHour();
		String text = "";
		if(hour < 12) {
			text = "Good Morning";
		}
		else if(hour < 17) {
			text = "Good Afternoon";
		}
		else {
			text = "Good Evening";
		}
		greeting.setBorder(new EmptyBorder(0, 5, 0, 0));
		greeting.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 17));
		greeting.setForeground(new Color(255, 255, 255));
		greeting.setText("<html>" + text + "<br/> Welcome to StudyLink! <html>");

		header.add(greeting, BorderLayout.WEST);
	}

	public void setTime() {
		timeFormat = DateTimeFormatter.ofPattern("HH:mm");

		dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy");

		timeLabel = new JLabel();
		labelLayout(timeLabel, 45);

		dateLabel = new JLabel();
		labelLayout(dateLabel, 20);

		clockStart();

	}

	public void clockStart() {
		Thread clock = new Thread() {

			@Override
			public void run() {
				while(true) {
					try {
						String time = LocalDateTime.now().format(timeFormat);
						timeLabel.setText(time);

						String day =String.valueOf(LocalDateTime.now().getDayOfWeek());

						String date = LocalDate.now().format(dateFormat);
						dateLabel.setText("<html>" + day + "<br/>" + date + "<html>");

						repaint();
						sleep(1000);

					} catch(InterruptedException e) {

					}

				}
			}
		};
		clock.start();
	}

	public void labelLayout(JLabel label, int size) {
		label.setFont(new Font("Arial Narrow", Font.PLAIN, size));
		label.setForeground(new Color(135, 146, 198, 150));

	}

	class Logout extends JButton implements ActionListener {

		public Logout() {
			setContentAreaFilled(false);
			setBorder(null);
			setHorizontalAlignment(SwingConstants.RIGHT);
			addActionListener(this);
			textLayout();
			setImage();
		}

		public void textLayout() {
			setText("Logout");
			setHorizontalTextPosition(SwingConstants.LEFT);
			setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
			setForeground(new Color(82, 121, 111));

		}

		public void setImage() {
			String path = "/logout.png";
			ImageIcon icon = new ImageIcon(getClass().getResource(path));
			Image newIcon = icon.getImage();
			newIcon = newIcon.getScaledInstance(30, 25, Image.SCALE_SMOOTH);
			icon = new ImageIcon(newIcon);
			setIcon(icon);
			setIconTextGap(10);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this) {
				frame.dispose();
			}

		}




	}


}
