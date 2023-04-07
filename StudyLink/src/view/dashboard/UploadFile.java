package view.dashboard;

import model.Course.Course;
import model.Course.CourseDAOImplementation;
import model.Topic.TopicDAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UploadFile extends RoundedPanel implements ActionListener {
    private String[] fields = {"Course Code", "Topic", "Title", "Resource Link"};
    private JComboBox courseBox;
    private JComboBox topicBox;
    private GridBagConstraints c;
    private JTextField titleField;
    private JTextField linkField;
    private JButton submit;
    private List<Course> courseList;
    TopicDAO topic = new TopicDAO();
    CourseDAOImplementation course = new CourseDAOImplementation();


    public UploadFile() {
        super(30, 30);

        setLayout(new GridBagLayout());
        setBackground(new Color(217, 230, 226));

        setConstraints();



    }

    public void setConstraints() {
        c = new GridBagConstraints();


        add(setFieldsLayout(fields[0]), addLeftComponent(0, 0));

        courseBox = new JComboBox();
        add(courseBox, addRightComponent(1, 0));

        add(setFieldsLayout(fields[1]), addLeftComponent(0, 1));

        topicBox = new JComboBox();
        add(topicBox, addRightComponent(1, 1));

        add(setFieldsLayout(fields[2]), addLeftComponent(0, 2));

        titleField = new JTextField(20);
        add(titleField, addRightComponent(1, 2));

        add(setFieldsLayout(fields[3]), addLeftComponent(0, 3));

        linkField = new JTextField(20);
        add(linkField, addRightComponent(1, 3));


        submit = new JButton("Submit");
        buttonLayout(submit);
        add(submit, addLeftComponent(0, 4));
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
        submit.setFocusPainted(false);
    }

    public JComboBox generateTopicBox() throws SQLException{
        JComboBox box = new JComboBox(topic.getTopicList().toArray(new String[0]));
        return box;
    }

    public JComboBox generateCourseBox() throws SQLException {
        Vector<String> courseCodeName = new Vector<String>();
        courseList = course.getAllCourses();
        for(Course c: courseList){
            String s = c.getCourseCode() + ": " + c.getCourseName();
            courseCodeName.add(s);
        }
        JComboBox box = new JComboBox(courseCodeName);
        return box;
    }

    public int getCourseCode(JComboBox box){
        String s = (String) box.getSelectedItem();
        String[] arr = s.split(" ");
        String code = (arr[1] == null || arr[1].length() == 0) ? null : (arr[1].substring(0, arr[1].length() - 1));
        return Integer.parseInt(code);
    }

    public int getTopicId(JComboBox box){
        String s = (String) box.getSelectedItem();
        String[] arr = s.split(":");
        String code = arr[0];
        return Integer.parseInt(code);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        File selectedFile = null;
        File newFile;
        int courseId;
        int topicId;
        JFileChooser file = new JFileChooser();
        int file2;
        if(e.getActionCommand().equals("Upload")){
            file2 = file.showSaveDialog(null);
            if(file2 != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(null, "Please choose a file");
                return;
            }
            else{
                selectedFile = file.getSelectedFile();
            }
        }

        if(e.getActionCommand().equals("Submit")){

        }
    }



}
