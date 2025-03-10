package structural.proxy;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();

        try {
            internet.connectTo("google.com");
            internet.connectTo("example.org");
            internet.connectTo("geeksforgeeks.org");
            internet.connectTo("google.com"); // Connect again to show access count incrementing

            // Try to access restricted site
            internet.connectTo("restricted1.com");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Print access statistics from the proxy
        if (internet instanceof ProxyInternet) {
            ((ProxyInternet) internet).printAccessStatistics();
        }

        System.out.println("\n--- Office Network Demo ---");

        // Demo with specialized office proxy
        OfficeNetworkProxy officeProxy = new OfficeNetworkProxy();

        try {
            officeProxy.connectTo("work-resource.com");
            officeProxy.connectTo("jira.company.com");
            officeProxy.connectTo("youtube.com"); // Non-productivity site
            officeProxy.connectTo("youtube.com"); // Access again to increase time spent
            officeProxy.connectTo("youtube.com"); // Access again to increase time spent
            officeProxy.connectTo("docs.company.com");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        officeProxy.printAccessStatistics();
        officeProxy.printProductivityReport();
    }
}
