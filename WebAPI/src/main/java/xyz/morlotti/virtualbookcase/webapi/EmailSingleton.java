package xyz.morlotti.virtualbookcase.webapi;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.email.EmailPopulatingBuilder;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

public class EmailSingleton
{
	/*----------------------------------------------------------------------------------------------------------------*/

	private EmailSingleton() {}

	/*----------------------------------------------------------------------------------------------------------------*/

	public static void sendMessage(String from, String to, String cc, String subject, String text) throws Exception
	{

		String host = "";
		String port = "";
		String mode = "";
		String user = "";
		String pass = "";

		if(host.isEmpty()
		   ||
		   port.isEmpty()
		   ||
		   mode.isEmpty()
		   ||
		   user.isEmpty()
		   ||
		   pass.isEmpty()
		) {
			return;
		}

		/*------------------------------------------------------------------------------------------------------------*/
		/* CREATE MAILER                                                                                              */
		/*------------------------------------------------------------------------------------------------------------*/

		MailerBuilder.MailerRegularBuilder mailerBuilder = MailerBuilder.withSMTPServer(host, Integer.parseInt(port), user, pass);

		switch(mode.trim())
		{
			case "0":
				mailerBuilder.withTransportStrategy(TransportStrategy.SMTP); // Sans cryptage
				break;

			case "1":
				mailerBuilder.withTransportStrategy(TransportStrategy.SMTPS); // Cryptage SSL
				break;

			case "2":
				mailerBuilder.withTransportStrategy(TransportStrategy.SMTP_TLS); // Cryptage TLS
				break;
		}

		/*------------------------------------------------------------------------------------------------------------*/

		Mailer mailer = mailerBuilder.buildMailer();

		/*------------------------------------------------------------------------------------------------------------*/
		/* CREATE EMAIL                                                                                               */
		/*------------------------------------------------------------------------------------------------------------*/

		EmailPopulatingBuilder emailBuilder = EmailBuilder.startingBlank();

		emailBuilder.from(from);

		to = to.trim();
		if(!to.isEmpty()) {
			emailBuilder.to(to.trim());
		}

		cc = cc.trim();
		if(!cc.isEmpty()) {
			emailBuilder.cc(cc.trim());
		}

		emailBuilder.withSubject(subject).withPlainText(text);

		/*------------------------------------------------------------------------------------------------------------*/

		Email email = emailBuilder.buildEmail();

		/*------------------------------------------------------------------------------------------------------------*/
		/* SEND EMAIL                                                                                                 */
		/*------------------------------------------------------------------------------------------------------------*/

		try
		{
			mailer.sendMail(email);
		}
		catch(RuntimeException e)
		{
			throw new Exception(e);
		}

		/*------------------------------------------------------------------------------------------------------------*/
	}

	/*----------------------------------------------------------------------------------------------------------------*/
}