//package guru.sfg.brewery.web.controllers;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//
//
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//public class BeerControllerTest2 {
//
//    @Autowired
//    WebApplicationContext wac;
//
//    protected MockMvc mockMvc;
//
//    @Before
//    public void setup(){
//        System.out.println("first");
//
//    }
//
//    @Test
//    public void deleteBeerHttpBasic() throws Exception {
//        System.out.println("second");
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .apply(springSecurity())
//                .build();
//        mockMvc.perform(delete("/api/v1/beer/5415e4c2-8f7e-47c4-b66e-3eebda6f6573")
//                .with(httpBasic("user1","user1")))
//                .andExpect(status().is2xxSuccessful());
//    }
//}
