package com.keepstudy.service;

import java.io.IOException;
import java.sql.SQLException;

public interface DatabaseService {

    void generate() throws SQLException, ClassNotFoundException, IOException;

}
