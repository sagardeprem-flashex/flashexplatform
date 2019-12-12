INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(email, name, password, username) VALUES('admin@admin.com', 'admin','$2a$10$YUqQFfcUSeSdz411LJECRucvefGA26BqpUHTe3Qm52JzjZFhxuS8i','Darshan');

INSERT INTO user_roles(user_id, role_id) VALUES('1', '2');





