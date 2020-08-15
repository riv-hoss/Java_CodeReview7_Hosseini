SELECT students.id as student_id, concat(students.name," ", students.surname) as student_name,
        classes.id as class_id, classes.name as class_name,
        enrollments.created_at as date_enrolled
FROM students
JOIN enrollments
    ON students.id = enrollments.student_id
JOIN classes
    ON enrollments.ongoing_class_id = classes.id
    WHERE enrollments.ongoing_class_id = 1;



SELECT students.id as student_id, concat(students.name," ", students.surname) as student_name,
        classes.id as class_id, classes.name as class_name,
        enrollments.created_at as date_enrolled
FROM students
JOIN enrollments
    ON students.id = enrollments.student_id
JOIN classes
    ON enrollments.ongoing_class_id = classes.id
    WHERE classes.name = "Algeb";