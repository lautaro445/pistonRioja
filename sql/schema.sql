

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


DROP SCHEMA IF EXISTS "PistonRiojano" CASCADE;
CREATE SCHEMA "PistonRiojano";


ALTER SCHEMA "PistonRiojano" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;



CREATE TABLE "PistonRiojano"."Vehiculos" (
    patente character varying(7) NOT NULL,
    marca character varying(30) NOT NULL,
    modelo character varying(30) NOT NULL,
    "año" integer NOT NULL,
    clientes_dni integer NOT NULL
);


ALTER TABLE "PistonRiojano"."Vehiculos" OWNER TO postgres;



CREATE TABLE "PistonRiojano"."Autos" (
    NOT NULL patente,
    cantidad_puertas integer NOT NULL,
    combustible character varying(6) NOT NULL,
    CONSTRAINT ck_combustible CHECK (((combustible)::text = ANY ((ARRAY['NAFTA'::character varying, 'DIESEL'::character varying, 'GNC'::character varying])::text[])))
)
INHERITS ("PistonRiojano"."Vehiculos");


ALTER TABLE "PistonRiojano"."Autos" OWNER TO postgres;



CREATE TABLE "PistonRiojano"."Clientes" (
    dni integer NOT NULL,
    nombre character varying(20) NOT NULL,
    apellido character varying(20) NOT NULL,
    telefono numeric(10,0) NOT NULL,
    email character varying(30) NOT NULL
);


ALTER TABLE "PistonRiojano"."Clientes" OWNER TO postgres;



CREATE TABLE "PistonRiojano"."DetallesRespuestos" (
    codigo_repuesto character varying(10) NOT NULL,
    num_orden integer NOT NULL,
    precio_vigente numeric(12,2) NOT NULL
);


ALTER TABLE "PistonRiojano"."DetallesRespuestos" OWNER TO postgres;



CREATE TABLE "PistonRiojano"."Mecanicos" (
    legajo integer CONSTRAINT "Mecánicos _legajo_not_null" NOT NULL,
    nombre character varying(20) CONSTRAINT "Mecánicos _nombre_not_null" NOT NULL,
    apellidos character varying(20) CONSTRAINT "Mecánicos _apellidos_not_null" NOT NULL,
    especialidad character varying(12) CONSTRAINT "Mecánicos _especialidad_not_null" NOT NULL,
    valor_hora integer CONSTRAINT "Mecánicos _valor_hora_not_null" NOT NULL
);


ALTER TABLE "PistonRiojano"."Mecanicos" OWNER TO postgres;


CREATE TABLE "PistonRiojano"."Motos" (
    NOT NULL patente,
    cilindrada integer NOT NULL
)
INHERITS ("PistonRiojano"."Vehiculos");


ALTER TABLE "PistonRiojano"."Motos" OWNER TO postgres;



CREATE TABLE "PistonRiojano"."Ordenes" (
    numero integer NOT NULL,
    fecha_ingreso date NOT NULL,
    descripcion_problema character varying(300) NOT NULL,
    responsable integer NOT NULL,
    estado_actual character varying(13) NOT NULL,
    patente_vehiculo character varying(7) NOT NULL,
    CONSTRAINT ck_estado CHECK (((estado_actual)::text = ANY ((ARRAY['PENDIENTE'::character varying, 'EN REPARACION'::character varying, 'FINALIZADA'::character varying, 'ENTREGADA'::character varying])::text[])))
);


ALTER TABLE "PistonRiojano"."Ordenes" OWNER TO postgres;



CREATE TABLE "PistonRiojano"."Repuestos" (
    codigo character varying(10) NOT NULL,
    descripcion character varying(200) NOT NULL,
    precio_unitario numeric(12,2) NOT NULL,
    stock_disponible integer NOT NULL
);


ALTER TABLE "PistonRiojano"."Repuestos" OWNER TO postgres;


ALTER TABLE "PistonRiojano"."Mecanicos"
    ADD CONSTRAINT ck_especialidad CHECK (((especialidad)::text = ANY ((ARRAY['MOTOR'::character varying, 'ELECTRICIDAD'::character varying, 'FRENOS'::character varying, 'GENERAL'::character varying])::text[]))) NOT VALID;



ALTER TABLE ONLY "PistonRiojano"."Clientes"
    ADD CONSTRAINT "pk_Clientes" PRIMARY KEY (dni);




ALTER TABLE ONLY "PistonRiojano"."DetallesRespuestos"
    ADD CONSTRAINT "pk_DetallesRepuestos" PRIMARY KEY (codigo_repuesto, num_orden);



ALTER TABLE ONLY "PistonRiojano"."Autos"
    ADD CONSTRAINT pk_autos PRIMARY KEY (patente);




ALTER TABLE ONLY "PistonRiojano"."Mecanicos"
    ADD CONSTRAINT "pk_mecánicos" PRIMARY KEY (legajo);




ALTER TABLE ONLY "PistonRiojano"."Motos"
    ADD CONSTRAINT pk_motos PRIMARY KEY (patente);


--
-- TOC entry 4807 (class 2606 OID 32842)
-- Name: Ordenes pk_orden; Type: CONSTRAINT; Schema: PistonRiojano; Owner: postgres
--

ALTER TABLE ONLY "PistonRiojano"."Ordenes"
    ADD CONSTRAINT pk_orden PRIMARY KEY (numero);




ALTER TABLE ONLY "PistonRiojano"."Repuestos"
    ADD CONSTRAINT pk_repuestos PRIMARY KEY (codigo);



ALTER TABLE ONLY "PistonRiojano"."Vehiculos"
    ADD CONSTRAINT pk_vehiculos PRIMARY KEY (patente);



ALTER TABLE ONLY "PistonRiojano"."Clientes"
    ADD CONSTRAINT uk_dni UNIQUE (dni);



ALTER TABLE ONLY "PistonRiojano"."Clientes"
    ADD CONSTRAINT uk_email UNIQUE (email);




ALTER TABLE ONLY "PistonRiojano"."Mecanicos"
    ADD CONSTRAINT uk_legajo UNIQUE (legajo);




ALTER TABLE ONLY "PistonRiojano"."Ordenes"
    ADD CONSTRAINT uk_orden UNIQUE (numero);


ALTER TABLE ONLY "PistonRiojano"."Vehiculos"
    ADD CONSTRAINT uk_patente UNIQUE (patente);




ALTER TABLE ONLY "PistonRiojano"."Repuestos"
    ADD CONSTRAINT uk_repuestos UNIQUE (codigo);




ALTER TABLE ONLY "PistonRiojano"."Clientes"
    ADD CONSTRAINT uk_telefono UNIQUE (telefono);




ALTER TABLE ONLY "PistonRiojano"."DetallesRespuestos"
    ADD CONSTRAINT "fk_DetallesRepuestos_Ordenes" FOREIGN KEY (num_orden) REFERENCES "PistonRiojano"."Ordenes"(numero);



ALTER TABLE ONLY "PistonRiojano"."DetallesRespuestos"
    ADD CONSTRAINT "fk_DetallesRepuestos_Repuestos" FOREIGN KEY (codigo_repuesto) REFERENCES "PistonRiojano"."Repuestos"(codigo);




ALTER TABLE ONLY "PistonRiojano"."Ordenes"
    ADD CONSTRAINT fk_orden_mecanico FOREIGN KEY (responsable) REFERENCES "PistonRiojano"."Mecanicos"(legajo);




ALTER TABLE ONLY "PistonRiojano"."Ordenes"
    ADD CONSTRAINT fk_orden_vehiculo FOREIGN KEY (patente_vehiculo) REFERENCES "PistonRiojano"."Vehiculos"(patente) NOT VALID;




ALTER TABLE ONLY "PistonRiojano"."Vehiculos"
    ADD CONSTRAINT fk_vehiculos_clientes FOREIGN KEY (clientes_dni) REFERENCES "PistonRiojano"."Clientes"(dni);





