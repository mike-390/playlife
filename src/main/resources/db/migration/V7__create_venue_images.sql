
CREATE TABLE IF NOT EXISTS venue_images (
                                        id UUID NOT NULL PRIMARY KEY,
                                        venue_id UUID NOT NULL,
                                        image_url VARCHAR(255) NOT NULL,
                                        is_cover BOOLEAN NOT NULL DEFAULT FALSE,
                                        CONSTRAINT fk_venue_images_venue FOREIGN KEY (venue_id) REFERENCES venues(id)
    );

CREATE TABLE IF NOT EXISTS court_images (
                                        id UUID NOT NULL PRIMARY KEY,
                                        court_id UUID NOT NULL,
                                        image_url VARCHAR(255) NOT NULL,
                                        is_main BOOLEAN NOT NULL DEFAULT FALSE,
                                        CONSTRAINT fk_court_images_court FOREIGN KEY (court_id) REFERENCES courts(id)
    );