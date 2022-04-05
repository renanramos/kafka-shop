## Shop-Report

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