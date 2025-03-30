# API para controle financeiro

classDiagram mermaid
```
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
