--creating table
create table transaction(transactiondate date not null,amount number not null,recieptant varchar(20) not null,remarks varchar(20) not null);

--insert into table
insert into transaction(transactiondate,amount,recieptant,remarks) values('29feb2024',200000,'prashanth','education');

insert into transaction(transactiondate,amount,recieptant,remarks) values('09apr2025',80000,'vignesh','family');

insert into transaction(transactiondate,amount,recieptant,remarks) values('30jan2026',100000,'shreyas','emergency');

insert into transaction(transactiondate,amount,recieptant,remarks) values('19jun2032',2000,'elroy','bills');

insert into transaction(transactiondate,amount,recieptant,remarks) values('09feb2030',50000,'rakesh','friend');

--1.Filter based on given range of data

create view range as select * from transaction where transactiondate between '09feb24' and '09feb29';
select * from range;

--2.least amount transferred

create view leastamount as select min(amount) as minimumamount from transaction;
select * from leastamount;

--3.Maximum amount transferred

create view maxamount as select max(amount) as maxamount from transaction;
select * from maxamount;

--4.Number of Transaction made to particular beneficiary

create view beneficiary as select recieptant as beneficiaryName,amount as transactionamount from transaction where recieptant='prashanth';
select * from beneficiary;

--5.Filter based on particular remarks

create view arrangeremarks as select * from transaction where remarks='education';
select * from arrangeremarks;