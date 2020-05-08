# Descrição

Para a solução do [desafio](challenge.md) optei por construir o serviço utilizando o [Micronaut](https://micronaut.io/).

Na aplicação tentei presar pela simplicidade no código. A validação das regras, poderia, inclusive, ter sido feita utilizando apenas uma expressão regular, mas para tornar melhor a legibilidade e manutenção, preferi manter expressões diferentes, dessa forma é possível apontar cada validação que não tenha passado.

Alguns pontos que eu acho interessante mencionar são:

- O desafio não dizia nas regras de validação, que um carácter de espaço em branco deveria ser considerado inválido, portanto, a aplicacão não deve apontar erro caso o carácter em branco esteja no meio da senha. Porém antes de executar as validações é feito um trim() do conteúdo, isso significa que apenas carácteres em branco no meio do conteúdo serão validados.

- O problema descrito pedia que fosse produzido como resultado um booleano, esse valor indica se a senha é ou não válida, como não acho interessante um serviço que não retorna uma estrutura bem definida, optei por criar uma estrtura de retorno com o boleano e algumas informações adicionais sobre a validação.

- A URI para validação da senha está como `/`, entendo que por se tratar de uma api unicamente para validação da senha, o DNS da API deverá representar sua funcionalidade, dessa forma se tornaria desnecessáio um contexto para a chamada. 

## Como executar

Para executar a aplicação existem dois caminhos: executando tudo pelo gradle ou gerando uma imagem do docker.

## Build

Para fazer o build basta executar o seguinte comando:

```$xslt
./gradlew clean build
```

## Execução

### Execução pelo gradle

Para executar pelo gradle basta rodar o comando:

```$xslt
./gradlew run
```

### Execução pelo docker

Para executar pelo docker devemos primeiro criar a imagem e em seguida executar.

```$xslt
docker build --tag backend-challenge:1.0.0 .
```

O comando acima deve gerar a imagem de acordo com o descrito no [Dockerfile](Dockerfile). Em seguida execute a imagem.

```$xslt
docker run -d --publish 8080:8080 backend-challenge:1.0.0
```

## Como usar

Independente do caminho seguido para executar a aplicação (gradle ou docker), a aplicação deve ser exposta na porta 8080. Para garantir que a aplicação está no ar é possível fazer uma chamada ao seu health check.

```$xslt
curl --request GET 'http://localhost:8080/health'
```

A chamada deve retornar um status http 200 indicando que a aplicação está no ar.

Para obter o contrato da aplicação em open api basta acessar o endereço:

```$xslt
http://localhost:8080/swagger/password-validation-service-1.0.0.yml
```

### Exemplos de requisições

Seguem duas chamadas com suas respectivas respostas para casos de sucesso e de erro na validaçõa da senha.

#### Senha válida

Request:
```$xslt
curl -v --request POST 'http://localhost:8080/' \
--header 'Content-Type: application/json' \
--data-raw '{
        "password": "AbTp9!foo"
}'
``` 

Response:
```$xslt
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> POST / HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> Content-Type: application/json
> Content-Length: 35
>
* upload completely sent off: 35 out of 35 bytes
< HTTP/1.1 200 OK
< Date: Fri, 8 May 2020 18:36:53 GMT
< content-type: application/json
< content-length: 16
< connection: keep-alive
<
* Connection #0 to host localhost left intact
{"isValid":true}* Closing connection 0
```

#### Senha inválida

Request:
```$xslt
curl -v --request POST 'http://localhost:8080/' \
--header 'Content-Type: application/json' \
--data-raw '{
        "password": "AbTp91foo"
}'
``` 

Response:
```$xslt
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> POST / HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> Content-Type: application/json
> Content-Length: 35
>
* upload completely sent off: 35 out of 35 bytes
< HTTP/1.1 400 Bad Request
< Date: Fri, 8 May 2020 18:38:49 GMT
< content-type: application/json
< content-length: 85
< connection: close
<
* Closing connection 0
{"isValid":false,"validationErrors":["Password must have at least one special char"]}
```
