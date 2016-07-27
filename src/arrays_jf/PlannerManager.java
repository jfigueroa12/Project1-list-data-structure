package arrays_jf;

import java.util.*;

/**
 * The client which interacts with the user and processes user input.
 * 
 * @author flyasth3sky
 */

public class PlannerManager {

    public static void main(String[] args) {
        
        Planner planner = new Planner();
        
        Planner backupPlan = null;
        
        String input = "";
        
        Scanner scanner = new Scanner(System.in);
        
        
        // program starts here
        while (!input.equals("Q")) {
            
            mainMenu();  //  show main menu
            
            
            System.out.print("Enter a selection: ");
            String temp = scanner.nextLine();
            input = temp.toUpperCase();
            
            switch (input) {
                case "A": //  add a Course to the list
                    
                    try {
                        
                        //  get user input
                        System.out.print("\nEnter course name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter department: ");
                        String department = scanner.nextLine().toUpperCase();
                        System.out.print("Enter course code: ");
                        String strCode = scanner.nextLine();
                        int code = Integer.parseInt(strCode);
                        System.out.print("Enter course section: ");
                        String strSection = scanner.nextLine();
                        byte section = Byte.parseByte(strSection);
                        System.out.print("Enter instructor: ");
                        String instructor = scanner.nextLine();
                        System.out.print("Enter position: ");
                        String strPos = scanner.nextLine();
                        int pos = Integer.parseInt(strPos);
                        
                        Course course = new Course(name, department, code, section, instructor);
                        planner.addCourse(course, pos);
                        
                        System.out.printf("%n%s %d.%02d"
                                + " was succesfully added to the planner.%n%n", department, code, section);
                        break;
                        
                    } catch (NumberFormatException n) {
                        System.out.println("\nInvalid value, returning to the main menu\n");
                        break;
                    } catch(FullPlannerException | IllegalArgumentException fpe) {
                        System.out.println(fpe.getMessage());
                        System.out.println("\nReturning to the main menu\n");
                        break;
                    }
                
                case "G": //  Get course from the list
                    
                    System.out.print("\nEnter position: ");
                    int pos = scanner.nextInt();
                    scanner.nextLine();
                    
                    Course course = planner.getCourse(pos);
                    
                    //  print the Course info to the screen
                    String one = String.format("%-5s%-26s%-12s%-6s%-9s%-11s%n", "No.", "Course Name", 
                        "Department", "Code", "Section", "Instructor");
                    String two = "";
                    for (int i = 0; i < 75; i++) {
                        two += "-";
                    }
                    two += "\n";
                    
                    String three = "";
        
            
                    three += String.format("%-5d%-28s%-11s%-8d%-6s%-11s%n", pos, course.getName(),
                        course.getDepartment(), course.getCode(), "0" + course.getSection(), course.getInstructor());
        
                    System.out.println(one + two + three);
                    break;
                
                case "R":  //  Remove a Course 
                    
                    System.out.print("\nEnter position: ");
                    pos = scanner.nextInt();
                    scanner.nextLine();
                    course = planner.getCourse(pos);
                    String department = course.getDepartment();
                    int code = course.getCode();
                    byte section = course.getSection();
                    
                    try {
                        planner.removeCourse(pos);
                        
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                        System.out.println("\nReturning to the main menu");
                        break;
                    }
                    System.out.printf("%n%s %d.%02d"
                                + " was succesfully removed from the planner.%n%n", 
                            department, code, section);
                    break;
                    
                case "P":  //  Print all the courses in the Planner 
                    
                    planner.printAllCourses();
                    break;
                
                case "F": //  Print the Courses filtered by department
                    
                    System.out.print("\nEnter department code: ");
                    String str = scanner.nextLine().toUpperCase();
                    
                    Planner.filter(planner, str);
                    break;
                    
                case "L": //  Check if a Course is already in the list
                    try {
                        System.out.print("\nEnter course name: ");
                    
                        String name = scanner.nextLine();
                        System.out.print("Enter department: ");
                        department = scanner.nextLine().toUpperCase();
                        System.out.print("Enter course code: ");
                        String strCode = scanner.nextLine();
                        code = Integer.parseInt(strCode);
                        System.out.print("Enter course section: ");
                        String strSection = scanner.nextLine();
                        section = Byte.parseByte(strSection);
                        System.out.print("Enter instructor: ");
                        String instructor = scanner.nextLine();
                        
                        course = new Course(name, department, code, section, instructor);
                        planner.exists(course);
                        
                    } catch (NumberFormatException nfe) {
                        System.out.println("\nInvalid value, returning to the main menu\n");
                        break;
                    }
                    break;
                
                case "S": //  Print the total amount of Courses in the list
                    
                    System.out.printf("%nThere are %d courses in the planner.%n%n", planner.size());
                    break;
                    
                case "B":  //  Create a backup of the Planner
                    try {
                        backupPlan = (Planner) planner.clone();
                    } catch (CloneNotSupportedException cnse) {
                        System.out.println("Backup failed.\n");
                    }
                    System.out.println("\nCreated a backup of the current planner.\n");
                    break;
                    
                case "PB": //  Print the Courses in the backup Planner
                    
                    System.out.println("");
                    if (backupPlan != null) {
                        backupPlan.printAllCourses();
                    } else {
                        System.out.println("No backup exists yet.\n");
                        
                    }
                    break;
                    
                case "RB": //  Revert to the backup Planner
                    if (backupPlan != null) {
                        planner = backupPlan;
                        try {
                            backupPlan = (Planner) planner.clone();
                        } catch (CloneNotSupportedException cnse) {
                            System.out.println("Error during revert to backup.\n");
                        }
                        System.out.println("\nPlanner successfully reverted to the backup copy.\n");
                    } else {
                        System.out.println("Backup copy does not exist yet.\n");
                    }
                    break;
                    
                
            }
            
        }
        
        System.out.println("\nProgram terminating successfully...\n");
    }
    
    

    /**
     * Prints out the main menu, showing the options the user can choose.
     */
    public static void mainMenu() {
        String menu = "(A) Add Course\n(G) Get Course\n(R) Remove Course\n" 
                + "(P) Print Courses in Planner\n(F) Filter by Department Code\n"
                + "(L) Look For Course\n(S) Size\n(B) Backup\n(PB) Print Courses in Backup\n"
                + "(RB) Revert to Backup\n(Q) Quit\n";
        System.out.println(menu);
    }
}
