insert into users(id, username, password, firstname, lastname, role, supervisor)
values(2, 'supervisor', 'password', 'Super', 'Visor', 'DEPARTMENT_HEAD', NULL);

INSERT INTO users(id, username, password, firstname, lastname, role, supervisor) 
values(1, 'testuser', 'password', 'Test', 'User', 'EMPLOYEE', 'supervisor');

select id, type, amount, status, datecreated, datelastmodified from reimbursement where "owner" in (select username from users where supervisor = 'supervisor');

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner)
values('1', 'UNIVERSITY_COURSES', 200.00, 'PEND_DS', current_date, current_date, 'testuser');

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner)
values('2', 'SEMINARS', 200.00, 'PEND_DS', current_date, current_date, 'testuser');

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner)
values('3', 'CERTIFICATION_PREPARATION_CLASSES', 200.00, 'PEND_DS', current_date, current_date, 'testuser');

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner)
values('4', 'CERTIFICATION', 200.00, 'PEND_DS', current_date, current_date, 'testuser');

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner)
values('5', 'TECHNICAL_TRAINING', 200.00, 'PEND_DS', current_date, current_date, 'testuser');

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner)
values('6', 'OTHER', 200.00, 'PEND_DS', current_date, current_date, 'testuser');