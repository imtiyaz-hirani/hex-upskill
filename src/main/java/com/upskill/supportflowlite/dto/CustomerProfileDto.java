package com.upskill.supportflowlite.dto;

import com.upskill.supportflowlite.enums.Priority;
import com.upskill.supportflowlite.enums.Status;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CustomerProfileDto {
    private int id;
    private String name;

     public static class TicketDto{
        private int id;
        private String subject;
        private Priority priority;
        private Status status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public Priority getPriority() {
            return priority;
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }
    }

     public static class ProductDto{
        private int id;
        private String planName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }
    }

    private List<TicketDto> listTicketsDto;
    private List<ProductDto> listProductDto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TicketDto> getListTicketsDto() {
        return listTicketsDto;
    }

    public void setListTicketsDto(List<TicketDto> listTicketsDto) {
        this.listTicketsDto = listTicketsDto;
    }

    public List<ProductDto> getListProductDto() {
        return listProductDto;
    }

    public void setListProductDto(List<ProductDto> listProductDto) {
        this.listProductDto = listProductDto;
    }
}


