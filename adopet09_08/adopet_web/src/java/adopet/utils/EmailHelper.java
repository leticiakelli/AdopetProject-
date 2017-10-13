package adopet.utils;

import org.apache.commons.mail.SimpleEmail;



public class EmailHelper {
	public static void main(String[] args) {
		sendEmail("smtp.gmail.com", 465, "leticiakelli@gmail.com", "TESTE#", "projetoadopet2017@gmail.com",
				"TESTEREMETENTE", "TESTE", "TESTE TESTE TESTE TESTE", "projeto.adopet");
	}

	public static boolean sendEmail(String smtp, int port, String emailDestinatario, String nomeDestinatario,
			String emailRemetente, String nomeRemetente, String assunto, String conteudo, String senhaEmail) {
		          SimpleEmail email = new SimpleEmail();
		boolean send = false;
		try {
			email.setHostName(smtp); // o servidor SMTP para envio do e-mail
			email.setSmtpPort(port);
			email.addTo(emailDestinatario, nomeDestinatario); // destinatário
			email.setFrom(emailRemetente, nomeRemetente); // remetente
			email.setSubject(assunto); // assunto do e-mail
			email.setMsg(conteudo); // conteudo do e-mail
//			System.out.println("autenticando...");
			email.setSSL(true);
			email.setAuthentication(emailRemetente, senhaEmail);
//			System.out.println("enviando...");
			email.send();
//			System.out.println("Email enviado!");
			send = true;
//			System.out.println("EMAIL ENVIADO");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao enviar email");
			send = false;
		}
		return send;

	}


}