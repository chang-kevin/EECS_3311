package components;
import helpers.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class addFile {
    private JFrame frame;
    private JLabel title;
    private JLabel fileName;
    private JTextField fileNameText;
    private JComboBox courseDropDownMenu;
    private JLabel courseNumberLabel;
    private JTextField courseNumber;
    
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

        
    }
}
