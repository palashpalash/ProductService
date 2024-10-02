package com.example.ProductService.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.io.Serializable;

@Entity
@Table(name="Product")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be a positive value")
    private Double price;
    @Size(max = 255, message = "Description can be at most 255 characters")
    private String description;
}
