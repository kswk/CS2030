public class Assessment implements Keyable<String> {
    
    String name;
    String grade;

    public Assessment(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "{" + name + ": " + grade + "}";
    }
    
    @Override
    public String getKey() {
        return name;
    }
    
    public String getGrade() {
        return grade;
    }
}
