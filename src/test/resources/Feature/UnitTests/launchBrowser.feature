Feature: launch in different environments

these are scenarios to make the framework launch in different config


@local
Scenario:

	Then launch locally
	

@AWS
Scenario:

	Then launch AWS cloud
	
	
@BrowserStack
Scenario:

	Then launch browserStack
	

@SauceLabs
Scenario:

	Then launch SauceLabs
	

	
	