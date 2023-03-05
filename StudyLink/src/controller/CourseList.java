package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CourseList extends JFrame {
    private String[] columnNames = { "Department", "Course Number", "Description" };
    private JPanel panel1;
    private JTable courseList;
    private JComboBox sortComboBox;
    private JButton button1;
    private JButton button2;

    public CourseList() {
        createTable();
        createCourseCombo();
    }

    private void createTable() {
        setVisible(true);
        setTitle("Course List");
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        // remove when connected to DB
        String[] columnNames = { "Department", "Course Number", "Description" };
        String[][] data = {
                { "EECS", "3311", "Software Design" },
                { "EECS", "3101", "Introduction to Algorithms" }
        };

        DefaultTableModel model = new DefaultTableModel(
                data,
                columnNames
        );
        courseList.setModel(model);
        TableColumnModel columns = courseList.getColumnModel();
        columns.getColumn(0).setMinWidth(100);
        columns.getColumn(1).setMinWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(1).setCellRenderer(centerRenderer);
    }

    private void createCourseCombo() {
        sortComboBox.setModel(new DefaultComboBoxModel(columnNames));
    }
}
