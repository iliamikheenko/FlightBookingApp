create table passenger(
    id bigserial primary key,
    name varchar(50) not null,
    surname varchar(50) not null,
    email varchar(128) not null unique
);
create table flight(
    id bigserial primary key,
    flight_number varchar(50) not null,
    airline_name varchar(50) not null,
    departure_airport varchar(50) not null,
    arrival_airport varchar(50) not null,
    departure_time timestamp not null,
    arrival_time timestamp not null,
    price decimal(10,2) not null
);
create table reservation(
    id bigserial primary key,
    reservation_number varchar(50) not null,
    user_id bigint references passenger(id),
    flight_id bigint references flight(id),
    num_seats integer not null,
    total_price decimal(10,2) not null
);
create table seat(
    id bigserial primary key,
    flight_id bigserial references flight(id),
    seat_number varchar(10) unique not null,
    reservation_id bigint references reservation(id),
    is_available boolean not null
);

