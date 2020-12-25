import school.dataAccess.AddressDao;
import school.dataAccess.StudentDao;
import school.dataAccess.TeacherDao;
import school.entities.Address;
import school.entities.Student;
import school.entities.Teacher;
import school.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class App {
    private static TeacherDao teacherDao;
    private static StudentDao studentDao;
    private static AddressDao addressDao;


    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        initializeDao(entityManager);
        entityManager.getTransaction().begin();
        initializeData();

        entityManager.getTransaction().commit();

        entityManager.close();
        JpaUtil.shutdown();
    }




    private static void initializeDao(EntityManager entityManager) {
        addressDao = new AddressDao(entityManager);
        studentDao = new StudentDao(entityManager);
        teacherDao = new TeacherDao(entityManager);
    }

    private static void initializeData(){
        Date date = new Date(2000, 11, 21);
        Set<Teacher> teachers = new HashSet<Teacher>();
        Set<Student> students = new HashSet<Student>();
        Set<Address> addresses = new HashSet<Address>();


        Address address = createAddress("0214446464","Qom","Qom","165161","Street 1");
        Address address2 = createAddress("021565448","Ilam","ilam","561561","Street 3");

        addresses.add(address);
        addresses.add(address2);

        Teacher teacher = createTeacher(10,"Ali","Alavi",date,50000.0,address);
        Teacher teacher2 = createTeacher(11,"Hamid","Hamedi",date,55000.0,address);
        Teacher teacher3 = createTeacher(12,"Ali","samadi",date,60000.0,address);
        teachers.add(teacher);
        teachers.add(teacher2);
        teachers.add(teacher3);


        Student student = createStudent(455,"Reza","Razavi",date);
        Student student2 = createStudent(434,"Javad","Javadi",date);
        Student student3 = createStudent(456,"Reza","majidi",date);
        Student student4 = createStudent(412,"Reza","Rajabi",date);
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        teacher.setStudents(students);
        teacher.setAddress(address);

        student.setTeachers(teachers);
        student.setAddresses(addresses);
        student2.setTeachers(teachers);

        Student loadedStudent = studentDao.load(2);//Read

//        student.setLastName("Razi");
//        studentDao.update(student);

//        teacher3.setFirstName("Aliiii");
//        teacherDao.update(teacher3);


        studentDao.delete(student);

    }

    private static Teacher createTeacher(Integer teacherCode, String firstName, String lastName,
                                         Date birthday, Double salary, Address address){
        Teacher teacher = new Teacher();
        teacher.setTeacherCode(teacherCode);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setBirthday(birthday);
        teacher.setSalary(salary);
        teacher.setAddress(address);
        teacherDao.save(teacher);
        return teacher;
    }

    private static Student createStudent(Integer studentCode, String firstName, String lastName, Date birthday) {
        Student student = new Student();
        student.setStudentCode(studentCode);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setBirthday(birthday);
        studentDao.save(student);
        return student;
    }

    private static Address createAddress(String number, String state, String city, String postalCode,
                                         String postalAddress){
        Address address = new Address();
        address.setState(state);
        address.setCity(city);
        address.setPostalAddress(postalAddress);
        address.setNumber(number);
        address.setPostalCode(postalCode);
        addressDao.save(address);
        return address;
    }
}
