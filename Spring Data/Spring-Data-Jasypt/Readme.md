Step by Step Points :- 

1st Approach
mvn jasypt:encrypt-value -Djasypt.encryptor.password=YOUR_SECRET_KEY -Djasypt.plugin.value=yourDatabaseUsername
mvn jasypt:encrypt-value -Djasypt.encryptor.password=YOUR_SECRET_KEY -Djasypt.plugin.value=yourDatabasePassword

Replace YOUR_SECRET_KEY with a strong encryption key of your choice.
Save this key securely, as it will be used to decrypt the values at runtime.
Replace yourDatabaseUsername and yourDatabasePassword with your actual database credentials.
The output will be encrypted values like ENC(EncryptedValueHere).

mvn jasypt:encrypt-value -Djasypt.encryptor.password=myKey -Djasypt.plugin.value=root
mvn jasypt:encrypt-value -Djasypt.encryptor.password=myKey -Djasypt.plugin.value=admin@123

Set the Encryption Key in Your Application
To enable Spring Boot to decrypt these properties at runtime, you need to provide the jasypt.encryptor.password as a runtime environment variable or VM option.

For example, in IntelliJ or your IDE, set the VM option:
-Djasypt.encryptor.password=YOUR_SECRET_KEY

Replace your plain-text username and password with the encrypted values in application.properties:
spring.datasource.username=ENC(EncryptedUsernameHere)
spring.datasource.password=ENC(EncryptedPasswordHere)


2nd Approach
In your application.properties file, add your sensitive values (like username and password) as plain text, but prefix them with DEC(...). 
This tells Jasypt that these values need to be encrypted.

spring.datasource.username=DEC(root)
spring.datasource.password=DEC(admin@123)

mvn jasypt:encrypt -Djasypt.encryptor.password=YOUR_SECRET_KEY -Djasypt.plugin.path="file:src/main/resources/application.properties"

After running this command:

Jasypt will replace DEC(...) entries in application.properties with their encrypted ENC(...) equivalents.
spring.datasource.username=ENC(H6ref33EZbH3gGFrnWSvLE5x6k7M/27PW0CJDkQWDzwtzQEFVh8vLbMzyqlJJZGc)
spring.datasource.password=ENC(NDyWE1tI/BV04B4Q/n2ydp0cDCwl+SVCjbUDhkKpNA9wJ42YAru/U3HWbrOLR7WC)

Just like in the manual approach, set the encryption key so that Spring Boot can decrypt the properties at runtime.

In your IDE, add the VM option:
--Djasypt.encryptor.password=YOUR_SECRET_KEY




Following the above steps :=> 

command: mvn jasypt:encrypt-value -Djasypt.encryptor.password=myKey -Djasypt.plugin.value=root
command: mvn jasypt:encrypt-value -Djasypt.encryptor.password=myKey -Djasypt.plugin.value=admin@123

Decrypt
command: mvn jasypt:decrypt-value -Djasypt.encryptor.password=myKey -Djasypt.plugin.value=cgEyF7vM65g4Pglq1vMejQxZNQhTfNwh5vOJG8DFASK2iGb3kHb99u8XbnHqnX7l

command: -Djasypt.encryptor.password=myKey
set this on Intellij Build and Run, It's the manual approach


2nd and best approach
spring.datasource.username=DEC(root)
spring.datasource.password=DEC(admin@123)

command: mvn jasypt:encrypt -Djasypt.encryptor.password=myKey -Djasypt.plugin.path="file:src/main/resources/application.properties"
It will automatically encrypt

spring.datasource.username=ENC(H6ref33EZbH3gGFrnWSvLE5x6k7M/27PW0CJDkQWDzwtzQEFVh8vLbMzyqlJJZGc)
spring.datasource.password=ENC(NDyWE1tI/BV04B4Q/n2ydp0cDCwl+SVCjbUDhkKpNA9wJ42YAru/U3HWbrOLR7WC)

or we can go to the path and execute for auto encryption
command: mvn jasypt:encrypt -Djasypt.encryptor.password=myKey

command: -Djasypt.encryptor.password=myKey
set this on Intellij Build and Run, It's the manual approach