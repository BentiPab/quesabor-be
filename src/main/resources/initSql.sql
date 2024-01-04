insert into qualities ( name , price ) values ('classic', 350);
insert into qualities ( name , price ) values ('premium', 500);

insert into empanadas ( name , safety_stock , stock , id_quality ) values ('carne suave', 100 ,10 ,1);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('pollo', 100 ,10 ,1);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('verdura', 100 ,10 ,1);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('carne picante', 100 ,10 ,1);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('jamon y queso', 100 ,10 ,1);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('choclo', 100 ,10 ,1);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('cebolla y queso', 100 ,10 ,1);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('pollo al verdeo', 100 ,10 ,2);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('carne a cuchillo', 100 ,10 ,2);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('humita', 100 ,10 ,2);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('matambre a la pizza', 100 ,10 ,2);
insert into empanadas ( name , safety_stock , stock , id_quality ) values ('capresse', 100 ,10 ,2);

insert into users(username, password, role) values ('pelba', '$2a$12$OjYJN9GnYC9DAgShkGLHGOwFOSk4KT0UKUuZUCsW/VNdqeCt9vPJC', 'ADMIN');

commit;