Create database CR7_Hosseini;
use CR7_Hosseini;

create table teachers(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
    );


create table students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
    );

create table classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


INSERT INTO teachers (name, surname, email)
    VALUES
        ("Andrew", "Mg", "a.mg@tim.com"),
        ("Sam", "Zhen", "s.zhen@coco.com"),
        ("Tom", "Softy", "tom.s@supernat.com"),
        ("Riv", "Rave", "rivrave@gravel.com");

INSERT INTO classes (name)
    VALUES
        ("Algeb"),
        ("Signal Processing"),
        ("DSP"),
        ("Prob");

INSERT INTO students (name, surname, email)
    VALUES
        ("Andy", "Nightmare", "a.mare@tl.edu.com"),
        ("Clint", "Eastbush", "clint.e@tl.edu.com"),
        ("Michael", "Jackdaughter", "m.daughter@beatit.edu.com"),
        ("Jason", "Stone", "j.s@albe.edu.com"),
        ("Walter", "White", "walt.w@albe.edu.com"),
        ("Jessy", "Berg", "jess.berg@albe.edu.com"),
        ("Tim", "coke", "timc12@applet.edu.com"),
        ("sam", "singh", "ssingh@applet.edu.com"),
        ("Elon", "Mask", "emask@teslax.edu.com"),
        ("Zhu", "Xin", "z.xin@teslax.edu.com");

## connects teachers to several courses
create table ongoing_classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT NOT NULL,
    teacher_id INT NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(class_id) REFERENCES classes(id),
    FOREIGN KEY(teacher_id) REFERENCES teachers(id)
);

create table enrollments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    ongoing_class_id INT NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (ongoing_class_id) REFERENCES ongoing_classes(id)
);




INSERT INTO ongoing_classes (class_id, teacher_id)
    VALUES
        (1,2),
        (2,3),
        (3,3);

INSERT INTO enrollments (student_id, ongoing_class_id)
    VALUES
        (1,1),(1,2),(1,3),(2,1),
        (2,2),(3,3),(9,1),(9,3),
        (10,1), (10,2), (6,1),
        (6,3), (7,1), (8,3), (4,2);