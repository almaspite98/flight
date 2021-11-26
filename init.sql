CREATE DATABASE javabase;
use javabase;
CREATE USER 'flight'@'localhost' IDENTIFIED BY 'flightpass';
GRANT ALL PRIVILEGES ON javabase.* TO 'flight'@'localhost';
FLUSH PRIVILEGES;
create table airlines (
       name varchar(255) not null,
        api_key varchar(255),
        primary key (name)
    );
create table flights (
       flight_id varchar(255) not null,
        airline varchar(255),
        arrival_time datetime(6),
        departure_time datetime(6),
        from_city varchar(255),
        number_of_seats integer not null,
        to_city varchar(255),
        primary key (flight_id)
    );
create table reservations (
       id integer not null auto_increment,
        email varchar(255),
        flight_id varchar(255),
        group_id integer,
        status varchar(255),
        timestamp datetime(6),
        primary key (id)
    );
create table users (
       email varchar(255) not null,
        airline varchar(255),
        departure varchar(255),
        password varchar(255),
        token varchar(255),
        transfer_time integer,
        primary key (email)
    );