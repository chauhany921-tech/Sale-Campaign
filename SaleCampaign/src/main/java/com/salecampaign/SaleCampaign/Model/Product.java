    package com.salecampaign.SaleCampaign.Model;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "tblproduct")
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "pid")
        private int pid;

        @Column
        private String name;

        @Column
        private int price;

        @Column(name = "curr_price")
        private int currentPrice;

        @Column
        private double discount;

        @Column(name = "inventory_count")
        private int inventoryCount;

        public Product(){}

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(int currentPrice) {
            this.currentPrice = currentPrice;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public int getInventoryCount() {
            return inventoryCount;
        }

        public void setInventoryCount(int inventoryCount) {
            this.inventoryCount = inventoryCount;
        }
    }
