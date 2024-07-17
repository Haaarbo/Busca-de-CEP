package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.Choice;

@SuppressWarnings("serial")
public class Cep extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCep;
	private JLabel lblNewLabel_1;
	private JTextField txtEndereco;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblNewLabel_3;
	private JTextField txtCidade;
	private JLabel lblNewLabel_2;
	String[] boxOptions = { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
			"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
	JComboBox<String> cboUf = new JComboBox<>(boxOptions);
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setLocationRelativeTo(null); // centraliza a janela
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Cep() {
		setTitle("Buscar CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setBounds(10, 34, 45, 13);
		contentPane.add(lblNewLabel);

		txtCep = new JTextField();
		txtCep.setBounds(80, 31, 85, 19);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setBounds(10, 87, 71, 13);
		contentPane.add(lblNewLabel_1);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(80, 84, 316, 19);
		contentPane.add(txtEndereco);

		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(10, 126, 45, 13);
		contentPane.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(80, 123, 316, 19);
		contentPane.add(txtBairro);

		lblNewLabel_3 = new JLabel("Cidade");
		lblNewLabel_3.setBounds(10, 166, 45, 13);
		contentPane.add(lblNewLabel_3);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(80, 163, 222, 19);
		contentPane.add(txtCidade);

		lblNewLabel_2 = new JLabel("UF");
		lblNewLabel_2.setBounds(321, 166, 34, 13);
		contentPane.add(lblNewLabel_2);

		cboUf.setBounds(348, 162, 48, 21);
		getContentPane().add(cboUf);
		contentPane.add(cboUf);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) { // caso o texto em txtCep seja ""
					JOptionPane.showMessageDialog(null, "Informe o CEP!");
					txtCep.requestFocus();
				} else { // caso nao esteja vazio
					buscarCep();
				}
			}
		});
		btnCep.setBounds(213, 30, 85, 21);
		contentPane.add(btnCep);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(80, 209, 85, 21);
		contentPane.add(btnLimpar);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre(); // cria um objeto da janela Sobre e chama de sobre
				sobre.setVisible(true); // faz com que apareca a janela criada
				sobre.setLocationRelativeTo(btnSobre);
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/about.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBounds(331, 18, 48, 48);
		contentPane.add(btnSobre);

		// restricoes, uso da biblioteca Atxy2k.jar para restricoes de txtCep
		RestrictedTextField valid = new RestrictedTextField(txtCep);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(175, 31, 20, 20);
		contentPane.add(lblStatus);

		// "valid"
		// recebe como argumento para o construtor de restricao o objeto que serao
		// aplicadas as restricoes
		valid.setOnlyNums(true);
		valid.setLimit(8);

	} // fim do construtor jframe

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					// se o resultado nao for igual a string 1, ou seja, se nao existir
					if (!resultado.equals("1")) {
						JOptionPane.showMessageDialog(null, "CEP nao encontrado!");
					} else {
						// se o resultado existir
						lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
					}
				}
				// settar o campo endereco
				txtEndereco.setText(tipoLogradouro + " " + logradouro);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	private void limpar() { //limpa todos os campos registrados
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.requestFocus(); //move a selecao para esta caixa de texto para proxima escrita
		lblStatus.setIcon(null); //remove o icone exibido quando a procura foi feita
	}
}
