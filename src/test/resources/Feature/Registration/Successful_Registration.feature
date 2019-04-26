Feature: A user can register to create a new account

		we are validatig a successful registration by a customer.
		
		As a user i want to create bank account with parabank
		
		
@REGISTRATION @REGRESSION
Scenario Outline: Successful Registration

	in this scenario we are validating that a user can create his/her new account
	once the registration is submitted the user will be automatically logged in to the welcome page
	the user then logs out and logs back in to make sure the user can login again
	then we validate the new account details in the overview page after loggin in


	Given user fills out the registration form
	And clicks register
	Then user should be logged in after submitting the registration form	
	Then user logs out and logs back in
	Then the logged in overviw page should appear and validate account over view details "<Testname>"
	
	
	
	Examples:
	
	|Testname 				  |
	|Successful_Registration  |
	
