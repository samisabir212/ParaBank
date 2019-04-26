Feature: A user must be able to make a deposit successfully

		As a user i want to make a deposit to make bank account

@DEPOSIT 
Scenario Outline: Successful Deposit

	In this scenario we are validating that a customer can make a
	successful deposit to their account


	Given new user is registered 
	And the account Balance should be 515.50 dollars
	When the customer Deposits 10000 dollars 
	Then the account Balance must be 10515.50 dollars
	And a new transaction should be Recorded "<Testname>"

	
	
	
	
	Examples:
	
	|Testname         	 |
	|Successful_Deposit  |
	
