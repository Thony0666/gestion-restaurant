\c postgres;
DROP DATABASE IF EXISTS restaurant;
CREATE DATABASE restaurant;
\c restaurant;
CREATE TABLE restaurant(
   id SERIAL PRIMARY KEY ,
   place VARCHAR(250) NOT NULL
);

CREATE TABLE menu(
   id SERIAL PRIMARY KEY ,
   name VARCHAR(250) NOT NULL,
   price DECIMAL(10,2)
);

CREATE TABLE unit(
   id SERIAL PRIMARY KEY ,
   name VARCHAR(250) NOT NULL
);

CREATE TABLE ingredient(
   id SERIAL PRIMARY KEY ,
   name VARCHAR(250) NOT NULL,
   price INT NOT NULL,
   unit_id INT REFERENCES unit(id)
);
CREATE TABLE qte_sortie(
   id SERIAL PRIMARY KEY ,
   quantity DECIMAL(10,2),
   ingredient INT,
   menu_id INT REFERENCES menu(id),
   sortie_date TIMESTAMP,
   restaurant_id INT REFERENCES restaurant(id)
);

CREATE TABLE stock(
   id SERIAL PRIMARY KEY ,
   quantity DECIMAL(10,2),
   ingredient_id INT REFERENCES ingredient(id),
   restaurant_id INT REFERENCES restaurant(id)
);

CREATE TABLE sale(
   id SERIAL PRIMARY KEY ,
   price INT,
   sale_date TIMESTAMP,
   restaurant_id INT REFERENCES restaurant(id),
   menu_id INT REFERENCES menu(id)
);

CREATE TABLE ingredient_of_menu(
   id SERIAL PRIMARY KEY ,
   quantity DECIMAL(10,2),
   menu_id INT REFERENCES menu(id),
   ingredient_id INT  REFERENCES ingredient(id)
);

CREATE TABLE action_stock(
   id SERIAL PRIMARY KEY ,
   ingredient_id INT REFERENCES ingredient(id),
   stock_id INT REFERENCES stock(id),
   type VARCHAR(150),
   quantity DECIMAL(10,2),
   action_date TIMESTAMP
);

insert into unit(name)values ('piece');
insert into unit(name)values ('litre');
insert into unit(name)values ('kg');


WITH top_menus AS (
    SELECT sale.menu_id, menu.name,
           SUM(sale.quantity) AS total_quantity
    FROM sale
             INNER JOIN menu ON menu.id = sale.menu_id
    WHERE sale.restaurant_id = 7
      AND sale.sale_date BETWEEN '2024-04-27 00:01:49' AND '2024-05-28 01:51:49'
    GROUP BY sale.menu_id, menu.name
),
     ingredient_usage AS (
         SELECT im.ingredient_id, tm.name,  SUM(im.quantity * tm.total_quantity) AS total_usage
         FROM ingredient_of_menu im
                  JOIN top_menus tm ON im.menu_id = tm.menu_id
         GROUP BY im.ingredient_id, tm.name
         ORDER BY total_usage DESC
    LIMIT 3
    )
SELECT i.id AS ingredient_id, i.name AS ingredient_name, u.name AS unit_name, iu.name AS menu_name, iu.total_usage
FROM ingredient_usage iu
         JOIN ingredient i ON iu.ingredient_id = i.id
         JOIN unit u ON i.unit_id = u.id;


select qs.ingredient ,qs.menu_id,qs.restaurant_id,qs.sortie_date ,sum(qs.quantity) from qte_sortie qs where qs.sortie_date between '2024-04-28' and '2024-06-28' group by qs.ingredient,qs.menu_id,qs.restaurant_id,qs.sortie_date;
SELECT qs.ingredient ,i.name, m.name, qs.quantity as qte_depense,u.name as unite FROM qte_sortie qs INNER JOIN menu m ON m.id = qs.menu_id INNER JOIN ingredient i ON qs.ingredient=i.id INNER JOIN unit u on i.unit_id = u.id  WHERE qs.restaurant_id=6 ORDER BY  qte_depense DESC LIMIT 3;

WITH sumQte AS (SELECT qs.ingredient ,qs.menu_id,qs.restaurant_id ,SUM(qs.quantity) as total
                FROM qte_sortie qs
                WHERE qs.sortie_date BETWEEN '2024-05-28' AND '2024-05-29'AND restaurant_id=9
                GROUP BY qs.ingredient,qs.menu_id,qs.restaurant_id)
SELECT i.id  ,i.name ,m.name, sq.total ,u.name  FROM sumQte AS sq JOIN menu m ON sq.menu_id = m.id
     JOIN ingredient i ON sq.ingredient = i.id JOIN unit u ON u.id = i.unit_id ORDER BY total DESC;

WITH sumQte AS (SELECT qs.ingredient ,qs.menu_id,qs.restaurant_id ,SUM(qs.quantity) as total
                FROM qte_sortie qs
                WHERE qs.sortie_date BETWEEN '2024-05-28' AND '2024-05-29'AND restaurant_id = 7
                GROUP BY qs.ingredient,qs.menu_id,qs.restaurant_id)
SELECT i.id AS ingredient_id ,i.name AS ingredient,m.name as menu, sq.total as qte_expenses, u.name as unit
FROM sumQte AS sq JOIN menu m ON sq.menu_id = m.id;

 WITH sale_mov AS (
     SELECT
         s.restaurant_id,
         s.menu_id,
         SUM(s.price) AS totalPrice,
         SUM(s.quantity) AS nbSold
     FROM sale s
     WHERE s.sale_date BETWEEN '2024-05-29' AND '2024-05-30'
    GROUP BY s.restaurant_id, s.menu_id
)
 SELECT
    sm.restaurant_id,
     r.place,
     m.name,
     sm.nbSold,
     sm.totalPrice
 FROM sale_mov sm
         JOIN restaurant r ON r.id = sm.restaurant_id
          JOIN menu m ON m.id = sm.menu_id order by sm.restaurant_id;
                                                                                                                               JOIN ingredient i ON sq.ingredient = i.id JOIN unit u ON u.id = i.unit_id ORDER BY total DESC LIMIT 3;









