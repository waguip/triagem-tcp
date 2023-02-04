package src.server;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ThreadCliente extends Thread {

    private Server serverPai;
    
    private Socket s;
    private BufferedWriter bW;
    private BufferedReader bR;
    
    JSONObject jsonObj;
    JSONParser parser = new JSONParser();
    
    //-----------PARAMETROS-SQL--------------------    
    private static final String username = "";
    private static final String password = "";
    private static final String dataConn = "jdbc:mysql://localhost:3306/distribuidos_servidor_medico";    
    //---------------------------------------------
        
    Connection sqlConn = null;
    Statement stmt = null;
    PreparedStatement pst = null;
    
    public ThreadCliente(Socket s, Server server) throws IOException{
        super(server);
        this.serverPai = server;
        this.s = s;
        this.bW = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));            
        this.bR = new BufferedReader(new InputStreamReader(s.getInputStream()));     
    }
   
    @Override
    public void run() {    
        try {            
            while(true) { 
                
                if(s.isClosed())
                    break;
                
                //Esperando msg do cliente
                String msgUsuario = bR.readLine();                
                
                //Coloca a msg no log
                serverPai.atualizaLog("Recebeu do cliente#" + s.getPort() + ": " + msgUsuario);
                
                //Tenta criar o objeto Json com a msg
                try {
                    jsonObj = (JSONObject) parser.parse(msgUsuario);
                } catch (ParseException ex) {                     
                    System.out.println("Erro ao criar Json");
                    continue;                    
                }
                
                //Pega o cod da função desejada e passa o Json pra função
                String funcao = String.valueOf(jsonObj.get("code"));                
                switch(funcao) {
                    case "1" ->  adicionarUsuarioNoBanco(jsonObj);
                    case "3" -> realizarLogin(jsonObj);
                    case "9" -> respondeTriagem(jsonObj);
                    case "10" -> retornaPosicao(jsonObj);
                    case "14" -> logout(jsonObj);
                    case "18" -> mostrarProximo(jsonObj);
                    case "5" -> medicoSolicitaChat(jsonObj);
                    case "12" -> pacientePronto(jsonObj);
                    case "6" -> trocaDeMensagem(jsonObj);
                    case "8" -> encerrarChat(jsonObj);
                }                                
            }
            
        } catch (IOException ex) {                
            //Fecha conexao ao chegar na excessao
            fecharConexao();                
        }             
    }
    
    private void abrirSql() throws SQLException {
        sqlConn = DriverManager.getConnection(dataConn, username, password);            
        stmt = sqlConn.createStatement();
    }
    
    private void fecharSql() throws SQLException {
        stmt.close();
        sqlConn.close();
    }
    
    private void fecharConexao() {
        try {                                    
            s.close();       
            bR.close();
            bW.close();            
            serverPai.atualizaLog("Cliente#" + s.getPort() + " desconectado.");  
        } catch (IOException ex) {
            System.out.println("Erro ao fechar conexao");
        }
    }
    
    //Resposta ao cliente que enviou a mensagem
    private void respondeAoCliente(String msg) throws IOException {
        bW.write(msg);
        bW.newLine();
        bW.flush();

        //Mostra oq enviou no log
        serverPai.atualizaLog("Enviou para o cliente#" + s.getPort() + ": " + msg);
    }
    
    //Resposta ao cliente com socket passado
    private void respondeAoCliente(String msg, Socket socket) throws IOException {                    
        BufferedWriter bWPaciente = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bWPaciente.write(msg);
        bWPaciente.newLine();
        bWPaciente.flush();

        serverPai.atualizaLog("Enviou para o cliente#" + socket.getPort() + ": " + msg);
    }
    
    private void adicionarUsuarioNoBanco(JSONObject jsonObj) {                    
        
        String nome = String.valueOf(jsonObj.get("name"));
        String cpf = String.valueOf(jsonObj.get("cpf"));
        String senha = String.valueOf(jsonObj.get("password"));
        String data = String.valueOf(jsonObj.get("birthday"));
        String sexo = String.valueOf(jsonObj.get("sex"));        
        boolean doutor = Boolean.parseBoolean(jsonObj.get("doctor").toString());
        
        try {                     
            //Conexão e preparação
            abrirSql();
            
            //Comando SQL
            String sql = "INSERT INTO usuario (nome, cpf, senha, data, sexo, doutor)" +
                         "VALUES (?, ?, ?, ?, ?, ?)";            
            
            pst = sqlConn.prepareStatement(sql);            
            pst.setString(1, nome);
            pst.setString(2, cpf);
            pst.setString(3, senha);
            pst.setString(4, data);
            pst.setString(5, sexo);
            pst.setBoolean(6, doutor);
                                    
            //Cria o JSON de resposta
            JSONObject jsonResposta = new JSONObject();        
            jsonResposta.put("code", 101);
            
            //Confere se foi cadastrado ou não
            int linhasAdicionadas = pst.executeUpdate();            
            if(linhasAdicionadas > 0) {                
                System.out.println(nome + " cadastrado");                                
                jsonResposta.put("success", true);                       
            } else {
                System.out.println(nome + " não cadastrado");                                
                jsonResposta.put("success", false);                                  
            }
            
            //Envia a resposta
            respondeAoCliente(jsonResposta.toJSONString());            
            
            //Fechando conexao sql
            fecharSql();            
                                   
        } catch(IOException | SQLException e) { 
            e.printStackTrace();
        }
    }   
    
    private void realizarLogin(JSONObject jsonObj) {                    
                
        String cpf = String.valueOf(jsonObj.get("cpf"));
        String senha = String.valueOf(jsonObj.get("password"));        
        
        try {                     
            //Conexão e preparação
            abrirSql();
            
            //Comando SQL
            String sql = "SELECT * FROM usuario WHERE cpf=? AND senha=?";      
            
            pst = sqlConn.prepareStatement(sql);                        
            pst.setString(1, cpf);
            pst.setString(2, senha);
                                    
            //Cria o JSON de resposta
            JSONObject jsonResposta = new JSONObject();        
            jsonResposta.put("code", 103);
            
            //Confere se foi logado ou não
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                
                jsonResposta.put("status", true);
                
                String nome = resultSet.getString("nome");
                cpf = resultSet.getString("cpf");
                String niver = resultSet.getString("data");
                String sex = resultSet.getString("sexo");
                Boolean doutor = "1".equals(resultSet.getString("doutor"));
                                
                JSONObject user = new JSONObject();
                user.put("name", nome);
                user.put("cpf", cpf);
                user.put("birthday", niver);
                user.put("sex", sex);                                           
                user.put("doctor", doutor) ;                
                jsonResposta.put("user", user);       
                
                Usuario usuario = new Usuario(nome, cpf, niver, sex, doutor, s);
                serverPai.usuarioLista.add(usuario);
                serverPai.atualizaUsuarios();
                System.out.println(serverPai.usuarioLista.toString());                
                                
            } else {
                jsonResposta.put("status", false);                
            }
            //Envia a resposta  
            respondeAoCliente(jsonResposta.toJSONString());
                        
            //Fechando conexao
            fecharSql();
                                   
        } catch(IOException | SQLException e) { 
            e.printStackTrace();
        }
    }    
    
    private void logout(JSONObject jsonObj){
                
        String cpf = String.valueOf(jsonObj.get("cpf"));        
        
        JSONObject jsonResposta = new JSONObject();        
        jsonResposta.put("code", 114);  
        jsonResposta.put("status", false);
        
        for(Usuario usuario : serverPai.usuarioLista) {
            //Qnd encontra o cpf correspondente            
            if(usuario.consulta(cpf)) {                             
                serverPai.usuarioLista.remove(usuario);               
                jsonObj.put("status", true); 
                serverPai.atualizaUsuarios();
                
                //Confere a lista de pacientes
                for(Paciente paciente : serverPai.pacienteLista){          
                    if(paciente.usuario == usuario){
                        serverPai.pacienteLista.remove(paciente);
                        serverPai.atualizaPacientes();
                        break;
                    }
                }
                
                break;
            }            
        }                                                       
        
        try {
            //Responde ao cliente
            respondeAoCliente(jsonResposta.toJSONString());           
        } catch (IOException ex) {
            System.out.println("Erro ao enviar msg");
        }        
    }
    
    private void respondeTriagem(JSONObject jsonObj){
        
        String code = String.valueOf(jsonObj.get("code"));  
        String cpf = String.valueOf(jsonObj.get("cpf"));
        int prior = Integer.parseInt(String.valueOf(jsonObj.get("priority")));
        String descr= String.valueOf(jsonObj.get("description"));
     
        System.out.println("Codigo: "+code+"cpf: "+cpf+"prioridade: "+prior+"descricao: "+descr);
        
        //Loop pelos usuarios logados
        for(Usuario usuario : serverPai.usuarioLista) {
            
            //Qnd encontra o cpf correspondente
            if(usuario.consulta(cpf)) {
                //Salva o usuario e seus dados na lista de pacientes
                Paciente paciente = new Paciente(usuario, descr, prior);
                serverPai.pacienteLista.add(paciente);
                break;
            }
        }
        
        //Reordena a lista de pacientes
        serverPai.pacienteLista.sort((Paciente p1, Paciente p2) -> {
            return p1.prioridade - p2.prioridade;
        });                
        
        System.out.println(serverPai.pacienteLista);
        serverPai.atualizaPacientes();
        
        //Cria o JSon de resposta
        JSONObject jsonResposta = new JSONObject(); 
        jsonResposta.put("code", 109);
        jsonResposta.put("success", true);                
        
        try {
            //Responde ao cliente
            respondeAoCliente(jsonResposta.toJSONString());
            
            bW.write(jsonResposta.toJSONString());
            bW.newLine();
            bW.flush();           
            serverPai.atualizaLog("Enviou para o cliente#" + s.getPort() + ": " + jsonResposta);                                    
            
        } catch (IOException ex) {
            System.out.println("Falha ao enviar mensagem");
        }            
    }
    
    private void retornaPosicao(JSONObject jsonObj) {

        String cpf = String.valueOf(jsonObj.get("cpf"));

        //posicao do paciente com o cpf dado
        int contador=-1;

        for(int x=0; x<serverPai.pacienteLista.size();x++){

          if(serverPai.pacienteLista.get(x).usuario.consulta(cpf)) {
                contador=x+1;
            }
        }

        System.out.println("Posicao server: "+contador);
        
        //json de resposta
        JSONObject jsonResposta = new JSONObject(); 
        jsonResposta.put("code", 110);
        jsonResposta.put("position",contador);
        jsonResposta.put("success", true);
        System.out.println("JSON criada pra enviar: " + jsonResposta.toJSONString());

         try {
            //Responde ao cliente
            respondeAoCliente(jsonResposta.toJSONString());           
        } catch (IOException ex) {
            System.out.println("Falha ao enviar msg");
        }       
    }

    private void mostrarProximo(JSONObject jsonObj) {
        
        JSONObject jsonResposta = new JSONObject(); 
        jsonResposta.put("code", 118);
        
        if(serverPai.pacienteLista.isEmpty()){
            jsonResposta.put("success", false);
        } else {        
            JSONObject jsonUser = new JSONObject(); 
            jsonUser.put("name", serverPai.pacienteLista.get(0).usuario.getNome());
            jsonUser.put("cpf", serverPai.pacienteLista.get(0).usuario.getCpf());
            jsonUser.put("birthday", serverPai.pacienteLista.get(0).usuario.getData());
            jsonUser.put("sex", serverPai.pacienteLista.get(0).usuario.getSexo());
            jsonUser.put("priority", serverPai.pacienteLista.get(0).prioridade);
            jsonUser.put("description", serverPai.pacienteLista.get(0).descricao);
            jsonResposta.put("user", jsonUser);
            jsonResposta.put("success", true);
        }
        
        try {
            respondeAoCliente(jsonResposta.toJSONString());
        } catch (IOException ex) {
            System.out.println("Erro ao enviar dados do paciente");
        }
    }
    
    private void medicoSolicitaChat(JSONObject jsonObj) {
                                     
        String toCpf = String.valueOf(jsonObj.get("toCpf"));  //Cpf Paciente
        String fromCpf = String.valueOf(jsonObj.get("fromCpf")); //Cpf Medico
        
        Usuario medico = null; 
        Paciente paciente = null;
               
        //Prepara resposta ao medico
        JSONObject jsonResposta = new JSONObject(); 
        jsonResposta.put("code", 105);
        jsonResposta.put("success", false);
        
        //Passa por usuarios logados procurando medico
        for(Usuario usuario : serverPai.usuarioLista) {            
            //Confere se é o cpf do medico q enviou a solicitação
            if(usuario.consulta(fromCpf)) {
                medico = usuario;                
                break;
            }                        
        }
        
        if(medico == null)
            return;
                        
        //Prepara mensagem ao paciente
        JSONObject jsonPaciente = new JSONObject(); 
        jsonPaciente.put("code", 155);
        
        //Passa por pacientes logados procurando paciente
        for(Paciente pacienteNaLista : serverPai.pacienteLista) {            
            //Confere se é o cpf do paciente solicitado
            if(pacienteNaLista.usuario.consulta(toCpf)) {
                paciente = pacienteNaLista;                
                jsonResposta.put("success", true);
                break;
            }                        
        }                             
                
        //Envia mensagem ao medico
        try {
            respondeAoCliente(jsonResposta.toJSONString());           
        } catch (IOException ex) {
            System.out.println("Não foi possivel enviar resposta");
        }                           
        
        if(paciente == null)
            return;
                        
        //Cria a classe chat com os dois
        Chat chat = new Chat(paciente, medico);
        serverPai.chatLista.add(chat);       
        serverPai.pacienteLista.remove(paciente);
        serverPai.atualizaPacientes();

        //Envia mensagem ao paciente
        try {
            Socket socketPaciente = paciente.usuario.getSocket();
            respondeAoCliente(jsonPaciente.toJSONString(), socketPaciente);
        } catch (IOException ex) {
            System.out.println("Erro ao enviar mensagem ao paciente");
        }                                                                                   
    }

    private void pacientePronto(JSONObject jsonObj) {
        
        Usuario medico = null;
        Paciente paciente = null;        
        String cpf = String.valueOf(jsonObj.get("cpf"));
        
        //Prepara resposta
        JSONObject jsonResposta = new JSONObject();
        jsonResposta.put("code", 112); 
        jsonResposta.put("success", false); 

        //Pega o paciente da lista de chat que possui o cpf eviado        
        for(Chat chat : serverPai.chatLista) {           
            if(chat.consultaPaciente(cpf)) {
                paciente = chat.getPaciente();
                medico = chat.getMedico();
                jsonResposta.put("success", true); 
                break;
            }
        }
        
        if (paciente != null) {
            serverPai.pacienteLista.remove(paciente);
            serverPai.atualizaPacientes();
        }
        
        //Envia a resposta pro paciente       
        try {            
            respondeAoCliente(jsonResposta.toJSONString());            
        } catch (IOException ex) {
            System.out.println("Não foi possivel enviar resposta ao paciente");
        }
        
        //Envia a mensagem pro medico (avisa q paciente pronto)        
        try {
            JSONObject jsonMedico = new JSONObject();
            jsonMedico.put("code", 212);
            jsonMedico.put("success", true); 

            Socket socketMedico = medico.getSocket();
            respondeAoCliente(jsonMedico.toJSONString(), socketMedico);
        } catch (IOException ex) {
            System.out.println("Erro ao enviar mensagem ao medico");
        }                                     
    }

    private void trocaDeMensagem(JSONObject jsonObj) {
     
        String cpf = String.valueOf(jsonObj.get("cpf"));
        String message = String.valueOf(jsonObj.get("message"));
                
        String nomeRemetente = null;
        Socket socketDestinatario = null;
        
        //Busca cpf na lista de chat
        for(Chat chat : serverPai.chatLista) {         
            
            //Se o cpf é de um medico
            if(chat.consultaMedico(cpf)) {
                nomeRemetente = chat.getMedico().getNome();
                socketDestinatario = chat.getPaciente().usuario.getSocket();
                break;
            }
            
            //Se o cpf é de um paciente
            if(chat.consultaPaciente(cpf)){
                nomeRemetente = chat.getPaciente().usuario.getNome();
                socketDestinatario = chat.getMedico().getSocket();
                break;
            }
        }
                        
        //Prepara resposta
        JSONObject jsonResposta = new JSONObject();
        jsonResposta.put("code", 106);
        
        if(nomeRemetente == null || socketDestinatario == null) {            
            jsonResposta.put("success", false);
            
        } else {
            
            jsonResposta.put("code", 106);
            jsonResposta.put("position", 1);
            jsonResposta.put("name", nomeRemetente);
            jsonResposta.put("message", message);
            
            //Envia mensagem a outra ponta
            try {                        
                respondeAoCliente(jsonResposta.toJSONString(), socketDestinatario);                
            } catch (IOException ex) {
                System.out.println("Não foi possivel enviar resposta");
            }
        }

        //Envia resposta a quem mandou a msg
        try {               
            respondeAoCliente(jsonResposta.toJSONString());
        } catch (IOException ex) {
            System.out.println("Não foi possivel enviar resposta");
        }                        
        
    }

    private void encerrarChat(JSONObject jsonObj) {
        
        String cpf = String.valueOf(jsonObj.get("cpf"));

        Socket socketDestinatario = null;
        
        //Busca cpf na lista de chat
        for(Chat chat : serverPai.chatLista) {         
            
            //Se o cpf é de um medico
            if(chat.consultaMedico(cpf)) {                
                socketDestinatario = chat.getPaciente().usuario.getSocket();
                serverPai.chatLista.remove(chat);
                break;
            }
            
            //Se o cpf é de um paciente
            if(chat.consultaPaciente(cpf)){                
                socketDestinatario = chat.getMedico().getSocket();
                serverPai.chatLista.remove(chat);
                break;
            }
        }
        
        //Prepara resposta
        JSONObject jsonResposta = new JSONObject();
        jsonResposta.put("code", 108);
        
        if(socketDestinatario == null) {            
            jsonResposta.put("success", false);
            
        } else {         
            
            jsonResposta.put("cpf", cpf);
            jsonResposta.put("position", 1);       
            
            //Envia resposta pra outra ponta
            try {
                respondeAoCliente(jsonResposta.toJSONString(), socketDestinatario);
            } catch (IOException ex) {
                System.out.println("Não foi possivel enviar resposta");
            }            
        }                
        
        //Envia resposta
        try {              
            respondeAoCliente(jsonResposta.toJSONString());            
        } catch (IOException ex) {
            System.out.println("Não foi possivel enviar resposta");
        }                                               
    }        
}    

