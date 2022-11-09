public class WrittenVariable {
    // Simple class for variables to be typed
    private String type;
    private String name;

    // Constructor assigns proper variables
    public WrittenVariable(String type, String name) {
        this.type = type;
        this.name = name;
    }

    // Public getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // String repr
    public String toString() {
        return type + " " + name; 
    }

    // Create strings for the function expressions for getting and setting
    public String generateGetter() {
        return "public " + type + " get" + name.substring(0,1).toUpperCase() + name.substring(1) + "() ";
    }

    public String generateSetter() {
        return "public void set" + name.substring(0,1).toUpperCase() + name.substring(1) + "(" + this.toString() + ") ";
    }
}
