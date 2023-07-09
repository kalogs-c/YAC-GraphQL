package br.com.kalogsc.yac.Controllers;

import br.com.kalogsc.yac.Records.Comment;
import br.com.kalogsc.yac.Records.Post;
import br.com.kalogsc.yac.Services.CommentService;
import br.com.kalogsc.yac.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class PostController {
    @Autowired
    PostService service;

    @QueryMapping
    public Post getPostById(@Argument String id) {
        return service.getPostById(id);
    }

    @MutationMapping
    public Collection<Post> createPost(@Argument String title, @Argument String content, @Argument String creator) {
        return service.createPost(title, content, creator);
    }

    @QueryMapping
    public Collection<Post> listUserPosts(@Argument String creator) {
        return service.listUserPosts(creator);
    }
}
