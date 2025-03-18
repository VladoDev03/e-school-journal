use e_school_journal;

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    username VARCHAR(45) UNIQUE
);

CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_iduser INT,
    FOREIGN KEY (user_iduser) REFERENCES user(id)
);

CREATE TABLE school (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45),
    address VARCHAR(45)
);

CREATE TABLE director (
    id INT PRIMARY KEY AUTO_INCREMENT,
    school_idschool INT,
    user_iduser INT,
    FOREIGN KEY (school_idschool) REFERENCES school(id),
    FOREIGN KEY (user_iduser) REFERENCES user(id)
);

CREATE TABLE grade (
    id INT PRIMARY KEY AUTO_INCREMENT,
    grade INT,
    stream VARCHAR(1),
    school_idschool INT,
    FOREIGN KEY (school_idschool) REFERENCES school(id)
);

CREATE TABLE subject (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45)
);

CREATE TABLE teacher (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45),
    user_iduser INT,
    FOREIGN KEY (user_iduser) REFERENCES user(id)
);

CREATE TABLE grade_has_subject (
    id INT PRIMARY KEY AUTO_INCREMENT,
    grade_grade_id INT,
    subject_idsubject INT,
    teacher_idteacher INT,
    FOREIGN KEY (grade_grade_id) REFERENCES grade(id),
    FOREIGN KEY (subject_idsubject) REFERENCES subject(id),
    FOREIGN KEY (teacher_idteacher) REFERENCES teacher(id)
);

CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_iduser INT,
    FOREIGN KEY (user_iduser) REFERENCES user(id)
);

CREATE TABLE parent (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_iduser INT,
    FOREIGN KEY (user_iduser) REFERENCES user(id)
);

CREATE TABLE students_has_parent (
    students_idstudents INT,
    parent_idparent INT,
    PRIMARY KEY (students_idstudents, parent_idparent),
    FOREIGN KEY (students_idstudents) REFERENCES student(id),
    FOREIGN KEY (parent_idparent) REFERENCES parent(id)
);

CREATE TABLE student_studies_subject (
    student_studies_subject_id INT PRIMARY KEY AUTO_INCREMENT,
    grade_has_subject_id INT,
    students_idstudents INT,
    FOREIGN KEY (grade_has_subject_id) REFERENCES grade_has_subject(id),
    FOREIGN KEY (students_idstudents) REFERENCES student(id)
);

CREATE TABLE mark (
    id INT PRIMARY KEY AUTO_INCREMENT,
    mark DOUBLE,
    mark_type ENUM('Pending', 'Approved', 'Rejected'),
    student_studies_subject_id INT,
    FOREIGN KEY (student_studies_subject_id) REFERENCES student_studies_subject(student_studies_subject_id)
);

CREATE TABLE absence (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    absence_type ENUM('Excused', 'Unexcused'),
    student_studies_subject_id INT,
    FOREIGN KEY (student_studies_subject_id) REFERENCES student_studies_subject(student_studies_subject_id)
);