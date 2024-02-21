--creating table
create table transaction(transactiondate date not null,amount number not null,recieptant varchar(20) not null,remarks varchar(20) not null);

--insert into table
insert into transaction(transactiondate,amount,recieptant,remarks) values('29feb2024',200000,'prashanth','education');

insert into transaction(transactiondate,amount,recieptant,remarks) values('09apr2025',80000,'vignesh','family');

insert into transaction(transactiondate,amount,recieptant,remarks) values('30jan2026',100000,'shreyas','emergency');

insert into transaction(transactiondate,amount,recieptant,remarks) values('19jun2032',2000,'elroy','bills');

insert into transaction(transactiondate,amount,recieptant,remarks) values('09feb2030',50000,'rakesh','friend');

--1.Filter based on given range of data

select * from transaction where transactiondate between '09feb24' and '09feb29';

--2.least amount transferred

select min(amount) as minimumamount from transaction;

--3.Maximum amount transferred

select max(amount) as maxamount from transaction;

--4.Number of Transaction made to particular beneficiary

select recieptant as beneficiaryName,amount as transactionamount from transaction where recieptant='prashanth';

--5.Filter based on particular remarks

select * from transaction where remarks='education';