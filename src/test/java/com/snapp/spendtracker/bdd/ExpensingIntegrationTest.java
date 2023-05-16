package com.snapp.spendtracker.bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snapp.spendtracker.bdd.util.InitialDataUtil;
import com.snapp.spendtracker.config.JwtAuthenticationFilter;
import com.snapp.spendtracker.infrastructure.api.dto.AddExpenseDto;
import com.snapp.spendtracker.infrastructure.api.dto.LoginUserDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
public class ExpensingIntegrationTest extends CucumberIntegrationTest {

    private String result;
    private String addRequestJson;
    private HttpHeaders headers = new HttpHeaders();

    private final WebApplicationContext webApplicationContext;
    private final JwtAuthenticationFilter filter;
    private final InitialDataUtil initialDataUtil;

    @When("I logged in.")
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

    @Given("I want to add my travel expenses.")
    public void iAddMyTravelExpenses() throws JsonProcessingException {
        var category = initialDataUtil.saveCategory("travel", BigDecimal.valueOf(1_000_000));
        initialDataUtil.saveNewExpense(category, BigDecimal.valueOf(300_000));
        addRequestJson = ow.writeValueAsString(AddExpenseDto.builder()
            .amount(BigDecimal.valueOf(800_000))
            .id(category.getId())
            .build());
    }

    @When("I had spent 300_000 up to now in my travel.")
    public void iSpentTooMoneyInTravel() throws Exception {
        var response = this.mockMvc
            .perform(post("/expenses/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(addRequestJson)
                .headers(headers))
            .andExpect(status().isOk())
            .andReturn();

        this.result = response.getResponse().getContentAsString();
    }

    @Then("I got notified to spend too many in my travel.")
    public void iGotNoticedByMyTravelExpenses() {
        assertEquals( "NOTICE!!You are spending too money on buying travel in this month!!", result);
    }


}
