package tests.unit;

import model.Course.Course;
import model.Course.CourseDAOImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class CourseDAOTest {
    int id = 3482;

    CourseDAOImplementation courseDAOImplementation = new CourseDAOImplementation();
    Course c1 = new Course.CourseBuilder(id).setCourseCode("EECS 3482").setCourseName("Cyber security").setCourseDesc("xyz").build();

    @AfterEach
    public void tearDown() throws SQLException {
        courseDAOImplementation.delete(id);
    }
    @Test
    public void addCourse() throws SQLException {



        assertNull(courseDAOImplementation.getCourseByNameOrId("EECS 3482"));
        courseDAOImplementation.add(c1);
        assertNotNull(courseDAOImplementation.getCourseByNameOrId("EECS 3482"));
        assertEquals("Cyber security", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseName());
        assertEquals("xyz", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseDesc());
        assertEquals("EECS 3482", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseCode());

    }
    @Test
    public void deleteCourse() throws SQLException {
        assertNull(courseDAOImplementation.getCourseByNameOrId("EECS 3482"));
        courseDAOImplementation.add(c1);
        assertNotNull(courseDAOImplementation.getCourseByNameOrId("EECS 3482"));
        assertEquals("Cyber security", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseName());
        assertEquals("xyz", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseDesc());
        assertEquals("EECS 3482", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseCode());
        courseDAOImplementation.delete(id);
        assertNull(courseDAOImplementation.getCourseByNameOrId("EECS 3482"));

    }
    @Test
    public void checkForDuplicateUsers() throws SQLException {
        String message = "Duplicate entry '3482' for key 'courses.PRIMARY'";
        String e = "";
        try {
            courseDAOImplementation.add(c1);
            courseDAOImplementation.add(c1);

        } catch (Exception ex) {
            e = ex.getMessage();
        }
        assertEquals(message, e);
    }
    @Test
    public void getCourseByNameOrId() throws SQLException {
        courseDAOImplementation.add(c1);
        assertEquals("Cyber security", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseName());
        assertEquals("xyz", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseDesc());
        assertEquals("EECS 3482", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseCode());

    }
    @Test
    public void update() throws SQLException {
        courseDAOImplementation.add(c1);
        assertEquals("Cyber security", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseName());
        c1.setCourseName("Introduction to Cyber Security");
        courseDAOImplementation.update(c1);
        assertNotEquals("Cyber security", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseName());
        assertEquals("Introduction to Cyber Security", courseDAOImplementation.getCourseByNameOrId("EECS 3482").getCourseName());
    }
}
