import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // Check if correct number of arguments is provided
        if (args.length < 2) {
            System.err.println("Usage: java Client <server_address> <server_port>");
            return;
        }

        // Extract server address and port from command-line arguments
        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]); // Parse the port from the command line

        try (Socket socket = new Socket(serverAddress, serverPort);
             FileInputStream fileInputStream = new FileInputStream("C:\\Users\\dhima\\eclipse-workspace\\ServerClientConnection\\example.txt");
             OutputStream outputStream = socket.getOutputStream()) {

            // Successful connection message
            System.out.println("The client has successfully connected to the server");

            // Read file and transfer its content to the server
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // File transfer completion message
            System.out.println("File transfer has been successfully completed from client to the server.");

            // Send completion message to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("File transfer is completed");

        } catch (IOException e) {
            // Print any exceptions that occur during the process
            e.printStackTrace();
        }
    }
}
