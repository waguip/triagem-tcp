package src.cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Cliente {
        
    Socket s; 
    
    BufferedWriter bWriter;
    BufferedReader bReader;
    
    ClienteLogTela clienteLog;
    
    public Cliente(String hostname, int porta) throws IOException {        
        this.s = new Socket(hostname,porta);
        
        this.bWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));            
        this.bReader = new BufferedReader(new InputStreamReader(s.getInputStream()));  
        
        clienteLog = new ClienteLogTela();
        clienteLog.setVisible(true);
    }
    
    //Envia msg ao server e retorna sua msg de resposta
    public String enviaMensagem(String texto) throws IOException{
        
        bWriter.write(texto);
        bWriter.newLine();
        bWriter.flush();
        
        clienteLog.atualizaLog("Enviou: " + texto);
        
        return recebeMensagem();
    }
    
    //Função para ficar esperando uma msg do server
    public String recebeMensagem() throws IOException{             
        String msgServidor = bReader.readLine();                                    
        return msgServidor;
    }    
     
    //Envia msg ao server sem esperar resposta
    public void enviaMensagemChat(String texto) throws IOException{        
        bWriter.write(texto);
        bWriter.newLine();
        bWriter.flush();
        
        clienteLog.atualizaLog("Enviou: " + texto);    
    }
       
    public void fecharConexao() throws IOException {
        s.close();
    }   
}