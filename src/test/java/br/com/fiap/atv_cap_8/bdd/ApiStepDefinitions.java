package br.com.fiap.atv_cap_8.bdd;

import io.cucumber.java.pt.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ApiStepDefinitions {

    private Response response;

    @Dado("que a API ESG está em execução")
    public void que_a_api_esg_esta_em_execucao() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Quando("eu faço uma requisição GET para {string}")
    public void eu_faco_uma_requisicao_get_para(String endpoint) {
        response = RestAssured
                .given()
                .when()
                .get(endpoint);
    }

    @Quando("eu faço uma requisição POST sem token para {string}")
    public void eu_faco_uma_requisicao_post_sem_token_para(String endpoint) {
        response = RestAssured
                .given()
                .contentType("application/json")
                .body("{}")
                .when()
                .post(endpoint);
    }

    @Entao("o status da resposta deve ser {int}")
    public void o_status_da_resposta_deve_ser(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Entao("o corpo deve conter o status {string}")
    public void o_corpo_deve_conter_o_status(String status) {
        response.then().body("status", equalTo(status));
    }

    @Entao("o corpo da resposta deve ser uma lista JSON")
    public void o_corpo_da_resposta_deve_ser_uma_lista_json() {
        response.then().body("$", isA(java.util.List.class));
    }

    @Entao("a resposta deve seguir o contrato {string}")
    public void a_resposta_deve_seguir_o_contrato(String schema) {
        response.then().body(matchesJsonSchemaInClasspath("schemas/" + schema));
    }
}