package arrays_jf;

/**
 *  The Course class represents a class a student may enroll for at an academic
 *  institution.
 * 
 * @author Jonathan Figueroa
 */
public class Course implements Cloneable {

    private String name;  // name of the course
    
    private String department;  //  school department (Ex. Engineering)
    
    private int code;  //  class number (Ex. 214)
    
    private byte section;  // course section
    
    private String instructor;  // name of instructor
    
   
    
    /**
     * Initializes a Course with a name, department, code, section and instructor
     * 
     * @param name
     *      name of the course
     * @param department
     *      name of the department
     * @param code
     *      class number
     * @param section
     *      course section
     * @param instructor
     *      name of instructor
     */
    
    Course(String name, String department, int code, byte section, String instructor) {
        this.name = name;
        this.department = department;
        this.code = code;
        this.section = section;
        this.instructor = instructor;
    }
    
    Course() {
        
    }

    //  Accesors and Mutators
    /**
     * Returns the name of the Course as a String.
     * 
     * @return the name of the Course as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the Course to the new name provided in the argument
     * @param name 
     *      the new Course name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the department of the Course as a String.
     * @return the department of the Course as a String
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the name of the department to the name passed in as an argument
     * @param department 
     *      the new name of the department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Returns the code of the Course as an integer.
     * 
     * @return the code of the Course as an integer
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the code of the Course to the one passed in as an argument
     * @param code
     *      the new code
     */
    public void setCode(int code) {
        if (code < 0)
            throw new IllegalArgumentException();
        
        this.code = code;
    }

    /**
     * Returns the section of the Course as a byte value.
     * 
     * @return the section of the Course as a byte value
     */
    public byte getSection() {
        return section;
    }

    /**
     * Sets the section of the Course to the one passed in as the argument
     * @param section
     *      the new section
     */
    public void setSection(byte section) {
        if (code < 0)
            throw new IllegalArgumentException();
        
        this.section = section;
    }

    /**
     * Returns the instructor of the Course as a String.
     * @return the instructor of the Course as a String
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Sets the name of the instructor to the name passed in as the argument
     * @param instructor
     *      the new instructor of the Course
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    
    
    
    //  clone() method
    //  for fields which are primitive types, super.clone(), will make an exact copy
    //  of the values.  For fields that are references,
    //  the reference will be copied over, causing both objects to refer to the same object.
    //  This is called a shallow copy.  After getting the object from super.clone(),
    //  you must update all object references so that they will point to different 
    //  objects but have the same values.  Most likely equals() method will return true.  
    //  This is called a deep copy.
    //  Objects that wish to override clone() should implement Cloneable interface or a
    //  CloneNotSupportedException will be thrown.
    
    /**
     * Creates and returns a copy of this object.  Changes to the copy will not
     * affect the original.
     * @return a clone of this object
     * @throws CloneNotSupportedException - if this object does not support 
     * Cloneable interface 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        //  since there are no object references contained inside this Course object
        //  just return super.clone()
        Course course = (Course) super.clone();
        return course;
    }
    
    /**
     * Indicates whether the object argument is equal to this object.
     * 
     * @param obj 
     *      the object that will be compared to this object for equality
     * @return true if this object equals the object passed in as an argument.
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Course course;
        if(obj != null && obj instanceof Course) {
            course = (Course) obj;
            return this.name.equals(course.name)  && 
                    this.department.equals(course.department) &&
                    this.code == course.code && 
                    this.section == course.section &&
                    this.instructor.equals(course.instructor);
        }
        return false;
    }
    
    
    
    
    
}
