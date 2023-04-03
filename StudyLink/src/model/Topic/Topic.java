package model.Topic;

import helpers.HyperlinkReg;

public class Topic {
    private String topicId;
    private String topicName;

    private String courseId;

    private HyperlinkReg url;


    public Topic(String topicId, String topicName, String courseId, HyperlinkReg url) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.courseId = courseId;
        this.url = url;
    }

    public String getTopicCourseId() {
        return courseId;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public HyperlinkReg getURL() { return this.url; }
}
