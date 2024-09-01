--db jdbc:postgresql://localhost:5432/aikam_db
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS purchases;

CREATE TABLE customers
(
    id         SERIAL,
    first_name CHARACTER(50) NOT NULL,
    last_name  CHARACTER(50) NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);

CREATE TABLE products
(
    id    SERIAL,
    name  CHARACTER(50) NOT NULL,
    price INTEGER       NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);

CREATE TABLE purchases
(
    id            SERIAL,
    customer_id   INTEGER,
    product_id    INTEGER,
    purchase_date DATE,
    CONSTRAINT purchases_pkey PRIMARY KEY (id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products (id)
);

INSERT INTO customers(first_name, last_name) VALUES ('Антон', 'Иванов');
INSERT INTO customers(first_name, last_name) VALUES ('Николай', 'Иванов');
INSERT INTO customers(first_name, last_name) VALUES ('Валентин', 'Петров');

INSERT INTO products (name, price) VALUES ('Минеральная вода', 100);
INSERT INTO products (name, price) VALUES ('Хлеб', 30);
INSERT INTO products (name, price) VALUES ('Сметана', 70);
INSERT INTO products (name, price) VALUES ('Колбаса', 300);
INSERT INTO products (name, price) VALUES ('Сыр', 420);

INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (1, 2, to_date('09.08.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (1, 3, to_date('09.08.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (1, 1, to_date('04.03.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (1, 5, to_date('11.06.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (1, 3, to_date('29.08.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (1, 2, to_date('05.04.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (2, 1, to_date('09.08.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (2, 1, to_date('03.04.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (2, 5, to_date('29.08.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (2, 2, to_date('05.04.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (3, 4, to_date('09.08.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (3, 4, to_date('03.04.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (3, 3, to_date('04.03.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (3, 5, to_date('11.06.2024', 'dd.mm.yyyy'));
INSERT INTO purchases (customer_id, product_id, purchase_date) VALUES (3, 1, to_date('29.08.2024', 'dd.mm.yyyy'));