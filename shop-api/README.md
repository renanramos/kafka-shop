## Shop-API

API composta de dois endpoints para realizar a compra de produtos `POST` e também para realizar a consulta de compras efetuadas `GET`
e o status da compra, se foi válida ou inválida.

* O arquivo `schema.sql` é responsável por criar as tabelas no banco de dados `H2`:

```
create table shop (
    id bigserial primary key auto_increment,
    buyer_identifier varchar(100) not null,
    identifier varchar not null,
    status varchar not null,
    date_shop date
);

create table shop_item (
    id bigserial primary key auto_increment,
    product_identifier varchar(100) not null,
    amount int not null,
    price float not null,
    shop_id bigint REFERENCES shop(id)
);
```


* Efetuar o <code>POST</code> de uma nova compra:

```
POST: /shop

{
    "buyerIdentifier": "b-1",
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
    "buyerIdentifier": "b-1",
    "items": [
        {
            "productIdentifier": "123456789",
            "amount": 1,
            "price": 99999.0
        }
    ]
}
```

* Consultar o status da compra realizada:

```
GET: /shop

[
    {
        "identifier": "063dea07-1e5c-4999-bbfb-3dffc0ba1602",
        "dateShop": "2022-03-07",
        "status": "SUCCESS",
        "buyerIdentifier": "b-1",
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