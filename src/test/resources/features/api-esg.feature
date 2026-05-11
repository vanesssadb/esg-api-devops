# language: pt

Funcionalidade: Validação das APIs ESG
  Como usuária da API Cidades ESG
  Quero validar os principais comportamentos da aplicação
  Para garantir qualidade, estabilidade e aderência ao contexto ESG

  Cenário: Verificar se a API está ativa
    Dado que a API ESG está em execução
    Quando eu faço uma requisição GET para "/health"
    Então o status da resposta deve ser 200
    E o corpo deve conter o status "UP"
    E a resposta deve seguir o contrato "health-schema.json"

  Cenário: Consultar lista de empresas
    Dado que a API ESG está em execução
    Quando eu faço uma requisição GET para "/companies"
    Então o status da resposta deve ser 200
    E o corpo da resposta deve ser uma lista JSON
    E a resposta deve seguir o contrato "list-schema.json"

  Cenário: Tentar cadastrar empresa sem autenticação
    Dado que a API ESG está em execução
    Quando eu faço uma requisição POST sem token para "/companies"
    Então o status da resposta deve ser 403