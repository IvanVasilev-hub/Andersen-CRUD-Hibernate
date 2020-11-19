CREATE TABLE IF NOT EXISTS users
(
    user_id    INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    role TEXT NOT NULL
);

CREATE table if not exists users_roles
(
    user_id int not null references users(user_id),
    role_id int not null references roles(role_id),
    constraint users_roles_pk primary key (user_id, role_id)
);
