## Shop-Validator

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