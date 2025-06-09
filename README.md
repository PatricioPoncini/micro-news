# `Micro News`
A simple distributed system composed of two microservices responsible for consuming news from an external API and storing them in Redis. These services communicate with each other using Kafka.

## `Stack`
[![My Skills](https://skillicons.dev/icons?i=java,spring,gradle,kafka,redis,docker)](https://skillicons.dev)

## `Setup`
1. **Clone the repository**
```bash
git clone https://github.com/PatricioPoncini/micro-news.git
cd micro-news
```
2. **Initialize all microservices**
```bash
docker compose up -d --build
```
You can list the running containers using:
```bash
docker ps
```

## `Mediastack Documentation`
You can find the full API documentation for Mediastack [here](https://mediastack.com/documentation)

### `Format`
To format the codebase using the predefined code style:
```bash
make format
```

<!---
TODO:
- Mejorar la forma de utilizar el comando format
-->