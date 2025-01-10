-- Insere alguns eventos de teste
INSERT INTO event (name, category_id, city, event_date)
VALUES
    ('Rock in the City', 10, 'São Paulo', '2025-10-10'),
    ('Jazz Night', 11, 'Rio de Janeiro', '2025-05-20'),
    ('Pop Festival', 10, 'São Paulo', '2025-07-01'),
    ('Classical Concert', 12, 'Curitiba', '2024-12-25');

-- Verifica os IDs gerados automaticamente para os eventos
SELECT * FROM event;

-- Insere ingressos na tabela ticket com os IDs gerados na tabela event
INSERT INTO ticket (event_id, sold) VALUES (1, FALSE);
INSERT INTO ticket (event_id, sold) VALUES (1, FALSE);
INSERT INTO ticket (event_id, sold) VALUES (2, FALSE);
INSERT INTO ticket (event_id, sold) VALUES (3, FALSE);
INSERT INTO ticket (event_id, sold) VALUES (4, FALSE);