    package com.salecampaign.SaleCampaign.Model;

    import jakarta.persistence.*;

    import java.time.LocalDate;
    import java.util.Date;
    import java.util.List;

    @Entity
    @Table(name = "tblcampaign")
    public class Campaign {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private int id;

        @Column(name = "start_date")
        private LocalDate startDate;

        @Column(name = "ending_date")
        private LocalDate endDate;

        @Column
        private String title;

        @Enumerated(EnumType.STRING)
        @Column
        private StatusEnum status;


        public Campaign() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public StatusEnum getStatus() {
            return status;
        }

        public void setStatus(StatusEnum status) {
            this.status = status;
        }
    }
