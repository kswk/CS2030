public class Student extends KeyableMap<String, String, Module> {
    
    public Student(String id) {
        super(id);
    }

    @Override
    public Student put(Module item) {
        map.put(item.getKey(), item);
        return this;
    }
}
