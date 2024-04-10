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

```
<dependency>
  <groupId>io.github.microcks.quarkus</groupId>
  <artifactId>quarkus-microcks</artifactId>
  <version>0.2.3</version>
  <scope>provided</scope>
</dependency>
```

## Run

Start Quarkus using:
```
quarkus dev
```

You will be asked to run tests with the following prompt. hit `r` to run tests which will trigger the downloading of container images to enable testcontainers to spin microcks (among other images)

```
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

You can access microcks at the following URL `http://localhost:32774`, or quarkus dev services at `http://localhost:8080/q/dev-ui`