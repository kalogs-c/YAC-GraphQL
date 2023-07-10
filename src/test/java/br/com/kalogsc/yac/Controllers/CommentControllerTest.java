package br.com.kalogsc.yac.Controllers;

import br.com.kalogsc.yac.Records.Comment;
import br.com.kalogsc.yac.Services.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.*;

@Import(CommentService.class)
@GraphQlTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    GraphQlTester graphQlTester;

    @Test
    void createComment() {
        // language=GraphQL
        String document = """
           mutation {
               createComment(content: "My comment", creator: "kalogs-c", postId: "random-post-id") {
                   id,
                   content,
                   postId,
                   creator,
               }
           }
        """;

        graphQlTester.document(document)
                .execute()
                .path("createComment")
                .entityList(Comment.class)
                .hasSize(1);
    }
}