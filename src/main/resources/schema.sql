\c
postgres;
DROP
DATABASE IF EXISTS restaurant;
CREATE
DATABASE restaurant;
\c
restaurant;
CREATE TABLE IF NOT EXISTS restaurant
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    place VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS menu
(
    id SERIAL PRIMARY KEY,
    name VARCHAR ( 250 ) NOT NULL,
    price DECIMAL (10,2)
);

CREATE TABLE IF NOT EXISTS unit
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS ingredient
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    price INT NOT NULL,
    unit_id INT REFERENCES unit(id)
);

CREATE TABLE IF NOT EXISTS stock
(
    id SERIAL PRIMARY KEY,
    quantity DECIMAL(10,2),
    ingredient_id INT REFERENCES ingredient(id)
);

CREATE TABLE IF NOT EXISTS menu_restaurant
(
    id SERIAL PRIMARY KEY,
    restaurant_id INT REFERENCES restaurant(id),
    menu_id INT REFERENCES menu(id)
);

CREATE TABLE IF NOT EXISTS ingredient_of_menu
(
    id SERIAL PRIMARY KEY,
    quantity DECIMAL(10, 2),
    menu_id INT REFERENCES menu(id),
    ingredient_id INT REFERENCES ingredient(id)
);

CREATE TABLE IF NOT EXISTS stock_in_restaurant
(
    id SERIAL PRIMARY KEY,
    restaurant_id INT REFERENCES restaurant(id),
    stock_ID INT REFERENCES stock(id)
);

CREATE TABLE IF NOT EXISTS action_stock
(
    id SERIAL PRIMARY KEY,
    type VARCHAR(150),
    action_date VARCHAR(50),
    quantity DECIMAL(10,2),
    ingredient_id INT REFERENCES ingredient(id),
    stock_id INT REFERENCES stock(id)
);

insert into unit(name)values ('piece');
insert into unit(name)values ('litre');
insert into unit(name)values ('kg');

SELECT *
FROM action_stock
WHERE action_date BETWEEN '2024-05-24' AND '2024-06-24';
SELECT action_date, type, quantity, name
FROM action_stock
         INNER JOIN ingredient ON action_stock.id = ingredient.id;
SELECT action_date, type, quantity, ingredient.name AS ingredient, unit.name AS unit
FROM action_stock
         INNER JOIN ingredient ON action_stock.id = ingredient.id
         INNER JOIN unit ON ingredient.id = unit.id;
SELECT action_stock.id, action_date, type, quantity, ingredient.name AS ingredient, unit.name AS
FROM action_stock INNER JOIN ingredient
ON action_stock.id = ingredient.id INNER JOIN unit ON ingredient.id = unit.id
WHERE action_date BETWEEN '2024-05-24' AND '2024-06-24';
SELECT action_stock.id, action_date, type, quantity, ingredient.name AS ingredient, unit.name AS unit
FROM action_stock
         INNER JOIN
     ingredient ON action_stock.ingredient_id = ingredient.id
         INNER JOIN
     unit ON ingredient.unit_id = unit.id
WHERE action_date BETWEEN '2024-05-24' AND '2024-06-24';


SELECT i.id   AS "Id Ingredient",
       i.name AS "nom_ingredient",
       m.name AS Menu,
       SUM(
               CASE
                   WHEN im.menu < 10 THEN 'Low price product'
                   WHEN price > 50 THEN 'High price product'
                   ELSE
                       'Normal product'
                   END
       )      AS total_quantity,
       u.name AS Unit
FROM action_stock a
         INNER JOIN ingredient i ON a.ingredient_id = i.id
         INNER JOIN ingredient_of_menu im ON im.ingredient_id = i.id
         INNER JOIN menu m ON im.menu_id = m.id
         INNER JOIN unit u ON i.unit_id = u.id
GROUP BY m.name, i.name, i.id, u.name, a.type
HAVING a.type = 'SORTIE'
ORDER BY "nom_ingredient" DESC;



SELECT a.ingredient_id, a.quantity
FROM action_stock a
         INNER JOIN ingredient i ON a.ingredient_id = i.id
         INNER JOIN menu m ON m.id = im.menu_id
         INNER JOIN unit u ON i.unit_id = u.id
WHERE a.type = 'SORTIE'
;