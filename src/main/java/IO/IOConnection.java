package IO;

import java.io.IOException;

public abstract class IOConnection {
    //IO Constants
    String server = null;
    int port = 0;

    //SFTP/FTP/SSH/etc. consts
    String user = null, password = null;

    //IO Action (no need for upload, as we won't be uploading any files)
    abstract void downloadFile(String remotePath, String localPath);

    //Con&DC
    abstract void connect() throws IOException;
    abstract void disconnect();

    //Possibly useful methods
    String getServer() {return server;}
    int getPort() {return port;}
}
