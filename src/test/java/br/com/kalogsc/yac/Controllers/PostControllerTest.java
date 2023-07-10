package br.com.kalogsc.yac.Controllers;

import br.com.kalogsc.yac.Records.Post;
import br.com.kalogsc.yac.Services.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.*;

@Import(PostService.class)
@GraphQlTest(PostController.class)
class PostControllerTest {
    @Autowired
    GraphQlTester graphQlTester;

    @Test
    void createPost() {
        // language=GraphQL
        String document = """
            mutation {
                createPost(title: "My post", content: "This is my post", creator: "kalogs-c") {
                    id,
                    title,
                    content,
                    creator,
                }
            }
        """;

        graphQlTester.document(document)
                .execute()
                .path("createPost")
                .entityList(Post.class)
                .hasSize(1);
    }

    @Test
    void getPostById() {
        // language=GraphQL
        String document = """
            query {
                getPostById(id: "id-1") {
                    id,
                    title,
                    content,
                    creator,
                }
            }
        """;

        graphQlTester.document(document)
                .execute()
                .path("getPostById")
                .entity(Post.class)
                .satisfies(post -> {
                    assertEquals("Example post 1", post.title());
                    assertEquals("This is my 1° post", post.content());
                });
    }

    @Test
    void listUserPosts() {
        // language=GraphQL
        String document = """
            query listUserPosts($creator: String!) {
                listUserPosts(creator: $creator) {
                    id,
                    title,
                    content,
                    creator,
                }
            }
        """;

        graphQlTester.document(document)
                .variable("creator", "generated")
                .execute()
                .path("listUserPosts")
                .entityList(Post.class)
                .hasSize(3);
    }
}