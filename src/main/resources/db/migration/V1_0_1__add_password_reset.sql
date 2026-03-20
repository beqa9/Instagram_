CREATE TABLE password_reset_tokens (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);