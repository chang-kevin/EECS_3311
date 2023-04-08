package model.Topic;

import helpers.HyperlinkReg;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String topicId;
    private String topicName;

    private String courseId;

    public List<HyperlinkReg> urlList = new ArrayList<>();


    public Topic(String topicId, String topicName, String courseId, ArrayList<HyperlinkReg> urlist) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.courseId = courseId;
        this.urlList  = urlist;

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

    //public HyperlinkReg getURL() { return this.url; }
}
