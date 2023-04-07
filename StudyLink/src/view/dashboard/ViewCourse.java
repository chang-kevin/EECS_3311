package view.dashboard;

import helpers.HyperlinkReg;
import model.Course.Course;
import model.Course.CourseDAO;
import model.Course.CourseDAOImplementation;
import model.Topic.Topic;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


public class ViewCourse {

    Course course;
    JPanel coursePage;
    private JPanel container;

    JPanel scrollPanel;
    List<Topic> topicList;

    /**
     * Constructor
     */
    public ViewCourse(Course course) throws SQLException {

        createPanel();

        this.course = course;
        update();

        topicList = getTopics();

        courseTitle();

        createContainer();

        addScrollPane();

        CoursePage();

    }


    /**
     * This method creates the base panel of the course page, which acts like a border for the primary panel.
     */
    public void createPanel() {

        coursePage = panelBorder(coursePage);

    }


    /**
     * This method creates the primary panel of the course page
     */
    public void CoursePage() {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        showDescription();

        showTopicsAndMaterials();

    }

    /**
     * This method is used to show the description of the course
     */
    public void showDescription() {
        JPanel descriptionHeader = new JPanel();
        coursePanel(descriptionHeader);
        createHeaderLabel(descriptionHeader, "Description");
        container.add(descriptionHeader);

        JPanel description = new JPanel();
        coursePanel(description);
        description.setPreferredSize(new Dimension(495, 200));
        description.setMaximumSize(new Dimension(495, 200));
        description.setBorder(null);
        createLabel(description, "<html>" + course.getCourseDesc() + "</html>");
        container.add(description);
    }

    /**
     * This method is used to show the Topics of the course and the Study Materials under these topics
     */
    public void showTopicsAndMaterials(){
        JPanel topicHeader = new JPanel();
        coursePanel(topicHeader);
        createHeaderLabel(topicHeader, "Topics");
        container.add(topicHeader);
        spacePanel();

        for(Topic t: topicList) {
            JPanel topicPanel = new JPanel();
            coursePanel(topicPanel);
            createLabel(topicPanel, t.getTopicName());
            container.add(topicPanel);
            for(int i = 0;i<topicList.get(i).getURL().size();i++){
                JPanel studyMaterialsUrl = new JPanel();
                coursePanel(studyMaterialsUrl);
                createURLLabel(studyMaterialsUrl,t.getURL().get(i) );
                container.add(studyMaterialsUrl);

            }



            spacePanel();
        }
    }


    /**
     * This method creates the layout of the JLabels used in this class
     * @param title is the text of the label
     * @return JLabel label
     */
    public JLabel labelLayout(String title) {
        JLabel label = new JLabel(title);
        label.setForeground(new Color(150, 150, 150));
        label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        return label;
    }


    /**
     * This method sets the title of the course. For e.g :- EECS 3311: Software Design
     */
    public void courseTitle() {
        if(course != null) {
            JLabel courseName = new JLabel(course.getCourseCode() + ": " + course.getCourseName());
            courseName.setBounds(10, 11, 517, 38);
            coursePage.add(courseName);
            courseName.setForeground(new Color(241, 146, 146));
            courseName.setBackground(new Color(255, 255, 255));
            courseName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        }
        else {
            JLabel courseName = new JLabel("");
            courseName.setBounds(10, 11, 246, 38);
            coursePage.add(courseName);
            courseName.setForeground(new Color(241, 146, 146));
            courseName.setBackground(new Color(255, 255, 255));
            courseName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        }

    }


    /**
     * This method sets the size and color of the panels used in the course panel
     * @param container
     */
    public void coursePanel(JPanel container) {
        container.setBackground(new Color(255, 255, 255));
        container.setBorder(new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192)) );
        container.setPreferredSize(new Dimension(495, 30));
        container.setMaximumSize(new Dimension(495, 30));
    }

    /**
     * This method creates an empty panel
     */
    public void spacePanel() {
        JPanel space = new JPanel();
        space.setBackground(new Color(255, 255, 255));
        space.setBorder(null);
        space.setPreferredSize(new Dimension(495, 20));
        space.setMaximumSize(new Dimension(495, 20));
        container.add(space);
    }


    /**
     * This method creates the header for each section in the content section, description and topics, of the course page
     * @param headerPanel panel in which the JLabel is added to
     * @param headerName the JLabel text
     */
    public void createHeaderLabel(JPanel headerPanel, String headerName) {

        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

        JLabel label = new JLabel(headerName);
        label.setForeground(new Color(75, 74, 74));
        label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        headerPanel.add(label);

        label.setHorizontalAlignment(SwingConstants.SOUTH_EAST);
    }


    /**
     * this method creates the several labels used in this class
     * @param labelPanel panel in which the JLabel is added to
     * @param labelName the text of the Jlabel
     */
    public void createLabel(JPanel labelPanel, String labelName) {
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));

        JLabel label = labelLayout(labelName);

        labelPanel.add(label);

        label.setHorizontalAlignment(SwingConstants.SOUTH_EAST);
    }


    /**
     * This method adds the hyperlink to the panel.
     * @param labelPanel panel to which the hyperlink is added to
     * @param url the hyperlink
     */
    public void createURLLabel(JPanel labelPanel, HyperlinkReg url) {
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        labelPanel.add(url.getHyperlink());

    }


    public void createContainer() {
        scrollPanel = new JPanel();
        scrollPanel.setBackground(new Color(255, 255, 255));
        scrollPanel.setBounds(10, 60, 517, 277);

        coursePage.add(scrollPanel);

        container = new JPanel();
        container.setBackground(new Color(255, 255, 255));
        container.setBounds(10, 60, 517, 277);
        container.setLayout(null);
    }

    public void addScrollPane() {
        JScrollPane scrollpane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setPreferredSize(new Dimension(517, 277));
        scrollpane.getVerticalScrollBar().setUnitIncrement(5);
        scrollpane.getVerticalScrollBar().setUI(new ScrollBarCustom());
        scrollpane.getVerticalScrollBar().setBackground(new Color(255, 255, 255));
        scrollpane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 8));
        scrollpane.setBorder(null);
        scrollPanel.add(scrollpane);
    }


    /**
     * this method updates the title of the course
     */
    public void update() {
        courseTitle();
    }

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
                graphics.setColor(new Color(213, 237, 209));
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                graphics.setColor(new Color(213, 237, 209));
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }
        };

        panel.setBounds(169, 203, 537, 348);
        panel.setOpaque(false);
        panel.setLayout(null);

        return panel;
    }


    /**
     * This method fetches the topics in the database
     * @return a list of topics registered under this course
     * @throws SQLException
     */
    public List<Topic> getTopics() throws SQLException {
        //get all topics then iterate
        CourseDAO courseDAO = new CourseDAOImplementation();
        List<Topic> topicList = courseDAO.getCourseTopics(Integer.toString(course.getCourseId()));

        return topicList;

    }

}
