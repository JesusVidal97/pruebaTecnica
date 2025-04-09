# pruebaTecnica
- **ES**
<br><br>
Este proyecto cuenta con un endpoint /prices/find al cual se le deben pasar por parametro tanto la fecha de aplicacion, **que debe tener el formato "yyyy-MM-dd HH:mm:ss"**, el id del producto, **que debe ser un numero entero postivo** y el id de la marca, **que debe ser un numero entero postivo**. **TODOS LOS PARAMETROS SON OBLIGATORIOS**
<br><br>
Para poder probarlo en un servidor de tomcat hay que ejecutar la clase PruebaTecnicaApplication, la cual inicializa una base de datos en memoria y lanza la aplicacion en el servidor.
Una vez ejecutada se podra acceder mediante http://localhost:8080/prices/find, un ejemplo de peticion correcto seria: http://localhost:8080/prices/find?applicationDate=2020-06-14%2010:00:00&productId=35455&brandId=1.
<br><br>
Se podran encontrar test unitarios de la aplicacion en com.jesus.prueba_tecnica.unitarios
<br>
Tambien dispone de test funcionales, que se encuentran en com.jesus.prueba_tecnica.funcionales.PriceIntegrationTest.java y contienen 5 casos de prueba.

---
- **EN**
<br><br>
This project includes an endpoint /prices/find, where the application date, **which must be in the format "yyyy-MM-dd HH:mm:ss"**, product ID, **which must be a positive integer**, and brand ID. **which must also be a positive integer** must be provided as parameters. **ALL PARAMETERS ARE REQUIRED.**
<br><br>
To test it on a Tomcat server, you need to run the PruebaTecnicaApplication class, which initializes an in-memory database and launches the application on the server. Once executed, you can access it via http://localhost:8080/prices/find. A correct request example would be: http://localhost:8080/prices/find?applicationDate=2020-06-14%2010:00:00&productId=35455&brandId=1.
<br><br>
You can find unit tests for the application in com.jesus.prueba_tecnica.unitarios.
<br>
The project also includes functional tests, located in com.jesus.prueba_tecnica.funcionales.PriceIntegrationTest.java, containing 5 test cases.