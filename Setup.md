# Gradle Build

Gradle option in the ide helps generate the build
else 
````gradle bootjar```` 

generates ````highlights.jar```` under build/libs

# Swagger
 
Endpoints available at http://localhost:8080/swagger-ui.html

# Mongo DB

- Install any version of Mongodb
- Create a database `highlights`
- Execute the following script to load data to the `contents` collection
	```
	.\mongo highlights D:\work\highlights\db\contents.js
	```