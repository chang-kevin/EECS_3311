package components;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

import helpers.HyperlinkReg;

public class CourseResource extends JFrame {

	private JLabel topic;
	private HyperlinkReg hyperlink;
	private Dashboard dashboard;

	public CourseResource() {
		
    	dashboard = new Dashboard();

		JPanel resources = new JPanel();
		resources.setBackground(new Color(240, 240, 240));
		resources.setBounds(0, 175, 834, 336);
		dashboard.frame.getContentPane().add(resources);
		resources.setLayout(null);

		topic = registerCourseTopic("Software Design Patterns");
		topic.setBounds(30, 40, 500, 60);
		resources.add(topic);

		hyperlink = new HyperlinkReg("https://www.youtube.com/playlist?list=PLF206E906175C7E07");
		hyperlink.setBounds(50, 65, 500, 50);
		resources.add(hyperlink.getHyperlink());
		
		JLabel lblNewLabel = new JLabel("   Software Design Resources");
		lblNewLabel.setForeground(new Color(133, 153, 205));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(0, 11, 772, 67);
		resources.add(lblNewLabel);

	}

	/**
	 * Register the course topic
	 * @param topicName Name of the topic
	 * @return JLabel
	 */
	private JLabel registerCourseTopic(String topicName) {
		topic = new JLabel(topicName);
		topic.setForeground(Color.DARK_GRAY);
		return topic;
	}
}