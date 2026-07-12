drop database if exists blackjack;
create database blackjack;
use blackjack;

create table Players (
id int auto_increment,
name varchar(100) not null unique,
email varchar(100),
regDate datetime default (current_timestamp),
primary key (id)
);

create table Games (
id int auto_increment,
playerId int,
startDate datetime default (current_timestamp),
endDate datetime default null,
bankRoll int not null,
stack int not null,
playerWon boolean default null,
moneyWon int default null,
primary key (id),
foreign key (playerId) references Players(id) on delete set null
);

create table Hands (
id int auto_increment,
gameId int not null,
blackJack boolean not null default false,
doubleDown boolean not null default false,
split boolean not null default false,
playerWon boolean default null, -- true: a játékos nyert, false: az osztó nyert, null: döntetlen.
points int not null,
bet int not null,
primary key (id),
foreign key (gameId) references Games(id) on delete cascade
);

insert into Players (id, name, email) values
(0, 'Játékos', null),
(1, 'Orsi', 'orsolya.pal@gmail.com'),
(2, 'Máté', 'mate.pal.p@gmail.com'),
(3, 'Mama', null),
(4, 'Kriszti', 'krisztapal27@gmail.com');

insert into Games(playerId, bankRoll, stack) values 
(1, 5000, 5000),
(2, 3000, 3000);

insert into Hands (gameId, blackJack, doubleDown, split, playerWon, points, bet) values 
(1, true, false, false, true, 21, 220),
(1, false, true, false, false, 22, 230),
(1, false, false, false, true, 18, 210),
(1, false, false, false, false, 16, 120),
(1, false, false, false, true, 19, 240),
(1, false, false, false, true, 20, 115),
(1, false, false, false, false, 23, 120),
(1, false, true, false, true, 20, 250),
(1, false, true, false, true, 21, 400);
