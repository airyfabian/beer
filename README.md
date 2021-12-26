# beer

#Problema

Bender es fanático de las cervezas, y quiere tener un registro de todas las cervezas que prueba y cómo calcular el precio que necesita para comprar una caja de algún tipo especifico de cervezas. Para esto necesita una API REST con esta información que posteriormente compartir con sus amigos.

#Funcionalidad

GET /beers: Lista todas las cervezas que se encuentran en el sistema

POST /beers/{beerID}: Lista un detalle de una cerveza especifica

GET /beers/{beerID}/boxprice: Entrega el valor que cuesta una caja especifica de cerveza dependiendo de los parámetros ingresados, esto quiere decir que multiplique el precio por la cantidad una vez se homologa la moneda a la que se ingresó por parámetro. 
 - Quanty: Cantidad de cervezas a comprar (valor por defecto 6) 
 - Currency: Tipo de moneda con que desea pagar.

