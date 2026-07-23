-- =========================================================
-- Departments
-- =========================================================
INSERT INTO department (dp_id, dp_name) VALUES (1, 'Engineering');
INSERT INTO department (dp_id, dp_name) VALUES (2, 'Human Resources');
INSERT INTO department (dp_id, dp_name) VALUES (3, 'Sales');

-- =========================================================
-- Skills
-- =========================================================
INSERT INTO skill (sk_id, sk_name) VALUES (1, 'Java');
INSERT INTO skill (sk_id, sk_name) VALUES (2, 'Spring Boot');
INSERT INTO skill (sk_id, sk_name) VALUES (3, 'SQL');
INSERT INTO skill (sk_id, sk_name) VALUES (4, 'Communication');
INSERT INTO skill (sk_id, sk_name) VALUES (5, 'Negotiation');

-- =========================================================
-- Employees
-- =========================================================
INSERT INTO employee (em_id, em_name, em_date_of_birth, em_salary, em_permanent, em_dp_id)
VALUES (1, 'Alice Johnson', '1990-04-12', 85000.00, true, 1);
INSERT INTO employee (em_id, em_name, em_date_of_birth, em_salary, em_permanent, em_dp_id)
VALUES (2, 'Bob Smith', '1988-11-02', 92000.00, true, 1);
INSERT INTO employee (em_id, em_name, em_date_of_birth, em_salary, em_permanent, em_dp_id)
VALUES (3, 'Carol Davis', '1995-06-23', 60000.00, false, 2);
INSERT INTO employee (em_id, em_name, em_date_of_birth, em_salary, em_permanent, em_dp_id)
VALUES (4, 'David Lee', '1992-01-30', 78000.00, true, 3);
INSERT INTO employee (em_id, em_name, em_date_of_birth, em_salary, em_permanent, em_dp_id)
VALUES (5, 'Eve Turner', '1985-09-14', 55000.00, false, 3);

-- Employee <-> Skill (many-to-many)
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 2);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 3);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 4);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 5);

-- =========================================================
-- Quiz schema (Hands-on 3)
-- One user, one attempt, four questions - matches the sample
-- attempt shown in the hands-on document.
-- =========================================================
INSERT INTO app_user (id, username) VALUES (1, 'jane.doe');

INSERT INTO question (id, question_text) VALUES (1, 'What is the extension of the hyper text markup language file?');
INSERT INTO question (id, question_text) VALUES (2, 'What is the maximum level of heading tag can be used in a HTML page?');
INSERT INTO question (id, question_text) VALUES (3, 'The HTML document itself begins with <html> and ends </html>. State True or False');
INSERT INTO question (id, question_text) VALUES (4, 'Choose the right option to store text value in a variable');

-- Question 1 options
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (1, '.xhtm', 0.0, 1);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (2, '.ht', 0.0, 1);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (3, '.html', 1.0, 1);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (4, '.htmx', 0.0, 1);

-- Question 2 options
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (5, '5', 0.0, 2);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (6, '3', 0.0, 2);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (7, '4', 0.0, 2);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (8, '6', 1.0, 2);

-- Question 3 options
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (9, 'false', 0.0, 3);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (10, 'true', 1.0, 3);

-- Question 4 options
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (11, '''John''', 0.5, 4);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (12, 'John', 0.0, 4);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (13, '"John"', 0.5, 4);
INSERT INTO quiz_option (id, option_text, score, question_id) VALUES (14, '/John/', 0.0, 4);

-- One attempt by user 1
INSERT INTO attempt (id, user_id, attempt_date) VALUES (1, 1, '2026-07-01 10:15:00');

-- Attempt questions (one row per question in this attempt)
INSERT INTO attempt_question (id, attempt_id, question_id) VALUES (1, 1, 1);
INSERT INTO attempt_question (id, attempt_id, question_id) VALUES (2, 1, 2);
INSERT INTO attempt_question (id, attempt_id, question_id) VALUES (3, 1, 3);
INSERT INTO attempt_question (id, attempt_id, question_id) VALUES (4, 1, 4);

-- Attempt options: every option shown to the user for that question,
-- with "selected" marking which one the user actually picked.
-- Question 1 (user picked option 3 -> .html, correct)
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (1, 1, 1, false);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (2, 1, 2, false);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (3, 1, 3, true);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (4, 1, 4, false);

-- Question 2 (user picked option 6 -> 3, incorrect; correct answer was option 8 -> 6)
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (5, 2, 5, false);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (6, 2, 6, true);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (7, 2, 7, false);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (8, 2, 8, false);

-- Question 3 (user picked option 10 -> true, correct)
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (9, 3, 9, false);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (10, 3, 10, true);

-- Question 4 (user picked option 11 -> 'John', partially correct)
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (11, 4, 11, true);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (12, 4, 12, false);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (13, 4, 13, false);
INSERT INTO attempt_option (id, attempt_question_id, option_id, selected) VALUES (14, 4, 14, false);
