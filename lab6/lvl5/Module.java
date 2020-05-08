public class Module extends KeyableMap<String, String, Assessment> {
    
    public Module(String code) {
        super(code);
    }

    @Override
    public Module put(Assessment item) {
        map.put(item.getKey(), item);
        return this;
    }
}
