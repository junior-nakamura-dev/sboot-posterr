
The API is configured on userId = 1 perspective simulating the request when an user is logged in the system 
and is possible to get the userId on SecurityContext.


**Script to start**

**Run stand-alone application**

1 - Run "docker build -t sboot-posterr ." on root directory

2 - Run "docker tag sboot-posterr:latest sboot-posterr:staging" on root directory

3 - Go to /infrastructure/staging and run "docker-compose up -d"

4 - Type on your browser http://localhost:8080/swagger-ui/

**Run for development environment**

1 - Go to /infrastructure/local and run "docker-compose up -d" for up the postgres db

2 - Run on your IDE with java env opts "-Dspring.profiles.active=default"


**Planning**
I think the only thing that is necessary is add a userId from the original post in post_entity, and create a new query to retrieve all posts with postOriginalId not null and change the existing query to return posts with postOriginalId = null.
After that create a new use case called GetRepostingPostUseCase for example, create a new method getRepostingPost in PostPort, implement the GetRepostingPostUseCase in service to use GetRepostingUseCase, implement the new method in PostPort in PostDBAdapter,
create a new method in PostHandler to use the GetRepostingPostUseCase and create a new get endpoint.

** Critique **

- Reflect on this project, and write what you would improve if you had more time.
- Write about scaling. If this project were to grow and have many users and posts, which parts do you think would fail first? In a real-life situation, what steps would you take to scale this product? What other types of technology and infrastructure might you need to use?
- **Be thorough!**

Actually with high use of the service probably database would be a failure point, because is blocking I/O. Actually I´m  using too much to control some post information.
If a had more time I wanna to split the services one for posts (sboot-post) and another for users (sboot-user). Probably in sboot-post I use a noSQL database to save the posts and use a Webflux for this service to improve the time response and use an asyncronous flow with a non blocking databse like mongoDB.
I add a BFF (Backend for frontend) service to composite the information to return. Other important improvement is add a API Gateway to expose on network with a service registry to scaling. I would use kubernetes or apache mesos to container orchestration and I will start a new pod after 70% CPU usage for example.
I use a GCP cloud because I have more familiarity. 


