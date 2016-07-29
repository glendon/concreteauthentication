# concreteauthentication

Desafio Java da Concrete


Instruções para execução:

1 - Faça o clone deste repositório

2 - Na raiz do projeto:

      _> gradle build
      -> java -jar build/libs/concreteauthentication-0.0.1-SNAPSHOT.jar
      
      
Endpoints:

POST /user 

    header: 
    Content-Type: application/json

    body:
      {
            "name": "João da Silva",
            "email": "joao@silva.org",
            "password": "anypass",
            "phones": [
                {
                    "number": "987654321",
                    "ddd": "21"
                }
            ]
      }


POST /login

    header: 
    Content-Type: application/json

    body:
      {
            "email": "joao@silva.org",
            "password": "anypass"
      }

GET /user/{id}

    header: 
    X-AUTH-TOKEN: {token retornado no login ou no cadastrado}
