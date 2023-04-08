package view.dashboard;

import model.Course.Course;
import model.Course.CourseDAOImplementation;
import model.Topic.TopicDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UploadLink extends RoundedPanel implements ActionListener, ItemListener {
    private String[] fields = {"Course Code", "Topic", "Title", "Resource Link"};
    private JComboBox courseBox;
    private JComboBox topicBox;
    private GridBagConstraints c;
    private JTextField titleField;
    private JTextField linkField;
    private JButton submit;
    private List<Course> courseList;
    private JButton refresh;
    TopicDAO topic = new TopicDAO();
    CourseDAOImplementation course = new CourseDAOImplementation();

    public UploadLink() throws SQLException {
        super(30, 30);
        setLayout(new GridBagLayout());
        setBackground(new Color(217, 230, 226));
        setConstraints();
    }

    public void setConstraints() throws SQLException {
        c = new GridBagConstraints();
        add(setFieldsLayout(fields[0]), addLeftComponent(0, 0));

        courseBox = new JComboBox(generateCourseList());
        courseBox.addItemListener(this);
        add(courseBox, addRightComponent(1, 0));

        //adding topic combobox
        add(setFieldsLayout(fields[1]), addLeftComponent(0, 2));
        topicBox = new JComboBox();
        add(topicBox, addRightComponent(1, 2));

        //Adding title text field
        add(setFieldsLayout(fields[2]), addLeftComponent(0, 3));
        titleField = new JTextField(20);
        add(titleField, addRightComponent(1, 3));

        //Adding resource text field
        add(setFieldsLayout(fields[3]), addLeftComponent(0, 4));
        linkField = new JTextField(20);
        add(linkField, addRightComponent(1, 4));

        //adding submit button
        submit = new JButton("Submit");
        buttonLayout(submit);
        add(submit, addLeftComponent(0, 5));
        submit.addActionListener(this);
    }

    public GridBagConstraints addLeftComponent(int x, int y) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 1;
        c.insets.left = 20;
        if(y == 1) {
            c.anchor = GridBagConstraints.WEST;
            c.weightx = 0;
        }
        if(y == 4) {
            c.weightx = 0.5;
        }
        return c;
    }

    public GridBagConstraints addRightComponent(int x, int y) {
        c.anchor = GridBagConstraints.EAST;
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 3;
        c.weightx = 1;
        c.insets.right = 50;
        return c;
    }

    public JLabel setFieldsLayout(String text) {
        JLabel field = new JLabel(text);
        field.setBorder(new EmptyBorder(15, 15, 10, 15));
        field.setForeground(new Color(53, 79, 82));
        field.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
        field.setHorizontalTextPosition(SwingConstants.CENTER);
        field.setHorizontalAlignment(SwingConstants.LEFT);
        return field;
    }

    public void buttonLayout(JButton submit) {
        submit.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
        submit.setBackground(new Color(74, 113, 117));
        submit.setForeground(new Color(255, 255, 255));
        submit.setPreferredSize(new Dimension(50, 25));
//        submit.setFocusPainted(false);
    }

    public Vector generateTopicsList() throws SQLException{
        String s = (String) courseBox.getSelectedItem();
        int courseCodeContent = getCourseCode(s);
        List arrList = topic.getTopicList(courseCodeContent);
        Vector vector = new Vector();
        for(int i = 0; i < arrList.size(); i++){
            vector.add(arrList.get(i));
        }
        return vector;
    }

    public String[] generateCourseList() throws SQLException {
        Vector<String> courseCodeName = new Vector<String>();
        courseList = course.getAllCourses();
        courseCodeName.add("Select");
        for(Course c: courseList){
            String s = c.getCourseCode() + ": " + c.getCourseName();
            courseCodeName.add(s);
        }
        String[] arr = new String[courseCodeName.size()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = courseCodeName.get(i);
        }
        return arr;
    }

    public int getCourseCode(String s){
        String[] arr = s.split(" ");
        String code = (arr[1] == null || arr[1].length() == 0) ? null : (arr[1].substring(0, arr[1].length() - 1));
        return Integer.parseInt(code);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Submit")){
            if(linkField.getText().isEmpty() || titleField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
            }
            else{
                try {

                    int resultMaterial = topic.insertIntoURL(linkField.getText(),(String) topicBox.getSelectedItem());
                    int course = getCourseCode((String) courseBox.getSelectedItem());

                    if(resultMaterial > 0 ){
                        JOptionPane.showMessageDialog(null, "Upload successful");
                        linkField.setText("");
                        titleField.setText("");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == 1){
            if(courseBox.getSelectedItem().equals("Select")){
                JOptionPane.showMessageDialog(null, "Please select a course");
            }

            else{
                try {
                    topicBox.setModel(new DefaultComboBoxModel(generateTopicsList()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
}
