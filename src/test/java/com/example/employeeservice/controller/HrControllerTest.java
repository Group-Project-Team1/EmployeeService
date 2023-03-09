package com.example.employeeservice.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import com.example.employeeservice.domain.response.VisaStatusResponse;
import com.example.employeeservice.service.HrEmployeeProfilesService;
import com.example.employeeservice.service.HrHomepageService;
import com.example.employeeservice.service.HrHousingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest()
@Slf4j
public class HrControllerTest {

    private MockMvc mockMvc;
    @Mock
    private HrHomepageService hrHomepageService;

    @Mock
    private HrEmployeeProfilesService hrEmployeeProfilesService;

    @Mock
    private HrHousingService hrHousingService;

    @InjectMocks
    private HrController hrController;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hrController).build();
    }

    @Test
    public void testFindAllVisaStatus() throws Exception {
        // Setup
        int page = 1;
        int itemsPerPage = 10;
        List<VisaStatusResponse> visaStatusResponses = new ArrayList<>();
        visaStatusResponses.add(new VisaStatusResponse("Li Lei", "H1B", "2024-06-01", "666"));
        when(hrHomepageService.findAllVisaStatusPaginated(page, itemsPerPage)).thenReturn(visaStatusResponses);

        // Execution
        mockMvc.perform(get("/hr/home?page=" + page + "&itemsPerPage=" + itemsPerPage))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.message").value("All active visa status."))
                .andExpect((ResultMatcher) jsonPath("$.status").value(HttpStatus.OK.toString()))
                .andExpect((ResultMatcher) jsonPath("$.data[0].name").value("John Doe"))
                .andExpect((ResultMatcher) jsonPath("$.data[0].visaType").value("H1B"))
                .andExpect((ResultMatcher) jsonPath("$.data[0].visaExpirationDate").value("2024-06-01"));
        // Verification
    }


}
