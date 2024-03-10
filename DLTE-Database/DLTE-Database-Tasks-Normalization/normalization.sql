--without Normal form

CREATE TABLE Userrecharge (
  2      username VARCHAR(50),
  3      upi VARCHAR(50),
  4      mobilenumber VARCHAR(15),
  5      email VARCHAR(100),
  6      wallet_type VARCHAR(20),
  7      recharged_date DATE,
  8      recharged_provider VARCHAR(100),
  9      recharged_to VARCHAR(50),
 10      recharged_amount NUMBER
 11  );
Table created.

--1NF

CREATE TABLE User_Recharge_first_nf (
  2      username VARCHAR(50) primary key,
  3      upi VARCHAR(50),
  4      mobilenumber VARCHAR(15),
  5      email VARCHAR(100),
  6      wallet_type VARCHAR(20),
  7      recharged_date DATE,
  8      recharged_provider VARCHAR(100),
  9      recharged_to VARCHAR(50),
 10      recharged_amount NUMBER
 11  );
Table created.

--2NF

 CREATE TABLE User_second_nf (
  2      username VARCHAR(50) primary key,
  3      upi VARCHAR(50),
  4      mobilenumber VARCHAR(15),
  5      email VARCHAR(100));
Table created.

create table Recharge_second_nf(
  2  username varchar(50),wallet_type VARCHAR(20),
  3  recharged_date DATE,
  4  recharged_provider VARCHAR(100),
  5  recharged_to VARCHAR(50),
  6  recharged_amount NUMBER,
  7  foreign key (username) references user_second_nf(username));

Table created.

--3NF

create table user_wallets_three_nf(wallet_type VARCHAR(20),
  2          recharged_date DATE,
  3          recharged_provider VARCHAR(100),
  4          recharged_to VARCHAR(50),
  5         recharged_amount NUMBER);

Table created.

 ALTER TABLE user_wallets_three_nf ADD CONSTRAINT pk_wallet_type PRIMARY KEY (wallet_type);

Table altered.

alter table Recharge_second_nf add constraint fk_wallet_type foreign key (wallet_type) references user_wallets_three_nf(wallet_type);

Table altered.
