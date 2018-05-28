package com.thiyagu.learning.spliterator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.StreamSupport;

/**
 * The driver program
 * 
 * @author thiyagu
 *
 */
public class SpendAnalyzer {
	
	 static BiConsumer<Payment.category,Double> biConsumer = (k,v) -> 
	 System.out.println("category : "+k+" Total Amount :" +v);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Payment> payments = createSampleData();
		//uses the native spliterator which is by index gives you the wrong results.
        //payments.parallelStream().collect(new AverageTotalCalculatorByCategory()).forEach(biConsumer);
        
        StreamSupport.stream(new PaymentByCategorySpliterator(payments),true).parallel().collect(new AverageTotalCalculatorByCategory()).forEach(biConsumer);
  
	}
	
	private static List<Payment> createSampleData() {
        List<Payment> paymentList = new ArrayList<>();
        for (int i=0; i<1000; i++) {
            paymentList.add(new Payment(10.0,Payment.category.ALCHOLIC));
            paymentList.add(new Payment(20.0,Payment.category.ALCHOLIC));
            paymentList.add(new Payment(30.0,Payment.category.ALCHOLIC));
            // average = 60

            paymentList.add(new Payment(20.0,Payment.category.ENTERTAINMENT));
            paymentList.add(new Payment(30.0,Payment.category.ENTERTAINMENT));
            paymentList.add(new Payment(40.0,Payment.category.ENTERTAINMENT));
            paymentList.add(new Payment(50.0,Payment.category.ENTERTAINMENT));
            paymentList.add(new Payment(60.0,Payment.category.ENTERTAINMENT));
            // average = 200

            paymentList.add(new Payment(30.0,Payment.category.LIFESTYLE));
            paymentList.add(new Payment(30.0,Payment.category.LIFESTYLE));
            paymentList.add(new Payment(20.0,Payment.category.LIFESTYLE));
            // average = 80
        }
        return paymentList;
    }
}
