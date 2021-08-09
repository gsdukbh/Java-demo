package top.werls.springbootuploadfile.controller;

import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.werls.springbootuploadfile.Exception.StorageException;
import top.werls.springbootuploadfile.Service.StorageService;
import top.werls.springbootuploadfile.util.MinioUtil;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

/**
 * @author leejiawei
 */
@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;


    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        var filesObj= storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList());

        model.addAttribute("files",filesObj);

        var list = MinioUtil.listObjects();
        System.out.println("-----------------");
        list.forEach(itemResult -> {
            try {
                var item = itemResult.get();

                System.out.println(item.objectName());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        System.out.println("-----------------");
        return "uploadForm";
    }

    /**
     * 正则
     * @param filename 文件名
     * @return 二进制
     */
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        MinioUtil.fileUpload(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageException exc) {
        return ResponseEntity.notFound().build();
    }

}