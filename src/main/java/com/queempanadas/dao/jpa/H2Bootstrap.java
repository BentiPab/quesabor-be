package com.queempanadas.dao.jpa;

import org.h2.util.ScriptReader;
import org.h2.util.StringUtils;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionImpl;
import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class H2Bootstrap extends AbstractJPADAO<Void> {

    public H2Bootstrap(Class<Void> clazz) {
        super(clazz);
    }

    public void load(boolean startWebServer, String runScript) {
        try {
            Server tcpServer = Server.createTcpServer().start();
            Configuration configuration = new Configuration().configure();
            Properties props = configuration.getProperties();
            if (props.getProperty(AvailableSettings.DRIVER).contains(".h2.")) {
                if (startWebServer) {
                    Server server = Server.createWebServer().start();
                }
                try (InputStream is = H2Bootstrap.class.getResourceAsStream(runScript)) {
                    Connection s = ((SessionImpl) getSession()).getJdbcConnectionAccess().obtainConnection();
                    Reader reader = new InputStreamReader(is);
                    Statement stat = s.createStatement();
                    ScriptReader r = new ScriptReader(reader);
                    while (true) {
                        String sql = r.readStatement();
                        if (sql == null) {
                            break;
                        }
                        if (StringUtils.isWhitespaceOrEmpty(sql)) {
                            continue;
                        }
                        stat.executeUpdate(sql);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
