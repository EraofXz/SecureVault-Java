import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- SecureVault Java (CySA+ Informed) ---");

        // 1. Input Sanitization Demo (Mencegah Injection)
        System.out.print("Masukkan Username: ");
        String rawUsername = scanner.nextLine();
        String safeUsername = sanitizeInput(rawUsername);
        System.out.println("Sanitized Username: " + safeUsername);

        // 2. Password Hashing Demo (Prinsip Secure Storage)
        System.out.print("Masukkan Password untuk di-hash: ");
        String rawPassword = scanner.nextLine();
        String hashedPassword = hashPassword(rawPassword);
        System.out.println("SHA-256 Hashed Password: " + hashedPassword);

        System.out.println("\n[Status: Secure Operation Completed Successfully]");
        scanner.close();
    }

    /**
     * Mencegah serangan Injection/XSS dengan hanya membenarkan aksara alfanumerik.
     * Ini adalah amalan 'Defensive Coding' yang dipelajari dalam CySA+.
     */
    private static String sanitizeInput(String input) {
        if (input == null) return "";
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

    /**
     * Menukarkan teks biasa kepada hash SHA-256. 
     * Memastikan data sensitif tidak disimpan dalam bentuk teks nyata (Plain-text).
     */
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error: Algoritma hashing tidak dijumpai.";
        }
    }
}
