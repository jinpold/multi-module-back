package store.ggun.alarm.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.alarm.domain.model.NotificationModel;
import store.ggun.alarm.serviceImpl.NotificationServiceImpl;
import store.ggun.alarm.domain.model.PostModel;
import store.ggun.alarm.serviceImpl.PostService;


@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;
    private final NotificationServiceImpl notificationServiceImpl;

    public PostController(PostService postService, NotificationServiceImpl notificationServiceImpl) {
        this.postService = postService;
        this.notificationServiceImpl = notificationServiceImpl;
    }

    @PostMapping("/create")
    public Mono<PostModel> createPost(@RequestBody PostModel postModel) {
        notificationServiceImpl.sendNotification(NotificationModel.builder()
                .id("2")
                .message("새 문의글이 등록되었습니다.")
                .userId("admin")
                .response(null)
                .adminId(null)
                .status("공지")
                .build());
        return postService.createPost(postModel);
    }

    @GetMapping("/list")
    public Flux<PostModel> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/find/{id}")
    public Mono<PostModel> getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deletePostById(@PathVariable String id) {
        return postService.deletePostById(id);
    }
}
