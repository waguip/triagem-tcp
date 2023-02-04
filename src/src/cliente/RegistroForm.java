package src.cliente;

import uteis.DragListener;
import uteis.JTextFieldLimit;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RegistroForm extends JDialog {
           
    Cliente cliente;
    LoginForm login;    
    JSONParser parser = new JSONParser();
    
    //Contrutor do formulario de registro
    public RegistroForm(Cliente cliente, LoginForm login) {
        this.cliente = cliente;     
        this.login = login;
        setTitle("Criar novo usuário");                        
        initComponents();      
        txtSenha.setDocument(new JTextFieldLimit(6));
        DragListener drag = new DragListener();
        addMouseListener(drag);
        addMouseMotionListener(drag);
    }

    //Inicializa componentes e etc
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgSexo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnRegistro = new javax.swing.JButton();
        SeparatorNome = new javax.swing.JSeparator();
        SeparatorSenha = new javax.swing.JSeparator();
        lblCpf = new javax.swing.JLabel();
        SeparatorSexo = new javax.swing.JSeparator();
        txtSenha = new javax.swing.JPasswordField();
        lblSexo = new javax.swing.JLabel();
        SeparatorData = new javax.swing.JSeparator();
        SeparatorDoutor = new javax.swing.JSeparator();
        rbMasc1 = new javax.swing.JRadioButton();
        rbFemi1 = new javax.swing.JRadioButton();
        lblData = new javax.swing.JLabel();
        cbDoutor = new javax.swing.JCheckBox();
        txtNome = new javax.swing.JTextField();
        SeparatorCpf = new javax.swing.JSeparator();
        lblSenha = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro");
        setName("dialog"); // NOI18N
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(130, 170, 227));
        jPanel1.setMinimumSize(new java.awt.Dimension(370, 490));
        jPanel1.setPreferredSize(new java.awt.Dimension(370, 490));

        jLabel1.setBackground(new java.awt.Color(102, 255, 255));
        jLabel1.setFont(new java.awt.Font("Liberation Serif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(234, 253, 252));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Triagem Médica");

        jLabel2.setBackground(new java.awt.Color(102, 255, 255));
        jLabel2.setFont(new java.awt.Font("Constantia", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(234, 253, 252));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registre-se como médico ou paciente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(191, 234, 245));
        jPanel2.setMinimumSize(new java.awt.Dimension(370, 490));
        jPanel2.setPreferredSize(new java.awt.Dimension(370, 490));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistro.setBackground(new java.awt.Color(130, 170, 227));
        btnRegistro.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnRegistro.setForeground(new java.awt.Color(234, 253, 252));
        btnRegistro.setText("Registrar");
        btnRegistro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 253, 252), 1, true));
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 240, 40));

        SeparatorNome.setForeground(new java.awt.Color(130, 170, 227));
        jPanel2.add(SeparatorNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 240, 10));

        SeparatorSenha.setForeground(new java.awt.Color(145, 216, 228));
        jPanel2.add(SeparatorSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 240, 10));

        lblCpf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCpf.setForeground(new java.awt.Color(145, 216, 228));
        lblCpf.setText("CPF");
        jPanel2.add(lblCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        SeparatorSexo.setForeground(new java.awt.Color(130, 170, 227));
        jPanel2.add(SeparatorSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 240, 10));

        txtSenha.setBackground(new java.awt.Color(191, 234, 245));
        txtSenha.setForeground(new java.awt.Color(30, 80, 137));
        txtSenha.setBorder(null);
        txtSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSenhaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSenhaFocusLost(evt);
            }
        });
        jPanel2.add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 240, 20));

        lblSexo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSexo.setForeground(new java.awt.Color(130, 170, 227));
        lblSexo.setText("Sexo");
        jPanel2.add(lblSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        SeparatorData.setForeground(new java.awt.Color(145, 216, 228));
        jPanel2.add(SeparatorData, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 240, 10));

        SeparatorDoutor.setForeground(new java.awt.Color(130, 170, 227));
        jPanel2.add(SeparatorDoutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 240, 10));

        rbMasc1.setBackground(new java.awt.Color(191, 234, 245));
        bgSexo.add(rbMasc1);
        rbMasc1.setForeground(new java.awt.Color(130, 170, 227));
        rbMasc1.setSelected(true);
        rbMasc1.setText("Masculino");
        rbMasc1.setToolTipText("");
        rbMasc1.setActionCommand("M");
        rbMasc1.setBorder(null);
        rbMasc1.setContentAreaFilled(false);
        rbMasc1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rbMasc1.setFocusable(false);
        rbMasc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMasc1ActionPerformed(evt);
            }
        });
        jPanel2.add(rbMasc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        rbFemi1.setBackground(new java.awt.Color(191, 234, 245));
        bgSexo.add(rbFemi1);
        rbFemi1.setForeground(new java.awt.Color(130, 170, 227));
        rbFemi1.setText("Feminino");
        rbFemi1.setActionCommand("F");
        rbFemi1.setBorder(null);
        rbFemi1.setContentAreaFilled(false);
        rbFemi1.setFocusable(false);
        rbFemi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFemi1ActionPerformed(evt);
            }
        });
        jPanel2.add(rbFemi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        lblData.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblData.setForeground(new java.awt.Color(145, 216, 228));
        lblData.setText("Data de nascimento");
        jPanel2.add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        cbDoutor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbDoutor.setForeground(new java.awt.Color(130, 170, 227));
        cbDoutor.setText("Sou médico");
        cbDoutor.setContentAreaFilled(false);
        cbDoutor.setFocusPainted(false);
        cbDoutor.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        cbDoutor.setIconTextGap(10);
        cbDoutor.setMargin(new java.awt.Insets(2, 0, 2, 2));
        cbDoutor.setRequestFocusEnabled(false);
        jPanel2.add(cbDoutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 240, -1));

        txtNome.setBackground(new java.awt.Color(191, 234, 245));
        txtNome.setForeground(new java.awt.Color(30, 80, 137));
        txtNome.setBorder(null);
        txtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNomeFocusLost(evt);
            }
        });
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        jPanel2.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 240, 20));

        SeparatorCpf.setForeground(new java.awt.Color(145, 216, 228));
        jPanel2.add(SeparatorCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 240, 10));

        lblSenha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSenha.setForeground(new java.awt.Color(145, 216, 228));
        lblSenha.setText("Senha");
        jPanel2.add(lblSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        lblNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNome.setForeground(new java.awt.Color(130, 170, 227));
        lblNome.setText("Nome");
        jPanel2.add(lblNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        txtData.setBackground(new java.awt.Color(191, 234, 245));
        txtData.setBorder(null);
        txtData.setForeground(new java.awt.Color(30, 80, 137));
        try {
            txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataFocusLost(evt);
            }
        });
        jPanel2.add(txtData, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 240, 20));

        jLabel12.setForeground(new java.awt.Color(130, 170, 227));
        jLabel12.setText("Já possui cadastro?");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 120, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("x");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 20, 30));

        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Logar");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 40, -1));

        txtCpf.setBackground(new java.awt.Color(191, 234, 245));
        txtCpf.setBorder(null);
        txtCpf.setForeground(new java.awt.Color(30, 80, 137));
        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel2.add(txtCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 240, 20));

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents
       
    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        dispose();
        System.exit(0);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        
        //Coletando entradas
        String cpf = txtCpf.getText();
        String senha = String.valueOf(txtSenha.getPassword()); 
        String nome = txtNome.getText();
        String data = txtData.getText().replace("/", "");
        String sexo = bgSexo.getSelection().getActionCommand();
        Boolean doutor = cbDoutor.isSelected();        
        
        //Verificando se entradas não são vazias
        if(cpf.isBlank() || senha.isBlank() || nome.isBlank() || data.isBlank()) {
            JOptionPane.showMessageDialog(
                    rootPane,
                    "Por favor, preencha todos os campos",
                    "Tente novamente",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }  
        
        //Cria o JSON e coloca as entradas
        JSONObject jsonObj = new JSONObject();        
        jsonObj.put("code", 1);
        jsonObj.put("cpf", cpf.replace("-", "").replace(".", ""));
        jsonObj.put("password", senha);
        jsonObj.put("name", nome);                
        jsonObj.put("birthday", data);
        jsonObj.put("sex", sexo);        
        jsonObj.put("doctor", doutor);    
        System.out.println("JSON criada: " + jsonObj.toJSONString());
                
        try {            
            //Envia a mensagem ao server
            String msgServer = cliente.enviaMensagem(jsonObj.toJSONString());
            //Log da resposta
            cliente.clienteLog.atualizaLog("Recebeu: " + msgServer);
            
            //Interpreta a mensagem do server
            jsonObj = new JSONObject();
            jsonObj = (JSONObject) parser.parse(msgServer);                                    
                        
            if(String.valueOf(jsonObj.get("success")).equals("true")) { 
                
                //Se registrado (success: true)
                login.setCpf(cpf);
                login.setVisible(true);                
                this.dispose();
                
            } else {                
                //Se não registrado (success: false)                
                JOptionPane.showMessageDialog(
                    rootPane,
                    "Confira seus dados e tente novamente!",
                    "Erro de Registro",
                    JOptionPane.ERROR_MESSAGE
                );                
            }                        
            
        } catch (IOException ex) {
            System.out.println("Mensagem não enviada");
        } catch (ParseException ex) {
           System.out.println("Erro ao criar Json");
        }               
    }//GEN-LAST:event_btnRegistroActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void rbFemi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFemi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbFemi1ActionPerformed

    private void txtNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusGained
        SeparatorNome.setForeground(new Color(130,170,227));
        lblNome.setForeground(new Color(130,170,227));                
    }//GEN-LAST:event_txtNomeFocusGained

    private void txtNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusLost
        SeparatorNome.setForeground(new Color(145,216,228));
        lblNome.setForeground(new Color(145,216,228));
    }//GEN-LAST:event_txtNomeFocusLost

    private void txtSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusGained
        SeparatorSenha.setForeground(new Color(130,170,227));
        lblSenha.setForeground(new Color(130,170,227));
    }//GEN-LAST:event_txtSenhaFocusGained

    private void txtSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusLost
        SeparatorSenha.setForeground(new Color(145,216,228));
        lblSenha.setForeground(new Color(145,216,228));
    }//GEN-LAST:event_txtSenhaFocusLost

    private void txtDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataFocusGained
        SeparatorData.setForeground(new Color(130,170,227));
        lblData.setForeground(new Color(130,170,227));
        
    }//GEN-LAST:event_txtDataFocusGained

    private void txtDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataFocusLost
        SeparatorData.setForeground(new Color(145,216,228));
        lblData.setForeground(new Color(145,216,228));
    }//GEN-LAST:event_txtDataFocusLost

    private void rbMasc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMasc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbMasc1ActionPerformed
                 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator SeparatorCpf;
    private javax.swing.JSeparator SeparatorData;
    private javax.swing.JSeparator SeparatorDoutor;
    private javax.swing.JSeparator SeparatorNome;
    private javax.swing.JSeparator SeparatorSenha;
    private javax.swing.JSeparator SeparatorSexo;
    private javax.swing.ButtonGroup bgSexo;
    private javax.swing.JButton btnRegistro;
    private javax.swing.JCheckBox cbDoutor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JRadioButton rbFemi1;
    private javax.swing.JRadioButton rbMasc1;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
       
}
