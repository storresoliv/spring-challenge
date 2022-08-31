create table phones (id VARCHAR(255) not null, city_code varchar(255), country_code varchar(255), number varchar(255), primary key (id));
create table users (id VARCHAR(255) not null, created timestamp, email varchar(255), is_active boolean not null, last_login timestamp, modified timestamp, name varchar(255), password varchar(255), token varchar(255), primary key (id));
create table users_phones (users_entity_id VARCHAR(255) not null, phones_id VARCHAR(255) not null);
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table users_phones add constraint UK_8em9x6dvssprisqghwwe0dgee unique (phones_id);
alter table users_phones add constraint FK2h8lrw8nchqt19wj7lqgdjfpo foreign key (phones_id) references phones;
alter table users_phones add constraint FKklb2yrghhctc3x9fo7qhup85x foreign key (users_entity_id) references users;
