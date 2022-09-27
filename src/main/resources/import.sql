-- DADOS DE USUÁRIOS
INSERT INTO USUARIOS values (100, 39, 'FLAVIO SANTANA');
INSERT INTO USUARIOS values (200, 1, 'CLARICE SANTANA');

-- DADOS DE POSTS
INSERT INTO PUBLICACOES values (101, CURRENT_TIMESTAMP, 'Cloudflare lança eSIM para proteger dados e diminuir riscos de golpes', 100);
INSERT INTO PUBLICACOES values (102, CURRENT_TIMESTAMP, 'Sony lança novo PS5 com AMD de 6 nm para consumir menos e não avisa ninguém', 100);
INSERT INTO PUBLICACOES values (201, CURRENT_TIMESTAMP, 'Índia quer emplacar novo GPS, mas Samsung, Apple e Xiaomi não estão felizes', 200);
INSERT INTO PUBLICACOES values (202, CURRENT_TIMESTAMP, 'Galaxy A53 deve receber Android 13 com One UI 5 antes mesmo de 2023', 200);

-- DADOS DE SEGUIDORES
INSERT INTO SEGUIDORES values (103, 100, 200);
INSERT INTO SEGUIDORES values (203, 200, 100);
