CREATE TABLE reviews (
                         id UUID NOT NULL PRIMARY KEY,
                         user_id UUID NOT NULL,
                         venue_id UUID NOT NULL,
                         rating INTEGER NOT NULL,
                         comment VARCHAR(1000),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES users(id),
                         CONSTRAINT fk_review_venue FOREIGN KEY (venue_id) REFERENCES venues(id),
                         CONSTRAINT check_rating_range CHECK (rating >= 1 AND rating <= 5)
);