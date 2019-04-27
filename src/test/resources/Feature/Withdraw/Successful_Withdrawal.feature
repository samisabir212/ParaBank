Feature: Withdraw

	As a customer, I want to withdraw funds from my account

@WITHDRAW @REGRESSION 
Scenario Outline: Withdraw from account when funds are available

	Given a customer has an account
	And the account balance is 415.50 dollars
	When the customer withdraws 115.50 dollars
	Then the account balance should be 300.00 dollars
	And a new transaction should be recorded "<Testname>"
	
	Examples:
	
	|Testname            |
	|Successful_Withdrawl|