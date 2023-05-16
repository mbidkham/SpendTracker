Why this branch?
===========
First I needed to implement the code efficient and on time to meet the deadline, 
So I decided to develop it as simple as possible. But after completing test cases and
testing my scenarios to be curtained about functionality, I made this branch to change 
the architecture of my app into DDD. I think it takes time to develop it and DDD design might be
a group decision containing a process.So this branch is not ready and complete :)
It is just some load thinking.


What is SpendTracker? 
 ===========
This is a financial management application.You can add your categories based on your daily expenses. 
After creating a specific category, you can add today expenses of that category. 
Application has a report of your expenses, so when you spend too money on each category, the app will notice you that the expenses
exceeds the limit of each category you have made.
In addition to this, you can get a report of total expenses of a category during a specific category.

Quick start
===========
Please read [Project Description Reference](https://docs.google.com/document/d/1FLZiyAYD3nlbxGtNGi84zGMSBr3-oCSXcwPR-TeFyKU/edit#heading=h.hm40tzuc74uo) Personal Finance Side Projects part number 11.

A common workflow includes:

| Task                                  | Do                                                                                                                                                                                                                                                                |
|---------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Install Requirements (no docker use): |                                                                                                                                                                                                                                                                   |
| -  openjdk:17                         | ``$ sudo apt install openjdk-17-jdk``                                                                                                                                                                                                                             |
| -  maven:3.8.4-openjdk-17             | ``$ wget https://downloads.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz`` <br>  ``$ sudo tar -xf apache-maven-3.8.4-bin.tar.gz -C /opt`` <br> ``$ export M2_HOME=/opt/apache-maven-3.8.4`` <br> ``$ export PATH=${M2_HOME}/bin:${PATH}`` |
| Build application:                    | Go to the project root first,                                                                                                                                                                                                                                     |
| -  By docker                          | ``$ docker build -t spendtracker .   ``                                                                                                                                                                                                                           |
| -  without docker                     | ``$ ./mvnw clean install``                                                                                                                                                                                                                                        |
| Run app based on custom port:         |                                                                                                                                                                                                                                                                   |
| -  By docker                          | ``$ docker run -p <HOSTPOR>:<CONTAINER_PORT> spendtracker``                                                                                                                                                                                                       |
| -  without docker                     | edit application.properties or set APP_PORT in deployment app properties, exp: APP_PORT:9090 <br> ``$ ./mvnw spring-boot:run ``                                                                                                                                   |
| [Swagger](#Swagger)                   | please find it here: http://localhost:<APP_PORT>/swagger-ui.html                                                                                                                                                                                                  |
| Use app by [curl](#Curl)              | Firstly you have to login ang get token , <br> Then set Authorization header start with 'Bearer '                                                                                                                                                                 |

   


## Swagger
  
Check /auth/login page and enter valid user and password and after receiving token,
    open the Authorize tab and enter your received token without Bearer in the box:

![token.png](src%2Ftest%2Fresources%2Fimages%2Ftoken.png)

After clicking on Authorize button, you can use the services and all the requests will be using your token.

## Curl

Firstly you have to log in:
```bash
curl -X 'POST http://localhost:3000/auth/login' -H 'accept: */*'-H 'Content-Type: application/json' -d '{"userName": "user","password": "pass"}'
```
Then you can create new category for exp:
 ```bash
curl -X 'POST' 'http://localhost:3000/category/add'-H 'accept: */*' -H 'Authorization: Bearer toke' -H 'Content-Type: application/json' -d '{"name": "coffee","limit": 10000}'
```
