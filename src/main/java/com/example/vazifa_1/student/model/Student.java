package com.example.vazifa_1.student.model;

import com.example.vazifa_1.group.model.Group;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Ismingiz bo'sh qolmasligi kerak!")
    private String fullName;

    @NotNull(message = "Yoshingiz ko'rsatilgan bo'lishi kerak!")
    @Min(value = 15, message = "Yosh {value} dan kichik bo'lmasligi kerak")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    private LocalDateTime createdAt;

    private String createdBy;
}


