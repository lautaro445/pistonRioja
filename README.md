PistonRiojano

Sistema de gestión para un taller mecánico, desarrollado en Java utilizando interfaz gráfica Swing y base de datos PostgreSQL.

El sistema permite gestionar vehículos, clientes, mecánicos, repuestos y órdenes de trabajo.

1. Tecnologías y versiones utilizadas

El proyecto fue desarrollado y probado utilizando las siguientes tecnologías:

Tecnología	Versión
Java (OpenJDK)	21.0.2
Java Compiler (Javac)	21.0.2
JDK incluido en NetBeans	Temurin 26.0.2
Apache Maven	3.9.11
PostgreSQL	18.6
PostgreSQL JDBC Driver	42.7.8
Nivel de compilación Java	17
Sistema operativo de desarrollo	Linux Fedora

Aclaraciones:

El proyecto utiliza Java 17 como nivel de compilación, según la configuración del archivo pom.xml.
Se recomienda utilizar JDK 21 o superior.
NetBeans utiliza un JDK propio (Temurin 26.0.2), pero no es obligatorio utilizar esa misma versión.
pgAdmin 4 es opcional y permite administrar PostgreSQL mediante una interfaz gráfica.
Maven se encarga de administrar las dependencias del proyecto.
2. Descarga del proyecto

El código fuente de PistonRiojano se encuentra alojado en GitHub:

Repositorio: https://github.com/lautaro445/pistonRioja

Como descargar el proyecto.

Descargar ZIP
Ingresar al repositorio de GitHub.
Presionar el botón verde Code.
Seleccionar Download ZIP.
Extraer el archivo descargado en una carpeta de preferencia.
Abrir el proyecto desde Apache NetBeans.
Opción 2: Clonar mediante Git

Si se tiene Git instalado, abrir una terminal y ejecutar:

bash
git clone https://github.com/lautaro445/pistonRioja.git


Si el proyecto ya está descargado y se desea obtener la última versión:

bash
git pull


En Windows también se puede realizar mediante GitHub Desktop, utilizando las opciones Clone repository y Pull origin.

3. Contenido de la descarga

Al descargar el repositorio se obtendrán los siguientes archivos:

Proyecto Java: contiene el código fuente de la aplicación, organizado en sus respectivos paquetes.
SchemaSQL: contiene las instrucciones SQL necesarias para crear el esquema y las tablas de la base de datos.
Datos de prueba: contiene registros de ejemplo para poder probar las funcionalidades del sistema.

Importante: si se desea volver a utilizar el proyecto desde cero, se debe ejecutar nuevamente el archivo SchemaSQL para reconstruir el esquema de la base de datos. Esto puede eliminar los datos existentes, por lo que se recomienda realizar una copia de seguridad antes de volver a ejecutarlo.

4. Requisitos previos

Antes de ejecutar el proyecto, es necesario contar con:

Java JDK 21 o superior.
Apache NetBeans.
Apache Maven 3.9 o superior.
PostgreSQL 18.6 (o una versión compatible).
Controlador JDBC de PostgreSQL (incluido mediante Maven).
Git (opcional, para clonar y actualizar el repositorio).

pgAdmin 4 es opcional y puede utilizarse para administrar la base de datos gráficamente.

5. Instalación de PostgreSQL

Es necesario tener PostgreSQL instalado y funcionando antes de ejecutar la aplicación.

Windows
Ingresar a la página oficial:

https://www.postgresql.org/download/windows/

Descargar el instalador correspondiente.
Ejecutar el instalador y seguir los pasos.
Durante la instalación, configurar la contraseña del usuario postgres.
Se recomienda instalar pgAdmin 4 para administrar la base de datos mediante una interfaz gráfica.
Debian

Abrir una terminal y ejecutar:

bash
sudo apt update
sudo apt install postgresql postgresql-contrib


Comprobar que el servicio esté funcionando:

bash
sudo systemctl status postgresql


Si no está iniciado:

bash
sudo systemctl start postgresql

Fedora

Abrir una terminal y ejecutar:

bash
sudo dnf install postgresql-server postgresql-contrib


Inicializar la base de datos:

bash
sudo postgresql-setup --initdb


Iniciar y habilitar el servicio:

bash
sudo systemctl enable --now postgresql


Para comprobar su funcionamiento:

bash
sudo systemctl status postgresql

6. Configuración de la base de datos

Una vez instalado PostgreSQL, se debe crear y configurar la base de datos para el proyecto.

Ejecución del SchemaSQL

El archivo SchemaSQL incluido en el repositorio contiene las instrucciones necesarias para crear el esquema PistonRiojano y sus respectivas tablas.

Se debe ejecutar utilizando PostgreSQL.

Windows
Abrir pgAdmin 4.
Conectarse al servidor PostgreSQL.
Abrir la herramienta Query Tool.
Abrir el archivo SchemaSQL.
Ejecutar todas las instrucciones SQL.
Comprobar que el esquema y las tablas se hayan creado correctamente.
Debian y Fedora

Se puede utilizar la terminal para ejecutar el archivo:

bash
sudo -u postgres psql -d NOMBRE_BASE_DATOS -f ruta/al/SchemaSQL.sql


El nombre de la base de datos debe coincidir con el utilizado en la configuración del proyecto.

Usuario de PostgreSQL

El SchemaSQL configura los permisos correspondientes para el usuario postgres.

Por lo tanto, se recomienda utilizar dicho usuario para la conexión.

Si no existe un usuario postgres, se deberá crear uno y otorgarle los permisos necesarios antes de ejecutar el esquema.

Datos de prueba

Los datos de prueba también se encuentran incluidos en el repositorio.

Una vez creado el esquema, se pueden ejecutar las instrucciones SQL correspondientes para cargar los registros de ejemplo.

Estos datos permiten probar las funcionalidades del sistema sin necesidad de cargar manualmente toda la información.

7. Configuración de la conexión Java

Antes de ejecutar la aplicación, es necesario configurar los datos de conexión a PostgreSQL.

Abrir el proyecto desde Apache NetBeans.
Buscar el paquete pistonriojano.dao.
Abrir la clase Conexion.java.
Localizar los parámetros de conexión a la base de datos.
Modificar los valores correspondientes según la configuración local.

Se deben revisar los siguientes parámetros:

Parámetro	Valor de ejemplo
Host	localhost
Puerto	5432
Base de datos	Nombre de la base creada
Usuario	postgres
Contraseña	Contraseña configurada en PostgreSQL

Una vez modificados los parámetros, guardar los cambios.

8. Comprobar la conexión

Para verificar que la aplicación puede conectarse correctamente a PostgreSQL:

Abrir la clase Conexion.java.
Ejecutar su método principal, si está disponible, o utilizar el mecanismo de prueba de conexión implementado en dicha clase.
Comprobar el mensaje de salida en la consola.

Si la conexión es correcta, se mostrará el siguiente mensaje:

text
¡Conexión exitosa!


Si aparece un error, verificar:

Que PostgreSQL esté iniciado.
Que el nombre de la base de datos sea correcto.
Que el usuario y la contraseña sean válidos.
Que el puerto de conexión sea el correcto.
Que los permisos del usuario sean suficientes.
9. Ejecución de la aplicación

Una vez configurada y comprobada la conexión:

Abrir el proyecto en Apache NetBeans.
Esperar a que Maven descargue las dependencias necesarias.
Localizar la clase Main.java.
Ejecutar el método main.
Esperar a que se inicie la interfaz gráfica.

La aplicación estará lista para utilizarse.

10. Dependencias

El proyecto utiliza Maven para administrar sus dependencias.

El controlador JDBC de PostgreSQL se encuentra declarado en el archivo pom.xml:

Dependencia	Versión
PostgreSQL JDBC Driver	42.7.8

Maven se encarga de descargar las dependencias necesarias al compilar o ejecutar el proyecto.

No es necesario descargar manualmente el controlador JDBC.

11. Recursos oficiales
Java: https://www.oracle.com/java/technologies/downloads/
Apache NetBeans: https://netbeans.apache.org/
Apache Maven: https://maven.apache.org/download.cgi
PostgreSQL: https://www.postgresql.org/download/
pgAdmin: https://www.pgadmin.org/download/
Git: https://git-scm.com/downloads
GitHub: https://github.com/lautaro445/pistonRioja

PistonRiojano

Sistema de gestión para talleres mecánicos.
