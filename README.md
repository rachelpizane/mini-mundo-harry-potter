
# 🧙 Mini Mundo Harry Potter

Este é um pequeno sistema desenvolvido em Java que simula um mini mundo inspirado no universo de Harry Potter. A aplicação permite cadastrar bruxos, exibir suas informações, lançar feitiços e listar todos os bruxos cadastrados.

## 📣 Objetivos e decisões
O principal objetivo do projeto foi exercitar os conceitos fundamentais de Programação Orientada a Objetos (POO) — como abstração, encapsulamento, herança e polimorfismo — além de aplicar uma organização em camadas utilizando os padrões MVC e DTO para separação de responsabilidades.

O projeto foi desenvolvido em Java puro com o intuito de aprofundar os princípios SOLID, especialmente o princípio da Inversão de Dependência. Toda a criação e integração dos objetos foi realizada manualmente, simulando o que frameworks como o Spring fazem automaticamente por meio de injeção de dependências.

Para complementar a experiência, optei por implementar uma interface via Terminal (camada de apresentação) e simular um repositório em memória utilizando um Map para armazenamento das entidades.

## 🛠 Tecnologias utilizadas

- Java
- Gradle
- JUnit
- Mockito


## ✨ Principais funcionalidade

- **Cadastro de bruxos**: Permite registrar novos bruxos das casas Sonserina e Grifinória.
- **Listagem de bruxos**: Exibe todos os bruxos cadastrados na aplicação.
- **Visualização de detalhes**: Consulta informações completas de um bruxo específico.
- **Lançamento de feitiços**: Executa feitiços a partir de um bruxo.
- **Armazenamento em memória**: Persistência simulada utilizando um Map como repositório em memória.
- **Interação via Terminal**: Interface baseada em linha de comando para entrada e exibição de dados.
- **Separação em camadas**: Organização estruturada em camadas.


## 🚀 Como rodar o projeto

Antes de começar, você vai precisar ter instalado em sua máquina:
- **Git**: Versão 2.49 ou superior
- **Java**: Versão 21 (LTS recomendada)

### 1. Clone o repositório
   ```bash
   git clone https://github.com/rachelpizane/mini-mundo-harry-potter.git
   cd mini-mundo-harry-potter
   ```

### 2. Execute o projeto 
   ```bash
   ./gradlew run
   ```

## 🙋🏻‍♀️ Autora

Desenvolvido por [Rachel Pizane](https://br.linkedin.com/in/rachel-pizane).