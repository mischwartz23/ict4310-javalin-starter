% Author: M I Schwartz

ICT4310 simple server using Javalin - and sample get and post clients

This example uses a Javalin server and the standard HttpClient to illustrate simple get and post requests

To build a standalone jar, use 
	mvn clean compile assembly:single
If building under Netbeans, this target is built automatically

The standalone jar can be run with 
	java -jar ict4310-javelin-helper-1.0-jar-with-dependencies.jar

* To match the slide show, three SimpleServers are provided:
    - edu.du.ict4310.initial.SimpleServer.java   Server with no routes
    - edu.du.ict4310.routes.SimpleServer.java    Server with 3 routes
    - edu.du.ict4310.SimpleServer.java           Server with routes and a template

* The introductory slide presentation is HTTP-Java-Javalin.md.html
