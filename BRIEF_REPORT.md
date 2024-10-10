# Brief report about the solution

## Author
Valentín Rojo Bianchi - DevOps Engineer (vrojobianchi@gmail.com)

Here I will clarify some details about the provided solution.

## Section "Technical Requirements"
- "Implement the microservice in one of the following languages: Golang, Java, or Python."
  - Done in Java.
- "The microservice should consume the people endpoint from the Star Wars API."
  - Done.
- "Fetch the data from the people endpoint."
  - Done.
- "Sort the fetched data in ascending order based on the name attribute."
  - Done. The app has a SwaggerUI, so its usage is nicely documented. More info in the README.md.
- "Create an endpoint in your microservice that returns the sorted data."
  - Done, it is the http://traefik.valen.net/swapi-consumer/list endpoint. It could use other name, like ".../swapi-consumer/listPeople" for sure, a better mapping, but you can change that easily, and since there is only one endpoint for the whole project I did not see why it could not stay this way, merely as "list".
- "Include error handling and logging."
  - Done

## Section "Containerization and Orchestration"
- "Containerize the microservice using Docker."
  - Done.
- "Write a Docker Compose file to run the service and dependencies locally."
  - It has no dependencies, it is a simple project, so I did not see the need to include a Docker Compose file. Since there are no dependencies. This was explained in detail in the email sent with the solution.
- "Deploy the service to a local Kubernetes cluster (minikube or kind)."
  - Done, I deployed it in my cluster. I have a K3S Rancher light-weight Kubernetes cluster. I have so I can have a sandbox environment with its own LB (METALLB) and reverse proxy (Traefik). It is really useful. The Kubernetes deployment was done in this cluster.

## Section "Extra Points: Performance and Scaling"
"The requirements below are not mandatory. The fact of not doing them will not discard you from the hiring
process neither reduce points. In case you decide to implement these requirements, they must work as
expected, otherwise the ones not working will not be considered."

I completed this section too.

- "Configure Horizontal Pod Autoscaler for the service."
  - Done, see kubernetes folder, it is explained in the README.md file there.
- "Write a simple performance test script to simulate load."
  - Done, see kubernetes folder. The "simple-stress-test.sh" file.
- "Document how to run the performance test and interpret the results."
  - Done in kubernetes/README.md file. Also there is the attached image "kubernetes/KD - Mercedes Benz Stress Test.png" too.

***