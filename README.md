# Syllabus Conversion and Management Application 
## Goal
'Syllabus Conversion and Management Application' documentation welcomes you! The foundation for comprehending the functionality, operational bounds, and user interface of the application is this extensive text. It creates the legal foundation for the creation of a desktop program intended to simplify syllabus modifications. Its main functions are to make it easier for syllabus documents to be converted to JSON format and to give users an organized way to monitor changes along with thorough justifications.    
## Application Details
'Syllabus Conversion and Management Application' makes use of Java, a popular and flexible programming language that ensures dependable performance and cross-platform compatibility. Java's libraries are used for smooth file operations and JSON processing, and JavaFX is selected for its graphical user interface (GUI), which provides a contemporary and responsive user experience. The self-contained program runs just using the Java Runtime Environment (JRE), requiring no external servers or databases. By avoiding complicated setup procedures, this approach guarantees simple deployment on any Windows system that has the required Java installation.
### Application Overview
The program, tailored for educators and school administrators, efficiently converts diverse syllabus data into a unified JSON format for seamless integration with systems like OASIS. It incorporates version control, prompting users to justify modifications, and ensuring transparent academic record-keeping. The import feature facilitates the examination and comparison of stored JSON files, promoting data accessibility and transferability. With its user-friendly design, the program ensures ease of use without requiring specialized training.
## Some Basic Requirements of the App
#### The software shall be able to detect and manage update reasoning, store uploaded syllabus files with a versioning system, and capture/display the date and time of updates
The application utilizes the GSON package to convert syllabus data into a JSON-formatted string. Users select a directory using a DirectoryChooser, and a TextInputDialog allows them to name the JSON file, defaulting to "output" if no name is provided. The application then creates the file path, uploads the JSON data, and generates a SyllabusVersioning object, logging modification details. A separate JSON file stores versioning data, intelligently named based on course code and file count. Success notifications confirm the successful writing of JSON data to files and their locations.
#### The software shall be able to convert syllabus documents to JSON files
The lecture object is serialized by the program into a JSON-formatted string (lectureString) using the Gson package. A DirectoryChooser is used to prompt users to select the location where the JSON file will be saved. The application uses a TextInputDialog to ask users to provide a custom name for the JSON file if a directory is chosen. The default name "output" is used if no name is supplied. The user-specified file name, the directory that has been picked, and the ".json" extension are used to generate the file path. Next, a FileWriter is used to write the JSON data to the designated file. Together with the file path, the application reports a success message confirming that the JSON data was successfully written to the file.
## Classes
* Activity Class:
This class represents an activity object and contains basic information about the activity. Contains numeric values for activity name, count, percentage, and four different learning objectives (LO). It also uses the StringProperty and IntegerProperty properties so that this information can be bound to JavaFX properties
* Competency Class:
This class represents a competency object and contains basic information about the competency. Includes competency description and numerical values for five different levels. It also uses the StringProperty and IntegerProperty properties so that this information can be bound to JavaFX properties.
* EnglishSyllabus Class:
This class creates the view in a sample syllabus pdf using JavaFX components.
![Screenshot_5](https://github.com/cansuarslangiray/SE302Project/assets/105562039/4635e334-a4b3-4021-9996-0797937c18be)
##### Figure 1: EnglishSyllabus screen view
* First Scene Class:
This class represents the starting point of a JavaFX application and visually represents a curriculum management system. The application allows adding Turkish and English curricula, viewing versions, and performing file operations. It also includes a function to display a help window with information about the application via the help menu. The application can load and edit Turkish or English curriculum by reading JSON files. It allows user interaction through menus and buttons and can switch to different screens. This class manages the application's main window and user interactions.
![Screenshot_3](https://github.com/cansuarslangiray/SE302Project/assets/105562039/54ed6b78-c43f-4e7e-b39e-d21f0fb0bfc5)
##### Figure 2: FirstScene screen view
* Lecture Class:
This class represents curriculum information. The class has features that include basic information such as the name of a course, code, period, theoretical and practice hours, local credit, ECTS credit, prerequisites, language, type, course level, delivery mode, teaching methods, coordinator, lecturer, and assistants. It also contains lists representing the weekly topics of the course, an evaluation table, a workload table, and a competency table. The toString method is used to return the content of the class in a readable form. The class also provides access to each property via getter methods. The class has a first-class constructor to convert data in JSON format to this object and convert this object to JSON format.
* Lesson Class:
This class is a Java class that represents the weekly topics and preparatory information of a course. The class has features that include each week's number, that week's topics, and preparation information. It also has getter and setter methods for accessing and assigning values to these properties. It can be used to keep class, weekly subject, and preparation information.
* ListVersion Class:
This class represents a scene used to list different versions in a JavaFX application. The class displays a list of objects derived from the application or the EnglishSyllabus class using a ListView. The user can select from this list and return to the main scene by clicking the "Back to Main Scene" button. The class also includes a method to return the FirstScene class that represents the starting point of the application.

![Screenshot_13](https://github.com/cansuarslangiray/SE302Project/assets/105562039/54a55aa7-1af5-40ee-8ee1-85d365f0cfa2)
##### Figure 2: ListVersion screen view
* SyllabusVersioning Class.
This class is a Java class that represents different versions of the curriculum. Each version has properties that include a name, reason for change, creation time, and information about the corresponding course. The class represents the object in a readable way with the toString method. It also has getter methods that return the name of the version, reason for change, creation time, and information about the relevant course.
* TurkishSyllabus Class:
This class creates the view in a sample syllabus pdf using JavaFX components.
* ViewVersion Class:
This class represents a scene used to display a specific version of a specific lesson in a JavaFX application. After entering the course code and version number, the user can view the details of the relevant version by clicking the "View Syllabus" button. Details displayed include the teacher's name, the reason for the change, creation time, course details, and weekly topics. Additionally, if the user logs in incorrectly, error messages are displayed and the opportunity to return to the main stage is provided with the "Back" button.
![Screenshot_10](https://github.com/cansuarslangiray/SE302Project/assets/105562039/66bdde39-cba3-48ef-9a33-84796158dbd4)
##### Figure 3: ViewVersion screen view
* workLoad Class:
This class is a Java class that represents the workload of a course. It contains details of the studies carried out for the learning activities of the relevant course. Each workload element has attributes that include the name of an activity, the number of occurrences of the activity, the total duration of the activity, and the overall workload of the activity. Setter methods are used to assign values to these properties. The class assigns initial values to these properties with the constructor method.
* Difference Class:
This class represents a Difference object and is used to track value changes in fields within the application. It contains basic information about the change: field name, old value, and new value. The toString method creates a text string describing the change.
* Comparator Class:
This class allows you to compare two different curricula and identify their differences. The Comparator class reads curriculum files in JSON format using the Lecture and Difference classes, detects their differences, and shows these differences to the user.
![Screenshot_14](https://github.com/cansuarslangiray/SE302Project/assets/105562039/44085ffa-a4f4-4a06-8c0c-78a4662a5a9a)
##### Figure 4: Comparator screen view
## Tests
#### Syllabus Comparator
###### Test Scenario 1
In this project, the findDifferences method of the Comparator class is tested. Below is the test scenario and a JUnit test implementation steps containing this scenario.

@Test
    void testFindDifferences() {
        Lecture syllabus1 = new Lecture(/* required parameters */);
        Lecture syllabus2 = new Lecture(/* required parameters */);
        Comparator comparator = new Comparator();
        List<Difference> differences = comparator.findDifferences(syllabus1, syllabus2);
        assertFalse(differences.isEmpty(), "Differences list should not be empty.");
        assertTrue(differences.stream().anyMatch(diff ->
                diff.getField().equals("Course Name") &&
                diff.getOldValue().equals(/* expected old value */) &&
                diff.getNewValue().equals(/* expected new value */)),
                "Expected difference not found in the list.");
    }
}
##### Test Implementation
1. Two different course curricula are created.
2. These two curricula are compared using the Comparator class.
3. Expected differences between the two curricula are identified.
4. As a result of the test, expected differences were found. The system works consistently.
#### Saving a JSON File
###### Test Scenario 2
This project saves data in JSON format to a file after the user fills out a certain form in a JavaFX application. Below is a test scenario and implementation to test this process.
1. @Test
void testSaveJsonFile() {
    TextField courseCodeTextField = lookup("#courseCodeTextField").query();
    courseCodeTextField.setText("CS101");
    //Fill in other form fields
   // Example: lookup("#fieldName").query().setText("Value");
    clickOn("#saveButton");
    assertText("#outputArea", "Saved successfully.");
}
2. @Test
void testSaveJsonFileWithInvalidData() {
    // Example: lookup("#fieldName").query().setText("");
    clickOn("#saveButton");
    assertText("#errorMessage", "Please fill in all fields.");
}
3. @Test
void testSaveJsonFileWhenFileExists() {
    TextField courseCodeTextField = lookup("#courseCodeTextField").query();
    courseCodeTextField.setText("CS101");
    lookup("#fieldName").query().setText("Value");
    clickOn("#saveButton");
    assertText("#errorMessage", "This file already exists. Choose another file name.");
}
4. @Test
void testSaveJsonFileWithoutFileName() {
    TextField courseCodeTextField = lookup("#courseCodeTextField").query();
    courseCodeTextField.setText("CS101");
    lookup("#fieldName").query().setText("Value");
    clickOn("#saveButton");
    assertText("#errorMessage", "Please enter the file name.");
}
##### Test Implementation 1
1. After the user fills out the form completely, he/she clicks the "Save" button.
2. A file selection window opens. This window allows the user to select the folder in which to save the JSON file. The folder is selected.
3. An input window appears for the user to enter the name of the JSON file to save. The user can make the naming he/she wishes, otherwise "output" is used as the default naming.
4. As a result, the system successfully saves a correct JSON file in the specified folder.
###### Test Implementation 2
1. After filling out the form with missing or incorrect information, the user clicks the "Save" button.
2. The system detects errors in the form and displays appropriate error notifications to the user. For example error messages such as "Course Code cannot be left blank", and "Invalid values".
3. In case of any errors, the system has not created any files. The system works consistently.
###### Test Implementation 3
1. The form is filled with complete and accurate information.
2. Click on the "Save" button.
3. While there is a file with the same course code and version number, an attempt is made to save it using the same code and number.
4. The system displays an error message informing the user that the file already exists and that they must select another file name. For example: "This filename is already in use, please select another filename."
5. When the error message is received, no file is created. The system works consistently.
###### Test Implementation 4
1. The form is filled out without entering the file name.
2. A suitable folder is selected.
3. Click on the "Save" button.
4. The system displays a warning message to the user stating that he/she neglected to enter the file name. For example: "Please enter the file name."
5. When the warning message is received, no file is created.
#### Viewing Syllabus
###### Test Scenario 3
In this project, the user is displayed a Syllabus with a specific course code and version number. Additionally, a valid file is opened and the Syllabus information is displayed correctly. Below is a test scenario and implementation to test this process.
@Test
void testViewSyllabus() {
    interact(() -> {
        lookup("#courseCodeField").queryTextInputControl().replaceText("ABC123");
        lookup("#versionNumberField").queryTextInputControl().replaceText("1");
        clickOn("#viewButton");
    });
    assertFileExists("Syllabus/ABC123/ABC1231.json");
    assertText("#outputArea", "Details of Version/ Versiyon Detayları:\n...");
}
##### Test Implementation
1. The "Course Code" and "Version Number" fields are filled in.
2. Click on the "View Syllabus" button.
3. The existence of a file with the specified course code and version number is checked. For example Syllabus/ABC123/ABC1231.json
4. As a result, it has been verified that the Syllabus is displayed correctly. The syllabus' student information, course details, weekly subject plan, and other information are presented to the user completely and accurately.
