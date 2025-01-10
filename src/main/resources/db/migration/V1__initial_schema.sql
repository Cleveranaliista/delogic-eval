-- Criação da tabela event
CREATE TABLE event (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id BIGINT,
    city VARCHAR(255),
    event_date DATE
);

-- Criação da tabela ticket
CREATE TABLE ticket (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    sold BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (event_id) REFERENCES event (id)
);
