package com.draft.apitest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.draft.apitest.helper.User;
import com.draft.apitest.helper.UserPost;
import com.draft.apitest.helper.UserPostComment;
import static io.restassured.RestAssured.given;
import static java.lang.System.out;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EmailsInCommentsValidityTest {
    private static String baseUrl = "https://jsonplaceholder.typicode.com";
    // private static String baseUrl = "http://localhost:3000";

    @DisplayName("Comments under posts of a specific user should have proper email format")
    @Tags({@Tag("flow"), @Tag("email_format")})
    @ParameterizedTest
    @ValueSource(strings= {"Delphine"})
    public void givenUsername_whenGetCommentsUnderUserPosts_thenEachShouldHaveProperEmailFormat(
        String username) {

        // -- Get userId of specific user = {username}
        Response response = getUsers();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        // List<Map<String, Object>> users = jsonPath.getList(".");
        List<User> users = jsonPath.getList(".", User.class);

        assertThat(users.size(), greaterThan(1));

        users.forEach(_user -> {
            out.println(String.format("id: %d | username: %s", _user.getId(), _user.getUsername()));
        });

        List<User> usersFiltered = 
            users.stream().
                filter(user -> user.getUsername().equals(username)).
                collect(Collectors.toList());

        assertThat(
            String.format("Username:%s should be unique in the system", username), 
            usersFiltered.size(), equalTo(1));

        User user = usersFiltered.get(0);
        // assertThat(user.getUsername(), equalTo("Delphine"));

        final int userId = (int) user.getId();

        // -- Get Posts
        out.println(String.format("- Retreiving posts and corresponding comments for user(%s):", username));

        Response responsePosts = getPostsForSpecificUser(userId);

        responsePosts.then().assertThat().statusCode(200);

        List<UserPost> posts = responsePosts.jsonPath().getList(".", UserPost.class);

        List<UserPost> userPosts = 
            posts.stream().
                filter(post -> post.getUserId() == (userId)).
                collect(Collectors.toList());

        assertThat(
            String.format("User \"%s\" should have at least 1 post", username),
            userPosts.size(), greaterThan(0));
        
        // -- Get Comments under Posts of the user
        userPosts.forEach(_post -> {
            int _postId = _post.getId();

            Response responseComments = getCommentsForThePost(_postId);

            List<UserPostComment> comments = 
                responseComments.jsonPath().getList(".", UserPostComment.class);

            out.println(String.format("-- postId: %d", _postId));

            comments.forEach(_comment -> {

                String emailAddress = _comment.getEmail();
                
                out.println(String.format("---- commentId: %d | email: %s", _comment.getId(), emailAddress));

                assertThat(
                    String.format("Email address in comment section should have proper format: \"%s\"", emailAddress), 
                    isEmailValid(emailAddress), equalTo(true));

            });
        });

    }

    @DisplayName("circleci failed testcase check")
    @Tag("failcheck")
    @Test
    public void failedCheckTest() {
        assertThat(true, is(true));
    }

    public boolean isEmailValid(String emailAddress) {
        
        return EmailValidator.getInstance().isValid(emailAddress);

    }

    Response getUsers() {
        String endPoint = baseUrl + "/users";

        var result = 
            given().
                log().method().
                log().uri().
            when().
                get(endPoint).
            then().
                log().status();

        var response = result.extract().response();

        return (Response) response;

    }

    Response getPostsForSpecificUser(int userId) {
        String endPoint = baseUrl + "/posts";

        var result = 
            given().
                log().method().
                log().uri().
                queryParam("userId", userId).
            when().
                get(endPoint).
            then().
                log().status();

        var response = result.extract().response();

        return (Response) response;

    }

    Response getCommentsForThePost(int postId) {

        String endPoint = baseUrl + "/comments";

        var result = 
            given().
                log().method().
                log().uri().
                queryParam("postId", postId).
            when().
                get(endPoint).
            then().
                log().status();

        var response = result.extract().response();

        return (Response) response;

    }

}