package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
// alinha abaixo importa recursos da biblioteca rs2xml.jat
import net.proteanit.sql.DbUtils;

public class TelaPaciente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaPaciente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    /*private void ficha() {
        String sql = "insert into tbficha (sintomas, motivo, pedexame, resulexame, diag, medica, alergia) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPSintomas.getText());
            pst.setString(2, txtPMotivo.getText());
            pst.setString(3, txtPPedido.getText());
            pst.setString(4, txtPResultado.getText());
            pst.setString(5, txtPDiag.getText());
            pst.setString(6, txtPMedi.getText());
            pst.setString(7, txtPAlergia.getText());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
                txtPNome.setText(null);
                txtPEndereco.setText(null);
                txtPTelefone.setText(null);
                txtPEmail.setText(null);
                txtPIdade.setText(null);
            }
            JOptionPane.showMessageDialog(null, "FICHA!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }*/

// metodo para adicionar paciente
    private void adicionar() {
        String sql = "insert into tbpacient (nome, ende, fone, idade, email, sexo) values(?, ?, ?, ?, ?, ?)";
        sql = "insert into tbficha (sintomas, motivo, pedexame, resulexame, diag, medica, alergia) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPNome.getText());
            pst.setString(2, txtPEndereco.getText());
            pst.setString(3, txtPTelefone.getText());
            pst.setString(4, txtPIdade.getText());
            pst.setString(5, txtPEmail.getText());
            pst.setString(6, cbPSexo.getSelectedItem().toString());
            pst.setString(1, txtPSintomas.getText());
            pst.setString(2, txtPMotivo.getText());
            pst.setString(3, txtPPedido.getText());
            pst.setString(4, txtPResultado.getText());
            pst.setString(5, txtPDiag.getText());
            pst.setString(6, txtPMedi.getText());
            pst.setString(7, txtPAlergia.getText());
            //validação dos campos obrigatorios
            if ((txtPNome.getText().isEmpty()) || (txtPTelefone.getText().isEmpty()) || (txtPEndereco.getText().isEmpty()) || (txtPIdade.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
            } else {
                //a linha abaixo atualiza a tabela paciente com os dados do formuláeio
                //a estrutura abaixo é usada para confirmar que o usuario foi adicionado
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
                    txtPNome.setText(null);
                    txtPEndereco.setText(null);
                    txtPTelefone.setText(null);
                    txtPEmail.setText(null);
                    txtPIdade.setText(null);
                    txtPSintomas.setText(null);
                    txtPMotivo.setText(null);
                    txtPPedido.setText(null);
                    txtPResultado.setText(null);
                    txtPDiag.setText(null);
                    txtPMedi.setText(null);
                    txtPAlergia.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo para pesquisar clientes pelo nome do filtro
    private void pesquisar_paciente() {
        String sql = "select * from tbpacient where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao % que é a continuação da String sql
            pst.setString(1, txtPPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            tblPacientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
        }
    }

    //metodo para setar os campos do formulário com o conteúdo da tabela
    public void setar_campos() {
        int setar = tblPacientes.getSelectedRow();
        txtPId.setText(tblPacientes.getModel().getValueAt(setar, 0).toString());
        txtPNome.setText(tblPacientes.getModel().getValueAt(setar, 1).toString());
        txtPEndereco.setText(tblPacientes.getModel().getValueAt(setar, 2).toString());
        txtPTelefone.setText(tblPacientes.getModel().getValueAt(setar, 3).toString());
        txtPIdade.setText(tblPacientes.getModel().getValueAt(setar, 4).toString());
        txtPEmail.setText(tblPacientes.getModel().getValueAt(setar, 5).toString());
        cbPSexo.setSelectedItem(tblPacientes.getModel().getValueAt(setar, 6).toString());
        /*txtPSintomas.setText(tblPacientes.getModel().getValueAt(setar, 7).toString());
        txtPMotivo.setText(tblPacientes.getModel().getValueAt(setar, 8).toString());
        txtPPedido.setText(tblPacientes.getModel().getValueAt(setar, 9).toString());
        txtPResultado.setText(tblPacientes.getModel().getValueAt(setar, 10).toString());
        txtPDiag.setText(tblPacientes.getModel().getValueAt(setar, 11).toString());
        txtPMedi.setText(tblPacientes.getModel().getValueAt(setar, 12).toString());
        txtPAlergia.setText(tblPacientes.getModel().getValueAt(setar, 13).toString());*/
        btnPAdicionar.setEnabled(false);
    }

    //metodo para alterar dados dos pacientes
    private void alterar() {
        String sql = "update tbpacient set nome=?, ende=?, fone=?, idade=?, email=?, sintomas=?, motivo=?, pedexame=?, resulexame=?, diag=?, medica=?, alergia=? where id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPNome.getText());
            pst.setString(2, txtPEndereco.getText());
            pst.setString(3, txtPTelefone.getText());
            pst.setString(4, txtPIdade.getText());
            pst.setString(5, txtPEmail.getText());
            pst.setString(6, txtPSintomas.getText());
            pst.setString(7, txtPMotivo.getText());
            pst.setString(8, txtPPedido.getText());
            pst.setString(9, txtPResultado.getText());
            pst.setString(10, txtPDiag.getText());
            pst.setString(11, txtPMedi.getText());
            pst.setString(12, txtPAlergia.getText());
            pst.setString(13, txtPId.getText());
            if ((txtPNome.getText().isEmpty()) || (txtPTelefone.getText().isEmpty()) || (txtPEndereco.getText().isEmpty()) || (txtPIdade.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
            } else {
                //a linha abaixo atualiza a tabela paciente com os dados do formuláeio
                //a estrutura abaixo é usada para confirmar que o usuario foi alterado
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do paciente alterados com sucesso!");
                    txtPNome.setText(null);
                    txtPEndereco.setText(null);
                    txtPTelefone.setText(null);
                    txtPEmail.setText(null);
                    txtPIdade.setText(null);
                    txtPSintomas.setText(null);
                    txtPMotivo.setText(null);
                    txtPPedido.setText(null);
                    txtPResultado.setText(null);
                    txtPDiag.setText(null);
                    txtPMedi.setText(null);
                    txtPAlergia.setText(null);
                    btnPAdicionar.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo responsavel pela remoção de paciente
    private void remover() {
        //a estrutura abaixo confirma a remoção do paciente
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse paciente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbpacient where id=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtPId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Paciente removido com sucesso!");
                    txtPNome.setText(null);
                    txtPEndereco.setText(null);
                    txtPTelefone.setText(null);
                    txtPEmail.setText(null);
                    txtPIdade.setText(null);
                    txtPSintomas.setText(null);
                    txtPMotivo.setText(null);
                    txtPPedido.setText(null);
                    txtPResultado.setText(null);
                    txtPDiag.setText(null);
                    txtPMedi.setText(null);
                    txtPAlergia.setText(null);
                    btnPAdicionar.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPNome = new javax.swing.JTextField();
        txtPEndereco = new javax.swing.JTextField();
        txtPTelefone = new javax.swing.JTextField();
        txtPEmail = new javax.swing.JTextField();
        cbPSexo = new javax.swing.JComboBox<>();
        btnPAdicionar = new javax.swing.JButton();
        btnPAlterar = new javax.swing.JButton();
        btnPRemover = new javax.swing.JButton();
        txtPPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtPId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPIdade = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPSintomas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtPMotivo = new javax.swing.JTextField();
        txtPPedido = new javax.swing.JTextField();
        txtPResultado = new javax.swing.JTextField();
        txtPDiag = new javax.swing.JTextField();
        txtPMedi = new javax.swing.JTextField();
        txtPAlergia = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jTextField5.setText("jTextField5");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jLabel1.setText("Nome*:");

        jLabel2.setText("Endereço*:");

        jLabel3.setText("Telefone*:");

        jLabel4.setText("E-mail:");

        jLabel5.setText("Sexo*:");

        cbPSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        btnPAdicionar.setText("Adicionar");
        btnPAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAdicionarActionPerformed(evt);
            }
        });

        btnPAlterar.setText("Alterar");
        btnPAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAlterarActionPerformed(evt);
            }
        });

        btnPRemover.setText("Remover");
        btnPRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPRemoverActionPerformed(evt);
            }
        });

        txtPPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPPesquisarKeyReleased(evt);
            }
        });

        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPacientes.getTableHeader().setReorderingAllowed(false);
        tblPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPacientes);

        jLabel7.setText("ID*:");

        txtPId.setEnabled(false);

        jLabel8.setText("Idade*:");

        jLabel10.setText("Sintomas:");

        jLabel11.setText("Motivo do Encaminhamento: ");

        jLabel12.setText("Pedido de Exame:");

        jLabel13.setText("Resultado de Exame:");

        jLabel14.setText("Diagnóstico:");

        jLabel15.setText("Prescrever Medicamento:");

        jLabel16.setText("Alergia:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Cadastrar novo Paciente");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Ficha de Referência");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Procurar por paciente");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("* Campos Obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPNome, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtPIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbPSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtPEndereco, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel18)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtPPesquisar))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPPedido)
                        .addComponent(txtPMotivo)
                        .addComponent(txtPResultado)
                        .addComponent(txtPDiag, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtPMedi, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtPAlergia)
                        .addComponent(txtPSintomas)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel19)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jLabel15))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnPAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnPAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnPRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnPAdicionar, btnPAlterar, btnPRemover});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(10, 10, 10)
                        .addComponent(txtPPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtPId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtPIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cbPSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(5, 5, 5)
                        .addComponent(txtPDiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPMedi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPAdicionar)
                            .addComponent(btnPAlterar)
                            .addComponent(btnPRemover)))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnPAdicionar, btnPAlterar, btnPRemover});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAdicionarActionPerformed
        //chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnPAdicionarActionPerformed

    //o evento abaixo é do tipo "enquanto for digitando"
    private void txtPPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPPesquisarKeyReleased
        // chamar método pesquisar clientes
        pesquisar_paciente();
    }//GEN-LAST:event_txtPPesquisarKeyReleased

    //evento que será usado para setar os campos da tabela (clicando com o mouse)
    private void tblPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacientesMouseClicked
        //chamando o metodo para setar os campos
        setar_campos();
    }//GEN-LAST:event_tblPacientesMouseClicked

    //cjhamando o metodo para alterar paciente
    private void btnPAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_btnPAlterarActionPerformed

    private void btnPRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPRemoverActionPerformed
        // chamando o metodo para remover um paciente
        remover();
    }//GEN-LAST:event_btnPRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPAdicionar;
    private javax.swing.JButton btnPAlterar;
    private javax.swing.JButton btnPRemover;
    private javax.swing.JComboBox<String> cbPSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTable tblPacientes;
    private javax.swing.JTextField txtPAlergia;
    private javax.swing.JTextField txtPDiag;
    private javax.swing.JTextField txtPEmail;
    private javax.swing.JTextField txtPEndereco;
    private javax.swing.JTextField txtPId;
    private javax.swing.JTextField txtPIdade;
    private javax.swing.JTextField txtPMedi;
    private javax.swing.JTextField txtPMotivo;
    private javax.swing.JTextField txtPNome;
    private javax.swing.JTextField txtPPedido;
    private javax.swing.JTextField txtPPesquisar;
    private javax.swing.JTextField txtPResultado;
    private javax.swing.JTextField txtPSintomas;
    private javax.swing.JTextField txtPTelefone;
    // End of variables declaration//GEN-END:variables
}
