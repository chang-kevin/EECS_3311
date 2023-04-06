package view.dashboard;

import model.Course.Course;

import javax.swing.*;

public class BookmarkButtons {
    JButton bookmarkButton;

    Course course;

    public BookmarkButtons(JButton bookmarkButton, Course course) {
        this.bookmarkButton = bookmarkButton;
        this.course = course;
    }

    public JButton getBookmarkButton() {
        return bookmarkButton;
    }

    public Course getCourse() {
        return this.course;
    }

}
