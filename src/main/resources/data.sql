INSERT INTO animes (name, author, genre, num_seasons, creation_year, is_ongoing)
VALUES
    ('Naruto', 'Masashi Kishimoto', 'Shonen', 5, '2002-10-03', true),
    ('Attack on Titan', 'Hajime Isayama', 'Action', 4, '2009-09-09', false),
    ('One Piece', 'Eiichiro Oda', 'Adventure', 10, '1999-10-20', true);


INSERT INTO users (name, gender, username, password)
VALUES
    ('Caio', 0, 'caio', '$2a$12$4LRuzjScArso6Bcw45RdX.izjw1/d1ASnhDk8kuzvvhikv.VDrCf2'),
    ('Ana', 1, 'ana', '$2a$12$lflGp70sQnDR/TIr6KuNPucsFnjITUyAJGcXFXdx4CTtOX5Dqg1dC');


INSERT INTO roles (name)
VALUES('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
(2,2);