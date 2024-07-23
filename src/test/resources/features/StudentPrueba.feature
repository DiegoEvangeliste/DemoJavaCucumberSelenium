@All
@Student
Feature: Certification Exam
  A student is going to take a programming certification

  @Prueba_1
  Scenario: Student is passed
    Given That the student Vinod is given a task to clear Java certification exam
    When Vinod got 90 marks in exam
    Then Vinod is known as Java certified

  @Prueba_2
  Scenario: The student graduated as regular
    Given the student Vinod presented himself to take the Java certification
    When Vinod passed in 3 attempts or less
    Then Vinod is known as Java certified