import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

// Alexander Phipps (no groupmates)
// COP3330 F24

public class UniversityPersonnelManagement {

    public static void main(String[] args) {

        ArrayList<Person> personList = new ArrayList<Person>();

        System.out.println("                  Welcome to my Personnel Management Program");
    
        // always true, loops until break ensuring program completion
        while(true) {
    
        int input = 0;

        System.out.println("Choose one of the options: ");
        System.out.println("1- Enter the information of a faculty");
        System.out.println("2- Enter the information of a student");
        System.out.println("3- Print tuition invoice for a student");
        System.out.println("4- Print faculty information");
        System.out.println("5- Enter the information of a staff member");
        System.out.println("6- Print the information of a staff member");
        System.out.println("7- Delete a person");
        System.out.println("8- Exit Program");
        System.out.print("Enter your selection: ");
        Scanner scan = new Scanner(System.in);
        
        try {
            input = scan.nextInt();
        }
            
        catch (Exception e) {
            System.out.println("Invalid entry - please try again");
            scan.nextLine();
            continue;
        }
        
            // enter new faculty
            if (input == 1) {
            
                Faculty fac = new Faculty();
                System.out.println("Enter the faculty's info:");
                scan.nextLine();

                System.out.print("Name of the faculty:");
                fac.setFullName(scan.nextLine());

                System.out.print("ID: ");
                String id = scan.nextLine();
                while (!isValidID(id)) {
                    System.out.println("Invalid ID format. Please enter an ID in the format aa1234:");
                    id = scan.nextLine();
                }
                fac.setID(id);
                
                while (true) {

                    String department;
                    System.out.print("Department:");
                    department = scan.nextLine();
                    
                    if(department.equalsIgnoreCase("mathematics")) {
                        fac.setDepartment("Mathematics");
                        break;
                    }

                    if(department.equalsIgnoreCase("engineering")) {
                        fac.setDepartment("Engineering");
                        break;
                    }

                    if(department.equalsIgnoreCase("arts")) {
                        fac.setDepartment("Arts");
                        break;
                    }

                    if(department.equalsIgnoreCase("science")) {
                        fac.setDepartment("Science");
                        break;
                    }
                        
                    else {
                        System.out.println("Sorry entered department " + department + " is invalid.");
                    }          

                }

                while (true) {

                    String rank;
                    System.out.print("Rank:");
                    rank = scan.nextLine();

                    if(rank.equalsIgnoreCase("professor")) {
                        fac.setRank("Professor");
                        break;
                    }

                    if(rank.equalsIgnoreCase("adjunct")){
                        fac.setRank("Adjunct");
                        break;
                    }

                    else {
                        System.out.print("Sorry entered rank " + rank + " is invalid.");
                    }

                }
                
                System.out.println("Faculty added!");
                personList.add(fac);
            
            }
        
            // enter new student
            if (input == 2) {
                Student stud = new Student();
    
                System.out.println("Enter the student's info:");
                scan.nextLine(); // just to clear newline

                System.out.print("Name of Student: ");
                stud.setFullName(scan.nextLine());
    
                System.out.print("ID: ");
                String id = scan.nextLine();
                while (!isValidID(id)) {
                    System.out.println("Invalid ID format. Please enter an ID in the format aa1234:");
                    id = scan.nextLine();
                }
                stud.setID(id);
    
                System.out.print("GPA: ");
                stud.setGPA(scan.nextDouble());
    
                System.out.print("Credit hours: ");
                stud.setCreditHours(scan.nextInt());
    
                System.out.println("Student added!");
                personList.add(stud);
                
            }
        
            // print tuition invoice for student
            if (input == 3) {

                System.out.print("Enter the student's id:");
                scan.nextLine();

                String id = scan.nextLine();
                Student matchedStudent = null;

                Iterator<Person> stuIt = personList.iterator();
                while (stuIt.hasNext()) {

                    Person currentPerson = stuIt.next();

                    if(currentPerson instanceof Student){

                        Student stud = (Student) currentPerson;

                        if(stud.getID().equals(id)) {
                           matchedStudent = stud;
                           break;
                        }
                        
                    }

                }
                
                if(matchedStudent == null) {
                    System.out.println("No student matched!");
                }

                else {
                    matchedStudent.print();
                }

            }

            // print faculty information
            if (input == 4) {

                System.out.print("Enter the faculty's id:");
                scan.nextLine();

                String id = scan.nextLine();
                Faculty matchedFaculty = null;

                Iterator<Person> facIt = personList.iterator();
                while (facIt.hasNext()) {

                    Person currentPerson = facIt.next();

                    if(currentPerson instanceof Faculty){

                        Faculty facu = (Faculty) currentPerson;

                        if(facu.getID().equals(id)) {
                            matchedFaculty = facu;
                           break;
                        }
                        
                    }

                }
                
                if(matchedFaculty == null) {
                    System.out.println("No faculty matched!");
                }
                
                else {
                    matchedFaculty.print();
                }

            }
        
            // enter info of a staff member
            if(input == 5){

                Staff sta = new Staff();
                System.out.println("Enter the staff's info:");
                scan.nextLine();

                System.out.print("Name of the staff:");
                sta.setFullName(scan.nextLine());

                System.out.print("ID: ");
                String id = scan.nextLine();
                while (!isValidID(id)) {
                    System.out.println("Invalid ID format. Please enter an ID in the format aa1234:");
                    id = scan.nextLine();
                }
                sta.setID(id);
                
                while (true) {

                    String department;
                    System.out.print("Department: ");
                    department=scan.nextLine();
                    
                    if(department.equalsIgnoreCase("mathematics") || department.equalsIgnoreCase("engineering") || department.equalsIgnoreCase("arts") || department.equalsIgnoreCase("science")) {
                        sta.setDepartment(department);
                        break;
                    }
                        
                    else {
                        System.out.println("Sorry, entered department " + department + " is invalid.");
                    }          

                }

                while (true) {

                    String status;
                    System.out.print("Status, Enter P for Part Time, or Enter F for Full Time:");
                    status = scan.nextLine();

                    if(status.equalsIgnoreCase("p")) {
                        sta.setStatus(status);
                        break;
                    }

                    else {
                        System.out.print("Sorry, entered rank " + status + " is invalid.");
                    }

                }
                
                System.out.println("Staff member added!");
                personList.add(sta);

            }

            // print info of a staff member
            if(input == 6){

                System.out.print("Enter the staff's id: ");
                scan.nextLine();

                String id = scan.nextLine();
                Staff matchedStaff = null;

                Iterator<Person> staIt = personList.iterator();
                while (staIt.hasNext()) {

                    Person currentPerson = staIt.next();

                    if(currentPerson instanceof Staff){

                        Staff staf = (Staff) currentPerson;

                        if(staf.getID().equals(id)) {
                            matchedStaff = staf;
                           break;
                        }
                        
                    }

                }
                
                if(matchedStaff == null) {
                    System.out.println("No staff matched!");
                }
                
                else {
                    System.out.println("Staff found:");
                    System.out.println("--------------------------------------------------");
                    System.out.println(matchedStaff.getFullName() + "                 " + matchedStaff.getID());
                    System.out.println(matchedStaff.getDepartment() + " Department, " + matchedStaff.getStatus());
                    System.out.println("--------------------------------------------------");
                }

            }

            // delete person from database TO-DO
            if (input == 7) {
                System.out.print("Enter the person's ID: ");
                scan.nextLine(); // clear buffer

                String id = scan.nextLine();
                boolean personFound = false;
            
                Iterator<Person> perIt = personList.iterator();
                while (perIt.hasNext()) {
                    Person per = perIt.next();
                    if (per.getID().equals(id)) {
                        perIt.remove();
                        System.out.println("Person removed.");
                        personFound = true;
                        break;
                    }
                }
            
                if (!personFound) {
                    System.out.println("Sorry, no person with ID " + id + " exists.");
                }
            }

            // end program
            if (input == 8) {
                while (true) {
                    System.out.print("Would you like to create the report? (Y/N): ");
                    scan.nextLine();
                    String response = scan.nextLine();

                    if (response.equalsIgnoreCase("y")) {
                        System.out.print("Would you like to sort students by descending GPA or name? (1 for GPA, 2 for Name): ");
                        String response2 = scan.nextLine();

                        try (PrintWriter writer = new PrintWriter(new FileWriter("report.txt"))) {
                            writer.println("Report created on " + java.time.LocalDate.now());
                            writer.println("***********************");

                            // Faculty Section
                            writer.println("\nFaculty Members");
                            writer.println("-------------------------");
                            int facultyIndex = 1;
                            for (Person person : personList) {
                                if (person instanceof Faculty) {
                                    Faculty faculty = (Faculty) person;
                                    writer.printf("%d. %s%n", facultyIndex++, faculty.getFullName());
                                    writer.printf("ID: %s%n", faculty.getID());
                                    writer.printf("%s, %s%n%n", faculty.getRank(), faculty.getDepartment());
                                }
                            }

                            // Staff Section
                            writer.println("\nStaff Members");
                            writer.println("-------------------------");
                            int staffIndex = 1;
                            for (Person person : personList) {
                                if (person instanceof Staff) {
                                    Staff staff = (Staff) person;
                                    writer.printf("%d. %s%n", staffIndex++, staff.getFullName());
                                    writer.printf("ID: %s%n", staff.getID());
                                    writer.printf("%s, %s%n%n", staff.getDepartment(), staff.getStatus());
                                }
                            }

                            // Students Section
                            writer.println("\nStudents");
                            writer.println("-------------------------");
                            final int[] studentIndex = {1};

                            if (response2.equals("1")) {
                                // Sort by GPA descending
                                personList.stream()
                                        .filter(person -> person instanceof Student)
                                        .map(person -> (Student) person)
                                        .sorted((s1, s2) -> Double.compare(s2.getGPA(), s1.getGPA()))
                                        .forEach(student -> writeStudent(writer, studentIndex[0]++, student));
                            } 
                            
                            else if (response2.equals("2")) {
                                // Sort by name
                                personList.stream()
                                        .filter(person -> person instanceof Student)
                                        .map(person -> (Student) person)
                                        .sorted((s1, s2) -> s1.getFullName().compareToIgnoreCase(s2.getFullName()))
                                        .forEach(student -> writeStudent(writer, studentIndex[0]++, student));
                            } 
                            
                            else {
                                System.out.println("Invalid sort option.");
                            }

                            writer.flush();
                            System.out.println("Report successfully written to report.txt");
                            System.exit(0);
                        } 
                        
                        catch (IOException e) {
                            System.out.println("Error writing to report file: " + e.getMessage());
                        }

                    } 
                    
                    else if (response.equalsIgnoreCase("n")) {
                        System.out.println("Okay, thank you!");
                        System.exit(0);
                    } 
                    
                    else {
                        System.out.println("Invalid response. Please enter Y or N.");
                    }
                }
            }
        }
    }

    // Helper method to write student information to the report
    private static void writeStudent(PrintWriter writer, int index, Student student) {
        writer.printf("%d. %s%n", index, student.getFullName());
        writer.printf("ID: %s%n", student.getID());
        writer.printf("GPA: %.2f%n", student.getGPA());
        writer.printf("Credit Hours: %d%n%n", student.getCreditHours());
    }

    private static boolean isValidID(String id) {
        return Pattern.matches("[a-zA-Z]{2}\\d{4}", id);
    }

}

class Faculty extends Employee {
 
    private String rank;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public void print() {
        printFacultyInfo(this);
    }

    public void printFacultyInfo(Faculty fac) {
        System.out.println("Faculty found:");
        System.out.println("--------------------------------------------------");
        System.out.println(fac.getFullName() + "               " + fac.getID());
        System.out.println(fac.getDepartment() + " Department, " + fac.getRank());
        System.out.println("--------------------------------------------------");
    }

}

class Staff extends Employee {

    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void print() {
        printStaffInfo(this);
    }

    public void printStaffInfo(Staff sta) {
        System.out.println("Faculty found:");
        System.out.println("--------------------------------------------------");
        System.out.println(sta.getFullName() + "               " + sta.getID());
        System.out.println(sta.getDepartment() + " Department, " + sta.getStatus());
        System.out.println("--------------------------------------------------");
    }

}

abstract class Employee extends Person {

    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}

class Student extends Person {

    private String fullName;
    private String id;
    private double gpa;
    private int creditHours;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getGPA() {
        return gpa;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int numberOfCreditHoursCurrentlyTaken) {
        this.creditHours = numberOfCreditHoursCurrentlyTaken;
    }

    @Override
    public void print() {
        makeTuitionInvoice(this);
    }

    public void makeTuitionInvoice(Student stud) {

        System.out.println("Here is the tuition invoice for " + stud.fullName + ":");
        System.out.println("--------------------------------------------------");
        System.out.println(stud.fullName + " " + stud.id);
        System.out.println("Credit Hours:" + stud.creditHours + "($236.45/credit hour)");
        System.out.println("Fees: $52");

        if(gpa >= 3.85) {
            double payment=(52+(stud.creditHours*236.45))*0.75;
            double discount= (52+(stud.creditHours*236.45))*0.25;
            System.out.println("Total payment: $" + String.format("%.2f", payment) + " ($" + String.format("%.2f", discount) + " discount applied)");
        }

        else {
            double payment=(52+(stud.creditHours*236.45));
            System.out.println("Total payment: $" + String.format("%.2f", payment) + " ($0 discount applied)");
        }

        System.out.println("--------------------------------------------------");

    }

}

abstract class Person {

    public String fullName;
    public String id;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public abstract void print();

}

