import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import java.util.List;
public class RestAssuredAPITest {
    int currentPage = 0;
    private static final String BASE_URL = "https://blockstream.info/api/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732/txs/";
    @Test
    public void validateTransactionIds() {
        // Specify the base URL to the RESTful web service
        for(int i=0; i<10; i++) {
            RestAssured.baseURI = BASE_URL + currentPage;
            // Get the RequestSpecification of the request to be sent to the server.
            RequestSpecification httpRequest = RestAssured.given();
            // specify the method type (GET) and the parameters if any.
            //In this case the request does not take any parameters
            Response response = httpRequest.request(Method.GET, "");
            JsonPath jsonPathEvaluator = response.jsonPath();
            //List all the txid in the response
            List<String> alltxid = jsonPathEvaluator.getList("txid");
            System.out.println(currentPage);
            for(String txid : alltxid)
            {
                // Compare the transactionIDs
                if(txid.equalsIgnoreCase("96d92f03000f625a38bf8cb91c01188a02b7972238cc6c4e0c6f334cf755004d")) {
                    System.out.println("Transaction Found with " + txid +" On Index "+currentPage);
                }
                else if(txid.equalsIgnoreCase("6dd68336c085d5b7b694e2bf6f6c11bca589aea07b6f1c0232bd627c3d217074")) {
                    System.out.println("2nd Transaction Found with " + txid +" On Index "+currentPage);
                    System.out.println(currentPage);
                }
                else
                {
                    System.out.println("Transaction ID Not Found");
                }
            }
            currentPage = currentPage+25;
        }
    }
}
