package store.ggun.alarm.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.alarm.domain.model.NotificationModel;
import store.ggun.alarm.serviceImpl.NotificationServiceImpl;


@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping(path = "/notifications")
public class NotificationController {
    private final NotificationServiceImpl notificationServiceImpl;

    @PostMapping("/subscribe")
    public Mono<NotificationModel> createNoticeModel(@RequestBody NotificationModel notification) {
        return notificationServiceImpl.createNoticeModel(notification);
    }

    @PostMapping("/{id}/respond")
    public Mono<NotificationModel> respondToNoticeModel(@PathVariable("id") String id, @RequestParam("status") String status) {
        return notificationServiceImpl.updateNoticeModelStatus(id, status);
    }

    @GetMapping(value = "/admin", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<NotificationModel> subscribeToAdminNoticeModels() {
        return notificationServiceImpl.getAdminNoticeModels();
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<NotificationModel> subscribeToUserNoticeModels(@PathVariable("userId") String userId) {
        return notificationServiceImpl.getUserNoticeModels(userId);
    }

    @GetMapping("/admin/{adminId}")
    public Flux<NotificationModel> getNotificationsByAdminId(@PathVariable("adminId") String lawyerId) {
        return notificationServiceImpl.getNotificationsByAdminId(lawyerId);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteNotification(@PathVariable("id") String id) {
        return notificationServiceImpl.deleteNotification(id);
    }
    //---------------------------------------------------------------------------


}
