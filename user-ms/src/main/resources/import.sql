INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(email, name, password, username) VALUES('admin@admin.com', 'admin','$2a$10$2lK4/LpzvEzSAZn4PT0xiOrB5O/NTur.MQ8qbiZeyowkQuQ2czoXy','admin1');

INSERT INTO user_roles(user_id, role_id) VALUES('1', '3');





