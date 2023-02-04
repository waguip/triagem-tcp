package src.cliente;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Thiago Renan de Lima
 */
public class ChatTela extends javax.swing.JFrame {

    Thread threadEsperaMsg;
    Boolean medico;
    Cliente cliente;
    String msgServer;
    String cpf;
    JSONObject jsonObj;
    JSONParser parser = new JSONParser();
    
    /**
     * Creates new form ChatForm
     */
    public ChatTela(Cliente cliente, String cpf, Boolean medico) {
        this.medico = medico;
        this.cliente = cliente;
        this.cpf = cpf;
        initComponents();
        aguardaMsg();
    }
    
    public void encerraChat(){
               
        if(medico) //novo atendimento
            new DoutorTela(cliente, cpf).setVisible(true);
        else{
            JOptionPane.showMessageDialog(null, "Atendimento concluido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            new TriagemForm(cliente, cpf).setVisible(true);
        }
        threadEsperaMsg.interrupt();
        this.dispose();
    }
    
    public void aguardaMsg(){
    
        threadEsperaMsg = new Thread(() -> {
            
            while(!threadEsperaMsg.isInterrupted()) {
                
                try {
                if(cliente.bReader.ready()){
                    
                    msgServer = cliente.bReader.readLine();
                    cliente.clienteLog.atualizaLog(msgServer);

                    try {
                        jsonObj = new JSONObject();
                        jsonObj = (JSONObject) parser.parse(msgServer);
                    } catch (ParseException ex) {
                        System.out.println("JSON não criado");
                    }
                    int code=Integer.parseInt(String.valueOf(jsonObj.get("code")));
                    
                    if(code==106){
                        String nome = String.valueOf(jsonObj.get("name"));
                        String msg  = String.valueOf(jsonObj.get("message"));

                        jtaChat.append(nome + ": " + msg + "\n");
                    } else if (code==108) {
                        encerraChat();
                        break;
                    } else if (code==212) {
                     
                        boolean success =Boolean.parseBoolean(jsonObj.get("success").toString());
                        if(success){
                            jtaChat.append("Paciente Conectado\n");
                        } else{
                           jtaChat.append("ERRO ao conectar o Paciente\n");
                        }
                        
                    }
                
                }
                } catch (IOException ex) {
                    System.out.println("Erro ao receber mensagens");
                }
            }
        });
    threadEsperaMsg.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaChat = new javax.swing.JTextArea();
        jbEnviar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaMensagemAEnviar = new javax.swing.JTextArea();
        jbSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jtaChat.setEditable(false);
        jtaChat.setColumns(20);
        jtaChat.setLineWrap(true);
        jtaChat.setRows(5);
        jScrollPane1.setViewportView(jtaChat);

        jbEnviar.setText("Enviar");
        jbEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnviarActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jtaMensagemAEnviar.setColumns(20);
        jtaMensagemAEnviar.setLineWrap(true);
        jtaMensagemAEnviar.setRows(5);
        jScrollPane3.setViewportView(jtaMensagemAEnviar);

        jbSair.setText("Sair");
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSair)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnviarActionPerformed
        
        jsonObj = new JSONObject(); 
        
        jsonObj.put("code", 6);
        jsonObj.put("cpf", cpf);
        jsonObj.put("message", jtaMensagemAEnviar.getText());
        jtaMensagemAEnviar.setText("");
        
        try {
            cliente.enviaMensagemChat(jsonObj.toJSONString());
        } catch (IOException ex) {
            System.out.println("Mensagem não enviada");
        }
    }//GEN-LAST:event_jbEnviarActionPerformed

    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        
        jsonObj = new JSONObject(); 
        
        jsonObj.put("code", 8);
        jsonObj.put("cpf", cpf);
                
        try{
            cliente.enviaMensagemChat(jsonObj.toJSONString());                     
        }catch (IOException ex) {
            System.out.println("Mensagem não enviada");
        }
       
    }//GEN-LAST:event_jbSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbEnviar;
    private javax.swing.JButton jbSair;
    private javax.swing.JTextArea jtaChat;
    private javax.swing.JTextArea jtaMensagemAEnviar;
    // End of variables declaration//GEN-END:variables
}
