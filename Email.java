/**
*	TCP Client Program
*	Connects to a TCP Server
*	Receives a line of input from the keyboard and sends it to the server
*	Receives a response from the server and displays it.
*
*	@author: Maika Fujii
@	version: 2.1
*/

import java.io.*;
import java.net.*;
class Email {

    public static void main(String argv[]) throws Exception
    {
        String sentence;
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
        System.out.print("FROM: ");
        fromEmail = inFromUser.readLine();
        System.out.print("TO: ");
        toEmail = inFromUser.readLine();
        System.out.print("SUBJECT: ");
        subject = inFromUser.readLine();

        System.out.println("Enter 'done' when done typing. \nContent: \n");
        int check = 1;
        while(check != 0){
            content = content + addContent;
            addContent = inFromUser.readLine();
            if(addContent.equals("done") || addContent.equals("Done")){
                check = 0;
            }
        }

        Socket clientSocket = null;

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
        outToServer.println("HELO smtp.chapman.edu");
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);

        //TO and FROM Email Addresses
        outToServer.println("FROM: "+fromEmail);
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM: "+ modifiedSentence);

        outToServer.println("TO: "+toEmail);
        modifiedSentence = inFromServer.readLine();
        System.out.println("TO: "+modifiedSentence);

        //DATA
        outToServer.println("DATA");
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);

        outToServer.println("SUBJECT: "+subject);
        modifiedSentence =inFromServer.readLine();
        System.out.println("SUBJECT: "+modifiedSentence);

        outToServer.println(content);
        outToServer.println(".");
        System.out.println(content);
        System.out.println(".");
        
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: ");







        clientSocket.close();

    }
}
