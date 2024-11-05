command: mvn jasypt:encrypt-value -Djasypt.encryptor.password=admin@123 -Djasypt.plugin.value=password

command: mvn jasypt:decrypt-value -Djasypt.encryptor.password=admin@123 -Djasypt.plugin.value=cgEyF7vM65g4Pglq1vMejQxZNQhTfNwh5vOJG8DFASK2iGb3kHb99u8XbnHqnX7l

command: -Djasypt.encryptor.password=admin@123 
set this on Intellij Build and Run, It's the manual approach 


2nd and best approach
spring.datasource.username=DEC(root)
spring.datasource.password=DEC(admin@123)

command: mvn jasypt:encrypt -Djasypt.encryptor.password=admin@123 -Djasypt.plugin.path="file:src/main/resources/application.properties"
It will automatically encrypt 

spring.datasource.username=ENC(H6ref33EZbH3gGFrnWSvLE5x6k7M/27PW0CJDkQWDzwtzQEFVh8vLbMzyqlJJZGc)
spring.datasource.password=ENC(NDyWE1tI/BV04B4Q/n2ydp0cDCwl+SVCjbUDhkKpNA9wJ42YAru/U3HWbrOLR7WC)

or we can go to the path and execute for auto encryption 
command: mvn jasypt:encrypt -Djasypt.encryptor.password=admin@123






