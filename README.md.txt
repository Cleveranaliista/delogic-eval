# Delogic Eval Project

## O projeto roda nesse diretório   cd C:/Users/cleve/Workbook/Teste/delogic-eval
## mvn spring-boot:run


## Pré-requisitos

- **Java 17** ou superior
- **Maven** instalado na máquina
- **MySQL** configurado (ou outro banco de dados compatível)
- Ferramenta para executar queries SQL, como MySQL Workbench ou DBeaver



## Configuração do Banco de Dados

1. Crie um banco de dados no MySQL:
   ```sql
   CREATE DATABASE delogic_eval;


##spring.datasource.url=jdbc:mysql://localhost:3306/delogic_eval
##spring.datasource.username=SEU_USUARIO
##spring.datasource.password=SUA_SENHA


##Swagger  http://localhost:8080/swagger-ui/index.html
