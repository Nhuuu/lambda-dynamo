# Lab: Lambda with Dynamo

## Overview

Let's use Lambda to handle database changes in real time!

**API Gateway Link:**
https://taskmaster-frontend-nhu.s3-us-west-2.amazonaws.com/lambda-dynamo.zip
 

## Feature Tasks

### Java Insert/Update Functionality

- Create a lambda function, in Java, that can add a record to your Taskmaster table.
- Run this only in "Test" mode
- It should receive the same object that your API was handling earlier.
- Repeat for "PUT" / update functionality
- Do NOT handle images at this point.
- You might want to keep this [warm](https://read.acloud.guru/how-to-keep-your-lambda-functions-warm-9d7e1aa6e2f0)...



## Issues

- Ran into error: "No public method found..."
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
- Joachen Busch
- Sapana Poudel
- Trevor Dobson
- Marisha Hoza
- Melfi Perez