package view.dashboard;

import model.Course.Course;

import javax.swing.*;

public class ViewButtons {
    JButton viewButton;

    Course course;

    public ViewButtons(JButton viewButton, Course course) {
        this.viewButton = viewButton;
        this.course = course;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public Course getCourse() {
        return this.course;
    }

}
