package br.com.kalogsc.yac.Services;

import br.com.kalogsc.yac.Records.Comment;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CommentService {
    Map<String, Comment> db = new HashMap<>();

    public Collection<Comment> createComment(String content, String postId, String creator) {
        String id = UUID.randomUUID().toString();
        var newComment = new Comment(id, content, postId, creator);
        db.put(id, newComment);

        return listCommentsByPost(postId);
    }

    public Collection<Comment> listCommentsByPost(String postId) {
        return db.values()
                .stream()
                .filter(c -> c.postId().equals(postId))
                .toList();
    }
}
