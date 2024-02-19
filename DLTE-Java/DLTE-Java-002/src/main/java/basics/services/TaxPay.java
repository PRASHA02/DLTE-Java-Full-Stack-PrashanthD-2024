package basics.services;

import java.util.Scanner;

public class TaxPay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long salary;
        System.out.println("Enter the salary amount");
        salary = sc.nextLong();
        int slabs;
        System.out.println("Enter the slabs (Old=0/New=1)");
        slabs = sc.nextInt();
        switch(slabs){
            case 0:if(salary>=0 && salary<=250000){
                System.out.println("The tax Salary to be paid  for old regemy is "+salary);
            }else if(salary>250000 && salary<=500000){
                salary = (long) (salary *0.05);
                System.out.println("The tax Salary to be paid  for old regemy is "+salary);
            }else if(salary>500000 && salary<=750000){
                salary = (long) (salary *0.20);
                System.out.println("The tax Salary to be paid  for old regemy is "+salary);
            }else if(salary>750000 && salary<=1000000){
                salary = (long) (salary *0.20);
                System.out.println("The tax Salary to be paid  for old regemy is "+salary);
            }else if(salary>1000000 && salary<=1250000){
                salary = (long) (salary *0.30);
                System.out.println("The tax Salary to be paid  for old regemy is "+salary);
            }else if(salary>1250000 && salary<=1500000){
                salary = (long) (salary *0.30);
                System.out.println("The tax Salary to be paid  for old regemy is "+salary);
            }else if(salary>1500000){
                salary = (long) (salary *0.30);
                System.out.println("The tax Salary to be paid  for old regemy is "+salary);
            }
            break;
            case 1:if(salary>=0 && salary<=250000){
                System.out.println("The tax Salary to be paid  for new regemy is "+salary);
            }else if(salary>250000 && salary<=500000){
                salary = (long) (salary *0.05);
                System.out.println("The tax Salary to be paid  for new regemy is "+salary);
            }else if(salary>500000 && salary<=750000){
                salary = (long) (salary *0.10);
                System.out.println("The tax Salary to be paid  for new regemy is "+salary);
            }else if(salary>750000 && salary<=1000000){
                salary = (long) (salary *0.15);
                System.out.println("The tax Salary to be paid  for new regemy is "+salary);
            }else if(salary>1000000 && salary<=1250000){
                salary = (long) (salary *0.20);
                System.out.println("The tax Salary to be paid  for new regemy is "+salary);
            }else if(salary>1250000 && salary<=1500000){
                salary = (long) (salary *0.25);
                System.out.println("The tax Salary to be paid  for new regemy is "+salary);
            }else if(salary>1500000){
                salary = (long) (salary *0.30);
                System.out.println("The tax Salary to be paid  for new regemy is "+salary);
            }
            break;

        }

    }

}

