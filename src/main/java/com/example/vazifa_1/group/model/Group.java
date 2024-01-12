package com.example.vazifa_1.group.model;

import com.example.vazifa_1.student.model.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "groupSt")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "ERROR : Guruh nomi bo'sh bo'lishi mumkin emas!")
    private String name;

    private int studentCount;

    private LocalDateTime createdAt;

    private String createdBy;

    @OneToMany(mappedBy = "group")
    private List<Student> students;
}


