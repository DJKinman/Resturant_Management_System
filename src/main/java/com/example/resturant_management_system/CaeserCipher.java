package com.example.resturant_management_system;

public class CaeserCipher {
    // Method to encrypt the text
    public static String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                char base = (Character.isUpperCase(c)) ? 'A' : 'a';
                c = (char) ((c - base + key) % 26 + base);
            }

            encryptedText.append(c);
        }

        return encryptedText.toString();
    }

    // Method to decrypt the text
    public static String decrypt(String text, int key) {
        return encrypt(text, 26 - key);  // Decryption is just encryption with the inverse key
    }

}
