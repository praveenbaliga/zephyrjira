package zephyr.test.automation;

import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ZephyrJiraConnector {
    public static void main(String[] args) throws URISyntaxException, IllegalStateException, IOException {
        // Replace Zephyr BaseUrl with the <ZAPI_CLOUD_URL> shared with ZAPI Cloud Installation
        String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
        // zephyr accessKey , we can get from Addons >> zapi section
        String accessKey = "YmVkMTRiZmQtYTA0Mi0zM2VkLWI0NDktZjJkYzkxYTMzYmU3IDYzZDY2MTVhZmI3NWY4NTY4ZjVlNjYwMyB6ZXBoeXI";
        // zephyr secretKey , we can get from Addons >> zapi section
        String secretKey = "T_pxlx6yWDVUQN1rECWiPDrLQifNt4H7T-6Egg64PCk";
        // Jira accountId
        String accountId = "63d6615afb75f8568f5e6603";
        //String accountId = "praveen.baliga@gmail.com";
        ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId).build();
        JwtGenerator jwtGenerator = client.getJwtGenerator();

        // API to which the JWT token has to be generated
        //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycles/search?versionId=-1&projectId=10001";
        //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/config/generalinformation";
        //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/stepdefect/byexecution";
        //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycle/14378a8c-958a-4093-b5c9-f1386fc70c03?versionId=-1&projectId=10001";
        //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/execution/statuses";
        //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/stepresult/search?executionId=c36ea606-ca20-48b4-a9ad-9b0d2d89475f&issueId=10008";
        //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/stepresult/282882bc-2965-42db-b9b9-d8b105ef42dc"; // update the step result
        String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/execution/c36ea606-ca20-48b4-a9ad-9b0d2d89475f"; // update the execution status

        URI uri = new URI(createCycleUri);
        int expirationInSec = 360;
        String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);

        // Print the URL and JWT token to be used for making the REST call
        System.out.println("FINAL API : " +uri.toString());
        System.out.println("JWT Token : " +jwt);


    }
}
