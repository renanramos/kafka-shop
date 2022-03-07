# Shop

Simulação de funcionamento de uma loja com módulos separados e que se comunicam via Kafka.

## Módulos

Todos os módulos foram desenvolvidos utilizando as seguintes tecnologias:

* Spring Boot
* H2 database
* Spring Data JPA
* Spring Kafka

<b>shop-api:</b> módulo de API que disponibiliza dois end-points.</br>
<b>shop-validator:</b> módulo que executa a validação do pedidobaseando-se na existencia do produto recebido e na quantidade de itens em estoque.</br>
<b>shop-api:</b> módulo com modelos compartilhados.</br>
<b>shop-report:</b> módulo com endpoint único com total de pedidos aceitos <code>SUCCESS</code> e não aceitos <code>ERROR</code>.

### Shop-API

API composta de dois endpoints para realizar a compra de produtos `POST` e também para realizar a consulta de compras efetuadas `GET`
e o status da compra, se foi válida ou inválida.

* O arquivo `schema.sql` é responsável por criar as tabelas no banco de dados `H2`:

```
create table shop (
    id bigserial primary key auto_increment,
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

* Consultar o status da compra realizada:

```
GET: /shop

[
    {
        "identifier": "063dea07-1e5c-4999-bbfb-3dffc0ba1602",
        "dateShop": "2022-03-07",
        "status": "SUCCESS",
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

Ao efetuar o envio de uma nova compra através do <code>shop-api</code>, o Kafka envia uma mensagem ao <code>shop-validator</code>
onde realizará a validação do código do produto enviado. 

No projeto existem dois arquivos <code>schema.sql</code> e <code>data.sql</code> nos quais realizarão a criação da tabela de 
produtos válidos e a inserção de registros iniciais. Inicialmente, somente dois códigos de produtos são válidos.

Script em `schema.sql`:
```
create schema if not exists product;

create table product (
    id bigserial primary key auto_increment,
    identifier varchar(100) not null,
    amount int not null
);
```

Script em `data.sql`:

```
insert into product values(1, '123456789', 100);
insert into product values(2, '987654321', 200);
```

### Shop-Report

O módulo `shop-report` expõe um endpoint `/shop_report` que disponibiliza a quantidade de compras aceitas ou recusadas no processo
de valição de compras.

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

Módulo contento as classes modelos utilizadas entre outros módulos. Uma forma de centralizar o código evitando a repetição
de código nos diferentes módulos.