package com.thiyagu.learning.spliterator;

import java.util.Date;

/**
 * Represents the payment model
 *
 * @author thiyagu
 *
 */
public class Payment {

	/**
	 * The Payment Category Enum
	 *
	 */
	public enum category {
		ALCHOLIC, NONALCHOLIC, LIFESTYLE, ENTERTAINMENT
	}

	/**
	 * The amount spent
	 */
	private Double amountSpent;

	/**
	 * The Payment Category
	 */
	private category category;

	/**
	 * The date on which the amount spent
	 */
	private Date dateSpent;

	/**
	 * The payment constructor
	 * 
	 * @param amountSpent
	 * @param category
	 */
	public Payment(Double amountSpent, com.thiyagu.learning.spliterator.Payment.category category) {
		super();
		this.amountSpent = amountSpent;
		this.category = category;
	}

	/**
	 * @return the amountSpent
	 */
	public Double getAmountSpent() {
		return amountSpent;
	}

	/**
	 * @param amountSpent
	 *            the amountSpent to set
	 */
	public void setAmountSpent(Double amountSpent) {
		this.amountSpent = amountSpent;
	}

	/**
	 * @return the category
	 */
	public category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(category category) {
		this.category = category;
	}

	/**
	 * @return the dateSpent
	 */
	public Date getDateSpent() {
		return dateSpent;
	}

	/**
	 * @param dateSpent
	 *            the dateSpent to set
	 */
	public void setDateSpent(Date dateSpent) {
		this.dateSpent = dateSpent;
	}

}
