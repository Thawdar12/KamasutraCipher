import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class kamasutra {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String KEY_FILE = "keyfile.txt";

    private static final Map<Character, Character> encryptionKey = new HashMap<>();
    private static final Map<Character, Character> decryptionKey = new HashMap<>();

    // shuffle the array
    private static void shuffleArray(char[] array) {
        Random rand = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);

            char temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }   
    
    // initializing the key
    private static void initializingKey(String key) {
        for (int i = 0; i < ALPHABET.length(); i++) {
            char original = ALPHABET.charAt(i);
            char shuffled = key.charAt(i);
            encryptionKey.put(original, shuffled);
            decryptionKey.put(shuffled, original);
        }
    }

    // generating key file
    public static void generateKeyFile() {
        File keyFile = new File(KEY_FILE);

        if (!keyFile.exists()) {
            char[] shuffledAlphabet = ALPHABET.toCharArray();
            shuffleArray(shuffledAlphabet);

            try (PrintWriter writer = new PrintWriter(new FileWriter(KEY_FILE))) {
                writer.println(shuffledAlphabet);
                initializingKey(new String(shuffledAlphabet));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (Scanner keyScanner = new Scanner(new File(KEY_FILE))) {
                String key = keyScanner.nextLine();
                initializingKey(key);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // encryption
    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);

            if (Character.toLowerCase(c) == 'f') {
                // Check if there is a next character
                if (i + 1 < plaintext.length()) {
                    char nextChar = plaintext.charAt(i + 1);
                    ciphertext.append(c).append(nextChar);
                    i++; // Increment i to skip the next character since it has already been processed
                } else {
                    // If 'f' is the last character, append it as is
                    ciphertext.append(c);
                }
            } else if (Character.isLetter(c)) {
                ciphertext.append(encryptionKey.getOrDefault(Character.toLowerCase(c), c));
            } else {
                ciphertext.append(c);
            }
        }

        return ciphertext.toString();
    }

    // decryption
    public static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);

            if (Character.toLowerCase(c) == 'f') {
                // Check if there is a next character
                if (i + 1 < ciphertext.length()) {
                    char nextChar = ciphertext.charAt(i + 1);
                    plaintext.append(c).append(nextChar);
                    i++; // Increment i to skip the next character since it has already been processed
                } else {
                    // If 'f' is the last character, append it as is
                    plaintext.append(c);
                }
            } else if (Character.isLetter(c)) {
                plaintext.append(decryptionKey.getOrDefault(Character.toLowerCase(c), c));
            } else {
                plaintext.append(c);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        generateKeyFile();
        
        if (args.length > 0) {
            switch (args[0]) {
                case "-k":
                    System.out.println("Key file generated: " + KEY_FILE);
                    break;
                case "-e":
                    if (args.length == 4) {
                        String plaintextFile = args[2];
                        String ciphertextFile = args[3];

                        try {
                            Scanner plaintextScanner = new Scanner(new File(plaintextFile));
                            PrintWriter ciphertextWriter = new PrintWriter(new FileWriter(ciphertextFile));
                            
                            while (plaintextScanner.hasNextLine()) {
                                String line = plaintextScanner.nextLine();
                                String encryptedLine = encrypt(line);
                                ciphertextWriter.println(encryptedLine);
                            }

                            plaintextScanner.close();
                            ciphertextWriter.close();

                            System.out.println("Encryption complete. \nCiphertext saved to: " + ciphertextFile);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Usage: kamasutra -e <keyfile.txt> <plaintext.txt> <ciphertext.txt>");
                    }
                    break;

                case "-d":
                    if (args.length == 4) {
                        String ciphertextFile = args[2];
                        String plaintextFile = args[3];

                        try {
                            Scanner ciphertextScanner = new Scanner(new File(ciphertextFile));
                            PrintWriter plaintextWriter = new PrintWriter(new FileWriter(plaintextFile));

                            while (ciphertextScanner.hasNextLine()) {
                                String line = ciphertextScanner.nextLine();
                                String decryptedLine = decrypt(line);
                                plaintextWriter.println(decryptedLine);
                            }

                            ciphertextScanner.close();
                            plaintextWriter.close();

                            System.out.println("Decryption complete. Plaintext saved to: " + plaintextFile);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("Usage: kamasutra -d <keyfile.txt> <ciphertext.txt> <plaintext.txt>");
                    }
                    break;
                default:
                    System.out.println("Invalid command. Use -k, -e, or -d.");
            }
        } else {
            System.out.println("No command specified. Use -k, -e, or -d.");
        }
    }
}


// Kamasutra.java

// import java.io.*;

// public class kamasutra {

//     public static void main(String[] args) throws IOException {
//         if (args.length < 2) {
//             System.out.println("Usage: java kamasutra <operation> <keyfile.txt> <inputfile.txt> <outputfile.txt>");
//             System.exit(1);
//         }

//         String operation = args[0];
//         String keyfile = args[1];
//         String inputfile = args[2];
//         String outputfile = args[3];

//         String key = loadKey(keyfile);

//         try {
//             String text = loadText(inputfile);

//             if (operation.equals("k")) {
//                 generateKeyPair(keyfile);
//             } else if (operation.equals("e")) {
//                 String ciphertext = encryptDecrypt(text, key, true);
//                 writeOutput(outputfile, ciphertext);
//             } else if (operation.equals("d")) {
//                 String plaintext = encryptDecrypt(text, key, false);
//                 writeOutput(outputfile, plaintext);
//             } else {
//                 System.out.println("Invalid operation. Use 'k' for key generation, 'e' for encryption, or 'd' for decryption.");
//                 System.exit(1);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//             System.exit(1);
//         }
//     }

//     private static String loadKey(String keyfile) throws IOException {
//         try (BufferedReader br = new BufferedReader(new FileReader(keyfile))) {
//             return br.readLine();
//         }
//     }

//     private static String loadText(String inputfile) throws IOException {
//         try (BufferedReader br = new BufferedReader(new FileReader(inputfile))) {
//             StringBuilder sb = new StringBuilder();
//             String line;
//             while ((line = br.readLine()) != null) {
//                 sb.append(line).append("\n");
//             }
//             return sb.toString().trim();
//         }
//     }

//     private static void generateKeyPair(String keyfile) throws IOException {
//         String alphabet = "abcdefghijklmnopqrstuvwxyz";
//         writeOutput(keyfile, alphabet);
//     }

//     private static String encryptDecrypt(String text, String key, boolean encrypt) {
//         StringBuilder result = new StringBuilder();
//         for (char ch : text.toCharArray()) {
//             if (ch == 'f') {
//                 result.append('f');
//             } else {
//                 int index = key.indexOf(ch);
//                 int pairedIndex = (index + key.length() / 2) % key.length();
//                 result.append(encrypt ? key.charAt(pairedIndex) : key.charAt(index));
//             }
//         }
//         return result.toString();
//     }

//     private static void writeOutput(String outputfile, String content) throws IOException {
//         try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputfile))) {
//             bw.write(content);
//         }
//     }
// }
