import java.io.*;

public class Framing {

    static String readFile(String path) throws IOException {
        StringBuilder data = new StringBuilder();
        BufferedReader input = new BufferedReader(new FileReader(path));
        String str;
        while ((str = input.readLine()) != null) data.append(str);

        input.close();

        return data.toString();
    }

    static void writeFile(String path, String data) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(path));
        output.write(data);
        output.close();
    }

    public static void main(String[] args){

        String data;
        try {
            data = readFile("test.txt");
            Encoder encoder = new Encoder();
            String encoded = encoder.encode(data, 32);
            writeFile("W.txt", encoded);
            Decoder decoder = new Decoder();
            data = readFile("W-error.txt");
            String decoded = decoder.decode(data);
            writeFile("W-decoded.txt", decoded);
        } catch (IOException ex) {
            System.err.println("Cannot access file");
            return;
        }
    }

}