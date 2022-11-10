import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Company {

    public String name;
    public String role;

    public int packageoffered;
    public double cgcriteria;
    public LocalDateTime registerationdatetime;
    ArrayList<Student> offeredstudents = new ArrayList<Student>();
    ArrayList<Student> placedstudents = new ArrayList<Student>();
    ArrayList<Student> apppliedstudents = new ArrayList<Student>();





    public Company(String name, String role, double cgcriteria, int packageoffered) {
        this.name = name;

        this.role = role;
        this.cgcriteria = cgcriteria;
        this.packageoffered = packageoffered;

    }

    public void offerStudents(){
        int i=0;
        Student tempstudent=null;

        int r = (int) (Math.random() * (apppliedstudents.size()));



        offeredstudents.add(apppliedstudents.get(r));
        tempstudent =apppliedstudents.get(r);
        tempstudent.map.put(this, "Offered");
        tempstudent.companiesofferedtostudent.add(this);
        tempstudent.packagesofcompaniesofferedtostudent.add(this.packageoffered);
        Collections.sort(tempstudent.packagesofcompaniesofferedtostudent, Collections.reverseOrder());
        tempstudent.currentpackageoffered=tempstudent.packagesofcompaniesofferedtostudent.get(0);
        apppliedstudents.remove(r);




    }

    public void registerToInstituteDrive (PlacementCell placementCell, String registerationdatetime){
        if (placementCell.companyregstart.isBefore(LocalDateTime.parse(registerationdatetime)) && placementCell.companyregend.isAfter(LocalDateTime.parse(registerationdatetime))) {
            System.out.println("Company " + name + " registered for placement drive");
            placementCell.registeredcompanies.add(this);
        }
        else{
            System.out.println("Company " + name + " registration failed");
        }

    }

    public void getSelectedStudents (){
        for(Student student : offeredstudents){
            System.out.println("Student " + student.name + " selected for company and offered a position ");
        }

    }
    public void updateRole(String newRole){
        this.role = newRole;
    }
    public void updatePackage(int newPackage){
        this.packageoffered = newPackage;
    }
    public void updateCGCriteria(double newCGCriteria){
        this.cgcriteria = newCGCriteria;
    }


}
