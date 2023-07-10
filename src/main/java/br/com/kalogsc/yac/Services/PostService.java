package br.com.kalogsc.yac.Services;

import br.com.kalogsc.yac.Records.Post;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PostService {
    Map<String, Post> db = new HashMap<>();

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 3; i++) {
            String id = String.format("id-%d", i);
            String title = String.format("Example post %d", i);
            String content = String.format("This is my %d° post", i);
            var post = new Post(id, title, content, "generated");

            db.put(id, post);
        }
    }

    public Collection<Post> createPost(String title, String content, String creator) {
        String id = UUID.randomUUID().toString();
        var newPost = new Post(id, title, content, creator);
        db.put(id, newPost);

        return listUserPosts(creator);
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
