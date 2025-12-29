
INSERT INTO t_category (name) VALUES ('Smartphones');
INSERT INTO t_category (name) VALUES ('Headphones');

INSERT INTO t_country (code, name) VALUES ('US', 'United States');
INSERT INTO t_country (code, name) VALUES ('KR', 'South Korea');
INSERT INTO t_country (code, name) VALUES ('KZ', 'Kazakhstan');
INSERT INTO t_item (name, description, price, category_id)
VALUES
    ('iPhone 15 Pro', 'Powerful flagship smartphone', 639980,
     (SELECT id FROM t_category WHERE name = 'Smartphones')),
    ('HyperX Cloud II', 'Popular Gaming Headphones', 30000,
     (SELECT id FROM t_category WHERE name = 'Headphones'));

INSERT INTO t_item_countries (item_id, countries_id)
VALUES
    ((SELECT id FROM t_item WHERE name = 'iPhone 15 Pro'), (SELECT id FROM t_country WHERE code = 'US')),
    ((SELECT id FROM t_item WHERE name = 'iPhone 15 Pro'), (SELECT id FROM t_country WHERE code = 'KZ')),
    ((SELECT id FROM t_item WHERE name = 'HyperX Cloud II'), (SELECT id FROM t_country WHERE code = 'KR'));