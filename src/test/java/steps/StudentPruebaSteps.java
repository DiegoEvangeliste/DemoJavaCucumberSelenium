package steps;

import com.devangeliste.testDemo.model.entity.Student;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class StudentPruebaSteps {

    private final Student student = new Student();

    @Given("^That the student (.*) is given a task to clear (.*) certification exam$")
    public void certificationName(String name, String certification) throws Throwable {
        student.setName(name);
        student.setCertification(certification);
    }

    @Given("^the student (.*) presented himself to take the (.*) certification$")
    public void certificationName2(String name, String certification) throws Throwable {
        student.setName(name);
        student.setCertification(certification);
    }

    @When("^(.*) got (\\d+) marks in exam$")
    public void gotMarks(String name, int marks) throws Throwable {
        student.setName(name);
        student.setMarks(marks);
    }

    @When("^(.+) passed in (\\d+) attempts or less$")
    public void vinodPassedInAttemptsOrLess(String name, int number) {
        student.setName(name);
        int attempts = student.getTechnologyNotes().get(student.getCertification()).size();

        student.setMarks(student.getTechnologyNotes().get(student.getCertification()).get(--attempts));

        assertTrue(attempts <= number);
    }

    @Then("^(.*) is known as (.*) certified$")
    public void certifiedYes(String name, String certification) throws Throwable {
        assertEquals(name, student.getName());
        assertEquals(certification, student.getCertification());
        assertTrue(student.getCertificationResult());
    }

}
