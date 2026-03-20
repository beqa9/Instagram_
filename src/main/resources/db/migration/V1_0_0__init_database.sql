CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       profile_image_url TEXT,
                       bio TEXT,
                       is_deleted BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT NOW(),
                       updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE posts (
                       id BIGSERIAL PRIMARY KEY,
                       user_id BIGINT REFERENCES users(id),
                       image_url TEXT NOT NULL,
                       caption TEXT,
                       is_deleted BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE likes (
                       id BIGSERIAL PRIMARY KEY,
                       user_id BIGINT REFERENCES users(id),
                       post_id BIGINT REFERENCES posts(id),
                       is_deleted BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT NOW(),
                       UNIQUE(user_id,post_id)
);

CREATE TABLE comments (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT REFERENCES users(id),
                          post_id BIGINT REFERENCES posts(id),
                          parent_id BIGINT REFERENCES comments(id),
                          text TEXT,
                          is_deleted BOOLEAN DEFAULT FALSE,
                          created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE follows (
                         id BIGSERIAL PRIMARY KEY,
                         follower_id BIGINT REFERENCES users(id),
                         following_id BIGINT REFERENCES users(id),
                         created_at TIMESTAMP DEFAULT NOW(),
                         UNIQUE(follower_id,following_id)
);