package com.redhat.labs.omp.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class ConfigResourceTest {

    @Test
    public void testGetConfigFileSuccess() {

        given().when().contentType(ContentType.JSON).get("/api/v1/config").then().statusCode(200).body(is(
                "{\"content\":\"---\\nproviders:\\n- label: AWS\\n  value: ec2\\n  regions:\\n  - label: US East 1 (N. Virginia)\\n    value: us-east-1\\n  - label: US East 2 (Ohio)\\n    value: us-east-2\\nopenshift:\\n  versions:\\n  - label: v4.1\\n    value: 4.1.31\\n  - label: v4.2\\n    value: 4.2.16\\n  - label: v4.3\\n    value: 4.3.0\\n  persistent-storage:\\n  - label: None\\n    value: none\\n  - label: 50GB\\n    value: 50G\\n  - label: 100GB\\n    value: 100G\\n  - label: 250GB\\n    value: 250G\\n  - label: 500GB\\n    value: 500G\\n  cluster-size:\\n  - label: Small\\n    value: small\\nuser-management:\\n  rbac:\\n    roles:\\n    - label: Developer \\n      value: developer\\n    - label: Observer \\n      value: observer\\n    - label: Admin \\n      value: admin\\n\",\"encoding\":\"base64\",\"file_path\":\"schema/config.yml\"}"));

    }

}
