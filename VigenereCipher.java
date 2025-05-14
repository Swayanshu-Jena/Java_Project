import java.util.Scanner;

public class VigenereCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the text to encrypt: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter the key: ");
        String customKey = scanner.nextLine();
        
        System.out.println("\nEncrypted text: " + text);
        System.out.println("Key: " + customKey);
        
        String decryption = decrypt(text, customKey);
        System.out.println("\nDecrypted text: " + decryption + "\n");
        
        scanner.close();
    }

    public static String vigenere(String message, String key, int direction) {
        int keyIndex = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder finalMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);

            // Append any non-letter character to the message
            if (!Character.isLetter(c)) {
                finalMessage.append(c);
            } else {
                // Find the right key character to encode/decode
                char keyChar = key.charAt(keyIndex % key.length());
                keyIndex++;

                // Define the offset and the encrypted/decrypted letter
                int offset = alphabet.indexOf(Character.toLowerCase(keyChar));
                int index = alphabet.indexOf(Character.toLowerCase(c));
                int newIndex = (index + offset * direction) % alphabet.length();
                if (newIndex < 0) {
                    newIndex += alphabet.length(); // handle negative indices for decryption
                }
                finalMessage.append(alphabet.charAt(newIndex));
            }
        }

        return finalMessage.toString();
    }

    public static String encrypt(String message, String key) {
        return vigenere(message, key, 1);
    }

    public static String decrypt(String message, String key) {
        return vigenere(message, key, -1);
    }
}