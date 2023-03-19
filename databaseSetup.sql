/*This is the script for the production version of the app (actual database instance).
Create your database using this script and the credentials below:
String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/studylink";
String username = "root";
String password = "Kungfuhustle10*";

Schema - studylink
*/

DROP DATABASE IF EXISTS studylink;
CREATE DATABASE studylink;
USE studylink;
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(45) NOT NULL,
    PRIMARY KEY (username),
    user_password VARCHAR(45) NOT NULL,
    user_role VARCHAR(45) NOT NULL,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    birthdate DATE
    );
CREATE TABLE IF NOT EXISTS courses (
                                       course_id INT NOT NULL,
                                       PRIMARY KEY(course_id),
    name VARCHAR(1000) NOT NULL,
    description TEXT,
    course_code VARCHAR(45) NOT NULL,
    username VARCHAR(45),
    FOREIGN KEY (username) REFERENCES users(username)
    );
CREATE TABLE IF NOT EXISTS user_courses (
    username VARCHAR(45),
    course_id INT,
    course_code VARCHAR(45) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY(course_id) REFERENCES courses(course_id),
    PRIMARY KEY (username, course_id)
    );
CREATE TABLE IF NOT EXISTS topics (
    topic_id VARCHAR(45) NOT NULL,
    topic_name VARCHAR(150),
    PRIMARY KEY (topic_id)
    );
CREATE TABLE IF NOT EXISTS course_topics (
                                             course_id INT,
                                             topic_id VARCHAR(45),
    FOREIGN KEY(course_id) REFERENCES courses(course_id),
    FOREIGN KEY(topic_id) REFERENCES topics(topic_id),
    PRIMARY KEY(course_id, topic_id)
    );
CREATE TABLE IF NOT EXISTS study_materials (
    material_id VARCHAR(45) NOT NULL,
    material_name VARCHAR(45),
    topic_id VARCHAR(45),
    username VARCHAR(45),
    PRIMARY KEY(material_id),
    FOREIGN KEY (topic_id) REFERENCES topics(topic_id),
    FOREIGN KEY(username) REFERENCES users(username)
    );
CREATE TABLE IF NOT EXISTS study_materials_urls (
    url_id VARCHAR(45) NOT NULL,
    PRIMARY KEY(url_id),
    material_id VARCHAR(45),
    FOREIGN KEY(material_id) REFERENCES study_materials(material_id),
    url VARCHAR(500)
    );

CREATE TABLE IF NOT EXISTS course_study_materials (
                                                      course_id INT,
                                                      FOREIGN KEY (course_id) REFERENCES courses(course_id),
    study_material_id VARCHAR(45),
    FOREIGN KEY(study_material_id) REFERENCES study_materials(material_id)
    );
CREATE TABLE IF NOT EXISTS course_ratings (
                                              course_id INT,
                                              FOREIGN KEY(course_id) REFERENCES courses(course_id),
    username VARCHAR(45),
    FOREIGN KEY(username) REFERENCES users(username),
    rating ENUM("1", "2", "3", "4", "5"),
    PRIMARY KEY (username, course_id)
    );
    
CREATE TABLE IF NOT EXISTS Study_materials_ratings (
    material_id VARCHAR(45) NOT NULL,
    username VARCHAR(45),
    FOREIGN KEY(username) REFERENCES users(username),
    rating INT NOT NULL ,
    CHECK (rating >= 1 AND rating <= 5),
    PRIMARY KEY (username,material_id)
    );

CREATE TABLE IF NOT EXISTS user_settings (
    user_settings_id VARCHAR(45) NOT NULL,
    PRIMARY KEY(user_settings_id),
    username VARCHAR(45),
    FOREIGN KEY(username) REFERENCES users(username),
    theme ENUM("light", "dark")
    );
CREATE TABLE IF NOT EXISTS user_files (
    file_id VARCHAR(45) NOT NULL,
    PRIMARY KEY (file_id),
    username VARCHAR(45),
    FOREIGN KEY(username) REFERENCES users(username),
    course_id INT NOT NULL,
    FOREIGN KEY(course_id) REFERENCES courses(course_id),
    topic_id VARCHAR(45) NOT NULL,
    FOREIGN KEY(topic_id) REFERENCES topics(topic_id),
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(45) NOT NULL,
    file_size INT NOT NULL,
    file_data BLOB NOT NULL,
    file_notes TEXT
    );
CREATE TABLE IF NOT EXISTS security_questions (
    question_id VARCHAR(45) NOT NULL,
    PRIMARY KEY (question_id),
    question_text VARCHAR(255) NOT NULL
    );
CREATE TABLE IF NOT EXISTS user_security_questions (
    username VARCHAR(45),
    FOREIGN KEY (username) REFERENCES users(username),
    question_id VARCHAR(45),
    FOREIGN KEY (question_id) REFERENCES security_questions(question_id),
    answer VARCHAR(255),
    PRIMARY KEY (username, question_id)
    );
INSERT INTO courses
VALUES
    (
        1001, 'Research Directions in Computing',
        'An introduction to research directions within the department and more broadly within the field. Students will attend lectures and other events organised by the department. Note: This course is expected to be completed in the first-year of study.',
        'EECS 1001', NULL
    ),
    (
        1012, 'Introduction to Computing: A Net-centric Approach',
        'The objectives of 1012 are threefold: providing a first exposure to event-driven programming, teaching students a set of computing skills (including reasoning about algorithms, tracing programs, test-driven development, unit testing), and providing an introduction to computing within a mobile, net-centric context. It uses problem-based approach to expose the underlying concepts and an experiential laboratory to implement them. A mature mobile software infrastructure (such as HTML, CSS, and JavaScript) is used so that students can pick up key programming concepts (such as variables and control flow) within a client-server context without being bogged down in complex or abstract constructs. Laboratory exercises expose students to a range of real-world problems with a view of motivating computational thinking and grounding the material covered in lecture.',
        'EECS 1012', NULL
    ),
    (
        1015, 'Introduction to Computer Science and Programming',
        'This course is an introduction to the concepts and tools of computer science as students learn a procedural subset of the Python programming language.',
        'EECS 1015', NULL
    ),
    (
        1019, 'Discrete Mathematics for Computer Science',
        'Introduction to abstraction. Use and development of precise formulations of mathematical ideas. Informal introduction to logic; introduction to naÃ¯ve set theory; induction; relations and functions; big O-notation; recursive definitions, recurrence relations and their solutions; graphs and trees. ',
        'EECS 1019', NULL
    ),
    (
        1022, 'Introduction to Object Oriented Programming',
        'Provides a first exposure to object-oriented programming and enhances student understanding of key computing skills such as programming with objects and simple data structures (e.g., arrays, linked lists), reasoning about algorithms, and working with software tools. It uses a problem-based approach to expose the underlying concepts and an experiential laboratory to implement them. A mature Integrated Development Environment (such as Java and the Eclipse programming, testing, and debugging environment) is used to expose and provide context to the underlying ideas. Laboratory exercises expose students to a range of real-world problems with a view of motivating computational thinking and grounding the material covered in lectures.',
        'EECS 1022', NULL
    ),
    (
        2001, 'Introduction to the Theory of Computation',
        'Introduction to the theory of computing, including automata theory, formal languages and Turing machines; theoretical models and their applications in various fields of computer science. The emphasis is on practical applications of the theory and concepts rather than formal rigour.',
        'EECS 2001', NULL
    ),
    (
        2011, 'Fundamentals of Data Structures',
        'A study of fundamental data structures and their use in the efficient implementation of algorithms. Topics include abstract data types, lists, stacks, queues, trees and graphs.',
        'EECS 2011', NULL
    ),
    (
        2021, 'Computer Organization', 'Introduction to computer organization and instruction set architecture, covering assembly language, machine language and encoding, addressing modes, single/multicycle datapaths (including functional units and controls), pipelining, memory segments and memory hierarchy.',
        'EECS 2021', NULL
    ),
    (
        2030, 'Advanced Object Oriented Programming',
        'This course continues the separation of concern theme introduced in LE/EECS 1020 3.00 and LE/EECS1021 3.00. While 1020 and 1021 focuses on the client concern, this course focuses on the concern of the implementer. Hence, rather than using an API (Application Programming Interface) to build an application, the student is asked to implement a given API. Topics include implementing classes (non-utilities, delegation within the class definition, documentation and API generation, implementing contracts), aggregations (implementing aggregates versus compositions and implementing collections), inheritance hierarchies (attribute visibility, overriding methods, abstract classes versus interfaces, inner classes); applications of aggregation and inheritance in concurrent programming and event-driven programming; recursion; searching and sorting including quick and merge sorts); stacks and queues; linked lists; binary trees.',
        'EECS 2030', NULL
    ),
    (
        2031, 'Software Tools', 'Tools commonly used in the software development process: the C language; shell programming; filters and pipes; version control systems and \"make\"; debugging and testing. ',
        'EECS 2031', NULL
    ),
    (
        3000, 'Professional Practice in Computing',
        'Professional, legal and ethical issues in the development, deployment and use of computer systems; their impact on society including privacy and security, computer crime, malware, and intellectual property; professional ethics and responsibilities; guest lecturers from industry, government and university.',
        'EECS 3000', NULL
    ),
    (
        3101, 'Design and Analysis of Algorithms',
        'Review of fundamental data structures. Analysis of algorithms: time and space complexity. Algorithm design paradigms: divide-and-conquer, exploring graphs, greedy methods, local search, dynamic programming, probabilistic algorithms, computational geometry. NP-complete problems.',
        'EECS 3101', NULL
    ),
    (
        3215, 'Embedded Systems', 'Introduction to the design of embedded systems using both hardware and software. Topics include microcontrollers; their architecture, and programming; design and implementation of embedded systems using field programmable gate arrays.',
        'EECS 3215', NULL
    ),
    (
        3221, 'Operating System Fundamentals',
        'Principles of operating systems. Concurrent processes, CPU scheduling, deadlocks, memory management, file systems, protection and security, and case studies.',
        'EECS 3221', NULL
    ),
    (
        3311, 'Software Design', 'A study of design methods and their use in the correct implementation, maintenance and evolution of software systems. Topics include design, implementation, testing, documentation needs and standards, support tools. Students design and implement components of a software system.',
        'EECS 3311', NULL
    ),
    (
        3401, 'Introduction to Artificial Intelligence and Logic Programming',
        'Artificial Intelligence (AI) deals with how to build intelligent systems. In this course, we examine fundamental concepts in AI: knowledge representation and reasoning, search, constraint satisfaction, reasoning under uncertainty, etc. The course also introduces logic programming and Prolog. ',
        'EECS 3401', NULL
    ),
    (
        3421, 'Introduction to Database Systems',
        'Concepts, approaches and techniques in database management systems (DBMS). Logical model of relational databases. An introduction to relational database design. Other topics such as query languages, crash recovery and concurrency control.',
        'EECS 3421', NULL
    ),
    (
        3461, 'User Interfaces', 'Introduces user interfaces and the tools and mechanisms to create and prototype them. Students work in small groups and learn how to design user interfaces, how to realize them and how to evaluate the end result.',
        'EECS 3461', NULL
    );
INSERT INTO users
VALUES
    (
        'ymann@my.yorku.ca', 'yuvtesh', 'admin',
        'yuvtesh', 'mann', NULL
    ),
    (
        'kev10th@my.yorku.ca', 'kevin', 'admin',
        'kevin', 'chang', NULL
    ),
    (
        'gelailai@my.yorku.ca', 'angella',
        'admin', 'angella', 'manalo', NULL
    ),
    (
        'manasvij@my.yorku.ca', 'manasvi',
        'admin', 'manasvi', 'jain', NULL
    ),
    (
        'aemcruz@my.yorku.ca', 'ammiel',
        'admin', 'ammiel', 'cruz', NULL
    );
INSERT INTO user_courses
VALUES
    (
        'aemcruz@my.yorku.ca', 3101, 'EECS 3101'
    ),
    (
        'aemcruz@my.yorku.ca', 1001, 'EECS 1012'
    );
INSERT INTO topics (topic_id, topic_name)
VALUES
    (1, 'sorting'),
    (2, 'Divide and Conquer'),
    (3, 'Dynamic Programming'),
    (4, 'Greedy Algorithms'),
    (5, 'Graphs'),
    (6, 'Time complexity'),
    (7, 'ER Model'),
    (8, 'EER model'),
    (9, 'Relational Model'),
    (10, 'mysql'),
    (
        11, 'Functional Dependencies and Normalization for Relational Databases'
    ),
    (
        12, 'The Relational Algebra and The Relational Calculus'
    ),
    (
        13, 'EECS-3000Course Introduction'
    ),
    (14, 'Cybercrime'),
    (15, 'Intellectual Property'),
    (16, 'Information Privacy'),
    (
        17, 'Regulating Commerce and Speech'
    ),
    (18, 'UML'),
    (19, 'Solid Design Prinicples'),
    (20, 'Design Patterns'),
    (21, 'J-unit testing'),
    (
        22, 'Architecture patterns and styles'
    ),
    (23, 'Design By contract'),
    (24, 'Integration Testing'),
    (
        25, 'Operating System Structures'
    ),
    (26, 'Process Management'),
    (
        27, 'Process synchronization in operating system'
    ),
    (28, 'Memory Management'),
    (
        29, 'security and protection in operating system'
    ),
    (30, 'Introduction to OOPS'),
    (31, 'Constructor'),
    (32, 'Inheritance'),
    (33, 'Encapsulation'),
    (34, 'Polymorphism'),
    (35, 'Abstraction'),
    (36, 'Java Swing'),
    (37, 'Binary Search'),
    (38, 'Binary trees');
INSERT INTO course_topics (course_id, topic_id)
VALUES
    (3101, 1),
    (3101, 2),
    (3101, 3),
    (3101, 4),
    (3101, 5),
    (3101, 6),
    (3421, 7),
    (3421, 8),
    (3421, 9),
    (3421, 10),
    (3421, 11),
    (3421, 12),
    (3000, 13),
    (3000, 14),
    (3000, 15),
    (3000, 16),
    (3000, 17),
    (3311, 18),
    (3311, 19),
    (3311, 20),
    (3311, 21),
    (3311, 22),
    (3311, 23),
    (3311, 24),
    (3221, 25),
    (3221, 26),
    (3221, 27),
    (3221, 28),
    (3221, 29),
    (2030, 30),
    (2030, 31),
    (2030, 32),
    (2030, 33),
    (2030, 34),
    (2030, 35),
    (2030, 36),
    (2011, 37),
    (2011, 38),
    (2011, 1),
    (2011, 6),
    (2011, 5);
INSERT INTO study_materials (
    material_id, material_name, topic_id,
    username
)
VALUES
    (
        1, 'youtube playlist', 1, 'ymann@my.yorku.ca'
    ),
    (
        2, 'youtube video', 2, 'ymann@my.yorku.ca'
    ),
    (
        3, 'youtube video', 3, 'aemcruz@my.yorku.ca'
    ),
    (
        4, 'youtube playlist', 4, 'ymann@my.yorku.ca'
    ),
    (
        5, 'youtube playlist', 5, 'ymann@my.yorku.ca'
    ),
    (
        6, 'website', 6, 'ymann@my.yorku.ca'
    ),
    (
        7, 'youtube video', 7, 'aemcruz@my.yorku.ca'
    ),
    (
        8, 'youtube video', 8, 'aemcruz@my.yorku.ca'
    ),
    (
        9, 'website', 9, 'aemcruz@my.yorku.ca'
    ),
    (
        10, 'youtube tutorial', 10, 'ymann@my.yorku.ca'
    ),
    (11, 'youtube video', 11, NULL),
    (12, 'youtube playlist', 12, NULL),
    (13, 'youtube video', 13, NULL),
    (14, 'youtube video', 14, NULL),
    (15, 'youtube video', 15, NULL),
    (16, 'youtube video', 16, NULL),
    (17, 'youtube video', 17, NULL),
    (18, 'playlist', 18, NULL),
    (19, 'playlist', 19, NULL),
    (20, 'playlist', 20, NULL),
    (21, 'youtube video', 21, NULL),
    (22, 'article', 22, NULL),
    (23, 'article', 23, NULL),
    (24, 'article', 24, NULL),
    (25, 'playlist', 25, NULL),
    (26, 'playlist', 26, NULL),
    (27, 'playlist', 27, NULL),
    (28, 'Playlist', 28, NULL),
    (29, 'Video', 29, NULL),
    (30, 'website', 30, NULL),
    (31, 'Website', 31, NULL),
    (32, 'Article', 32, NULL),
    (33, 'Article', 33, NULL),
    (34, 'article', 34, NULL),
    (35, 'Article', 35, NULL),
    (36, 'youtube tutorial', 36, NULL),
    (37, 'youtube video', 37, NULL),
    (38, 'youtube video', 38, NULL);
INSERT INTO study_materials_urls (url_id, material_id, url)
VALUES
    (
        1, 1, 'https://www.youtube.com/playlist?list=PLuZ_bd9XlByzTIP5j1aWXo7smCIxvzd2D'
    ),
    (39,1,'https://www.youtube.com/watch?v=PkJIc5tBRUE'),
    (
        2, 2, 'https://www.youtube.com/watch?v=2Rr2tW9zvRg'
    ),
    (
        3, 3, 'https://www.youtube.com/watch?v=vYquumk4nWw'
    ),
    (
        4, 4, 'https://www.youtube.com/watch?v=HzeK7g8cD0Y&list=PLqM7alHXFySESatj68JKWHRVhoJ1BxtLW'
    ),
    (
        5, 5, 'https://www.youtube.com/watch?v=1n5XPFcvxds&list=PLqM7alHXFySEaZgcg7uRYJFBnYMLti-nh'
    ),
    (
        6, 6, 'https://www.geeksforgeeks.org/understanding-time-complexity-simple-examples/'
    ),
    (
        7, 7, 'https://www.youtube.com/watch?v=wOD02sezmX8'
    ),
    (
        8, 8, 'https://www.youtube.com/watch?v=LpsA0vgu2MI'
    ),
    (
        9, 9, 'https://www.geeksforgeeks.org/relational-model-in-dbms/'
    ),
    (
        10, 10, 'https://www.youtube.com/watch?v=HXV3zeQKqGY&t=6139s'
    ),
    (
        11, 11, 'https://www.youtube.com/watch?v=dR-jJimWWHA&t=166s'
    ),
    (
        12, 12, 'https://www.youtube.com/watch?v=LL_eHNQA6wk&list=PLjHXQcEJRUzaPyPil-6Tp9QWL1g-oxBIi'
    ),
    (
        13, 13, 'https://www.youtube.com/watch?v=oX-IoIB9QCg&t=21s'
    ),
    (
        14, 14, 'https://www.youtube.com/watch?v=i_5ew4btJiQ&t=7s'
    ),
    (
        15, 15, 'https://www.youtube.com/watch?v=vywD6haoKTs&t=679s'
    ),
    (
        16, 16, 'https://www.youtube.com/watch?v=2iPDpV8ojHA&t=3s'
    ),
    (
        17, 17, 'https://www.youtube.com/watch?v=-o3rzPmiQ1w&t=3s'
    ),
    (
        18, 18, 'https://www.youtube.com/watch?v=hZFwL7n6wus&list=PLr3OnN7KdzeiR4zrkbXLQC2xPMAhPhR86'
    ),
    (
        19, 19, 'https://www.youtube.com/watch?v=HLFbeC78YlU&list=PL6n9fhu94yhXjG1w2blMXUzyDrZ_eyOme'
    ),
    (
        20, 20, 'https://www.youtube.com/watch?v=v9ejT8FO-7I&list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc'
    ),
    (
        21, 21, 'https://www.youtube.com/watch?v=vZm0lHciFsQ&t=480s'
    ),
    (
        22, 22, 'https://www.geeksforgeeks.org/difference-between-architectural-style-architectural-patterns-and-design-patterns/'
    ),
    (
        23, 23, 'https://objectcomputing.com/resources/publications/sett/september-2011-design-by-contract-in-java-with-google'
    ),
    (
        24, 24, 'https://www.javatpoint.com/integration-testing'
    ),
    (
        25, 25, 'https://www.youtube.com/watch?v=fvN98a_7AT4&list=PLBlnK6fEyqRgRF-FUWec-0w4yWCurLy1t'
    ),
    (
        26, 26, 'https://www.youtube.com/watch?v=N-eRvBw8ow4&list=PLySKRj7KSLYiSyHYEIjwnULNxZq4eQct2'
    ),
    (
        27, 27, 'https://www.youtube.com/watch?v=ph2awKa8r5Y&list=PLBlnK6fEyqRjDf_dmCEXgl6XjVKDDj0M2'
    ),
    (
        28, 28, 'https://www.youtube.com/watch?v=RhNWpUrO5MQ&list=PL8tc66sMn9Kjt2Wf5H9O-TMqZFQukoCQ1'
    ),
    (
        29, 29, 'https://www.youtube.com/watch?v=DKb7KhfoZmU'
    ),
    (
        30, 30, 'https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/IntroOO.html'
    ),
    (
        31, 31, 'https://www.w3schools.com/java/java_constructors.asp'
    ),
    (
        32, 32, 'https://www.geeksforgeeks.org/inheritance-in-java/'
    ),
    (
        33, 33, 'https://www.geeksforgeeks.org/encapsulation-in-java/'
    ),
    (
        34, 34, 'https://www.geeksforgeeks.org/polymorphism-in-java/'
    ),
    (
        35, 35, 'https://www.geeksforgeeks.org/abstraction-in-java-2/'
    ),
    (
        36, 36, 'https://www.youtube.com/watch?v=Kmgo00avvEw&t=1921s'
    ),
    (
        37, 37, 'https://www.youtube.com/watch?v=6ysjqCUv3K4'
    ),
    (
        38, 38, 'https://www.youtube.com/watch?v=-DzowlcaUmE'
    );
INSERT INTO course_study_materials (course_id, study_material_id)
VALUES
    (3101, 1),
    (3101, 2),
    (3101, 3),
    (3101, 4),
    (3101, 5),
    (3101, 6),
    (3421, 7),
    (3421, 8),
    (3421, 9),
    (3421, 10),
    (3421, 11),
    (3421, 12),
    (3000, 13),
    (3000, 14),
    (3000, 15),
    (3000, 16),
    (3000, 17),
    (3311, 18),
    (3311, 19),
    (3311, 20),
    (3311, 21),
    (3311, 22),
    (3311, 23),
    (3311, 24),
    (3221, 25),
    (3221, 26),
    (3221, 27),
    (3221, 28),
    (3221, 29),
    (2030, 30),
    (2030, 31),
    (2030, 32),
    (2030, 33),
    (2030, 34),
    (2030, 35),
    (2030, 36),
    (2011, 37),
    (2011, 38),
    (2011, 1),
    (2011, 5),
    (2011, 6);
INSERT INTO security_questions
VALUES
    (
        1, 'What is the name of your first pet?'
    ),
    (2, 'What was your first car?'),
    (
        3, 'What is the name of the town where you were born?'
    ),
    (
        4, 'What elementary school did you attend?'
    ),
    (
        5, 'What is your mothers maiden name?'
    );
INSERT INTO user_security_questions
VALUES
    ('ymann@my.yorku.ca', 3, 'India'),
    (
        'kev10th@my.yorku.ca', 3, 'India'
    ),
    (
        'gelailai@my.yorku.ca', 3, 'Toronto'
    ),
    (
        'manasvij@my.yorku.ca', 3, 'Toronto'
    ),
    (
        'aemcruz@my.yorku.ca', 3, 'Toronto'
    );
Insert into Study_materials_ratings values (1,'ymann@my.yorku.ca',4),(1,'manasvij@my.yorku.ca',3),(1,'aemcruz@my.yorku.ca',4),(1,'gelailai@my.yorku.ca',5),(1,'kev10th@my.yorku.ca',2),(2,'ymann@my.yorku.ca',4),(2,'manasvij@my.yorku.ca',5),(2,'aemcruz@my.yorku.ca',4),(2,'gelailai@my.yorku.ca',3),(2,'kev10th@my.yorku.ca',3);
SELECT AVG(rating) AS AverageRating FROM Study_materials_ratings where material_id ='1';


