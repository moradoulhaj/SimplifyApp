# Use an official Tomcat base image
FROM tomcat:9-jdk11-openjdk

# Set the working directory to the Tomcat webapps directory
WORKDIR /usr/local/tomcat/webapps

# Copy the WAR file from the target directory into the Tomcat webapps directory
COPY target/Simplify-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080 to the outside world
EXPOSE 8080

# Start Tomcat when the container runs
CMD ["catalina.sh", "run"]
