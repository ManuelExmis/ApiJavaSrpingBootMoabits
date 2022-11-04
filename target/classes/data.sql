INSERT INTO rol (id, name, read, edit, delete, active)
select 1, 'Admin', true, true, true, true
where not exists (select name from rol where name = 'Admin');

INSERT INTO rol (id, name, read, edit, delete, active)
select 2, 'Viewer', true, false, false, true
where not exists (select name from rol where name = 'Viewer');


INSERT INTO rol (id, name, read, edit, delete, active)
select 3, 'User', true, true, false, true
where not exists (select name from rol where name = 'User');