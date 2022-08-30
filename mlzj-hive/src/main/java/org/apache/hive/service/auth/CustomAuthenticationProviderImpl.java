package org.apache.hive.service.auth;


import javax.security.sasl.AuthenticationException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.slf4j.Logger;

public class CustomAuthenticationProviderImpl implements org.apache.hive.service.auth.PasswdAuthenticationProvider {

    private final Logger LOG = org.slf4j.LoggerFactory.getLogger(CustomAuthenticationProviderImpl.class);

    private static final String HIVE_JDBC_PASSWD_AUTH_PREFIX = "hive.jdbc_passwd.auth.%s";

    private Configuration conf = null;

    private HiveConf hiveConf;

    @Override
    public void Authenticate(String userName, String passwd) throws AuthenticationException {
        LOG.info("user: " + userName + " try login.");
        String passwdConf = getConf().get(String.format(HIVE_JDBC_PASSWD_AUTH_PREFIX, userName));
        if (passwdConf == null) {
            String message = "user's ACL Configuration is not found. user:" + userName;
            LOG.info(message);
            throw new AuthenticationException(message);
        }
        if (!passwd.equals(passwdConf)) {
            String message = "user name and password is mismatch. user:" + userName;
            throw new AuthenticationException(message);
        }
    }

    public Configuration getConf() {
        if (hiveConf == null) {
            hiveConf = new HiveConf();
        }
        if (conf == null) {
            this.conf = new Configuration(hiveConf);
        }
        return conf;
    }

    public void init() {

    }

    public CustomAuthenticationProviderImpl(HiveConf conf) {
        this.hiveConf = conf;
    }

    public CustomAuthenticationProviderImpl() {
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }
}
