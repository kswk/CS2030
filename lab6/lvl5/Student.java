import java.util.Optional;

public class Student extends KeyableMap<String, String, Module> {
    
    public Student(String id) {
        super(id);
    }

    public Student put(String code, String assessment, String grade) {
        Optional<Module> module = get(code);
        module.ifPresentOrElse(x -> x.put(new Assessment(assessment, grade)), 
                                () -> put(new Module(code).put(new Assessment(assessment, grade))));
        return this;
    }

    @Override
    public Student put(Module item) {
        map.put(item.getKey(), item);
        return this;
    }
}
