package com.graalvm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfigService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateOrInsertConfig(Config config) {
        config.setUsername("Testing");
        config.setEmail("Testing@gmail.com");
        if (configExists()) {
            return updateIndexConfig(config);
        }
        return 0;
    }

    public Config getConfig() {
        try {
            String sql = "SELECT * FROM Config LIMIT 1";
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Config config = new Config();
                config.setUsername(rs.getString("username"));
                config.setEmail(rs.getString("email"));
                return config;
            });
        } catch (Exception e) {
            log.error("There are no records found for the configuration.");
            return new Config();
        }
    }

    private boolean configExists() {
        String sql = "SELECT COUNT(*) FROM Config";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count > 0;
    }

    private int updateIndexConfig(Config indexConfig) {
        String sql = "UPDATE Config SET " +
                "username = ?, " +
                "email = ?" +
                "WHERE id = (SELECT MIN(id) FROM Config) ";

        return jdbcTemplate.update(sql,
                "Testing",
                "Testing@gmail.com");
    }
}
