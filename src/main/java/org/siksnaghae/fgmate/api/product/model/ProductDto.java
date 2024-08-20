package org.siksnaghae.fgmate.api.product.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String productImg;
    private String date;
    private String description;

    @QueryProjection
    public ProductDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productImg = product.getProductImg();
        this.date = product.getDate();
        this.description = product.getDescription();
    }

}
