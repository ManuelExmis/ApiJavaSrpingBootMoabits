
create or replace function fn_allusers()
    RETURNS table (
		  id bigint, name text, date_of_birth date, age int,
		phone varchar(255), address varchar(255), email varchar(255),
		login varchar(255), password varchar(255), rol_id bigint, active bool, edit bool,
		rol_name text, read bool
	)
	LANGUAGE 'plpgsql'
as
  'BEGIN return query SELECT u.id, u.name, u.date_of_birth, u.age, u.phone, u.address,
  		u.email, u.login, u.password, u.rol_id, r.active, r.edit, r.name as rol_name, r.read
    FROM users u
	inner join rol r
		on r.id = u.rol_id;
END;' ;
