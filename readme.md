#TodoList -> Spring boot - ReactJS - GraphQL 

An experimental project to learn how to use graphql with spring boot. <br/>
Using reactJS as frontend

## Installation and run

The project is using java 14 and nodeJS 12.16.x. Make sure it's installed on your machine.
<br/>
*Make sure to run the backend before the frontend*
###Backend
To run the backend, open a terminal and move into the project folder. Then, simply run 
```shell script
./gradlew bootRun
```
The server will run on the port 8080. Make sure it's available.
###Frontend
Open a new terminal and move into the `todo-ui` folder
<br/>
Install packages by running
```shell script
   npm install
```
Once installed, run the frontend with the command
```shell script
    npm start
```
The frontend will open itself on the port 3000 and directly communicate with the backend.

##TODO
(Most of the work on the backend is already done)
- Finish displaying information in each list item
- UI to create a new todo item
- Removing a todo item
- Mark an item as done
- Filter by category
- Edit an item (API to do)
- Assign a category to an item

##License
MIT
