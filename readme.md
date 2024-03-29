
In this progressive project, we are going to model a lab essay management database.

### 26/12/2023
Add MyBatis DAOs to the existing hierarchy with the same requirements. Choose any XML or interface mapping.
Switch service classes to MyBatis.

* All added all necessary files to configure myBatis, these were:
    * BatchInfoMapper.xml
    * EmployeeEmployeeLaboratoryToolsMapper.xml
    * EmployeeEmployeeWorkAreasMapper.xml
    * EmployeeMapper.xml
    * EmployeeWorkAreaMapper.xml
    * EnergyEfficiencyEssayMapper.xml
    * EquipmentForTestModelMapper.xml
    * EssayModuleMapper.xml
    * GasConsumptionEssayMapper.xml
    * LaboratoryToolMapper.xml
    * LabTestReportMapper.xml
    * TemperatureEssayMapper.xml
    * myBatis-config.xml

In the Factory class now it's possible to access to each implementation myBatis or jdbc.
Now the framework enum is configured in "myBatis"


### 19/12/2023
Build hierarchy for Schema from the below course.

Create DAO classes with necessary interfaces, abstract classes, and Generics.

DAO should be scalable and flexible to support another framework and another database as well. All CRUD operations should be supported using JDBC. Use connection pool from the below block.
Implement Service layer with necessary abstraction to be able to switch between databases and frameworks.

The packages:
* Domain: that contains the 12 classes that represent the tables for the mySQL database:
* BatchInfo.java ,Employee.java, EmployeeEmployeeWorkAreas.java, EmployeeLaboratoryTools.java,
  EmployeeWorkArea.java, EnergyEfficiencyEssay.java,
  EquipmentForTestModel.java, EssayModule.java, GasConsumptionEssay.java,
  LaboratoryTool.java, LabTestReport.java, TemperatureEssay.java.
* Persistence: that contains the DAO classes for each class with the CRUD operations for each class.
  It also contains the AbstractDao and the abstract interface InterfaceGenericDao.
* Service that contains the service for each class.

The ConnectionPool clas is implemented for each method in the abstract class. The source of this file is in:
src/main/java/com/solvd/essay/persistence/ConnectionPool.java

To achieve the abstraction to switch between databases, we can find the config.properties in which we can change the
information about our database

To achieve the abstraction to switch between frameworks, there were created the enum Framework in the service package.
In this enum the implementation can be changed .Also, was created the Factory class that enables us to get access to
the framework used.
In this case, it only contains the jdbc implementation.

### 14/12/2023

Exercises

* 10 statements for insertion.
* 10 statements for updating.
* 10 statements for deletions.
* 5 alter table.
* 1 big statement to join all tables in the database.
* 5 statements with left, right, inner, outer joins.
* 7 statements with aggregate functions and group by and without having.
* 7 statements with aggregate functions and group by and with having.

There were added all the queries in the essay project folder. Each file
has the name of the asked queries.


### 12/12/2024

Create a database schema using Mysql Workbench for the
new hierarchy with at least 12 tables and all relations.
The schema should satisfy the 3 Normal Forms.

The schema was created and satisfy the 3 normal forms.
The tables created were:
* batch_info: it contains the information of the batch
* employee: it has the information of every employee
* equipment_for_test_model: the model of the equipment tested and it info
* essay_module: the module in wich the equipment is tested
* laboratory_tool: the tools used
* employee_work_area: the areas of works of the employees
* lab_test_report: the unique information of each essay
* temperature_essay: the temperature essay results
* gas_consumption_essay: the gas consumption essay results
* energy_efficiency_essay: the energy efficiency essay results
* employee_employee_work_areas: an intermediate table that joins
  the employees with their different working areas
* employee_laboratory_tools: an intermediate table that joins
  the employees with the different tools that use

