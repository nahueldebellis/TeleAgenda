# TeleAgenda

TeleAgenda es una API que agenda de contactos donde se podran guardar los contactos que uno tenga en el celular.
Esta es una alternativa al guardado de contactos de la cuenta de google o del guardado local de contactos.
Esta desarrollado en java con springboot

# Agenda Telefonica

Lo primero que hice fue ver que se me pedia y crear un diseño inicial de esta API.
Pense que desde un inicio podria guardar los datos que se me piden con regex asi todo el tema del formato esta solucionado.
Lo primero que me genero dudas del enunciado fueron las letras, hasta que me di cuenta que se correspondian con el teclado de los celulares viejos,
ahi vi que podia usar una regex y ya. Creo que esto se puede mejorar pero fue la primera idea que se me ocurrio.

Despues queria implementar alguna especie de filtro para poder filtrar por provincia o por fechas de creacion.
Esto lo hice con Specifications que segun lei es la forma que te da Spring de crear tus querys propias retornando Predicates de hibernate.

Para la segunda version simplemente especifique que se va tener que mandarlo por un parametro aparte opcional porque sino no tenia forma de identificar cuantos numeros son parte de este.
Esto lo pense asi por que si a futuro quiero indicar la provincia sin poner el codigo de area puedo reutilizar ese parametro.
Otra desicion de formato que tome para la api fue que no se indique el +54 ya que la api solo se pense en argentina.
Y para la paginacion aplique un Pageable que da Spring, el cual aplica el limit en la query y me deja ir geteando por paginas.

Lo unico que me falta es mejorar los errores que se muestran al cliente de la api.

# Diseño

Soy conciente que existen formas de documentar una api como OpenApi, pero decidi no usarlo.

## /contacto {name: required, phone: requires, codArea: optinal}

- POST
- crea un nuevo contacto
- usa la fecha actual para setear el created_at

## /contacto/<name>

- GET
- muestra el primer contacto que encuentre con eses nombre.

## /contactos?page=&size&name=&provincia=&created_from=

- GET 

Lista por pagina los distintos contactos, por default los muestra de a 2.
deja filtrar por ciertos campos:

- name: parte del nombre del contacto
- provincia: parte del codigo de area/provincia
- created_from: a partir de que fecha fue creado

# Herramientas

Decidi usar:

------------------------------------------------------------
Gradle 7.4.2

Build time:   2022-03-31 15:25:29 UTC
Revision:     540473b8118064efcc264694cbcaa4b677f61041

Kotlin:       1.5.31
Groovy:       3.0.9
Ant:          Apache Ant(TM) version 1.10.11 compiled on July 10 2021
JVM:          17.0.3.1 (Oracle Corporation 17.0.3.1+2-LTS-6)
OS:           Windows 10 10.0 amd64

------------------------------------------------------------
java 17.0.3.1 2022-04-22 LTS
Java(TM) SE Runtime Environment (build 17.0.3.1+2-LTS-6)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.3.1+2-LTS-6, mixed mode, sharing)# General

------------------------------------------------------------

Las versiones que se piden las hice como tags de git.

Como no conocia bien las herramientas lo primero que hice fue leer un poco de ellas, algunas guias de spring y algunas de hibernate.
Por esto mismo la primera parte me dedique a levantar el entorno con su base de datos.
Luego trate de aplicar la forma de trabajo que venia haciendo de java, mezclado con lo que lei de el framework.

# Mejoras propuestas a futuro

- Que el registro de contactos sea por un owner/user que pueda autentificarese y obtener sus contactos.
- Tener en DB todos los ![codigos de area](https://www.argentina.gob.ar/pais/codigo-telefonia) para no tener que pedirle en la api que siga un formato.
  por esto es que el campo y el filtro lo llame provincia en vez de codigo de area. Apunto a que en un futuro cambie.
- Un cliente GUI para esta api.
- Poder integrarlo con el celular para bajar los contactos y que los guarde.
- Poder integrar con el celular un import de todos los contactos.

