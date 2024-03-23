package element.spring.boot.springboot;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLSyntaxErrorException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndPointTesting {
    @MockBean
    private TransactionServices transactionServices;

    @InjectMocks
    private TransactionController transactionController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void callSave() throws Exception {
        String request = "{\n" +
                "  \"transactionId\": 7952345,\n" +
                "  \"transactionDate\": \"2026-11-21\",\n" +
                "  \"transactionBy\": \"elroy\",\n" +
                "  \"transactionTo\": \"jeevan\",\n" +
                "  \"transactionAmount\": 50000,\n" +
                "  \"transactionFor\": \"friend\"\n" +
                "}\n";
        TransactionsModel transactionsModel = new TransactionsModel(7952345L,  new Date("11/21/2026"),"elroy","jeevan",50000L,"friend");

        when(transactionServices.apiSave(any())).thenReturn(transactionsModel);

        mockMvc.perform(post("/transaction/").contentType(MediaType.APPLICATION_JSON).content(request)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.transactionId").value(7952345L)).
                andExpect(jsonPath("$.transactionDate").value("2026-11-20T18:30:00.000+00:00")).
                andExpect(jsonPath("$.transactionBy").value("elroy")).
                andExpect(jsonPath("$.transactionTo").value("jeevan")).
                andExpect(jsonPath("$.transactionAmount").value(50000)).
                andExpect(jsonPath("$.transactionFor").value("friend"))
        ;//success

//        mockMvc.perform(post("/transaction/").contentType(MediaType.APPLICATION_JSON).content(request)).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$.transactionId").value(795234L)).//value is not correct
//                andExpect(jsonPath("$.transactionDate").value("2026-11-20T18:30:00.000+00:00")).
//                andExpect(jsonPath("$.transactionBy").value("elroy")).
//                andExpect(jsonPath("$.transactionTo").value("jeevan")).
//                andExpect(jsonPath("$.transactionAmount").value(50000)).
//                andExpect(jsonPath("$.transactionFor").value("friend"))
//        ;//failure


    }

    @Test
    void testPointBy() throws Exception {
        TransactionsModel transactionsModel1 = new TransactionsModel(7952345L,  new Date("11/21/2026"),"elroy","jeevan",50000L,"friend");
        TransactionsModel transactionsModel2 = new TransactionsModel(111L,new Date("03/2/2023"),"prashanth","vignesh",1000000L,"education");
        TransactionsModel transactionsModel3 = new TransactionsModel(895775L,new Date("12/2/2024"),"vineeth","prashanth",50000L,"family");
        List<TransactionsModel> transactionsModels = Stream.of(transactionsModel3,transactionsModel1,transactionsModel2).collect(Collectors.toList());
        when(transactionServices.apiFindBySender(eq("elroy"))).thenReturn((transactionsModels));
//        mockMvc.perform(get("/transaction/findBy")).andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(7952345L)).
//                andExpect(jsonPath("$[0].transactionDate").value("2026-11-20T18:30:00.000+00:00")).
//                andExpect(jsonPath("$[0].transactionBy").value("elroy")).
//                andExpect(jsonPath("$[0].transactionTo").value("jeevan")).
//                andExpect(jsonPath("$[0].transactionAmount").value(50000L)).
//                andExpect(jsonPath("$[0].transactionFor").value("friend"));
//    }//failure because index and path is wrong
                mockMvc.perform(get("/transaction/findBy/elroy")).andExpect(status().isOk()).
                andExpect(jsonPath("$[1].transactionId").value(7952345L)).
                andExpect(jsonPath("$[1].transactionDate").value("2026-11-20T18:30:00.000+00:00")).
                andExpect(jsonPath("$[1].transactionBy").value("elroy")).
                andExpect(jsonPath("$[1].transactionTo").value("jeevan")).
                andExpect(jsonPath("$[1].transactionAmount").value(50000L)).
                andExpect(jsonPath("$[1].transactionFor").value("friend"));
    }//success

    @Test
    void testPointTo() throws Exception {
        TransactionsModel transactionsModel1 = new TransactionsModel(7952345L,  new Date("11/21/2026"),"elroy","jeevan",50000L,"friend");
        TransactionsModel transactionsModel2 = new TransactionsModel(111L,new Date("03/23/2023"),"prashanth","vignesh",1000000L,"education");
        TransactionsModel transactionsModel3 = new TransactionsModel(895775L,new Date("12/24/2024"),"vineeth","prashanth",50000L,"family");
        List<TransactionsModel> transactionsModels = Stream.of(transactionsModel1,transactionsModel2,transactionsModel3).collect(Collectors.toList());
//        when(transactionServices.apiFindByReceiver(eq("jeevan"))).thenReturn((transactionsModels));
//
//                mockMvc.perform(get("/transaction/findTo/jeevan")).andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(7952345L)).
//                andExpect(jsonPath("$[0].transactionDate").value("2026-11-20T18:30:00.000+00:00")).
//                andExpect(jsonPath("$[0].transactionBy").value("elroy")).
//                andExpect(jsonPath("$[0].transactionTo").value("jeevan")).
//                andExpect(jsonPath("$[0].transactionAmount").value(50000L)).
//                andExpect(jsonPath("$[0].transactionFor").value("friend"));
//    }//success

        when(transactionServices.apiFindByReceiver(eq("jeevan"))).thenReturn((transactionsModels));

                mockMvc.perform(get("/transaction/findTo/jeevan")).andExpect(status().isOk()).
                andExpect(jsonPath("$[1].transactionId").value(7952345L)).
                andExpect(jsonPath("$[1].transactionDate").value("2026-11-20T18:30:00.000+00:00")).
                andExpect(jsonPath("$[1].transactionBy").value("elroy")).
                andExpect(jsonPath("$[1].transactionTo").value("jeevan")).
                andExpect(jsonPath("$[1].transactionAmount").value(50000L)).
                andExpect(jsonPath("$[1].transactionFor").value("friend"));
    }//failure because index  is wrong



    @Test
    void testPointFor() throws Exception {
        TransactionsModel transactionsModel1 = new TransactionsModel(7952345L,  new Date("11/21/2026"),"elroy","jeevan",50000L,"friend");
        TransactionsModel transactionsModel2 = new TransactionsModel(111L,new Date("03/20/2023"),"prashanth","vignesh",1000000L,"education");
        TransactionsModel transactionsModel3 = new TransactionsModel(895775L,new Date("12/24/2024"),"vineeth","prashanth",50000L,"family");
        List<TransactionsModel> transactionsModels = Stream.of(transactionsModel2,transactionsModel1,transactionsModel3).collect(Collectors.toList());
        when(transactionServices.apiFindByAmount(eq(1000000L))).thenReturn((transactionsModels));
//        mockMvc.perform(get("/transaction/findAmount/50000")).andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(7952345L)).
//                andExpect(jsonPath("$[0].transactionDate").value("2026-11-20T18:30:00.000+00:00")).
//                andExpect(jsonPath("$[0].transactionBy").value("elroy")).
//                andExpect(jsonPath("$[0].transactionTo").value("jeevan")).
//                andExpect(jsonPath("$[0].transactionAmount").value(50000)).
//                andExpect(jsonPath("$[0].transactionFor").value("friend"));
//    }//failure because No value at JSON path "$[0].transactionId"


        mockMvc.perform(get("/transaction/findAmount/1000000")).andExpect(status().isOk()).
                andExpect(jsonPath("$[0].transactionId").value(111L)).
                andExpect(jsonPath("$[0].transactionDate").value("2023-03-20T18:30:00.000+00:00")).
                andExpect(jsonPath("$[0].transactionBy").value("prashanth")).
                andExpect(jsonPath("$[0].transactionTo").value("vignesh")).
                andExpect(jsonPath("$[0].transactionAmount").value(1000000L)).
                andExpect(jsonPath("$[0].transactionFor").value("education"));
    }//success
}
