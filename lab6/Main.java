import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws NoSuchRecordException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Roster roster = new Roster("year");
        for (int i = 0; i < N; i++) {
            String id = sc.next();
            String code = sc.next();
            String assessment = sc.next();
            String grade = sc.next();
            if (roster.get(id) == null) {
                Student student = new Student(id);
                Module module = new Module(code);
                Assessment a = new Assessment(assessment, grade);
                roster.put(student.put(module.put(a)));
            } else {
                if (roster.get(id).get(code) == null) {
                    Module module = new Module(code);
                    Assessment a = new Assessment(assessment, grade);
                    roster.get(id).put(module.put(a));
                } else {
                    Assessment a = new Assessment(assessment, grade);
                    roster.get(id).get(code).put(a);
                }
            }
        }
        while (sc.hasNext()) {
            String id = sc.next();
            String code = sc.next();
            String assessment = sc.next();
            try {
                String grade = roster.getGrade(id, code, assessment);
                System.out.println(grade);
            } catch (NoSuchRecordException e) {
                System.out.println("NoSuchRecordException: No such record: " + id + " " + code + " " + assessment);
            }
        }
    }
}
