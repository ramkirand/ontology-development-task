                                        Ontology Development Task

Implementation details:
The tool can be accessed locally at http://localhost:3000/view-ontology 
 
1. Implemented validation for the input field Ontology Id. Added swagger for the API’s
2. Implemented the front end using React.
3.	Added two components.
4.	To search information on ontology by Id.
5. 	To view all the ontologies data.
6.	Added code to track the time taken for the API to fetch data.
7.	Used caching mechanism to increase performance.

Assumptions made:
1.	The external service will be available all time, did not handled the resilience part in the code.
2.	Added unit test for the backend code but not for the UI.
3	Added loggers in the code.
4.	Currently the CORS is set to * for everyone instead of any specified users.
5.	Security for the API’ is not implemented.
6.	The code cannot handle different mongo dB configuration i.e master and slave.

