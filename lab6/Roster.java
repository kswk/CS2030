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
        if (map.get(id) == null || map.get(id).get(code) == null || map.get(id).get(code).get(assessment) == null) {
            throw new NoSuchRecordException("No such record: " + id + " " + code + " " + assessment);
        } else {
            return map.get(id).get(code).get(assessment).getGrade();
        }
    }
}
