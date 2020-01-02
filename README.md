# p6
p6 project

here is the process to deploy. 

Tomcat manager Tool is used to deploy this web-app

The Tomcat Apache web server is free software that can be downloaded from their website. It is required that there is a JDK available on the user's machine and that the JAVA_HOME environment variable is set correctly.

1. Start Tomcat
We can start the Tomcat server by simply running the startup script located at $CATALINA_HOME\bin\startup. There is a .bat and a .sh in every installation.

Choose the appropriate option depending on whether you are using a Windows or Unix based operating system.

2. Configure Roles
During the deployment phase, we'll have some options, one of which is to use Tomcat's management dashboard. To access this dashboard, we must have an admin user configured with the appropriate roles.

To have access to the dashboard the admin user needs the manager-gui role. Later, we will need to deploy a WAR file using Maven, for this, we need the manager-script role too.

Let's make these changes in $CATALINA_HOME\conf\tomcat-users

To test that Tomcat is setup properly run the startup script (startup.bat/startup.sh), if no errors are displayed on the console we can double-check by visiting http://localhost:8080.

If you see the Tomcat landing page, then we have installed the server correctly.

3. Resolve Port Conflict
By default, Tomcat is set to listen to connections on port 8080. If there is another application that is already bound to this port, the startup console will let us know.

To change the port, we can edit the server configuration file server.xml located at $CATALINA_HOME\conf\server.xml. 
