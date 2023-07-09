package br.com.kalogsc.yac.Services;

import br.com.kalogsc.yac.Records.Post;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PostService {
    Map<String, Post> db = new HashMap<>();

    public Collection<Post> createPost(String title, String content, String creator) {
        String id = UUID.randomUUID().toString();
        var newPost = new Post(id, title, content, creator);
        db.put(id, newPost);

        return db.values();
    }

    public Post getPostById(String id) {
        return db.get(id);
    }

    public Collection<Post> listUserPosts(String creator) {
        return db.values()
                .stream()
                .filter(p -> p.creator().equals(creator))
                .toList();
    }
}
