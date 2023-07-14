# DockerizedSpringApp
Simple dockerized spring boot app that persists students to a MySQL database.

----------------------------------------------------------------------------------

Hello and thank you for checking out my dockerized Spring-Boot project!

The application was kept very minimal - it only has the POST and DELETE mappings on purpose. the main idea of this project was to showcase,
on a very basic level, the concepts of a RESTful API (in the future I intend making it more robust with more capabilities) combined with
the generation of the app's image and running its containers - one for the app and one for the MySQL db.
students have the following variables: Long Id (auto-generated), String firstName, String lastName, String email, String password.



- Prerequisites:

1. Docker (preferably Docker Desktop).
2. JDK 11
3. Apache Maven.
4. MySQL database.
5. Postman / equivalent tools

* the above are needed in order to run the project in its entirety on your local machine (without dockerizing it).
* though the project contains a .jar file that I've created, you will need Maven to rebuild it after making changes so its not a mandatory
  installation, as long as you wouldn't want to make any changes to the project.



- Important instructions:

  all of the following stages should take place after navigating into the project's directory, for example: Users/Username/desktop/DockerWithSpringApp

1. In case any changes were made to the project and you'd like to rebuild it using the Maven build tool (assuming you've added the M2_HOME
   and PATH variables on your local machine) open your terminal and insert the command 'mvn clean install -DskipTests'. the '-DskipTests'
   flag is HIGHLY IMPORTANT, without adding it the app will terminate upon the app's initialization process and won't work due to hibernate's
   attempts to test the connection to MySQL (during build) - which fail.

3. To create a Docker image of the app (tomcat uses port 8080) insert the command:

       docker build -t <image_name> -f Dockerfile .

   The -f flag specifies which Dockerfile (there may be several, as in our case) Docker should use for this image

5. To create a Docker image of MySQL (uses port 3306) insert the command:

       docker build -t <image_name> -f Dockerfile-mysql .

7. Run the MySQL container first. insert the command:

       docker run -d -p 8080:3306 --name <preferred_container name> <image_name>

8. Now, run the app's container. insert the command:

       docker run -d -p 7777:8080 --name <preferred_container_name> <image_name>

   For stage #8, when inserting the ports (7777:8080), the first port is the gateway to port 8080 from our local machine.

   The choice of the first port is yours - it could be any port you choose as long as it's not already taken.



- Important note:

  When creating a container - it's local host adress will be 127.0.0.1 - same as our local machine's IP address.
  If so, a problem was created: we're using postman from our local machine and we need to find a way to give our app a proper URL
  configuration in the application.properties file. On a local machine it would be (depending on your Spring version):

      spring.datasource.url=jdbc:mysql://localhost:3306/<schema_name>

  but in our case it would not work because the container, as mentioned, has it's own localhost.
  To solve this issue go into the terminal on your local machine and write the following command:
  
      docker inspect \ --format '{{ .NetworkSettings.IPAddress }}' <mysql_container_name>
  
  This command is used to extract the IP address of the given container, in this case the one that contains our MySQL db.
  The next step will be editing the spring.datasource.url line in the app's application.properties file and replacing it with the
  newly obtained IP address (it will usually be 172.17.0.2 or something similar.
  I've already made the change for you prior building the project's .jar file, but it's a step worth doing in order to make sure that
  the project will be able to run without having connectivity issues.


   -- You should be all set and ready to add / remove students from your database - all done by using containers :D --
