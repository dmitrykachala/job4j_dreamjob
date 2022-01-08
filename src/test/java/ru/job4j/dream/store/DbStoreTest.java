package ru.job4j.dream.store;

import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class DbStoreTest {
    @Test
    public void whenCreatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        store.save(new Post(1, "Job"));
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is("Job"));
    }

    @Test
    public void whenDeletePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        Post postInDb = store.findById(post.getId());
        store.delete(1);
        assertNull(store.findById(1));
    }

    @Test
    public void whenFindPost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.toString(), is(store.findById(1).toString()));
    }

    @Test
    public void whenCreateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Job");
        store.save(candidate);
        Candidate canInDb = store.findCanById(candidate.getId());
        assertThat(canInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Job");
        store.save(candidate);
        store.save(new Candidate(1, "Job"));
        Candidate canInDb = store.findCanById(candidate.getId());
        assertThat(canInDb.getName(), is("Job"));
    }

    @Test
    public void whenDeleteCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Job");
        store.save(candidate);
        Candidate canInDb = store.findCanById(candidate.getId());
        store.deleteCan(1);
        assertNull(store.findCanById(1));
    }

    @Test
    public void whenFindPCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Job");
        store.save(candidate);
        Candidate canInDb = store.findCanById(candidate.getId());
        assertThat(canInDb.toString(), is(store.findCanById(1).toString()));
    }
}