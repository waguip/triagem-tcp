package src.server;

/**
 *
 * @author wagui
 */
public class Paciente {
    Usuario usuario;
    String descricao;
    int prioridade;

    public Paciente(Usuario usuario, String descricao, int prioridade) {
        this.usuario = usuario;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public int getPrioridade() {
        return prioridade;
    }       

    @Override
    public String toString() {
        return "Paciente{" + "usuario =" + usuario + ", descricao=" + descricao + ", prioridade=" + prioridade + '}';
    }
}
