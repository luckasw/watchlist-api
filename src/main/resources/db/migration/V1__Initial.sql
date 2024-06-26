CREATE TABLE IF NOT EXISTS users (
                                              id SERIAL PRIMARY KEY,
                                              username VARCHAR(255) NOT NULL,
                                              password VARCHAR(255) NOT NULL,
                                              email VARCHAR(255) NOT NULL,
                                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS movies (
                                               id SERIAL PRIMARY KEY,
                                               title VARCHAR(255) NOT NULL,
                                               description TEXT,
                                               release_date DATE NOT NULL,
                                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS watchlist (
                                                  id SERIAL PRIMARY KEY,
                                                  user_id INT NOT NULL,
                                                  movie_id INT NOT NULL,
                                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                  FOREIGN KEY (user_id) REFERENCES users(id),
                                                  FOREIGN KEY (movie_id) REFERENCES movies(id)
);