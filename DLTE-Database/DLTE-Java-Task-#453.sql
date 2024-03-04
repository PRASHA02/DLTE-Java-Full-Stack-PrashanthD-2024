--insert  using procedure

 create or replace procedure insert_data(
     date_t date,
     amount_t number,
     to_t varchar2,
     remarks_t varchar2,
     info_t out varchar2
)
as
begin
 insert into transaction(transactiondate,amount,recieptant,remarks) values(date_t,amount_t,to_t,remarks_t);
 info_t:='completed';
exception
when others then
 info_t:='not completed' || SQLERRM;
end;
 /
variable info_t varchar2;
execute insert_data('09-FEB-2002',30000,'prash','myself',:info_t);
print info_t;

--delete using procedure

create or replace procedure delete_date(
enter_date date,
error_t out varchar
)
as 
begin
    delete from transaction where transactiondate = enter_date;
    error_t:='no error';
exception
    when others then
    error_t:='error occured' || SQLERRM;
end;
/

variable error_t varchar;
execute delete_date('29-feb-24',:error_t);

--filter using procedures

create or replace procedure filter_transaction(
   to_names out varchar2,
   error_filter out varchar2   
)
as 
 begin select recieptant into to_names from transaction where remarks='education';
 error_filter:='Completed';
 exception
  when no_data_found then
  error_filter:='No data found';
  when others then
  error_filter:='error occured'||SQLERRM;
end;
/
variable to_name varchar;
variable error_filter  varchar2;
execute filter_transaction(:to_name,:filter_error);
print to_name;
print error_filter;