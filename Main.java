import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlacementCell placementCell = new PlacementCell();
        Student student1 = new Student("chehak", "1", "CSE", 9.5);
        Student student2 = new Student("rohan", "2", "CSAM", 9.2);
        Student student3 = new Student("sujata", "3", "CSD", 8);

        Company companyy = new Company("Amazon", "SDE", 9.0, 10000000);
        Company companyx = new Company("skippy", "SDE", 6.0, 100);
        ArrayList<Student> allstudentsever = new ArrayList<Student>();
        allstudentsever.add(student1);
        allstudentsever.add(student2);
        allstudentsever.add(student3);
        ArrayList<Company> allcompaniessever = new ArrayList<Company>();
        allcompaniessever.add(companyy);
        allcompaniessever.add(companyx);

        placementCell.companyregstart = LocalDateTime.parse("2021-01-01T00:00:00");
        placementCell.companyregend = LocalDateTime.parse("2021-01-01T23:59:59");

        companyy.registerToInstituteDrive(placementCell, "2021-01-01T10:00:00");
        companyx.registerToInstituteDrive(placementCell, "2021-01-01T10:00:00");
        student1.registerPlacementDrive(placementCell,"2022-01-11T10:00:00");
        student1.registerForCompany(companyy);
        student1.registerForCompany(companyx);
        student2.registerPlacementDrive(placementCell,"2022-01-11T10:00:00");
        student2.registerForCompany(companyy);
        student2.registerForCompany(companyx);








        while(true) {
            System.out.println("Welcome to FutureBuilder:\n 1) Enter the Application\n 2) Exit the Application");
            Scanner scanner = new Scanner(System.in);
            int choice1 = scanner.nextInt();
            while (choice1 == 1) {
                System.out.println("Choose The mode you want to Enter in:-\n 1) Enter as Student Mode\n 2) Enter as Company Mode\n 3) Enter as Placement Cell Mode\n 4) Return To Main Application");
                int choice2 = scanner.nextInt();
                while (choice2 == 1) {
                    System.out.println("Welcome to Student Mode: Choose the Student Query to perform-\n 1) Enter as a Student(Give Student Name, and Roll No.)\n 2) Add students\n3) Back");
                    int choice3 = scanner.nextInt();
                    if (choice3 == 1) {
                        System.out.println("Enter the Student Name");
                        String name = scanner.next();
                        //scanner.nextLine();
                        System.out.println("Enter the Student Roll No.");

                        String rollno = scanner.next();
                        Student tempstudent = null;


                        while (true) {

                            for (Student student : allstudentsever) {
                                if (student.name.equals(name) && student.rollnumber.equals(rollno)) {
                                    System.out.println("Welcome " + student.name);
                                    tempstudent = student;
                                }
                            }





                            System.out.println("Choose 1) Register for placement drive\n 2) Register for company\n 3) Get all available companies\n 4) Get Current status\n 5)Update CGPA\n 6) Accept Offer\n 7) Reject Offer\n8) Back");
                            int choice4 = scanner.nextInt();
                            if (choice4 == 1) {
                                System.out.println("Enter the reg datetime");
                                String regdatetime=scanner.next();
                                tempstudent.registerPlacementDrive(placementCell, regdatetime);

                            } else if (choice4 == 2) {
                                System.out.println("Enter the Company Name");
                                String companyname = scanner.next();
                                for (Company company : allcompaniessever) {
                                    if (company.name.equals(companyname)) {
                                        tempstudent.registerForCompany(company);
                                    }
                                }


                            } else if (choice4 == 3) {
                                tempstudent.getAllCompanies(placementCell);

                            } else if (choice4 == 4) {
                                tempstudent.getCurrentStatus();
                                if (tempstudent.currentStatus=="placed"){
                                    //System.out.println("placed in"+ tempstudent.placedincompany.name);
                                }


                                ArrayList<Integer> offeredpackages= new ArrayList<>();

                                for(Company company: tempstudent.companiesofferedtostudent) {
                                        //System.out.println("offered by" + company.name);
                                        offeredpackages.add(company.packageoffered);



                                }
                                    if (offeredpackages.size()>0){
                                        Collections.sort(offeredpackages, Collections.reverseOrder());
                                        //System.out.println("highest Offered Package is " + offeredpackages.get(0));
                                        for (Company company2 : tempstudent.companiesregisteredtobystudent) {
                                            if (tempstudent.map.get(company2) == "offered") {
                                                if (company2.packageoffered == offeredpackages.get(0)) {
                                                    System.out.println("highest offered company is" + company2.name);
                                                }
                                            }
                                        }
                                    }









                            } else if (choice4 == 5) {
                                System.out.println("Enter the updated CGPA");
                                double cgpa = scanner.nextDouble();
                                tempstudent.updateCG(cgpa, placementCell);

                            } else if (choice4 == 6) {


                                for(Company company: tempstudent.companiesregisteredtobystudent) {
                                    if (tempstudent.map.get(company) == "offered") {
                                        System.out.println("offered by" + company.name);


                                    }
                                }

                                    tempstudent.reactToOffer("Accept");





                            } else if (choice4 == 7) {


                                tempstudent.reactToOffer("Reject");











                            } else if (choice4 == 8) {
                                break;
                            }
                        }

                    }



                 else if (choice3 == 2) {
                    System.out.println("Enter number of students to add");
                    int numberofstudents = scanner.nextInt();
                    for (int i = 0; i < numberofstudents; i++) {
                        System.out.println("Enter Student Name");
                        String name = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter Student Roll No.");
                        String rollno = scanner.next();
                        System.out.println("Enter Student CGPA");
                        double cgpa = scanner.nextDouble();
                        System.out.println("Enter Student Branch");
                        String branch = scanner.next();
                        allstudentsever.add(new Student(name, rollno, branch, cgpa));

                    }
                } else if (choice3 == 3) {
                    break;
                }

        }
            while (choice2 == 2) {
                System.out.println("Welcome to Company Mode: Choose the Company Query to perform-\n 1) Add company and details\n 2) Choose company \n 3) Get available companies\n4) Back");
                int choice3 = scanner.nextInt();
                if (choice3 == 1) {
                    System.out.println("Enter Company Name");
                    scanner.nextLine();
                    String nname = scanner.nextLine();

                    System.out.println("Enter Company Job Profile");

                    String nrole = scanner.nextLine();
                    System.out.println("Enter Company CGPA Criteria");

                    double ncgpa = scanner.nextDouble();
                    System.out.println("Enter Company Salary");

                    int nsalary = scanner.nextInt();


                    allcompaniessever.add(new Company(nname, nrole, ncgpa, nsalary));
                    for(int i=0;i< allcompaniessever.size();i++){
                        System.out.println(allcompaniessever.get(i).name);
                    }
                    //System.out.println(allcompaniessever);


                } else if (choice3 == 2) {
                    System.out.println("Enter any of these companies");
                    String companyname = scanner.next();
                    Company tempcompany = null;



                    for (Company company : allcompaniessever) {
                        if (company.name.equals(companyname)) {
                            System.out.println("Welcome " + company.name);

                        }
                    }





                    while(true) {
                        for (Company company : allcompaniessever) {
                            if (company.name.equals(companyname)) {
                                tempcompany = company;
                            }
                        }
                        System.out.println("Choose the Company Query to perform-\n 1) Update Company Job Profile\n 2) Update Company Salary\n 3) Update Company CGPA Criteria\n 4) Register to drive\n5) Select students from applied students\n6) Back");
                        int choice4 = scanner.nextInt();
                        if (choice4 == 1) {
                            System.out.println("Enter new Job Profile");
                            String newrole = scanner.next();
                            tempcompany.updateRole(newrole);

                        } else if (choice4 == 2) {
                            System.out.println("Enter new Salary");
                            int newsalary = scanner.nextInt();
                            tempcompany.updatePackage(newsalary);


                        } else if (choice4 == 3) {
                            System.out.println("Enter new CGPA Criteria");
                            double newcgpa = scanner.nextDouble();
                            tempcompany.updateCGCriteria(newcgpa);


                        } else if (choice4 == 4) {
                            System.out.println("Enter registration date amd time");
                            String date = scanner.next();
                            tempcompany.registerToInstituteDrive(placementCell,date);
                        }  else if (choice4 == 5) {
                            tempcompany.offerStudents();
                        }



                        else if (choice4 == 6) {
                            break;
                        }


                    }

                } else if (choice3 == 3) {
                    for (Company company : allcompaniessever) {
                        System.out.println(company.name);



                    }
                } else if (choice3 == 4) {
                    break;
                }


            }

            while (choice2 == 3) {
                System.out.println("Welcome to Placement Cell Mode: Choose the Placement Cell Query to perform-\n 1) Open Student Registration\n 2) Open Company Registration\n 3) Get Number of Registered Students\n 4) Get Number of Registered Companies\n 5) Get Number of Offered/Blocked/Unoffered Students\n 6) Get Student details\n 7) Get Company details\n 8) Get Average Package 9) Get Company process results 10) Back");
                int choice3 = scanner.nextInt();
                if (choice3 == 1) {
                    System.out.println("Enter Student Registration Start Time");
                    String studentregstart = scanner.next();
                    System.out.println("Enter Student Registration End Time");
                    String studentregend = scanner.next();
                    placementCell.openStudentRegistration(studentregstart, studentregend);

                } else if (choice3 == 2) {
                    System.out.println("Enter Company Registration Start Time");
                    String companyregstart = scanner.next();
                    System.out.println("Enter Company Registration End Time");
                    String companyregend = scanner.next();
                    placementCell.openCompanyRegistration(companyregstart, companyregend);

                } else if (choice3 == 3) {
                    placementCell.getNumberRegisteredStudents();

                } else if (choice3 == 4) {
                    placementCell.getNumberRegisteredCompanies();

                } else if (choice3 == 5) {
                    placementCell.getNumberPlacedStudents();
                    placementCell.getNumberBlockedStudents();
                    placementCell.getNumberUnplacedStudents();

                } else if (choice3 == 6) {
                    System.out.println("Enter Student Name");
                    String studentname = scanner.next();
                    System.out.println("Enter Student Roll No.");
                    String studentrollno = scanner.next();
                    placementCell.getStudentDetails(studentname, studentrollno);


                } else if (choice3 == 7) {
                    System.out.println("Enter Company Name");
                    String companyname = scanner.next();
                    placementCell.getCompanyDetails(companyname);


                } else if (choice3 == 8) {
                    placementCell.getAveragePackage();


                } else if (choice3 == 9) {
                    System.out.println("Enter Company Name");
                    String companyname = scanner.next();
                    for (Company company : allcompaniessever) {
                        if (company.name.equals(companyname)) {
                            placementCell.getCompanyProcessResults(company);
                        }
                    }


                } else if (choice3 == 10) {
                    break;
                }
            }
            if (choice2 == 4) {
                break;

            }


        }

        if (choice1==2){
            break;
        }









        }


        System.out.println("Thank You for using FutureBuilder");
    }
}
