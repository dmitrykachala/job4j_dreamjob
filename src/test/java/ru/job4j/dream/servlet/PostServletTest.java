package ru.job4j.dream.servlet;

import org.junit.Test;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServletTest {

    @Test
    public void whenCreatePost() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("name of new post");
        when(req.getParameter("description")).thenReturn("d");
        new PostServlet().doPost(req, resp);
        Post post = DbStore.instOf().findByName("name of new post");
        assertThat(post, notNullValue());
    }

}