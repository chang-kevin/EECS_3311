package view.dashboard;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import model.Course.Course;
import model.Course.CourseDAOImplementation;
import model.Files.Files;
import model.User.UserSession;

public class UploadFile extends JPanel implements ActionListener {
    CourseDAOImplementation imp = new CourseDAOImplementation();
    JPanel upload;
    private JPanel panel;
    private JTextField textField;
    private JPanel borderPanel;
    private JPanel panelContainer;
    private List<Course> courseList;

    //Helper Functions
    /**
     * Sets the background and the bounds of a JPanel
     * @param panel The panel that needs to have the properties set
     * @param color The color that the panel needs to be set
     * @param x The horizontal alignment
     * @param y The vertical alignment
     * @param width The width of the panel
     * @param height The height of the panel
     * @return The panel with the modified properties
     */
    public JPanel setBackgroundAndBounds(JPanel panel, Color color, int x, int y, int width, int height){
        panel.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        panel.setBounds(x, y, width, height);
        panel.setLayout(null);
        return panel;
    }

    /*
     * This method returns a combobox that will show a list of all the courses that is currently in the database.
     * */
    public JComboBox generateCourseBox() throws SQLException {
        Vector<String> courseCodeName = new Vector<String>();
        courseList = imp.getAllCourses();
        for(Course c: courseList){
            String s = c.getCourseCode() + ": " + c.getCourseName();
            courseCodeName.add(s);
        }
        JComboBox box = new JComboBox(courseCodeName);
        return box;
    }

    /*
     * This method generates a combobox that will show a list of all the topics that is in our database.
     *  */
    public JComboBox generateTopicBox() throws SQLException{
        JComboBox box = new JComboBox(imp.getTopicList().toArray(new String[0]));
        return box;
    }

    /**
     * This functions formats the label
     * @param label The label that we want to format
     * @param y The position that we want at a static position from the top
     * @return Returning the formatted label
     */
    public JLabel textLayout(JLabel label, int y) {
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        label.setBounds(20, y, 81, 14);
        panelContainer.add(label);
        return label;
    }

    /**
     * Customizing the button
     * @param btn The button we want to customize
     * @param x The horizontal alignment
     * @param y The vertical alignment
     * @param width The width
     * @param height The height
     * @param c The color
     * @return The formatted button
     */
    public JButton createButton(JButton btn, int x, int y, int width, int height, Color c){
        btn.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btn.setForeground(c);
        btn.setBounds(x, y, width, height);
        return btn;
    }

    //UI Functions
    /**
     * This method sets up the upload page
     * @throws SQLException
     */

    public UploadFile() throws SQLException {
        createPanel();
        createBorderPanels();
        pageTitle();
        createLabels();
        createInputField();
        displayBtns();
    }

    /**
     *FIX
     */
    public void createPanel() {
        upload = new JPanel();
        upload = createUpload();
    }

    /**
     * This function will generate all the borders for all the panels used in the upload feature.
     */
    public void createBorderPanels() {
        //Sets the background color of the panel that holds all the upload features (title, course code, topic, etc)
        panelContainer = new JPanel();
        Color c = new Color(244, 181, 181);
        panelContainer = setBackgroundAndBounds(panelContainer, c, 20, 20, 497, 315);
        panelContainer.setOpaque(true);
        upload.add(panelContainer);

        borderPanel = new JPanel();
        c = new Color(255, 255, 255);
        borderPanel = setBackgroundAndBounds(borderPanel, c, 10, 170, 477, 80);
        panelContainer.add(borderPanel);
    }

    /*
    * This method sets the title of the panel (upload)
    * */
    public void pageTitle() {
        JLabel pageTitle = new JLabel();
        pageTitle.setBounds(10, 0, 111, 33);
        panelContainer.add(pageTitle);
        pageTitle.setForeground(new Color(255, 255, 255));
        pageTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        pageTitle.setText("Upload File");
    }

    /*
    * This method creates labels (Course Code, Topic, Title, and Choose File)
    * that will be used to specify which course the material is
    * for, what topic it is related to (study notes, lecture notes, etc) and the title of the study material.
    * */
    public void createLabels() {
        JLabel codeText = new JLabel("Course Code");
        codeText = textLayout(codeText, 40);

        JLabel topicText = new JLabel("Topic");
        topicText = textLayout(topicText, 70);

        JLabel titleText = new JLabel("Title");
        titleText = textLayout(titleText, 100);

        JLabel chooseFileText = new JLabel("Choose file");
        chooseFileText = textLayout(chooseFileText, 130);
    }

    /**
     * Creating the input fields.
     * Add a combobox that will show a drop-down list of courses.
     * Add a combobox that will show a box of topics.
     * Add a text field that will allow the user to enter a name of the file
     * @throws SQLException
     */
    public void createInputField() throws SQLException {
        //generating course combobox
        JComboBox courseCodeList = generateCourseBox();
        courseCodeList.setBounds(154, 40, 300, 20);
        panelContainer.add(courseCodeList);

        //generating topic combo box
        JComboBox topicList = generateTopicBox();
        topicList.setBounds(154, 70, 300, 20);
        panelContainer.add(topicList);

        //generating text field to type in file name
        textField = new JTextField();
        textField.setBounds(154, 100, 104, 20);
        panelContainer.add(textField);
        textField.setColumns(10);
    }

    /**
     * This function will display the upload and submit button.
     */
    public void displayBtns() {
        Color c = new Color(0, 0, 0);
        //Displaying upload button
        JButton fileName = new JButton("Upload");
        fileName = createButton(fileName, 154, 135, 149, 20, c);
        fileName.addActionListener(this);
        panelContainer.add(fileName);

        //Displaying the submit button
        JButton btnNewButton = new JButton("Submit");
        btnNewButton = createButton(btnNewButton, 154, 280, 100, 20, c);
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(this);
        panelContainer.add(btnNewButton);
    }


    public JPanel createUpload() {
        upload = panelBorder(panel);
        upload.setBounds(169, 203, 537, 348);
        return upload;
    }

    /**
     * This function creates the panel for where we can upload the files
     * @param panel The portion of the screen that displays the upload file functionality
     * @return The panel
     */
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
        File selectedFile = null;
        File newFile;
        int courseId;
        int topicId;
        JFileChooser file = new JFileChooser();
        System.out.println(file);
        int file2;
        if(e.getActionCommand().equals("Upload")){
            file2 = file.showSaveDialog(null);
            System.out.println(file);
            if(file2 != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(null, "Please choose a file");
                return;
            }
            else{
                System.out.println("Line 266" + file.getSelectedFile());
                selectedFile = file.getSelectedFile();
            }
        }

        if(e.getActionCommand().equals("Submit")){
            System.out.println(selectedFile);
//            String s = file.getSelectedFile().getAbsolutePath();
////          Files files = new Files.Filesbuilder().setFile_name(file.getSelectedFile());
//            String id = UUID.randomUUID().toString();
//            String username = UserSession.getInstance().getCurrentUser().getUsername();
//            try {
//                courseId = getCourseCode(generateCourseBox());
//                topicId = getTopicId(generateTopicBox());
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            String filetype =
        }
    }

    public int getCourseCode(JComboBox box){
        String s = (String) box.getSelectedItem();
        String[] arr = s.split(" ");
        String code = (arr[1] == null || arr[1].length() == 0) ? null : (arr[1].substring(0, arr[1].length() - 1));
        System.out.println(code);
        return Integer.parseInt(code);
    }

    public int getTopicId(JComboBox box){
        String s = (String) box.getSelectedItem();
        String[] arr = s.split(":");
        String code = arr[0];
        System.out.println(code);
        return Integer.parseInt(code);
    }

//    public File FileBuilder(File fileNew){
//
//    }


}
