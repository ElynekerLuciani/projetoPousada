package controller;

import container.ContainerMenuCliente;
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
import exception.CnpjInvalidoException;
import model.Contato;
import model.Cpf;
import exception.CpfInvalidoException;
import exception.PassaporteInvalidoExpection;
import model.Documento;
import model.Endereco;
import model.Estado;
import model.TipoCliente;
import model.TipoEnum;
import model.ValidarCPF;
import model.ValidarPassaporte;
import model.ValidateString;
import view.TelaCadastroCliente;
import model.ClienteStrategy;

/**
 *
 * @author Elyneker Luciani
 */
public class TelaCadastroClienteController {

    private TelaCadastroCliente telaCadastroCliente;
    private final EstadoCidadeDAO estadoCidadeDAO = new EstadoCidadeDAO();
    private final Cidade cidade = new Cidade();
    private Cliente clienteModelo;

    public void setTelaCadastroCliente(TelaCadastroCliente t) {
        this.telaCadastroCliente = t;
    }

    public void executar(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Cadastrar":
                cadastrar();
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

//    private void cadastrarTipoCliente() {
//        TipoEnum tipo = TipoEnum.PF;
//        ClienteStrategy cliente = tipo.cadastrarNovoCliente();
//        cliente.cadastrarCliente(clienteModelo);
//    }
    /**
     *
     */
    private void cadastrar() {
        clienteModelo = new Cliente();
        try {
            //Primeiro verificamos se o campo nome não está vazio e testa se o nome inserido é válido (não contém caracteres especiais)
            if (ValidateString.verifyOnlyLetters(telaCadastroCliente.getjTextFieldNome()
                    .getText()
                    .replace(".", "")
                    .replace("-", "")
                    .replace("/", "")
                    .trim())) {
                //Pegando o nome do cliente, removendo espaços e colocando em fonte maiúscula
                clienteModelo.setNomeCliente(
                        telaCadastroCliente.getjTextFieldNome()
                                .getText()
                                .trim()
                                .toLowerCase());
                //Pegando o endereço do cliente e o id da cidade
                clienteModelo.setEnderecoCliente(
                        new Endereco(telaCadastroCliente.getjTextFieldEndereco()
                                .getText()
                                .trim()
                                .toLowerCase(),
                                new Cidade(telaCadastroCliente.getCidadeModelo().getSelectedItemCod())));
                //Pegando os dados de celular e de telefone da tela de cadastro e completando o objeto cliente
                clienteModelo.setContatoCliente(
                        new Contato(telaCadastroCliente.getjFormattedTextFieldCelular()
                                .getText().replace(".", "")
                                .replace("-", "")
                                .replace("(", "")
                                .replace(")", ""),
                                telaCadastroCliente.getjFormattedTextFieldTelefone()
                                        .getText()
                                        .replace(".", "")
                                        .replace("-", "")
                                        .replace("(", "")
                                        .replace(")", "")));
                //Verificamos se o tipo de cliente que está selecionado é pessoa física e validamos o CPF
                if (telaCadastroCliente.getjRadioButtonPessoaFisica().isSelected()
                        && ValidarCPF.isValid(
                                telaCadastroCliente.getjFormattedTextFieldCPF()
                                        .getText())) {
                    //Pegando o numero do campo cpf e convertendo para o objeto CPF
                    clienteModelo.setDocumento(
                            new Documento(new Cpf(Cpf.converteCpf(
                                    telaCadastroCliente.getjFormattedTextFieldCPF()
                                            .getText().replace(".", "")
                                            .replace("-", "")
                                            .trim()))));
                    //Passando o tipo de cliente
                    clienteModelo.setTipoCliente(TipoCliente.PF);
                    //Strategy responsável pelo cadastro dos tipos de clientes
                    TipoEnum tipo = TipoEnum.PF;
                    ClienteStrategy cliente = tipo.cadastrarNovoCliente();
                    cliente.cadastrarCliente(clienteModelo);
                } //Verificamos se o cliente selecionado é do tipo pessoa jurídica
                else if (telaCadastroCliente.getjRadioButtonPessoaJuridica().isSelected()) {
                    //Pegando o numero do campo cnpj e convertendo para o objeto CNPJ. Na classe cnpj é realizado um teste para validar o número
                    clienteModelo.setDocumento(new Documento(new Cnpj(
                            Cnpj.converteCnpj(telaCadastroCliente.getjFormattedTextFieldCNPJ()
                                    .getText().replace(".", "")
                                    .replace("-", "")
                                    .replace("/", "")
                                    .trim()))));
                    //Passando o tipo de cliente
                    clienteModelo.setTipoCliente(TipoCliente.PJ);
                    //Strategy responsável pelo cadastro dos tipos de clientes
                    TipoEnum tipo = TipoEnum.PJ;
                    ClienteStrategy cliente = tipo.cadastrarNovoCliente();
                    cliente.cadastrarCliente(clienteModelo);
                } //Verificamos se o cliente selecionado é do tipo estrangeiro e validamos o passaporte (precisa ainda inserir novas regras nessa validação)
                else if (telaCadastroCliente.getjRadioButtonEstrangeiro().isSelected()
                        && ValidarPassaporte.isValid(telaCadastroCliente.getjTextFieldPassaporte()
                                .getText())) {
                    //Pegando o número do campo passaporte e colocando no objeto documento
                    clienteModelo.setDocumento(new Documento(telaCadastroCliente.getjTextFieldPassaporte()
                            .getText()));
                    //Passando o tipo de clietne
                    clienteModelo.setTipoCliente(TipoCliente.ES);
                    //Strategy responsável pelo cadastro dos tipos de clientes
                    TipoEnum tipo = TipoEnum.ES;
                    ClienteStrategy cliente = tipo.cadastrarNovoCliente();
                    cliente.cadastrarCliente(clienteModelo);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um tipo de cliente", "Cadastro não realizado", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                //Mensagem para o usuário corrigir o nome do cliente pois possui caracteres incorretos testados na validação inicial
                throw new Exception("Verifique e nome do cliente.");
                //JOptionPane.showMessageDialog(null, "Verifique o nome do cliente", "Cadastro não realizado", JOptionPane.WARNING_MESSAGE);
            }
            //Se o cliente selecionado estiver passado na verificação, aparece uma mensagem de sucesso e limpamos todos os campos
            JOptionPane.showMessageDialog(null, "Cadastro realizado", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (HeadlessException | CnpjInvalidoException | CpfInvalidoException | PassaporteInvalidoExpection e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Cadastro não realizado", JOptionPane.ERROR_MESSAGE);
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, x.getMessage(), "Cadastro não realizado", JOptionPane.ERROR_MESSAGE);
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
        } limparParcialmenteCampos();
        
    }

    /**
     * Neste método realizamos a limpeza dos campos quando o usuário clica em
     * cancelar na tela de cadastro de cliente.
     */
    private void limparCampos() {
        telaCadastroCliente.getjRadioButtonPessoaFisica().setSelected(true);
        telaCadastroCliente.getjTextFieldNome().setText("");
        telaCadastroCliente.getjFormattedTextFieldCPF().setValue(null);
        telaCadastroCliente.getjFormattedTextFieldCNPJ().setText("");
        telaCadastroCliente.getjTextFieldPassaporte().setText("");
        //telaCadastroCliente.getjComboBoxEstado().setModel(new DefaultComboBoxModel<>());
        telaCadastroCliente.getjComboBoxCidade().setModel(new DefaultComboBoxModel<>());
        telaCadastroCliente.getjTextFieldEndereco().setText("");
        telaCadastroCliente.getjFormattedTextFieldCelular().setValue(null);
        telaCadastroCliente.getjFormattedTextFieldTelefone().setValue(null);
        bloquearComponentes();
    }
    
    /**
     * Este método limpa apenas alguns campos para que, durante a troca de
     * tipo de cliente selecionado na tela de cadastro, alguns dados permaneca
     * na tela, facilitando a conclusão do cadastro pelo usuário.
     */
    private void limparParcialmenteCampos() {
        telaCadastroCliente.getjFormattedTextFieldCPF().setValue(null);
        telaCadastroCliente.getjFormattedTextFieldCNPJ().setValue(null);
        telaCadastroCliente.getjTextFieldPassaporte().setText("");
    }

}
