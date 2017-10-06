/**
*	TCP Client Program
*	Connects to a TCP Server
*	Receives a line of input from the keyboard and sends it to the server
*	Receives a response from the server and displays it.
*
*	@author: Maika Fujii
*   ID: 1935412
*   Email: fujii108@mail.chapman.edu
*   Course: CPSC-353 - 9:00-9:50
*   Assignment #3
@	  version: 2.1
*/

import java.io.*;
import java.net.*;
class Email {

    public static void main(String argv[]) throws Exception
    {
        String fromEmail;
        String toEmail;
        String subject;
        String modifiedSentence = "";
        String content = "";
        String addContent = "";
	      String welcomeMessage;


        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        //Get information for email
        System.out.println("Enter the sender email address receiver address, and subject: \n");
        //From email
        System.out.print("FROM: ");
        fromEmail = inFromUser.readLine();
        //To email
        System.out.print("TO: ");
        toEmail = inFromUser.readLine();
        //subject of email
        System.out.print("SUBJECT: ");
        subject = inFromUser.readLine();

        //Record body of email with while loop
        System.out.println("Enter 'done' on its own line when done typing. \nContent: ");
        int check = 1;
        while(check != 0){
            content = content + addContent + " ";
            addContent = inFromUser.readLine();
            if(addContent.equals("done") || addContent.equals("Done")){
                check = 0;
            }
        }

        Socket clientSocket = null;
    //TCP Connection
		try
		{
			clientSocket = new Socket("smtp.chapman.edu", 25);
		}
		catch(Exception e)
		{
			System.out.println("Failed to open socket connection");
			System.exit(0);
		}

        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
        BufferedReader inFromServer =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //welcome message
        welcomeMessage = inFromServer.readLine();
        System.out.println("FROM SERVER:" + welcomeMessage);

        //acknowledge server
        outToServer.println("HELO icd.chapman.edu");
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);

        //SMTP - send mail and print to console
        System.out.println("Send to Server MAIL FROM: " + fromEmail);
        outToServer.println("MAIL FROM: "+fromEmail);
        modifiedSentence = inFromServer.readLine();
        System.out.println("MAIL FROM:"+ modifiedSentence);

        System.out.println("Send to Server RCPT TO: "+toEmail);
        outToServer.println("RCPT TO: "+toEmail);
        modifiedSentence = inFromServer.readLine();
        System.out.println("RCPT TO: "+modifiedSentence);

        //DATA
        System.out.println("Send to Server DATA");
        outToServer.println("DATA");
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);

        System.out.println("Send to Server SUBJECT: "+subject);
        outToServer.println("SUBJECT: "+subject);
        System.out.println("Send to Server Content: \n"+content);
        outToServer.println(content);
        System.out.println(".");
        outToServer.println(".");
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: "+modifiedSentence);

        //indicate the process has completed
        System.out.println("QUIT");
        outToServer.println("QUIT");
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: "+modifiedSentence);




        clientSocket.close();

    }
}
