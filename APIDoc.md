# HTP Vault
## This is a simple project on how to use spring cloud vault (Java based)
## Hashicorp Vault is a tool for securely accessing secrets

## This project have fews api or rest service on how to encrypt and decrypt plaintext using the vault key created.
## Other feature is to rotate the key and rewrap the ciphertext using the latest key

##Author: Muhd Zabidi Wahid
##Date: 29/10/2021

##API provided by this project

1. Endpoint: http://host:port/api/orders
   Method: POST
   Example of request body: {  "id": 1,  "customerName": "John",  "productName": "Nomad",  "orderDate": "2018-04-18T22:07:42.916+0000"}
   API description: This API will create a key named "order" in vault server. Then it will encrypt customerName field using the created key. Above data in request body    will be store in table orders in postgre with encrypted customerName. 

2. Endpoint: http://host:port/api/orders
   Method: GET
   API description: This API will retrieve all ciphertext that has being decrypt with it's other information that using the key name "order".  

3. Endpoint: http://host:port/api/orders
   Method: DELETE
   API description: This API will delete the key name "order" and all the data in table orders.
   Note: delete_allowed for this key name must be set to true first to delete the key in vault.

4. Endpoint: http://host:port/api/update
   Method: POST
   API description: This API will rotate the key for the key name "order" and rewrap the ciphertext in this key using the latest key.

5. Endpoint: http://host:port/api/update
   Method: GET
   API description: This API will retrieve all the key for the key name "order".
   Note: exportable for this key must be set to true first


##Additional Info
##These two endpoints are config example using transit api provided by vault itself

1. Endpoint: http://vaultHost:vaultPort/v1/transit/keys/order/config
   Method: POST
   Example of request body: {  "exportable": "true"}

2. Endpoint: http://vaultHost:vaultPort/v1/transit/keys/order/config
   Method: POST
   Example of request body: {"deletion_allowed" : "true"}
   