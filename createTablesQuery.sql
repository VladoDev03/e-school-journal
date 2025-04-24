CREATE DATABASE e_school_journal;

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

CREATE TABLE teaching (
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

CREATE TABLE studying (
    studying_id INT PRIMARY KEY AUTO_INCREMENT,
    teaching_id INT,
    students_idstudents INT,
    FOREIGN KEY (teaching_id) REFERENCES teaching(id),
    FOREIGN KEY (students_idstudents) REFERENCES student(id)
);

CREATE TABLE mark (
    id INT PRIMARY KEY AUTO_INCREMENT,
    mark DOUBLE,
    mark_type ENUM('Pending', 'Approved', 'Rejected'),
    studying_id INT,
    FOREIGN KEY (studying_id) REFERENCES studying(studying_id)
);

CREATE TABLE absence (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    absence_type ENUM('Excused', 'Unexcused'),
    studying_id INT,
    FOREIGN KEY (studying_id) REFERENCES studying(studying_id)
);