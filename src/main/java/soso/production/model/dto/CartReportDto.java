package soso.production.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CartReportDto {
    private Long id;
    private Date date;
    private BigDecimal fullPrice;
    private String email;
    private String phoneNumber;

    public CartReportDto() {}
    public CartReportDto(Long id, Date date, BigDecimal fullPrice,
                         String email, String phoneNumber) {
        this.id = id;
        this.date = date;
        this.fullPrice = fullPrice;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
