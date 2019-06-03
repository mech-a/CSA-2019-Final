package IO;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class FTPGetter extends IOConnection implements DemoIOConstants {

    //FTP port
    private int port = DemoIOConstants.PORT;
    //Client
    private FTPClient ftp;

    public FTPGetter(String server, String user, String password) {
        this.server = server;
        this.user = user;
        this.password = password;
    }

    public FTPGetter(String server, int port, String user, String password) {
        this.server = server;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public void connect() throws IOException {
        //Initialize FTPClient
        ftp = new FTPClient();

        //Keep track of log messages if needed for debugging
        //ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        //Attempt connection and see if it is successful
        ftp.connect(server, port);
        int reply = ftp.getReplyCode();
        if(!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
        else {
            System.out.println("Success in connecting to FTP Server");
        }

        //Login and wait
        ftp.login(user, password);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }



    public void downloadFile(String remoteFilePath, String localFilePath) {
        try {
            FileOutputStream output = new FileOutputStream(localFilePath);
            ftp.retrieveFile(remoteFilePath, output);
            System.out.println("FTP File downloaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        if (ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already downloaded from FTP server
                // basically this case won't really ever occur. We can just shut down the program
            }
        }
    }

    public static void main(String[] args) {
        FTPGetter wget = new FTPGetter(SERVER, PORT, USER, PASSWORD);
        //System.out.println(LOCAL_PATH);

        try {
            wget.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner userIn = new Scanner(System.in);
        String input;

        System.out.println("Please enter the name of the file.");
        input = userIn.nextLine();

        while(!Arrays.asList(LEGAL_NAMES).contains(input)) {
            System.out.println("Error! Illegal file.");
            input = userIn.nextLine();
        }

        wget.downloadFile(input, LOCAL_PATH+input);
        wget.disconnect();
    }
}
