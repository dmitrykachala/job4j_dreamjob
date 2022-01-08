package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

public class MainStore {

    public static void main(String[] args) {

        Store store = DbStore.instOf();

        Post p1 = new Post(0, "Jedi");
        store.save(new Post(0, "Java Job"));
        store.save(p1);
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }

        Post p2 = new Post(1, "Middle");
        store.save(p2);

        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }

        for (var can : store.findAllCandidates()) {
            System.out.println(can);
        }

        Candidate c1 = new Candidate(0, "qqq");
        Candidate c2 = new Candidate(0, "www");
        Candidate c3 = new Candidate(0, "eee");

        store.save(c1);
        store.save(c2);
        store.save(c3);
        store.save(new Candidate(2, "rrr"));

        System.out.println(store.findCanById(3));

        store.delete(1);

        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }

        for (var can : store.findAllCandidates()) {
            System.out.println(can);
        }

    }
}
