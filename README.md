# Inventory Manager
This project is made for managing inventory. Basic features like Adding/Updating/Deleting Inventories and Items has been created. Additional featur of Creating a Shipment that sends Inventory items from one inventory to another has also been coded.
Technology Stack used are: Spring boot (Java) for backend and MongoDB (Database).

## Installation
Java v8 is required to run the program.

## Execution
To run the project: Open Terminal and move to directory inventory/src/main/java/com/example/inventory
```bash
java InventoryApplication.java
```

## REST-API
API details to manage inventory

## Inventories
Used to play with inventories

**URL** : `/inventories` : Retreieves all the Inventories

**Method** : `GET`

### Response
```json
[
  {
    "id": "Inventory Id",
    "inventoryName": "Name of Inventory",
    "location": "Location of the Inventory"
  }
]
```
**URL** : `/inventories` : Add an Inventory

**Method** : `POST`

**Data**

```json
  {
    "inventoryName": "Name of the Inventory",
    "location": "Location of the Inventory"
  }
```

### Response
**Content** : `Inventory Added`

**URL** : `/inventories` : Add an Inventory

**Method** : `PUT`

**Data**

```json
  {
    "id": "inventiory ID",
    "inventoryName": "Name of the Inventory",
    "location": "Location of the Inventory"
  }
```

### Response
**Content** : `Inventory Updated`

**URL** : `/inventories/{ID}` : Delete an Inventory

**Method** : `DELETE`

### Response
**Content** : `Inventory Deleted`


## Items
Used to play with Items of Inventory

**URL** : `/items` : Retreieves all the Items

**Method** : `GET`

### Response
```json
[
  {
    "id": "Item Id",
    "desc": "Item Description",
    "itemName": "Name of the Iten"
  }
]
```
**URL** : `/items` : Add an Item

**Method** : `POST`

**Data**

```json
  {
    "itemName": "Name of the Item",
    "desc": "Description of the Item"
  }
```

### Response
**Content** : `Item Added`

**URL** : `/items` : Update an Inventory

**Method** : `PUT`

**Data**

```json
  {
    "id": "item ID",
    "itemName": "Name of the Item",
    "desc": "Description of the Item"
  }
```

### Response
**Content** : `Item Updated`

**URL** : `/items/{ID}` : Delete an Item

**Method** : `DELETE`

### Response
**Content** : `Item Deleted`

## Managing Inventory Item
Used to play with Items of Inventory

**URL** : `/inventories/{id}` : Retreieves all the items of given inventory

**Method** : `GET`

### Response
```json
[
    {
        "item": {
            "id": "Item Id",
            "desc": "Item Description",
            "itemName": "item name"
        },
        "nos": Quantity of Items
    }
]
```
**URL** : `/inventories/{id}/add` : Add an Items to inventory

**Method** : `POST`

**Data**

```json
[
  {
    "itemId": "Item Id",
    "nos": quantity of Items
  }
 ]
```

### Response
**Content** : `Items Added`

**URL** : `/inventories/{id}/remove` : Remove Items to inventory

**Method** : `POST`

**Data**

```json
[
  {
    "itemId": "Item Id",
    "nos": quantity of Items
  }
 ]
```
### Response
**Content** : `Items Removed`

##Managing Shipments
Used to manage Shipments

**URL** : `/shipment` : Retreieves all the Shipments

**Method** : `GET`

### Response
```json
[
  {
        "id": "Inventory ID",
        "from": "Sender ID",
        "to": "Receiver ID",
        "items": [
            {
                "itemId": "Shipped Item ID",
                "nos": Quantity of Items
            }
        ]
    }
]
```
**URL** : `/shipment` : Create a Shipment

**Method** : `POST`

**Data**

```json
  {
        "from": "Sender ID",
        "to": "Receiver ID",
        "items": [
            {
                "itemId": "Item ID (to be shipped)",
                "nos": Quantity of Items
            }
        ]
  }
```

### Response
**Content** : `Shipment Created`




