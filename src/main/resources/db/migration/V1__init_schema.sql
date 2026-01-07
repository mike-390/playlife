
CREATE TABLE venues (
                        id UUID NOT NULL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255),
                        phone_number VARCHAR(50)
);

CREATE TABLE courts (
                        id UUID NOT NULL PRIMARY KEY,
                        venue_id UUID NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        sport_type VARCHAR(50) NOT NULL,
                        has_lights BOOLEAN DEFAULT FALSE,
                        price_per_hour NUMERIC(10, 2),
                        CONSTRAINT fk_venue FOREIGN KEY (venue_id) REFERENCES venues(id)
);

CREATE TABLE bookings (
                          id UUID NOT NULL PRIMARY KEY,
                          court_id UUID NOT NULL,
                          user_id UUID,
                          start_time TIMESTAMP NOT NULL,
                          end_time TIMESTAMP NOT NULL,
                          status VARCHAR(50) NOT NULL,
                          total_price NUMERIC(10, 2),
                          CONSTRAINT fk_court FOREIGN KEY (court_id) REFERENCES courts(id)
);