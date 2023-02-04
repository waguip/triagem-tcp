package src.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author wagui
 */
public class Server extends Thread{
    
    ServerSocket ss;
    Socket s;
    public ArrayList<Usuario> usuarioLista = new ArrayList<>();
    public ArrayList<Paciente> pacienteLista = new ArrayList<>();
    public ArrayList<Chat> chatLista = new ArrayList<>();
    
    ServerTela serverTela;
        
    public Server(int porta) throws IOException {
        ss = new ServerSocket(porta); 
        serverTela = new ServerTela();
        serverTela.setVisible(true);
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());        
        serverTela.atualizainfo(porta, ss.getInetAddress().toString(), formatter.format(date));
    }
    
    public void iniciar() throws IOException {        
                        
        Thread threadConexao = new Thread(() -> {
            while(true) {            
                try {
                    s = ss.accept();                                        
                    atualizaLog("Nova conexão de cliente#" + s.getPort());
                    ThreadCliente tc = new ThreadCliente(s, this);
                    Thread thread = new Thread(tc);
                    thread.start();
                } catch (IOException ex) {
                    System.out.println("Falha ao criar servidor");
                }
            }    
        });   
        
        threadConexao.start();
    }     
    
    public void atualizaLog(String texto) {
        serverTela.atualizaLog(texto);
    }
    
    public void atualizaUsuarios() {      
        
        String[] usuariosLog = new String[usuarioLista.size()];                
        
        for (int i = 0; i < usuarioLista.size(); i++) {
            String tipo = usuarioLista.get(i).getTipo();
            String nome = usuarioLista.get(i).getNome();                        

            usuariosLog[i] = nome + ", " + tipo;
        }       

        serverTela.atualizaUsuarios(usuariosLog);
    }
    
    public void atualizaPacientes() {
        
        String[] pacientesLog = new String[pacienteLista.size()];                
        
        for (int i = 0; i < pacienteLista.size(); i++) {
            int prioridade = pacienteLista.get(i).getPrioridade();
            String nome = pacienteLista.get(i).usuario.getNome();                        

            pacientesLog[i] = nome + ", prioridade " + prioridade;
        }       

        serverTela.atualizaPacientes(pacientesLog);
    }
}
