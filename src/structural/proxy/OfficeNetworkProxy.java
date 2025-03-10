package structural.proxy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OfficeNetworkProxy extends ProxyInternet {
    private Set<String> productivitySites;
    private Map<String, Long> timeSpent;
    private static final long WORK_HOUR_LIMIT_MS = 10 * 60 * 1000; // 10 minutes in milliseconds

    public OfficeNetworkProxy() {
        super();
        productivitySites = new HashSet<>();
        timeSpent = new HashMap<>();

        // Sites related to work
        productivitySites.add("jira.company.com");
        productivitySites.add("git.company.com");
        productivitySites.add("docs.company.com");
    }

    @Override
    public void connectTo(String serverHost) throws Exception {
        // Add extra checks for office network
        // Track time spent on non-productivity sites during work hours
        boolean isWorkHours = isWorkingHours();
        boolean isProductivitySite = productivitySites.contains(serverHost);

        if (isWorkHours && !isProductivitySite) {
            long currentTimeSpent = timeSpent.getOrDefault(serverHost, 0L);
            if (currentTimeSpent > WORK_HOUR_LIMIT_MS) {
                System.out.println("WARNING: You've spent over 10 minutes on " + serverHost + " during work hours");
            }

            // Update time tracking (simplified - would track actual duration in real implementation)
            timeSpent.put(serverHost, currentTimeSpent + 60000); // Add one minute
        }

        // Call parent implementation for standard restriction checks and logging
        super.connectTo(serverHost);
    }

    private boolean isWorkingHours() {
        // Simplified implementation - would check actual time in real implementation
        return true; // Assume it's always working hours for demo
    }

    public void printProductivityReport() {
        System.out.println("\n===== PRODUCTIVITY REPORT =====");
        System.out.println("Time spent on non-productivity sites during work hours:");

        if (timeSpent.isEmpty()) {
            System.out.println("No non-productivity sites visited yet.");
        } else {
            timeSpent.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .forEach(entry -> {
                        long minutes = entry.getValue() / 60000;
                        System.out.println("  " + entry.getKey() + ": " + minutes + " minutes");
                    });
        }
        System.out.println("==============================\n");
    }
}
