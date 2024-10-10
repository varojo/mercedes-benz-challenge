# Kubernetes deployment

## Introduction

This section will guide you through the deployment of the SWAPI consumer app in a Kubernetes cluster.

## Deployment

Create the namespace

```bash
kubectl create namespace mercedes-benz
```

Then create all the app's resources
```bash
kubectl create -f swapi-consumer-configMap.yaml -f swapi-consumer-deployment.yaml -f swapi-consumer-ingress.yaml -f swapi-consumer-secret.yaml -f swapi-consumer-service.yaml
```

Then create a horizontal pod autoscaler in an imperative way using:

```bash
kubectl autoscale deployment mercedes-benz-swapi-consumer --cpu-percent=60 --min=1 --max=5 -n mercedes-benz
```

### Performance test

You can stress test this microservice to see the pods scale up, then down once the load is gone.
Use the simple script in "simple-stress-test.sh"

Like so:
```bash
./simple-stress-test.sh
```

### Monitor auto-scaler results

***
### Dashboard images

Prometheus Dashboard: kubernetes/Prometheus - Mercedes Benz Stress Test.png
Kubernetes Dashboard: kubernetes/KD - Mercedes Benz Stress Test.png
***

A nice way to see if the autoscaler is working is to take a look at the kubernetes-dashboard. Just create an access token and have a look. 
Of course Prometheus is very useful too. Also you can simply take a look at the namespace's pods, if they rise in number, then flat out to the
minimum number of pods, you are good to go, it is working.

So in **CLI** when you are executing the stress test you can see something like this:

```bash
valen@valen-sandbox:~/mercedes-benz$ kubectl get all
NAME                                               READY   STATUS    RESTARTS   AGE
pod/mercedes-benz-swapi-consumer-5fc4b5f9c-rsrws   1/1     Running   0          3m41s
pod/mercedes-benz-swapi-consumer-5fc4b5f9c-747xv   0/1     Pending   0          63s
pod/mercedes-benz-swapi-consumer-5fc4b5f9c-mpgzk   0/1     Pending   0          63s
pod/mercedes-benz-swapi-consumer-5fc4b5f9c-jth4z   0/1     Pending   0          48s
pod/mercedes-benz-swapi-consumer-5fc4b5f9c-qzpc6   1/1     Running   0          63s

NAME                                   TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)   AGE
service/mercedes-benz-swapi-consumer   ClusterIP   10.43.124.255   <none>        80/TCP    79m

NAME                                           READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/mercedes-benz-swapi-consumer   2/5     5            2           79m

NAME                                                      DESIRED   CURRENT   READY   AGE
replicaset.apps/mercedes-benz-swapi-consumer-5667c5b69c   0         0         0       79m
replicaset.apps/mercedes-benz-swapi-consumer-5fc4b5f9c    5         5         2       3m41s

NAME                                                               REFERENCE                                 TARGETS    MINPODS   MAXPODS   REPLICAS   AGE
horizontalpodautoscaler.autoscaling/mercedes-benz-swapi-consumer   Deployment/mercedes-benz-swapi-consumer   313%/60%   1         5         5          67m
```

And if you are monitoring using the **Kubernetes Dashboard** you 
may see something like what you can see on this image: `kubernetes/KD - Mercedes Benz Stress Test.png`.

You can also take a look at the **Prometheus Dashboard** to see the traffic rising. Nice and clear. **This was a separate test**.

Alas, my cluster has not many resources and it is lacking memory, so several PODs cannot start because the cluster
lacks memory. Like you can see in the last line of this POD's description: `Warning  FailedScheduling  2m21s  default-scheduler  0/1 nodes are available: 1 Insufficient memory. preemption: 0/1 nodes are available: 1 No preemption victims found for incoming pod..`
Whole output below.

```bash
valen@valen-sandbox:~/mercedes-benz$ kubectl describe pod/mercedes-benz-swapi-consumer-5fc4b5f9c-jth4z
Name:             mercedes-benz-swapi-consumer-5fc4b5f9c-jth4z
Namespace:        mercedes-benz
Priority:         0
Service Account:  default
Node:             <none>
Labels:           app=mercedes-benz-swapi-consumer
                  pod-template-hash=5fc4b5f9c
Annotations:      <none>
Status:           Pending
IP:               
IPs:              <none>
Controlled By:    ReplicaSet/mercedes-benz-swapi-consumer-5fc4b5f9c
Containers:
  mercedes-benz-swapi-consumer:
    Image:      docker-registry.valen.net:5000/mercedes-benz-swapi-consumer:1.0.0
    Port:       8080/TCP
    Host Port:  0/TCP
    Limits:
      cpu:     500m
      memory:  1Gi
    Requests:
      cpu:      100m
      memory:   512Mi
    Liveness:   http-get http://:8080/actuator/health/liveness delay=5s timeout=5s period=30s #success=1 #failure=3
    Readiness:  http-get http://:8080/actuator/health/readiness delay=5s timeout=5s period=30s #success=1 #failure=3
    Environment Variables from:
      mercedes-benz-swapi-consumer  Secret     Optional: false
      mercedes-benz-swapi-consumer  ConfigMap  Optional: false
    Environment:
      LOGBACK_PATTERN:  %d{ISO8601} |  %highlight(%-5.5level) | %-20.20thread | %-32.40X{requestId:-No requestId} | %-50.50logger{36} | %green(%msg%n)
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-scftd (ro)
Conditions:
  Type           Status
  PodScheduled   False 
Volumes:
  kube-api-access-scftd:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    ConfigMapOptional:       <nil>
    DownwardAPI:             true
QoS Class:                   Burstable
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type     Reason            Age    From               Message
  ----     ------            ----   ----               -------
  Warning  FailedScheduling  2m21s  default-scheduler  0/1 nodes are available: 1 Insufficient memory. preemption: 0/1 nodes are available: 1 No preemption victims found for incoming pod..
```