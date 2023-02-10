import components.*;
import domainobjects.*;


public class Main {
    public static void main(String[] args) {
        new LoginPage();


        Course c1 = new Course(3000,"professional practise in computing",3);
        Course c2 = new Course(3311,"Software design",3);
        Course c3 = new Course(3101,"Design and analysis of algorithms",3);
        Course c4 = new Course(1012,"Introduction to Computing: A Net-centric Approach",1);

        Course c5 = new Course(3221,"Operating System Fundamentals",3);
        Course c6 = new Course(3421,"Introduction to Database Systems",3);
        Course c7 = new Course(3215,"Embedded Systems",3);
        Course c8 = new Course(2011,"professional practise in computing",2);
        Course c9 = new Course(2000,"Introduction to the Theory of Computation",2);
        Course c10 = new Course(2021,"Computer Organization",2);
        Course c11 = new Course(2030,"Advanced Object Oriented Programming",2);
        Course c12 = new Course(2031,"Software Tools",2);
        Course c13 = new Course(1001,"Research Directions in Computing",1);
        Course c14 = new Course(1019,"Discrete Mathematics for Computer Science",1);
        Course c15 = new Course(1090,"Introduction to Logic for Computer Science",1);
        Course c16 = new Course(1022,"Introduction to Object Oriented Programming",1);
        CourseList.courses.add(c1);
        CourseList.courses.add(c2);
        CourseList.courses.add(c3);
        CourseList.courses.add(c4);
        CourseList.courses.add(c5);
        CourseList.courses.add(c6);
        CourseList.courses.add(c7);
        CourseList.courses.add(c8);
        CourseList.courses.add(c9);
        CourseList.courses.add(c10);
        CourseList.courses.add(c11);
        CourseList.courses.add(c12);
        CourseList.courses.add(c13);
        CourseList.courses.add(c14);
        CourseList.courses.add(c15);
        CourseList.courses.add(c16);



        CourseList.search(3000);
        CourseList.list();
        User u1 = new User("Yuvtesh","mann.yuvtesh@gmail.com","xyz","mann",new DOB(29,04,2001));
        UserList.users.add(u1);
        User u2 = new User("Manasvi","manasvij@my.yorku.ca","abc" , "mj", new DOB(27, 11, 2002));
        UserList.users.add(u2);
        UserList.search("manasvij@my.yorku.ca");
    }


