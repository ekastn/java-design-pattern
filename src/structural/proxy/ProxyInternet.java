package structural.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyInternet implements Internet {
    private Internet realInternet;
    private List<String> restrictedSites;
    private Map<String, Integer> accessLog;

    public ProxyInternet() {
        realInternet = new RealInternet();
        restrictedSites = new ArrayList<>();
        accessLog = new HashMap<>();

        // Initialize list of restricted websites
        restrictedSites.add("restricted1.com");
        restrictedSites.add("restricted2.com");
        restrictedSites.add("adult-content.com");
        restrictedSites.add("gambling.com");
    }

    @Override
    public void connectTo(String serverHost) throws Exception {
        if (restrictedSites.contains(serverHost)) {
            throw new Exception("Access Denied: Connection to " + serverHost + " is restricted by company policy");
        }

        logAccess(serverHost);

        realInternet.connectTo(serverHost);
    }

    private void logAccess(String serverHost) {
        // Update access count for this server
        accessLog.put(serverHost, accessLog.getOrDefault(serverHost, 0) + 1);
        System.out.println("LOG: Access to " + serverHost + " (Total visits: " + accessLog.get(serverHost) + ")");
    }

    public void printAccessStatistics() {
        System.out.println("\n===== ACCESS STATISTICS =====");
        if (accessLog.isEmpty()) {
            System.out.println("No websites have been accessed yet.");
        } else {
            System.out.println("Website access count:");
            accessLog.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " visits"));
        }
        System.out.println("============================\n");
    }
}
