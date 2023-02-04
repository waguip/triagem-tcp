package src.cliente;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DoutorTela extends javax.swing.JFrame {
    
    JSONObject jsonObj;
    JSONParser parser = new JSONParser();
    Cliente cliente;
    String cpfMedico;
    String cpfPaciente;
    
    public DoutorTela(Cliente cliente, String cpf) {
        this.cliente = cliente;
        this.cpfMedico = cpf;
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbMostrarProximo = new javax.swing.JButton();
        lblData = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        jbAtender = new javax.swing.JButton();
        lblPrioridade = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDescricao = new javax.swing.JTextArea();
        jbLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Atendimento");

        jbMostrarProximo.setText("Mostrar Proximo");
        jbMostrarProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMostrarProximoActionPerformed(evt);
            }
        });

        lblData.setText("Data:");

        lblDescricao.setText("Descrição:");

        lblName.setText("Nome:");

        lblSexo.setText("Sexo:");

        jbAtender.setText("Atender");
        jbAtender.setEnabled(false);
        jbAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtenderActionPerformed(evt);
            }
        });

        lblPrioridade.setText("Prioridade:");

        lblCPF.setText("CPF:");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jtaDescricao.setColumns(20);
        jtaDescricao.setLineWrap(true);
        jtaDescricao.setRows(5);
        jScrollPane1.setViewportView(jtaDescricao);

        jbLogout.setText("Logout");
        jbLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrioridade)
                            .addComponent(lblDescricao)
                            .addComponent(lblCPF)
                            .addComponent(lblSexo)
                            .addComponent(lblData)
                            .addComponent(lblName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbMostrarProximo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbAtender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbMostrarProximo)
                        .addGap(18, 18, 18)
                        .addComponent(jbAtender)
                        .addGap(18, 18, 18)
                        .addComponent(jbLogout)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPrioridade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblName)
                        .addGap(12, 12, 12)
                        .addComponent(lblSexo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescricao)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbMostrarProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMostrarProximoActionPerformed
                 
        jsonObj = new JSONObject(); 
        jsonObj.put("code", 18);        
        
        try {
            
            String msgServer = cliente.enviaMensagem(jsonObj.toJSONString());
            cliente.clienteLog.atualizaLog(msgServer);
            
            jsonObj = new JSONObject();
            jsonObj = (JSONObject) parser.parse(msgServer);
            
        } catch (IOException ex) {
            System.out.println("Mensagem nao enviada");            
        } catch (ParseException ex) {
            System.out.println("JSON não criado");
        }       
        
        if(Boolean.parseBoolean(String.valueOf(jsonObj.get("success")))) {
            
            jsonObj = (JSONObject)jsonObj.get("user");                                        

            lblPrioridade.setText("Prioridade: " + (jsonObj.get("priority")));
            lblName.setText("Nome: " + (jsonObj.get("name")));
            lblData.setText("Data: " + (jsonObj.get("birthday")));
            jtaDescricao.setText((String) (jsonObj.get("description")));
            lblSexo.setText("Sexo: " + (jsonObj.get("sex")));
            lblCPF.setText("CPF: " + (jsonObj.get("cpf")));

            this.cpfPaciente = String.valueOf(jsonObj.get("cpf"));

            jbAtender.setEnabled(true);    
            jbMostrarProximo.setEnabled(false);

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Fila Vazia",
                "Atenção", 
                JOptionPane.ERROR_MESSAGE
            );            
        }              
    }//GEN-LAST:event_jbMostrarProximoActionPerformed

    private void jbAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtenderActionPerformed
               
        jsonObj = new JSONObject(); 
        
        jsonObj.put("code", 5);
        jsonObj.put("toCpf", cpfPaciente);
        jsonObj.put("fromCpf", cpfMedico);
        
        try {
            String msgServer = cliente.enviaMensagem(jsonObj.toJSONString());
            cliente.clienteLog.atualizaLog(msgServer);
            
            jsonObj = new JSONObject();
            jsonObj = (JSONObject) parser.parse(msgServer);
        } catch (IOException ex) {
            System.out.println("Mensagem não enviada");
        } catch (ParseException ex) {
            System.out.println("Erro ao criar JSON");      
        }        
                
        if(Boolean.parseBoolean(jsonObj.get("success").toString())){
            
            new ChatTela(cliente, cpfMedico, true).setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao criar um chat",
                "Erro", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_jbAtenderActionPerformed

    private void jbLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLogoutActionPerformed
        jsonObj = new JSONObject(); 
        jsonObj.put("code", 14);
        jsonObj.put("cpf", cpfMedico);        
        
        try {            
            String mensagem = cliente.enviaMensagem(jsonObj.toJSONString());
            cliente.clienteLog.atualizaLog(mensagem);
            this.dispose();
            new LoginForm(cliente).setVisible(true);
        } catch (IOException ex) {
            System.out.println("Mensagem não enviada");
        }
        
    }//GEN-LAST:event_jbLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAtender;
    private javax.swing.JButton jbLogout;
    private javax.swing.JButton jbMostrarProximo;
    private javax.swing.JTextArea jtaDescricao;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrioridade;
    private javax.swing.JLabel lblSexo;
    // End of variables declaration//GEN-END:variables

    
}
