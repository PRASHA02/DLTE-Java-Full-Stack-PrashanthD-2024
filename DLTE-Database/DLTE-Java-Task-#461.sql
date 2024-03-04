--perform trigger operation

--before when insert new transaction with null or empty remarks assign some valid remarks

create or replace trigger remarks_triggers
before insert on transaction
for each row
begin
  if: new.remarks is null then: new.remarks: = 'Enter some some remarks that are valid';
  end if;
end;
/