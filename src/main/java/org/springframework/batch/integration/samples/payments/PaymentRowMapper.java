package org.springframework.batch.integration.samples.payments;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.batch.integration.samples.payments.model.Payment;
import org.springframework.jdbc.core.RowMapper;

public class PaymentRowMapper implements RowMapper<Payment> {

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Payment payment=new Payment();
		payment.setSourceAccountNo(rs.getString("sourceAccountNo"));
		payment.setDestinationAccountNo(rs.getString("destinationAccountNo"));
		payment.setAmount(rs.getBigDecimal("AMOUNT"));
		payment.setDate(rs.getDate("PAY_DATE"));
		return payment;
	}

}
