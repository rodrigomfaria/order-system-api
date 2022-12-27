INSERT INTO category (id,"name") VALUES
	 (1,'Computing'),
	 (2,'Office'),
	 (3,'Bed, table and bath'),
	 (4,'Eletronics'),
	 (5,'Gardem'),
	 (6,'Decoration'),
	 (7,'Perfumery'),
	 (8,'Computing'),
	 (9,'Office'),
	 (10,'Bed, table and bath');
INSERT INTO category (id,"name") VALUES
	 (11,'Eletronics'),
	 (12,'Gardem'),
	 (13,'Decoration'),
	 (14,'Perfumery');

INSERT INTO state (id,"name") VALUES
	 (1,'Minas Gerais'),
	 (2,'São Paulo');

INSERT INTO city (id,"name",state_id) VALUES
	 (1,'Uberlândia',1),
	 (2,'São Paulo',2);

INSERT INTO costumer (id,costumer_type,email,"name",registration_number) VALUES
	 (1,1,'maria@gmail.com','Maria Silva','12345678910');

INSERT INTO phone_number (costumer_id,phone_numbers) VALUES
	 (1,'123456789'),
	 (1,'987654321');

INSERT INTO address (id,complement,district,"number",public_place,zip_code,city_id,costumer_id) VALUES
	 (1,'Apto 303','Jardim','300','Rua das Flores','38400000',1,1),
	 (2,'Sala 800','Centro','105','Avenida Matos','38401000',1,1);

INSERT INTO product (id,"name",price) VALUES
	 (1,'Computer',2000.0),
	 (2,'Printer',800.0),
	 (3,'Mouse',80.0),
	 (4,'office desk',300.0),
	 (5,'towel',50.0),
	 (6,'quilt',200.0),
	 (7,'TV true color',1200.0),
	 (8,'brushcutter',800.0),
	 (9,'Abajour',100.0),
	 (10,'table lamp',180.0);
INSERT INTO product (id,"name",price) VALUES
	 (11,'Shampoo',90.0);

INSERT INTO product_category (product_id,category_id) VALUES
	 (1,1),
	 (1,4),
	 (2,1),
	 (2,2),
	 (2,4),
	 (3,1),
	 (3,4),
	 (4,2),
	 (5,3);
INSERT INTO product_category (product_id,category_id) VALUES
	 (6,3),
	 (7,4),
	 (8,5),
	 (9,6),
	 (10,6),
	 (11,7);

INSERT INTO demand (id,create_at,costumer_id,address_id) VALUES
	 (1,'2017-09-30 10:32:00',1,1),
	 (2,'2017-10-10 19:35:00',1,2);

INSERT INTO demand_item (discount,price,quantity,demand_id,product_id) VALUES
	 (0.0,2000.0,1,1,1),
	 (0.0,80.0,2,1,3),
	 (100.0,800.0,1,2,2);

INSERT INTO payment (demand_id,payment_status) VALUES
	 (1,2),
	 (2,1);

INSERT INTO payment_billet (due_date,payment_date,demand_id) VALUES
	 ('2017-10-20 00:00:00',NULL,2);

INSERT INTO payment_card (installments_number,demand_id) VALUES
	 (6,1);