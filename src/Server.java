import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        // Check if correct number of arguments is provided
        if (args.length < 1) {
            System.err.println("Please provide a port number.");
            return;
        }

        // Extract the port number from command-line arguments
        int port = Integer.parseInt(args[0]); // Port specified as a command-line argument

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            // Server started message
            System.out.println("Server is listening on port " + port);

            while (true) {
                // Accept client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client is connected successfully");

                // Set up input and output streams for file transfer
                InputStream inputStream = clientSocket.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");

                // Read data from client and save it to a file
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                // File transfer completion message
                System.out.println("File transfer is completed successfully");

                // Close streams and the client socket
                fileOutputStream.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            // Print any exceptions that occur during the process
            e.printStackTrace();
        }
    }
}
