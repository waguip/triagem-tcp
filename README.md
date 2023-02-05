# Triagem Médica TCP
Sistema de triagem médica com cliente e servidor utilizando sockets em java com o protocolo TCP

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
