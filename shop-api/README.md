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
