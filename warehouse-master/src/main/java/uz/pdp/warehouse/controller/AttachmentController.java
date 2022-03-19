package uz.pdp.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.AttachmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping
    public Result upload(MultipartHttpServletRequest request) {
        return attachmentService.upload(request);
    }

}
