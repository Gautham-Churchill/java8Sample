package com.thiyagu.learning.spliterator;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * The PaymentByCategorySpliterator implementation which split the stream based
 * on payment category
 * 
 * @author thiyagu
 *
 */
public class PaymentByCategorySpliterator implements Spliterator<Payment> {

	private List<Payment> paymentList;
	private int current;
	private int last; // inclusive

	public PaymentByCategorySpliterator(List<Payment> payment) {
		this.paymentList = payment;
		last = paymentList.size() - 1;
	}

	public PaymentByCategorySpliterator(List<Payment> payment, int start, int last) {
		this.paymentList = payment;
		this.current = start;
		this.last = last;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Payment> action) {
		if (current <= last) {
			action.accept(paymentList.get(current));
			current++;
			return true;
		}
		return false;
	}

	@Override
	public Spliterator<Payment> trySplit() {
		if ((last - current) < 100) {
			return null;
		}

		// start from middle element
		int splitPosition = current + (last - current) / 2;
		// if the categories are the same, we can't split here, as we are in the middle
		// of a batch
		Payment.category categoryBeforeSplit = paymentList.get(splitPosition - 1).getCategory();
		Payment.category categoryAfterSplit = paymentList.get(splitPosition).getCategory();

		// keep moving forward until we reach a split between categories
		while (categoryBeforeSplit.equals(categoryAfterSplit)) {
			splitPosition++;
			categoryBeforeSplit = categoryAfterSplit;
			categoryAfterSplit = paymentList.get(splitPosition).getCategory();
		}

		// safe to create a new spliterator
		PaymentByCategorySpliterator secondHalf = new PaymentByCategorySpliterator(paymentList, splitPosition, last);
		// reset our own last value
		last = splitPosition - 1;

		return secondHalf;
	}

	@Override
	public long estimateSize() {
		return last - current;
	}

	@Override
	public int characteristics() {
		return 0;
	}

}
