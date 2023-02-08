package components;
import helpers.*;
import domainobjects.Course;
import domainobjects.CourseList;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addFile implements ActionListener{
    private JFrame frame;
    private JLabel title;
    private JLabel fileName;
    private JLabel courseListLabel;
    private JLabel instructorLabel;
    private JLabel courseYearLabel;
    private JTextField fileNameText;
    private JTextField instructorText;
    private JButton fileChoose;
    private JButton submit;
    private JComboBox courseDropDownMenu;
    private JComboBox courseYear;
    private CourseList courseList = new CourseList();
    
    addFile(){
        frame = new JFrame();
        frame.setBounds(100, 100, 850, 550);
        frame.setLayout(null);
        frame.setVisible(true);

        Bounds titleBounds = new Bounds(300, 0, 400, 30);
        title = BoundField.generateLabelComponent("Add your study materials here", titleBounds);
        frame.add(title);

        Bounds fileNameBounds = new Bounds(10, 50, 300, 30);
        fileName = BoundField.generateLabelComponent("Enter file name", fileNameBounds);
        frame.add(fileName);

        Bounds fileNameTextBounds = new Bounds(150, 50, 200, 30);
        fileNameText = BoundField.generateFieldComponent(fileNameTextBounds);
        frame.add(fileNameText);

        Bounds courseListBounds = new Bounds(10, 100, 100, 30);
        courseListLabel = BoundField.generateLabelComponent("Course Name", courseListBounds);
        frame.add(courseListLabel);

        courseDropDownMenu = new JComboBox(toString(courseList.getCourses()));
        courseDropDownMenu.setBounds(150, 100, 100, 40);
        frame.add(courseDropDownMenu);

        Bounds instructorBounds = new Bounds(10, 150, 100, 30);
        instructorLabel = BoundField.generateLabelComponent("Instructor", instructorBounds);
        frame.add(instructorLabel);

        Bounds instructorTextBounds = new Bounds(100, 150, 200, 30);
        instructorText = BoundField.generateFieldComponent(instructorTextBounds);
        frame.add(instructorText);

        Bounds courseYearBounds = new Bounds(10, 200, 100, 30);
        courseYearLabel = BoundField.generateLabelComponent("Course Year", courseYearBounds);
        frame.add(courseYearLabel);

        courseYear = new JComboBox(generateYears());
        courseYear.setBounds(100, 200, 200, 30);
        frame.add(courseYear);

        fileChoose = new JButton("Choose File");
        fileChoose.setBounds(10, 250, 100, 40);
        fileChoose.addActionListener(this);
        frame.add(fileChoose);
        
        submit = new JButton("Submit");
        submit.setBounds(150, 300, 100, 40);
        submit.addActionListener(this);
        frame.add(submit);
    }

    private String[] toString(ArrayList<Course> arrayList){
        String arr = arrayList.toString();
        arr = arr.replaceAll("\\[","").replaceAll("\\]", "");
        String[] courseArr = new String[]{arr};
        return courseArr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fileChoose){
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(null); //select file to open
        }
        if(e.getSource() == submit){
            JOptionPane.showMessageDialog(null, "Upload successful", "upload", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

     /**
     * Generate the year list dynamically
     */
    private String[] generateYears(){
        int endYear = Calendar.getInstance().get(Calendar.YEAR);
        int difference = endYear - 1940;
        int startYear = 1940;
        String[] arr = new String[difference];
        for (int year = 0; year < difference; year++){
            arr[year] = Integer.toString(startYear);
            startYear++;
        }
        return arr;
    }
}
