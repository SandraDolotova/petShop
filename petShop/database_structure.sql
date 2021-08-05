drop database if exists PetManager;
create database if not exists PetManager;
use PetManager;

create table if not exists owners(
                                     id int auto_increment not null,
                                     name varchar(50) not null,
    primary key(id)
    );
create table if not exists petTypes(
                                       id int auto_increment not null,
                                       type varchar(20) not null,
    primary key(id)
    );
create table if not exists pets(
                                   id int auto_increment not null,
                                   ownerId int null,
                                   name varchar(50) not null,
    age int not null,
    pet_type_id int not null,
    primary key(id),
    foreign key(ownerId) references owners(id),
    foreign key(pet_type_id) references petTypes(id)
    );
create table if not exists meals(
                                    id int auto_increment not null,
                                    name varchar(50) not null,
    weight double not null,
    pet_type_id int not null,
    primary key(id),
    foreign key(pet_type_id) references petTypes(id)
    );
create table if not exists toys(
                                   id int auto_increment not null,
                                   material varchar(20) not null,
    pet_type_id int not null,
    price double not null,
    primary key(id),
    foreign key(pet_type_id) references petTypes(id)
    );
