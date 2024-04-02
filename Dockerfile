FROM openjdk:17
ADD target/student-0.0.1-SNAPSHOT.jar student-doc.jar
ENTRYPOINT ["java", "-jar", "/student-doc.jar"]
