package controller;
import helpers.*;
import model.Course;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadFile implements ActionListener{
    private JTextField fileNameText;
    private JTextField instructorText;
    private JTextField courseTextField;
    private JButton fileChoose;
    private JButton submit;
    private JComboBox courseYear;

    public UploadFile() {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 850, 550);
        frame.setLayout(null);
        frame.setVisible(true);

        Bounds titleBounds = new Bounds(300, 0, 400, 30);
        JLabel title = BoundField.generateLabelComponent("Add your study materials here", titleBounds);
        frame.add(title);

        Bounds fileNameBounds = new Bounds(10, 50, 300, 30);
        JLabel fileName = BoundField.generateLabelComponent("Enter file name", fileNameBounds);
        frame.add(fileName);

        Bounds fileNameTextBounds = new Bounds(150, 50, 200, 30);
        fileNameText = BoundField.generateFieldComponent(fileNameTextBounds);
        frame.add(fileNameText);

        Bounds courseListBounds = new Bounds(10, 100, 100, 30);
        JLabel courseListLabel = BoundField.generateLabelComponent("Course Name", courseListBounds);
        frame.add(courseListLabel);

        Bounds courseTextBounds = new Bounds(150, 100, 300, 30);
        courseTextField = BoundField.generateFieldComponent(courseTextBounds);
        frame.add(courseTextField);

        Bounds instructorBounds = new Bounds(10, 150, 100, 30);
        JLabel instructorLabel = BoundField.generateLabelComponent("Instructor", instructorBounds);
        frame.add(instructorLabel);

        Bounds instructorTextBounds = new Bounds(100, 150, 200, 30);
        instructorText = BoundField.generateFieldComponent(instructorTextBounds);
        frame.add(instructorText);

        Bounds courseYearBounds = new Bounds(10, 200, 100, 30);
        JLabel courseYearLabel = BoundField.generateLabelComponent("Course Year", courseYearBounds);
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
            fileNameText.setText("");
            instructorText.setText("");
            courseTextField.setText("");
        }
        
    }

    /**
     * Generate the year list dynamically
     */
    public String[] generateYears(){
        int endYear = Calendar.getInstance().get(Calendar.YEAR);
        int difference = endYear - 1940;
        int startYear = 1940;
        String[] arr = new String[difference+1];
        arr[0] = " ";
        for (int i = 1; i < arr.length; i++){
            arr[i] = Integer.toString(startYear);
            startYear++;
        }
        return arr;
    }

    
}
