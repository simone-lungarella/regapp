# Regapp

Application that gives an overview of how a Registrar operates to manage all the operations that a Registrant requires. 
It gives the possibility to look at XML files that are used to send requests and also gives the possibility to look at XML received as a response. 
The Registro .it side is simulated with a persistence layer to save all the entities needed to make the process work.

Is being used Primefaces in front end on a JSF-based web app and Spring and basic design patterns to handle the backend.
The server is an InnoDB with MySQL running on localhost. The dependencies are handled with Maven.

Use the dnsentities.sql file to set up the database and run the queries.
