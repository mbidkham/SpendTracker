package com.snapp.spendtracker.bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import com.snapp.spendtracker.bdd.util.InitialDataUtil;
import com.snapp.spendtracker.config.JwtAuthenticationFilter;
import com.snapp.spendtracker.controller.dto.AddCategoryDto;
import com.snapp.spendtracker.controller.dto.LoginUserDto;
import com.snapp.spendtracker.controller.dto.SearchCategoryDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
public class CategoryIntegrationTest extends CucumberIntegrationTest{
    private String result;
    private String searchRequestJson;
    private String addRequestJson;
    private HttpHeaders headers = new HttpHeaders();

    private final WebApplicationContext webApplicationContext;
    private final JwtAuthenticationFilter filter;
    private final InitialDataUtil initialDataUtil;


    @When("I logged in and got token.")
    public void iLoggedIn() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).addFilters(filter).build();
        LoginUserDto loginUserDto = LoginUserDto.builder()
            .userName("m.bidkham")
            .password("password")
            .build();
        String requestJson = ow.writeValueAsString(loginUserDto);
        var response = this.mockMvc
            .perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isOk())
            .andReturn();

        this.result = response.getResponse().getContentAsString();
        var authorizationHeader = result.split(":")[1].replaceAll("\"", "")
            .replace("}", "");
        headers.set("Authorization", "Bearer " + authorizationHeader);

    }
    @Given("I want to add new category for myself")
    public void iAddNewCategory() throws JsonProcessingException {
        AddCategoryDto addCategoryDto = AddCategoryDto.builder()
            .limit(BigDecimal.valueOf(1_000_000))
            .name("clothes")
            .build();
        addRequestJson = ow.writeValueAsString(addCategoryDto);

    }

    @When("I entered valid category name and limit value")
    public void inputDataIsValidAndCategoryNameIsNew() throws Exception {
        var response = this.mockMvc
            .perform(post("/category/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(addRequestJson)
                .headers(headers))
            .andExpect(status().isOk())
            .andReturn();

        this.result = response.getResponse().getContentAsString();
    }

    @Then("Show the message {string}")
    public void showTheMessage(String message){
        assertTrue(result.contains(message));
    }

    @Given("I want to see my categories having names containing 'cof'")
    public void iGetMyCofCategories() throws Exception {
        initialDataUtil.saveCategory("coffee", BigDecimal.valueOf(100_000));
        var categoryDto = SearchCategoryDto.builder().name("cof").page(0).pageSize(1).build();
        searchRequestJson = ow.writeValueAsString(categoryDto);

    }
    @When("There is 1 category with name 'coffee'")
    public void iCanSeeMyCoffeeCategory() throws Exception {
        var response = this.mockMvc
            .perform(post("/category/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRequestJson)
                .headers(headers))
            .andExpect(status().isOk())
            .andReturn();

        this.result = response.getResponse().getContentAsString();
    }
    @Then("I get my categories")
    public void iGetMyCategories(){
        assertEquals(JsonPath.parse(result).read("$.content[0].name", String.class), "coffee");
        assertEquals(JsonPath.parse(result).read("$.totalPages", String.class), "1");
    }

}
