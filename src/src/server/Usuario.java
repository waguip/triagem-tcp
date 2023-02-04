package src.server;

import java.net.Socket;

/**
 *
 * @author wagui
 */
public class Usuario {

    private String nome;
    private String cpf;
    private String data;
    private String sexo;
    private Boolean doutor;
    private Socket socket;

    public Usuario(String nome, String cpf, String data, String sexo, Boolean doutor, Socket socket) {
        this.nome = nome;
        this.cpf = cpf;        
        this.data = data;
        this.sexo = sexo;
        this.doutor = doutor;
        this.socket = socket;
    }    

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String getTipo() {
        return this.doutor ? "doutor" : "paciente";
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", cpf=" + cpf + ", data=" + data + ", sexo=" + sexo + ", doutor=" + doutor + '}';
    }     
    
    public Boolean consulta(String cpf) {
        return (this.cpf == null ? cpf == null : this.cpf.equals(cpf));
    }
}
