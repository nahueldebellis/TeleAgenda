# Herramientas
Decidi usar gradle, git, java y postgres.
Las versiones que se piden las hice como tags de git.

# General
Como no conocia bien las herramientas lo primero que hice fue leer un poco de ellas, algunas guias de spring y algunas de hibernate.
Por esto mismo la primera parte me dedique a levantar el entorno con su base de datos.
Luego trate de aplicar la forma de trabajo que venia haciendo de java, mezclado con lo que lei de el framework.

# Agenda Telefonica

Lo primero que hice fue ver que se me pedia y crear un diseño inicial de esta API.
Pense que desde un inicio podria guardar los datos que se me piden con regex asi todo el tema del formato esta solucionado.
Lo primero que me genero dudas del enunciado fueron las etras, hasta que me di cuenta que se correspondian con el teclado de los celulares viejos,
ahi me di cuenta que podia usar una regex y ya. Creo que esto se puede mejorar pero fue la primera idea que se me ocurrio.
Despues me di cuenta que queria implementar alguna especie de filtro para poder filtrar por provincia o por fechas de creacion.

# Mejoras propuestas
- Que el registro de contactos sea por un owner/user que pueda autentificarese y obtener sus contactos.
- Tener en DB todos los ![codigos de area](https://www.argentina.gob.ar/pais/codigo-telefonia) para no tener que pedirle en la api que siga un formato.
- Un cliente GUI para esta api.


