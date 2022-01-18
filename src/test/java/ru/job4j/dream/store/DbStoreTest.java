package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class DbStoreTest {

    @Before
    public void clear() {
        BasicDataSource pool = new BasicDataSource();
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(
                        DbStore.class.getClassLoader()
                                .getResourceAsStream("db.properties")
                )
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DROP TABLE IF EXISTS post;\n" + "\n"
                     + "CREATE TABLE IF NOT EXISTS post (\n" + "    id SERIAL PRIMARY KEY,\n"
                     + "    name TEXT,\n" + "    created DATE,\n" + "    description TEXT\n"
                     + ");\n" + "\n" + "DROP TABLE IF EXISTS candidate;\n" + "\n"
                     + "CREATE TABLE IF NOT EXISTS candidate (\n" + "    id SERIAL PRIMARY KEY,\n"
                     + "    name TEXT,\n" + "    created DATE,\n" + "    id_city integer\n"
                     + ");\n" + "\n" + "DROP TABLE IF EXISTS cities;\n" + "\n"
                     + "CREATE TABLE IF NOT EXISTS cities (\n" + "    id SERIAL PRIMARY KEY,\n"
                     + "    name TEXT\n" + ");\n" + "\n"
                     + "INSERT INTO cities(name) values ('Мск'),('Спб'),('Екб');")
        ) {
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindAndCreateAndUpdateAndDeletePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job").setDescription("smth").setCreated(new Date());
        store.save(post);
        Post postInDb = store.findById(post.getId());

        assertThat(postInDb.toString(), is(store.findById(1).toString()));

        assertThat(postInDb.getName(), is(post.getName()));

        store.save(new Post(1, "Job").setDescription("smth").setCreated(new Date()));
        postInDb = store.findById(post.getId());

        assertThat(postInDb.getName(), is("Job"));

        store.remove(store.findById(1));

        assertNull(store.findById(1));
    }

    @Test
    public void whenFindAndCreateAndUpdateAndDeleteCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Job").setCityId(1).setCreated(new Date());
        store.save(candidate);
        Candidate canInDb = store.findCanById(candidate.getId());

        assertThat(canInDb.toString(), is(store.findCanById(1).toString()));

        assertThat(canInDb.getName(), is(candidate.getName()));

        store.save(new Candidate(1, "Job").setCityId(1).setCreated(new Date()));
        canInDb = store.findCanById(candidate.getId());

        assertThat(canInDb.getName(), is("Job"));

        store.remove(store.findCanById(1));

        assertNull(store.findCanById(1));
    }

}