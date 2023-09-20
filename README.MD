## HTP Vault

### This is a simple project on how to use spring cloud vault (Java based)
### Hashicorp Vault is a tool for securely accessing secrets

### Author: Muhd Zabidi Wahid
### Date: 29/10/2021

#### Steps to run this project

#### Prequisite
1. JDK 1.8
2. Maven 
3. Postgre

#### Steps

1. Run the sql script from SpringCloudVaultSample/script/postgre.sql in your postgre instance.

2. Edit configuration in SpringCloudVaultSample/src/main/resources/bootstrap.properties
   - Server Configuration
   - Spring Cloud Configuration
   - Database Configuration

3. Go to project directory cd ../SpringCloudVaultSample

4. Build the project using mvn clean install or mvn clean package

5. Run the project as spring boot app using mvn spring-boot:run

6. Your project should be running now on host:port that have been set at Server Configuration   

7. You may know test this project REST API (Refer to APIDoc)

## Credit
Hashicorp

## References
https://github.com/hashicorp/vault
https://learn.hashicorp.com/tutorials/vault/eaas-spring-demo?in=vault/app-integration
https://www.vaultproject.io/docs



