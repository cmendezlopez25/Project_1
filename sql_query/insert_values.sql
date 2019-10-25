insert into users(id, username, password, firstname, lastname, role, supervisor)
values(2, 'supervisor', 'password', 'Super', 'Visor', 'DEPARTMENT_HEAD', NULL);

INSERT INTO users(id, username, password, firstname, lastname, role, supervisor) 
values(1, 'testuser', 'password', 'Test', 'User', 'EMPLOYEE', 'supervisor');

select id, type, amount, status, datecreated, datelastmodified from reimbursement where "owner" in (select username from users where supervisor = 'supervisor');

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner, message, passgrade, dateevent)
values('1', 'UNIVERSITY_COURSES', 200.00, 'PEND_DS', current_date, current_date, 'testuser', 'Science class', 80, current_date);

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner, message, passgrade, dateevent)
values('2', 'SEMINARS', 200.00, 'PEND_DS', current_date, current_date, 'testuser', 'Angular Debacle', null, current_date);

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner, message, passgrade, dateevent)
values('3', 'CERTIFICATION_PREPARATION_CLASSES', 200.00, 'PEND_DS', current_date, current_date, 'testuser', 'Almost Java class', 60, current_date);

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner, message, passgrade, dateevent)
values('4', 'CERTIFICATION', 200.00, 'PEND_DS', current_date, current_date, 'testuser', 'Java OCA', 65, current_date);

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner, message, passgrade, dateevent)
values('5', 'TECHNICAL_TRAINING', 200.00, 'PEND_DS', current_date, current_date, 'testuser', 'Computer Hardware', 50, current_date);

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner, message, passgrade, dateevent)
values('6', 'OTHER', 200.00, 'PEND_DS', current_date, current_date, 'testuser', 'Something Useful', null, current_date);

insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, owner, message, passgrade, dateevent)
values('7', 'CERTIFICATION', 500.00, 'PEND_DS', current_date, current_date, 'supervisor', 'Another thing', 70, current_date);