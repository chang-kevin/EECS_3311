package view.dashboard;

import model.Course.Course;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardLayoutDisplay extends RoundedPanel implements ActionListener {

	private CourseLevel courses;
	private CardLayout layout;
	private List<ViewButtons> viewList;
	private Bookmark bookmark;
	private SearchBar search;

	public CardLayoutDisplay() throws SQLException {
		super(30, 30);
		layout = new CardLayout();
		viewList = new ArrayList<>();
		setLayout(layout);

		bookmark = new Bookmark();
		add(bookmark, "bookmark");

		AccountManagement settings = new AccountManagement();
		add(settings, "settings");

		courses = new CourseLevel();

		add(courses.one, "one");

		add(courses.two, "two");

		add(courses.three, "three");
		setListener(courses);

		UploadLink uploadPage = new UploadLink();
		add(uploadPage, "upload");

	}

	public void setCourse(Course course) throws SQLException {
		ViewCourse viewCourse = new ViewCourse(course);
		add(viewCourse, "course");
	}

	public void swapTo(String card) {
		layout.show(this, card);
	}

	public void setListener(CourseLevel level) {
		for(ViewButtons viewButton : level.viewButtonList) {
			viewButton.getViewButton().addActionListener(this);
		}

		for(BookmarkButtons bookmarkButton : level.bookmarkList) {
			bookmarkButton.getBookmarkButton().addActionListener(this);
		}

		for(ViewButtons btn : bookmark.viewBookmark) {
			btn.getViewButton().addActionListener(this);
		}
	}

	public void searchAction(Course course) {
		System.out.println(course.getCourseName());
				try {
					setCourse(course);
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}
		swapTo("course");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(ViewButtons view : courses.viewButtonList) {
			if(e.getSource() == view.getViewButton()) {
				try {
					setCourse(view.getCourse());
					System.out.println(view.getCourse().getCourseCode());
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}
				swapTo("course");
			}

		}

		for(BookmarkButtons bookmarkBtn : courses.bookmarkList) {
			if(e.getSource() == bookmarkBtn.getBookmarkButton()) {
				bookmark.addBookmark(bookmarkBtn.getCourse());

			}
		}

		for(ViewButtons btn : bookmark.viewBookmark) {
			if(e.getSource() == btn.getViewButton()) {
				try {
					setCourse(btn.getCourse());
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}
				swapTo("course");
			}
		}

	}

}







