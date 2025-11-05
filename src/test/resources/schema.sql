CREATE TABLE Users (
                       user_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Wishlist (
                          wishlist_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          owner_id INT UNSIGNED NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (owner_id) REFERENCES Users(user_id)
);

CREATE TABLE Wish (
                      wish_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(150) NOT NULL,
                      description TEXT,
                      price DECIMAL(10,2),
                      url VARCHAR(255),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Wishlist_User (
                               wishlist_id INT UNSIGNED NOT NULL,
                               user_id INT UNSIGNED NOT NULL,
                               role VARCHAR(50) DEFAULT 'viewer',
                               PRIMARY KEY (wishlist_id, user_id),
                               FOREIGN KEY (wishlist_id) REFERENCES Wishlist(wishlist_id) ON DELETE CASCADE,
                               FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE Wishlist_Wish (
                               wishlist_id INT UNSIGNED NOT NULL,
                               wish_id INT UNSIGNED NOT NULL,
                               PRIMARY KEY (wishlist_id, wish_id),
                               FOREIGN KEY (wishlist_id) REFERENCES Wishlist(wishlist_id) ON DELETE CASCADE,
                               FOREIGN KEY (wish_id) REFERENCES Wish(wish_id) ON DELETE CASCADE
);