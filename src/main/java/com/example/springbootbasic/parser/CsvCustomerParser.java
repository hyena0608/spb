package com.example.springbootbasic.parser;

import com.example.springbootbasic.domain.customer.Customer;
import com.example.springbootbasic.domain.customer.CustomerStatus;

import static com.example.springbootbasic.exception.ParserException.*;
import static com.example.springbootbasic.util.CharacterUnit.SPACE;

public class CsvCustomerParser {
    private static final int CUSTOMER_ID_INDEX = 0;
    private static final int CUSTOMER_STATUS_INDEX = 1;

    public Customer parseToCustomerFrom(String customerText) {
        String[] customerPiece = customerText.split(SPACE.getUnit());
        Long customerId = Long.parseLong(customerPiece[CUSTOMER_ID_INDEX]);
        String customerType = customerPiece[CUSTOMER_STATUS_INDEX];
        CustomerStatus findCustomerStatus = CustomerStatus.findCustomerBy(customerType)
                .orElseThrow(() -> new IllegalArgumentException(CSV_PARSER_EXCEPTION.getMessage()));
        return new Customer(customerId, findCustomerStatus);
    }
}
