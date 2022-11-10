import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Student {
    public String name;
    public String rollnumber;
    public String branch;
    public double CG;
    public String currentStatus; // placed, blocked, unplaced

    public int currentpackageoffered;
    public Company placedincompany;



    ArrayList<Company> companiesregisteredtobystudent = new ArrayList<Company>();
    ArrayList<Company> companiesofferedtostudent = new ArrayList<Company>();
    ArrayList<Integer> packagesofcompaniesofferedtostudent = new ArrayList<Integer>();



    public HashMap<Company,String> map=new HashMap<Company,String>(); // applied, not applied, offered, placed



    public Student(String name, String rollnumber, String branch, double CG) {
        this.name = name;
        this.rollnumber = rollnumber;
        this.branch = branch;
        this.CG = CG;
        this.currentStatus="unplaced";
        this.currentpackageoffered=0;

    }



    public void registerPlacementDrive (PlacementCell placementCell, String registerationdatetime){
        System.out.println("Student " + name + " registered for placement drive at" + LocalDateTime.parse(registerationdatetime));
        placementCell.registeredstudents.add(this);
        for(Company company: placementCell.registeredcompanies){
            map.put(company,"Not Applied");
        }
    }

    public void registerForCompany(Company company){
        if (!this.currentStatus.equals("blocked")) {
            System.out.println("Student " + name + " registered for company " + company.name);
            map.put(company, "Applied");
            companiesregisteredtobystudent.add(company);
            company.apppliedstudents.add(this);
        }
    }

    public void getAllCompanies (PlacementCell placementCell){

        if (this.currentStatus.equals("unplaced")) {
            for(Company company: placementCell.registeredcompanies){
                if ((company.cgcriteria<this.CG) && (company.packageoffered > 3*(this.currentpackageoffered))) {
                    System.out.println("Can apply to Company " + company.name );


                }

            }

    }

    }


    public void getCurrentStatus() {
        System.out.println(currentStatus);
        //System.out.println(map);
        for (Company key: map.keySet()){
            System.out.println(key.name +" = "+map.get(key));
        }

    }
    public void updateCG(double newCG ,PlacementCell placementCell){
       placementCell.updateStudentcg(this.name,this.rollnumber,newCG);
    }

    public void reactToOffer(String reaction){

            Collections.sort(packagesofcompaniesofferedtostudent, Collections.reverseOrder());
            this.currentpackageoffered=packagesofcompaniesofferedtostudent.get(0);
            System.out.println("current package getting is, "+ this.currentpackageoffered);
            //Company tempcompany=null;
        if (reaction=="Accept"){
            for(Company company: companiesofferedtostudent){
                //tempcompany=company;
                if (company.packageoffered==this.currentpackageoffered){
                    System.out.println("by" + company.name);
                    this.placedincompany=company;
                    this.currentStatus="placed";
                    company.placedstudents.add(this);
                    company.offeredstudents.remove(this);

                    for (Company key: map.keySet()){
                        if (key.name==company.name){
                            map.put(key,"Placed");
                        }

                    }
                }
            }
            //tempcompany.apppliedstudents.remove(this);

        }
        else {
            for(Company company: companiesofferedtostudent){
                if (company.packageoffered==this.currentpackageoffered){



                    company.offeredstudents.remove(this);

                    System.out.println("by" + company.name);
                    for (Company key: map.keySet()){
                        if (key.name==company.name){
                            map.put(key,"Rejected");
                        }


                    }
                    if (!map.containsValue("Offered")){
                        this.currentStatus="blocked";
                    }
                }
            }


            }


        }


    }






















//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        PlacementCell placementCell = new PlacementCell();
//        Student student1 = new Student("chehak", "1", "CSE", 9.5);
//        Student student2 = new Student("rohan", "2", "CSAM", 9);
//        Student student3 = new Student("sujata", "3", "CSD", 8);
//
//        Company company1 = new Company("Amazon", "SDE", 9.0, 10000000);
//        ArrayList<Student> allstudentsever = new ArrayList<Student>();
//        allstudentsever.add(student1);
//        allstudentsever.add(student2);
//        allstudentsever.add(student3);
//        ArrayList<Company> allcompaniessever = new ArrayList<Company>();
//        allcompaniessever.add(company1);
//
//
//
//
//        System.out.println("Welcome to FutureBuilder:\n 1) Enter the Application\n 2) Exit the Application");
//        Scanner scanner = new Scanner(System.in);
//        int choice1 = scanner.nextInt();
//        while (choice1==1){
//            System.out.println("Choose The mode you want to Enter in:-\n 1) Enter as Student Mode\n 2) Enter as Company Mode\n 3) Enter as Placement Cell Mode\n 4) Return To Main Application");
//            int choice2 = scanner.nextInt();
//            while (choice2==1) {
//                System.out.println("Welcome to Student Mode: Choose the Student Query to perform-\n 1) Enter as a Student(Give Student Name, and Roll No.)\n 2) Add students\n3) Back");
//                int choice3 = scanner.nextInt();
//                if (choice3 == 1) {
//                    //enter as a student
//
//                    break;
//                } else if (choice3 == 2) {
//                    System.out.println("Enter number of students to add");
//                    int numberofstudents = scanner.nextInt();
//                    for (int i = 0; i < numberofstudents; i++) {
//                        System.out.println("Enter Student Name");
//                        String name = scanner.next();
//                        System.out.println("Enter Student Roll No.");
//                        String rollno = scanner.next();
//                        System.out.println("Enter Student CGPA");
//                        double cgpa = scanner.nextDouble();
//                        System.out.println("Enter Student Branch");
//                        String branch = scanner.next();
//                        allstudentsever.add(new Student(name, rollno, branch, cgpa));
//                        break;
//                    }
//                } else if (choice3 == 3) {
//                    break;
//                }
//            }
//            while (choice2==2){
//                System.out.println("Welcome to Company Mode: Choose the Company Query to perform-\n 1) Add company and details\n 2) Choose company \n 3) Get available companies\n4) Back");
//                int choice3 = scanner.nextInt();
//                if (choice3 == 1) {
//                    System.out.println("Enter Company Name");
//                    String nname=scanner.nextLine();
//                    System.out.println("Enter Company Job Profile");
//                    String nrole=scanner.nextLine();
//                    System.out.println("Enter Company CGPA Criteria");
//                    double ncgpa=scanner.nextDouble();
//                    System.out.println("Enter Company Salary");
//                    int nsalary=scanner.nextInt();
//
//                    Company newcompany= new Company(nname, nrole, ncgpa, nsalary);
//                    allcompaniessever.add(newcompany);
//                    break;
//                }
//
//                else if (choice3 == 2) {
//                    //go into company
//
//
//                    break;
//                }
//                else if (choice3==3){
//                    for (Company company:allcompaniessever){
//                        System.out.println(company.name);
//
//                        break;
//                    }
//                }
//                else if (choice3==4){
//                    break;
//                }
//
//
//
//            }
//
//            while (choice2==3){
//                System.out.println("Welcome to Placement Cell Mode: Choose the Placement Cell Query to perform-\n 1) Open Student Registration\n 2) Open Company Registration\n 3) Get Number of Registered Students\n 4) Get Number of Registered Companies\n 5) Get Number of Offered/Blocked/Unoffered Students\n 6) Get Student details\n 7) Get Company details\n 8) Get Average Package 9) Get Company process results 10) Back");
//                int choice3 = scanner.nextInt();
//                if (choice3 == 1) {
//                    System.out.println("Enter Student Registration Start Time");
//                    String studentregstart = scanner.next();
//                    System.out.println("Enter Student Registration End Time");               //need to check ye time vaali cheezein how to input and parse
//                    String studentregend = scanner.next();
//                    placementCell.openStudentRegistration(studentregstart, studentregend);
//                    break;
//                } else if (choice3 == 2) {
//                    System.out.println("Enter Company Registration Start Time");
//                    String companyregstart = scanner.next();
//                    System.out.println("Enter Company Registration End Time");
//                    String companyregend = scanner.next();
//                    placementCell.openCompanyRegistration(companyregstart, companyregend);
//                    break;
//                } else if (choice3 == 3) {
//                    placementCell.getNumberRegisteredStudents();
//                    break;
//                } else if (choice3 == 4) {
//                    placementCell.getNumberRegisteredCompanies();
//                    break;
//                } else if (choice3 == 5) {
//                    placementCell.getNumberPlacedStudents();
//                    placementCell.getNumberBlockedStudents();
//                    placementCell.getNumberUnplacedStudents();
//                    break;
//                } else if (choice3 == 6) {
//                    System.out.println("Enter Student Name");
//                    String studentname = scanner.next();
//                    System.out.println("Enter Student Roll No.");
//                    String studentrollno = scanner.next();
//                    placementCell.getStudentDetails(studentname, studentrollno);
//                    break;
//
//                } else if (choice3 == 7) {
//                    System.out.println("Enter Company Name");
//                    String companyname = scanner.next();
//                    placementCell.getCompanyDetails(companyname);
//                    break;
//
//                } else if (choice3 == 8) {
//                    placementCell.getAveragePackage();
//                    break;
//
//                }else if (choice3 == 9) {
//                    System.out.println("Enter Company Name");
//                    String companyname = scanner.next();
//                    for(Company company: allcompaniessever){
//                        if (company.name.equals(companyname)){
//                            placementCell.getCompanyProcessResults(company);
//                        }
//                    }
//                    break;
//
//                }
//                else if (choice3 == 10) {
//                    break;
//                }
//            }
//            while (choice2==4){
//                break;
//
//            }
//
//
//
//
//
//
//
//
//
//        }
//
//
//
//        System.out.println("Thank you for using FutureBuilder");
//
//
//
//
//
//
//    }
//}




///////import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        PlacementCell placementCell = new PlacementCell();
//        Student student1 = new Student("chehak", "1", "CSE", 9.5);
//        Student student2 = new Student("rohan", "2", "CSAM", 9);
//        Student student3 = new Student("sujata", "3", "CSD", 8);
//
//        Company company1 = new Company("Amazon", "SDE", 9.0, 10000000);
//        Company companyx = new Company("skippy", "SDE", 6.0, 10000000);
//        ArrayList<Student> allstudentsever = new ArrayList<Student>();
//        allstudentsever.add(student1);
//        allstudentsever.add(student2);
//        allstudentsever.add(student3);
//        ArrayList<Company> allcompaniessever = new ArrayList<Company>();
//        allcompaniessever.add(company1);
//        allcompaniessever.add(companyx);
//
//
//
//
//
//        while(true) {
//            System.out.println("Welcome to FutureBuilder:\n 1) Enter the Application\n 2) Exit the Application");
//            Scanner scanner = new Scanner(System.in);
//            int choice1 = scanner.nextInt();
//            while (choice1 == 1) {
//                System.out.println("Choose The mode you want to Enter in:-\n 1) Enter as Student Mode\n 2) Enter as Company Mode\n 3) Enter as Placement Cell Mode\n 4) Return To Main Application");
//                int choice2 = scanner.nextInt();
//                while (choice2 == 1) {
//                    System.out.println("Welcome to Student Mode: Choose the Student Query to perform-\n 1) Enter as a Student(Give Student Name, and Roll No.)\n 2) Add students\n3) Back");
//                    int choice3 = scanner.nextInt();
//                    if (choice3 == 1) {
//                        System.out.println("Enter the Student Name");
//                        String name = scanner.next();
//                        scanner.nextLine();
//                        System.out.println("Enter the Student Roll No.");
//
//                        String rollno = scanner.next();
//                        Student tempstudent = null;
//
//
//                        while (true) {
//
//                            for (Student student : allstudentsever) {
//                                if (student.name.equals(name) && student.rollnumber.equals(rollno)) {
//                                    System.out.println("Welcome " + student.name);
//                                    tempstudent = student;
//                                }
//                            }
//
//
//
//
//
//                            System.out.println("Choose 1) Register for placement drive\n 2) Register for company\n 3) Get all available companies\n 4) Get Current status\n 5)Update CGPA\n 6) Accept Offer\n 7) Reject Offer\n8) Back");
//                            int choice4 = scanner.nextInt();
//                            if (choice4 == 1) {
//                                tempstudent.registerPlacementDrive(placementCell);
//
//                            } else if (choice4 == 2) {
//                                System.out.println("Enter the Company Name");
//                                String companyname = scanner.next();
//                                for (Company company : allcompaniessever) {
//                                    if (company.name.equals(companyname)) {
//                                        tempstudent.registerForCompany(company);
//                                    }
//                                }
//
//
//                            } else if (choice4 == 3) {
//                                tempstudent.getAllCompanies(placementCell);
//
//                            } else if (choice4 == 4) {
//                                tempstudent.getCurrentStatus();
//                                if (tempstudent.currentStatus=="placed"){
//                                    System.out.println("placed in"+ tempstudent.placedincompany.name);
//                                }
//
//
//                                ArrayList<Integer> offeredpackages= new ArrayList<>();
//                                for(Company company: tempstudent.companiesregisteredtobystudent) {
//                                    if (tempstudent.map.get(company) == "offered") {
//                                        System.out.println("offered by" + company.name);
//                                        offeredpackages.add(company.packageoffered);
//
//
//                                    }
//                                    Collections.sort(offeredpackages, Collections.reverseOrder());
//                                    System.out.println("highest Offered Package is" + offeredpackages.get(0));
//                                    for (Company company2 : tempstudent.companiesregisteredtobystudent) {
//                                        if (tempstudent.map.get(company2) == "offered") {
//                                            if (company2.packageoffered == offeredpackages.get(0)) {
//                                                System.out.println("highest offered company is" + company2.name);
//                                            }
//                                        }
//                                    }
//                                }
//
//
//
//
//
//
//
//
//                            } else if (choice4 == 5) {
//                                System.out.println("Enter the updated CGPA");
//                                double cgpa = scanner.nextDouble();
//                                tempstudent.updateCG(cgpa, placementCell);
//
//                            } else if (choice4 == 6) {
//
//                                //accept offer
//                            } else if (choice4 == 7) {
//
//                                //reject offer
//                            } else if (choice4 == 8) {
//                                break;
//                            }
//                        }
//
//                    }
//
//
//
//                 else if (choice3 == 2) {
//                    System.out.println("Enter number of students to add");
//                    int numberofstudents = scanner.nextInt();
//                    for (int i = 0; i < numberofstudents; i++) {
//                        System.out.println("Enter Student Name");
//                        String name = scanner.next();
//                        scanner.nextLine();
//                        System.out.println("Enter Student Roll No.");
//                        String rollno = scanner.next();
//                        System.out.println("Enter Student CGPA");
//                        double cgpa = scanner.nextDouble();
//                        System.out.println("Enter Student Branch");
//                        String branch = scanner.next();
//                        allstudentsever.add(new Student(name, rollno, branch, cgpa));
//
//                    }
//                } else if (choice3 == 3) {
//                    break;
//                }
//
//        }
//            while (choice2 == 2) {
//                System.out.println("Welcome to Company Mode: Choose the Company Query to perform-\n 1) Add company and details\n 2) Choose company \n 3) Get available companies\n4) Back");
//                int choice3 = scanner.nextInt();
//                if (choice3 == 1) {
//                    System.out.println("Enter Company Name");
//                    scanner.nextLine();
//                    String nname = scanner.nextLine();
//
//                    System.out.println("Enter Company Job Profile");
//
//                    String nrole = scanner.nextLine();
//                    System.out.println("Enter Company CGPA Criteria");
//
//                    double ncgpa = scanner.nextDouble();
//                    System.out.println("Enter Company Salary");
//
//                    int nsalary = scanner.nextInt();
//
//
//                    allcompaniessever.add(new Company(nname, nrole, ncgpa, nsalary));
//                    for(int i=0;i< allcompaniessever.size();i++){
//                        System.out.println(allcompaniessever.get(i).name);
//                    }
//                    System.out.println(allcompaniessever);
//
//
//                } else if (choice3 == 2) {
//                    System.out.println("Enter any of these companies");
//                    String companyname = scanner.next();
//                    Company tempcompany = null;
//
//
//
//                    for (Company company : allcompaniessever) {
//                        if (company.name.equals(companyname)) {
//                            System.out.println("Welcome " + company.name);
//
//                        }
//                    }
//
//
//
//
//
//                    while(true) {
//                        for (Company company : allcompaniessever) {
//                            if (company.name.equals(companyname)) {
//                                tempcompany = company;
//                            }
//                        }
//                        System.out.println("Choose the Company Query to perform-\n 1) Update Company Job Profile\n 2) Update Company Salary\n 3) Update Company CGPA Criteria\n 4) Register to drive\n5) Back");
//                        int choice4 = scanner.nextInt();
//                        if (choice4 == 1) {
//                            System.out.println("Enter new Job Profile");
//                            String newrole = scanner.next();
//                            tempcompany.role = newrole;
//
//                        } else if (choice4 == 2) {
//                            System.out.println("Enter new Salary");
//                            int newsalary = scanner.nextInt();
//                            tempcompany.packageoffered = newsalary;
//
//                        } else if (choice4 == 3) {
//                            System.out.println("Enter new CGPA Criteria");
//                            double newcgpa = scanner.nextDouble();
//                            tempcompany.cgcriteria = newcgpa;
//
//                        } else if (choice4 == 4) {
//                            System.out.println("Enter registration date amd time");
//                            String date = scanner.next();
//                            tempcompany.registerToInstituteDrive(placementCell,date);
//                        } else if (choice4 == 5) {
//                            break;
//                        }
//
//
//                    }
//
//                } else if (choice3 == 3) {
//                    for (Company company : allcompaniessever) {
//                        System.out.println(company.name);
//
//
//
//                    }
//                } else if (choice3 == 4) {
//                    break;
//                }
//
//
//            }
//
//            while (choice2 == 3) {
//                System.out.println("Welcome to Placement Cell Mode: Choose the Placement Cell Query to perform-\n 1) Open Student Registration\n 2) Open Company Registration\n 3) Get Number of Registered Students\n 4) Get Number of Registered Companies\n 5) Get Number of Offered/Blocked/Unoffered Students\n 6) Get Student details\n 7) Get Company details\n 8) Get Average Package 9) Get Company process results 10) Back");
//                int choice3 = scanner.nextInt();
//                if (choice3 == 1) {
//                    System.out.println("Enter Student Registration Start Time");
//                    String studentregstart = scanner.next();
//                    System.out.println("Enter Student Registration End Time");
//                    String studentregend = scanner.next();
//                    placementCell.openStudentRegistration(studentregstart, studentregend);
//
//                } else if (choice3 == 2) {
//                    System.out.println("Enter Company Registration Start Time");
//                    String companyregstart = scanner.next();
//                    System.out.println("Enter Company Registration End Time");
//                    String companyregend = scanner.next();
//                    placementCell.openCompanyRegistration(companyregstart, companyregend);
//
//                } else if (choice3 == 3) {
//                    placementCell.getNumberRegisteredStudents();
//
//                } else if (choice3 == 4) {
//                    placementCell.getNumberRegisteredCompanies();
//
//                } else if (choice3 == 5) {
//                    placementCell.getNumberPlacedStudents();
//                    placementCell.getNumberBlockedStudents();
//                    placementCell.getNumberUnplacedStudents();
//
//                } else if (choice3 == 6) {
//                    System.out.println("Enter Student Name");
//                    String studentname = scanner.next();
//                    System.out.println("Enter Student Roll No.");
//                    String studentrollno = scanner.next();
//                    placementCell.getStudentDetails(studentname, studentrollno);
//
//
//                } else if (choice3 == 7) {
//                    System.out.println("Enter Company Name");
//                    String companyname = scanner.next();
//                    placementCell.getCompanyDetails(companyname);
//
//
//                } else if (choice3 == 8) {
//                    placementCell.getAveragePackage();
//
//
//                } else if (choice3 == 9) {
//                    System.out.println("Enter Company Name");
//                    String companyname = scanner.next();
//                    for (Company company : allcompaniessever) {
//                        if (company.name.equals(companyname)) {
//                            placementCell.getCompanyProcessResults(company);
//                        }
//                    }
//
//
//                } else if (choice3 == 10) {
//                    break;
//                }
//            }
//            if (choice2 == 4) {
//                break;
//
//            }
//
//
//        }
//
//        if (choice1==2){
//            break;
//        }
//
//
//
//
//
//
//
//
//
//        }
//
//
//        System.out.println("Thank You for using FutureBuilder");
//    }
//}