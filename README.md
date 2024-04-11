# Quarkus with Microcks for API mocking

_Reference repo_: https://github.com/microcks/microcks-quarkus

As always, I like to start with minimal projects to understand the impact of each incremental feature (dependency/lib/etc.) that we add.

Let's start with a clean minimal Quarkus REST service, and add Microcks to it to understand what is its net benefit

## Generate a project using the quarkus cli:

This will create a default Quarkus app with a minimal REST GET resource and tests using RestAssured

```
quarkus create
```

## Include Microcks

Now, we include Microcks

### Include Microcks dependency:

```xml
<dependency>
  <groupId>io.github.microcks.quarkus</groupId>
  <artifactId>quarkus-microcks</artifactId>
  <version>0.2.3</version>
  <scope>provided</scope>
</dependency>
```

### Import OpenAPIs for mocking or contract testing

Under `src/test/resources`, you can list all openapi, grpc, postman, soapui (all files understood by microcks) to be imported. I import openapis using the following application config:

```yaml
quarkus:
  microcks:
    devservices:
      artifacts:
        primaries: openapi/*-openapi.yaml
```

## Ensure Testcontainers finds your docker socket (when not using Docker)

For those using Podman, Rancher, etc., ensure the docker socket is available at the expected path:

In my case with Rancher Desktop, my socket is in my home directory, so I create a soft link to /var/run/docker.sock
```
sudo ln -s $HOME/.rd/docker.sock /var/run/docker.sock
```

Start Quarkus using:
```
quarkus dev
```

You will be asked to run tests with the following prompt. hit `r` to run tests which will trigger the downloading of container images to enable testcontainers to spin microcks (among other images)

```shell
...
Press [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the terminal, [h] for more options>
...
2024-04-09 23:31:01,708 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Starting to pull image
2024-04-09 23:31:01,709 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  0 pending,  0 downloaded,  0 extracted, (0 bytes/0 bytes)
2024-04-09 23:31:08,623 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  4 pending,  1 downloaded,  0 extracted, (1 MB/? MB)
2024-04-09 23:31:12,977 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  3 pending,  2 downloaded,  0 extracted, (64 MB/? MB)
2024-04-09 23:31:15,387 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  2 pending,  3 downloaded,  0 extracted, (113 MB/? MB)
2024-04-09 23:31:15,581 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  1 pending,  4 downloaded,  0 extracted, (117 MB/? MB)
2024-04-09 23:31:17,088 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  1 pending,  4 downloaded,  1 extracted, (118 MB/? MB)
2024-04-09 23:31:21,682 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  1 pending,  4 downloaded,  2 extracted, (218 MB/? MB)
2024-04-09 23:31:21,743 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  1 pending,  4 downloaded,  3 extracted, (218 MB/? MB)
2024-04-09 23:31:21,802 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  1 pending,  4 downloaded,  4 extracted, (218 MB/? MB)
2024-04-09 23:31:22,839 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  0 pending,  5 downloaded,  4 extracted, (218 MB/220 MB)
2024-04-09 23:31:24,844 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pulling image layers:  0 pending,  5 downloaded,  5 extracted, (220 MB/220 MB)
2024-04-09 23:31:24,885 INFO  [tc.qua.io/microcks/microcks-uber:latest] (docker-java-stream--2040217696) Pull complete. 5 layers, pulled in 23s (downloaded 220 MB at 9 MB/s)
2024-04-09 23:31:42,697 INFO  [io.git.mic.qua.dep.DevServicesMicrocksProcessor] (build-16) The 'default' microcks container is ready on http://localhost:32774
...
```

You can access Microcks by finding the URL in the Quarkus dev services portal:  `http://localhost:8080/q/dev-ui` under `Dev Services`. In my case in the current iteration, it's `http://localhost:32796` (ports are randomized)

In Microcks under `API | Services`, the imported openapi contract `Greetings Service - 0.0.2`. This OpenAPI is used for contract testing our API using the openapi as the source of truth