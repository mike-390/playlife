
CREATE TABLE users (
                       id UUID NOT NULL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,
                       phone_number VARCHAR(50),
                       role VARCHAR(50) NOT NULL,          -- ADMIN, PLAYER, OWNER
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE bookings
ADD CONSTRAINT fk_booking_user
FOREIGN KEY (user_id)
REFERENCES users(id);