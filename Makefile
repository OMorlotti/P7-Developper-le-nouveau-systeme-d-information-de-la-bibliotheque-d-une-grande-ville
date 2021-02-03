all:
	mvn clean package
	cd WebAPI/target ; java -jar  VirtualBookcaseWebAPI-*.jar

site:
	mvn clean package
	cd userwebsite/target ; java -jar  VirtualBookcaseUserWebSite-*.jar