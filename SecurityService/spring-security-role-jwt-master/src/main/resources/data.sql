-- CREATE DETAULT ROLES FOR RETAIL BANKING SYSTEM
INSERT INTO role (id, description, name) VALUES (1, 'Employee role', 'EMPLOYEE');
INSERT INTO role (id, description, name) VALUES (2, 'Customer role', 'CUSTOMER');

-- CREATE DEFAULT EMPLOYEE with 
-- username : 'pod2@bank.com'
-- password : 12345 
INSERT INTO tbl_user(`id`, `username`, `password`) VALUES (1, 'pod2@bank.com', '$2a$10$13CxbuEZnqoNI5YPXGRIy.z2eUhvhrKumjqLKdq3lUuPkh3Eqtl.W');
INSERT INTO user_roles(`user_id`, `role_id`) VALUES ('1', '1');

