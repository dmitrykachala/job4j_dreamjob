package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);

    void save(Candidate candidate);

    void save(User user);

    void delete(int id);

    void deleteCan(int id);

    Post findById(int id);

    Post findByName(String name);

    Candidate findCanById(int id);

    Candidate findCanByName(String name);

    User findUserByEmail(String email);
}
