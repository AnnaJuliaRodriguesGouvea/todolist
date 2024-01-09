# TODO LIST

### Funcionalidades
- **Gerenciamento de Tarefas**: 
    - *Listagem de tarefas:* o sistema mostra em uma tabela todas as tarefas cadastradas no banco de dados.
    - *Cadastro de tarefas com três níveis de prioridade:*
        - LOW: Baixa prioridade;
        - MEDIUM: Média prioridade;
        - HIGH: Alta prioridade.
    - *Atualização dos dados:* é possível realizar a atualização do nome e prioridade.
    - *Atualização do status:* é possível mudar o status da tarefa no seguinte fluxo: **NEW -> WORKING -> COMPLETED**
    - *Exclusão:* é possível realizar a exclusão de uma tarefa.

### Tecnologias utilizadas
O sistema utiliza as seguintes tecnologias:
- Java 17
- Spring Boot (Spring Data & Spring Web)
- Lombok
- Mapstruct
- Maven
- MySQL
- Docker
- Angular 17
- PrimeNG

### Compilar e executar o sistema
- **Instalações necessárias:**
    - *Java:* versão 17 ou superior
    - *Maven:* versão 3.9.5
    - *Node.JS:* versão 21.4.0 ou superior
    - *Docker:* versão 24.0.2 ou superior
    - *Docker-Compose:* versão 2.19.0 ou superior

- **Clonar o repositório:**
    - Para clonar o repositório, abra o terminal na pasta em que deseja armazená-lo em sua máquina e digite:
    ```sh
        git clone https://github.com/AnnaJuliaRodriguesGouvea/todolist.git
    ```

- **Rodar Backend (Java application e MySQL):**
    - A aplicação Java e o banco de dados MySQL são inicializados por meio do Docker. Portando para rodar o backend siga os passos abaixo:
        - Abra a pasta **/backend** no terminal e digite o comando:
        ```sh
            docker-compose up --build -d
        ```

- **Rodar Frontend:**
    - Instalações iniciais: o projeto utiliza diversas bibliotecas instaladas usando **npm** (estas podem ser verificadas no arquivo **frontend/package.json**). Dessa forma, é necessário realizar a instalação de todas elas. Para isso, siga os passos abaixo:
        - Abra a pasta **/frontend** no terminal e digite o comando:
        ```sh
            npm install
        ```
    - Rodar frontend: 
        - Abra a pasta **/frontend** no terminal e digite o comando:
        ```sh
            ng serve
        ```

*Agora basta acessar a url "http://localhost:4200/" e utilizar o sistema!*
