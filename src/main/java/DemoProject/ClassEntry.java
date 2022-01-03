package DemoProject;

import java.util.Arrays;
import java.util.List;

public class ClassEntry {
  private int courseID;
  private String course;
  private String courseTitle;
  private String courseStartTime;
  private String courseEndTime;
  private String courseTime;
  private String courseDescription;
  private List<String> courseDays;
  public ClassEntry(int courseID,String course, String courseTitle, int courseStartHour, int courStartMinutes, int courseEndHour,
                    int courseEndMinutes, List<String> courseDays,String courseDescription) {
    this.courseID = courseID;
    this.course = course;
    this.courseTitle = courseTitle;
    setCourseStartTime(courseStartHour, courStartMinutes);
    this.courseStartTime = getCourseStartTime();
    setCourseEndTime(courseEndHour, courseEndMinutes);
    this.courseEndTime = getCourseEndTime();
    this.courseTime = getCourseTime();
    setCourseTime(courseStartTime,courseEndTime);
    this.courseDays = courseDays;
    this.courseDescription = courseDescription;
  }

  public int getCourseID() {
    return courseID;
  }

  public void setCourseID(int courseID) {
    this.courseID = courseID;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getCourseTitle() {
    return courseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    this.courseTitle = courseTitle;
  }

  public String getCourseStartTime() {
    return courseStartTime;
  }

  public void setCourseStartTime(int hours, int min) {
    this.courseStartTime = convertDate(hours) + ":" + convertDate(min);
  }

  public String getCourseEndTime() {
    return courseEndTime;
  }

  public void setCourseEndTime(int hours, int min) {
    this.courseEndTime = convertDate(hours) + ":" + convertDate(min);
  }

  public String getCourseTime() {
    return courseTime;
  }

  public void setCourseTime(String courseStartTime, String courseEndTime) {
    this.courseTime = this.courseStartTime + " - " + this.courseEndTime;
  }

  public String convertDate(int input) {
    return (input >= 10) ? String.valueOf(input) : "0" + String.valueOf(input);
  }

  public void setCourseDays(List<String> courseDays) {
    this.courseDays = courseDays;
  }

  public String getCourseDays() {
//    return courseDays;
    return Arrays.toString(courseDays.toArray()).replace("[", "").replace("]", "");
  }
  public void setCourseDescription(String courseDescription) {
    this.courseDescription = courseDescription;
  }
  public String getCourseDescription() {
    return courseDescription;
  }
}

