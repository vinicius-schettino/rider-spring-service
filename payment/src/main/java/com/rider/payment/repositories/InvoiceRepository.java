package com.rider.payment.repositories;
import com.rider.payment.entities.invoice.Invoice;
import com.rider.payment.entities.invoice.InvoiceType;
import com.rider.payment.entities.payment.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    List<Invoice> findAllByAmount(Double Amount);
    List<Invoice> findAllByPaymentStatus(PaymentStatus paymentStatus);
    List<Invoice> findAllByInvoiceType(InvoiceType invoiceType);
    List<Invoice> findAllByUserName(String UserName);
    List<Invoice> findAllByDriverName(String DriverName);
    List<Invoice> findAllByRideDate(Date RideDate);

}
