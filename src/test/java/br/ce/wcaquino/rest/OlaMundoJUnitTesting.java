package br.ce.wcaquino.rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class OlaMundoJUnitTesting {
    @Test
    public void testOlaMundo() {
        Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
        Assert.assertEquals("Ola Mundo!", response.getBody().asString());
        //Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        //Assert.assertTrue("O status code deveria ser 201", response.statusCode() == 201);
        Assert.assertEquals(201, response.statusCode());

        // falha: assertivas não correspondentes (expect(200) to equal(500))
        // erro: runtime, durante a execução do código
        ValidatableResponse validation = response.then();
        validation.statusCode(200);
    }
}
