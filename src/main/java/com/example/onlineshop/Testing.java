package com.example.onlineshop;

import java.sql.Connection;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public class Testing {
    Connection con = (Connection) getServletContext().getAttribute("DBConnection");
}
