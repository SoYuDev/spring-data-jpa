drop table if exists producto cascade;
drop sequence if exists producto_seq;
create sequence producto_seq start with 1 increment by 1;
create table producto (precio numeric(10,2), id bigint not null,
 nombre_producto varchar(512), descripcion TEXT, primary key (id));