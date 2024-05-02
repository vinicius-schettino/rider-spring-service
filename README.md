# RideR
<img width="200" alt="Screenshot 2024-05-02 at 16 04 40" src="https://github.com/vinicius-schettino/rider-spring-service/assets/7289698/3db54b3b-914f-4e85-90c6-252ab0499296">



Template Base para o projeto de Desenvolvimento Web - Backend em Spring. [Mais detalhes nos slides](https://docs.google.com/presentation/d/1qGXsu2uaL51Oo81beAYPUmmJt8OP3z4A8cipoDWGXVg/edit?usp=sharing)

## Atividade

> [!NOTE]
> [TravelAPI ](https://github.com/vinicius-schettino/travel-api)vai ser nossa referência.

Vamos desenvolver um sistema de transporte por aplicativo, onde cada grupo será responsável (owner) por um dos serviços que integram a aplicação. O esquemático abaixo ilustra a arquitetura proposta:

![image](https://github.com/vinicius-schettino/rider-spring-service/assets/7289698/c904fe40-ae2b-4c83-ab42-3de08cc4c725)

A divisão dos [grupos](https://github.com/orgs/vinicius-schettino/teams) por enquanto ficou:

- Discípulos de Chico Moedas
    - Driver
- Travelers
  - Payment
- RiseCode
  - Ride
- Periferia da Gastronomia
  - User

O desenvolvimento será incremental, com checkpoints para avaliação a cada parte. 


### Parte #01 - CRUD

> [!WARNING]
> Prazo da Primeira Parte: 16/05/2024 (Tudo aprovado e na `main`)

> [!WARNING]
> Utilizar o /`$SERVIÇO` como prefixo para as URLs

O objetivo dessa primeira parte é ter uma primeira versão funcional de cada serviço, englobando as funcionalidades básicas de CRUD.


#### Checklist
- [ ] Criar o código inicial do projeto com Spring Initializr [aqui](https://start.spring.io/) ou pelo [próprio IntelliJ](https://www.jetbrains.com/help/idea/spring-initializr-project-wizard.html#step-1-basic-project-configuration). O nome do pacote deve ser `com.rider.$SERVICO`, por exemplo `com.rider.payment`
- [ ]  Adicione as seguintes dependências:
  - [ ] org.springframework.boot:spring-boot-starter-hateoas
  - [ ] org.springframework.boot:spring-boot-starter-validation
  - [ ] org.springframework.boot:spring-boot-starter-data-rest
  - [ ] org.springframework.boot:spring-boot-starter-data-jpa
  - [ ] org.springframework.data:spring-data-rest-hal-explorer
- [ ] [Habilitar o endpoint OpenAPI](https://www.baeldung.com/spring-rest-openapi-documentation)
- [ ] Criar as entidades e repositórios com os endpoints de CRUD e validação básica
- [ ] Criar SQL com dados de exemplo/teste [(como fizemos aqui](https://github.com/vinicius-schettino/travel-api/blob/main/src/main/resources/data.sql)) (~20 registros ao todo, pode usar ChatGPT) 
- [ ] Criar um ou mais Pull Requests com as modificações e marcar @vschettino para revisão
