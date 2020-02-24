package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification reqspec;
	public static ResponseSpecification resspec;

	public RequestSpecification requestSpec() throws IOException {

		if (reqspec == null) {// this condition is used to store mutiple logs out puts as if request specificaiton is updated everytime then the logs are also overwritten
			PrintStream ps = new PrintStream(new FileOutputStream("log.txt"));
			reqspec = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseURL"))
					.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(ps)).addFilter(ResponseLoggingFilter.logResponseTo(ps))
					.build();
			return reqspec;
		}
		return reqspec;
	}

	public ResponseSpecification responseSpec() {
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return resspec;
	}

	public static String getGlobalProperties(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/Global.properties");
		prop.load(fis);

		return prop.getProperty(key);

	}
}
