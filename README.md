Recomendações de boas práticas:

1) Sempre use Log4J e faça logs claros do que está sendo processado;
2) Não utilizar classe dentro de classe;
3) Criar um diretorio especifico para os DTOs (AuthRequest, AuthResponse)
4) Cuidado com uso de classes staticas, uma classe só deve ser estatica se o objetivo for compartilhar os 
mesmos valores em toda a aplicação;

Evolução do projeto:

1) Habilite o spring actuator no projeto;
2) O path /health/ do spring actuator deve ficar sem autenticação;