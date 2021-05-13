# 상품

### 상품 목록 조회

`GET /api/products` 

```json
# 응답
200
[
    {
        "product_id": 1,
        "price": 10000,
        "name": "치킨",
        "image_url": "http://example.com/chicken.jpg"
    },
    ...
]
```

### 상품 추가

`POST /api/products`

```json
# 요청
{
    "price": 10000,
    "name": "치킨",
    "image_url": "http://example.com/chicken.jpg"
}
```

```json
# 응답
201
header
Location: /api/products/{product_id}
```

### 상품 단일 조회

`GET /api/products/{product_id}`

```json
# 응답
200
{
    "product_id": 1,
    "price": 10000,
    "name": "치킨",
    "image_url": "http://example.com/chicken.jpg"
}
```

### 상품 단일 삭제

`DELETE /api/products/{product_id}`

```json
# 응답
204
```

# 장바구니

### 장바구니 아이템 목록 조회

`GET /api/customers/{customer_name}/carts`

```json
# 응답
200
[
    {
        "cart_id": 1,
        "price": 10000,
        "name": "치킨",
        "image_url": "http://example.com/chicken.jpg"
    },
    ...
]
```

### 장바구니 아이템 추가

`POST /api/customers/{customer_name}/carts`

```json
# 요청
{
    "product_id": 1
}
```

```json
# 응답
201
header
Location: /api/customers/{customer_name}/carts/{cart_id}
```

### 장바구니 아이템 단일 삭제

`DELETE /api/customers/{customer_name}/carts/{cart_id}`

```json
# 응답
204
```

# 주문

### 주문 추가(주문하기)

`POST /api/customers/{customer_name}/orders`

```json
# 요청
[
    {
        "cart_id": 1,
        "quantity": 5
    },
    ...
]
```

```json
# 응답
201
header
Location: /api/customers/{customer_name}/orders/{order_id}
```

### 주문 목록(내역) 조회

`GET /api/customers/{customer_name}/orders`

```json
# 응답
200
[
    {
        "order_id": 1,
        "order_details": [
            {
                "product_id": 1,
                "price": 10000,
                "name": "치킨",
                "image_url": "http://example.com/chicken.jpg",
                "quantity": 5
            },
            ...
        ]
    },
    ...
]
```

### 주문 단일 조회

`GET /api/customers/{customer_name}/orders/{order_id}`

```json
# 응답
200
{
    "order_id": 1,
    "order_details": [
        {
            "product_id": 1,
            "price": 10000,
            "name": "치킨",
            "image_url": "http://example.com/chicken.jpg",
            "quantity": 5
        },
        ...
    ]
}
```
