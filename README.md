# pruebaTecnica
- **ES**
<br><br>
Este proyecto cuenta con un endpoint /prices/find al cual se le deben pasar por parametro tanto la fecha de aplicacion, el id del producto y el id de la marca.
<br><br>
Para poder probarlo en un servidor de tomcat hay que ejecutar la clase PruebaTecnicaApplication, la cual inicializa una base de datos en memoria y lanza la aplicacion en el servidor.
Una vez ejecutada se podra acceder mediante http://localhost:8080/prices/find, un ejemplo de peticion correcto seria: http://localhost:8080/prices/find?applicationDate=2020-06-14%2010:00:00&productId=35455&brandId=1.
<br><br>
Tambien dispone de test funcionales, que se encuentran en PriceControllerTest y contienen 5 casos de prueba.

---
- **EN**
<br><br>
This project includes an endpoint /prices/find, where the application date, product ID, and brand ID must be provided as parameters.
<br><br>
To test it on a Tomcat server, you need to run the PruebaTecnicaApplication class, which initializes an in-memory database and launches the application on the server. Once executed, you can access it via http://localhost:8080/prices/find. A correct request example would be: http://localhost:8080/prices/find?applicationDate=2020-06-14%2010:00:00&productId=35455&brandId=1.
<br><br>
The project also includes functional tests, located in PriceControllerTest, containing 5 test cases.