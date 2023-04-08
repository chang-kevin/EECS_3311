package view.dashboard;

import helpers.HyperlinkReg;
import model.Course.Course;
import model.Course.CourseDAO;
import model.Course.CourseDAOImplementation;
import model.Topic.Topic;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


public class ViewCourse extends RoundedPanel {

    Course course;
    JPanel coursePage;
    List<Topic> topicList;
    private JPanel contentPanel;

    /**
     * Constructor
     */
    public ViewCourse(Course course) throws SQLException {
        super(30, 30);

        this.course = course;
        setLayoutPanel();
        setBackground(new Color(129, 169, 173));
        setConstraints();

        topicList = getTopics();

        CoursePage();

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

    public void setLayoutPanel() {
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 0};
        layout.rowHeights = new int[]{0, 0, 0};
        layout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        layout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        setLayout(layout);
    }

    public void setConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(createTitle(course.getCourseCode() + ": " + course.getCourseName()), gbc);
        createCoursePane();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(addScrollPane(), gbc);

    }


    /**
     * This method creates the primary panel of the course page
     */
    public void CoursePage() {

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
        contentPanel.add(descriptionHeader);

        JPanel description = new JPanel();
        coursePanel(description);
        description.setPreferredSize(new Dimension(495, 200));
        description.setMaximumSize(new Dimension(495, 200));
        description.setBorder(null);
        createLabel(description, "<html>" + course.getCourseDesc() + "</html>");
        contentPanel.add(description);
    }

    /**
     * This method is used to show the Topics of the course and the Study Materials under these topics
     */
    public void showTopicsAndMaterials(){
        JPanel topicHeader = new JPanel();
        coursePanel(topicHeader);
        createHeaderLabel(topicHeader, "Topics");
        contentPanel.add(topicHeader);
        spacePanel();

        for(Topic t: topicList) {
            JPanel topicPanel = new JPanel();
            coursePanel(topicPanel);
            createLabel(topicPanel, t.getTopicName());
            contentPanel.add(topicPanel);


            for (HyperlinkReg hr: t.urlList) {
                JPanel studyMaterialsUrl = new JPanel();
                coursePanel(studyMaterialsUrl);
                createURLLabel(studyMaterialsUrl, hr);
                contentPanel.add(studyMaterialsUrl);
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
     * This method sets the size and color of the panels used in the course panel
     * @param container
     */
    public void coursePanel(JPanel container) {
        container.setBackground(new Color(255, 255, 255));
        container.setBorder(new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192)) );

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
        contentPanel.add(space);
    }


    /**
     * This method creates the header for each section in the content section, description and topics, of the course page
     * @param headerPanel panel in which the JLabel is added to
     * @param headerName the JLabel text
     */
    public void createHeaderLabel(JPanel headerPanel, String headerName) {

        //headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

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
