package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import edu.princeton.cs.algs4.TrieSET;
import edu.princeton.cs.algs4.TrieST;
import org.eclipse.jetty.util.Trie;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    protected TrieST<Node> myTrieSet = new TrieST();
    private KDTree kdt ;
    private Map<Point,Node> nodePointMap;
    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();
        List<Point> points = new ArrayList<>();
        nodePointMap = new HashMap<>();
        for (Node node: nodes) {
            String s =node.name();
            if(s!=null){
                myTrieSet.put(cleanString(s),node);
            }
            if(this.neighbors(node.id()).size()>0) {
                Point point = new Point(node.lon(), node.lat());
                points.add(point);
                nodePointMap.put(point, node);
            }
        }

        kdt = new KDTree(points);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point curr =  kdt.nearest(lon,lat);
        return nodePointMap.get(curr).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> all = new ArrayList<>();
        myTrieSet.keysWithPrefix(cleanString(prefix)).forEach(elem->all.add(myTrieSet.get(elem).name()));
        return all ;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String locationNameClean = cleanString(locationName);
        List<String> locationKeys = getLocationsByPrefix(locationNameClean);
        List<Map<String, Object>> resMaps = new ArrayList<>();
        for (String key : locationKeys){
            Map<String,Object> resMap = new HashMap<>();
            Node node = myTrieSet.get(cleanString(key));
            if(node != null){
                resMap.put("lat",node.lat());
                resMap.put("lon",node.lon());
                resMap.put("name",node.name());
                resMap.put("id",node.id());
                resMaps.add(resMap);
            }
        }
        return resMaps;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
