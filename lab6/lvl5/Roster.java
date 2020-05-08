import java.util.Optional;
import java.util.NoSuchElementException;

public class Roster extends KeyableMap<String, String, Student> {
    
    public Roster(String year) {
        super(year);
    }
    
    @Override
    public Roster put(Student item) {
        map.put(item.getKey(), item);
        return this;
    }

    public String getGrade(String id, String code, String assessment) throws NoSuchRecordException {
        try {
            Optional<String> grade = get(id).flatMap(x -> x.get(code)).flatMap(x -> x.get(assessment)).map(
                    x -> x.getGrade());
            return grade.orElseThrow();
        } catch (NoSuchElementException e) {
            throw new NoSuchRecordException("No such record: " + id + " " + code + " " + assessment);
        }
    }

    public Roster put(String id, String code, String assessment, String grade) {
        Optional<Student> student = get(id);
        student.ifPresentOrElse(x -> x.put(code, assessment, grade), 
                () -> put(new Student(id).put(new Module(code).put(
                        new Assessment(assessment, grade)))));
        return this;
    }
}
