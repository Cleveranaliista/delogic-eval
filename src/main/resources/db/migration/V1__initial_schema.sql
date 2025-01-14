CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    sports BOOLEAN DEFAULT FALSE,
    theatre BOOLEAN DEFAULT FALSE,
    concerts BOOLEAN DEFAULT FALSE,
    jazz BOOLEAN DEFAULT FALSE,
    classical BOOLEAN DEFAULT FALSE,
    opera BOOLEAN DEFAULT FALSE,
    rock BOOLEAN DEFAULT FALSE,
    vegas BOOLEAN DEFAULT FALSE,
    broadway BOOLEAN DEFAULT FALSE,
    musicals BOOLEAN DEFAULT FALSE
);

CREATE TABLE venues (
    venue_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    venue_name VARCHAR(255) NOT NULL,
    city VARCHAR(255),
    state VARCHAR(255),
    seating_capacity INT
);

CREATE TABLE categories (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_group VARCHAR(255),
    category_name VARCHAR(255) NOT NULL,
    category_description TEXT
);

CREATE TABLE dates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    calendar_date DATE NOT NULL,
    day INT NOT NULL,
    week INT NOT NULL,
    month INT NOT NULL,
    quarter INT NOT NULL,
    year INT NOT NULL,
    holiday_flag BOOLEAN DEFAULT FALSE
);

CREATE TABLE events (
    event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    venue_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    date_id BIGINT NOT NULL,
    event_name VARCHAR(255) NOT NULL,
    event_start_time DATETIME NOT NULL,
    city VARCHAR(255),
    event_date DATE,
    FOREIGN KEY (venue_id) REFERENCES venues(venue_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (date_id) REFERENCES dates(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE listings (
    listing_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seller_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    date_id BIGINT NOT NULL,
    number_of_tickets INT NOT NULL,
    price_per_ticket DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) GENERATED ALWAYS AS (number_of_tickets * price_per_ticket) STORED,
    listing_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (seller_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (date_id) REFERENCES dates(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE sales (
    sale_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    listing_id BIGINT NOT NULL,
    seller_id BIGINT NOT NULL,
    buyer_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    date_id BIGINT NOT NULL,
    quantity_sold INT NOT NULL,
    price_paid DECIMAL(10,2) NOT NULL,
    commission_amount DECIMAL(10,2) AS (price_paid * 0.10) STORED,
    sale_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (listing_id) REFERENCES listings(listing_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (seller_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (buyer_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (date_id) REFERENCES dates(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tickets (
    ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    name VARCHAR(255),
    sold BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX idx_event_id ON tickets(event_id);
CREATE INDEX idx_listing_event_date ON listings(event_id, date_id);
CREATE INDEX idx_sale_event_date ON sales(event_id, date_id);
