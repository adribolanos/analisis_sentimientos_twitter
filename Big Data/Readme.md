# Read me!

Hola, estos archivos te ayudarán a realizar un análisis de sentimientos en twitter. Se divide en dos partes, extracción de los tweets y análisis de los mismos. Espero que te sea útil :)  


# Extracción de Tweets

1. Descargaremos el proyecto con el botón verde "Clonar o Descargar".Puedes ejecutar la función desde la línea de comando llamando al intérprete con el archivo tweepy_adri.py. Este archivo es el que realiza la función.  
2. Se crea un csv automáticamente al ejecutarlo, que se encuentra en el repositorio con el nombre resultadotweet.csv

# Análisis de sentimiento
Se realiza con el método map reduce. Se toma como base el csv generado en el paso anterior, se eliminan los campos innecesarios(favoritos, retweets, y fecha).
Se analiza cada tweet y se realiza un nuevo csv con las puntuaciones de cada tweet. Este documento se encuentra como sentimiento.csv
Se necesita Intellij, hadoop, maven. 
Las clases y el pom estarán en el repositorio pero no el proyecto completo por cuestiones de tamaño.

### Observaciones
> Para extraer los tweets en este proyecto se necesita tener instalado el intérprete de python, y las claves de licencia de tweepy.
> Para realizar el análisis de sentimientos se necesitan las credenciales de google natural language, intellij.
