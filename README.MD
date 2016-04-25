## GFSum

Demo for running a sum function in Gemfire.

### Compile

`$ mvn clean package`

Two jar files are generated. Deploy the file **gfsumdemo-1.0-SNAPSHOT-domain.jar** to the gemfire cache. 

 
Before run the application make sure you have the function ***https://github.com/caxqueiroz/gf-sum-func*** deployed to Gemfire cluster. 
 
 
 ### Run
 
 `$ java -jar target/gfsumdemo-1.0-SNAPSHOT.jar --gemfire.locator.address=<locator addr> --gemfire.locator.port=<locator port>`
 
 It will load 1000 ticks into gemfire and sum their ask price. 

