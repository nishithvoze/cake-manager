Working steps:

1. Search for all cakes list - http://localhost:8081/

2. Add a cake by giving CakeEntity fields in body using url - http://localhost:8081/cakes

Sample Curl :

curl --location --request POST 'http://localhost:8081/cakes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title":"Chocolate Truffle",
    "description":"Delicious Dark Chocolate Truffle Cake",
    "image":"https://cdn.shopify.com/s/files/1/0034/8343/5072/products/WhatsAppImage2020-12-21at8.58.35PM_b0dcaa2a-e46f-4037-946b-a6a777316814.jpg?v=1610523901"
}'

3. Try to see list of cakes now using url - http://localhost:8081/cakes
It will have above cake added in the list.

4. Search for Cake by giving its title like - http://localhost:8081/cakes/Chocolate%20Truffle


------------

Change done :

CakeController - Controller with all api mapping. Also initializing the cake list.

CakeEntity - Cake object with its constructor and fields and getter/setters. Changes name of table and some fields.

CakeRepository - saving / retrieving the data for cakes.

CakeManager - Service layer having all methods to show, search, save cakes.

CakeUtils - Read all cakes from url and save them. Showing all cakes list in tabular form. Showing searched cake details in tabular form.

CakeApplication - Running the spring application

application.properties - just added to change port

pom.xml - all dependencies added

