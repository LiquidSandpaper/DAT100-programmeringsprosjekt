package no.hvl.dat100.javel.oppgave5;

import no.hvl.dat100.javel.oppgave3.Customer;
import no.hvl.dat100.javel.oppgave3.PowerAgreementType;
import no.hvl.dat100.javel.oppgave2.MonthlyPower;

public class Invoice {

    private Customer customer; // customer associated with this invoice
    private String month; // month that the invoice covers
    private double[][] usage; // power usage this month (per day and per hour)
    private double[][] powerPrices; // power prices for this month

    private double amount; // power price for this month


    public Invoice(Customer customer, String month, double[][] usage, double[][] powerPrices) {
        this.customer = customer;
        this.month = month;
        this.usage = usage;
        this.powerPrices = powerPrices;
        this.amount = 0;
    }

    public void computeAmount() {
        PowerAgreementType powerAgreement = this.customer.getAgreement();
        
        switch(powerAgreement) {
            case PowerAgreementType.SPOTPRICE: {
                this.amount = MonthlyPower.computeSpotPrice(usage, powerPrices);
                return;
            }

            case PowerAgreementType.POWERSUPPORT: {

                this.amount = MonthlyPower.computeSpotPrice(usage, powerPrices);
                this.amount -= MonthlyPower.computePowerSupport(usage, powerPrices);
                return;
            }

            case PowerAgreementType.NORGESPRICE: {
                this.amount = MonthlyPower.computeNorgesPrice(usage);
                return;
            }
        }
    }

    public void printInvoice() {
        String formattedIvoice = String.format("""
        %s,

        month:      %s,
        usage:      %.2f kWh,
        fee:        %.2f NOK""",
            customer.toString(),
            month,
            MonthlyPower.computePowerUsage(usage),
            amount
        );

        System.out.println(formattedIvoice);
    }
}
