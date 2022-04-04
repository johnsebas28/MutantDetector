# Mutant Detector
### Detector de mutantes - Proyecto de aprendizaje

## Tabla de contenido
- Concepto prueba
- Apoyo y documentación
- Documentación API
- Instalación
- Pruebas JACOCO

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

---

### Documentación API
URL Microservicio AWS
http://xmenmutant-env.eba-dqmzwbsa.us-east-1.elasticbeanstalk.com:5000/

#### Métodos
> ##### Post -> /mutant/
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



