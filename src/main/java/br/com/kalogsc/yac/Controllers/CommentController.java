package br.com.kalogsc.yac.Controllers;

import br.com.kalogsc.yac.Records.Comment;
import br.com.kalogsc.yac.Records.Post;
import br.com.kalogsc.yac.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class CommentController {
    @Autowired
    CommentService service;

    // Bind the comments when querying posts
    @SchemaMapping
    public Collection<Comment> comments(Post post) {
        return service.listCommentsByPost(post.id());
    }

    @MutationMapping
    public Collection<Comment> createComment(@Argument String content, @Argument String postId, @Argument String creator) {
        return service.createComment(content, postId, creator);
    }
}
