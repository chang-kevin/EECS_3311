package model.Topic;

import helpers.HyperlinkReg;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String topicId;
    private String topicName;

    private String courseId;

    public List<HyperlinkReg> urlList = new ArrayList<>();


    public Topic(String topicId, String topicName, String courseId, HyperlinkReg newUrl) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.courseId = courseId;
        HyperlinkReg url = newUrl;
        this.urlList.add(url);

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
