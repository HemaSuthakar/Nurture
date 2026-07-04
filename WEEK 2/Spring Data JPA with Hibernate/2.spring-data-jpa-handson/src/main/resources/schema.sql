DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS country;

-- Hands on 1: Country table used for Query Methods
CREATE TABLE country (
    co_code VARCHAR(2) NOT NULL,
    co_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (co_code)
);

-- Hands on 2: Stock table used for Query Methods
CREATE TABLE stock (
    st_id INT NOT NULL AUTO_INCREMENT,
    st_code VARCHAR(10),
    st_date DATE,
    st_open NUMERIC(10,2),
    st_close NUMERIC(10,2),
    st_volume NUMERIC(15),
    PRIMARY KEY (st_id)
);

-- Hands on 3/4/5/6: Payroll schema (department, employee, skill)
CREATE TABLE department (
    dp_id INT NOT NULL AUTO_INCREMENT,
    dp_name VARCHAR(100),
    PRIMARY KEY (dp_id)
);

CREATE TABLE employee (
    em_id INT NOT NULL AUTO_INCREMENT,
    em_name VARCHAR(100),
    em_salary NUMERIC(10,2),
    em_permanent BOOLEAN,
    em_date_of_birth DATE,
    em_dp_id INT,
    PRIMARY KEY (em_id),
    CONSTRAINT fk_employee_department FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);

CREATE TABLE skill (
    sk_id INT NOT NULL AUTO_INCREMENT,
    sk_name VARCHAR(100),
    PRIMARY KEY (sk_id)
);

CREATE TABLE employee_skill (
    es_em_id INT NOT NULL,
    es_sk_id INT NOT NULL,
    PRIMARY KEY (es_em_id, es_sk_id),
    CONSTRAINT fk_es_employee FOREIGN KEY (es_em_id) REFERENCES employee(em_id),
    CONSTRAINT fk_es_skill FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id)
);
