
INSERT INTO rol_usuario(id_rol_usuario, nombre, descripcion) VALUES(1, 'Alumno', 'descripcion');
INSERT INTO rol_usuario(id_rol_usuario, nombre, descripcion) VALUES(2, 'Profesor', 'descripcion');

INSERT INTO usuario(id_usuario, nombre_completo, rut, verificador, fecha_nacimiento, direccion, comuna, telefono_fijo, telefono_celular, suspendida, id_rol_usuario) VALUES (1, 'Pedro Flores', 21265055, '2', '1997-06-06', 'direccion_1', 'comuna_1', 123456789, 123456789, false, 1);
INSERT INTO usuario(id_usuario, nombre_completo, rut, verificador, fecha_nacimiento, direccion, comuna, telefono_fijo, telefono_celular, suspendida, id_rol_usuario) VALUES (2, 'Andrés Carrera', 21265055, '2', '1996-06-06', 'direccion_1', 'comuna_1', 123456789, 123456789, false, 2);

INSERT INTO profesor(id_profesor, id_usuario, cargo) VALUES (1, 2, 'cargo_1');

INSERT INTO alumno(id_alumno, id_usuario, curso, parentesco, nombre_completo, rut, verificador, direccion, comuna, telefono_fijo, telefono_celular) VALUES (1, 1, '1D', 'Padre', 'Sebastian Osea', 21262020, '3', 'direccion_1', 'comuna_1', 123456789, 123456789);

INSERT INTO morosidad(id_morosidad, fecha_inicio, fecha_final, dias_retraso, sancion, nombre_libro, id_alumno) VALUES (1, '2018-05-22', '2018-05-25', 3, 'sancion_1', 'libro_1', 1);

INSERT INTO estado_prestamo(id_estado_prestamo, nombre, descripcion) VALUES (1, 'estado_prestamo_1', 'descripcion');
INSERT INTO estado_prestamo(id_estado_prestamo, nombre, descripcion) VALUES (2, 'estado_prestamo_2', 'descripcion');

INSERT INTO tipo_libro(id_tipo_libro, nombre, descripcion) VALUES (1, 'tipo_libro_1', 'descripcion');

INSERT INTO libro(id_libro, nombre, autor, editorial, numero_paginas, ubicacion, categoria, numero_copias, id_tipo_libro) VALUES (1, 'libro_1', 'autor_1', 'editorial_1', 100, 'ubicacion_1', 'categoria_1', 5, 1);

INSERT INTO copia(codigo, id_libro, disponible) VALUES (1000000, 1, true);
INSERT INTO copia(codigo, id_libro, disponible) VALUES (1000001, 1, true);
INSERT INTO copia(codigo, id_libro, disponible) VALUES (1000002, 1, true);
INSERT INTO copia(codigo, id_libro, disponible) VALUES (1000003, 1, true);
INSERT INTO copia(codigo, id_libro, disponible) VALUES (1000004, 1, true);

INSERT INTO prestamo(id_prestamo, fecha_inicio, fecha_entrega, cantidad_renovacion, id_estado_prestamo, id_usuario, codigo) VALUES (1, '2018-05-23', '2018-05-27', 0, 1, 1, 1000000);

INSERT INTO rol_empleado(id_rol_empleado, nombre, descripcion) VALUES (1, 'ADMINISTRADOR', 'DESCRIPCIÓN');
INSERT INTO rol_empleado(id_rol_empleado, nombre, descripcion) VALUES (2, 'BIBLIOTECARIO', 'DESCRIPCIÓN');

INSERT INTO empleado(id_empleado, nombre, contrasena, nombre_completo, id_rol_empleado)VALUES (1, 'pedro', '12345', 'Pedro Ladislao', 1);
INSERT INTO empleado(id_empleado, nombre, contrasena, nombre_completo, id_rol_empleado)VALUES (2, 'jose', '12345', 'Jose Inzunza', 2);