	# Technologies used: Java 8,Spring Boot Mongo DB, React,JWT for authentication & authorization

Ontology Development Task
Implementation details:
  
  The tool can be accessed locally at http://localhost:3000/view-ontology 
 

  Implemented validation for the input field Ontology Id. 
	Added swagger for the API’s
	Implemented the front end using React.
	Added two components.
	To search information on ontology by Id.
	To view all the ontologies meta data.
	Added code to track the time taken for the API to fetch data.
	Used caching mechanism to increase performance.

Assumptions made:
	The external service will be available all time, did not handled the resilience part in the code.
	Added unit test for the backend code but not for the UI.
	Added loggers in the code.
	Currently the CORS is set to “ * ” for everyone instead of any specified users.
	Security for the API’ is not implemented.
	The code cannot handle different mongo dB configuration i.e master and slave.

 


