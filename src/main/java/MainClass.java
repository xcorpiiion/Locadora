import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.unip.view.LocadoraView;

@SpringBootApplication
@ComponentScan("br.com.unip.")
public class MainClass {

	public static void main(String[] args) {
		SpringApplication.run(MainClass.class, args);
		iniciaJOptinPane();
		while (true) {
			Object[] escolhaClienteOrLocadora = { "Cliente", "Locadora" };
			String resposta = (String) JOptionPane.showInputDialog(null, "Escolher se você é um Cliente ou Locadora",
					"Cliente ou Locadora", JOptionPane.QUESTION_MESSAGE, null, escolhaClienteOrLocadora, null);
			switch (resposta) {
			case "Locadora":
				LocadoraView.escolherCadastroCarroOuCadastroLocadora();
				break;
			case "Cliente":
				JOptionPane.showMessageDialog(null, "Cliente");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Digito invalido");
				break;
			}
		}
	}

	private static void iniciaJOptinPane() {
		System.setProperty("java.awt.headless", "false");
		SwingUtilities.invokeLater(() -> {
			JFrame jFrame = new JFrame("myframe");
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setVisible(true);
		});
	}

}
