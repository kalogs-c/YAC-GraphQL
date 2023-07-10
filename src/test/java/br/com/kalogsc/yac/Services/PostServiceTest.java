package br.com.kalogsc.yac.Services;

import br.com.kalogsc.yac.Records.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceTest {
    @Autowired
    PostService service;

    @Test
    void createPost() {
        Collection<Post> posts = service.createPost("New", "Post", "kalogs-c");

        assertEquals(1, posts.size());
    }

    @Test
    void getPostById() {
        var post = service.getPostById("id-1");

        assertEquals("Example post 1", post.title());
        assertEquals("generated", post.creator());
    }

    @Test
    void listUserPosts() {
        Collection<Post> posts = service.listUserPosts("generated");

        assertEquals(3, posts.size());
    }
}