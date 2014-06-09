VehiclesRThem

Java Requirement: 1.7
mongoDB should be installed and without mongoDB this application does not work.
mongoDB should be configured with its default port and localhost IP 127.0.0.1

Running application is easy. It comes with embedded jetty server and just type a commands: 
mvn clean install
mvn jetty:run

The source code is also publicly available on github:
https://github.com/solxiom/vehiclesRThem


Have fun ordering cool vehicles :

 car	<color>	<number	of	wheels>	<number	of	steering	wheels>	
Example:	car	blue	6	1	

motorcycle	<color>	<rider	sex	f/m>	
Example:	motorcycle	yellow	f	

boat	<color>	<floats	yes/no>	<number	of	periscopes>	
Note:	the	last	parameter	is	applicable	only	if	"floats"	is	set	to	"no"	
Example:	boat	orange	yes	
Example:	boat	orange	no	1	


o ferrari	
  Ferrari	is	red,	has	four	wheels,	one	steering	wheel	and	is	cool	

o bicycle	<color>	<road/mtb/dirt>

 
