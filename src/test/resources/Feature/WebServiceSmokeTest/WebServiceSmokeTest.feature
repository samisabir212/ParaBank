Feature: Execute All WebServices 


@SMOKEWEBSERVICES @REGRESSION 
Scenario Outline: Withdraw from account when funds are available

	Given We create a new account
	Then call Login service
	Then GetAccount service to get customerID
	Then GetCustomer service to validate Customer info
	Then GetAccounts service to get account info
	Then UpdateAccount service to update account info
	Then Deposit 1000 dollars to the first account
	Then GetAccount to validate the deposit amount
	Then GetTransactions to get deposit transaction ID
	Then GetTransaction to validate the deposit transaction and deposit amount
	Then CreateAccount to create a new Checkings Account
	Then Call GetAccounts to validate the new Checkings Account was created
	Then Call GetAccount using the new Checkings Account ID to validate the account
	Then Withdraw 200 from the new Checkings account
	Then GetTransactions to look for the withdraw transaction
	Then Call GetTransaction using the withdraw transaction ID to validate the transaction
	Then Transfer 1000 from the old Checkings account to the new Checkings account
	Then GetTransactions to look for the transfer transaction
	Then call GetTransaction using the transfer transaction id to calidate the transaction
	
	
	
	
	Examples:
	
	|Testname            |
	|Successful_SmokeTest|