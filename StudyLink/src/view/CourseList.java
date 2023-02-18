package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CourseList extends JFrame {
    private JTable table;
    private JPanel panel1;

    public CourseList() {
        setVisible(true);
        setTitle("Course List");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        String[] columnNames = { "Department", "Course Number", "Description", "Session" };

        // Data to be displayed in the JTable
        String[][] data = {
                { "EECS", "3311", "Software Design", "W" },
                { "EECS", "3101", "Introduction to Algorithms", "W" }
        };

        DefaultTableModel model = new DefaultTableModel();
        for (String columnName: columnNames) {
            model.addColumn(columnName);
        }

        model.addRow(new Object[]{"EECS", "3311", "Software Design", "W"});
        model.addRow(new Object[]{ "EECS", "3101", "Introduction to Algorithms", "W" });

        table.setModel(model);
    }
}
