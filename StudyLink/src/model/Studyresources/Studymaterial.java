package model.Studyresources;

import java.sql.SQLException;
import java.util.List;

public class Studymaterial {
    private String material_id;
    private List<String> url;
    private double rating ;

    public Studymaterial(String material_id, List<String> url) throws SQLException {
        this.material_id = material_id;
        this.url = url;
        this.rating = StudymaterialDAO.getRating(material_id);
    }
    public String getStudy_material_id() {
        return material_id;
    }

    public List<String> getUrl() {
        return url;
    }

    public double getRating() {
        return rating;
    }

    public void setmaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
