package basics.services;

import lombok.*;

import java.util.Date;
@Data
public class TransactionClass {
   private Date date;

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public Double getTransactionAmount() {
      return transactionAmount;
   }

   public void setTransactionAmount(Double transactionAmount) {
      this.transactionAmount = transactionAmount;
   }

   public String getReceipt() {
      return receipt;
   }

   public void setReceipt(String receipt) {
      this.receipt = receipt;
   }

   public String getRemarks() {
      return remarks;
   }

   public void setRemarks(String remarks) {
      this.remarks = remarks;
   }

   public TransactionClass(Date date, Double transactionAmount, String receipt, String remarks) {
      this.date = date;
      this.transactionAmount = transactionAmount;
      this.receipt = receipt;
      this.remarks = remarks;
   }

   private Double transactionAmount;
   private String receipt;
   private  String remarks;

}
