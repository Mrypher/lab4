public class VehicleFactory {
    public static VehicleFramework createVehicle(String type) {
        switch (type.toLowerCase()) {
            case "volvo": return new Volvo240();
            case "saab": return new Saab95();
            case "scania": return new Scania();
            default: throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}