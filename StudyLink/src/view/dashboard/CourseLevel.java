package view.dashboard;

import model.Course.Course;
import model.Course.CourseDAOImplementation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

//import static com.sun.tools.javac.main.Option.J;


/**
 * 
 * Class for creating the course page by level and displays the courses under each one. 
 * 
 */
public class CourseLevel extends JPanel implements ActionListener{
    
	JPanel twoLevel; 
	JPanel threeLevel;
	JPanel oneLevel;
	JFrame frame;
	private JPanel courseContentPanel;
	private JPanel labelContainer;
	private JButton bookmark;
	private JButton view;

	private List<JButton> bookmarkButtonList;

	private List<Course> courseList;

	private List<Course> courseWithButtons;

    /*
     * Constructor 
     */
    public CourseLevel() throws SQLException {
		
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
		
		JLabel pageTitle = new JLabel();
		pageTitle.setForeground(new Color(241, 171, 165));
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pageTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 15));
		pageTitle.setText(title);
		pageTitle.setBounds(10, 11, 142, 29);
		level.add(pageTitle);


		courseContentPanel = new JPanel();
		courseContentPanel = coursePanel();

		labelContainer = new JPanel();

		labelContainer = createContainer(labelContainer, 0);
		courseContentPanel.add(labelContainer);
		
		contentLabels(labelContainer);
    
		generateCourses(title);

		level.add(courseContentPanel);

		return level;
    }
    /**
     * This method sets a layout for the JPanel container of each course to be displayed as a list. 
     * @param container JPanel component containing the course information. 
     * @param y-bound value for container placement. 
     * @return Returns the container panel.
     */
    public JPanel createContainer(JPanel container, int y) {	
    	container.setBackground(new Color(255, 255, 255));
    	container.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));
    	container.setBounds(10, y, 497, 40);
    	container.setLayout(null);
		return container;
    }
    /**
     * This method creates the information label for each list of courses per course page. 
     * @param labelContainer JPanel container for the label for absolute label placement.
     */
    public void contentLabels(JPanel labelContainer) {
    	JLabel courseCode = new JLabel("Course Code");
		createLabels(courseCode, 0, 100, labelContainer);	
		
		JLabel courseName = new JLabel("Course Name");
		createLabels(courseName, 100, 197, labelContainer);
		
		JLabel bookmarkLabel = new JLabel("Bookmark");
		createLabels(bookmarkLabel, 297, 100, labelContainer); 	
		
		JLabel viewCourse = new JLabel("View Course");
		createLabels(viewCourse, 397, 100, labelContainer);

    }
    
    /**
     * This method sets the layout for the labels. 
     * @param label JLabel containing the label for each course information. 
     * @param x x-bound value for placement.
     * @param width The width of the label. 
     * @param container JPanel container where the labels are to be added. 
     */
    public void createLabels(JLabel label, int x, int width, JPanel container) {
    	label.setLayout(null);
    	label.setForeground(new Color(150, 150, 150));
    	label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
    	label.setBounds(x, 0, width, 40);
    	label.setHorizontalAlignment(SwingConstants.CENTER);
    	container.add(label);
    }
    
    /**
     * This method sets the layout for the panel containing the list of the courses. 
     * @return
     */
    public JPanel coursePanel() {
		courseContentPanel.setBackground(new Color(255, 255, 255));
		courseContentPanel.setBounds(10, 48, 517, 289);
		courseContentPanel.setLayout(null);
		return courseContentPanel; 
    }
    
    /**
     * This method generates the courses and displays it on a list. 
     */
    public void generateCourses(String title) throws SQLException {
    	//y-value of panel used to hold the a course 
    	int y = 40; 

    	// get number of courses then iterate through this

    	//number of courses change value
		CourseDAOImplementation courseDAO = new CourseDAOImplementation();
		courseList = courseDAO.getAllCourses();
		courseWithButtons = new ArrayList<>();


		bookmarkButtonList = new ArrayList<>();

		for (Course course: courseList) {

			String text = "View";
			view = new JButton();
			view = buttonStyler(view, text);
			view.setBounds(408, 7, 80, 25);
			course.setViewButton(view);

			String state = "Bookmark";
			bookmark = new JButton();
			bookmark = buttonStyler(bookmark, state);
			bookmark.setBounds(308, 7, 80, 25);
			bookmark.addActionListener(this);
			bookmarkButtonList.add(bookmark);
			course.setBookmarkButton(bookmark);

			courseWithButtons.add(course);
		}

		if (title.equals("1000 Level Courses")) {
			for(Course e: courseWithButtons) {
				if (e.getCourseId() < 2000) {
					JPanel container = new JPanel();
					container = createContainer(container, y);
					courseContentPanel.add(container);
					createCourse(container, e.getCourseName(), e.getCourseCode());

					e.getViewButton().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ae) {
							if (ae.getSource() == e.getViewButton()) {
								// should show the course description and study materials
							}
						}
					});
					container.add(e.getBookmarkButton());
					container.add(e.getViewButton());
					y = y + 40;
				}
			}
		}
		else if (title.equals("2000 Level Courses")) {
			for(Course e: courseWithButtons) {
				if (e.getCourseId() >= 2000 && e.getCourseId() < 3000) {
					JPanel container = new JPanel();
					container = createContainer(container, y);
					courseContentPanel.add(container);
					createCourse(container, e.getCourseName(), e.getCourseCode());

					e.getViewButton().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ae) {
							if (ae.getSource() == e.getViewButton()) {
								// should show the course description and study materials
							}
						}
					});

					container.add(e.getBookmarkButton());
					container.add(e.getViewButton());
					y = y + 40;
				}
			}
		} else if (title.equals("3000 Level Courses")){
			for(Course e: courseWithButtons) {
				if (e.getCourseId() >= 3000) {
					JPanel container = new JPanel();
					container = createContainer(container, y);
					courseContentPanel.add(container);
					createCourse(container, e.getCourseName(), e.getCourseCode());
					e.getViewButton().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ae) {
							if (ae.getSource() == e.getViewButton()) {
								// should show the course description and study materials
							}
						}
					});

					container.add(e.getBookmarkButton());
					container.add(e.getViewButton());
					y = y + 40;
				}
			}
		}


	}
    
   
    /**
     * This method creates the components for the information on each courses (course code, name, bookmark, view course). 
     * @param container JPanel container for absolute placement of the course components. 
     * @param courseName Course name to be displayed. 
     * @param courseCode Course code to be displayed. 
     */
    public void createCourse(JPanel container, String courseName, String courseCode) {
    	
    	JLabel code = new JLabel(courseCode);
    	createLabels(code, 0, 100, container);
    	
    	JLabel name = new JLabel(courseName); 
    	createLabels(name, 100, 197, container);
    	
    }
    
    
    /**
     * This method sets the layout for the bookmark and view buttons. 
     * @param button JButton component for the courses. 
     * @param text Name to be displayed as text on the button. 
     * @return Returns the customized JButton.
     */
    public JButton buttonStyler(JButton button, String text) {
    	button.setText(text);
		button.setFont(new Font("Dubai", Font.BOLD, 13));
		button.setOpaque(true);
		button.setBackground(new Color(216, 237, 214));
		button.setForeground(new Color(255, 255, 255));
		button.setFocusPainted(false);		
		button.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		button.addActionListener(this);
		return button;
    	
    }
    
    /**
	 * This method creates a rounded border and sets the layout for each course level panel. 
	 * @param level JPanel for the course page
	 * @return Returns the course page panel with rounded border. 
	 */
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
    
    
    /**
	  * Invoked on button click which changes the bookmark state. 
	  */
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