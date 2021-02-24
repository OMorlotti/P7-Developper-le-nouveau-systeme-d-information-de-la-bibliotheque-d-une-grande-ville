.PHONY: api
api:
	mvn clean package
	cd WebAPI/target ; java -jar VirtualBookcaseWebAPI-*.jar

.PHONY: site
site:
	mvn clean package
	cd UserWebSite/target ; java -jar VirtualBookcaseUserWebSite-*.jar

.PHONY: batch
batch:
	mvn clean package
	cd Batch/target ; java -jar VirtualBookcaseBatch-*.jar
