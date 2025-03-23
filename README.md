# Login APP

## Sobre o Projeto

Este projeto é uma aplicação web desenvolvida em **Java** que permite o cadastro de usuários  e login de usuários. O projeto é dividido em duas partes principais:

1. **Back-end:** Desenvolvido em Java, utilizando Servlets para gerenciar as requisições HTTP.
2. **Front-end:** Desenvolvido em React, utilizando componentes funcionais.

## Funcionalidades
- **Login de usuário** (sem autenticação)
- **Cadastro de usuário** (sem persistência)
- **Busca de CEP**: No cadastro de usuário, realiza requisições HTTP do tipo `GET` para a rota `/endereco/cep/[CEP]`
- **Integração com API ViaCEP**: Busca informações de um CEP na API do ViaCEP e retorna as informações no formato JSON
- **Tratamento de erros**: Lida com erros de requisição e CEPs inválidos

## Tecnologias Utilizadas
### Back-end

- **Java**: Linguagem principal para lógica e gerenciamento do servidor.
- **Spring Boot**: Framework para simplificar a criação de aplicações Java.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **RestTemplate**: Para realizar a comunicação com APIs externas.

### Front-end

- **React**: Biblioteca JavaScript para construção de interfaces de usuário.
- **JavaScript**: Linguagem principal para lógica do front-end.
- **HTML/CSS**: Para estrutura e estilo das páginas web.
- **Axios**: Biblioteca para fazer requisições HTTP.


## Como Executar

1. Certifique-se de ter o **JDK 11** (ou superior) instalado.
2. Clone este repositório:
   ```bash
   git clone https://github.com/NadsonJR/DesafioNTT.git
3. Importe o projeto em sua IDE favorita (Eclipse, IntelliJ, etc.).
4. Compile o projeto usando Maven:
  ```clean install```
5. Inicie a aplicação:
 ```mvn spring-boot:run```
8. Acesse a aplicação via navegador ou ferramenta como Postman:
  ```http://localhost:5550/pessoas```

## Endpoints

### POST /pessoas/create
Cria um novo usuário.

### POST /pessoas/login
Realiza o login de um usuário.

### GET /pessoas/check-email
Verifica se um email já está cadastrado.

### GET /pessoas/all
Retorna todos os usuários cadastrados.

### GET /pessoas/{id}
Retorna um usuário pelo ID.

### PUT /pessoas/{id}
Atualiza as informações de um usuário pelo ID.

### GET /endereco/cep/{cep}
Busca informações de um CEP na API do ViaCE

### POST /endereco/create}
Cria um novo endereço.


## Tecnologias Utilizadas para Testes Automatizados

Este projeto utiliza várias tecnologias para implementar testes automatizados para uma aplicação web. Abaixo está uma descrição das tecnologias utilizadas:

### Tecnologias

#### Java
- **Java**: A principal linguagem de programação usada para escrever os scripts de teste.

#### RestAssured
- **RestAssured**: Uma biblioteca Java usada para testar e validar APIs REST. Ela simplifica o processo de fazer requisições HTTP e asserções nas respostas.

#### Selenium
- **Selenium WebDriver**: Uma ferramenta de automação web usada para interagir com navegadores e realizar ações como clicar, digitar e navegar.

#### JUnit
- **JUnit**: Um framework de testes usado para escrever e executar testes em Java.

#### Cucumber
- **Cucumber**: Uma ferramenta para executar testes automatizados escritos em linguagem natural. Utiliza a sintaxe Gherkin para definir cenários de teste.

#### Apache POI
- **Apache POI**: Uma biblioteca para ler e escrever documentos do Microsoft Office, usada aqui para gerar um documento Word com capturas de tela tiradas durante a execução dos testes.

#### Log4j
- **Log4j**: Um framework de logging usado para registrar informações durante a execução dos testes.

#### Maven
- **Maven**: Uma ferramenta de automação de build usada para gerenciar dependências do projeto e construir o projeto.

#### WebDriver Manager
- **WebDriver Manager**: Uma biblioteca que ajuda a gerenciar os drivers de navegador necessários para o Selenium WebDriver.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

**Autor:** Antonio Nadson\
**Contato:** [nadsonjunior@live.com](mailto\:seu-email@example.com)

