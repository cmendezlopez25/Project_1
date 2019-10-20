insert into users(id, username, password, firstname, lastname, role, supervisor)
values(2, 'supervisor', 'password', 'Super', 'Visor', 'DEPARTMENT_HEAD', NULL);

INSERT INTO users(id, username, password, firstname, lastname, role, supervisor) 
values(1, 'testuser', 'password', 'Test', 'User', 'EMPLOYEE', 'supervisor');

select id, type, amount, status, datecreated, datelastmodified from reimbursement where "owner" in (select username from users where supervisor = 'supervisor');