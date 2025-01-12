-- Popula a tabela Categories
INSERT INTO categories (category_id, category_name, category_group)
VALUES
    (10, 'Music Festival', 'Music'),
    (11, 'Jazz Events', 'Music'),
    (12, 'Classical Music', 'Music');

-- Popula a tabela Venues
INSERT INTO venues (venue_id, venue_name, city, state, seating_capacity)
VALUES
    (1, 'São Paulo Arena', 'São Paulo', 'SP', 50000),
    (2, 'Rio Jazz Club', 'Rio de Janeiro', 'RJ', 2000),
    (3, 'Curitiba Opera House', 'Curitiba', 'PR', 3000);

-- Popula a tabela Dates
INSERT INTO dates (calendar_date, day, week, month, quarter, year, holiday_flag)
VALUES
    ('2025-10-10', 10, 41, 10, 4, 2025, FALSE),
    ('2025-05-20', 20, 21, 5, 2, 2025, FALSE),
    ('2025-07-01', 1, 27, 7, 3, 2025, FALSE),
    ('2024-12-25', 25, 52, 12, 4, 2024, TRUE);

-- Popula a tabela Events
INSERT INTO events (event_name, category_id, venue_id, date_id, city, event_start_time, event_date)
VALUES
    ('Rock in the City', 10, 1, 1, 'São Paulo', '2025-10-10 19:00:00', '2025-10-10'),
    ('Jazz Night', 11, 2, 2, 'Rio de Janeiro', '2025-05-20 20:00:00', '2025-05-20'),
    ('Pop Festival', 10, 1, 3, 'São Paulo', '2025-07-01 18:00:00', '2025-07-01'),
    ('Classical Concert', 12, 3, 4, 'Curitiba', '2024-12-25 21:00:00', '2024-12-25');

-- Popula a tabela Tickets
INSERT INTO tickets (event_id, sold)
SELECT event_id, FALSE
FROM events;
