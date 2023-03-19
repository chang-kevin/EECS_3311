package model.Topic;

public class Topic {
    private String topicId;
    private String topicName;

    private String courseId;

    public Topic(String topicId, String topicName, String courseId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.courseId = courseId;
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
}
