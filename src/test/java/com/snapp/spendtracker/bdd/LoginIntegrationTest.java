package com.snapp.spendtracker.bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snapp.spendtracker.config.JwtAuthenticationFilter;
import com.snapp.spendtracker.infrastructure.api.dto.LoginUserDto;
import com.snapp.spendtracker.infrastructure.repository.UserInformationRepository;
import com.snapp.spendtracker.application.service.AuthenticationManager;
import com.snapp.spendtracker.util.JwtTokenUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
public class LoginIntegrationTest extends CucumberIntegrationTest{
    private final WebApplicationContext webApplicationContext;
    private final AuthenticationManager authenticationManager;
    private final UserInformationRepository userInformationRepository;
    private final JwtAuthenticationFilter filter;
    private String result;
    private String requestJson;
    @Given("I want to login")
    public void iWantToLogin() throws JsonProcessingException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).addFilters(filter).build();
        LoginUserDto loginUserDto = LoginUserDto.builder()
            .userName("m.bidkham")
            .password("password")
            .build();
        requestJson = ow.writeValueAsString(loginUserDto);
    }

    @When("I entered valid user and pass")
    public void iEnteredLoginData() throws Exception {
        var response = this.mockMvc
            .perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isOk())
            .andReturn();

        this.result = response.getResponse().getContentAsString();
    }
    @Then("Perform a valid token")
    public void performValidToken(){
        var token = JwtTokenUtil.generateToken("m.bidkham");
        var user = userInformationRepository.findByUserName("m.bidkham");
        assertEquals(user.get().getPassword(), authenticationManager.hashPassword("password"));
        assertTrue(result.contains(token));
    }

}
