Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('1','Andres','Guzman','Debito',null,null);
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('2','John','Doe','Credito',null,null);
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('3','Stefany','Vaca','Paypal',null,null);
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('4','Manuel','Suarez','Bitcoin',null,null);
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('5','Luci','Martinez','Bitcoin',null,null);
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('6','Luna','García','Debito',null,null);
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('7','Andres','Suarez','Bitcoin',null,null);
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('8','Pepa','Guzman','Debito',null,null);
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('9','Lou','Loe','Credito',null,null);
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('10','Yuli','Vaca','Bitcoin',STR_TO_DATE('22/03/25','%d/%m/%y'),STR_TO_DATE('22/03/25','%d/%m/%y'));
Insert into java_hibernate_curso.CLIENTES (ID,NOMBRE,APELLIDO,FORMA_PAGO,CREADO_EN,EDITADO_EN) values ('11','Anacleto','Stopa','Credito',STR_TO_DATE('22/03/25','%d/%m/%y'),STR_TO_DATE('22/03/25','%d/%m/%y'));

Insert into java_hibernate_curso.alumnos (ID,NOMBRE,APELLIDO) values ('1','Anacleto','Gonzalez');
Insert into java_hibernate_curso.alumnos (ID,NOMBRE,APELLIDO) values ('2','Patroclo','Mena');

Insert into java_hibernate_curso.cursos (ID,PROFESOR,TITULO) values ('1','Andres','Curso de Java');
Insert into java_hibernate_curso.cursos (ID,PROFESOR,TITULO) values ('2','Stefany','Curso de maquillaje cuantico con IA');

INSERT INTO java_hibernate_curso.direcciones (calle, numero) VALUES ('vaticano', 123);
INSERT INTO java_hibernate_curso.direcciones (calle, numero) VALUES ('colon', 456);
INSERT INTO java_hibernate_curso.direcciones (calle, numero) VALUES ('Espinardo', 789);
INSERT INTO java_hibernate_curso.direcciones (calle, numero) VALUES ('Churra', 357);

INSERT INTO java_hibernate_curso.tbl_clientes_direcciones (id_cliente, id_direccion) VALUES (1, 1);
INSERT INTO java_hibernate_curso.tbl_clientes_direcciones (id_cliente, id_direccion) VALUES (1, 2);
INSERT INTO java_hibernate_curso.tbl_clientes_direcciones (id_cliente, id_direccion) VALUES (3, 3);
INSERT INTO java_hibernate_curso.tbl_clientes_direcciones (id_cliente, id_direccion) VALUES (3, 4);

INSERT INTO java_hibernate_curso.clientes_detalles (prime, puntos_acumulados, cliente_detalle_id) VALUES (1, 8000, 1);
INSERT INTO java_hibernate_curso.tbl_alumnos_cursos (alumno_id, curso_id) values (1, 1)
INSERT INTO java_hibernate_curso.tbl_alumnos_cursos (alumno_id, curso_id) values (2, 2)


INSERT INTO java_hibernate_curso.facturas (descripcion, total, id_cliente) values ('Oficina', 4000, 1)
INSERT INTO java_hibernate_curso.facturas (descripcion, total, id_cliente) values ('Casa', 5000, 1)
INSERT INTO java_hibernate_curso.facturas (descripcion, total, id_cliente) values ('Deporte', 6000, 2)
INSERT INTO java_hibernate_curso.facturas (descripcion, total, id_cliente) values ('Computacion', 7000, 2)
INSERT INTO java_hibernate_curso.facturas (descripcion, total, id_cliente) values ('Videojuegos', 8000, 4)
