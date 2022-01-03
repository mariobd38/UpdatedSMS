package DemoProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllCourses {
  private final int courseID;
  private final MajorCourses majorCourses;
  private final String courseTeaching;
  private final List<String> weekdaysList;
  public AllCourses(String courseTeaching, String[] weekdays) {
    this.courseTeaching = courseTeaching;
    courseID = this.courseTeaching.hashCode();
    majorCourses = new MajorCourses(this.courseTeaching);
    weekdaysList = new ArrayList<>(Arrays.asList(weekdays).subList(2, weekdays.length-1));
  }
  public ObservableList<ClassEntry> getAllCourses() {
    return FXCollections.observableArrayList(
      new ClassEntry(Integer.parseInt(courseID + "1"), courseTeaching + "-" + majorCourses.getCourses().get(0).getCourseCode(),
              majorCourses.getCourses().get(0).getCourseName(),
              10,0, 11, 15,
              new ArrayList<>() {{
        add(weekdaysList.get(0));
        add(weekdaysList.get(2));
        add(weekdaysList.get(4));
      }},majorCourses.getCourses().get(0).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "2"),courseTeaching + "-" + majorCourses.getCourses().get(0).getCourseCode(),
              majorCourses.getCourses().get(0).getCourseName(),
              12,0, 13, 15,
              new ArrayList<>() {{
        add(weekdaysList.get(0));
        add(weekdaysList.get(2));
        add(weekdaysList.get(4));
      }},majorCourses.getCourses().get(0).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "3"),courseTeaching + "-" + majorCourses.getCourses().get(0).getCourseCode(),
              majorCourses.getCourses().get(0).getCourseName(),
              13,0, 14, 15,
              new ArrayList<>() {{
        add(weekdaysList.get(1));
        add(weekdaysList.get(3));
      }},majorCourses.getCourses().get(0).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "4"),courseTeaching + "-" + majorCourses.getCourses().get(1).getCourseCode(),
              majorCourses.getCourses().get(1).getCourseName(),
              11, 30, 12, 45,
              new ArrayList<>() {{
        add(weekdaysList.get(0));
        add(weekdaysList.get(2));
        add(weekdaysList.get(4));
      }},majorCourses.getCourses().get(1).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "5"),courseTeaching + "-" + majorCourses.getCourses().get(1).getCourseCode(),
              majorCourses.getCourses().get(1).getCourseName(),
              16, 0, 17, 15,
              new ArrayList<>() {{
        add(weekdaysList.get(1));
        add(weekdaysList.get(3));
      }},majorCourses.getCourses().get(1).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "6"),courseTeaching + "-" + majorCourses.getCourses().get(2).getCourseCode(),
              majorCourses.getCourses().get(2).getCourseName(),
              16, 0, 17, 15,
              new ArrayList<>() {{
        add(weekdaysList.get(1));
        add(weekdaysList.get(3));
      }},majorCourses.getCourses().get(2).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "7"),courseTeaching + "-" + majorCourses.getCourses().get(2).getCourseCode(),
              majorCourses.getCourses().get(2).getCourseName(),
              17, 0, 18, 15,
              new ArrayList<>() {{
        add(weekdaysList.get(0));
        add(weekdaysList.get(2));
      }},majorCourses.getCourses().get(2).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "8"),courseTeaching + "-" + majorCourses.getCourses().get(3).getCourseCode(),
              majorCourses.getCourses().get(3).getCourseName(),
              10, 0, 11, 15,
              new ArrayList<>() {{
        add(weekdaysList.get(2));
        add(weekdaysList.get(4));
      }},majorCourses.getCourses().get(3).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "9"),courseTeaching + "-" + majorCourses.getCourses().get(3).getCourseCode(),
              majorCourses.getCourses().get(3).getCourseName(),
              16, 0, 17, 15,
              new ArrayList<>() {{
        add(weekdaysList.get(2));
        add(weekdaysList.get(4));
      }},majorCourses.getCourses().get(3).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "10"),courseTeaching + "-" + majorCourses.getCourses().get(4).getCourseCode(),
              majorCourses.getCourses().get(4).getCourseName(),
              8, 0, 9, 15,
              new ArrayList<>() {{
        add(weekdaysList.get(1));
        add(weekdaysList.get(3));
      }},majorCourses.getCourses().get(4).getCourseDescription()),
      new ClassEntry(Integer.parseInt(courseID + "11"),courseTeaching + "-" + majorCourses.getCourses().get(4).getCourseCode(),
              majorCourses.getCourses().get(4).getCourseName(),
              13, 30, 14, 45,
              new ArrayList<>() {{
        add(weekdaysList.get(0));
        add(weekdaysList.get(2));
      }},majorCourses.getCourses().get(4).getCourseDescription())
    );
  }
}
