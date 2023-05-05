package com.example.cuahangbantraicay.model;

import java.util.Date;

public class Products {
        private Integer id;
        private String name;
        private String image;
        private Float price_in;
        private Float price_sell;
        private String content;
        private Integer category_id;
        private Integer discount_id;
        private Integer quantity;
        private Integer quantity_sold;
        private Boolean status;
        private Date createdAt;
        private Date updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Float getPrice_in() {
            return price_in;
        }

        public void setPrice_in(Float price_in) {
            this.price_in = price_in;
        }

        public Float getPrice_sell() {
            return price_sell;
        }

        public void setPrice_sell(Float price_sell) {
            this.price_sell = price_sell;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getCategory_id() {
            return category_id;
        }

        public void setCategory_id(Integer category_id) {
            this.category_id = category_id;
        }

        public Integer getDiscount_id() {
            return discount_id;
        }

        public void setDiscount_id(Integer discount_id) {
            this.discount_id = discount_id;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Integer getQuantity_sold() {
            return quantity_sold;
        }

        public void setQuantity_sold(Integer quantity_sold) {
            this.quantity_sold = quantity_sold;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
