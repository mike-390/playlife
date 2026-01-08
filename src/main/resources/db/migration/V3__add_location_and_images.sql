ALTER TABLE venues
ADD COLUMN latitude DOUBLE PRECISION,
ADD COLUMN longitude DOUBLE PRECISION;

CREATE TABLE court_images (
                              id UUID NOT NULL PRIMARY KEY,
                              court_id UUID NOT NULL,
                              image_url VARCHAR(500) NOT NULL,
                              is_main BOOLEAN DEFAULT FALSE,
                              CONSTRAINT fk_court_image FOREIGN KEY (court_id) REFERENCES courts(id)
);