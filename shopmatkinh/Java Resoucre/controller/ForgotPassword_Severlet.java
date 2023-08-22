package controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Servlet implementation class ForgotPassword_Severlet
 */
@WebServlet("/forgotpassword")
public class ForgotPassword_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		RequestDispatcher dispatcher = null;
		int otpValue = 0;
		HttpSession mySession = request.getSession();
		DAO dao = new DAO();
		if(dao.checkAccountExist(email) != null) {
				if(email != null || email.equals("")) {
					Random ran = new Random();
					otpValue = ran.nextInt(12556050);
					String to = email;
					
					Properties props =  new Properties();
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.socketFactory.port", "465");
					props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.port","465");
					Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("nguyenddqui@gmail.com","fkvareyarfzsfggi");
						}
					});
					try {
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(email));
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
						message.setSubject("OTP CODE");
						message.setText("Your OTP is: "+ otpValue);
						Transport.send(message);
						System.out.println("Send message sucessfully");
						
					}catch(MessagingException e) {
						throw new RuntimeException(e);
					}
					dispatcher = request.getRequestDispatcher("user-otp.jsp");
					request.setAttribute("message", "OTP is sent to your email !");
					mySession.setAttribute("email", email);
					mySession.setAttribute("otp", otpValue);
					dispatcher.forward(request, response);
				}
			}else {
			request.setAttribute("message", "Your email is invalid !");
			dispatcher = request.getRequestDispatcher("forgot-password.jsp");
			dispatcher.forward(request, response);
		}
}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
