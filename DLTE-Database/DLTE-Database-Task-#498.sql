--select priveleges--

create user vignesh identified by vignesh02;
grant connect to vignesh;
grant select on transaction to vignesh;
alter session set current_schema=system;


--delete priveleges--

create user ajay identified by ajay02;
grant connect to ajay;
grant delete on transaction to ajay;
alter session set current_schema=system;

--select priveleges--

create user vineeth identified by vineeth02;
grant connect to vineeth;
grant select on transaction to vineeth;
alter session set current_schema=system;

--insert priveleges--

create user prathvi identified by prathvi02;
grant insert on transaction to prathvi;
grant connect to prathvi;
alter session set current_schema=system;

--update priveleges--

create user pranav identified by pranav02;
grant update on transaction to pranav;
grant connect to pranav;
alter session set current_schema = system;