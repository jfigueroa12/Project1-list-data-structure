package arrays_jf;

/**
 * The Planner class is a list data structure that stores and organizes Course 
 * objects.  Abstract data type: List.
 * 
 * 
 * @author Jonathan Figueroa
 */
public class Planner implements Cloneable {
    
    final int MAX_COURSES = 50;  //  max quantity that the list can hold

    Course[] cList;  //  array that holds the course objects
    
    int total; // holds the count of the total amount of courses in the list
    
    /**
     *  Creates an empty Planner.
     */
    Planner() {
        cList = new Course[MAX_COURSES];
    }
    
    /**
     * Determines the number of Courses currently in the list.
     * 
     * @return The number of Courses in this planner.
     */
    public int size() {
        return total;
    }
    
    /**
     * Adds a Course to the Planner at the given position.
     * 
     * @param course
     *      The Course to add to the Planner.
     * @param position
     *      The position of this Course in the list.
     * @throws FullPlannerException
     *      If the Planner is already full.
     * @throws IllegalArgumentException
     *      If the position is out of range.
     */
    
    public void addCourse(Course course, int position) throws FullPlannerException,
            IllegalArgumentException {
        
        position -= 1;
        //  System.out.println(position);
        
        if (position < 0 || position >= total + 1) {
            
            throw new IllegalArgumentException("position not within valid range");
        }
        if (total == MAX_COURSES) {
            
            throw new FullPlannerException("The planner is full");
        }
        
        //  add course to the data structure
        //  shifts all elements after "position" to the right by one if you are adding 
        //  the Course somewhwere in the middle of the list. (we are using an array)
        //  If the position you selected is at the end of the list, just append to the list
        if ( position != total + 1) {
            for (int i = total; i > position; i--) {
                cList [i] = cList [i -1];
            }
        }
        
        cList [position] = course;  //  place the course at the specified position
        total++;  //  increase the total to reflect the adding of a course
    }
    
    /**
     * Appends the Course to the end of the list.
     * 
     * @param course 
     *      The Course to be added to the list.
     */
    public void addCourse(Course course) {
        addCourse(course, size() + 1);
    }
    
    /* removeCourse() method
    Note:
    Postcondition - The Course at the desired position has been removed.  All Courses
    that were originally greater than or equal to position are moved backward one
    position. (Ex. If there are 5 Courses in a Planner, positioned 1 - 5 and you remove
    the course in position 4, the item that was in position 5 will be moved to 
    position 4)
    */
    
    /**
     * Removes the Course at the specified position from the Planner.
     * @param position 
     *      The position of the item that will be removed.
     * @throws IllegalArgumentException
     *      If position is out of range
     */
    
    public void removeCourse(int position) throws IllegalArgumentException {
        
        
        if (position < 1 || position > total)
            throw new IllegalArgumentException("position is not within a valid range");
        
        //  if position points to the last element, just erase
        if ( position - 1 == total - 1) {
            cList [total - 1] = null;
            total--;
            
        } else {
            //  shift all elements starting at "position" to the left, overwrite the field
            //  at position - 1 to remove it from the array.
            
            // shift every element down by one to the left
            for ( int i = position; i < total; i++ ) {
                cList [i-1] = cList [i];
            }
            //  erase the element that was previously at the end
            cList [total - 1] = null;
            total--;
        }
    }
    

    /**
     * Retrieve the Course from the specified position but does not remove it 
     * from the list.
     * 
     * @param position
     *      The position of the Course that will be retrieved.
     * @return 
     *      The Course at the specified position.
     */
    
    public Course getCourse(int position) {
        if (position < 1 || position > total)
            throw new IllegalArgumentException("position is not within a valid range");
        
        return cList[position - 1];
    }
    
    /**
     * Displays a neatly formatted table of each course filtered by department 
     * from the Planner.
     * @param planner
     *      The list that will be searched.
     * @param department 
     *      The three letter department code.
     */
    
    public static void filter(Planner planner, String department) {
        //  formatting
        System.out.println(String.format("%-5s%-26s%-12s%-6s%-9s%-11s", "No.", "Course Name", 
                "Department", "Code", "Section", "Instructor"));
        for (int i = 0; i < 75; i++) {
            System.out.print("-");
        }
        System.out.println("");
        
        //  print the relevant info to the screen
        for (int i = 0; i < planner.size(); i++) {
            Course course = planner.getCourse(i + 1);
            if (course.getDepartment().equals(department)) {
                System.out.println(String.format("%-5d%-28s%-11s%-8d%-6s%11s", i + 1, course.getName(),
                        course.getDepartment(), course.getCode(), "0" + course.getSection(), course.getInstructor()));
            }
        }
    }
    
    /**
     * Checks whether a Course is already in the list.
     * @param course
     *      The course we are looking for.
     * @return 
     *      true if the course is in the list, false otherwise.
     */
    
    public boolean exists (Course course) {
        for (int i = 0; i < total; i++) {
            if (cList[i].equals(course))
                return true;
        }
        return false;
    }
    
    /**
     * Creates a copy of this Planner.  Subsequent changes to the copy will not affect
     * the original and vice versa.
     * @return
     *      A copy (backup) of this Planner object.
     * @throws CloneNotSupportedException 
     *      If the object does not support cloning.
     */
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Planner planner = (Planner) super.clone();
        
        // update the mutable fields for a deep copy
        planner.cList = (Course []) cList.clone();
        
        return planner;
    }
    
    /**
     * Prints a neatly formatted table of each Course in the list.
     */
    
    public void printAllCourses() {
        System.out.println(this);
    }
    
    /**
     * Returns: The String representation of this Planner object, which is a
     * neatly formatted table of each Course in the Planner on its own line.
     * @return
     *      The String representation of this Planner.
     */
    
    @Override
    public String toString() {
        
        String one = String.format("%-5s%-26s%-12s%-6s%-9s%-11s%n", "No.", "Course Name", 
                "Department", "Code", "Section", "Instructor");
        String two = "";
        for (int i = 0; i < 75; i++) {
            two += "-";
        }
        two += "\n";
        
        String three = "";
        for (int i = 0; i < size(); i++) {
            Course course = cList[i];
            three += String.format("%-5d%-28s%-11s%-8d%-6s%11s%n", i + 1, course.getName(),
                        course.getDepartment(), course.getCode(), "0" + course.getSection(), course.getInstructor());
        }
        
        return one + two + three;
    }

    //  Done implementing Planner class
}
