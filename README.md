# Kamasutra Cipher Implementation

## Overview
This project implements the Kama Sutra cipher in Java. The Kama Sutra cipher is a type of substitution cipher where each letter in the alphabet is paired with another letter. However, if the letter ‘f’ is encountered, it is not substituted but is left as is. This "flipped" version of the cipher makes it more unique and complex. The program can generate a key, encrypt plaintext, and decrypt ciphertext using the generated key.

## What I Learned
- **Substitution Cipher Basics**: Learned how to implement a substitution cipher by pairing letters from the alphabet, while taking into account special cases (e.g., the letter 'f' remains unchanged).
- **Randomization**: Gained experience with randomization techniques to shuffle the alphabet and create a substitution key for encryption and decryption.
- **File Handling**: Learned how to read from and write to files in Java, enabling the program to handle external text files for encryption and decryption.
- **Command-Line Arguments**: Implemented the program to accept command-line arguments, allowing users to specify options for generating keys, encrypting, and decrypting files.
- **Encryption and Decryption Logic**: Understood how to implement encryption and decryption by using key mappings and handling edge cases where characters like 'f' are treated differently.
- **Java Collections**: Learned how to use the `Map` interface in Java to store the encryption and decryption mappings between characters.
- **Program Structure**: Gained insight into organizing a Java program with multiple methods, making it modular and easier to maintain.
- **Key Management**: Improved understanding of how to manage and generate cryptographic keys securely, ensuring the correct pairing of characters.

## Program Features
- **Generate a Key**: The program can generate a random key that pairs each letter of the alphabet with another letter.
- **Encrypt Text**: Using the generated key, the program can encrypt plaintext by substituting each letter with its paired counterpart from the key.
- **Decrypt Text**: The program can also decrypt ciphertext back to plaintext using the same key.
- **Keyfile**: The key is stored in a file (`keyfile.txt`) and is used for both encryption and decryption.
  
