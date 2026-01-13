
ALTER TABLE bookings
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE bookings
    ALTER COLUMN created_at SET NOT NULL;

ALTER TABLE bookings
    ADD CONSTRAINT fk_bookings_user
        FOREIGN KEY (user_id)
            REFERENCES users(id);