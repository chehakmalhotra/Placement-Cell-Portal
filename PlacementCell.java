import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlacementCell {
    public LocalDateTime studentregstart;
    public LocalDateTime studentregend;
    public LocalDateTime companyregstart;
    public LocalDateTime companyregend;

    ArrayList<Student> registeredstudents = new ArrayList<Student>();
    ArrayList<Company> registeredcompanies = new ArrayList<Company>();



    public void openStudentRegistration(String studentregstart, String studentregend) {
        System.out.println("Student registration opened");
        this.studentregstart = LocalDateTime.parse(studentregstart);
        boolean isBefore = this.studentregstart.isBefore(this.companyregend);
        if(isBefore==true){
            System.out.println("Invalid start time, can't start before company registrations end");
        }
        this.studentregend = LocalDateTime.parse(studentregend);
    }

    public void openCompanyRegistration(String companyregstart, String companyregend) {
        System.out.println("Company registration opened");
        this.companyregstart = LocalDateTime.parse(companyregstart);
        this.companyregend = LocalDateTime.parse(companyregend);

    }
    public void getNumberRegisteredStudents(){
        System.out.println("Number of registered students: " + registeredstudents.size());
    }
    public void getNumberRegisteredCompanies(){
        System.out.println("Number of registered companies: " + registeredcompanies.size());
    }

    public void getNumberPlacedStudents(){
        int count = 0;
        for (Student student : registeredstudents) {
            if (student.currentStatus.equals("placed")) {
                count++;
                System.out.println( student.name + "has been placed");
            }

        }
        System.out.println("Number of placed students: " + count);

    }

    public void getNumberBlockedStudents(){
        int count = 0;
        for (Student student : registeredstudents) {
            if (student.currentStatus.equals("blocked")) {
                count++;
            }
        }
        System.out.println("Number of blocked students: " + count);
    }

    public void getNumberUnplacedStudents(){
        int count = 0;
        for (Student student : registeredstudents) {
            if (student.currentStatus.equals("unplaced")) {
                count++;
            }
        }
        System.out.println("Number of unplaced students: " + count);
    }

    public void getOfferedStudents(){
        for (Student student : registeredstudents) {
            if (student.map.containsValue("Offered")) {
                System.out.println(student.name + " has been offered a position");
            }

        }
    }

    public void getStudentDetails(String name, String rollnumber){
        for (Student student : registeredstudents) {
            if (student.name.equals(name) && student.rollnumber.equals(rollnumber)) {
                System.out.println("Student " + student.name + " details:");
                for( Company acompany: registeredcompanies){
                    if (student.companiesregisteredtobystudent.contains(acompany)){
                        System.out.println("Has applied to company " + acompany.name );
                    }
                    else System.out.println("Has not applied to company " + acompany.name );
                }

                for( Company acompany: student.companiesregisteredtobystudent){
                    if (student.companiesofferedtostudent.contains(acompany)){
                        System.out.println("Has been offered a position by company " + acompany.name );
                    }
                    else System.out.println("Has not been offered a position by company " + acompany.name );
                }


            }
        }


    }

    public void getCompanyDetails(String name) {
        for (Company company : registeredcompanies) {
            if (company.name.equals(name)) {
                System.out.println("Company " + company.name + " details:");
                for (Student student: company.offeredstudents){
                System.out.println("Has offered student " + student.name);
                }
                for (Student student: company.placedstudents){
                    System.out.println("Has placed student " + student.name);
                }
            }
        }

    }
    public void getAveragePackage(){
        int sum = 0;
        for (Company company : registeredcompanies) {
            sum += company.packageoffered;
        }
        System.out.println("Average package offered: " + sum/registeredcompanies.size());
    }

    public void getCompanyProcessResults(Company company){
        company.getSelectedStudents();
        for (Student student: company.placedstudents){
            System.out.println("Has placed student " + student.name);
        }
    }

    public void updateStudentcg(String name, String rollnumber, double cg){
        for (Student student : registeredstudents) {
            if (student.name.equals(name) && student.rollnumber.equals(rollnumber)) {
                student.CG = cg;
            }
        }
    }


}










