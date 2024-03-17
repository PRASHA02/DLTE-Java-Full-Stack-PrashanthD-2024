package basics.services;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainXml {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        List<Transaction> transactionsList = Stream.of(
                new Transaction(new Date(2026,02,2),200000.0,"Prashanth","Education"),
                new Transaction(new Date(2024,05,11),100000.0,"Vineeth","Family"),
                new Transaction(new Date(2027,02,9),30000.0,"Vignesh","Friend"),
                new Transaction(new Date(2024,10,7),50000.0,"Ajay","Emergency"),
                new Transaction(new Date(2025,12,30),176536.0,"elroy","Food")
        ).collect(Collectors.toList());

        TransactionList transactionList = new TransactionList(transactionsList);
        Transaction transaction = new Transaction();
        JAXBContext jaxbContext = JAXBContext.newInstance(TransactionList.class);
        System.out.println("--Menu--");
        System.out.println("1->Marshaller\n2->Unmarshaller");
        System.out.println("Enter Your Choice");
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 1 :  Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(transactionList,new FileOutputStream("transactionMarshaller.xml"));
        System.out.println("XML Built Successfully");
        break;
            case 2:  Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
                TransactionList myLists = (TransactionList) unmarshaller.unmarshal(new FileInputStream("transactionMarshaller.xml"));
                myLists.getTransactions().stream()
                        .filter(each -> each.getToRecipient().equalsIgnoreCase("Prashanth"))
                        .forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid Choice");
                return;
        }
//
    }
    }

