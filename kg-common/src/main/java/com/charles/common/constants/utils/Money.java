package com.charles.common.constants.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money implements Serializable, Comparable<Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4668709424612725772L;
	
	/**
	 * 缺省的币种代码，为CNY（人民币）
	 */
	private static final String DEFAULT_CURRENCY_CODE = "CNY";
	
	/**
	 * 缺省的取整模式（四舍五入）
	 */
	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
	
	/**
     * 金额，以元为单位
     */
    private BigDecimal               amount;
    
	/**
     * 币种。
     */

    private Currency                 currency;
    
    /**
     * 创建一个具有缺省金额（0）和缺省币种的货币对象
     */
    public Money() {
    	this("0");
    }
    
    /**
     * 创建一个具有金额<code>yuan</code>元<code>cent</code>分和缺省币种的货币对象
     * @param yuan
     * @param cent
     */
    public Money(long yuan, int cent) {
    	this(yuan, cent, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }
    
    /**
     * 创建一个具有金额<code>yuan</code>元<code>cent</code>分和指定币种的货币对象
     * @param yuan
     * @param cent
     * @param currency
     */
    public Money(long yuan, int cent, Currency currency) {
    	this.currency = currency;
    	this.setAmount(new BigDecimal(yuan).add(new BigDecimal(cent).divide(getCentFactor())));
    }
    /**
     * 创建一个具有金额<code>amount</code>和缺省币种的货币对象
     * @param string
     */
    public Money(String amount) {
    	this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
	}
    
    /**
     * 创建一个具有金额<code>amount</code>元和制动币种<code>currency</code>的货币对象
     * @param amount
     * @param currency
     */
    public Money(String amount, Currency currency) {
    	this(new BigDecimal(amount), currency);
    }
    
    /**
     * 创建一个具有<code>amount</code>元和指定币种<code>currency</code>的货币对象
     * 如果金额不能转换为整数分，则使用指定的取整模式<code>roundingMode</code>取整
     * @param amount
     * @param currency
     * @param roundingMode
     */
    public Money(String amount, Currency currency, RoundingMode roundingMode) {
    	this(new BigDecimal(amount), currency, roundingMode);
    }
    /**
     * 创建一个具有金额<code>amount</code>和指定币种的货币对象。
     * 如果金额不能转换为整数分，则使用缺省的取整模式<code>DEFAULT_ROUNDING_MODE</code>进行取整。
     * @param amount
     * @param currency
     */
    public Money(BigDecimal amount, Currency currency) {
    	this(amount, currency, DEFAULT_ROUNDING_MODE);
    }
    
    /**
     * 创建一个具有金额和指定币种的货币对象，
     * 如果金额不能转换为整数分，则使用指定的取整模式<code>roundingMode</code> 取整
     * @param amount
     * @param currency
     * @param roundingMode
     */
    public Money(BigDecimal amount, Currency currency, RoundingMode roundingMode) {
    	this.currency = currency;
    	this.setAmount(amount, roundingMode);
    }
	/**
     * 对象比较。
     *
     * <p>
     * 比较本对象与另一对象的大小。
     * 如果待比较的对象的类型不是<code>Money</code>，则抛出<code>java.lang.ClassCastException</code>。
     * 如果待比较的两个货币对象的币种不同，则抛出<code>java.lang.IllegalArgumentException</code>。
     * 如果本货币对象的金额少于待比较货币对象，则返回-1。
     * 如果本货币对象的金额等于待比较货币对象，则返回0。
     * 如果本货币对象的金额大于待比较货币对象，则返回1。
     *
     * @param other 另一对象。
     * @return -1表示小于，0表示等于，1表示大于。
     *
     * @exception ClassCastException 待比较货币对象不是<code>Money</code>。
     *            IllegalArgumentException 待比较货币对象与本货币对象的币种不同。
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
	public int compareTo(Object other) {
		return compareTo((Money)other);
	}
	
	/**
	 * 货币比较
	 * <p>
	 * 比较本货币对象与另一货币对象的大小
	 * 如果待比较的两个货币对象的币种不同，则抛出<code>java.lang.IllegalArugmentException</code>
	 * 如果本货币对象的金额少于待比较货币对象的金额，则返回-1;
	 * 如果本货币对象的金额等于待比较货币对象的金额，则返回0;
	 * 如果本货币对象的金额大于待比较货币对象的金额，则返回1。
	 * </p>
	 * @param other
	 * @return	-1表示小于，0表示等于，1表示大于
	 */
	public int compareTo(Money other) {
		assertSameCurrencyAs(other);
		
		return amount.compareTo(other.amount);
	}
	
	/**
	 * 断言本货币对象与另一货币对象是否具有相同的币种
	 * 
	 * 如果本货币对象与另一货币对象具有相同的币种，则方法返回
	 * 否则抛出运行时异常<code>java.lang.IllegalArgumentException</code>
	 * @param other
	 */
	private void assertSameCurrencyAs(Money other) {
		if(!currency.equals(other.currency)) {
			throw new IllegalArgumentException("Money math currency mismatch.");
		}
	}
	
	/**
	 * 货币累加
	 * <p>
	 * 如果两货币币种相同，则本货币对象的金额等于两货币对象金额之和，并返回本货币对象的引用。
	 * 如果两货币对象币种不同，抛出<code>java.lang.IllegalArgumentException</code>
	 * </p>
	 * @param other	作为加数的货币对象
	 * @return		累加后的本货币对象
	 */
	public Money addTo(Money other) {
        assertSameCurrencyAs(other);
        this.amount = this.amount.add(other.amount);
        return this;
    }
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount, RoundingMode roundingMode) {
		if (amount != null) {
            this.amount = amount.setScale(currency.getDefaultFractionDigits(), roundingMode);
        }
	}
	
	public void setAmount(BigDecimal amount) {
		this.setAmount(amount, DEFAULT_ROUNDING_MODE);
	}
	
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	/**
	 * 获取本货币币种的元/分换算比率
	 * @return
	 */
	public BigDecimal getCentFactor() {
		return new BigDecimal(10).pow(currency.getDefaultFractionDigits());
	}
}
