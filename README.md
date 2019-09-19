# Lab: Lambda with Dynamo

## Overview

Let's use Lambda to handle database changes in real time!

**API Gateway Link:** https://mb58t93et1.execute-api.us-west-2.amazonaws.com/dev

## Feature Tasks
```
Migrate the functionality of your Create, Update, and Delete methods in your original Taskmaster Java application to their matching Lambda functions.
You may use any language to create these functions!
These endpoints should work ONLY with JSON data or URL Params
Taskmaster functionality must remain the same
With the exception of History (do not implement)
Refactor your react application to send a JSON object to the server instead of raw form data
```

### Java Insert/Update Functionality

* Create a new (empty) Lambda function for each route in Taskmaster
* Get all Tasks
* Get tasks for a user
* Create New Task
* Delete Task
* Update Task State
* Change Task Assignee
* Create a new API using AWS API Gateway
* Create a new resource /tasks
* Create a new Resource + Method to match the taskmaster functionality

For each resource, point them at the proper Lambda function
* GET /tasks
* GET /tasks/{user}
* POST /tasks
* PUT /tasks/{id}/state
* PUT /tasks/{id}/assign/{assignee}


## Issues

- Trouble updating using the tests, had to pass in full Task object.

## Credits and contributions

- Matt Stuhring
- Nhu Trinh
- @Bomibear
- Travis Cox
- Peter Lee
- Padmapriya Ganapathi
- Renee Messick
- Jack Kinne
- Jane Hur