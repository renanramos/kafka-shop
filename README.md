# Shop

Simulação de funcionamento de uma loja com módulos
separados e que se comunicam via Kafka.

> Projeto em andamento e em desenvolvimento

## Módulos

Existem (até no momento) três diferentes módulos:

<ul>
    <li>
        <b>shop-api:</b> módulo de API que disponibiliza dois end-points
    </li>
    <li>
        <b>shop-validator:</b> módulo que executa a validação do pedidobaseando-se na existencia do produto recebido e na quantidade de itens em estoque
    </li>
    <li>
        <b>shop-api:</b> módulo com modelos compartilhados
    </li>
</ul>