package basics.loans;

public interface MyBank {
    void writeFile();
    void readFile();
    void addNewLoan(LoanProduct loanProduct);
    void availableLoans();
    void closedLoans();
}
