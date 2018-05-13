package com.CouponSystem;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.CouponSystem.Exceptions.NullConnectionException;

@SpringBootApplication
public class CouponSystemApplication 
{

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException 
	{
		SpringApplication.run(CouponSystemApplication.class, args);
	}

}
