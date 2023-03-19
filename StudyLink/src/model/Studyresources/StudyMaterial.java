package model.Studyresources;

import java.sql.SQLException;
import java.util.List;

public class StudyMaterial {
    private String materialId;
    private List<String> url;
    private double rating ;

    public StudyMaterial(String materialId, List<String> url,double rating) throws SQLException {
        this.materialId = materialId;
        this.url = url;
        this.rating = rating;
    }
    public String getStudyMaterialId() {
        return materialId;
    }

    public List<String> getUrl() {
        return url;
    }

    public double getRating() {
        return rating;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
