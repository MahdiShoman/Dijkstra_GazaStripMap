public class HaverSine {
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth radius in kilometers

        // Convert latitude and longitude from degrees to radians
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Haversine formula
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers
        double distance = R * c;

        return distance;
    }

/*    public static void main(String[] args) {
        // Example usage
        double lat1 = 31.4500; // Latitude for Abasan al-Kabira
        double lon1 = 34.4667; // Longitude for Abasan al-Kabira

        double lat2 = 31.4000; // Latitude for An-Naseriyah
        double lon2 = 34.4667; // Longitude for An-Naseriyah

        double distance = haversine(lat1, lon1, lat2, lon2);
        System.out.printf("The distance between the two cities is %.2f kilometers.", distance);
    }*/
}