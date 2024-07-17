package cep;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import cep.Cep;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		int[] v1 = new int[5];
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(600, 250, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModal(true); //setta que a janela será focada até seu fechamento
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel_1 = new JLabel("Buscar CEP v1.0");
			lblNewLabel_1.setBounds(165, 25, 103, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel = new JLabel("Abrahão N. Guimarães");
			lblNewLabel.setBounds(151, 48, 144, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblWebService = new JLabel("Web Service:");
			lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblWebService.setBounds(29, 146, 79, 13);
			contentPanel.add(lblWebService);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("José de Assis:");
			lblNewLabel_4.setBounds(29, 120, 91, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblFonte = new JLabel("https://www.youtube.com/@ProfessorJosedeAssis");
			lblFonte.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					link("https://www.youtube.com/@ProfessorJosedeAssis");
				}
			});
			lblFonte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblFonte.setForeground(SystemColor.textHighlight);
			lblFonte.setBounds(130, 120, 271, 13);
			contentPanel.add(lblFonte);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("https://www.republicavirtual.com.br/");
			lblNewLabel_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblNewLabel_6.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					link("https://www.republicavirtual.com.br/");
				}
			});
			lblNewLabel_6.setForeground(SystemColor.textHighlight);
			lblNewLabel_6.setBounds(130, 146, 239, 13);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JButton btnGithub = new JButton("");
			btnGithub.setForeground(SystemColor.control);
			btnGithub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
				        Desktop.getDesktop().browse(new URI("https://www.github.com/Haaarbo"));
				    } catch (IOException | URISyntaxException ex) {
				        ex.printStackTrace();
				    }
				}
			});
			btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
			btnGithub.setBorder(null);
			btnGithub.setBounds(126, 196, 48, 48);
			contentPanel.add(btnGithub);
		}
		{
			JButton btnLinkedin = new JButton("");
			btnLinkedin.setForeground(SystemColor.control);
			btnLinkedin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
				        Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/abrahao0707/"));
				    } catch (IOException | URISyntaxException ex) {
				        ex.printStackTrace();
				    }
				}
			});
			btnLinkedin.setIcon(new ImageIcon(Sobre.class.getResource("/img/linkedin.png")));
			btnLinkedin.setBorder(null);
			btnLinkedin.setBounds(247, 196, 48, 48);
			contentPanel.add(btnLinkedin);
		}
		
		JLabel lblNewLabel_3 = new JLabel("Fontes:");
		lblNewLabel_3.setBounds(10, 83, 45, 13);
		contentPanel.add(lblNewLabel_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	} //fim do construtor
	
	//metodo da classe em questao para realizar o link de algo do java para links externos
	private void link (String site) {
		Desktop desk = Desktop.getDesktop(); //cria um objeto Desktop que reconhecer e pega o ambiente de interface grafica do sistema 
		try {
			URI uri = new URI(site);
			/*
			 * Existe o termo URL e URI
			 * Nesse caso sera usado URI pois executa o navegador padrao DENTRO da interface grafica no SO do usuario
			 * Cria uma instancia do tipo URI que recebera a url do site em questao
			 */
			desk.browse(uri); //manipula a variavel do desktop para que abra o site via navegador padrao
		} catch (Exception e) {
			System.out.println(e); //tratamento do erro, diz o erro que deu
		}
	}
}
