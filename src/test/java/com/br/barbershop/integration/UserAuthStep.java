package com.br.barbershop.integration;

import com.br.barbershop.controller.UserAuthController;
import com.br.barbershop.helpers.TestContext;
import com.br.barbershop.model.DTO.address.DataRegisterAddress;
import com.br.barbershop.model.DTO.user.DataRegisterUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class UserAuthStep {

  private MockMvc mockMvc;
  private MvcResult mvcResult;
  private DataRegisterUser validClient;
  private DataRegisterAddress validAddress;
  @Autowired
  private UserAuthController controller;

  @Autowired
  private TestContext testContext;

  private UUID userUUID;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .build();
  }

  /* Test to validate user creation */
  @Given("Valid data")
  public void valid_data() {
    validAddress = new DataRegisterAddress("80430154", "Largo da Galícia", "Curitiba");
    validClient = new DataRegisterUser("tawdrysson@hotmail.com", "Tawdrysson Vowoy Satuon", "zEu*ja*WA6", "07471013028", "21998443394", new Date(2004, 9, 13), validAddress);
  }
  @When("The registration request is sent")
  public void the_registration_request_is_sent() throws Exception {
    String jsonContent = new ObjectMapper().writeValueAsString(validClient);
    this.mvcResult = mockMvc.perform(post("/auth/user")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonContent))
    .andReturn();
  }
  @Then("The customer is created and the status {int} is received")
  public void the_customer_is_created_and_the_status_is_received(Integer int1) throws Exception {
    assertEquals("Check if the return status is 200 OK", int1.intValue(), this.mvcResult.getResponse().getStatus());

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = objectMapper.readTree(this.mvcResult.getResponse().getContentAsString());

    JsonNode id = rootNode.path("id");
    JsonNode email = rootNode.path("email");
    JsonNode username = rootNode.path("username");
    JsonNode document = rootNode.path("document");
    JsonNode dateOfBirth = rootNode.path("dateOfBirth");
    JsonNode phoneNumber = rootNode.path("phoneNumber");

    JsonNode address = rootNode.path("address");

    String idStr = id.asText();

    testContext.setUserUUID(UUID.fromString(idStr));
  }


  @Given("There are clients registered")
  public void there_are_clients_registered() {
  }
  @When("I request a list of all clients")
  public void i_request_a_list_of_all_clients() throws Exception {
    this.mvcResult = mockMvc.perform(get("/auth/user")
            .contentType(MediaType.APPLICATION_JSON))
        .andReturn();
  }
  @Then("I receive a successful response with a list of clients")
  public void i_receive_a_successful_response_with_a_list_of_clients() {
    assertEquals("Check if the return status is 200 OK", 200, mvcResult.getResponse().getStatus());
  }
  @Then("The list is paginated according to the default settings")
  public void the_list_is_paginated_according_to_the_default_settings() throws UnsupportedEncodingException, JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode responseJson = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
    System.out.println(mvcResult.getResponse().getContentAsString());
  }


  @Given("There is a registered client with a known ID")
  public void there_is_a_registered_client_with_a_known_id() {
    this.userUUID = testContext.getUserUUID();
  }
  @When("I request the client details by this ID")
  public void i_request_the_client_details_by_this_id() throws Exception {
    this.mvcResult = mockMvc.perform(get("/auth/user/" + userUUID)
            .contentType(MediaType.APPLICATION_JSON))
        .andReturn();
  }
  @Then("I receive a successful response with the client's details")
  public void i_receive_a_successful_response_with_the_client_s_details() {
    assertEquals("Check if the return status is 200 OK", 200, mvcResult.getResponse().getStatus());
  }


  /* Test to validate user deletion */
  @Given("a valid id")
  public void a_valid_id() {
    this.userUUID = testContext.getUserUUID();
  }
  @When("a request to delete the customer is sent")
  public void a_request_to_delete_the_customer_is_sent() throws Exception {
    this.mvcResult = mockMvc.perform(delete("/auth/user/" + userUUID)
            .contentType(MediaType.APPLICATION_JSON))
        .andReturn();
  }
  @Then("a {int} no content status is received")
  public void a_no_content_status_is_received(Integer int1) {
    assertEquals("Check if the return status is 204 no content", int1.intValue(), mvcResult.getResponse().getStatus());
  }

}
