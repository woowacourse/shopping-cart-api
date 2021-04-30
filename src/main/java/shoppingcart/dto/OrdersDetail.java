//package shoppingcart.dto;
//
///**
// * table
// *     id         bigint  not null auto_increment,
// *     orders_id  bigint  not null,
// *     product_id bigint  not null,
// *     quantity   integer not null,
// */
//
///**
// * response
// *     private final Long productId;
// *     private final int price;
// *     private final String name;
// *     private final String imageUrl;
// *     private int quantity;
// */
//
///**
// * request
// *     @NotNull
// *     private final Long cartId;
// *     @Min(0)
// *     private final int quantity;
// */
//public class OrdersDetail {
//
//    private final Long productId;
//    private final int quantity;
//
//    public OrdersDetail(final Long productId, final int quantity) {
//        this.productId = productId;
//        this.quantity = quantity;
//    }
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//}
