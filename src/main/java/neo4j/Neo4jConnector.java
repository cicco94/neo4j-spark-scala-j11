package neo4j;

import org.neo4j.driver.*;

import static org.neo4j.driver.Values.parameters;

public class Neo4jConnector {
    public final static Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j","root"));
    public final static Session session = driver.session(SessionConfig.forDatabase("neo4j"));


    public static void main(String...args) {

        Driver driver = GraphDatabase.driver("bolt://<HOST>:<BOLTPORT>",
                AuthTokens.basic("<USERNAME>","<PASSWORD>"));

        try (Session session = driver.session(SessionConfig.forDatabase("neo4j"))) {

            String cypherQuery =
                    "MATCH (p:Product)-[:PART_OF]->(:Category)-[:PARENT*0..]->" +
                            "(:Category {categoryName:$category})" +
                            "RETURN p.productName as product";

            var result = session.readTransaction(
                    tx -> tx.run(cypherQuery,
                            parameters("category","Dairy Products"))
                            .list());

            for (Record record : result) {
                System.out.println(record.get("product").asString());
            }
        }
        driver.close();
    }
}
