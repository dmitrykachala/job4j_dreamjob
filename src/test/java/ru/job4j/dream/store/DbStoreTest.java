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
    public void whenFindAndCreateAndUpdateAndDeletePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        Post postInDb = store.findById(post.getId());

        assertThat(postInDb.toString(), is(store.findById(1).toString()));

        assertThat(postInDb.getName(), is(post.getName()));

        store.save(new Post(1, "Job"));
        postInDb = store.findById(post.getId());

        assertThat(postInDb.getName(), is("Job"));

        store.delete(1);

        assertNull(store.findById(1));
    }

    @Test
    public void whenFindAndCreateAndUpdateAndDeleteCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Job");
        store.save(candidate);
        Candidate canInDb = store.findCanById(candidate.getId());

        assertThat(canInDb.toString(), is(store.findCanById(1).toString()));

        assertThat(canInDb.getName(), is(candidate.getName()));

        store.save(new Candidate(1, "Job"));
        canInDb = store.findCanById(candidate.getId());

        assertThat(canInDb.getName(), is("Job"));

        store.deleteCan(1);

        assertNull(store.findCanById(1));
    }

}