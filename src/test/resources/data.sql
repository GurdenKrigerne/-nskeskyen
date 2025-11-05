-- ---------------------------------------------------
-- Indsæt brugere
-- ---------------------------------------------------
INSERT INTO Users (username, email, password_hash) VALUES
                                                       ('mads', 'mads@example.com', 'hashedpassword123'),
                                                       ('sofie', 'sofie@example.com', 'hashedpassword456'),
                                                       ('emil', 'emil@example.com', 'hashedpassword789');

-- ---------------------------------------------------
-- Indsæt ønskelister
-- ---------------------------------------------------
INSERT INTO Wishlist (name, description, owner_id) VALUES
                                                       ('Mads juleønsker', 'Mine ønsker til julen 2025', 1),
                                                       ('Sofies fødselsdagsønsker', 'Ønsker til min 25-års fødselsdag', 2),
                                                       ('Rejseudstyr', 'Alt hvad jeg skal bruge til min sommerrejse', 3);

-- ---------------------------------------------------
-- Indsæt ønsker
-- ---------------------------------------------------
INSERT INTO Wish (title, description, price, url) VALUES
                                                      ('AirPods Pro', 'Trådløse høretelefoner fra Apple', 1999.00, 'https://apple.com/airpods-pro'),
                                                      ('Hoptimist', 'En klassisk dansk designfigur', 249.95, 'https://hoptimist.dk'),
                                                      ('Samsonite kuffert', 'Stor hardcase rejsekuffert', 1200.00, 'https://samsonite.dk'),
                                                      ('Harry Potter bogsæt', 'Komplet samling på 7 bøger', 799.00, 'https://bogogide.dk'),
                                                      ('GoPro Hero 12', 'Actionkamera til eventyr', 3499.00, 'https://gopro.com');

-- ---------------------------------------------------
-- Knyt ønsker til ønskelister
-- ---------------------------------------------------
INSERT INTO Wishlist_Wish (wishlist_id, wish_id) VALUES
                                                     (1, 1),
                                                     (1, 2),
                                                     (2, 4),
                                                     (3, 3),
                                                     (3, 5);

-- ---------------------------------------------------
-- Giv adgangsroller til brugere
-- ---------------------------------------------------
INSERT INTO Wishlist_User (wishlist_id, user_id, role) VALUES
                                                           (1, 1, 'owner'),
                                                           (2, 2, 'owner'),
                                                           (3, 3, 'owner'),
                                                           (1, 2, 'viewer'),
                                                           (2, 1, 'editor');




