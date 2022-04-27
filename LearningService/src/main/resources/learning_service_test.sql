insert into students (registered, first_name, surname, email, email_notification) values
    ('2022-02-28 08:23:31', 'John', 'Smith', 'john.smith@abc.com', 0),
    ('2022-04-04 19:09:01', 'Jane', 'Eastwood', 'jane.eastwood@qwerty.com', 1);

insert into courses (title, description, study_time) values
    ('JavaScript for Beginners', 'The basics of JavaScript: language syntax, GUI building in the browser, Node.js runtime environment, practice projects.', 160),
    ('Algorithms for Competitive Programming', 'Common algorithms and data structures for programming contests. Main topics: arrays, lists, maps, trees, graphs, strings, mathematical algorithms, dynamic programming.', 320),
    ('Python Fundamentals', 'Python practice projects.', 40),
    ('Java Concurrency', 'Multithreading, concurrency and parallelism in Java.', 20);

insert into student_courses (student_id, course_id, registered, completed) values
    (1, 1, now(), null),
    (2, 2, '2022-01-03 09:09:22', '2022-03-15 15:12:21'),
    (2, 3, '2021-09-01 22:22:22', '2021-10-31 06:32:19'),
    (2, 4, '2022-03-15 16:02:05', null);






