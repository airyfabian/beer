package com.cleveritgroup.airyfabian.test.beers.currency;

import java.math.BigDecimal;

/**
 * The enum Currency.
 */
public enum Currency {

    /**
     * The Usd.
     */
    USD(new BigDecimal("1")), // USA 1 USD as reference
    /**
     * The Eur.
     */
    EUR(new BigDecimal("0.89")), // Euros
    /**
     * The Gbp.
     */
    GBP(new BigDecimal("0.76")), // Pounds
    /**
     * The Cny.
     */
    CNY(new BigDecimal("6.38")), // chine
    /**
     * The Rub.
     */
    RUB(new BigDecimal("74.09")), // ruse
    /**
     * The Jpy.
     */
    JPY(new BigDecimal("113.63")), // japon
    /**
     * The Clp.
     */
    CLP(new BigDecimal("855.16")); // chile

    private BigDecimal amount;

    Currency(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
