use e_school_journal;

CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    keycloak_id VARCHAR(45)
);

CREATE TABLE school (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45),
    address VARCHAR(45)
);

CREATE TABLE director (
    id INT PRIMARY KEY AUTO_INCREMENT,
    school_id INT,
    keycloak_id VARCHAR(45),
    FOREIGN KEY (school_id) REFERENCES school(id)
);

CREATE TABLE grade (
    id INT PRIMARY KEY AUTO_INCREMENT,
    grade INT,
    stream VARCHAR(1),
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES school(id)
);

CREATE TABLE subject (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45)
);

CREATE TABLE teacher (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45),
    keycloak_id VARCHAR(45)
);

CREATE TABLE teaching (
    id INT PRIMARY KEY AUTO_INCREMENT,
    grade_id INT,
    subject_id INT,
    teacher_id INT,
    FOREIGN KEY (grade_id) REFERENCES grade(id),
    FOREIGN KEY (subject_id) REFERENCES subject(id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);

CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
keycloak_id VARCHAR(45)
);

CREATE TABLE parent (
    id INT PRIMARY KEY AUTO_INCREMENT,
keycloak_id VARCHAR(45)
);

CREATE TABLE students_has_parent (
    student_id INT,
    parent_id INT,
    PRIMARY KEY (student_id, parent_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (parent_id) REFERENCES parent(id)
);

CREATE TABLE studying (
    id INT PRIMARY KEY AUTO_INCREMENT,
    teaching_id INT,
    student_id INT,
    FOREIGN KEY (teaching_id) REFERENCES teaching(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE TABLE mark (
    id INT PRIMARY KEY AUTO_INCREMENT,
    mark DOUBLE,
    mark_type ENUM('Pending', 'Approved', 'Rejected'),
    studying_id INT,
    FOREIGN KEY (studying_id) REFERENCES studying(id)
);

CREATE TABLE absence (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    absence_type ENUM('Excused', 'Unexcused'),
    studying_id INT,
    FOREIGN KEY (studying_id) REFERENCES studying(id)
);