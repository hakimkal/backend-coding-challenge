README
====

IMPORTANT
====
To avoid unconcious bias, we aim to have your submission reviewed anonymously by one of our engineering team. Please try and avoid adding personal details to this document such as your name, or using pronouns that might indicate your gender.

Notes:
This is a Springboot application.  
The frontend(angular app) of this application have been extracted and placed on the thymeleaf path.
The IDE used is Intellij IDEA.



Requirements: Maven and Mysql.

To setup:

1. Open your MySQL and create a database  'expensify_db'
2. Edit the application.yaml and update mysql connnection parameters
3. to compile  a .jar file(expensify.jar) run 'mvn clean package' from the solution directory
4. to run the application run the command 'java -jar target/expensify.jar' from the solution directory.
5. Go to http://localhost:8080 on your browser



User Story 3:

You may enter a Euro amount e.g (22 EUR) an automatic conversion will be done using the Yahoo YQL API with a fallback to openexchange.org


User Story 4:

VAT is calculated on front end. note that the conversion to GBP is done at the backend hence VAT calculation is in the default currency entered by the user.
e.g 84.56 Eur has a [20%]  VAT of 14.09 EUR but will only be converted to GBP at the backend.

