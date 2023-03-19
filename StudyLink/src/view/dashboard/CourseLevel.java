package view.dashboard;

import model.Course.Course;
import model.Course.CourseDAOImplementation;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Class for creating the course page by level and displays the courses under each one.
 *
 */
public class CourseLevel extends JPanel implements ActionListener{

	JPanel twoLevel;
	JPanel threeLevel;
	JPanel oneLevel;
	private JPanel courseContentPanel;
	private JButton bookmark;
	private JButton view;
	List<ViewButtons> viewButtonList;
	private List<JButton> bookmarkButtonList;
	List<Course> courseList;
	private JPanel scrollPanel;
	private JLabel pageTitle;
	private List<Course> course;

	/*
	 * Constructor
	 */
	public CourseLevel() throws SQLException {
		viewButtonList = new ArrayList<>();
		CourseDAOImplementation courseDAO = new CourseDAOImplementation();
		courseList = courseDAO.getAllCourses();
		setCourseButtons();

		oneLevel = new JPanel();
		oneLevel = createCourseLevels(oneLevel, "1000 Level Courses");

		twoLevel = new JPanel();
		twoLevel = createCourseLevels(twoLevel, "2000 Level Courses");

		threeLevel = new JPanel();
		threeLevel = createCourseLevels(threeLevel, "3000 Level Courses");

	}

	/**
	 * This method creates and sets layout the JPanel component for each course level.
	 * @param level JPanel component for a course level.
	 * @param title Page title for the course page.
	 * @return Returns the panel for a course level page.
	 */
	public JPanel createCourseLevels(JPanel level, String title) throws SQLException {

		level = panelBorder(level);

		pageTitle = new JLabel();
		pageTitle.setForeground(new Color(241, 171, 165));
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pageTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 15));
		pageTitle.setText(title);
		pageTitle.setBounds(10, 11, 142, 29);
		level.add(pageTitle);

		createContainer(level);

		addScrollPane();

		addCourses();

		return level;
	}
	public void createContainer(JPanel level) {
		scrollPanel = new JPanel();
		scrollPanel.setBackground(new Color(255, 255, 255));
		scrollPanel.setBounds(10, 48, 517, 289);
		level.add(scrollPanel);

		courseContentPanel = new JPanel();
		courseContentPanel.setBackground(new Color(255, 255, 255));
		courseContentPanel.setBounds(10, 48, 517, 289);
	}
	public void addScrollPane() {
		JScrollPane scrollpane = new JScrollPane(courseContentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setPreferredSize(new Dimension(517, 289));
		scrollpane.getVerticalScrollBar().setUnitIncrement(5);
		scrollpane.getVerticalScrollBar().setUI(new ScrollBarCustom());
		scrollpane.getVerticalScrollBar().setBackground(new Color(255, 255, 255));
		scrollpane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 8));
		scrollpane.setBorder(null);
		scrollPanel.add(scrollpane);
	}

	public List<Course> courseInList() {
		course = new ArrayList<>();

		if(pageTitle.getText().equals("1000 Level Courses")) {
			for (Course e : courseList) {
				if (e.getCourseId() < 2000) {
					course.add(e);
				}
			}
		}
		else if(pageTitle.getText().equals("2000 Level Courses")) {
			for (Course e : courseList) {
				if (e.getCourseId() >= 2000 && e.getCourseId() < 3000) {
					course.add(e);
				}
			}
		}
		else if (pageTitle.getText().equals("3000 Level Courses")) {
			for(Course e : courseList) {
				if (e.getCourseId() >= 3000) {
					course.add(e);
				}
			}
		}
		return course;
	}

	public void addCourses() {
		courseContentPanel.setLayout(new BoxLayout(courseContentPanel, BoxLayout.Y_AXIS));

		courseInList();
		setCourseButtons();

		JPanel labelPanel = new JPanel();
		coursePanel(labelPanel);
		labelPanel = createLabels(labelPanel);
		labelPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		courseContentPanel.add(labelPanel);

		for(Course e : course) {
			JPanel boxPanel = new JPanel();
			coursePanel(boxPanel);
			ViewButtons viewButton = new ViewButtons(e.getViewButton(), e);
			createCourse(boxPanel, e.getCourseName(), e.getCourseCode(), e);
			viewButtonList.add(viewButton);
			courseContentPanel.add(boxPanel);

		}
	}

	public JPanel createLabels(JPanel labelPanel) {
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		String[] labelNames = {"Course Code", "Course Name", "Bookmark", "View Course"};
		labelPanel.add(Box.createHorizontalGlue());
		for(int i = 0; i < 4; i++) {
			JLabel label = new JLabel(labelNames[i]);
			labelLayout(label);
			if(i == 1) {
				label.setPreferredSize(new Dimension(197, 40));
				label.setMaximumSize(new Dimension(197, 40));
			}
			else {
				label.setPreferredSize(new Dimension(100, 40));
				label.setMaximumSize(new Dimension(100, 40));
			}
			labelPanel.add(label);

		}

		return labelPanel;
	}
	public void labelLayout(JLabel label) {
		label.setLayout(null);
		label.setForeground(new Color(150, 150, 150));
		label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void coursePanel(JPanel container) {
		container.setBackground(new Color(255, 255, 255));
		container.setBorder(new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192)) );
		container.setPreferredSize(new Dimension(495, 40));
		container.setMaximumSize(new Dimension(495, 40));
	}

	public void setCourseButtons () {
		bookmarkButtonList = new ArrayList<>();
		for (Course course: courseList) {

			String text = "View";
			view = new JButton();
			view = buttonStyler(view, text);
			course.setViewButton(view);

			String state = "Bookmark";
			bookmark = new JButton();
			bookmark = buttonStyler(bookmark, state);
			bookmark.addActionListener(this);
			bookmarkButtonList.add(bookmark);
			course.setBookmarkButton(bookmark);
		}
	}

	public void createCourse(JPanel boxPanel, String courseName, String courseCode, Course e) {
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));

		JLabel name = new JLabel(courseName);
		labelLayout(name);
		name.setMaximumSize(new Dimension(205, 40));
		name.setPreferredSize(new Dimension(205, 40));


		JLabel code = new JLabel(courseCode);
		code.setMaximumSize(new Dimension(100, 40));
		code.setPreferredSize(new Dimension(100, 40));
		labelLayout(code);
		JLabel space = new JLabel();
		space.setMaximumSize(new Dimension(13, 40));
		space.setPreferredSize(new Dimension(13, 40));

		JLabel spacer = new JLabel();
		spacer.setMaximumSize(new Dimension(17, 40));
		spacer.setPreferredSize(new Dimension(17, 40));

		boxPanel.add(Box.createHorizontalGlue());

		boxPanel.add(code);
		boxPanel.add(name);
		boxPanel.add(e.getBookmarkButton());
		boxPanel.add(spacer);
		boxPanel.add(e.getViewButton());
		boxPanel.add(space);


	}

	public JButton buttonStyler(JButton button, String text) {
		button.setText(text);
		button.setFont(new Font("Dubai", Font.BOLD, 13));
		button.setOpaque(true);
		button.setBackground(new Color(216, 237, 214));
		button.setForeground(new Color(255, 255, 255));
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(80, 25));
		button.setPreferredSize(new Dimension(80, 40));
		button.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		button.addActionListener(this);
		return button;

	}

	public JPanel panelBorder(JPanel level) {
		level = new JPanel() {
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

		level.setForeground(new Color(207, 234, 204));
		level.setOpaque(false);
		level.setBackground(new Color(207, 234, 204));
		level.setBounds(169, 203, 537, 348);
		level.setLayout(null);

		return level;
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getSource() == bookmark) {
			if(bookmark.getText() == "Bookmark")
			{
				bookmark.setBackground(new Color(239, 163, 156));
				bookmark.setText("Remove");
			}
			else {
				bookmark.setText("Bookmark");
				bookmark.setBackground(new Color(216, 237, 214));
			}
		}

	}


}