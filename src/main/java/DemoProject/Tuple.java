package DemoProject;

public class Tuple {
  private String courseName;
  private Integer courseCode;
  private String courseDescription;

  public Tuple(String str, Integer i,String desc) {
    this.courseName = str;
    this.courseCode = i;
    this.courseDescription = desc;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseCode(String firstStringValue) {
    this.courseName = firstStringValue;
  }

  public Integer getCourseCode() {
    return courseCode;
  }

  public void setCourseName(Integer integerValue) {
    this.courseCode = integerValue;
  }

  public String getCourseDescription() {
    return courseDescription;
  }

  public void setCourseDescription(String courseDescription) {
    this.courseDescription = courseDescription;
  }
}
