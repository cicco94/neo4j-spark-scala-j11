docker stop neo4j
docker rm neo4j
docker run -d --publish=7474:7474 --publish=7687:7687 --env=NEO4J_AUTH=none --name neo4j neo4j