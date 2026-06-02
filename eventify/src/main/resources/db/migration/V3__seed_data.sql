INSERT INTO venues (name, address, city, max_cap) VALUES
    ('Movistar Arena', 'Calle 63 # 24-40', 'Bogota', 14000),
    ('Teatro Mayor', 'Calle 39 # 14-85', 'Bogota', 1200),
    ('Parque Norte', 'Cra 48 # 67-90', 'Medellin', 8000),
    ('Estadio Atanasio', 'Cra 74 # 48-10', 'Medellin', 40000),
    ('Centro de Convenciones', 'Av. Santander # 47-80', 'Cartagena', 5000),
    ('Arena del Rio', 'Cra 30 # 8-50', 'Barranquilla', 10000),
    ('Teatro Metropolitano', 'Calle 57 # 53-35', 'Medellin', 1500),
    ('Auditorio Luz', 'Cra 15 # 98-20', 'Bogota', 900),
    ('Pabellon Caribe', 'Cra 54 # 72-10', 'Barranquilla', 2500),
    ('Plaza Mayor', 'Cra 54 # 24-01', 'Medellin', 12000);

INSERT INTO categories (name, description) VALUES
    ('Concerts', 'Live music and concert events'),
    ('Workshops', 'Hands-on learning sessions'),
    ('Conferences', 'Professional and academic conferences'),
    ('Sports', 'Sports competitions and tournaments'),
    ('Gastronomy', 'Food and beverage experiences'),
    ('Festivals', 'Large scale cultural festivals'),
    ('Theater', 'Stage plays and performing arts'),
    ('Rock', 'Rock music events');

INSERT INTO events (name, event_date, description, venue_id, active) VALUES
    ('Concierto de ROCK', DATE '2026-01-15', 'Show de rock en vivo en Bogota', 1, TRUE),
    ('Workshop Creativo 2', DATE '2026-01-16', 'Taller practico de creatividad', 2, TRUE),
    ('Conferencia Innovacion 3', DATE '2026-01-17', 'Tendencias de tecnologia y negocio', 3, TRUE),
    ('Festival Urbano 4', DATE '2026-01-18', 'Festival cultural urbano', 4, TRUE),
    ('Evento Gastronomico 5', DATE '2026-01-19', 'Experiencia culinaria con chefs invitados', 5, TRUE),
    ('Torneo Deportivo 6', DATE '2026-01-20', 'Competencia deportiva de alto nivel', 6, TRUE),
    ('Obra de Teatro 7', DATE '2026-01-21', 'Presentacion teatral de temporada', 7, TRUE),
    ('Concierto Sinfonico 8', DATE '2026-01-22', 'Concierto sinfonico con orquesta', 8, TRUE),
    ('Workshop Liderazgo 9', DATE '2026-01-23', 'Sesion practica de liderazgo', 9, TRUE),
    ('Conferencia IA 10', DATE '2026-01-24', 'Aplicaciones de inteligencia artificial', 10, TRUE);

INSERT INTO events (name, event_date, description, venue_id, active)
SELECT
    'Evento ' || gs,
    DATE '2026-02-01' + ((gs - 11) % 120),
    'Descripcion del evento numero ' || gs,
    ((gs - 1) % 10) + 1,
    TRUE
FROM generate_series(11, 200) AS gs;

INSERT INTO events_categories (event_id, category_id)
VALUES
    (1, 1),
    (1, 8);

INSERT INTO events_categories (event_id, category_id)
SELECT id, ((id - 1) % 8) + 1
FROM events
WHERE id > 1;

INSERT INTO events_categories (event_id, category_id)
SELECT id, ((id + 2 - 1) % 8) + 1
FROM events
WHERE id > 1;
