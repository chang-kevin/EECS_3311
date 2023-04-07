package view.dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import model.Course.Course;
import model.Course.CourseDAOImplementation;

public class CourseLevel implements ActionListener {

	private JPanel contentPanel;
	private String title;
	private GridBagConstraints c;
	private List<Course> course;
	List<Course> courseList;
	private JButton view;
	private JButton bookmark;
	List<BookmarkButtons> bookmarkList;
	List<ViewButtons> viewButtonList;
	RoundedPanel one;
	RoundedPanel two;
	RoundedPanel three;

	public CourseLevel() throws SQLException {

		bookmarkList = new ArrayList<>();
		viewButtonList = new ArrayList<>();

		CourseDAOImplementation courseDAO = new CourseDAOImplementation();
		courseList = courseDAO.getAllCourses();
		one = new RoundedPanel(30, 30);
		one = panelLayout(one, "1000 Level Courses");


		two = new RoundedPanel(30, 30);
		two = panelLayout(two, "2000 Level Courses");


		three = new RoundedPanel(30, 30);
		three = panelLayout(three, "3000 Level Courses");

	}

	public RoundedPanel panelLayout(RoundedPanel level, String title) throws SQLException {
		this.title = title;
		level.setBackground(new Color(129, 169, 173));
		setConstraints(level, title);
		addColumnLabels();
		addCourses();
		return level;
	}

	public JLabel createTitle(String text) {
		JLabel title = new JLabel(text);
		title.setBorder(new EmptyBorder(15, 15, 10, 15));
		title.setForeground(new Color(53, 79, 82));
		title.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		title.setHorizontalTextPosition(SwingConstants.LEFT);
		return title;
	}

	public void createCoursePane() {
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(217, 230, 226));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
	}

	public JScrollPane addScrollPane() {
		JScrollPane scrollpane = new JScrollPane(contentPanel);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.getVerticalScrollBar().setUnitIncrement(5);
		scrollpane.getVerticalScrollBar().setUI(new ScrollBarCustom());
		scrollpane.getVerticalScrollBar().setBackground(new Color(232, 240, 238));
		scrollpane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 8));
		scrollpane.setBorder(null);
		return scrollpane;
	}

	public void setConstraints(RoundedPanel level, String title) {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{0, 0};
		layout.rowHeights = new int[]{0, 0, 0};
		layout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		level.setLayout(layout);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		level.add(createTitle(title), gbc);
		createCoursePane();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		level.add(addScrollPane(), gbc);

	}

	public void addColumnLabels() {
		JPanel labelPanel = new JPanel();
		labelPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		labelPanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192)) );
		labelPanel.setBackground(new Color(255, 255, 255));
		createLabels(labelPanel);
		contentPanel.add(labelPanel);
	}

	public void createLabels(JPanel labelPanel) {
		labelPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		JLabel code = new JLabel("Course Code");
		c.gridwidth = 1;
		addGBC(labelPanel, code, 0, 0);

		JLabel name = new JLabel("Course Name");
		name.setPreferredSize(new Dimension(150, 40));
		name.setPreferredSize(new Dimension(150, 40));
		c.gridwidth = 3;
		addGBC(labelPanel, name, 1, 0);

		JLabel bookmark = new JLabel("Bookmark");

		c.gridwidth = 1;
		addGBC(labelPanel, bookmark, 4, 0);


		JLabel view = new JLabel("View Course");
		addGBC(labelPanel, view, 5, 0);

	}
	public void addGBC(JPanel labelPanel, JLabel component, int x, int y) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.5;
		c.gridx = x;
		c.gridy = y;
		labelLayout(component);
		labelPanel.add(component, c);
	}

	public void courseLayout(JPanel coursePanel) {
		coursePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		coursePanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192)) );
		coursePanel.setBackground(new Color(255, 255, 255));
	}

	public void addCourses() throws SQLException {
		courseInList();
		setCourseButtons();

		int y = 1;
		for(Course course : course) {
			ViewButtons viewButton = new ViewButtons(course.getViewButton(), course);
			BookmarkButtons bookmarkButtons = new BookmarkButtons(course.getBookmarkButton(), course);
			createCourse(course.getCourseName(), course.getCourseCode(), course,y);
			bookmarkList.add(bookmarkButtons);
			viewButtonList.add(viewButton);
			y++;
		}
		setBookmarkListener();
	}

	public void createCourse(String courseName, String courseCode, Course e,int y) {
		JPanel coursePanel = new JPanel();
		courseLayout(coursePanel);
		coursePanel.setLayout(new GridBagLayout());

		JLabel code = new JLabel(courseCode);
		labelLayout(code);
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 5);
		addGBCToCourse(coursePanel, code, 0, y);

		JLabel name = new JLabel(courseName);
		labelLayout(name);
		name.setPreferredSize(new Dimension(100, 40));
		c.gridwidth = 2;
		addGBCToCourse(coursePanel, name, 1, y);

		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		addGBCToCourse(coursePanel, e.getBookmarkButton(), 3, y);

		addGBCToCourse(coursePanel, e.getViewButton(), 4, y);

		contentPanel.add(coursePanel);

	}

	public void addGBCToCourse(JPanel coursePanel, Component component, int x, int y) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0.5;
		c.gridx = x;
		c.gridy = y;
		coursePanel.add(component, c);
	}

	public void labelLayout(JLabel label) {
		label.setForeground(new Color(150, 150, 150));
		label.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public JButton buttonStyler(JButton button, String text) {
		button.setText(text);
		button.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		button.setBackground(new Color(74, 113, 117));
		button.setForeground(new Color(255, 255, 255));
		button.setPreferredSize(new Dimension(50, 25));
		button.setFocusPainted(false);
		button.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		//button.addActionListener(this);
		return button;

	}

	public void setCourseButtons () {
		for (Course course : courseList) {

			String text = "View";
			view = new JButton();
			view = buttonStyler(view, text);
			course.setViewButton(view);

			String state = "Bookmark";
			bookmark = new JButton();
			bookmark = buttonStyler(bookmark, state);
			bookmark.addActionListener(this);
			course.setBookmarkButton(bookmark);
		}
	}

	public List<Course> courseInList() {
		course = new ArrayList<>();

		if(title.equals("1000 Level Courses")) {
			for (Course e : courseList) {
				if (e.getCourseId() < 2000) {
					course.add(e);
				}
			}
		}
		else if(title.equals("2000 Level Courses")) {
			for (Course e : courseList) {
				if (e.getCourseId() >= 2000 && e.getCourseId() < 3000) {
					course.add(e);
				}
			}
		}
		else if (title.equals("3000 Level Courses")) {
			for(Course e : courseList) {
				if (e.getCourseId() >= 3000) {
					course.add(e);
				}
			}
		}
		return course;
	}

	public void setBookmarkListener() {
		for(BookmarkButtons btn : bookmarkList) {
			btn.getBookmarkButton().addActionListener(this);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Bookmark b = new Bookmark();
			for(BookmarkButtons btn : bookmarkList) {
				if(e.getSource() == btn) {
					ViewButtons viewButton = new ViewButtons(btn.getCourse().getViewButton(), btn.getCourse());
					b.addBookmark(btn.getCourse());
					b.viewBookmark.add(viewButton);
					b.invalidate();
					b.validate();
					b.repaint();
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

	}
}


