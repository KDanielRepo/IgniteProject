import org.apache.ignite.*;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.lang.IgniteRunnable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws Exception  {

        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

// Open JDBC connection
        Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");

// Get data
        try (Statement stmt = conn.createStatement()) {
            try (ResultSet rs =
                         stmt.executeQuery("SELECT p.name, c.name " +
                                 " FROM Person p, City c " +
                                 " WHERE p.city_id = c.id")) {

                while (rs.next())
                    System.out.println(rs.getString(1) + ", " + rs.getString(2));
            }
        }

        /*Ignite ignite = Ignition.start();

        IgniteCluster cluster = ignite.cluster();

// Compute instance over remote nodes.
        IgniteCompute compute = ignite.compute(cluster.forRemotes());

// Print hello message on all remote nodes.
        //compute.broadcast(() -> System.out.println("Hello node: " + cluster.localNode().id()));
        compute.broadcast(new IgniteRunnable() {
            @Override
            public void run() {
                System.out.println("Hello node: " + cluster.localNode().id());
            }
        });*/
        /*try (Ignite ignite = Ignition.start()) {

            // Put values in cache.

            IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCache");

            cache.put(1, "Hello");

            cache.put(2, "World!");

            // Get values from cache

            // Broadcast 'Hello World' on all the nodes in the cluster.

            //ignite.compute().broadcast(()->System.out.println(cache.get(1) + " " + cache.get(2)));
            ignite.compute().broadcast(new IgniteRunnable() {
                @Override
                public void run() {
                    System.out.println(cache.get(1) + " " + cache.get(2));
                }
            });

        }*/

    }
}
