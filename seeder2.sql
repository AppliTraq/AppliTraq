INSERT INTO applitraq_db.users (age, email, gender, location, password, username)
    VALUES (25, 'user@mail.com', 'Female', 'Los Angeles, CA', '$2a$10$WD2g7r4fh
.45PPylOp7puuDIimUZej1IJks1vXNrACLI7EPElxAVK', 'user');

INSERT INTO applitraq_db.job_applications (company, description, location, salary, title, user_id)
    VALUES ('Google', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has
been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 'Las Angeles, CA', 75000, 'Software Developer', 2),
    ('Apple', 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of
classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.', 'San Antonio, TX', 70000, 'Software Engineer', 2),
    ('Netflix', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered
alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.', 'Austin, TX', 80000, 'Web Developer', 2),
    ('Amazon', 'It is a long established fact that a reader will be distracted by the readable content of a page when
looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', 'Huntington, CA', 90000, 'Software Developer II', 2),
    ('Hulu', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 'Las Angeles, CA', 75000, 'Software Developer', 2),
    ('Microsoft', 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of
classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.', 'San Antonio, TX', 65000, 'Software Engineer II', 2),
    ('Home Depot', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered
alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.', 'Austin, TX', 80000, 'FullStack Web Developer', 2),
    ('Nike', 'It is a long established fact that a reader will be distracted by the readable content of a page when
looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', 'Huntington, CA', 90000, 'Frontend Developer', 2);

INSERT INTO applitraq_db.notes (id, content, date, title, job_id)
VALUES (1, 'Do take home test 2', ('2021-02-24'), 'Test', 10),
       (2, 'Do take home test', ('2021-03-05'), 'Test', 11),
       (3, 'John Smith 213-456-7890', ('2021-03-06'), 'Contact', 12),
       (4, 'Do take home test', ('2021-04-20'), 'Test', 13),
       (5, 'John Smith', ('2021-05-23'), 'Contact', 14),
       (6, 'Michelle Smith', ('2021-05-25'), 'Manager', 15),
       (7, 'Do take home test', ('2021-06-06'), 'Test', 16),
       (8, 'John Smith', ('2021-07-15'), 'Contact', 17),
       (9, 'Do take home test', ('2021-07-18'), 'Test', 18);

INSERT INTO applitraq_db.reminders (description, title, job_id)
    VALUES ('Do take home test', 'Reminder 3', 10),
    ('Do take home test', 'Test', 12),
    ('John Smith 213-456-7890', 'Reminder 3', 13),
    ('Do take home test', 'Reminder 1', 14),
    ('John Smith', 'Contact', 15),
    ('Michelle Smith', 'Manager', 16),
    ('Do take home test', 'Reminder 1',17),
    ('John Smith', 'Contact', 18);

INSERT INTO applitraq_db.timeline (timeline_id, date, kanban_status, job_applications)
VALUES (16, '2021-02-24'), 4, 11),
       (17, '2021-03-05', 2, 12),
       (18, '2021-03-06'), 3, 13),
       (19, '2021-04-20'), 3, 14),
       (20, '2021-05-23'), 3, 15),
       (21, '2021-05-25'), 3, 16),
       (22, '2021-06-06'), 3, 17),
       (23, '2021-07-15'), 4, 18);