package br.ce.wcaquino.rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class OlaMundoJUnitTesting {
    @Test
    public void testOlaMundo() {
        Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
        Assert.assertEquals("Ola Mundo!", response.getBody().asString());
        //Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        //Assert.assertTrue("O status code deveria ser 201", response.statusCode() == 201);
        Assert.assertEquals(200, response.statusCode());

        // falha: assertivas não correspondentes (expect(200) to equal(500))
        // erro: runtime, durante a execução do código
        ValidatableResponse validation = response.then();
        validation.statusCode(200);
    }

    @Test
    public void devoConhecerOutrasFormasRestAssured() {
        given()//Pré-condições
                .when()//Ação
                .get("http://restapi.wcaquino.me/ola")
                .then()//Assertivas
                .statusCode(201);
    }

    @Test
    public void devoConhecerOsMatchersHamcrest() {
        assertThat("Maria", Matchers.is("Maria"));
        assertThat(128, Matchers.isA(Integer.class));
        assertThat(128d, Matchers.isA(Double.class));
        assertThat(128d, Matchers.greaterThan(1d));
        assertThat(128d, Matchers.greaterThan(1d));
        assertThat(120d, lessThan(121d));

        List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);

        assertThat(impares, hasSize(5));
        assertThat(impares, contains(1, 3, 5, 7, 9));
        assertThat(impares, containsInAnyOrder(9, 1, 5, 7, 3));
        assertThat(impares, hasItem(1));
        assertThat(impares, hasItems(1, 5));

        assertThat("Maria", not("João"));
        assertThat("Maria", anyOf(is("Maria"), is("João")));
        assertThat("Maria", allOf(startsWith("Ma"), containsString("ri"), endsWith("a")));
    }
}
