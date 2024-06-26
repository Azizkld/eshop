package com.billcom.eshop.Request;
// Java Program to Illustrate EmailDetails Class


// Importing required classes
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class EmailDetails {

    // Class data members
    private String recipient;
    private String msgBody;
    @Lob
    @Column(length = 10000,name="subject")//
    private String subject;
    private String attachment;
}
