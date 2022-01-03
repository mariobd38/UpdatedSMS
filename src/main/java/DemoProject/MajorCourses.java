package DemoProject;


import java.util.ArrayList;
import java.util.List;

public class MajorCourses {
  private List<Tuple> courses;
  public MajorCourses(String major) {
    switch(major) {
      case "CS":
        courses = getCSCourses();
        break;
      case "BIO":
        courses = getBIOCourses();
        break;
      case "BUS":
        courses = getBUSCourses();
        break;
      case "PHY":
        courses = getPHYCourses();
        break;
      default:
        break;
    }
  }

  public List<Tuple> getCourses() {
    return courses;
  }
  public ArrayList<Tuple> getCSCourses() {
    return new ArrayList<>() {{
      add(new Tuple("Introduction to Programming",100,"Computing methodology, process, and computational problem solving. Algorithm Design; program design, development, and testing."));
      add(new Tuple("Data Structures & Algorithms",200,"Representations and operations on basic data structures. Arrays, linked lists, stacks, queues, and recursion; binary search trees and balanced trees; hash tables."));
      add(new Tuple("Computer Architecture",300,"Logic gates, combinational circuits, sequential circuits, memory and bus system, control unit, CPU, exception processing, traps and interrupts, input-output and communication."));
      add(new Tuple("Software Engineering",400,"Theory and methodology of programming complex computer software. Analysis, design, and implementation of programs."));
      add(new Tuple("Machine Learning",500,"Algorithms and computer methods for machine learning. Supervised methods: neural networks, linear regression, logistic regression; unsupervised methods: dimensionality reduction, subspace learning."));
    }};
  }
  public ArrayList<Tuple> getBIOCourses() {
    return new ArrayList<>() {{
      add(new Tuple("General Biology",100,"\tA beginning course in biology stressing processes common to living organisms."));
      add(new Tuple("Human Anatomy",200,"Gross and microscopic anatomy of organ system of human body."));
      add(new Tuple("Genetics & Evolution",300,"Faulty genes, genetic variation, human disorders. Big data, molecular, and transmission genetics. A brief look at the impact on human evolution"));
      add(new Tuple("Plant Biology",400,"Cell biology and structure, photosynthesis, respiration, secondary metabolism, physiology of water relations and transport, growth and development, evolution of major groups"));
      add(new Tuple("Animal Physiology",500,"Physiology of vertebrate and invertebrate animals with emphasis on diversity of solutions to physiological problems and on functional integration of organ systems."));
    }};
  }
  public ArrayList<Tuple> getBUSCourses() {
    return new ArrayList<>() {{
      add(new Tuple("Exploration of Business",100,"Business careers and business education. Skills needed, opportunities, and options withn various occupations. Study and interpersonal skills for academic and personal success."));
      add(new Tuple("Ethics of Business",200,"Theoretical concepts of ethics in business decisions. Ethics of decision alternatives using different approaches, with application of an integrative ethical decision model"));
      add(new Tuple("Fundamentals of Finance",300,"Objectives of financial management. Financing the business enterprise. Introduction to the cost of capital, valuation, international finance, and the techniques of present value and its applications."));
      add(new Tuple("Marketing",400,"Function of marketing in organizations and society. Strategic marketing planning in domestic and global settings to include marketing concepts, consumer behavior, market research"));
      add(new Tuple("Financial Management",500,"Explore role of finance in a shareholder value based framework. Financial analysis and planning, investment, capital structure, financial markets, capital raising and capital disbursement decisions"));

    }};
  }
  public ArrayList<Tuple> getPHYCourses() {
    return new ArrayList<>() {{
      add(new Tuple("Fundamentals of Physics",100,"Fundamental principles of physics in areas of mechanics and oscillatory motion. Designed for students requiring calculus-based physics."));
      add(new Tuple("Modern Physics",200,"Special theory of relativity. Particle properties of electromagnetic radiation, and wave properties of particles. Introduction to quantum theory with applications to atomic structure."));
      add(new Tuple("Quantum Mechanics",300,"Mathematical and physical foundations of quantum theory in terms of wave and matrix mechanics. Applications to properties of atoms and solids."));
      add(new Tuple("Relativity",400,"Relative coordinates, Lorentz transformation, covariant formation of the laws of physics, applications of special relativity, introduction to curved space time, cosmology."));
      add(new Tuple("Electromagnetic Theory",500,"Electrostatics, magnetic induction, and magnetostatics, Maxwell's equations, electromagnetic waves and radiation, fields in macroscopic media, special relativity."));
    }};
  }
}
