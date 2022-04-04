# Mutant Detector
### Detector de mutantes - Proyecto de aprendizaje

## Tabla de contenido
- [Concepto prueba](###Concepto-Prueba)
- [Apoyo y documentación](###Apoyo-y-documentación)
- [Documentación API](###Documentación-API)
- [Instalación](###Instalación)
- [Pruebas JACOCO](###Pruebas-JACOCO)

---

### Concepto Prueba
crear un programa con un método o función con la siguiente firma:<br>
**boolean isMutant(String[] dna);**<br>
En donde se recibirá como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.
Sabrás si un humano es mutante, **si encuentras más de una secuencia de cuatro letras
iguales**, de forma oblicua, horizontal o vertical.<br>

**Ejemplo Caso mutante:** <br>
`String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};` <br>
En este caso el llamado a la función isMutant(dna) devuelve “true”.

---

### Apoyo y documentación
[Java - reactive](https://www.youtube.com/watch?v=i0lJZeLdAi8&ab_channel=miw-upm) <br>
[baeldung](https://www.baeldung.com/)
[JACOCO](https://github.com/jacoco/jacoco)
---

### Documentación API
URL Microservicio AWS
http://xmenmutant-env.eba-dqmzwbsa.us-east-1.elasticbeanstalk.com:5000/

#### Métodos
> ##### Post -> /mutant
> Recibe una objeto el cual contiene la base nitrogenada del ADN y Devuelve una respuesta http 200-OK si el DNA enviado pertenece a un mutante. de lo contrario, si el DNA es de un humano la respuesta que devolvería es un código http 403-Forbidden <br>
> **Headers** <br>
> `Content-Type:application/json` <br>
> **Body - Ejemplo** <br>
> `{
    "dna": [
        "ATGCAA",
        "CAGTGG",
        "TTATGA",
        "AGAAGA",
        "CCCCTA",
        "CCCCTG"
    ]
}`<br>
> #### Response
> - HTTP Status 200-OK (Is mutant)
> - HTTP Status 403-Forbidden (is Human)

> ##### Get -> /stats
> Devuelve las estadísticas de la base de datos. Cantidad de DNA mutante procesado y cantidad de DNA Humano procesado <br>
> #### Response - Ejemplo
>  `{
    "count_mutant_dna": 6,
    "count_human_dna": 2,
    "ratio": 3.0
} `

---

### Instalación
#### Requisitos:
- Java jdk 11.0.13
- apache-maven-3.8.5

#### Instrucciones:
1. Clonar el repositorio <br>
`git clone https://github.com/johnsebas28/MutantDetector.git`

2. Cambiar variables de configuración. Abrir el archivo `application.properties` ubicado en el path `src\main\resources` <br>

<pre><code>server.port=5000
spring.datasource.url=[Your Mysql Database URL ]
spring.datasource.username=[YourUser]
spring.datasource.password=[yourPassword]
spring.jpa.hibernate.ddl-auto=update</code></pre>

3. Entrar a la carpeta del proyecto (Root Path) para compilarlo y hostearlo a través del siguiente comando (Windows)<br>
`.\mvnw.cmd spring-boot:run` 
4. El proyecto está configurado para abrir en el puerto 5000<br>
`.\mvnw.cmd spring-boot:run` <br>
Cuando el proyecto compila, se crea la Base de datos en AWS Gracias a JPS.Hibernate. <br>
`spring.jpa.hibernate.ddl-auto=update`

---

### Pruebas JACOCO
Para realizar las pruebas y obtener el porcentaje de cobertura se utlizió la librería Jacoco.

#### Instrucciones:
1. Entrar a la carpeta del proyecto a través de un terminal (root Path)
2. Correr el siguiete comando <br>
   `mvn clean test` <br>
3. Para conocer el porcentaje de cobertura de las pruebas:
    - Entrar a la ruta `\target\site\jacoco` y abrir el archivo `index.html`
> **Nota**: El proyecto cuenta con una cobertura del 92%
