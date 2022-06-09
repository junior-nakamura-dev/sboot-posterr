
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


