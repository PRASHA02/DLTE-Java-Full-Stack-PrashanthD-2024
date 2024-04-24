//package element.spring.boot.springboot;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.lenient;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class DlteJavaSpringTask1089ApplicationTests {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private TransactionServices transactionServices;
//
//    @Test
//    void CallInsert(){
//        TransactionsModel transactionsModel1 = new TransactionsModel(111L,new Date(2024,03,2),"prashanth","vignesh",1000000L,"education");
//        TransactionsModel transactionsModel2 = new TransactionsModel(895775L,new Date(2024,12,2),"vineeth","prashanth",50000L,"family");
//        TransactionsModel transactionsModel3 = new TransactionsModel(7952345L,new Date(2026,11,21),"elroy","jeevan",50000L,"friend");
//
//       lenient().when(jdbcTemplate.update(anyString(),any(Object[].class))).thenReturn(1);
//
//
//       TransactionsModel transactionsModel = transactionServices.apiSave(transactionsModel1);
//
//       assertEquals(transactionsModel.getTransactionAmount(),transactionsModel1.getTransactionAmount());//success
//      // assertNotSame(transactionsModel,transactionsModel1);//failure
//
//    }
//
//    @Test
//    void callFindById(){
//        TransactionsModel transactionsModel1 = new TransactionsModel(111L,new Date(2024,03,2),"prashanth","vignesh",1000000L,"education");
//        TransactionsModel transactionsModel2 = new TransactionsModel(895775L,new Date(2024,12,2),"vineeth","prashanth",50000L,"family");
//        TransactionsModel transactionsModel3 = new TransactionsModel(7952345L,new Date(2026,11,21),"elroy","jeevan",50000L,"friend");
//
//        List<TransactionsModel> transactionsModelList = Stream.of(transactionsModel1,transactionsModel2).collect(Collectors.toList());
//        when(jdbcTemplate.query(anyString(),any(Object[].class),any(RowMapper.class))).thenReturn(transactionsModelList);
//
//        List<TransactionsModel> actualList = transactionServices.apiFindBySender("prashanth");
//
//       // assertTrue(transactionsModelList.size()==actualList.size());//success
//
//        //assertNull(transactionsModelList==actualList);//false
//
//       assertEquals(transactionsModel1.getTransactionAmount(),actualList.get(0).getTransactionAmount());//success
//
//
//    }
//
//    @Test
//    void callFindByReceiver(){
//        TransactionsModel transactionsModel1 = new TransactionsModel(111L,new Date(2024,03,2),"prashanth","vignesh",1000000L,"education");
//        TransactionsModel transactionsModel2 = new TransactionsModel(895775L,new Date(2024,12,2),"vineeth","prashanth",50000L,"family");
//        TransactionsModel transactionsModel3 = new TransactionsModel(7952345L,new Date(2026,11,21),"elroy","jeevan",50000L,"friend");
//
//        List<TransactionsModel> transactionsModelList = Stream.of(transactionsModel3,transactionsModel2).collect(Collectors.toList());
//
//        when(jdbcTemplate.query(anyString(),any(Object[].class),any(RowMapper.class))).thenReturn(transactionsModelList);
//
//        List<TransactionsModel> actualList = transactionServices.apiFindByReceiver("jeevan");
//
//        //assertSame(transactionsModelList,actualList);//success
//
//        assertNotEquals(transactionsModelList,actualList);//fail
//
//    }
//
//    @Test
//    void callFindByAmount(){
//        TransactionsModel transactionsModel1 = new TransactionsModel(111L,new Date(2024,03,2),"prashanth","vignesh",1000000L,"education");
//        TransactionsModel transactionsModel2 = new TransactionsModel(895775L,new Date(2024,12,2),"vineeth","prashanth",50000L,"family");
//        TransactionsModel transactionsModel3 = new TransactionsModel(7952345L,new Date(2026,11,21),"elroy","jeevan",50000L,"friend");
//
//        List<TransactionsModel> transactionsModelList = Stream.of(transactionsModel1,transactionsModel2,transactionsModel3).collect(Collectors.toList());
//
//        when(jdbcTemplate.query(anyString(),any(Object[].class),any(RowMapper.class))).thenReturn(transactionsModelList);
//
//        List<TransactionsModel> actualList = transactionServices.apiFindByAmount(50000L);
//
//        assertEquals(transactionsModelList,actualList);//success
//
////        assertNull(transactionsModelList==actualList);//fail
//
//    }
//
//
//}
