ALTER TABLE usuario ADD COLUMN role VARCHAR(50) NOT NULL DEFAULT 'USER';
UPDATE usuario SET role = 'ADMIN' WHERE login = 'admin';
