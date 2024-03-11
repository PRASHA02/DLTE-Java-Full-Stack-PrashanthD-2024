package sip.tax;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

@WebServlet("/sip/*")
public class SipTax extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        super.doGet(req, resp);
//        int choice = Integer.parseInt("choice");
//        if(choice==1){

         String principleAmount = req.getParameter("principle");
         String interest = req.getParameter("interestRate");
         String time = req.getParameter("period");
         String annualIncome = req.getParameter("AnnualIncome");
         String Slabs =req.getParameter("slabs");
         Double estimatedAmount = 0.0, totalAmount = 0.0;

        if (principleAmount!=null && interest!= null && time!= null) {
            int principle = Integer.parseInt(principleAmount);
            Double interestRate = Double.parseDouble(interest);
            int period = Integer.parseInt(time);
            interestRate = interestRate / (12 * 100);
            period *= 12;
            totalAmount = (principle * ((Math.pow(1 + interestRate, period) - 1) / interestRate) * (1 + interestRate));
            estimatedAmount = totalAmount - principle;
            resp.setContentType("application/json");
            Gson gson = new Gson();
            String totalAmountMessage = gson.toJson(totalAmount);
            resp.getWriter().println("The total Amount = " + totalAmountMessage);
            String estimatedAmountMessage = gson.toJson(estimatedAmount);
            resp.getWriter().println("Estimated Amount = " + estimatedAmountMessage);
        }
        if(annualIncome!=null && Slabs!=null){
            Long salary=Long.parseLong(annualIncome);
            int slabs=Integer.parseInt(Slabs);
           if(slabs==0) {
               if (salary >= 0 && salary <= 250000) {
                   resp.getWriter().println("The tax Salary to be paid  for old regemy is : " + salary);

               } else if (salary > 250000 && salary <= 500000) {
                  salary=(long) (salary * 0.05);
                   resp.getWriter().println("The tax Salary to be paid  for old regemy is : " + salary);
               } else if (salary > 500000 && salary <= 750000) {
                   salary=(long) (salary * 0.20);

                   resp.getWriter().println("The tax Salary to be paid  for old regemy is : " + salary);
               } else if (salary > 750000 && salary <= 1000000) {
                   salary=(long)(salary * 0.20);

                   resp.getWriter().println("The tax Salary to be paid  for old regemy is : " + salary);
               } else if (salary > 1000000 && salary <= 1250000) {
                   salary=(long) (salary * 0.30);

                   resp.getWriter().println("The tax Salary to be paid  for old regemy is : " + salary);
               } else if (salary > 1250000 && salary <= 1500000) {
                   salary=(long)(salary * 0.30);

                   resp.getWriter().println("The tax Salary to be paid  for old regemy is : " + salary);
               } else if (salary > 1500000) {
                   salary=(long) (salary * 0.30);

                   resp.getWriter().println("The tax Salary to be paid  for old regemy is : " + salary);
               }
           }
                    else if(slabs==1){
                        if (salary >= 0 && salary <= 250000) {

                            resp.getWriter().println("The tax Salary to be paid  for new regemy is : " + salary);
                        } else if (salary > 250000 && salary <= 500000) {
                            salary = (long)  (salary * 0.05);

                            resp.getWriter().println("The tax Salary to be paid  for new regemy is : " + salary);
                        } else if (salary > 500000 && salary <= 750000) {
                            salary = (long) (salary * 0.10);

                            resp.getWriter().println("The tax Salary to be paid  for new regemy is : " + salary);
                        } else if (salary > 750000 && salary <= 1000000) {
                            salary = (long)(salary * 0.15);

                            resp.getWriter().println("The tax Salary to be paid  for new regemy is : " + salary);
                        } else if (salary > 1000000 && salary <= 1250000) {
                            salary = (long) (salary * 0.20);
                            resp.getWriter().println("The tax Salary to be paid  for new regemy is : " + salary);
                        } else if (salary > 1250000 && salary <= 1500000) {
                            salary = (long)  (salary * 0.25);

                            resp.getWriter().println("The tax Salary to be paid  for new regemy is : " +salary);
                        } else if (salary > 1500000) {
                            salary = (long) (salary * 0.30);

                            resp.getWriter().println("The tax Salary to be paid  for new regemy is : " + salary);
                        }
                    }

            }

        }


}
