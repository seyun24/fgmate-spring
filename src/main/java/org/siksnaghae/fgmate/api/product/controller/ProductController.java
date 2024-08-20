package org.siksnaghae.fgmate.api.product.controller;

import lombok.RequiredArgsConstructor;
import org.siksnaghae.fgmate.api.product.model.Product;
import org.siksnaghae.fgmate.api.product.model.ProductDto;
import org.siksnaghae.fgmate.api.product.service.ProductService;
import org.siksnaghae.fgmate.common.response.ApiResponse;
import org.siksnaghae.fgmate.common.response.BaseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/app/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{refriId}")
    public ApiResponse<List<ProductDto>> findAllProducts(@PathVariable("refriId") Long refriId) {
        try {
            List<ProductDto> products = productService.findAllProducts(refriId);
            return new ApiResponse<>(products);
        } catch (BaseException exception) {
            return new ApiResponse<>(exception.getStatus());
        }
    }

    @GetMapping
    public ApiResponse<ProductDto> findProduct(@RequestParam Long productId) {
        try {
            ProductDto product = productService.findProduct(productId);
            return new ApiResponse<>(product);
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @PostMapping
    public ApiResponse<String> saveProduct(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return new ApiResponse<>("성공");
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @PostMapping(value = "/upload2", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<String> testProduct(
            @RequestPart(value = "product") Product product,
            @RequestPart(value = "file") MultipartFile file)
    {
        try {
            String fileUrl = productService.saveProduct(product, file);
            return new ApiResponse<>(fileUrl);
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @PostMapping(value = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<String> saveProduct(
            @RequestPart(value = "product") Product product,
            @RequestPart(value = "file") MultipartFile file)
    {
        try {
            String fileUrl = "";
            if (!file.isEmpty()) {
                fileUrl = productService.saveImg(file);
                product.setProductImg(fileUrl);
            }
            productService.saveProduct(product);
            return new ApiResponse<>(fileUrl);
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }


    @PatchMapping
    public ApiResponse<String> modifyProduct(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return new ApiResponse<>("정보 수정 완료");
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<String> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return new ApiResponse<>("선택하신 식품이 삭제되었습니다.");
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }
}
