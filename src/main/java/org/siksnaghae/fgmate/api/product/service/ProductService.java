package org.siksnaghae.fgmate.api.product.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.siksnaghae.fgmate.api.product.model.Product;
import org.siksnaghae.fgmate.api.product.model.ProductDto;
import org.siksnaghae.fgmate.api.product.repository.ProductRepository;
import org.siksnaghae.fgmate.common.response.BaseException;
import org.siksnaghae.fgmate.util.ImageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static org.siksnaghae.fgmate.common.response.BaseResponseStatus.DATABASE_ERROR;
import static org.siksnaghae.fgmate.common.response.BaseResponseStatus.IMAGE_UPLOAD_FAIL;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public List<ProductDto> findAllProducts(Long refrigeratorId) throws BaseException {
        try {
            List<Product> productList = productRepository.findByRefrigeratorId(refrigeratorId);
            return productList.stream()
                    .map(product -> new ProductDto(product))
                    .collect(Collectors.toList());

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public ProductDto findProduct(Long productsId) throws BaseException {
        try {
            return productRepository.findByProductIdDtl(productsId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void saveProduct(Product product) throws BaseException {
        try {
            productRepository.save(product);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public String saveProduct(Product product, MultipartFile file) throws BaseException {
        String fileUrl = "";
        if (!file.isEmpty()) {
            fileUrl = ImageUtil.saveImg(file, amazonS3Client, bucketName);
            product.setProductImg(fileUrl);
        }
        try {
            productRepository.save(product);
            return fileUrl;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public String saveImg(MultipartFile file) throws BaseException {
        try {

            String fileName = file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucketName,fileName, file.getInputStream(), metadata);
            URL fileUrl = amazonS3Client.getUrl(bucketName, fileName);

            return fileUrl.toString();
        } catch (Exception exception) {
            throw new BaseException(IMAGE_UPLOAD_FAIL);
        }
    }

    public void deleteProduct(Long productId) throws BaseException {
        try {
            productRepository.deleteById(productId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
