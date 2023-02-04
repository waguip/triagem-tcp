package src.server;

/**
 *
 * @author wagui
 */
public class Chat {
    
    private Paciente paciente;
    private Usuario medico;
       
    public Chat(Paciente paciente, Usuario medico) {
        this.paciente = paciente;
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Usuario getMedico() {
        return medico;
    }
    
    public Boolean consultaPaciente(String cpf) {
        return paciente.usuario.consulta(cpf);
    }
    
    public Boolean consultaMedico(String cpf) {
        return medico.consulta(cpf);
    }
}
