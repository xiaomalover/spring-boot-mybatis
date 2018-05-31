package com.weison.controller;

import java.io.IOException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.weison.exception.StorageFileNotFoundException;
import com.weison.service.StorageService;

/**
 * 文件上传控制器类
 */
@Controller
@RequestMapping(value = "/file")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * 文获取件列表
     * @param model UI Model
     * @return 模版
     * @throws IOException IO异常
     */
    @GetMapping("/list")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute(
            "files",
            storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(
                    FileUploadController.class,
                    "serveFile",
                    path.getFileName().toString()
                ).build().toString()
            ).collect(Collectors.toList())
        );

        return "uploadForm";
    }

    /**
     * 下载文件
     * @param filename 文件路径
     * @return 文件资源
     */
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }

    /**
     *
     * @param file 文件
     * @param redirectAttributes 跳转对象
     * @return 跳转
     */
    @PostMapping("/list")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute(
            "message",
            "文件上传成功：" + file.getOriginalFilename() + "!"
        );
        return "redirect:/file/list";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound() {
        return ResponseEntity.notFound().build();
    }

}
