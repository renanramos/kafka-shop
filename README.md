# Shop

This project simulates the operation of a shop with separate modules that communicate through Kafka.

## Modules

All modules in this project have been developed using the following technologies:

- Spring Boot
- H2 database
- Spring Data JPA
- Spring Kafka

### **shop-api:**
This module provides two endpoints for the shop API.

### **shop-common:**
It contains global Kafka implementations and configurations.

### **shop-model:**
This module comprises shared models.

### **shop-report:**
The module includes a single endpoint for tracking the total count of accepted orders (code: `SUCCESS`) and rejected orders (code: `ERROR`).

### **shop-retry:**
This module is dedicated to simulating retries in case of failure.

### **shop-validator:**
It performs order validation based on product existence and item quantity in stock.

## Shop-API

The shop API consists of two endpoints for product purchase (using `POST`) and for querying completed purchases (using `GET`), including the purchase status (valid or invalid).

### Database Schema

The database schema is defined in the `schema.sql` file, responsible for creating tables in the H2 database:

```sql
CREATE TABLE shop (
    id BIGSERIAL PRIMARY KEY AUTO_INCREMENT,
    identifier VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    date_shop DATE
);

CREATE TABLE shop_item (
    id BIGSERIAL PRIMARY KEY AUTO_INCREMENT,
    product_identifier VARCHAR(100) NOT NULL,
    amount INT NOT NULL,
    price FLOAT NOT NULL,
    shop_id BIGINT REFERENCES shop(id)
);
```

### Making a new Purchase (POST)

To initiate a new purchase, use a `POST` request:
```
POST: /shop

{
    "buyerIdentifier": "1234",   <-- any code here
    "items": [
        {
            "productIdentifier": "123456789",
            "amount": "1",
            "price": "99999"
        }
    ]
}

RESPONSE:

{
    "identifier": "063dea07-1e5c-4999-bbfb-3dffc0ba1602",
    "dateShop": "2022-03-07",
    "status": "PENDING",
    "items": [
        {
            "productIdentifier": "123456789",
            "amount": 1,
            "price": 99999.0
        }
    ]
}
```
### Querying purchase status (GET)

To check the status of a completed purchase, use a `GET` request:

```
GET: /shop

[
    {
        "identifier": "063dea07-1e5c-4999-bbfb-3dffc0ba1602",
        "dateShop": "2022-03-07",
        "status": "SUCCESS",
        "buyerIdentifier": "1234",
        "items": [
            {
                "productIdentifier": "123456789",
                "amount": 1,
                "price": 99999.0
            }
        ]
    }
]
```

### Shop-Validator

When a new purchase is sent through `shop-api`, Kafka sends a message to `shop-validator`, which validates the product
code sent.

### Database Setup
In this project, two SQL files, `schema.sql` and `data.sql`, create a table of valid products and insert initial records.
Initially, only two product codes are considered valid.

`schema.sql` script:

```
CREATE SCHEMA IF NOT EXISTS product;

CREATE TABLE product (
id BIGSERIAL PRIMARY KEY AUTO_INCREMENT,
identifier VARCHAR(100) NOT NULL,
amount INT NOT NULL
);
```

`data.sql` script:

``` 
INSERT INTO product VALUES(1, '123456789', 100);
INSERT INTO product VALUES(2, '987654321', 200);
```

### Shop-Report
The shop-report module exposes an endpoint, `/shop_report`, which provides information on the number of accepted
(code: `SUCCESS`) and rejected (code: `ERROR`) purchases in the validation process.

```
GET: /shop_report

[
    {
        "identifier": "SUCCESS",
        "amount": 1
    },
    {
        "identifier": "ERROR",
        "amount": 0
    }
]
```

### Shop-Model
This module contains model classes shared among various other modules, reducing code repetition.

### Shop-Common
The shop-common module is designed to contain all Kafka services used by other modules, providing a centralized approach
to Kafka-related functionalities.

### Setting up the application

The entire Kafka Shop project relies on Kafka application. To get the Kafka application up and running, you'll need to 
execute the `docker-compose` command. Follow these steps:

1- Make sure you're in the root path.
2- Start the Kafka server by running the following command to initialize the Kafka application

```
docker-compose -f misc/docker/docker-compose up
```
