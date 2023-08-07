# TaskTracker

**Objective:**
Task Tracker API as submission for an assignment towards EPay Later

**Requirements**:
Intellij Idea, Java 17, Postgresql 

**Maven Dependencies** :
Starter Web, Lombok, Spring starter JPA, Postgresql, Logging.

**How to get started:** 
1. Export the repo into your preferred folder. 
2. Open the project in Intellij.
3. Have Postgresql installed on your system and create a connection, database and table tasks(Columns named id, description, due_date) required.
4. The database has been configured in the Application.properties file with Username: postgres, Password: root make sure to match all fields.
5. Run the Application.

**How to use the endpoints**
1. POST /tasks: Create a new task.
2. GET /tasks/{id}: Retrieve a task by its ID.
3. GET /tasks: Retrieve a list of all tasks.
4. PUT /tasks/{id}: Update an existing task by its ID.
5. DELETE /tasks/{id}: Delete a task by its ID.

**Note:** 
This project does not contain any sort of Unit or Integration Test being done on it. 
