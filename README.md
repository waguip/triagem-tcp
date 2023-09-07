# Triagem Médica TCP
Sistema de triagem médica com cliente e servidor utilizando sockets em java com o protocolo TCP

### Principais Funcionalidades:

- Registro de Usuários: Médicos e pacientes podem se registrar no sistema. Os detalhes dos usuários são salvos em um banco de dados SQL.
- Triagem de Pacientes: Os pacientes preenchem um formulário com seus sintomas e são colocados em uma fila de atendimento, priorizados com base na gravidade dos sintomas.
- Atendimento Médico: Os médicos têm a responsabilidade de chamar o próximo paciente da fila para iniciar um chat. Eles podem revisar as informações de triagem antes de iniciar o atendimento.
- Chat entre Médico e Paciente: O chat ocorre somente quando ambos (médico e paciente) aceitam entrar na conversa. O servidor gerencia a comunicação entre eles.
- Comunicação em JSON: Todas as mensagens trocadas entre médicos e pacientes são formatadas em JSON (JavaScript Object Notation), proporcionando uma comunicação estruturada e eficiente.

## Cliente
<p float="left" width="100%">
  <img src="https://user-images.githubusercontent.com/51832038/216794291-3a96e6e2-e28b-4c78-a0ef-ea5d8f64cf84.png" width="50%">    
  <img src="https://user-images.githubusercontent.com/51832038/216794191-d3b0e63f-e93b-4ad9-9df4-72af85c26343.png" width="45%">
</p>
Os clientes podem ser médicos ou pacientes, seus dados de registro são salvos em SQL <br><br>

- O paciente preenche um fórmulario com seus sintomas e é colocado em uma fila por ordem de prioridade (triagem) <br>
- O médico é responsável por chamar o proximo paciente da fila para o chat <br>
- O chat acontece quando os dois lados aceitam entrar na conversa

## Servidor
![LogServidor](https://user-images.githubusercontent.com/51832038/216794653-235afd16-ff73-4b9c-8b63-96a3ddd31f5f.png) <br><br>
O servidor é responsável por gerenciar a comunicação entre todos os clientes, as mensagens são passadas utilizando JSON
