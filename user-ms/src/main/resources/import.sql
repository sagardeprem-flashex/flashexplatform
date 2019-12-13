INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(email, name, password, username) VALUES('admin@admin.com', 'admin','$2a$10$rz9L3HNq4i3m3/U63er9..AtqT7tRy6BcrD6Zr4kbA2W4z8PEg69O','Darshan');
INSERT INTO users(email, name, password, username) VALUES('parth@admin.com', 'Parth','$2a$10$97KIfZRkyCGmpNCr.jssROPCOUEyKwHhUSgVJVWeDofUNMc60hPIK','parth');
INSERT INTO users(email, name, password, username) VALUES('anup@admin.com', 'Anup','$2a$10$HxW8qDoSrlnElQbVUz92juMMAP3JWjWGCNQCglX7oKW3VW1LlB6b.','anup');
INSERT INTO users(email, name, password, username) VALUES('anurag@admin.com', 'Anurag','$2a$10$fKyD8npQN8dDPP34Mo.IYexYyK7SWKxqx/Gqew.9LvfK/0AK3ZoNS','anurag');

INSERT INTO user_roles(user_id, role_id) VALUES('1', '2');
INSERT INTO user_roles(user_id, role_id) VALUES('2', '1');
INSERT INTO user_roles(user_id, role_id) VALUES('3', '1');
INSERT INTO user_roles(user_id, role_id) VALUES('4', '1');





