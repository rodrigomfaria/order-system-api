create table address (
    id serial not null,
	complement varchar(255),
	district varchar(255),
	number varchar(255),
	public_place varchar(255),
	zip_code varchar(255),
	city_id int4,
	costumer_id int4,
	primary key (id)
);

create table category (
   id  serial not null,
	name varchar(255),
	primary key (id)
);

create table city (
    id serial not null,
	name varchar(255),
	state_id int4,
	primary key (id)
);

create table costumer (
    id serial not null,
	costumer_type int4,
	email varchar(255),
	name varchar(255),
	registration_number varchar(255),
	primary key (id)
);

create table demand (
    id serial not null,
	create_at timestamp,
	costumer_id int4,
	address_id int4,
	primary key (id)
);

create table demand_item (
    discount float8,
	price float8,
	quantity int4,
	demand_id int4 not null,
	product_id int4 not null,
	primary key (demand_id, product_id)
);

create table payment (
   demand_id int4 not null,
	payment_status int4,
	primary key (demand_id)
);

create table payment_billet (
    due_date timestamp,
	payment_date timestamp,
	demand_id int4 not null,
	primary key (demand_id)
);

create table payment_card (
    installments_number int4,
	demand_id int4 not null,
	primary key (demand_id)
);

create table phone_number (
    costumer_id int4 not null,
	phone_numbers varchar(255)
);

create table product (
    id serial not null,
	name varchar(255),
	price float8,
	primary key (id)
);

create table product_category (
    product_id int4 not null,
	category_id int4 not null
);

create table state (
    id serial not null,
	name varchar(255),
	primary key (id)
);

alter table costumer 
    add constraint UK_21twn7bxgsmisulawd9jntr7c unique (email);

alter table address 
   add constraint FKpo044ng5x4gynb291cv24vtea 
   foreign key (city_id) 
   references city;

alter table address 
   add constraint FKg8w7v9jv4a58polo4gd9uj7ns 
   foreign key (costumer_id) 
   references costumer;

alter table city 
   add constraint FK6p2u50v8fg2y0js6djc6xanit 
   foreign key (state_id) 
   references state;

alter table demand 
   add constraint FK5fuutddjbajby7ry24ienbwf5 
   foreign key (costumer_id) 
   references costumer;

alter table demand 
   add constraint FKmm9ok8i54u0sj2s6ido6k4n5 
   foreign key (address_id) 
   references address;

alter table demand_item 
   add constraint FKpj87y33gjm2m2eohmj3643n1k 
   foreign key (demand_id) 
   references demand;

alter table demand_item 
   add constraint FKcggktub19p20qrr1872j5ug3k 
   foreign key (product_id) 
   references product;

alter table payment 
   add constraint FK8tta4tw5dwhxxcygbcc9718lg 
   foreign key (demand_id) 
   references demand;

alter table payment_billet 
   add constraint FKdmotgo4or3i98m6gb119p1xgj 
   foreign key (demand_id) 
   references payment;

alter table payment_card 
   add constraint FKpv19s71fno7t8o8t0efdaccma 
   foreign key (demand_id) 
   references payment;

alter table phone_number 
   add constraint FKkd4wx2oja35ngr1fnsdrmyyen 
   foreign key (costumer_id) 
   references costumer;

alter table product_category 
   add constraint FKkud35ls1d40wpjb5htpp14q4e 
   foreign key (category_id) 
   references category;

alter table product_category 
       add constraint FK2k3smhbruedlcrvu6clued06x 
       foreign key (product_id) 
       references product;