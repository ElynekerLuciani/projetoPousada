package controller;

import dao.EstadoCidadeDAO;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Cidade;
import model.Cliente;
import model.Cnpj;
import model.CnpjInvalidoException;
import model.Contato;
import model.Cpf;
import model.CpfInvalidoException;
import model.Documento;
import model.Endereco;
import model.Estado;
import model.ValidarCPF;
import model.ValidateString;
import static model.ValidateString.verifyOnlyLetters;
import view.TelaCadastroCliente;

/**
 *
 * @author Elyneker Luciani
 */
public class TelaCadastroClienteController {

    private TelaCadastroCliente telaCadastroCliente;
    private final EstadoCidadeDAO estadoCidadeDAO = new EstadoCidadeDAO();
    private final Cidade cidade = new Cidade();
    private final Cliente clienteModelo = new Cliente();

    public void setTelaCadastroCliente(TelaCadastroCliente t) {
        this.telaCadastroCliente = t;
    }

    public void executar(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Cadastrar":
                cadastrarCliente();
                break;
            case "Pessoa Física":
                bloquearComponentes();
                break;
            case "Cancelar":
                limparCampos();
                break;
            case "Pessoa Jurídica":
                bloquearComponentes();
                break;
            case "Estrangeiro":
                bloquearComponentes();
                break;

        }
    }

    /**
     * Neste método criamos uma lista contendo todos os nomes de estados para
     * ser utilizado no combobox estados na tela de cadastro de cliente.
     *
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ArrayList<Object> carregarEstadosCombobox() throws ClassNotFoundException, SQLException {
        ArrayList<Estado> listaEstados = estadoCidadeDAO.listarEstados();
        ArrayList<Object> estados = new ArrayList();
        try {
            for (int i = 0; i < listaEstados.size(); i++) {
                estados.add(listaEstados.get(i));
            }
//            estadoModelo = new EstadoComboBoxModel(estados);
//            telaCadastroCliente.getjComboBoxEstado().setModel(estadoModelo);
        } catch (Exception e) {
            System.out.println("CidadeEstadoController.carregarEstadosCombobox " + e);
        }
        return estados;
    }

    /**
     * Neste método é responsável pela criação da lista contendo todas as
     * cidades do estado selecionado na tela de cadastro de cliente. Criamos uma
     * lista de cidade que é enviada para a tela preencher os dados no combobox
     * de cidades. Estes dados são selecionados a partir do número do id que foi
     * selecionado no combobox estado.
     *
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ArrayList<Object> carregarCidadesCombobox() throws ClassNotFoundException, SQLException {
        ArrayList<Object> cidades = new ArrayList();
        try {
            //ArrayList<Cidade> listaCidades = estadoCidadeDAO.listarCidades(telaCadastroCliente.getjComboBoxEstado().getSelectedIndex()+1);
            ArrayList<Cidade> listaCidades = estadoCidadeDAO.listarCidades(telaCadastroCliente.getEstadoModelo().getSelecteditemCod());
            for (int i = 0; i < listaCidades.size(); i++) {
                cidades.add(listaCidades.get(i));
            }
//            cidadeModelo = new CidadeComboBoxModel(cidades);
//           
//            telaCadastroCliente.getjComboBoxCidade().setModel(cidadeModelo);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Controller.CidadeEstadoController.carregarCidadesCombobox: " + e);
        }
        return cidades;
    }

    /**
     *
     */
    private void cadastrarCliente() {
        try {
            //Primeiro verificamos se o campo nome não está vazio e testa se o nome inserido é válido (não contém caracteres especiais)
            if (!telaCadastroCliente.getjTextFieldNome().getText().isEmpty()
                    && ValidateString.verifyOnlyLetters(telaCadastroCliente.getjTextFieldNome().getText().replace(".", "").replace("-", "").replace("/", "").trim())) {
                //Pegando o nome do cliente, removendo espaços e colocando em fonte maiúscula
                clienteModelo.setNomeCliente(telaCadastroCliente.getjTextFieldNome().getText().trim().toLowerCase());
                //Verificamos se o tipo de cliente que está selecionado é pessoa física
                if (telaCadastroCliente.getjRadioButtonPessoaFisica().isSelected() && ValidarCPF.isValid(
                        telaCadastroCliente.getjFormattedTextFieldCPF().getText())) {
                    //Pegando o numero do campo cpf e convertendo para o objeto CPF
                    clienteModelo.setDocumento(new Documento(new Cpf(Cpf.converteCpf(telaCadastroCliente.getjFormattedTextFieldCPF().getText().replace(".", "").replace("-", "").trim()))));
                } //Verificamos se o cliente selecionado é do tipo pessoa jurídica
                else if (telaCadastroCliente.getjRadioButtonPessoaJuridica().isSelected()) {
                    //Pegando o numero do campo cnpj e convertendo para o objeto CNPJ. Na classe cnpj é realizado um teste para validar o número
                    clienteModelo.setDocumento(new Documento(new Cnpj(Cnpj.converteCnpj(telaCadastroCliente.getjFormattedTextFieldCNPJ().getText().replace(".", "").replace("-", "").replace("/", "").trim()))));
                } //Verificamos se o cliente selecionado é do tipo estrangeiro
                else if (telaCadastroCliente.getjRadioButtonEstrangeiro().isSelected()) {
                    //Pegando o número do campo passaporte e colocando no objeto documento
                    clienteModelo.setDocumento(new Documento(telaCadastroCliente.getjTextFieldPassaporte().getText()));
                } 
                
                //Pegando o endereço do cliente e o id da cidade
                clienteModelo.setEnderecoCliente(new Endereco(telaCadastroCliente.getjTextFieldEndereco().getText().toLowerCase(),
                        new Cidade(telaCadastroCliente.getCidadeModelo().getSelectedItemCod())));
                //Pegando o celular e o telefone
                clienteModelo.setContatoCliente(new Contato(telaCadastroCliente.getjFormattedTextFieldCelular().getText().replace(".", "").replace("-", "").replace("(", "").replace(")", ""),
                        telaCadastroCliente.getjFormattedTextFieldTelefone().getText().replace(".", "").replace("-", "").replace("(", "").replace(")", "")));
                
                System.out.println(clienteModelo.getContatoCliente().getTelefone().isEmpty());

            } else {
                JOptionPane.showMessageDialog(null, "Verifique o nome do cliente", "Cadastro não realizado", JOptionPane.WARNING_MESSAGE);

            }
        } catch (HeadlessException | CnpjInvalidoException | CpfInvalidoException e) {
            System.out.println(e);
        }
    }

    /**
     * Neste método realizamos a bloqueio de componentes da tela de cadastro
     * cliente para que o usuário ao selecionar um dos tipo (pessoa física,
     * pessoa jurídica ou estrangeiro) possa inserir os dados corretamente,
     * evitando que sejam inseridos dados incorretos para um desses tipos.
     */
    private void bloquearComponentes() {
        if (telaCadastroCliente.getjRadioButtonPessoaFisica().isSelected()) {
            telaCadastroCliente.getjFormattedTextFieldCPF().setEditable(true);
            telaCadastroCliente.getjFormattedTextFieldCNPJ().setEditable(false);
            telaCadastroCliente.getjTextFieldPassaporte().setEditable(false);
        } else if (telaCadastroCliente.getjRadioButtonPessoaJuridica().isSelected()) {
            telaCadastroCliente.getjFormattedTextFieldCPF().setEditable(false);
            telaCadastroCliente.getjFormattedTextFieldCNPJ().setEditable(true);
            telaCadastroCliente.getjTextFieldPassaporte().setEditable(false);
        } else if (telaCadastroCliente.getjRadioButtonEstrangeiro().isSelected()) {
            telaCadastroCliente.getjFormattedTextFieldCPF().setEditable(false);
            telaCadastroCliente.getjFormattedTextFieldCNPJ().setEditable(false);
            telaCadastroCliente.getjTextFieldPassaporte().setEditable(true);
        }
    }

    /**
     * Neste método realizamos a limpeza dos campos quando o usuário clica em
     * cancelar na tela de cadastro de cliente.
     */
    private void limparCampos() {
        telaCadastroCliente.getjRadioButtonPessoaFisica().setSelected(true);
        telaCadastroCliente.getjTextFieldNome().setText("");
        telaCadastroCliente.getjFormattedTextFieldCPF().setText("");
        telaCadastroCliente.getjFormattedTextFieldCNPJ().setText("");
        telaCadastroCliente.getjTextFieldPassaporte().setText("");
        //telaCadastroCliente.getjComboBoxEstado().setModel(new DefaultComboBoxModel<>());
        telaCadastroCliente.getjComboBoxCidade().setModel(new DefaultComboBoxModel<>());
        telaCadastroCliente.getjTextFieldEndereco().setText("");
        telaCadastroCliente.getjFormattedTextFieldCelular().setText("");
        telaCadastroCliente.getjFormattedTextFieldTelefone().setText("");
        bloquearComponentes();
    }

}
