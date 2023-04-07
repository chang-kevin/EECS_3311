package model.Topic;

import helpers.HyperlinkReg;

import java.util.ArrayList;

public class Topic {
    private String topicId;
    private String topicName;

    private String courseId;

    private ArrayList<HyperlinkReg> url;


    public Topic(String topicId, String topicName, String courseId, ArrayList<HyperlinkReg> url) {
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

    public ArrayList<HyperlinkReg> getURL() { return this.url; }
}
