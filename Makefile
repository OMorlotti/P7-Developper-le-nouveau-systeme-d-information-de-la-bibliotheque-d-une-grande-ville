all:
	mvn clean package
	cd WebAPI/target ; java -jar  VirtualBookcaseWebAPI-*.jar
