package first;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

@WebServlet("/sip/*")
public class Interactions extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int principle = Integer.parseInt(req.getParameter("principle"));
        Double interestRate = Double.parseDouble(req.getParameter("interestRate"));
        int period = Integer.parseInt(req.getParameter("period"));
        Double estimatedAmount = 0.0,totalAmount=0.0;

        String amount=req.getParameter("Amount");
        String taxslabs=req.getParameter("Slabs");

        if(principle!=0&&interestRate!=0&&period!=0){
            interestRate = interestRate / (12*100);
            period *=12;
            totalAmount = (principle*((Math.pow(1+interestRate,period)-1)/interestRate)*(1+interestRate));
            estimatedAmount = totalAmount - principle;
            resp.setContentType("application/json");
            Gson gson = new Gson();
            String totalAmountMessage = gson.toJson(totalAmount);
            resp.getWriter().println("The total Amount = "+totalAmountMessage);
            String estimatedAmountMessage = gson.toJson(estimatedAmount);
            resp.getWriter().println("Estimated Amount = "+estimatedAmountMessage);
        }else{
            String received = search(Long.parseLong(amount),Integer.parseInt(taxslabs));
            resp.getWriter().println(received);
        }

    }
    public String search(Long salary,int slabs){
        Gson gson = new Gson();
        if(slabs==0 || slabs==1){
            switch(slabs){
                case 0:if(salary>=0 && salary<=250000){
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for old regemy is : "+ result+ " }";

                }else if(salary>250000 && salary<=500000){
                    salary = (long) (salary *0.05);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for old regemy is : "+ result+ " }";
                }else if(salary>500000 && salary<=750000){
                    salary = (long) (salary *0.20);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for old regemy is : "+ result+ " }";
                }else if(salary>750000 && salary<=1000000){
                    salary = (long) (salary *0.20);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for old regemy is : "+ result+ " }";
                }else if(salary>1000000 && salary<=1250000){
                    salary = (long) (salary *0.30);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for old regemy is : "+ result+ " }";
                }else if(salary>1250000 && salary<=1500000){
                    salary = (long) (salary *0.30);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for old regemy is : "+ result+ " }";
                }else if(salary>1500000){
                    salary = (long) (salary *0.30);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for old regemy is : "+ result+ " }";
                }
                    break;
                case 1:if(salary>=0 && salary<=250000){
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for new regemy is : "+ result+ " }";
                }else if(salary>250000 && salary<=500000){
                    salary = (long) (salary *0.05);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for new regemy is : "+ result+ " }";
                }else if(salary>500000 && salary<=750000){
                    salary = (long) (salary *0.10);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for new regemy is : "+ result+ " }";
                }else if(salary>750000 && salary<=1000000){
                    salary = (long) (salary *0.15);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for new regemy is : "+ result+ " }";
                }else if(salary>1000000 && salary<=1250000){
                    salary = (long) (salary *0.20);
                    System.out.println("The tax Salary to be paid  for new regemy is "+salary);
                }else if(salary>1250000 && salary<=1500000){
                    salary = (long) (salary *0.25);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for new regemy is : "+ result+ " }";
                }else if(salary>1500000){
                    salary = (long) (salary *0.30);
                    String result = gson.toJson(salary);
                    return "{The tax Salary to be paid  for new regemy is : "+ result+ " }";
                }
                    break;

            }
        return  "{taxSlabs:"+slabs+",status:Not found}";

        }

    }

}
}
