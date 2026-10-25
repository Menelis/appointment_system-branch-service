-- 1. Insert Provinces (using a unique constraint/index on name to prevent duplicates)
INSERT INTO provinces (name)
VALUES ('Eastern Cape'),
       ('Free State'),
       ('Gauteng'),
       ('KwaZulu-Natal'),
       ('Limpopo'),
       ('Northern Cape'),
       ('North West'),
       ('Western Cape'),
       ('Mpumalanga');

-- 2. Insert Cities (linking them dynamically to their respective province IDs)
INSERT INTO cities (name, province_id)
VALUES('Bhisho', (SELECT id FROM provinces WHERE name = 'Eastern Cape')),
      ('Gqeberha', (SELECT id FROM provinces WHERE name = 'Eastern Cape')),
      ('East London', (SELECT id FROM provinces WHERE name = 'Eastern Cape')),
      ('Bloemfontein', (SELECT id FROM provinces WHERE name = 'Free State')),
      ('Johannesburg', (SELECT id FROM provinces WHERE name = 'Gauteng')),
      ('Pietermaritzburg', (SELECT id FROM provinces WHERE name = 'KwaZulu-Natal')),
      ('Polokwane', (SELECT id FROM provinces WHERE name = 'Limpopo')),
      ('Mbombela', (SELECT id FROM provinces WHERE name = 'Mpumalanga')),
      ('Kimberley', (SELECT id FROM provinces WHERE name = 'Northern Cape')),
      ('Mahikeng', (SELECT id FROM provinces WHERE name = 'North West')),
      ('Cape Town', (SELECT id FROM provinces WHERE name = 'Western Cape')),
      ('Stellenbosch', (SELECT id FROM provinces WHERE name = 'Western Cape')),
      ('George', (SELECT id FROM provinces WHERE name = 'Western Cape')),
      ('Paarl', (SELECT id FROM provinces WHERE name = 'Western Cape')),
      ('Durban', (SELECT id FROM provinces WHERE name = 'KwaZulu-Natal'));