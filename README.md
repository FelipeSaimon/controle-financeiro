# API para controle financeiro

Visando em atender uma necessidade pessoal em controlar minha movimentação financeira, essa aplicação vai atender o básico onde poderei inserir informações do que entra e do que sái.
A ideia inicial é uma aplicação simples, pode ter mais coisas futuramente, dependendo da necessidade. O intuito inicial é também colocar em prática o que aprendi sobre spring boot, e o que sei de reactjs.

## Diagrama de classes
``` mermaid
classDiagram
    class Usuario {
        +String nome
        +String login
        +String senha
        +Boolean logado
        +inserirMovimentacao(valor: float, tipo: String) void
    }

    class Movimentacao {
        +float valor
        +String tipo
        +Date dataCriacao
    }

    Usuario "1" -- "*" Movimentacao : possui
    Usuario ..> Movimentacao : cria se logado
```
