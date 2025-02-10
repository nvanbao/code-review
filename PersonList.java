
package person;
import java.util.*;

public class PersonList {
    private ArrayList<Person> personList = new ArrayList<>();
    
    public void addPerson(Person person){
        personList.add(person);
    }
    
    public boolean updatePersonById(String id){
        for(Person person : personList){
            if(person.getId().equals(id)){
                person.updatePersonById(id);
                return true;
            }
        }
        return false;
    }
    deletePersonById() có nguy cơ ConcurrentModificationException

\\ Khi duyệt ArrayList và xóa phần tử, dùng for-each dễ gây ConcurrentModificationException.

    public boolean deletePersonById(String id){
    Iterator<Person> iterator = personList.iterator();
    while (iterator.hasNext()) {
        Person person = iterator.next();
        if (person.getId().equals(id)) {
            iterator.remove(); // Tránh lỗi ConcurrentModificationException
            return true;
        }
    }
    return false;
}
    
    public Person findPersonById(String id){
        for(Person person : personList){
            if(person.getId().equals(id)){
                return person;
            }
        }
        return null;
    }
    
    public void displayAll(){
        for(Person person : personList){
            person.displayDetails();
        }
    }
    
    public Student findTop3Students(){
        List<Student> students = new ArrayList<>();
        for(Person person : personList){
            if(person instanceof Student){
            students.add((Student) person);
            }
        }
        students.sort((s1,s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        if(students.size() >= 3){
            return students.get(2);
        }
        return null;
    }
    
    public Teacher findTeacherWithHighestIncome(){
        List<Teacher> teachers = new ArrayList<>();
        for(Person person : personList){
            if(person instanceof Teacher){
                teachers.add((Teacher) person);
            }
        }
        double maxIncome = 0;
        for(Teacher teacher : teachers){
            if(teacher.calculateIncome() > maxIncome){
                maxIncome = teacher.calculateIncome();
            }
        }
        for(Teacher teacher : teachers){
            if(teacher.calculateIncome() == maxIncome){
                return teacher;
            }
        }
        return null;
    }
    
    public List<Student> findStudentsWithScholarships(){
        List<Student> findStudentWithScholarShips = new ArrayList<>();
        for(Person person : personList){
            if(person instanceof Student){
                if(((Student) person).getGpa() > 3.5){
                    findStudentWithScholarShips.add((Student) person);
                }
            }
        }
        return findStudentWithScholarShips;
    }
}
