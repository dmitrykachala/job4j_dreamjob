package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import java.util.Collection;

public interface Store {

    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);

    void save(Candidate candidate);

    void save(User user);

    Post findById(int id);

    Post findByName(String name);

    void remove(Post post);

    void remove(Candidate candidate);

    Candidate findCanById(int id);

    Candidate findCanByName(String name);

    User findUserByEmail(String email);

    City findCityById(int id);

    Collection<City> findAllCities();

    Collection<Candidate> findLastDayCandidates();

    Collection<Post> findLastDayPosts();
}
