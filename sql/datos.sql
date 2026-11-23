INSERT INTO "PistonRiojano"."Clientes" (dni, nombre, apellido, telefono, email) VALUES (
'45172714'::integer, 'ANDRES'::character varying, 'VEGA'::character varying, '3804561825'::numeric, 'andreslarioja10@gmail.com'::character varying),
('25147628'::integer, 'LEANDRO'::character varying, 'PAREDES'::character varying, '3804825246'::numeric, 'campeon2026@gmail.com'::character varying),
('36172729'::integer, 'NESTOR'::character varying, 'VALDEZ'::character varying, '3804752638'::numeric, 'nestitor16@gmail.com'::character varying);

INSERT INTO "PistonRiojano"."Mecanicos" (legajo, nombre, apellidos, especialidad, valor_hora) VALUES (
'1'::integer, 'MARCOS'::character varying, 'VERA'::character varying, 'GENERAL'::character varying, '400'::integer),
('2'::integer, 'LEONARDO'::character varying, 'ALVAREZ'::character varying, 'FRENOS'::character varying, '500'::integer),
('3'::integer, 'LAUTARO'::character varying, 'OCAMPO'::character varying, 'MOTOR'::character varying, '600'::integer);

INSERT INTO "PistonRiojano"."Repuestos" (codigo, descripcion, precio_unitario, stock_disponible) VALUES (
'A1'::character varying, 'Amortiguadores Celta Corsa Classic Suzuki Fun Kit X4'::character varying, '157768.00'::numeric, '4'::integer),
('A2'::character varying, 'Amortiguadores Peugeot 206 207 Sw Delanteros Topes Fuelles'::character varying, '121403.00'::numeric, '6'::integer),
('A3'::character varying, 'Amortiguadores Peugeot 206 207 Traseros'::character varying, '74597.00'::numeric, '3'::integer),
('A4'::character varying, 'Amortiguadores Gol Trend Voyage Cazoleta Fuelle Tope Kit x4'::character varying, '270762.00'::numeric, '4'::integer),
('A5'::character varying, 'Espirales Traseros Gnc Toyota Sw4 Sw4 1998-2005'::character varying, '120637.00'::numeric, '8'::integer),
('A6'::character varying, 'Espirales Traseros Gnc Toyota Celica-1998 Kit X2'::character varying, '178722.00'::numeric, '12'::integer),
('A7'::character varying, 'Bieletas De Suspension Regulable Toyota Corolla'::character varying, '50442.00'::numeric, '16'::integer),
('A8'::character varying, 'Rotulas De Suspension Superiores Toyota Hilux 2005'::character varying, '13770.00'::numeric, '34'::integer),
('A9'::character varying, 'Bujias Precalentamiento Champion Audi A4 2.0 Tdi'::character varying, '77534.00'::numeric, '12'::integer),
('B1'::character varying, 'Extremo De Direccion Chevrolet Corsa Classic 03 A 09'::character varying, '24015.00'::numeric, '23'::integer),
('B2'::character varying, 'Cazoleta Amortiguador Delantero Vectra Astra Meriva'::character varying, '62557.00'::numeric, '25'::integer);

INSERT INTO "PistonRiojano"."Autos" (patente, marca, modelo, "anio", clientes_dni, cantidad_puertas, combustible) VALUES (
'AB123CD'::character varying, 'TOYOTA'::character varying, 'COROLLA'::character varying, '2025'::integer, '45172714'::integer, '4'::integer, 'NAFTA'::character varying),
('AB124CD'::character varying, 'TOYOTA'::character varying, 'YARIS CROSS'::character varying, '2025'::integer, '25147628'::integer, '4'::integer, 'GNC'::character varying);

INSERT INTO "PistonRiojano"."Motos" (
patente, marca, modelo, "anio", clientes_dni, cilindrada) VALUES (
'AB125CD'::character varying, 'HONDA'::character varying, 'CB190R 2.0'::character varying, '2025'::integer, '36172729'::integer, '184'::integer),
('AB126CD'::character varying, 'HONDA'::character varying, 'CB750 HORNET'::character varying, '2025'::integer, '25147628'::integer, '755'::integer);