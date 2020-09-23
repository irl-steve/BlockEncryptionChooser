# BlockEncryptionChooser
This is a Java Program that allows users to encrypt and decrypt files, choosing between different block ciphers, modes of operation and padding.

# Technical 
This program uses the Java Cipher object (https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html) to encrypt and decrypt files. The Cipher object takes in a string of the form "algorithm", or "algorithm/mode/padding". There are a set amount of valid strings that can be passed to the Cipher object:

* AES/CBC/NoPadding
* AES/CBC/PKCS5Padding 
* AES/ECB/NoPadding
* AES/ECB/PKCS5Padding 
* DES/CBC/NoPadding
* DES/CBC/PKCS5Padding 
* DES/ECB/NoPadding
* DES/ECB/PKCS5Padding
* DESede/CBC/NoPadding
* DESede/CBC/PKCS5Padding 
* DESede/ECB/NoPadding
* DESede/ECB/PKCS5Padding
* RSA/ECB/PKCS1Padding
* RSA/ECB/OAEPWithSHA-1AndMGF1Padding
* RSA/ECB/OAEPWithSHA-256AndMGF1Padding

In the program the string passed to the Cipher object is formed from the concatenation of three substrings, one for each the algorithm, mode, and padding. The substrings are chosen based on user input and is controlled by a switch statement. To further ensure that only valid strings are sent, the options presented to the user for mode and padding are changed when RSA is selected.

# For users
Upon starting the program the user must select the algorithm used. The message will be shown: 
"Welcome please choose the algorithm to use:
1. Advanced Encryption Standard (AES)
2. Data Encryption Standard (DES)
3. Triple DES (DESede)
4. Rivest–Shamir–Adleman (RSA)" 

To choose, input a number from 1 - 4 depending on the choice. If an invalid number is inputted, then the following message will be shown:
Please enter a valid choice

After the algorithm is chosen successfully the mode must be selected. If the RSA algorithm was picked then the mode is set to "ECB". 
The following message is shown, and the mode is selected the same way as the algorithm above.

"Please enter your choice of mode
1. CBC 
2. ECB"

After mode decision finally the padding is picked, for all algorithms except RSA the options are presented by the message:
"Please enter your choice of padding
1. NoPadding
2. PKCS5Padding"

For RSA it is: 
"Please enter your choice of padding
1. PKCS1Padding
2. OAEPWithSHA-1AndMGF1Padding
3. OAEPWithSHA-256AndMGF1Padding"

Provided no errors occur, the message "DONE" will be printed to the console, and the encrypted file will be available to view.
