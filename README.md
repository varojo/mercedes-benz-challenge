# Mercedes Benz - Swapi Consumer

## Introduction

### Objective
Implement and deploy a microservice that interacts with the SWAPI - The Star Wars API demonstrating
proficiency in software development, containerization, orchestration, and performance optimization.
Please provide your completed solution as a public GitHub repository.

### Technical Requirements
• Implement the microservice in one of the following languages: Golang, Java, or Python.
• The microservice should consume the people endpoint from the Star Wars API.
• Fetch the data from the people endpoint.
• Sort the fetched data in ascending order based on the name attribute.
• Create an endpoint in your microservice that returns the sorted data.
• Include error handling and logging.

## Authors and acknowledgment
- DevOps Engineer: [valentin.rojo@luceit.com](mailto:valentin.rojo@luceit.com)

## Support
- DevOps Engineer: [valentin.rojo@luceit.com](mailto:valentin.rojo@luceit.com)

## License
MIT License

## Technologies

- Java 17
- Spring Boot 3.x.x and Spring Framework 6
- Docker
- Apache Maven
- LogBack
- GIT

## Requirements

In order to run this app in your machine of choice you must have the following.

- Java SDK 17
- Docker Engine (optional)
- Apache Maven (included as maven wrapper 'mvnw' file)

## Installation instructions

### Run from CLI (BASH)
To run using the maven wrapper (from CLI):

1. Define these other environment variables.

```bash
export LOGBACK_PATTERN="%d{ISO8601} |  %highlight(%-5.5level) | %-20.20thread | %-32.40X{requestId:-No requestId} | %-50.50logger{36} | %green(%msg%n)"
export SERVER_PORT=8181
export SPRING_PROFILES_ACTIVE=dev
```

2. Execute maven wrapper: clean install

```bash
./mvnw clean install
```

3. Look for the `.jar` file in <project-root>/target/*.jar

The file name should something like "mercedes-benz-swapi-consumer-0.0.1-SNAPSHOT.jar"

Move to target folder and execute:

```bash
java -jar mercedes-benz-swapi-consumer-0.0.1-SNAPSHOT.jar
```

### Run from IntelliJ

#### Maven

Add these env vars to your maven runner (you can access from the panel on the right side).

```
SERVER_PORT=8181;LOGBACK_PATTERN=%d{ISO8601} |  %highlight(%-5.5level) | %-20.20thread | %-32.40X{requestId:-No requestId} | %-50.50logger{36} | %green(%msg%n);SPRING_PROFILES_ACTIVE=dev
```
Then run a clean install.

#### IntelliJ run/debug app feature

Create a new app and add the same env vars

```
SERVER_PORT=8181;LOGBACK_PATTERN=%d{ISO8601} |  %highlight(%-5.5level) | %-20.20thread | %-32.40X{requestId:-No requestId} | %-50.50logger{36} | %green(%msg%n);SPRING_PROFILES_ACTIVE=dev
```

**You are ready!** Your app should run now.

## Docker

You can dockerize this app with the following commands.

### Build

Build for local use
```bash
docker build -t mercedes-benz-swapi-consumer:1.0.0 .
```

Build to later push to your repository
```bash
docker build -t <your-docker-registry>/mercedes-benz-swapi-consumer:1.0.0 .
```

### Run

Run your image on your machine

```bash
sudo docker run -e LOGBACK_PATTERN="%d{ISO8601} |  %highlight(%-5.5level) | %-20.20thread | %-32.40X{requestId:-No requestId} | %-50.50logger{36} | %green(%msg%n)" -e SERVER_PORT=8080 -e SPRING_PROFILES_ACTIVE=dev -p 8383:8080 mercedes-benz-swapi-consumer:1.0.0
```

### Publish your image on docker registry

```bash
docker push <your-docker-registry>/mercedes-benz-swapi-consumer:1.0.0 .
```

I have my own **private docker registry** for my cluster to use so I can do the following:

1. Tag my local image accordingly
```bash
docker tag mercedes-benz-swapi-consumer:1.0.0 docker-registry.valen.net:5000/mercedes-benz-swapi-consumer:1.0.0
```

2. Push it to my docker registry
```bash
docker push docker-registry.valen.net:5000/mercedes-benz-swapi-consumer:1.0.0
```

Done!

## Kubernetes deployment

Refer to the kubernetes folder in this project root. The instructions are there too. Including the **stress test**.

## Usage instructions

### SwaggerUI (OpenAPI)

The project once deployed, has a swaggerUI (OpenAPI) incorporated. To check where the webserver is listening refer to the ``src > main > resources > applicatiom.yaml`` file.

Check out the SwaggerUI portal to see an interactive API documentation.

Usually listening in `http://localhost:8181/swapi-consumer/doc/swagger-ui/index.html`.

### Prometheus

You can check out this app Prometheus metrics on `http://localhost:8181/actuator/prometheus`. Mind the port.
If you have a Prometheus instance running in your cluster you may consume this endpoint.

### Liveness and readiness probes

Your rediness and liveness probes are available on:

- http://localhost:8181/actuator/health/liveness
- http://localhost:8181/actuator/health/readiness

***

### Notice: banner properties
Remember that the properties being read by the application banner are set to be read from
the application.jar "META-INF/MANIFEST.MF" file.

Meaning that you will not be able to see those values if you execute this application 
locally without compiling the app into a jar file.

***
***

## Additional documentation

### More about the Java Spring ecosystem

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#web)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Integrate with your tools

- [ ] [Set up project integrations](https://gitlab.northgateplc.es/arquetipos/spring-boot-3xx/-/settings/integrations)

### Contribution guidelines

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Set auto-merge](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

### Test and Deploy guidelines

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing(SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)
