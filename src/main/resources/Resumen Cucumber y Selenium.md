# Cucumber
Es un framework que nos permite implementar BBD, esta basado en un lenguaje algoritmo llamado *Gherkin* (es un lenguaje semantico para entender las funcionalidades que se van a implementar en desarrollo). **Cucumber** permite implementar los test es escritos en el lenguaje Gherkin y nos facilita testear/comprobar que se cumplan los criterios de aceptacion de las historias de usuario.

Historia de usuario escrita en *Gherkin*:
- *Feature*: El usuario quiere agregar una pelicula manualmente.
- *Scenario*: Agregar una pelicula.
- *Steps*:
    - *Given*: estoy en la pagina principal.
        - *When*: hago click en "Add new movie".
          - *Then*: soy redireccionado a la pantalla para agregar una pelicula.
        - *When*: completo el nombre de la pelicula, agrego la categoria de la pelicula y hago click en "Save Changes".
           - *Then*: Soy redireccionado a la pagina principal y debo poder ver la pelicula que acabo de agregar.
  
Hay que tener en cuenta que para lograr utilizar *Cucumber* correctamente, no debemos acoplar las features; por lo que se recomiendo tener 1 unica feature por historia de usuario, ya que:
- evitaremos problemas a la hora de enfocar la funcionaliad de nuestro negocio.
- se hará mas facil y legible el test.
- no da la posibilidad de concatenar features en un futuro pero sin añadirlas.


## Dependencias

    <!-- Cucumber -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>${cucumber.version}</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-spring</artifactId>
        <version>${cucumber.version}</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>${cucumber.version}</version>
        <scope>test</scope>
    </dependency>


## Steps
*Cucumber* posee 3 pasos:
- **Given**: representan el estado del mundo antes de un evento (condiciones previas).
- **When**: representan el evento (ej: pulsar un boton).
- **Then**: representan las expectativas (lo que hay que comprobar si es verdad).

Ademas con **And** y **But** podemos extender los pasos entre sí.

Para poder utilizar *Cucumber* para los tests, debemos tener los siguientes tipos de archivos claves:
- Archivo **.java**:
  - *Clase Test general **"Runner.java"***: esta clase nos va a permitir testear las features, debemos utilziar las siguientes anotaciones para que todo funcione:
    - `@RunWith(Cucumber.class)`: le indicamos a la clase de test que reconozca los archivos *.feature* escritos en Gherkins.
    - `@CucumberOptions(features = "src/test/resources", glue = "src/test/java/steps", plugin = { "pretty", "html:target/cucumber-reports" })`:
      - **"features = ···"**: indicamos donde se guardan todos los archivos *.feature*.
      - **"glue = ···"**: indicamos donde se guardan los steps escritos en *.java*.
      - **"plugin = ···"**: indicamos donde se guarda un informe de la prueba que se realiza.
  - *Clase de los Steps*: en estos archivos vamos a describir en codigo java el comportamiento para cada feature creada....
- Archivos **.feature**: en estos archivos vamos a transcribir con Gherkins las historias de usuario indicando su nombre, descripcion, rol del usuario, escenarios, ejemplos, y todo lo que se considere necesario.


### Scenarios
Cuando dentro de los **Scnearios** queremos agrear una tabla de datos de ejemplo, a estos le debemos agregar una palabra clave extra, esta es **Outline**; de esta manera le vamos a indicar a *Cucumber* que el siguiente scenario posee una tabla con datos de ejemplo. El uso de esta tabla es simple, donde queramos usar el dato de ejemplo debemos poner `<nombre_encabezado>` y despues de describir la tarea utilizamos la anotacion **Examples** y armamos la tabla, comenzando con el *nombre del encabezado* y debajo de el los datos de prueba (puede haber mas de una columna); por ejemplo:

    Feature: Navigation bar
        To see the subpages
        Without logging in
        I can click the navigation bar links
     
        Scenario Outline: I can access the subpages through the navigation bar
            Given I navigate to www.freerangetesters.com
            When I go to <section> using the navigation bar
            Examples:
                | section    |
                | Cursos     |
                | Recursos   |


### Comodines
Podemos generalizar las anotaciones **GIVEN/WHEN/THEN** y los **STEPS** con el uso de **"comodines"** (va a tomar las variables en el orden que se dispongan los comodines en la oracion dentro de la anotacion); esto se puede hacer de dos maneras:
- Comodines generales:
  - **(.*)**: va a tomar cualquier valor.
  - **(.+)**: va a tomar cualquier valor de tipo String.
  - **(\\d+)**: va a tomar cualquier digito (no se permiten 0 digitos).
- Comodines puntuales:
  - **(?:Opcion 1|Opcion 2|Opciones···)**: solo va a funcionar si coinciden las opciones indicadas.

Debemos tener en cuanta lo siguiente, cuando vamos a querer generalizar, debemos en la definicion de las anotaciones dentro de los *STEPS* poner al principio el siguiente simbolo **^** y al final el siguiente **$**; ademas si usamos comodines puntuales, debemos indicar dentro de los parentesis al principio lo siguiente **?:** o si por ejemplo tenemos `(?:select|selects)` podemos simplificarlo en `selects?`. De esta manera le vamos a indicar a *CUCUMBER* donde comienza, termina y utilizamos los distintios tipos de comodines.

### TAGS
Suele pasar que una misma *feature* mas de 1 escenario distinto, para poder identificar correctamente cada uno de estos podemos hacer uso de **TAGS** (estos tambien se pueden utilizar para identificar features, tablas de ejemplo, etc). Los tasgs los debemos poner antes de la definicion del bloque, por ejemplo:

    @Tag_feature
    Feature: nombre Feature
        Descripcion de la Feature

        @Tag_Scenario_1
        Scenario Outline 1: Descripcion del Scenario
            Given:
            Then:
            When:

            Examples:
                |ejemplo 1|
                |ejemplo 2|
                |ejemplo 3|
        
        @Tag_Scenario_2
        Scenario 2: Descripcion del Scenario
            Given:
            Then:
            When:

De esta manera si queremos podemos indicar que tags correr y cuales no correr; a su vez tambien podemos utilizar conectores logicos AND y OR.
Otro detalle es que en la clase general de test, en `@CucumberOption(···)` podemos agregar un tag por defecto `tags = "@Tag_por_defecto"`, el cual va a ejecutar en el caso que no encuentre el/los tags que se indiquen para correr.

### Backgrouds
Otro recurso que podemos utilizar para optimizar las pruebas son los **BACKGROUNDS**, estos nos permiten definir steps previos y comunes a varios scenarios (estos steps se van a inicializar/ejecutar) antes de cada scenario:

    Feature: nombre Feature
        Descripcion de la Feature

        Background: Descripcion del background
            Given:

        Scenario 1: Descripcion del Scenario
            Then:
            When:

        Scenario 2: Descripcion del Scenario
            Then:
            When:

---

## Ejemplos

### Ejemplo simple 1
Las historias de usuario escritas en Gherkins las debemos guardar con la extension **'.feature'**.

**User.feature**:

    Feature: Certification Exam
        A student is going to take a programming certification
    
        Scenario: User is passed
            Given That the user Vinod is given a task to clear Java certification exam
            When Vinod got 90 marks in exam
            Then Vinod is known as Java certified

**Runner.java**:

    @RunWith(Cucumber.class)
    @CucumberOptions(features = "src/test/resources", glue = "src/test/java/steps", plugin = { "pretty", "html:target/cucumber-reports" })
    public class Runner { }

Debemos indicarle al test con la anotacion `@RunWith(Cucumber.class)` para que utilice **Cucumber** y lea los archivos **.feature**.

**UserSteps.java**:

    public class UserSteps {
        private User user = new User();

        @Given("^That the user (.*) is given a task to clear (.*) certification exam$")
        public void certificationName(String name, String certification) throws Throwable {
            user.setName(name);
            user.setCertification(certification);
        }

        @When("^(.*) got (\\d+) marks in exam$")
        public void gotMarks(String name, int marks) throws Throwable {
            user.setName(name);
            user.setMarks(marks);
        }

        @Then("^(.*) is known as (.*) certified$")
        public void certifiedYes(String name, String certification) throws Throwable {
            assertThat(name, is(user.getName()));
            assertThat(user.getCertification(), equalsTo("Java"));
            assertThat(user.getResult(), is(true));
        }
    }

---
---

# Selenium
Es un conjunto de herramientas Open-source para la automatizacion de pruebas funcionales sobre aplicacicones web. Una de las herramientas que mas se utiliza es **Selenium WebDriver**, que es una libreria para automatizar navegadores; uno de sus principales objetivos es testear aplicaciones web. Esta herramienta nos permite interactuar con las distintas paginas webs.

Se recomienda que para aplicaciones de mediano y gran tamaño, al realizar este tipo de tests, se nos va a facilitaria aplicar el patron de **POM (Page Object Model)**, el mismo consiste en crear un "repositorio" de objetos para almacenar todos los elementos web; esto ayuda a reducir la duplicación de código y mejora el mantenimiento de los casos de prueba.

Otro tip al momento de aplicar el patron *POM* es hacer una clase general **("BasePage")** donde vamos a definir funcionalidades y objetos en comun de la estructura de la pagina web; las funcionalidades aqui definidas tambien nos van a permitir un mejor manejo de la instancia creada de la pagina. Una vez hecho esta pagina base (clase general), las paginas puntuales (clases) extiende esta esta.

Dentro de la clase **Runner** podemos definir que despues de cada test se cierre/termine la instancia del navegador que se abrió; esto lo podemos hacer ya que esta es la clase que vamos a ejecutar para comprobar/testear todas las ventanas, features y steps.

## Dependencias

<!-- Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>${selenium.version}</version>
    </dependency>
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>${webdrivermanager.version}</version>
    </dependency>