# 🛍️ Sale Campaign Management System

A sophisticated **Spring Boot REST API** application designed to manage e-commerce sale events and product discounts. This system automates the complete lifecycle of sale campaigns, handles inventory-linked pricing, and maintains a detailed price history using **Spring Data JPA**, **MySQL**, and **DTO (Data Transfer Object)** patterns.

---

## 🌟 Key Features

* **Campaign Scheduling**: Create and manage sale campaigns with automated `START_DATE` and `END_DATE` tracking.
* **Bulk Product Management**: Admin capability to seed the database with multiple products in a single request.
* **Dynamic Discounting**: Apply specific discount percentages to products; the system automatically calculates the `Current Price` based on original price.
* **Automated Status Tracking**: Uses a `StatusEnum` to manage campaign states like `UPCOMING`, `ACTIVE`, and `ENDED`.
* **Price History Logging**: Maintains a dedicated history table (`ProductHistory`) to track every price change for auditing and analytics.
* **Centralized Error Handling**: Robust validation and exception management using a `GlobalExceptionHandler` and custom `NotFoundException`.

---

## 🛠️ Tech Stack & Architecture

* **Framework**: Spring Boot 3.x
* **Language**: Java 17+
* **Data Access**: Spring Data JPA
* **Database**: MySQL / H2
* **Architecture Pattern**: Controller-Service-Repository with a dedicated DTO (Data Transfer Object) layer.

---

## 📂 Project Modules

* **`com.salecampaign.SaleCampaign.Controller`**: REST Endpoints for Products and Campaign management.
* **`com.salecampaign.SaleCampaign.Model`**: Core Entities (`Product`, `Campaign`, `ProductDiscount`, `ProductHistory`) representing database tables.
* **`com.salecampaign.SaleCampaign.DTO`**: Data objects for secure request/response handling and validation.
* **`com.salecampaign.SaleCampaign.Services`**: Business logic for campaign flow, status updates, and discount calculation.
* **`com.salecampaign.SaleCampaign.Repository`**: JPA interfaces for data persistence.

---

## 📝 API Endpoints Documentation

### 📦 Product Management (`/product`)
| Method | Endpoint | Parameters / Body | Description |
| :--- | :--- | :--- | :--- |
| **POST** | `/save-products` | `List<ProductDto>` | Bulk upload new products to the inventory. |
| **GET** | `/{pid}` | `PathVar: pid` | Fetches details of a specific product. |
| **GET** | `/products` | `pageNum, pageSize` | Lists all products with built-in pagination. |
| **POST** | `/discount` | `CreateCampaignDTO` | Initializes a new Sale Campaign with discounts. |

### 📅 Campaign Operations (`/campaign`)
| Method | Endpoint | Parameters | Description |
| :--- | :--- | :--- | :--- |
| **GET** | `/{cid}` | `PathVar: cid` | Fetches complete campaign details and associated discounts. |

---

## ⚙️ Local Setup Guide

1. **Clone the project**:
   ```bash
   git clone [https://github.com/chauhany921-tech/SaleCampaign.git](https://github.com/chauhany921-tech/SaleCampaign.git)
   🧪 Testing with Postman Example
Step 1: Save Bulk Products
URL: http://localhost:8080/product/save-products

Method: POST

Body (JSON):

JSON

[
  {
    "name": "Gaming Laptop",
    "price": 80000,
    "currentPrice": 80000,
    "discount": 0,
    "inventoryCount": 50
  },
  {
    "name": "Wireless Mouse",
    "price": 1500,
    "currentPrice": 1500,
    "discount": 0,
    "inventoryCount": 200
  }
]
Step 2: Create a Sale Campaign
URL: http://localhost:8080/product/discount

Method: POST

Body (JSON):

JSON

{
    "title": "Diwali Grand Sale",
    "startDate": "2023-11-10",
    "endDate": "2023-11-15",
    "campaignDiscount": [
        {
            "productId": 1,
            "discount": 15
        },
        {
            "productId": 2,
            "discount": 10
        }
    ]
}
