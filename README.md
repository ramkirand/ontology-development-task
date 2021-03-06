					Full Stack Project
	
	# Technologies used: Java 8,Spring Boot, Mongo DB, React,JWT for authentication & authorization

	Ontology Development Task
	Implementation details:
	The tool can be accessed locally at http://localhost:3000
 
 	Implemented Below Components:
  
  	1.Login Form.
  	2.Registration Form(registering a user).
  	3.Search information for an Ontology based on its Id.
  	4.View list of Ontologies meta data in a tabular form.
  
  	Best Practices followed:
  
  	1. Validation for the input fields. 
  	2. Added Swagger for the API’s
  	3. Added code to track the time taken for the API to fetch data.
  	4. Used caching mechanism to increase performance.
  	5. Took TDD approach.
  	6. Applied SOLID design principles in addition to OOPS.

	Assumptions made:
	The external service will be available all time, did not handled the resilience part in the code.
	Added unit test for the backend code but not for the UI.
	Added loggers in the code.
	Currently the CORS is set to “ * ” for everyone instead of specified users.
	Security for the API’ is not implemented.
	The code cannot handle different mongo dB configuration i.e master and slave.
	
	Run Spring Boot application

	We also need to add some rows into roles collection before assigning any role to User.
	Run following MongoDB insert statements:

	db.roles.insertMany([
   	{ name: "ROLE_USER" },
   	{ name: "ROLE_MODERATOR" },
   	{ name: "ROLE_ADMIN" },
	])

 


