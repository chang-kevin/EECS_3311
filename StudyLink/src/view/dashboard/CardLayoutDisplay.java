package view.dashboard;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

public class CardLayoutDisplay extends RoundedPanel {

	private CardLayout layout;

	public CardLayoutDisplay() throws SQLException {
		super(30, 30);
		layout = new CardLayout();

		setLayout(layout);


		RoundedPanel homePage = new RoundedPanel(30, 30);
		homePage.setBackground(new Color(217, 230, 226));
		add(homePage, "dashboard");

		CourseLevel one = new CourseLevel("1000 Level Courses");
		add(one, "one");

		CourseLevel two = new CourseLevel("2000 Level Courses");
		add(two, "two");

		CourseLevel three = new CourseLevel("3000 Level Courses");
		add(three, "three");

		UploadFile uploadPage = new UploadFile();
		add(uploadPage, "upload");

	}

	public void swapTo(String card) {
		layout.show(this, card);
	}
}







